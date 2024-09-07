package com.kclm.cels.service.impl;

import com.kclm.cels.dao.IBaseTermDao;
import com.kclm.cels.dao.ITestRecordDao;
import com.kclm.cels.dao.impl.BaseTermDaoImpl;
import com.kclm.cels.dao.impl.TestRecordDaoImpl;
import com.kclm.cels.entity.BaseTerm;
import com.kclm.cels.entity.TestRecord;
import com.kclm.cels.entity.Vocabulary;
import com.kclm.cels.entity.Word;
import com.kclm.cels.exceptions.NoTestRecordException;
import com.kclm.cels.service.ITestingService;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;


public class TestingServiceImpl implements ITestingService {

    private IBaseTermDao baseTermDao = new BaseTermDaoImpl();
    private ITestRecordDao testRecordDao = new TestRecordDaoImpl();

    @Override
    public List<BaseTerm> getTestingData(int num) {
        List<Word> words = new ArrayList<>(baseTermDao.getAllWords());
        List<Vocabulary> vocabularies = new ArrayList<>(baseTermDao.getAllVocabularies());
        List<BaseTerm> baseTerms = new ArrayList<>();
        baseTerms.addAll(words);
        baseTerms.addAll(vocabularies);
        Collections.shuffle(baseTerms);
        if (num > 0 && num <= baseTerms.size()) {
            return baseTerms.subList(0, num);
        }
        return null;
    }

    @Override
    public List<TestRecord> reviewTestRecord() throws NoTestRecordException {
        File t = new File(TEST_RECORD_DIR);
        if (!t.exists()) {
            throw new NoTestRecordException("历史测试记录目录不存在");
        }
        File tr = new File(TEST_RECORD_DIR, TEST_RECORD_FILE);
        if (!tr.exists()) {
            throw new NoTestRecordException("历史测试记录文件不存在");
        }
        List<TestRecord> records = testRecordDao.read(tr.getAbsolutePath());
        List<TestRecord> sortedRecords = records.stream()
                .sorted(Comparator.comparing(TestRecord::getTime).reversed())
                .collect(Collectors.toList());
        return sortedRecords;
    }

    @Override
    public void saveTestRecord(TestRecord record) {
        List<TestRecord> records = null;
        try {
            records = testRecordDao.read(TEST_RECORD_DIR + "/" + TEST_RECORD_FILE);
        } catch (NoTestRecordException e) {
            System.out.println(e.getMessage());
            records = new ArrayList<>();
        }

        records.add(record);
        File t = new File(TEST_RECORD_DIR);
        if (!t.exists()) {
            t.mkdirs();
        }
        testRecordDao.write(records, TEST_RECORD_DIR + "/" + TEST_RECORD_FILE);
    }
}
