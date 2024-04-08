package com.kclm.cels.service;

import com.kclm.cels.entity.Note;
import com.kclm.cels.exceptions.NoNoteException;
import com.kclm.cels.exceptions.NoTestRecordException;

import java.util.List;

public interface INoteService {

    // 生词本文件路径
    String NOTE_DIR = "datas/note";
    // 生词本文件名
    String NOTE_FILE = "wrong.txt";


    /**
     * TODO 从错题本文件获取生词Note类的List集合
     * @return List<Note> 生词集合
     * @throws NoNoteException 抛出异常
     */
    List<Note> getNote () throws NoNoteException;

    /**
     * TODO 保存错题至错题本文件
     * @param en 英文
     * @param cn 中文解释
     * @throws NoNoteException 抛出异常
     */
    void saveNote(String en,String cn) throws NoNoteException;

    /**
     * TODO 移除答对次数>=3的错题
     */
    void clearNote();

    /**
     * TODO 更新错题答对次数
     * @param note 要更新的生词对象
     * @throws NoNoteException 抛出异常
     */
    void updateNote(Note note) throws NoNoteException;
}
