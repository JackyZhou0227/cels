package com.kclm.cels.service.impl;

import com.kclm.cels.dao.IBaseTermDao;
import com.kclm.cels.dao.impl.BaseTermDaoImpl;
import com.kclm.cels.entity.PageBean;
import com.kclm.cels.entity.Vocabulary;
import com.kclm.cels.entity.Word;
import com.kclm.cels.service.IBrowserService;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;


/**
 * TODO 浏览单词和词汇的业务层实现
 */
public class BrowserServiceImpl implements IBrowserService {

    private IBaseTermDao baseTermDao = new BaseTermDaoImpl();
    @Override
    public Map<Character, Set<Word>> getFirstMap() {

        Map<Character, Set<Word>> map = new HashMap<>();

        Set<Word> words = baseTermDao.getAllWords();
        for (Word word : words) {
            Character firstChar = word.getFirst();
            if(!map.containsKey(firstChar)){
                map.put(firstChar,new HashSet<>());
            }
            map.get(firstChar).add(word);
        }
        return map;
    }

    @Override
    public PageBean getPageVocabularies() {

        Set<Vocabulary> allVocabularies = baseTermDao.getAllVocabularies();
        if(allVocabularies != null){
            PageBean pageBean = new PageBean(allVocabularies,PAGE_SIZE);
            return pageBean;
        }

        return null;
    }
}
