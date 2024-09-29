package honestcalculator;

public enum Messages {
    ENTER("Enter an equation"),
    ERROR_NUMBER("Do you even know what numbers are? Stay focused!"),
    ERROR_OPERATION("Yes ... an interesting math operation. You've slept through all classes, haven't you?"),
    DIVISION_ZERO("Yeah... division by zero. Smart move...");

    private final String string;

    Messages(String string) {
        this.string = string;
    }

    @Override
    public String toString() {
        return string;
    }
}
