package models;

import core.Model;

public abstract class Person extends Model {
    protected Integer id;
    protected String name;
    protected String email;

    public String getName() {
        return name;
    }

    public Person setName(String name) {
        this.name = name;
        return this;
    }
}
