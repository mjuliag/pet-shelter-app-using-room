package com.example.android.pets.Contracts;

import com.example.android.pets.Entities.Pets;

public interface CatalogContract {

    interface View {
        void showPets();
        void editPet(Pets pet);
        void createPet();
    }

    interface Presenter {
        void getPets();
        void onPetSelected();
        void onNewPet();
    }
}
