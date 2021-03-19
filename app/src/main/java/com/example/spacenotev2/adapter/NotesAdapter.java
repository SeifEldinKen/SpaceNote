package com.example.spacenotev2.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.net.Uri;
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
import com.makeramen.roundedimageview.RoundedImageView;

import java.io.InputStream;
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


        if (!note.getImagePath().isEmpty()) {
            holder.roundedImageViewNote.setImageURI(Uri.parse(note.getImagePath()));
            holder.roundedImageViewNote.setVisibility(View.VISIBLE);
        }





        holder.itemView.setOnClickListener(v -> {

            Toast.makeText(context, String.valueOf(note.getPostId()), Toast.LENGTH_SHORT).show();

        });

    }

    @Override
    public int getItemCount() {
        return notesList.size();
    }

    public Note getNote(int position) {
        return notesList.get(position);
    }


    // class view holder
    static class NoteViewHolder extends RecyclerView.ViewHolder {

        TextView textViewTitle, textViewSubTitle, textViewDataTime;
        LinearLayout layoutNote;
        RoundedImageView roundedImageViewNote;


        public NoteViewHolder(@NonNull View itemView) {
            super(itemView);

            textViewTitle = itemView.findViewById(R.id.textViewTitle);
            textViewSubTitle = itemView.findViewById(R.id.textViewSubTitle);
            textViewDataTime = itemView.findViewById(R.id.textViewDateTime);
            layoutNote = itemView.findViewById(R.id.layoutNote);
            roundedImageViewNote = itemView.findViewById(R.id.roundedImageViewNote);
        }

    }
}
