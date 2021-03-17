package com.example.spacenotev2.ui.activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.spacenotev2.R;
import com.example.spacenotev2.databinding.ActivityCreateNoteBinding;
import com.example.spacenotev2.model.Note;
import com.example.spacenotev2.ui.viewmodel.NoteViewModel;
import com.example.spacenotev2.ui.viewmodel.NoteViewModelFactory;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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

        initViewModel();

        binding.textViewDateTime.setText(
                new SimpleDateFormat("EEEE, dd MMMM yyyy HH:mm a", Locale.getDefault()).format(new Date())
        );


        // default note color
        selectedNoteColor = "#333333";
        setSubTitleIndicatorColor();

        binding.noteColors.viewColor1.setOnClickListener(v -> {

            selectedNoteColor = "#333333";

            binding.noteColors.imageViewColor1.setImageResource(R.drawable.ic_done);
            binding.noteColors.imageViewColor2.setImageResource(0);
            binding.noteColors.imageViewColor3.setImageResource(0);
            binding.noteColors.imageViewColor4.setImageResource(0);
            binding.noteColors.imageViewColor5.setImageResource(0);

            setSubTitleIndicatorColor();
        });

        binding.noteColors.viewColor2.setOnClickListener(v -> {

            selectedNoteColor = "#FDBE3B";

            binding.noteColors.imageViewColor1.setImageResource(0);
            binding.noteColors.imageViewColor2.setImageResource(R.drawable.ic_done);
            binding.noteColors.imageViewColor3.setImageResource(0);
            binding.noteColors.imageViewColor4.setImageResource(0);
            binding.noteColors.imageViewColor5.setImageResource(0);

            setSubTitleIndicatorColor();

        });

        binding.noteColors.viewColor3.setOnClickListener(v -> {

            selectedNoteColor = "#FF4842";

            binding.noteColors.imageViewColor1.setImageResource(0);
            binding.noteColors.imageViewColor2.setImageResource(0);
            binding.noteColors.imageViewColor3.setImageResource(R.drawable.ic_done);
            binding.noteColors.imageViewColor4.setImageResource(0);
            binding.noteColors.imageViewColor5.setImageResource(0);

            setSubTitleIndicatorColor();

        });

        binding.noteColors.viewColor4.setOnClickListener(v -> {

            selectedNoteColor = "#3A52Fc";

            binding.noteColors.imageViewColor1.setImageResource(0);
            binding.noteColors.imageViewColor2.setImageResource(0);
            binding.noteColors.imageViewColor3.setImageResource(0);
            binding.noteColors.imageViewColor4.setImageResource(R.drawable.ic_done);
            binding.noteColors.imageViewColor5.setImageResource(0);

            setSubTitleIndicatorColor();

        });

        binding.noteColors.viewColor5.setOnClickListener(v -> {

            selectedNoteColor = "#000000";

            binding.noteColors.imageViewColor1.setImageResource(0);
            binding.noteColors.imageViewColor2.setImageResource(0);
            binding.noteColors.imageViewColor3.setImageResource(0);
            binding.noteColors.imageViewColor4.setImageResource(0);
            binding.noteColors.imageViewColor5.setImageResource(R.drawable.ic_done);

            setSubTitleIndicatorColor();

        });




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
                selectedNoteColor,
                ""
        );
    }

    private boolean checkDataFromUI() {
        return !getDataFromUI().getTitle().isEmpty() && !getDataFromUI().getSubTitle().isEmpty();
    }

    private void nextActivity() {
        Intent intentNote = new Intent(this, MainActivity.class);
        startActivity(intentNote);
    }

    private void setSubTitleIndicatorColor() {
        GradientDrawable gradientDrawable = (GradientDrawable) binding.viewSubtitleIndicator.getBackground();
        gradientDrawable.setColor(Color.parseColor(getDataFromUI().getColor()));
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
        }
    }
}