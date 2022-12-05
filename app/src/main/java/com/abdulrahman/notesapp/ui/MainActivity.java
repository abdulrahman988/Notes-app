package com.abdulrahman.notesapp.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SearchView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.abdulrahman.notesapp.Adapter.NotesAdapter;
import com.abdulrahman.notesapp.Model.Notes;
import com.abdulrahman.notesapp.R;
import com.abdulrahman.notesapp.ViewModel.NotesViewModel;
import com.abdulrahman.notesapp.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private NotesViewModel notesViewModel;

    private NotesAdapter adapter;
    private List<Notes> filterNotesList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        notesViewModel = new ViewModelProvider(this).get(NotesViewModel.class);

        binding.tvNoFilter.setOnClickListener(view -> {
            binding.tvNoFilter.setBackgroundResource(R.drawable.selected);
            binding.tvHiLow.setBackgroundResource(0);
            binding.tvLowHi.setBackgroundResource(0);
            loadData(0);
        });

        binding.tvHiLow.setOnClickListener(view -> {
            binding.tvNoFilter.setBackgroundResource(0);
            binding.tvHiLow.setBackgroundResource(R.drawable.selected);
            binding.tvLowHi.setBackgroundResource(0);
            loadData(1);
        });

        binding.tvLowHi.setOnClickListener(view -> {
            binding.tvNoFilter.setBackgroundResource(0);
            binding.tvHiLow.setBackgroundResource(0);
            binding.tvLowHi.setBackgroundResource(R.drawable.selected);
            loadData(2);
        });




        binding.newNotesBtn.setOnClickListener(view -> {
            startActivity(new Intent(MainActivity.this, InsertNotesActivity.class));
        });
        notesViewModel.getAllNotes.observe(this, notes -> {
          setAdapter(notes);
          filterNotesList = notes;
        });

    }

    //methods used
    private void loadData(int i) {
        if (i == 0){
            notesViewModel.getAllNotes.observe(this, notes -> {
                setAdapter(notes);
                filterNotesList = notes;
            });
        }else if (i == 1){
            notesViewModel.highToLow.observe(this, notes -> {
                setAdapter(notes);
                filterNotesList = notes;
            });
        }else {
            notesViewModel.lowToHigh.observe(this, notes -> {
                setAdapter(notes);
                filterNotesList = notes;
            });
        }
    }

    private void setAdapter(List<Notes> notes) {
        binding.notesRecycler.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        adapter = new NotesAdapter(MainActivity.this,notes);
        binding.notesRecycler.setAdapter(adapter);
    }

    private void notesFilter(String s) {
        ArrayList<Notes> filterNames = new ArrayList<>();

        for (Notes notes:this.filterNotesList){
            if (notes.notesTitle.contains(s) || notes.notesSubtitle.contains(s)){
                filterNames.add(notes);
            }
        }
        this.adapter.searchNotes(filterNames);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search_notes, menu);

        MenuItem menuItem = menu.findItem(R.id.app_bar_search);

        SearchView searchView = (SearchView) menuItem.getActionView();
        searchView.setQueryHint("Search notes here...");
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                notesFilter(s);
                return false;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }

}