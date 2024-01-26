package com.kclm.cels.service.impl;

import com.kclm.cels.dao.IBaseTermDao;
import com.kclm.cels.dao.impl.BaseTermDaoImpl;
import com.kclm.cels.entity.BaseTerm;
import com.kclm.cels.entity.Vocabulary;
import com.kclm.cels.entity.Word;
import com.kclm.cels.exceptions.NoHistoryException;
import com.kclm.cels.service.IGameService;

import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.*;



public class GameServiceImpl implements IGameService {

    private IBaseTermDao baseTermDao = new BaseTermDaoImpl();
    @Override
    public Map<String, String> en2cn(boolean isNew) throws NoHistoryException {

        if (isNew) {
            List<BaseTerm> baseTerms = init();
            Map <String, String> map = new HashMap<>();
            for (BaseTerm baseTerm : baseTerms) {
                String key = baseTerm.getEn();
                StringBuilder builder = new StringBuilder();
                for (String s : baseTerm.getCn()) {
                    builder.append(s.trim()).append(",");
                }
                builder.deleteCharAt(builder.length()-1);
                map.put(key,builder.toString());
            }
            return map;
        }else {
            Map<String, String> map = baseTermDao.readFromFile(checkHistory(true));
            return map;
        }

    }

    private String checkHistory(boolean en2cn) throws NoHistoryException {
        File h = new File(HISTORY_DIR);
        if (!h.exists()) {
            throw new NoHistoryException("游戏记录目录不存在");
        }
        File hr = null;
        if (en2cn){
            hr = new File(EN_TO_CN_FILE);
        }else {
            hr = new File(CN_TO_EN_FILE);
        }

        if(!hr.exists()){
            throw new NoHistoryException("游戏记录文件不存在");
        }
        return hr.getAbsolutePath();
    }

    @Override
    public Map<String, String> cn2en(boolean isNew) throws NoHistoryException {
        if (isNew) {
            List<BaseTerm> baseTerms = init();
            Map <String, String> map = new HashMap<>();
            for (BaseTerm baseTerm : baseTerms) {
                String en = baseTerm.getEn();
                StringBuilder builder = new StringBuilder();
                for (String s : baseTerm.getCn()) {
                    builder.append(s.trim()).append(",");
                }
                builder.deleteCharAt(builder.length()-1);
                map.put(builder.toString(),en);
            }
            return map;
        }else{

            Map<String, String> map = baseTermDao.readFromFile(checkHistory(false));
            return map;
        }
    }

    @Override
    public void saveGameHistory(Map<String, String> map, boolean en2cn) {

        String fileName = EN_TO_CN_FILE;
        if (!en2cn){
            fileName = CN_TO_EN_FILE;
        }
        File file = new File(HISTORY_DIR);
        if(!file.exists()) {
            file.mkdirs();
        }
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(fileName))){
            out.writeObject(map);
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public List<BaseTerm> init(){
        Set<Word> wordsSet = baseTermDao.getAllWords();
        Set<Vocabulary> vocabulariesSet = baseTermDao.getAllVocabularies();
        List<BaseTerm> baseTerms = new ArrayList<>();
        baseTerms.addAll(wordsSet);
        baseTerms.addAll(vocabulariesSet);
        Collections.shuffle(baseTerms);
        return baseTerms;
    }

}
