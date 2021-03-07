package com.example.spacenotev2.ui.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import android.content.Intent;
import android.os.Bundle;
import com.example.spacenotev2.databinding.ActivityCreateNoteBinding;
import com.example.spacenotev2.model.Note;
import com.example.spacenotev2.ui.viewmodel.NoteViewModel;
import com.example.spacenotev2.ui.viewmodel.NoteViewModelFactory;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class CreateNoteActivity extends AppCompatActivity {

    private ActivityCreateNoteBinding binding;
    private NoteViewModel noteViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // init view binding
        binding = ActivityCreateNoteBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        initViewModel();

        binding.textViewDateTime.setText(
                new SimpleDateFormat("EEEE, dd MMMM yyyy HH:mm a", Locale.getDefault()).format(new Date())
        );


        binding.imageViewSave.setOnClickListener((v) -> {

            if (checkDataFromUI()) {
                insertNote();
                nextActivity();
            }

        });


    }

    private void initViewModel() {
        noteViewModel = new ViewModelProvider(this, new NoteViewModelFactory(this)).get(NoteViewModel.class);
    }

    private void insertNote() {
        noteViewModel.insertNote(getDataFromUI());
    }

    private Note getDataFromUI() {
        return new Note(
                binding.editTextNoteTitle.getText().toString().trim(),
                binding.editTextNoteSubtitle.getText().toString().trim(),
                binding.editTextNote.getText().toString().trim(),
                binding.textViewDateTime.getText().toString(),
                "",
                "",
                ""
        );
    }

    private boolean checkDataFromUI() {

//        if (getDataFromUI().getTitle().isEmpty()) {
//            Toast.makeText(this, "Note title can't be empty", Toast.LENGTH_SHORT).show();
//        } else if (getDataFromUI().getSubTitle().isEmpty()) {
//            Toast.makeText(this, "Note can't be empty", Toast.LENGTH_SHORT).show();
//        }

        return !getDataFromUI().getTitle().isEmpty() && !getDataFromUI().getSubTitle().isEmpty();
    }

    private void nextActivity() {
        Intent intentNote = new Intent(this, MainActivity.class);
        startActivity(intentNote);
    }

}