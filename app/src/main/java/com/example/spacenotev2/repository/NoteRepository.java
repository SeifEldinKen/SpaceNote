package com.example.spacenotev2.repository;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.loader.content.AsyncTaskLoader;
import androidx.room.RoomDatabase;

import com.example.spacenotev2.dao.NoteDao;
import com.example.spacenotev2.db.NoteDatabase;
import com.example.spacenotev2.model.Note;
import java.util.List;

public class NoteRepository {

    private final NoteDao noteDao;
    private final LiveData<List<Note>> fetchAllNotesLiveData;


    public NoteRepository(Context context) {
        NoteDatabase db = NoteDatabase.getInstance(context);
        noteDao = db.noteDao();
        fetchAllNotesLiveData = noteDao.getAllNotes();
    }


    public void insertNote(Note note) {
        new InsertAsyncTask(noteDao).execute(note);
    }

    public void deleteNote(Note note) {
        new DeleteAsyncTask(noteDao).execute(note);
    }

    public void deleteAllNotes() {
        new DeleteAllNotesAsyncTask(noteDao).execute();
    }

    public void updateNote(Note note) {
        new UpdateNoteAsyncTask(noteDao).execute(note);
    }

    public LiveData< List<Note> > getAllNotes() {
        return this.fetchAllNotesLiveData;
    }



    // background threads
    private static class InsertAsyncTask extends AsyncTask<Note, Void, Void> {

        private NoteDao noteDao;

        public InsertAsyncTask(NoteDao noteDao) {
            this.noteDao = noteDao;
        }

        @Override
        protected Void doInBackground(Note... notes) {
            noteDao.insertNote(notes[0]);
            return null;
        }
    }

    private static class DeleteAsyncTask extends AsyncTask<Note, Void, Void> {

        private NoteDao noteDao;

        public DeleteAsyncTask(NoteDao noteDao) {
            this.noteDao = noteDao;
        }

        @Override
        protected Void doInBackground(Note... notes) {
            this.noteDao.deleteNote(notes[0]);
            return null;
        }
    }

    private static class DeleteAllNotesAsyncTask extends AsyncTask<Void, Void, Void> {

        private NoteDao noteDao;

        public DeleteAllNotesAsyncTask(NoteDao noteDao) {
            this.noteDao = noteDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            noteDao.deleteAllNotes();
            return null;
        }
    }

    private static class UpdateNoteAsyncTask extends AsyncTask<Note, Void, Void> {

        private NoteDao noteDao;

        public UpdateNoteAsyncTask(NoteDao noteDao) {
            this.noteDao = noteDao;
        }

        @Override
        protected Void doInBackground(Note... notes) {
            noteDao.updateNote(notes[0]);
            return null;
        }
    }

}
