package dev.justpizza.argparser;

import java.util.HashSet;
import java.util.Optional;
import java.util.OptionalInt;

public class ParamSchema {
    private String name;
    private ParamType paramType;
    private boolean optional;

    private boolean isArray = false;
    private int arrLen = 1;
    private HashSet<String> optionsSet = new HashSet<String>();

    public ParamSchema(HashSet<String> optionsSet) { /// initialization optionsSet
        this.paramType = ParamType.OPTIONS_SET;
        this.optionsSet = optionsSet;
    }


    public ParamSchema(String name) {
        this.name = name;
        this.paramType = ParamType.POSITIVE_DOUBLE;
        this.optional = false;
    }
    public boolean hasOption(String option){
        return optionsSet.contains(option);
    }

    public boolean isArray() {
        return isArray;
    }

    public void setArray(boolean array) {
        isArray = array;
    }

    public int getArrLen() {
        return arrLen;
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
    public ParamSchema(String name, boolean optional) {
        this.name = name;
        this.paramType = ParamType.POSITIVE_DOUBLE;
        this.optional = optional;
    }
    public ParamSchema(String name, ParamType paramType, boolean optional, boolean isArray, int arrLen) {
        this.name = name;
        this.paramType = paramType;
        this.optional = optional;
        this.isArray = isArray;
        this.arrLen = arrLen;
    }

    public void setArrLen(int arrLen) {
        this.arrLen = arrLen;
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
