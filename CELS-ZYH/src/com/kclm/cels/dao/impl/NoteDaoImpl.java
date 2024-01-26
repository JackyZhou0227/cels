package com.kclm.cels.dao.impl;

import com.kclm.cels.dao.INoteDao;
import com.kclm.cels.entity.Note;
import com.kclm.cels.exceptions.NoNoteException;

import java.io.*;
import java.util.List;

/***
 * TODO Note类的读写方法的持久层实现
 */
public class NoteDaoImpl implements INoteDao {

    /***
     * TODO 读取文件中的Note对象的List集合
     * @param fileName 文件名
     * @return List<Note> 文件中读取的Note对象的List集合
     */
    @Override
    public List<Note> readNote(String fileName) throws NoNoteException {
        //判断 fileName是否存在
        if(!new File(fileName).exists()) {
            return null;
        }
        // 调用readObject方法直接读到
        try ( ObjectInputStream in = new ObjectInputStream(new FileInputStream(fileName))){
            List<Note> notes = (List<Note>) in.readObject();
            return notes;
        }catch (Exception e){
            return null;
        }
    }

    /***
     * TODO 将文件中的Note对象的List集合写入文件
     * @param notes 需要写入文件的Note类型的List集合
     * @param fileName 文件名
     */
    @Override
    public void writeNote(List<Note> notes, String fileName) {
        File file = new File(fileName);
        if(!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
        //利用利用对象流完成写入
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file))){
            out.writeObject(notes);
        }
        //处理异常
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
