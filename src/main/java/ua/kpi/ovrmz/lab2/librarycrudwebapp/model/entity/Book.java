package ua.kpi.ovrmz.lab2.librarycrudwebapp.model.entity;

import java.util.Objects;

public class Book {
    private Integer id;
    private String title;
    private String author;
    private Library library;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Library getLibrary() {
        return library;
    }

    public void setLibrary(Library library) {
        this.library = library;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return Objects.equals(id, book.id) &&
                Objects.equals(title, book.title) &&
                Objects.equals(author, book.author) &&
                Objects.equals(library, book.library);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, author, library);
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", library=" + library +
                '}';
    }

    public static class Builder {
        Book instance = new Book();

        public Builder setId(Integer id) {
            instance.id = id;
            return this;
        }

        public Builder setTitle(String title) {
            instance.title = title;
            return this;
        }

        public Builder setAuthor(String author) {
            instance.author = author;
            return this;
        }

        public Builder setLibrary(Library library) {
            instance.library = library;
            return this;
        }

        public Book build() {
            return instance;
        }
    }
}