public enum Symbols {

    PLUS ("+"),
    MINUS ("-"),
    MULTIPLAY ("*"),
    SPACE (" "),
    DIVIDE ("/");

    private String value;

    Symbols(String value) {
        this.value = value;
    }


    public String getValue() {
        return value;
    }
}
