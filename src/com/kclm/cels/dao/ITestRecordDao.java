package com.kclm.cels.dao;

import com.kclm.cels.entity.TestRecord;
import com.kclm.cels.exceptions.NoTestRecordException;

import java.util.List;

public interface ITestRecordDao {

    void write(List<TestRecord> records, String fileName);

    List<TestRecord> read(String fileName) throws NoTestRecordException;
}
