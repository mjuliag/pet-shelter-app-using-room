package com.example.android.pets;

import com.example.android.pets.Entities.Pets;

import java.util.List;

public interface PetsRepository {

    void getPets(Callback<List<Pets>> callback);

    void getPet(Callback<Pets> callback, long petId);

    void insertPet(Callback<Boolean> callback, Pets pet);

    void updatePet(Callback<Boolean> callback, Pets pet);
}
