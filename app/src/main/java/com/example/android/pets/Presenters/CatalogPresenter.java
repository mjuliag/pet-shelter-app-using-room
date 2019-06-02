package com.example.android.pets.Presenters;

import com.example.android.pets.Contracts.CatalogContract;
import com.example.android.pets.PetsRepository;

public class CatalogPresenter implements CatalogContract.Presenter {

    private CatalogContract.View view;
    private PetsRepository petsRepository;

    public CatalogPresenter(CatalogContract.View view, PetsRepository petsRepository) {
        this.view = view;
        this.petsRepository = petsRepository;
    }

    @Override
    public void getPets() {

        petsRepository.getPets(response -> view.showPets(response));
    }

    @Override
    public void onPetSelected() {

    }

    @Override
    public void onNewPet() {

    }
}
