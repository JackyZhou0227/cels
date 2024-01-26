package com.kclm.cels.service.impl;

import com.kclm.cels.dao.INoteDao;
import com.kclm.cels.dao.impl.NoteDaoImpl;
import com.kclm.cels.entity.Note;
import com.kclm.cels.exceptions.NoNoteException;
import com.kclm.cels.service.INoteService;

import java.io.File;
import java.util.*;

public class NoteServiceImpl implements INoteService {

    private INoteDao noteDao = new NoteDaoImpl();
    @Override
    public List<Note> getNote() throws NoNoteException {

        File t = new File(NOTE_DIR);
        if(!t.exists()){
            throw new NoNoteException("生词本目录不存在");
        }
        File tr = new File(NOTE_DIR,NOTE_FILE);
        if(!tr.exists()){
            throw new NoNoteException("生词本文件不存在");
        }

        return noteDao.readNote(tr.getAbsolutePath());
    }

    @Override
    public void saveNote(String en, String cn) throws NoNoteException {
        List<Note> notes = null;
        File file = new File(NOTE_DIR,NOTE_FILE);
        try {
            notes = getNote();
            if (notes == null) {
                notes = new ArrayList<>();
            }
        }catch (NoNoteException e){
            System.out.println(e.getMessage());
            notes = new ArrayList<>();
        }
        boolean isExist = false;
        for (Note n : notes) {
            if (n.getEn().equals(en)){
                isExist = true;
            }
        }
        if (!isExist){
            Note note = new Note();
            note.setEn(en);
            note.setCn(cn);
            notes.add(note);
        }
        noteDao.writeNote(notes,file.getAbsolutePath());
    }


    @Override
    public void clearNote() {
        File file = new File(NOTE_DIR, NOTE_FILE);

        // 获取笔记列表，处理可能抛出的异常
        List<Note> notes;
        try {
            notes = getNote();

            if (notes != null) {
                // 使用 Iterator 来安全地遍历和移除元素
                Iterator<Note> iterator = notes.iterator();
                while (iterator.hasNext()) {
                    Note note = iterator.next();
                    if (note.getRightNum() >= 3) {
                        iterator.remove();
                    }
                }
                noteDao.writeNote(notes, file.getAbsolutePath());
            }
        } catch (NoNoteException e) {
            System.out.println(e.getMessage());
            // 如果由于 NoNoteException 导致 notes 为空或未初始化，同样清空文件
            if (file.exists()) {
                file.delete();
            }
        }

    }

    @Override
    public void updateNote(Note note) throws NoNoteException {
        String fileName = NOTE_DIR+"/"+NOTE_FILE;
        List<Note> notes = getNote();
        try {
            for (Note note1 : notes) {
                if (note1.getEn().equals(note.getEn())){
                    notes.remove(note1);
                    notes.add(note);
                }
            }
        }catch (Exception e){

        }

        noteDao.writeNote(notes,fileName);
    }


}
