package com.example.android.pets.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

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
