package net.noerlol.util;

public class ArrayJoiner {
    private final String separator;
    private final String[] data;
    public ArrayJoiner(String[] data, String separator) {
        this.separator = separator;
        this.data = data;
    }

    public ArrayJoiner(String[] data, char separator) {
        this.separator = String.valueOf(separator);
        this.data = data;
    }

    public String join() {
        String b = "";
        for (String str : data) {
            b += str + this.separator;
        }
        return b;
    }
}
