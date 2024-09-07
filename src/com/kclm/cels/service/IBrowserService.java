package com.kclm.cels.service;

import com.kclm.cels.entity.PageBean;
import com.kclm.cels.entity.Word;

import java.util.*;

public interface IBrowserService {

    int PAGE_SIZE = 15;

    /***
     * 加载所有首字母相同的单词
     * @return Map<Character, Set < Word>>
     */
    Map<Character, Set<Word>> getFirstMap();

    /***
     * 分页的方式输出所有词汇
     * @return PageBean
     */
    PageBean getPageVocabularies();
}
