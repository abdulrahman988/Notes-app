package com.abdulrahman.notesapp.Dao;

import androidx.lifecycle.LiveData;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.abdulrahman.notesapp.Model.Notes;

import java.util.List;

@androidx.room.Dao
public interface NotesDao {

    @Query("SELECT * FROM Notes_DataBase")
    LiveData<List<Notes>>getAllNotes();

    @Query("SELECT * FROM NOTES_DATABASE ORDER BY notes_priority DESC")
    LiveData<List<Notes>>highToLow();

    @Query("SELECT * FROM NOTES_DATABASE ORDER BY notes_priority ASC")
    LiveData<List<Notes>>lowToHigh();

    @Query("DELETE FROM Notes_DataBase WHERE id=:id ")
    void deleteNotes(int id);

    @Insert
    void insertNotes(Notes...  notes);

    @Update
    void updateNotes(Notes notes);

}
