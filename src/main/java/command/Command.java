package command;

abstract public class Command {
    public boolean matchesInput(String input) {
        return input.equals(this.getCommandLabel());
    }

    public abstract void execute(String[] args);

    public abstract String getCommandLabel();
}
