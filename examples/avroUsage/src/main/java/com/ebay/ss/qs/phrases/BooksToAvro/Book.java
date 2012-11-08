/**
 * Autogenerated by Avro
 * 
 * DO NOT EDIT DIRECTLY
 */
package com.ebay.ss.qs.phrases.BooksToAvro;  
@SuppressWarnings("all")
/** Auto-generated schema */
public class Book extends org.apache.avro.specific.SpecificRecordBase implements org.apache.avro.specific.SpecificRecord {
  public static final org.apache.avro.Schema SCHEMA$ = new org.apache.avro.Schema.Parser().parse("{\"type\":\"record\",\"name\":\"Book\",\"namespace\":\"com.ebay.ss.qs.phrases.BooksToAvro\",\"doc\":\"Auto-generated schema\",\"fields\":[{\"name\":\"title\",\"type\":\"string\",\"doc\":\"Auto-Generated Field\"},{\"name\":\"author\",\"type\":[\"null\",\"string\"],\"doc\":\"Auto-Generated Field\"},{\"name\":\"year\",\"type\":[\"null\",\"int\"],\"doc\":\"Auto-Generated Field\"},{\"name\":\"other\",\"type\":[\"null\",\"string\"],\"doc\":\"Auto-Generated Field\"}]}");
  /** Auto-Generated Field */
  @Deprecated public java.lang.CharSequence title;
  /** Auto-Generated Field */
  @Deprecated public java.lang.CharSequence author;
  /** Auto-Generated Field */
  @Deprecated public java.lang.Integer year;
  /** Auto-Generated Field */
  @Deprecated public java.lang.CharSequence other;
  public org.apache.avro.Schema getSchema() { return SCHEMA$; }
  // Used by DatumWriter.  Applications should not call. 
  public java.lang.Object get(int field$) {
    switch (field$) {
    case 0: return title;
    case 1: return author;
    case 2: return year;
    case 3: return other;
    default: throw new org.apache.avro.AvroRuntimeException("Bad index");
    }
  }
  // Used by DatumReader.  Applications should not call. 
  @SuppressWarnings(value="unchecked")
  public void put(int field$, java.lang.Object value$) {
    switch (field$) {
    case 0: title = (java.lang.CharSequence)value$; break;
    case 1: author = (java.lang.CharSequence)value$; break;
    case 2: year = (java.lang.Integer)value$; break;
    case 3: other = (java.lang.CharSequence)value$; break;
    default: throw new org.apache.avro.AvroRuntimeException("Bad index");
    }
  }

  /**
   * Gets the value of the 'title' field.
   * Auto-Generated Field   */
  public java.lang.CharSequence getTitle() {
    return title;
  }

  /**
   * Sets the value of the 'title' field.
   * Auto-Generated Field   * @param value the value to set.
   */
  public void setTitle(java.lang.CharSequence value) {
    this.title = value;
  }

  /**
   * Gets the value of the 'author' field.
   * Auto-Generated Field   */
  public java.lang.CharSequence getAuthor() {
    return author;
  }

  /**
   * Sets the value of the 'author' field.
   * Auto-Generated Field   * @param value the value to set.
   */
  public void setAuthor(java.lang.CharSequence value) {
    this.author = value;
  }

  /**
   * Gets the value of the 'year' field.
   * Auto-Generated Field   */
  public java.lang.Integer getYear() {
    return year;
  }

  /**
   * Sets the value of the 'year' field.
   * Auto-Generated Field   * @param value the value to set.
   */
  public void setYear(java.lang.Integer value) {
    this.year = value;
  }

  /**
   * Gets the value of the 'other' field.
   * Auto-Generated Field   */
  public java.lang.CharSequence getOther() {
    return other;
  }

  /**
   * Sets the value of the 'other' field.
   * Auto-Generated Field   * @param value the value to set.
   */
  public void setOther(java.lang.CharSequence value) {
    this.other = value;
  }

  /** Creates a new Book RecordBuilder */
  public static com.ebay.ss.qs.phrases.BooksToAvro.Book.Builder newBuilder() {
    return new com.ebay.ss.qs.phrases.BooksToAvro.Book.Builder();
  }
  
  /** Creates a new Book RecordBuilder by copying an existing Builder */
  public static com.ebay.ss.qs.phrases.BooksToAvro.Book.Builder newBuilder(com.ebay.ss.qs.phrases.BooksToAvro.Book.Builder other) {
    return new com.ebay.ss.qs.phrases.BooksToAvro.Book.Builder(other);
  }
  
  /** Creates a new Book RecordBuilder by copying an existing Book instance */
  public static com.ebay.ss.qs.phrases.BooksToAvro.Book.Builder newBuilder(com.ebay.ss.qs.phrases.BooksToAvro.Book other) {
    return new com.ebay.ss.qs.phrases.BooksToAvro.Book.Builder(other);
  }
  
