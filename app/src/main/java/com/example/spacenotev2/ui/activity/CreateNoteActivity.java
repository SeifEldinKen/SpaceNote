package com.example.spacenotev2.ui.activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.spacenotev2.R;
import com.example.spacenotev2.databinding.ActivityCreateNoteBinding;
import com.example.spacenotev2.model.Note;
import com.example.spacenotev2.ui.viewmodel.NoteViewModel;
import com.example.spacenotev2.ui.viewmodel.NoteViewModelFactory;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class CreateNoteActivity extends AppCompatActivity {

    private ActivityCreateNoteBinding binding;
    private NoteViewModel noteViewModel;
    private String selectedNoteColor;
    private static final int REQUEST_CODE_SELECT_IMAGE = 1;
    private Uri imageUri;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // init view binding
        binding = ActivityCreateNoteBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.textViewDateTime.setText(
                new SimpleDateFormat("EEEE, dd MMMM yyyy HH:mm a", Locale.getDefault()).format(new Date())
        );

        initViewModel();

        // default note color
        selectedNoteColor = "#333333";
        setSubTitleIndicatorColor(selectedNoteColor, R.drawable.ic_done, 0, 0, 0, 0);


        binding.noteColors.viewColor1.setOnClickListener(v -> {
            setSubTitleIndicatorColor("#333333", R.drawable.ic_done, 0, 0, 0, 0);
        });

        binding.noteColors.viewColor2.setOnClickListener(v -> {
            setSubTitleIndicatorColor("#FDBE3B", 0, R.drawable.ic_done, 0, 0, 0);
        });

        binding.noteColors.viewColor3.setOnClickListener(v -> {
            setSubTitleIndicatorColor("#FF4842", 0, 0, R.drawable.ic_done, 0, 0);
        });

        binding.noteColors.viewColor4.setOnClickListener(v -> {
            setSubTitleIndicatorColor("#3A52Fc", 0, 0, 0, R.drawable.ic_done, 0);
        });

        binding.noteColors.viewColor5.setOnClickListener(v -> {
            setSubTitleIndicatorColor("#000000", 0, 0, 0, 0, R.drawable.ic_done);
        });

        binding.noteColors.layoutAddImage.setOnClickListener(v -> {
            selectImage();
        });

        binding.imageViewSaveNote.setOnClickListener(v -> {
            insert();
            goToActivityNext();
        });


    }


    private void initViewModel() {
        // init view model
        noteViewModel = new ViewModelProvider(this, new NoteViewModelFactory(this)).get(NoteViewModel.class);
    }

    private void insert() {
        // this method add new note to database
        noteViewModel.insertNote(getDataFromUI());
    }

    private Note getDataFromUI() {
        // this method fetch all data from user interface
        return new Note(
                binding.editTextNoteTitle.getText().toString().trim(),
                binding.editTextNoteSubtitle.getText().toString().trim(),
                binding.editTextNote.getText().toString().trim(),
                binding.textViewDateTime.getText().toString(),
                imageUri == null ? "" : imageUri.toString(),
                selectedNoteColor,
                ""
        );
    }


    private void goToActivityNext() {
        Intent intentNote = new Intent(this, MainActivity.class);
        startActivity(intentNote);
    }

    private void setSubTitleIndicatorColor(String color, int...ids) {

        selectedNoteColor = color;

        binding.noteColors.imageViewColor1.setImageResource(ids[0]);
        binding.noteColors.imageViewColor2.setImageResource(ids[1]);
        binding.noteColors.imageViewColor3.setImageResource(ids[2]);
        binding.noteColors.imageViewColor4.setImageResource(ids[3]);
        binding.noteColors.imageViewColor5.setImageResource(ids[4]);

        GradientDrawable gradientDrawable = (GradientDrawable) binding.viewSubtitleIndicator.getBackground();
        gradientDrawable.setColor(Color.parseColor(selectedNoteColor));
    }

    private void selectImage() {

        Intent intentImage = new Intent();
        intentImage.setType("image/*");
        intentImage.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intentImage, REQUEST_CODE_SELECT_IMAGE);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE_SELECT_IMAGE && resultCode == RESULT_OK && data != null) {
            imageUri = data.getData();

            binding.imageNote.setImageURI(imageUri);
            binding.imageNote.setVisibility(View.VISIBLE);

        }
    }



}