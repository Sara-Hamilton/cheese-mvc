package org.launchcode.cheesemvc.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.*;

@Entity
public class Cheese {

    @Id
    @GeneratedValue
    private int id;

    @NotNull
    @Size(min=3, max=15)
    private String name;

    @NotNull
    @Size(min=1, message="Description must not be empty")
    private String description;

    private CheeseType type;

    // private int cheeseId;
    // private static int nextId = 1;

    @Digits(integer=1,fraction=0)
    @Min(value = 1, message="Enter a number between 1 and 5.")
    @Max(value = 5, message="Enter a number between 1 and 5.")
    private int rating;

    public Cheese(String name, String description) {
        // this();
        this.name = name;
        this.description = description;
    }

    public Cheese() { }

    public int getId() {
        return id;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    // public int getCheeseId() {
        // return cheeseId;
    // }

    // public void setCheeseId(int cheeseId) {
        // this.cheeseId = cheeseId;
    // }

    public String getName() {
        return name;
    }
    public void setName(String aName) {
        name = aName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String aDescription) {
        description = aDescription;
    }

    public CheeseType getType() {
        return type;
    }

    public void setType(CheeseType type) {
        this.type = type;
    }
}
