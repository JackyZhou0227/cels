package com.kclm.cels.dao;

import com.kclm.cels.entity.Note;
import com.kclm.cels.exceptions.NoNoteException;

import java.util.List;

public interface INoteDao {

    List<Note> readNote(String fileName) throws NoNoteException;
    void writeNote(List<Note> notes, String fileName);
}
