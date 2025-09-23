package consoleconfig;

public enum Encoding {
    CP866("cp866"),
    UTF8("UTF-8");

    private final String value;

    Encoding(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return value;
    }
}
