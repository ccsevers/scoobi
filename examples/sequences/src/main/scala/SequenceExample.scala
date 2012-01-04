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

import com.nicta.scoobi._
import com.nicta.scoobi.Scoobi._
import com.nicta.scoobi.io.text._
import java.io._
import com.nicta.scoobi.io.sequence.SequenceFileInput
import com.nicta.scoobi.io.sequence.SequenceFileOutput
import org.apache.hadoop.io.Text
import com.nicta.scoobi.io.sequence.OutputConverter

object SequenceExample {
  implicit val converter = new OutputConverter[Text, Text, (Text, Text)] {
     def toKeyValue(s: (Text,Text)) = (s._1, s._2)
  }
  
  implicit def textIdent(text : Text) : Text = text
  
  def main(args: Array[String]) = withHadoopArgs(args) { a =>
    val inputFile = a(0)
    val outputFile = a(1)
    val lines = SequenceFileInput.fromSequenceFile[Text, Text](inputFile).map(_._2.toString)
    DList.persist(SequenceFileOutput.toSequenceFile[String](lines, outputFile))
 
    //DList.persist(SequenceFileOutput.toSequenceFileX[Text, Text, Text,Text](lines, outputFile)(textIdent, textIdent))
  }
}
