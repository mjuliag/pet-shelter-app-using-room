package com.example.android.pets.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.android.pets.Entities.Pets;

import java.util.List;

@Dao
public interface PetsDao {

    @Query("SELECT * FROM pets")
    List<Pets> getAllPets();

    @Update
    void update(Pets pets);

    @Insert
    void insert(Pets pets);

    @Delete
    void delete(Pets pets);
}
