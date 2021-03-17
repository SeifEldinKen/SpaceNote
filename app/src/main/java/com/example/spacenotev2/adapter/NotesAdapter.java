package com.example.spacenotev2.adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.spacenotev2.R;
import com.example.spacenotev2.model.Note;
import java.util.List;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.NoteViewHolder> {

    private final List<Note> notesList;
    private final Context context;


    // Constructor
    public NotesAdapter(List<Note> notesList, Context context) {
        // init note list
        this.notesList = notesList;
        this.context = context;
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

        GradientDrawable gradientDrawable = (GradientDrawable) holder.layoutNote.getBackground();
        if (!note.getColor().isEmpty()) {
            gradientDrawable.setColor(Color.parseColor(note.getColor()));
        } else {
            gradientDrawable.setColor(Color.parseColor("#333333"));
        }


        holder.itemView.setOnClickListener(v -> {
            Toast.makeText(context, note.getColor(), Toast.LENGTH_SHORT).show();
        });

    }

    @Override
    public int getItemCount() {
        return notesList.size();
    }


    // class view holder
    static class NoteViewHolder extends RecyclerView.ViewHolder {

        TextView textViewTitle, textViewSubTitle, textViewDataTime;
        LinearLayout layoutNote;


        public NoteViewHolder(@NonNull View itemView) {
            super(itemView);

            textViewTitle = itemView.findViewById(R.id.textViewTitle);
            textViewSubTitle = itemView.findViewById(R.id.textViewSubTitle);
            textViewDataTime = itemView.findViewById(R.id.textViewDateTime);
            layoutNote = itemView.findViewById(R.id.layoutNote);

        }

    }
}
