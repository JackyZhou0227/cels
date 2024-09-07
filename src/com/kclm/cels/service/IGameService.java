package com.kclm.cels.service;

import com.kclm.cels.exceptions.NoHistoryException;

import java.util.*;

public interface IGameService {

    //定义保存历史记录的目录路径
    String HISTORY_DIR = "datas/history";
    // 定义保存 英文到中文没有完成的数据的存储文件
    String EN_TO_CN_FILE = "datas/history/en2cn.his";
    //定义保存 中文到英文没有完成的数据的存储文件
    String CN_TO_EN_FILE = "datas/history/cn2en.his";

    /***
     * 获取所有还没有完成的 提示英文，回答中文 的数据
     * 注: 数据来源有两个地方:
     * 1.从原始数据中获取所有的单词和词汇
     * 2.从之前保存过的历史文件中读取的数据 到底返回哪个数据，取决于参数 isNew 如果 isNew为真，则返回1 否则，返回2
     * @param isNew 此参数决定本方法的数据来源
     * @return 返回题集
     */
    Map<String, String> en2cn(boolean isNew) throws NoHistoryException;

    /***
     * 获取所有还没有完成的提示中文，回答英文的数据
     * 注: 数据来源有两个地方:
     * 1. 从原始数据中获取所有的单词和词汇
     * 2.从之前保存过的历史文件中读取的数据 到底返回哪个数据，取决于参数 isNew 如果 isNew为真，则返回1 否则，返回2
     * @param isNew 此参数决定本方法的数据来源
     * @return 返回题集
     */
    Map<String, String> cn2en(boolean isNew) throws NoHistoryException;

    /**
     * 持久化没有被用户所答的单词和词汇，第二个参数 en2cn为true，则把此数据持化久到en2cn.his 文件否则，持久化到cn2en.his 文件
     */
    void saveGameHistory(Map<String, String> map, boolean en2cn);
}
