/**
  * Copyright 2011 National ICT Australia Limited
  *
  * Licensed under the Apache License, Version 2.0 (the "License");
  * you may not use this file except in compliance with the License.
  * You may obtain a copy of the License at
  *
  * http://www.apache.org/licenses/LICENSE-2.0
  *
  * Unless required by applicable law or agreed to in writing, software
  * distributed under the License is distributed on an "AS IS" BASIS,
  * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
  * See the License for the specific language governing permissions and
  * limitations under the License.
  */
package com.nicta.scoobi.io.avro

import java.io.IOException
import org.apache.commons.logging.Log
import org.apache.commons.logging.LogFactory
import org.apache.hadoop.conf.Configuration
import org.apache.hadoop.fs.Path
import org.apache.hadoop.fs.FileSystem
import org.apache.hadoop.io.NullWritable
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat
import org.apache.hadoop.mapreduce.Job
import org.apache.avro.mapred.AvroKey
import org.apache.avro.mapreduce.AvroKeyInputFormat
import scala.collection.JavaConversions._
import com.nicta.scoobi.DList
import com.nicta.scoobi.WireFormat
import com.nicta.scoobi.io.DataSource
import com.nicta.scoobi.io.InputConverter
import com.nicta.scoobi.io.Helper
import com.nicta.scoobi.impl.plan.Smart
import com.nicta.scoobi.impl.plan.AST
import org.apache.avro.Schema
import org.apache.avro.generic.GenericRecord
import org.apache.avro.specific.SpecificRecord
import java.io.DataOutput
import java.io.DataInput
import java.io.ByteArrayOutputStream
import org.apache.avro.io.EncoderFactory
import org.apache.avro.generic.GenericDatumWriter
import org.apache.avro.generic.GenericDatumReader
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import org.apache.avro.specific.SpecificDatumWriter
import org.apache.avro.specific.SpecificDatumReader
import org.apache.avro.io.DatumWriter
import org.apache.avro.io.DatumReader
import org.apache.avro.io.Encoder
import org.apache.avro.file.DataFileWriter
import org.apache.avro.file.DataFileStream


/** Smart functions for materializing distributed lists by loading Avro files. */
object AvroInput {
  lazy val logger = LogFactory.getLog("scoobi.AvroInput")

   
  implicit def SpecificRecordFmt[T <: SpecificRecord] = new WireFormat[T] {
    def toWire(x: T, out: DataOutput) { 
      val schema : Schema = x.getSchema
      val byteStream : ByteArrayOutputStream  = new ByteArrayOutputStream
      val encoder : Encoder = EncoderFactory.get.binaryEncoder(byteStream, null)
      val datumWriter : SpecificDatumWriter[T] = new SpecificDatumWriter[T](schema)
      val dataFileWriter : DataFileWriter[T] = new DataFileWriter[T]( datumWriter)
      dataFileWriter.create(schema, byteStream);
      byteStream.flush
      val byteArray = byteStream.toByteArray
      out.writeInt(byteArray.size)
      out.write(byteArray) 
    }
    def fromWire(in: DataInput): T = {
      val size = in.readInt()
      val barr = new Array[Byte](size)
      in.readFully(barr)
    	  val byteStream : ByteArrayInputStream = new ByteArrayInputStream(barr)
      val datumReader : DatumReader[T] = new SpecificDatumReader[T]
    	  val dataFileReader : DataFileStream[T] = new DataFileStream[T](byteStream, datumReader)
    	  val record : T = dataFileReader.next()
    	  record
    }
  }


  /** Create a new DList from the contents of one or more Avro files. The type of the DList must conform to
    * the schema types allowed by Avro, as constrained by the 'AvroSchema' type class. In the case of a directory
    * being specified, the input forms all the files in that directory. */
  def fromAvroFile[A : Manifest : WireFormat : AvroSchema](paths: String*): DList[A] = fromAvroFile(List(paths: _*))


