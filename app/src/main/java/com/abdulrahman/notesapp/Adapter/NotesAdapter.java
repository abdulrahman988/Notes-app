package com.abdulrahman.notesapp.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.abdulrahman.notesapp.Model.Notes;
import com.abdulrahman.notesapp.R;
import com.abdulrahman.notesapp.Utils;
import com.abdulrahman.notesapp.databinding.ItemNotesBinding;
import com.abdulrahman.notesapp.ui.MainActivity;
import com.abdulrahman.notesapp.ui.UpdateNotesActivity;

import java.util.ArrayList;
import java.util.List;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.notesViewHolder> {
    private Context context;
    private List<Notes>notes;
    private List<Notes>allNotesItem;

    public NotesAdapter(Context context, List<Notes> notes) {
        this.context = context;
        this.notes = notes;
        allNotesItem = new ArrayList<>();
    }
    public void searchNotes(List<Notes> filteredNotes){
        this.notes = filteredNotes;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public notesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new notesViewHolder(ItemNotesBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull notesViewHolder holder, int position) {
        Notes note = notes.get(position);

        if ("1".equalsIgnoreCase(note.notesPriority)){
            holder.binding.notesPriority.setBackgroundResource(R.drawable.green_shape);

        }else if ("2".equalsIgnoreCase(note.notesPriority)){
            holder.binding.notesPriority.setBackgroundResource(R.drawable.yellow_shape);
        }
        else { holder.binding.notesPriority.setBackgroundResource(R.drawable.red_shape); }

        holder.binding.notesTitle.setText(note.notesTitle);
        holder.binding.notesSubtitle.setText(note.notesSubtitle);
        holder.binding.notesDate.setText(note.notesDate);

        holder.binding.getRoot().setOnClickListener(view -> {

            Intent intent = new Intent(context, UpdateNotesActivity.class);

            intent.putExtra(Utils.ID, note.id);
            intent.putExtra(Utils.TITLE, note.notesTitle);
            intent.putExtra(Utils.SUBTITLE, note.notesSubtitle);
            intent.putExtra(Utils.PRIORITY, note.notesPriority);
            intent.putExtra(Utils.NOTE, note.notes);

            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

    public static class notesViewHolder extends RecyclerView.ViewHolder{
        private ItemNotesBinding binding;
        public notesViewHolder(@NonNull ItemNotesBinding binding) {
            super(binding.getRoot());
                this.binding = binding;
        }
    }
}
