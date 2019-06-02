package com.example.android.pets.Contracts;

import com.example.android.pets.Entities.Pets;

import java.util.List;

public interface CatalogContract {

    interface View {
        void showPets(List<Pets> pets);
        void editPet(Pets pet);
        void createPet();
    }

    interface Presenter {
        void getPets();
        void onPetSelected();
        void onNewPet();
    }
}
