package com.example.android.pets;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android.pets.Entities.Pets;

import java.util.List;

public class PetAdapter extends RecyclerView.Adapter<PetAdapter.CustomViewHolder> {

    private List<Pets> pets;

    private OnItemClickListener onItemClickListener;

    @NonNull
    @Override
    public PetAdapter.CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item, parent, false);

        return new CustomViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull PetAdapter.CustomViewHolder holder, int position) {

        Pets pet = pets.get(position);
        holder.pet = pet;

        String petBreed = pet.getBreed();

        holder.name.setText(pet.getName());

        // If the pet breed is empty string or null, then use some default text
        // that says "Unknown breed", so the TextView isn't blank.
        if (TextUtils.isEmpty(petBreed)) {
            holder.summary.setText(R.string.unknown_breed);
        } else {
            holder.summary.setText(petBreed);
        }
    }

    @Override
    public int getItemCount() {
        return pets == null ? 0 : pets.size();
    }

    public void setPets(List<Pets> pets) {
        this.pets = pets;
        notifyDataSetChanged();
    }

    class CustomViewHolder extends RecyclerView.ViewHolder {
        TextView name, summary;
        Pets pet;

        CustomViewHolder(View view) {
            super(view);
            name = view.findViewById(R.id.name);
            summary = view.findViewById(R.id.summary);
            view.setOnClickListener((view1 -> onItemClickListener.onClick(pet)));
        }
    }

    public interface OnItemClickListener {

        void onClick(Pets pet);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }
}
