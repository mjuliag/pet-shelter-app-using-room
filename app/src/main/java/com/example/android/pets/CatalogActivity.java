package com.example.android.pets;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android.pets.Contracts.CatalogContract;
import com.example.android.pets.Entities.Pets;
import com.example.android.pets.Presenters.CatalogPresenter;
import com.example.android.pets.data.PetContract.PetEntry;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Displays list of pets that were entered and stored in the app.
 */
public class CatalogActivity extends AppCompatActivity implements CatalogContract.View {

    @BindView(R.id.fab)
    FloatingActionButton fab;
    @BindView(R.id.list)
    RecyclerView petListView;
    @BindView(R.id.empty_view)
    View emptyView;

    PetAdapter adapter;

    CatalogContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catalog);
        ButterKnife.bind(this);

        fab.setOnClickListener(view -> EditorActivity.start(this));

        //petListView.setEmptyView(emptyView);

        petListView.setLayoutManager(new LinearLayoutManager(this));

        adapter = new PetAdapter();
        petListView.setAdapter(adapter);

//        petListView.setOnItemClickListener((adapterView, view, position, id) -> {
//            Uri currentPetUri = ContentUris.withAppendedId(PetEntry.CONTENT_URI, id);
//            EditorActivity.startWith(this, currentPetUri);
//        });

        PetsRepository petsRepository = new PetsSqlLiteService(getLoaderManager(), this, getContentResolver());

        presenter = new CatalogPresenter(this, petsRepository);
        presenter.getPets();

        emptyView.setVisibility(View.GONE);
    }

    private void insertPet() {

        ContentValues values = new ContentValues();
        values.put(PetEntry.COLUMN_PET_NAME, "Toto");
        values.put(PetEntry.COLUMN_PET_BREED, "Terrier");
        values.put(PetEntry.COLUMN_PET_GENDER, PetEntry.GENDER_MALE);
        values.put(PetEntry.COLUMN_PET_WEIGHT, 7);

        getContentResolver().insert(PetEntry.CONTENT_URI, values);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu options from the res/menu/menu_catalog.xml file.
        // This adds menu items to the app bar.
        getMenuInflater().inflate(R.menu.menu_catalog, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // User clicked on a menu option in the app bar overflow menu
        switch (item.getItemId()) {
            // Respond to a click on the "Insert dummy data" menu option
            case R.id.action_insert_dummy_data:
                insertPet();
                return true;
            // Respond to a click on the "Delete all entries" menu option
            case R.id.action_delete_all_entries:
                showDeleteConfirmationDialog();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void deleteAllPets() {
        int rowsDeleted = getContentResolver().delete(PetEntry.CONTENT_URI, null, null);
        Log.v("CatalogActivity", rowsDeleted + " rows deleted from pet database");
    }

    private void showDeleteConfirmationDialog() {
        // Create an AlertDialog.Builder and set the message, and click listeners
        // for the postivie and negative buttons on the dialog.
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(R.string.delete_all_pets_dialog_msg);
        builder.setPositiveButton(R.string.delete, (dialog, id) -> {
            // User clicked the "Delete" button, so delete the pet.
            deleteAllPets();
        });
        builder.setNegativeButton(R.string.cancel, (dialog, id) -> {
            // User clicked the "Cancel" button, so dismiss the dialog
            // and continue editing the pet.
            if (dialog != null) {
                dialog.dismiss();
            }
        });

        // Create and show the AlertDialog
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    @Override
    public void showPets(List<Pets> pets) {

        adapter.setPets(pets);

    }

    @Override
    public void editPet(Pets pet) {

    }

    @Override
    public void createPet() {

    }
}