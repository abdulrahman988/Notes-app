package com.abdulrahman.notesapp.Repository;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.abdulrahman.notesapp.Dao.NotesDao;
import com.abdulrahman.notesapp.Database.NotesDatabase;
import com.abdulrahman.notesapp.Model.Notes;

import java.util.List;

public class NotesRepository {
    public NotesDao notesDao;
    public LiveData<List<Notes>> getAllNotes;
    public LiveData<List<Notes>> highToLow;
    public LiveData<List<Notes>> lowToHigh;

    public NotesRepository(Application application) {
        NotesDatabase database = NotesDatabase.getDataBaseInstance(application);
        notesDao = database.notesDao();
        getAllNotes = notesDao.getAllNotes();
        highToLow = notesDao.highToLow();
        lowToHigh = notesDao.lowToHigh();
    }

    public void insertNotes(Notes notes){
        notesDao.insertNotes(notes);
    }

    public void deleteNotes(int id){
        notesDao.deleteNotes(id);
    }
    public void updateNotes(Notes notes){
        notesDao.updateNotes(notes);
    }
}
