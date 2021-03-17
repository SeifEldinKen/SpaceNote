package com.example.spacenotev2.ui.activity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import com.example.spacenotev2.adapter.NotesAdapter;
import com.example.spacenotev2.databinding.ActivityMainBinding;
import com.example.spacenotev2.model.Note;
import com.example.spacenotev2.ui.viewmodel.NoteViewModel;
import com.example.spacenotev2.ui.viewmodel.NoteViewModelFactory;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private NoteViewModel noteViewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // init view binding
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        initViewModel();
        observer();

        binding.imageViewAddNoteMain.setOnClickListener(v -> {
            Intent intentCreateNote = new Intent(this, CreateNoteActivity.class);
            startActivity(intentCreateNote);
        });

    }




    private void initViewModel() {
        noteViewModel = new ViewModelProvider(this, new NoteViewModelFactory(this)).get(NoteViewModel.class);
    }


    private void observer() {
        // the live data notes
        noteViewModel.getAllNotes().observe(this, this::setupRecyclerView);
    }


    private void setupRecyclerView(List<Note> notesList) {
        // recycler view adapter
        NotesAdapter adapter = new NotesAdapter(notesList, this);

        binding.recyclerViewNotes.setLayoutManager(
                new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        );

        binding.recyclerViewNotes.refreshDrawableState();
        binding.recyclerViewNotes.setAdapter(adapter);
    }

}