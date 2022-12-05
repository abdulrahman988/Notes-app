package com.abdulrahman.notesapp.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.abdulrahman.notesapp.Model.Notes;
import com.abdulrahman.notesapp.Repository.NotesRepository;

import java.util.List;

public class NotesViewModel extends AndroidViewModel {

    public NotesRepository repository;
    public LiveData<List<Notes>> getAllNotes;
    public LiveData<List<Notes>> highToLow;
    public LiveData<List<Notes>> lowToHigh;

    public NotesViewModel(@NonNull Application application) {
        super(application);
        repository = new NotesRepository(application);
        getAllNotes = repository.notesDao.getAllNotes();

        highToLow = repository.highToLow;
        lowToHigh = repository.lowToHigh;
    }

    public void insertNotes(Notes notes){
        repository.insertNotes(notes);
    }

    public void deleteNotes(int id){
        repository.deleteNotes(id);
    }
    public  void updateNotes(Notes notes){
        repository.updateNotes(notes);
    }

}
