package com.kclm.cels.service;

import com.kclm.cels.entity.BaseTerm;
import com.kclm.cels.entity.TestRecord;
import com.kclm.cels.exceptions.NoTestRecordException;

import java.util.*;

public interface ITestingService {

    //定文存放测试记录的目录路径
    String TEST_RECORD_DIR = "datas/testing" ;
    //定义存放测试记录的文件路径
    String TEST_RECORD_FILE = "records.rec";

    /**
     * TODO 获取测试数据，从原始数据中随机抽取出指定数量的数据
     * @param num 指定数量
     * @return 抽取的数据集合
     */
    List<BaseTerm> getTestingData (int num);

    /**
     * TODO 从文件中读取保存过的测试记录
     * @return List<TestRecord>
     * @throws NoTestRecordException 测试记录文件不存在时抛出异常
     */
    List<TestRecord> reviewTestRecord() throws NoTestRecordException;

    /**
     * TODO 持久化测试的数据到文件
     * @param record 要保存的测试记录
     */
    void saveTestRecord(TestRecord record);
}
