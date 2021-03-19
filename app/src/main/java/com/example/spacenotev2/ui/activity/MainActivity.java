package com.example.spacenotev2.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;
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

    // RecyclerView and Adapter
    private NotesAdapter notesAdapter;


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
        noteViewModel.getAllNotes().observe(this, this::initRecyclerView);
    }


    private void initRecyclerView(List<Note> notesList) {
        // recycler view adapter
        notesAdapter = new NotesAdapter(notesList, this);

        binding.recyclerViewNotes.setLayoutManager(
                new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        );

        binding.recyclerViewNotes.refreshDrawableState();
        binding.recyclerViewNotes.setAdapter(notesAdapter);

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(
                    @NonNull RecyclerView recyclerView,
                    @NonNull RecyclerView.ViewHolder viewHolder,
                    @NonNull RecyclerView.ViewHolder target
            ) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                // delete item
                int position = viewHolder.getAdapterPosition();

                noteViewModel.deleteNote(notesAdapter.getNote(position));
            }
        }).attachToRecyclerView(binding.recyclerViewNotes);

    }

}