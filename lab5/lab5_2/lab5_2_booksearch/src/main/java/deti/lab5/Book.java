package deti.lab5;

import java.util.Date;

public class Book {
    private String title;
    private String author;
    private Date published;

    public Book(String title, String author, Date published) {
        this.title = title;
        this.author = author;
        this.published = published;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public Date getPublished() {
        return published;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setPublished(Date published) {
        this.published = published;
    }


    @Override
    public String toString() {
        return "Book{" + "title=" + title + ", author=" + author + ", published=" + published + '}';
    }
}
