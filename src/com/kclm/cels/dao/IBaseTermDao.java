package com.kclm.cels.dao;

import com.kclm.cels.entity.Vocabulary;
import com.kclm.cels.entity.Word;

import java.util.Map;
import java.util.Set;

public interface IBaseTermDao {

    String WORD_FILE = "datas/dic/w.dic";

    String VOCABULARY_FILE = "datas/dic/v.dic";

    Set<Word> getAllWords();

    Set<Vocabulary> getAllVocabularies();

    void writeToFile(Map<String, String> map, String fileName);

    Map<String, String> readFromFile(String fileName);


}
