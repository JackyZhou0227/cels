package com.kclm.cels.dao.impl;

import com.kclm.cels.dao.IBaseTermDao;
import com.kclm.cels.entity.Vocabulary;
import com.kclm.cels.entity.Word;

import java.io.*;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

/***
 * TODO 单词持久层实现类
 */
public class BaseTermDaoImpl implements IBaseTermDao {

    /***
     * TODO 获取所有Word，并返回Set
     * @return 包含所有Word的Set
     */
    @Override
    public Set<Word> getAllWords() {
        Set<Word> words = new TreeSet<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(WORD_FILE))){

            String line;
            while( (line = reader.readLine()) != null) {

                String[] parts = line.split(" +");
                String en = parts[0];
                String[] cn = parts[1].split(":+");
                Word word = new Word(en, cn);
                words.add(word);
            }

        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }

        return words;
    }

    /***
     * TODO 获取所有Vocabulary，并返回Set
     * @return 包含所有Vocabulary的Set
     */
    @Override
    public Set<Vocabulary> getAllVocabularies() {
        Set<Vocabulary> vocabularies = new TreeSet<>();

        try( BufferedReader reader= new BufferedReader(new FileReader(VOCABULARY_FILE))) {

            String line;
            while( (line = reader.readLine()) != null) {
                String[] parts = line.split("#+");
                String en = parts[0];
                String[] cn = parts[1].split(":+");
                Vocabulary vocabulary = new Vocabulary(en, cn);
                vocabularies.add(vocabulary);
            }

        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }

        return vocabularies;
    }

    /***
     * TODO 利用对象流完成Map对象的序列化
     * @param map 需要写入文件的map
     * @param fileName 文件名
     */
    @Override
    public void writeToFile(Map<String, String> map, String fileName) {
        //1.创建File对象
        File file = new File(fileName);

        //2.判断此file目录是否存在
        if(!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }

        //3.利用利用对象流完成写入
        try(ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file))) {
            out.writeObject(map);
        }
        //4.处理异常
        catch (Exception e) {
            throw new RuntimeException("写入失败",e);
        }
    }

    @Override
    public Map<String, String> readFromFile(String fileName) {
        //判断 fileName是否存在
        if(!new File(fileName).exists()) {
            return null;
        }
        // 调用readObject方法直接读到
        try ( ObjectInputStream in = new ObjectInputStream(new FileInputStream(fileName))){
            Map<String, String> map = (Map<String, String>) in.readObject();
            return map;
        }catch (Exception e){
            e.printStackTrace();
        }
        // 正常返回
        return null;
    }
}
