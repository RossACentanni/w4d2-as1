package com.example.w4d2_as1;

public class BookItem {

    private String title;
    private String authors;
    private String coverURL;

    public BookItem(String title, String authors, String coverURL) {
        this.title = title;
        this.authors = authors;
        this.coverURL = coverURL;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthors() {
        return authors;
    }

    public void setAuthors(String authors) {
        this.authors = authors;
    }

    public String getCoverURL() {
        return coverURL;
    }

    public void setCoverURL(String coverURL) {
        this.coverURL = coverURL;
    }
}
