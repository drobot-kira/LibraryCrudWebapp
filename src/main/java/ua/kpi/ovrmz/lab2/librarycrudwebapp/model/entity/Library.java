package ua.kpi.ovrmz.lab2.librarycrudwebapp.model.entity;

import java.util.Objects;

public class Library {
    private Integer id;
    private String name;
    private Integer foundationYear;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getFoundationYear() {
        return foundationYear;
    }

    public void setFoundationYear(Integer foundationYear) {
        this.foundationYear = foundationYear;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Library library = (Library) o;
        return Objects.equals(id, library.id) &&
                Objects.equals(name, library.name) &&
                Objects.equals(foundationYear, library.foundationYear);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, foundationYear);
    }

    @Override
    public String toString() {
        return "Library{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", foundationYear=" + foundationYear +
                '}';
    }

    public static class Builder {
        Library instance = new Library();

        public Builder setId(Integer id) {
            instance.id = id;
            return this;
        }

        public Builder setName(String name) {
            instance.name = name;
            return this;
        }

        public Builder setFoundationYear(Integer foundationYear) {
            instance.foundationYear = foundationYear;
            return this;
        }

        public Library build() {
            return instance;
        }
    }
}