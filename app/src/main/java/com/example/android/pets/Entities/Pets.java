package com.example.android.pets.Entities;

import android.database.Cursor;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.android.pets.PetGender;
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
                    int idColumnIndex = cursor.getColumnIndex(PetContract.PetEntry._ID);
                    int nameColumnIndex = cursor.getColumnIndex(PetContract.PetEntry.COLUMN_PET_NAME);
                    int breedColumnIndex = cursor.getColumnIndex(PetContract.PetEntry.COLUMN_PET_BREED);
                    int genderColumnIndex = cursor.getColumnIndex(PetContract.PetEntry.COLUMN_PET_GENDER);
                    int weightColumnIndex = cursor.getColumnIndex(PetContract.PetEntry.COLUMN_PET_WEIGHT);
                    Pets pet = new Pets(cursor.getInt(idColumnIndex),
                            cursor.getString(nameColumnIndex),
                            cursor.getString(breedColumnIndex),
                            cursor.getInt(genderColumnIndex),
                            cursor.getInt(weightColumnIndex));
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
    private Integer gender;
    private Integer weight;

    public Pets(long id, String name, String breed, Integer gender, Integer weight) {
        this.id = id;
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

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public PetGender getGenderEnum() {
        return PetGender.values()[gender];
    }

    public void setGenderEnum(PetGender gender) {
        this.gender = gender.ordinal();
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }
}
