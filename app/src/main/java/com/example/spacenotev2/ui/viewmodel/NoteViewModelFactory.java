package com.example.spacenotev2.ui.viewmodel;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class NoteViewModelFactory implements ViewModelProvider.Factory {

    private Context context;

    public NoteViewModelFactory(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {

        if (modelClass.isAssignableFrom(NoteViewModel.class)) {
            return (T) new NoteViewModel(this.context);
        }

        throw new IllegalArgumentException("viewModel class is not fond.");
        //return null;
    }
}
