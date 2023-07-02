package org.example.model;

import java.math.BigDecimal;


public class Book {
    private Long id;
    private String title;
    private BigDecimal price;
    private LiteraryFormat literaryFormat;

    private List<Author> authors;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public LiteraryFormat getLiteraryFormat() {
        return literaryFormat;
    }

    public void setLiteraryFormat(LiteraryFormat literaryFormat) {
        this.literaryFormat = literaryFormat;
    }


    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", bigDecimal=" + price +
                ", literaryFormat=" + literaryFormat +
                '}';
    }
}
