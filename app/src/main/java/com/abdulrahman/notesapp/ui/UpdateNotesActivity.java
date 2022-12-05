package com.abdulrahman.notesapp.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.abdulrahman.notesapp.Model.Notes;
import com.abdulrahman.notesapp.R;
import com.abdulrahman.notesapp.Utils;
import com.abdulrahman.notesapp.ViewModel.NotesViewModel;
import com.abdulrahman.notesapp.databinding.ActivityUpdateNotesBinding;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class UpdateNotesActivity extends AppCompatActivity {

    ActivityUpdateNotesBinding binding;

    private int updatedID;
    private String updatedTitle;
    private String updatedSubtitle;
    private String updatedPriority;
    private String updatedNotes;
    private NotesViewModel notesViewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityUpdateNotesBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        notesViewModel = new ViewModelProvider(this).get(NotesViewModel.class);

        updatedID = getIntent().getIntExtra(Utils.ID,0);
        updatedTitle = getIntent().getStringExtra(Utils.TITLE);
        updatedSubtitle = getIntent().getStringExtra(Utils.SUBTITLE);
        updatedPriority = getIntent().getStringExtra(Utils.PRIORITY);
        updatedNotes = getIntent().getStringExtra(Utils.NOTE);

        binding.updateTitle.setText(updatedTitle);
        binding.updateSubtitle.setText(updatedSubtitle);
        binding.updateNotes.setText(updatedNotes);

        binding.greenPriority.setOnClickListener(V-> {
            binding.greenPriority.setImageResource(R.drawable.ic_baseline_done_24);
            binding.yellowPriority.setImageResource(0);
            binding.redPriority.setImageResource(0);
            updatedPriority = "1";
        });
        binding.yellowPriority.setOnClickListener(V-> {
            binding.greenPriority.setImageResource(0);
            binding.yellowPriority.setImageResource(R.drawable.ic_baseline_done_24);
            binding.redPriority.setImageResource(0);
            updatedPriority = "2";
        });
        binding.redPriority.setOnClickListener(V-> {
            binding.greenPriority.setImageResource(0);
            binding.yellowPriority.setImageResource(0);
            binding.redPriority.setImageResource(R.drawable.ic_baseline_done_24);
            updatedPriority = "3";
        });

        binding.updateNotesBtn.setOnClickListener(view -> {
            String title = binding.updateTitle.getText().toString();
            String subtitle = binding.updateSubtitle.getText().toString();
            String notes = binding.updateNotes.getText().toString();

            UpdateNotes(title, subtitle, notes);
        });

        if ("1".equalsIgnoreCase(updatedPriority)){
            binding.greenPriority.setImageResource(R.drawable.ic_baseline_done_24);

        }else if ("2".equalsIgnoreCase(updatedPriority)){
            binding.yellowPriority.setImageResource(R.drawable.ic_baseline_done_24);
        }
        else { binding.redPriority.setImageResource(R.drawable.ic_baseline_done_24); }

    }

    private void UpdateNotes(String title, String subtitle, String notes) {

        Date c = Calendar.getInstance().getTime();
        SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy", Locale.getDefault());
        String formattedDate = df.format(c);

        Notes updateNotes = new Notes();

        updateNotes.id = updatedID;
        updateNotes.notesTitle = title;
        updateNotes.notesSubtitle = subtitle;
        updateNotes.notes = notes;
        updateNotes.notesPriority = updatedPriority;
        updateNotes.notesDate = formattedDate;

        notesViewModel.updateNotes(updateNotes);
        Toast.makeText(this,"Note Updated successfully",Toast.LENGTH_SHORT).show();

        finish();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.delete_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.ic_delete){
            DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    switch (which){
                        case DialogInterface.BUTTON_POSITIVE:
                            notesViewModel.deleteNotes(updatedID);
                            finish();
                            break;

                        case DialogInterface.BUTTON_NEGATIVE:
                            dialog.dismiss();
                            break;
                    }
                }
            };
            AlertDialog.Builder ab = new AlertDialog.Builder(this);
            ab.setMessage("Are you sure you want to delete?").setPositiveButton("Yes", dialogClickListener)
                    .setNegativeButton("No", dialogClickListener).show();
        }
        return true;
    }
}