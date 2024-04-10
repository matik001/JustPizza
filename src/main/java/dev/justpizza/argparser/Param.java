package dev.justpizza.argparser;

public class Param {
    ParamSchema schema;
    Object value;

    public Param(ParamSchema schema, Object value) {
        this.schema = schema;
        this.value = value;
    }

    public ParamSchema getSchema() {
        return schema;
    }

    public void setSchema(ParamSchema schema) {
        this.schema = schema;
    }

    public void setValue(Object value){
        this.value = value;
    }

    public String getString(){
        return (String)value;
    }

    public double getDouble(){
        return (double)value;
    }
}