  /** Create a new DList from the contents of a list of one or more Avro files. The type of the
    * DList must conform to the schema types allowed by Avro, as constrained by the 'AvroSchema' type
    * class. In the case of a directory being specified, the input forms all the files in
    * that directory. */
  def fromAvroFile[A : Manifest : WireFormat : AvroSchema](paths: List[String]): DList[A] = {
    val sch = implicitly[AvroSchema[A]]

    val source = new DataSource[AvroKey[sch.AvroType], NullWritable, A] {
      private val inputPaths = paths.map(p => new Path(p))

      val inputFormat = classOf[AvroKeyInputFormat[sch.AvroType]]

      def inputCheck() = inputPaths foreach { p =>
        if (Helper.pathExists(p)) {
          logger.info("Input path: " + p.toUri.toASCIIString + " (" + Helper.sizeString(Helper.pathSize(p)) + ")")
          logger.debug("Input schema: " + sch.schema)
        } else {
           throw new IOException("Input path " + p + " does not exist.")
        }
      }

      def inputConfigure(job: Job) = {
        inputPaths foreach { p => FileInputFormat.addInputPath(job, p) }
        job.getConfiguration.set("avro.schema.input.key", sch.schema.toString)
      }

      def inputSize(): Long = inputPaths.map(p => Helper.pathSize(p)).sum

      val inputConverter = new InputConverter[AvroKey[sch.AvroType], NullWritable, A] {
        def fromKeyValue(context: InputContext, k: AvroKey[sch.AvroType], v: NullWritable) = sch.fromAvro(k.datum)
      }
    }

    DList.fromSource(source)
  }
  
 def fromAvroGeneric[T <:GenericRecord : Manifest](schema: Schema, paths: String*): DList[T ] = fromAvroGeneric(schema, List(paths: _*))
  
  def fromAvroGeneric[T<:GenericRecord : Manifest](schema : Schema, paths: List[String]): DList[T] = {

	
    val source = new DataSource[AvroKey[T], NullWritable, T] {
      private val inputPaths = paths.map(p => new Path(p))

      val inputFormat = classOf[AvroKeyInputFormat[T]]

      def inputCheck() = inputPaths foreach { p =>
        if (Helper.pathExists(p)) {
          logger.info("Input path: " + p.toUri.toASCIIString + " (" + Helper.sizeString(Helper.pathSize(p)) + ")")
          logger.debug("Input schema: " + schema)
        } else {
           throw new IOException("Input path " + p + " does not exist.")
        }
      }

      def inputConfigure(job: Job) = {
        inputPaths foreach { p => FileInputFormat.addInputPath(job, p) }
        job.getConfiguration.set("avro.schema.input.key", schema.toString)
      }

      def inputSize(): Long = inputPaths.map(p => Helper.pathSize(p)).sum

      val inputConverter = new InputConverter[AvroKey[T], NullWritable, T] {
        def fromKeyValue(context: InputContext, k: AvroKey[T], v: NullWritable) = k.datum.asInstanceOf[T]
      }
    }
    implicit def GenericRecordFmt[T <: GenericRecord] = new WireFormat[T] {
	    	def toWire(x: T, out: DataOutput) { 
	      val schema : Schema = x.getSchema
	      val byteStream : ByteArrayOutputStream  = new ByteArrayOutputStream
	      val encoder : Encoder = EncoderFactory.get.binaryEncoder(byteStream, null)
	      val datumWriter : GenericDatumWriter[T] = new GenericDatumWriter[T](schema)
	      val dataFileWriter : DataFileWriter[T] = new DataFileWriter[T]( datumWriter)
	      dataFileWriter.create(schema, byteStream);
	      byteStream.flush
	      val byteArray = byteStream.toByteArray
	      out.writeInt(byteArray.size)
	      out.write(byteArray) 
	    }
	    def fromWire(in: DataInput): T = {
	      val size = in.readInt()
	      val barr = new Array[Byte](size)
	      in.readFully(barr)
	    	  val byteStream : ByteArrayInputStream = new ByteArrayInputStream(barr)
	      val datumReader : DatumReader[T] = new GenericDatumReader[T]
	    	  val dataFileReader : DataFileStream[T] = new DataFileStream[T](byteStream, datumReader)
	    	  val record : T = dataFileReader.next()
	    	  record
	    }
  }

    DList.fromSource(source)
  }
  
 
}
