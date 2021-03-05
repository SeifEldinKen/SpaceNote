package com.example.spacenotev2.ui.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.spacenotev2.R;
import com.example.spacenotev2.databinding.ActivityCreateNoteBinding;
import com.example.spacenotev2.model.Note;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class CreateNoteActivity extends AppCompatActivity {

    private ActivityCreateNoteBinding binding;

    private String noteTitle, noteSubTitle, noteBody;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // init view binding
        binding = ActivityCreateNoteBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        binding.textViewDateTime.setText(
                new SimpleDateFormat("EEEE, dd MMMM yyyy HH:mm a", Locale.getDefault()).format(new Date())
        );

        noteTitle = binding.editTextNoteTitle.getText().toString().trim();
        noteSubTitle = binding.editTextNoteSubtitle.getText().toString().trim();
        noteBody = binding.editTextNote.getText().toString().trim();

    }


    private Note getDataFromUI() {
        return new Note(
                noteTitle,
                noteSubTitle,
                noteBody,
                binding.textViewDateTime.getText().toString(),
                "",
                "",
                ""
        );
    }

}