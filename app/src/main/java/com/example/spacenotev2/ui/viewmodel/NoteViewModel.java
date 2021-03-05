package com.example.spacenotev2.ui.viewmodel;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import com.example.spacenotev2.model.Note;
import com.example.spacenotev2.repository.NoteRepository;
import java.util.List;

public class NoteViewModel extends ViewModel {

    private NoteRepository noteRepository;
    private LiveData<List<Note>> fetchAllNotes;


    public NoteViewModel(Context context) {
        noteRepository = new NoteRepository(context);
        fetchAllNotes = noteRepository.getAllNotes();
    }


    public LiveData<List<Note>> getAllNotes() {
        return fetchAllNotes;
    }

    public void insertNote(Note note) {
        noteRepository.insertNote(note);
    }

    public void deleteNote(Note note) {
        noteRepository.deleteNote(note);
    }

}
