/**
 * Autogenerated by Avro
 * 
 * DO NOT EDIT DIRECTLY
 */
package com.season.platform.common.avro;  
@SuppressWarnings("all")
@org.apache.avro.specific.AvroGenerated
public class base_users extends org.apache.avro.specific.SpecificRecordBase implements org.apache.avro.specific.SpecificRecord {
  private static final long serialVersionUID = 6736492948501854214L;
  public static final org.apache.avro.Schema SCHEMA$ = new org.apache.avro.Schema.Parser().parse("{\"type\":\"record\",\"name\":\"base_users\",\"namespace\":\"example.avro\",\"fields\":[{\"name\":\"_id\",\"type\":\"string\"},{\"name\":\"updated\",\"type\":[\"string\",\"null\"]},{\"name\":\"lastEntered\",\"type\":[\"string\",\"null\"]}]}");
  public static org.apache.avro.Schema getClassSchema() { return SCHEMA$; }
  @Deprecated public CharSequence _id;
  @Deprecated public CharSequence updated;
  @Deprecated public CharSequence lastEntered;

  /**
   * Default constructor.  Note that this does not initialize fields
   * to their default values from the schema.  If that is desired then
   * one should use <code>newBuilder()</code>. 
   */
  public base_users() {}

  /**
   * All-args constructor.
   */
  public base_users(CharSequence _id, CharSequence updated, CharSequence lastEntered) {
    this._id = _id;
    this.updated = updated;
    this.lastEntered = lastEntered;
  }

  public org.apache.avro.Schema getSchema() { return SCHEMA$; }
  // Used by DatumWriter.  Applications should not call. 
  public Object get(int field$) {
    switch (field$) {
    case 0: return _id;
    case 1: return updated;
    case 2: return lastEntered;
    default: throw new org.apache.avro.AvroRuntimeException("Bad index");
    }
  }
  // Used by DatumReader.  Applications should not call. 
  @SuppressWarnings(value="unchecked")
  public void put(int field$, Object value$) {
    switch (field$) {
    case 0: _id = (CharSequence)value$; break;
    case 1: updated = (CharSequence)value$; break;
    case 2: lastEntered = (CharSequence)value$; break;
    default: throw new org.apache.avro.AvroRuntimeException("Bad index");
    }
  }

  /**
   * Gets the value of the '_id' field.
   */
  public CharSequence getId$1() {
    return _id;
  }

  /**
   * Sets the value of the '_id' field.
   * @param value the value to set.
   */
  public void setId$1(CharSequence value) {
    this._id = value;
  }

  /**
   * Gets the value of the 'updated' field.
   */
  public CharSequence getUpdated() {
    return updated;
  }

  /**
   * Sets the value of the 'updated' field.
   * @param value the value to set.
   */
  public void setUpdated(CharSequence value) {
    this.updated = value;
  }

  /**
   * Gets the value of the 'lastEntered' field.
   */
  public CharSequence getLastEntered() {
    return lastEntered;
  }

  /**
   * Sets the value of the 'lastEntered' field.
   * @param value the value to set.
   */
  public void setLastEntered(CharSequence value) {
    this.lastEntered = value;
  }

  /**
   * Creates a new base_users RecordBuilder.
   * @return A new base_users RecordBuilder
   */
  public static base_users.Builder newBuilder() {
    return new base_users.Builder();
  }
  
  /**
   * Creates a new base_users RecordBuilder by copying an existing Builder.
   * @param other The existing builder to copy.
   * @return A new base_users RecordBuilder
   */
  public static base_users.Builder newBuilder(base_users.Builder other) {
    return new base_users.Builder(other);
  }
  
  /**
   * Creates a new base_users RecordBuilder by copying an existing base_users instance.
   * @param other The existing instance to copy.
   * @return A new base_users RecordBuilder
   */
  public static base_users.Builder newBuilder(base_users other) {
    return new base_users.Builder(other);
  }
  
