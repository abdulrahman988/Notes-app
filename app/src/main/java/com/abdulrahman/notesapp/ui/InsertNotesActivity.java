package com.abdulrahman.notesapp.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.widget.Toast;

import com.abdulrahman.notesapp.Model.Notes;
import com.abdulrahman.notesapp.R;
import com.abdulrahman.notesapp.ViewModel.NotesViewModel;
import com.abdulrahman.notesapp.databinding.ActivityInsertNotesBinding;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class InsertNotesActivity extends AppCompatActivity {

    private ActivityInsertNotesBinding binding;
    private String title, subtitle, notes;
    private NotesViewModel notesViewModel;
    private String priority = "1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityInsertNotesBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        notesViewModel = new ViewModelProvider(this).get(NotesViewModel.class);

        binding.greenPriority.setOnClickListener(V-> {
            binding.greenPriority.setImageResource(R.drawable.ic_baseline_done_24);
            binding.yellowPriority.setImageResource(0);
            binding.redPriority.setImageResource(0);
            priority = "1";
        });
        binding.yellowPriority.setOnClickListener(V-> {
            binding.greenPriority.setImageResource(0);
            binding.yellowPriority.setImageResource(R.drawable.ic_baseline_done_24);
            binding.redPriority.setImageResource(0);
            priority = "2";
        });
        binding.redPriority.setOnClickListener(V-> {
            binding.greenPriority.setImageResource(0);
            binding.yellowPriority.setImageResource(0);
            binding.redPriority.setImageResource(R.drawable.ic_baseline_done_24);
            priority = "3";
        });
        binding.doneNotesBtn.setOnClickListener(v->{

            title = binding.notesTitle.getText().toString();
            subtitle = binding.notesSubtitle.getText().toString();
            notes = binding.notesData.getText().toString();

            CreateNotes(title, subtitle, notes);
        });
    }

    private void CreateNotes(String title, String subtitle, String notes) {

        Date c = Calendar.getInstance().getTime();
        SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy", Locale.getDefault());
        String formattedDate = df.format(c);

        Notes notes1 = new Notes();
        notes1.notesTitle = title;
        notes1.notesSubtitle = subtitle;
        notes1.notes = notes;
        notes1.notesPriority = priority;
        notes1.notesDate = formattedDate;

        notesViewModel.insertNotes(notes1);
        Toast.makeText(this,"Note Created successfully",Toast.LENGTH_SHORT).show();

        finish();

    }
}