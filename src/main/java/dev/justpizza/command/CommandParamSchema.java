package dev.justpizza.command;

public class CommandParamSchema{
    public String paramFullName; /// i.e. "help" for --help
    public String paramShortName; /// i.e. "h" for -h
    public Boolean hasValue; /// i.e. "-f" doesn't has value, "-f 5" has value
    public CommandParamSchema(String paramFullName, String paramShortName, Boolean hasValue) {
        this.paramFullName = paramFullName;
        this.paramShortName = paramShortName;
        this.hasValue = hasValue;
    }
}
