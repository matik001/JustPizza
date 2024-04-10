package dev.justpizza.argparser;

public class ParamSchema {
    private String name;
    private ParamType paramType;
    private boolean optional;

    public ParamSchema(String name) {
        this.name = name;
        this.paramType = ParamType.DOUBLE;
        this.optional = false;
    }

    public ParamSchema(String name, ParamType paramType) {
        this.name = name;
        this.paramType = paramType;
        this.optional = false;
    }

    public ParamSchema(String name, ParamType paramType, boolean optional) {
        this.name = name;
        this.paramType = paramType;
        this.optional = optional;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ParamType getParamType() {
        return paramType;
    }

    public void setParamType(ParamType paramType) {
        this.paramType = paramType;
    }

    public boolean isOptional() {
        return optional;
    }

    public void setOptional(boolean optional) {
        this.optional = optional;
    }
}
