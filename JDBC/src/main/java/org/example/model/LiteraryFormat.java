package org.example.model;

public class LiteraryFormat {
    private Long id;

    private String format;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public String getFormat() {
        return format;
    }

    @Override
    public String toString() {
        return "LiteraryFormat{" +
                "id=" + id +
                ", format='" + format + '\'' +
                '}';
    }
}
