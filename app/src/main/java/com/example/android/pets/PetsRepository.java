package com.example.android.pets;

import com.example.android.pets.Entities.Pets;

import java.util.List;

public interface PetsRepository {

    void getPets(Callback<List<Pets>> pets);

}
