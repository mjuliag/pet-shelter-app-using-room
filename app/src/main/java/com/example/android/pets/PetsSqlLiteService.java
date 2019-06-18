package com.example.android.pets;

import android.app.LoaderManager;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;

import com.example.android.pets.Entities.Pets;
import com.example.android.pets.data.PetContract;

import java.util.List;

public class PetsSqlLiteService implements PetsRepository {

    private static final int PETS_LOADER = 0;
    private static final int PET_LOADER = 1;
    private static final String[] PROJECTION = new String[]{
            PetContract.PetEntry._ID,
            PetContract.PetEntry.COLUMN_PET_NAME,
            PetContract.PetEntry.COLUMN_PET_BREED,
            PetContract.PetEntry.COLUMN_PET_GENDER,
            PetContract.PetEntry.COLUMN_PET_WEIGHT};

    private LoaderManager loaderManager;
    private Context context;
    private ContentResolver contentResolver;

    public PetsSqlLiteService(LoaderManager loaderManager, Context context, ContentResolver contentResolver) {
        this.loaderManager = loaderManager;
        this.context = context.getApplicationContext();
        this.contentResolver = contentResolver;
    }

    @Override
    public void getPets(Callback<List<Pets>> callback) {

        loaderManager.initLoader(PETS_LOADER, null, new LoaderManager.LoaderCallbacks<Cursor>() {
            @Override
            public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {

                return getCursorLoader(PetContract.PetEntry.CONTENT_URI);
            }

            @Override
            public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
                List<Pets> pets = Pets.with(cursor);
                callback.onSuccess(pets);
            }

            @Override
            public void onLoaderReset(Loader<Cursor> loader) {

            }
        });

    }

    private CursorLoader getCursorLoader(Uri data) {
        return new CursorLoader(
                context,
                data,
                PROJECTION,
                null,
                null,
                null);
    }

    @Override
    public void getPet(Callback<Pets> callback, long id) {
        loaderManager.initLoader(PET_LOADER, null, new LoaderManager.LoaderCallbacks<Cursor>() {

            @Override
            public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {
                Uri currentPetUri = ContentUris.withAppendedId(PetContract.PetEntry.CONTENT_URI, id);
                return getCursorLoader(currentPetUri);
            }

            @Override
            public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
                Pets pet = Pets.with(cursor).get(0);
                callback.onSuccess(pet);
            }

            @Override
            public void onLoaderReset(Loader<Cursor> loader) {

            }
        });
    }

    @Override
    public void insertPet(Callback<Boolean> callback, Pets pet) {

        ContentValues values = new ContentValues();
        values.put(PetContract.PetEntry.COLUMN_PET_NAME, "Toto");
        values.put(PetContract.PetEntry.COLUMN_PET_BREED, "Terrier");
        values.put(PetContract.PetEntry.COLUMN_PET_GENDER, PetContract.PetEntry.GENDER_MALE);
        values.put(PetContract.PetEntry.COLUMN_PET_WEIGHT, 7);

        contentResolver.insert(PetContract.PetEntry.CONTENT_URI, values);
    }

    @Override
    public void updatePet(Callback<Boolean> callback, Pets pet) {

    }
}

