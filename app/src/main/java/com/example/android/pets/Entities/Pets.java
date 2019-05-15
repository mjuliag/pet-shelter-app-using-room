package com.example.android.pets.Entities;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "pets")
public class Pets {

    @PrimaryKey(autoGenerate = true)
    private String _id;


    @ColumnInfo(name = "name")
    private String name;

    @ColumnInfo(name = "breed")
    private String breed;

    @ColumnInfo(name = "gender")
    private String gender;

    @ColumnInfo(name = "weight")
    private String weight;

    public String getId() {
        return _id;
    }

    public void setId(String _id) {
        this._id = _id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }
}
