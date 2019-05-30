package com.example.android.pets.Entities;

import android.database.Cursor;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.android.pets.data.PetContract;

import java.util.ArrayList;
import java.util.List;

@Entity(tableName = "pets")
public class Pets {

    public static List<Pets> with(Cursor cursor) {
        List<Pets> pets = new ArrayList<>();
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                do {
                    int nameColumnIndex = cursor.getColumnIndex(PetContract.PetEntry.COLUMN_PET_NAME);
                    int breedColumnIndex = cursor.getColumnIndex(PetContract.PetEntry.COLUMN_PET_BREED);
                    int genderColumnIndex = cursor.getColumnIndex(PetContract.PetEntry.COLUMN_PET_GENDER);
                    int weightColumnIndex = cursor.getColumnIndex(PetContract.PetEntry.COLUMN_PET_WEIGHT);
                    Pets pet = new Pets(cursor.getString(nameColumnIndex),
                            cursor.getString(breedColumnIndex),
                            cursor.getString(genderColumnIndex),
                            cursor.getString(weightColumnIndex));
                    pets.add(pet);
                } while (cursor.moveToNext());
            }
        }
        return pets;
    }

    @PrimaryKey(autoGenerate = true)
    private long id;
    private String name;
    private String breed;
    private String gender;
    private String weight;

    public Pets(String name, String breed, String gender, String weight) {
        this.name = name;
        this.breed = breed;
        this.gender = gender;
        this.weight = weight;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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
