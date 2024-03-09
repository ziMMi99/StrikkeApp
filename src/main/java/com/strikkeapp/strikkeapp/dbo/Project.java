package com.strikkeapp.strikkeapp.dbo;

public class Project {
    //Class variables

    String name;
    String description;
    String note;
    public Project(String name, String description, String note) {
        this.name = name;
        this.description = description;
        this.note = note;
    }

    @Override
    public String toString() {
        return "Project{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", note='" + note + '\'' +
                '}';
    }

    //Getters
    public String getName() {
        return name;
    }
    public String getDescription() {
        return description;
    }
    public String getNote() {
        return note;
    }

    //Setters
    public void setName(String name) {
        this.name = name;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public void setNote(String note) {
        this.note = note;
    }
}