  /**
   * RecordBuilder for Book instances.
   */
  public static class Builder extends org.apache.avro.specific.SpecificRecordBuilderBase<Book>
    implements org.apache.avro.data.RecordBuilder<Book> {

    private java.lang.CharSequence title;
    private java.lang.CharSequence author;
    private java.lang.Integer year;
    private java.lang.CharSequence other;

    /** Creates a new Builder */
    private Builder() {
      super(com.ebay.ss.qs.phrases.BooksToAvro.Book.SCHEMA$);
    }
    
    /** Creates a Builder by copying an existing Builder */
    private Builder(com.ebay.ss.qs.phrases.BooksToAvro.Book.Builder other) {
      super(other);
    }
    
    /** Creates a Builder by copying an existing Book instance */
    private Builder(com.ebay.ss.qs.phrases.BooksToAvro.Book other) {
            super(com.ebay.ss.qs.phrases.BooksToAvro.Book.SCHEMA$);
      if (isValidValue(fields()[0], other.title)) {
        this.title = (java.lang.CharSequence) data().deepCopy(fields()[0].schema(), other.title);
        fieldSetFlags()[0] = true;
      }
      if (isValidValue(fields()[1], other.author)) {
        this.author = (java.lang.CharSequence) data().deepCopy(fields()[1].schema(), other.author);
        fieldSetFlags()[1] = true;
      }
      if (isValidValue(fields()[2], other.year)) {
        this.year = (java.lang.Integer) data().deepCopy(fields()[2].schema(), other.year);
        fieldSetFlags()[2] = true;
      }
      if (isValidValue(fields()[3], other.other)) {
        this.other = (java.lang.CharSequence) data().deepCopy(fields()[3].schema(), other.other);
        fieldSetFlags()[3] = true;
      }
    }

    /** Gets the value of the 'title' field */
    public java.lang.CharSequence getTitle() {
      return title;
    }
    
    /** Sets the value of the 'title' field */
    public com.ebay.ss.qs.phrases.BooksToAvro.Book.Builder setTitle(java.lang.CharSequence value) {
      validate(fields()[0], value);
      this.title = value;
      fieldSetFlags()[0] = true;
      return this; 
    }
    
    /** Checks whether the 'title' field has been set */
    public boolean hasTitle() {
      return fieldSetFlags()[0];
    }
    
    /** Clears the value of the 'title' field */
    public com.ebay.ss.qs.phrases.BooksToAvro.Book.Builder clearTitle() {
      title = null;
      fieldSetFlags()[0] = false;
      return this;
    }

    /** Gets the value of the 'author' field */
    public java.lang.CharSequence getAuthor() {
      return author;
    }
    
    /** Sets the value of the 'author' field */
    public com.ebay.ss.qs.phrases.BooksToAvro.Book.Builder setAuthor(java.lang.CharSequence value) {
      validate(fields()[1], value);
      this.author = value;
      fieldSetFlags()[1] = true;
      return this; 
    }
    
    /** Checks whether the 'author' field has been set */
    public boolean hasAuthor() {
      return fieldSetFlags()[1];
    }
    
    /** Clears the value of the 'author' field */
    public com.ebay.ss.qs.phrases.BooksToAvro.Book.Builder clearAuthor() {
      author = null;
      fieldSetFlags()[1] = false;
      return this;
    }

    /** Gets the value of the 'year' field */
    public java.lang.Integer getYear() {
      return year;
    }
    
    /** Sets the value of the 'year' field */
    public com.ebay.ss.qs.phrases.BooksToAvro.Book.Builder setYear(java.lang.Integer value) {
      validate(fields()[2], value);
      this.year = value;
      fieldSetFlags()[2] = true;
      return this; 
    }
    
    /** Checks whether the 'year' field has been set */
    public boolean hasYear() {
      return fieldSetFlags()[2];
    }
    
    /** Clears the value of the 'year' field */
    public com.ebay.ss.qs.phrases.BooksToAvro.Book.Builder clearYear() {
      year = null;
      fieldSetFlags()[2] = false;
      return this;
    }

    /** Gets the value of the 'other' field */
    public java.lang.CharSequence getOther() {
      return other;
    }
    
    /** Sets the value of the 'other' field */
    public com.ebay.ss.qs.phrases.BooksToAvro.Book.Builder setOther(java.lang.CharSequence value) {
      validate(fields()[3], value);
      this.other = value;
      fieldSetFlags()[3] = true;
      return this; 
    }
    
    /** Checks whether the 'other' field has been set */
    public boolean hasOther() {
      return fieldSetFlags()[3];
    }
    
    /** Clears the value of the 'other' field */
    public com.ebay.ss.qs.phrases.BooksToAvro.Book.Builder clearOther() {
      other = null;
      fieldSetFlags()[3] = false;
      return this;
    }

    @Override
    public Book build() {
      try {
        Book record = new Book();
        record.title = fieldSetFlags()[0] ? this.title : (java.lang.CharSequence) defaultValue(fields()[0]);
        record.author = fieldSetFlags()[1] ? this.author : (java.lang.CharSequence) defaultValue(fields()[1]);
        record.year = fieldSetFlags()[2] ? this.year : (java.lang.Integer) defaultValue(fields()[2]);
        record.other = fieldSetFlags()[3] ? this.other : (java.lang.CharSequence) defaultValue(fields()[3]);
        return record;
      } catch (Exception e) {
        throw new org.apache.avro.AvroRuntimeException(e);
      }
    }
  }
}