  /**
   * RecordBuilder for base_users instances.
   */
  public static class Builder extends org.apache.avro.specific.SpecificRecordBuilderBase<base_users>
    implements org.apache.avro.data.RecordBuilder<base_users> {

    private CharSequence _id;
    private CharSequence updated;
    private CharSequence lastEntered;

    /** Creates a new Builder */
    private Builder() {
      super(base_users.SCHEMA$);
    }
    
    /**
     * Creates a Builder by copying an existing Builder.
     * @param other The existing Builder to copy.
     */
    private Builder(base_users.Builder other) {
      super(other);
      if (isValidValue(fields()[0], other._id)) {
        this._id = data().deepCopy(fields()[0].schema(), other._id);
        fieldSetFlags()[0] = true;
      }
      if (isValidValue(fields()[1], other.updated)) {
        this.updated = data().deepCopy(fields()[1].schema(), other.updated);
        fieldSetFlags()[1] = true;
      }
      if (isValidValue(fields()[2], other.lastEntered)) {
        this.lastEntered = data().deepCopy(fields()[2].schema(), other.lastEntered);
        fieldSetFlags()[2] = true;
      }
    }
    
    /**
     * Creates a Builder by copying an existing base_users instance
     * @param other The existing instance to copy.
     */
    private Builder(base_users other) {
            super(base_users.SCHEMA$);
      if (isValidValue(fields()[0], other._id)) {
        this._id = data().deepCopy(fields()[0].schema(), other._id);
        fieldSetFlags()[0] = true;
      }
      if (isValidValue(fields()[1], other.updated)) {
        this.updated = data().deepCopy(fields()[1].schema(), other.updated);
        fieldSetFlags()[1] = true;
      }
      if (isValidValue(fields()[2], other.lastEntered)) {
        this.lastEntered = data().deepCopy(fields()[2].schema(), other.lastEntered);
        fieldSetFlags()[2] = true;
      }
    }

    /**
      * Gets the value of the '_id' field.
      * @return The value.
      */
    public CharSequence getId$1() {
      return _id;
    }

    /**
      * Sets the value of the '_id' field.
      * @param value The value of '_id'.
      * @return This builder.
      */
    public base_users.Builder setId$1(CharSequence value) {
      validate(fields()[0], value);
      this._id = value;
      fieldSetFlags()[0] = true;
      return this; 
    }

    /**
      * Checks whether the '_id' field has been set.
      * @return True if the '_id' field has been set, false otherwise.
      */
    public boolean hasId$1() {
      return fieldSetFlags()[0];
    }


    /**
      * Clears the value of the '_id' field.
      * @return This builder.
      */
    public base_users.Builder clearId$1() {
      _id = null;
      fieldSetFlags()[0] = false;
      return this;
    }

    /**
      * Gets the value of the 'updated' field.
      * @return The value.
      */
    public CharSequence getUpdated() {
      return updated;
    }

    /**
      * Sets the value of the 'updated' field.
      * @param value The value of 'updated'.
      * @return This builder.
      */
    public base_users.Builder setUpdated(CharSequence value) {
      validate(fields()[1], value);
      this.updated = value;
      fieldSetFlags()[1] = true;
      return this; 
    }

    /**
      * Checks whether the 'updated' field has been set.
      * @return True if the 'updated' field has been set, false otherwise.
      */
    public boolean hasUpdated() {
      return fieldSetFlags()[1];
    }


    /**
      * Clears the value of the 'updated' field.
      * @return This builder.
      */
    public base_users.Builder clearUpdated() {
      updated = null;
      fieldSetFlags()[1] = false;
      return this;
    }

    /**
      * Gets the value of the 'lastEntered' field.
      * @return The value.
      */
    public CharSequence getLastEntered() {
      return lastEntered;
    }

    /**
      * Sets the value of the 'lastEntered' field.
      * @param value The value of 'lastEntered'.
      * @return This builder.
      */
    public base_users.Builder setLastEntered(CharSequence value) {
      validate(fields()[2], value);
      this.lastEntered = value;
      fieldSetFlags()[2] = true;
      return this; 
    }

    /**
      * Checks whether the 'lastEntered' field has been set.
      * @return True if the 'lastEntered' field has been set, false otherwise.
      */
    public boolean hasLastEntered() {
      return fieldSetFlags()[2];
    }


    /**
      * Clears the value of the 'lastEntered' field.
      * @return This builder.
      */
    public base_users.Builder clearLastEntered() {
      lastEntered = null;
      fieldSetFlags()[2] = false;
      return this;
    }

    @Override
    public base_users build() {
      try {
        base_users record = new base_users();
        record._id = fieldSetFlags()[0] ? this._id : (CharSequence) defaultValue(fields()[0]);
        record.updated = fieldSetFlags()[1] ? this.updated : (CharSequence) defaultValue(fields()[1]);
        record.lastEntered = fieldSetFlags()[2] ? this.lastEntered : (CharSequence) defaultValue(fields()[2]);
        return record;
      } catch (Exception e) {
        throw new org.apache.avro.AvroRuntimeException(e);
      }
    }
  }

  private static final org.apache.avro.io.DatumWriter
    WRITER$ = new org.apache.avro.specific.SpecificDatumWriter(SCHEMA$);  

  @Override public void writeExternal(java.io.ObjectOutput out)
    throws java.io.IOException {
    WRITER$.write(this, org.apache.avro.specific.SpecificData.getEncoder(out));
  }

  private static final org.apache.avro.io.DatumReader
    READER$ = new org.apache.avro.specific.SpecificDatumReader(SCHEMA$);  

  @Override public void readExternal(java.io.ObjectInput in)
    throws java.io.IOException {
    READER$.read(this, org.apache.avro.specific.SpecificData.getDecoder(in));
  }

}
