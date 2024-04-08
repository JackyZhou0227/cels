package com.kclm.cels.dao.impl;

import com.kclm.cels.dao.ITestRecordDao;
import com.kclm.cels.entity.TestRecord;
import com.kclm.cels.exceptions.NoTestRecordException;

import java.io.*;
import java.util.List;

/***
 * TODO 测试记录持久层读写方法的实现类
 */
public class TestRecordDaoImpl implements ITestRecordDao {
    /***
     * TODO 把测试记录持久化到指定的文件中
     * @param records 测试记录的List集合
     * @param fileName 文件名
     */
    @Override
    public void write(List<TestRecord> records, String fileName) {

        //根据文件创建对象输出流
        File file = new File(fileName);

        if(!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }

        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(fileName))){
            //调用持久化对象的方法
            out.writeObject(records);
        }
        //处理IO异常
        catch (Exception e){
            throw new RuntimeException("持久化失败",e);
        }

    }


    /***
     * TODO 从文件是读出持久化的测试数据数据
     * @param fileName 文件名
     */
    @Override
    public List<TestRecord> read(String fileName) throws NoTestRecordException {

        if(!new File(fileName).exists()) {
            throw new NoTestRecordException( "无历史记录");
        }
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(fileName))){
            List<TestRecord> records = (List<TestRecord>) in.readObject();
            return records;
        }catch (Exception e){
           throw new NoTestRecordException( "无历史记录",e);
        }

    }
}
