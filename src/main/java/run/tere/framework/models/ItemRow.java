package run.tere.framework.models;

public class ItemRow {

    private String pattern;

    public ItemRow(String pattern) {
        if (pattern.length() != 9) {
            throw new IllegalArgumentException("Pattern must be 9 characters");
        }
        this.pattern = pattern;
    }

    public String getPattern() {
        return pattern;
    }

}
