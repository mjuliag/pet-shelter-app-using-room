package com.example.android.pets;

import android.app.LoaderManager;
import android.content.ContentResolver;
import android.content.Context;
import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.os.Bundle;

import com.example.android.pets.Entities.Pets;
import com.example.android.pets.data.PetContract;

import java.util.List;

public class PetsSqlLiteService implements PetsRepository, LoaderManager.LoaderCallbacks<Cursor> {

    private static final int PET_LOADER = 0;
    private LoaderManager loaderManager;
    private Context context;
    private ContentResolver contentResolver;
    private Callback<List<Pets>> callback;

    public PetsSqlLiteService(LoaderManager loaderManager, Context context, ContentResolver contentResolver) {
        this.loaderManager = loaderManager;
        this.context = context.getApplicationContext();
        this.contentResolver = contentResolver;
    }

    @Override
    public void getPets(Callback<List<Pets>> callback) {

        this.callback = callback;
        loaderManager.initLoader(PET_LOADER, null, this);

    }

    @Override
    public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {
        String[] projection = {
                PetContract.PetEntry._ID,
                PetContract.PetEntry.COLUMN_PET_NAME,
                PetContract.PetEntry.COLUMN_PET_BREED,
                PetContract.PetEntry.COLUMN_PET_GENDER,
                PetContract.PetEntry.COLUMN_PET_WEIGHT};

        return new CursorLoader(
                context,
                PetContract.PetEntry.CONTENT_URI,
                projection,
                null,
                null,
                null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
        List<Pets> pets = Pets.with(cursor);
        callback.onSuccess(pets);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }
}
