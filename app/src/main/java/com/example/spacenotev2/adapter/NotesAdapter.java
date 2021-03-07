package com.example.spacenotev2.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.spacenotev2.R;
import com.example.spacenotev2.model.Note;

import java.util.List;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.NoteViewHolder> {

    private final List<Note> notesList;

    public NotesAdapter(List<Note> notesList) {
        this.notesList = notesList;
    }


    @NonNull
    @Override
    public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View viewItem = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_container_note, parent, false);
        return new NoteViewHolder(viewItem);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteViewHolder holder, int position) {
        Note note = notesList.get(position);

        holder.textViewTitle.setText(note.getTitle());
        holder.textViewSubTitle.setText(note.getSubTitle());
        holder.textViewDataTime.setText(note.getDataTime());

    }

    @Override
    public int getItemCount() {
        return notesList.size();
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    static class NoteViewHolder extends RecyclerView.ViewHolder {

        TextView textViewTitle, textViewSubTitle, textViewDataTime;

        public NoteViewHolder(@NonNull View itemView) {
            super(itemView);

            textViewTitle = itemView.findViewById(R.id.textViewTitle);
            textViewSubTitle = itemView.findViewById(R.id.textViewSubTitle);
            textViewDataTime = itemView.findViewById(R.id.textViewDateTime);

        }
    }
}
