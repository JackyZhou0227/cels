package com.kclm.cels.controller.impl;

import com.kclm.cels.controller.Controller;
import com.kclm.cels.entity.*;
import com.kclm.cels.exceptions.NoHistoryException;
import com.kclm.cels.exceptions.NoNoteException;
import com.kclm.cels.exceptions.NoTestRecordException;
import com.kclm.cels.service.IBrowserService;
import com.kclm.cels.service.IGameService;
import com.kclm.cels.service.INoteService;
import com.kclm.cels.service.ITestingService;
import com.kclm.cels.service.impl.BrowserServiceImpl;
import com.kclm.cels.service.impl.GameServiceImpl;
import com.kclm.cels.service.impl.NoteServiceImpl;
import com.kclm.cels.service.impl.TestingServiceImpl;
import com.kclm.cels.util.InputUtil;
import com.kclm.cels.view.IView;
import com.kclm.cels.view.impl.ViewImpl;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.*;


public class ControllerImpl implements Controller {

    IView view = new ViewImpl();
    private IBrowserService browserService = new BrowserServiceImpl();

    private IGameService gameService = new GameServiceImpl();

    private ITestingService testingService = new TestingServiceImpl();

    private INoteService noteService = new NoteServiceImpl();

    @Override
    public void action() {

        view.welcome();//显示欢迎菜单

        //定义变量
        boolean exit = false;
        int choice = -1;

        //循环
        while(!exit) {

            view.mainMenu();
            //提示用户输入
            choice = InputUtil.getInt("请选择>"); //InputUtil是你要封装的一个获取用户输入的工具类
            //判断
            switch(choice) {
                case 1:
                    //业务1 浏览单词和词汇
                    Browser();
                    break;
                case 2:
                    //业务2 游戏
                    try {
                        Game();
                    }catch (NoHistoryException e1){
                        e1.printStackTrace();
                    }catch(NoNoteException e2){
                        e2.printStackTrace();
                }
                    break;
                case 3:
                    //业务3 做测试&查看测试记录
                    try {
                        Test();
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                    break;
                case 4:
                    try {
                        note();
                    }catch (NoNoteException e){
                        e.printStackTrace();
                    }

                    break;
                case 0:
                    //退出
                    exit = true;
                    InputUtil.release();
                    break;
                default:
                    //提示用户选择不正确
                    System.out.println("你的菜单选择不正确");
            }
        }
    }

    // 浏览基库（单词与词汇）
    private void Browser() {

        boolean rtnTop = false; //是否返回上一级
        int choice = -1;
        while(!rtnTop) {
            //提示用户输入
            view.showBrowserMenu();
            choice = InputUtil.getInt("请选择具体的功能>");
            //
            switch(choice) {

                //按字母浏览单词word
                case 1:

                    Map<Character, Set<Word>> firstMap = browserService.getFirstMap();
                    firstMap.forEach((key, value) -> {
                        System.out.printf("%s[%d] ", key, value.size());
                        if (key == 'M') {
                            System.out.println();
                        }
                    });
                    System.out.println();
                    char c = InputUtil.getLetter("请您选择要查看的首字母>");
                    Set<Word> words = firstMap.get(c);
                    int count = 0;
                    for (Word word : words) {
                        count ++;
                        String cn = String.join(",",word.getCn());
                        System.out.println(count + ". " + word.getEn() +" => 解释：" + "[" + cn + "]");
                    }
                    break;

                //分页浏览词汇vocabulary
                case 2:

                    PageBean page = browserService.getPageVocabularies();
                    page.setStyle("每页【"+page.getPageSize()+"】行 ※ 共计【"+page.getPages()+"】页 ※ 词汇量总数: 【"
                            +page.getTotal() + "】.   返回上一级:0");
                    System.out.println(page.getStyle());

                        boolean continueBrowsing = true;
                        while (continueBrowsing){
                            int pageNum = getPageNum(page.getPages());
                            if (pageNum == 0){
                                continueBrowsing = false;
                                break;
                            }
                            page.setCurrent(pageNum);
                            page.setStyle("当前页【"+page.getCurrent()+"】 ※ 共计【"+page.getPages()+"】页");
                            page.getPageData(pageNum).forEach((word) -> {

                                String cn = String.join(",", word.getCn());
                                System.out.println(word.getEn() + " => 解释：" + "[ " + cn + "]");
                            });
                            System.out.println(page.getStyle());
                        }
                    break;

                //返回上一级
                case 0:
                    rtnTop = true;
                    break;

                default:
                    //提示用户选择不正确。
                    System.out.println("你的子菜单选择不正确");
            }
        }
    }

    //获取正确的页码
    private int getPageNum(int max){
        int pageNum = InputUtil.getInt("返回上一级:0 请输入页码>【1~"+ max +"】>");
        if (pageNum < 0 || pageNum > max){
            System.out.println("输入的页码不正确");
            return getPageNum(max);
        }
        return pageNum;
    }

    //  做游戏学习单词【随机性】
    private void Game() throws NoHistoryException, NoNoteException {

        boolean rtnTop = false; //是否返回上一级
        int choice = -1;

        while(!rtnTop) {

            //提示用户输入
            view.showGameSubMenu();
            choice = InputUtil.getInt("请选择具体的功能>");
            //
            switch(choice) {
                case 1:

                    char c = InputUtil.getLetter(" ※ 是否要从上次继续? Y or N >");
                    boolean isNew = true;
                    if (c == 'Y'){
                        isNew = false;
                    }
                    Map<String, String> map = gameService.en2cn(isNew);
                    Map<String, String> tempMap = gameService.en2cn(isNew);
                    Set<String> keys = map.keySet();
                    int total = 0;
                    int rightNum = 0;
                    for (String key : keys) {

                        String answer = InputUtil.getString("【退出:e或者q】    ※〖" + key + "〗的中文是：");
                        String[] rAnswers = map.get(key).split(",");
                        boolean isRight = judgeAnswer(answer, rAnswers);
                        if (answer.equals("e") || answer.equals("q")){
                            summary(total, rightNum);
                            gameService.saveGameHistory(tempMap, true);
                            break;
                        }else if(isRight){
                            System.out.println("√ 回答正确, 完整解释是：【" + map.get(key) + "】");
                            rightNum ++;
                            total ++;
                            tempMap.remove(key);
                        }else if (!isRight){
                            System.out.println("〤 回答错误, 完整解释是：【" + map.get(key) + "】");
                            total ++;
                            tempMap.remove(key);
                            noteService.saveNote(key,map.get(key));
                        }
                    }
                    break;

                case 2:

                    c = InputUtil.getLetter(" ※ 是否要从上次继续? Y or N >");
                    isNew = true;
                    if (c == 'Y'){
                        isNew = false;
                    }
                    Map<String, String> map2 = gameService.cn2en(isNew);
                    Map<String, String> tempMap2 = gameService.cn2en(isNew);
                    keys = map2.keySet();
                    total = 0;
                    rightNum = 0;
                    for(String key : keys) {

                        String answer = InputUtil.getString("【退出:e或者q】    ※〖" + key + "〗的英文是：");
                        if (answer.equals("e") || answer.equals("q")){
                            summary(total, rightNum);
                            gameService.saveGameHistory(tempMap2, false);
                            break;
                        }else if(answer.equals(map2.get(key)) ){
                            System.out.println("√ 回答正确, 完整解释是：【" + map2.get(key) + "】");
                            rightNum ++;
                            total ++;
                            tempMap2.remove(key);
                        }else{
                            System.out.println("〤 回答错误, 完整解释是：【" + map2.get(key) + "】");
                            total ++;
                            tempMap2.remove(key);
                            noteService.saveNote(map2.get(key),key);
                        }
                    }
                    break;
                case 0:
                    rtnTop = true;
                    break;
                default:
                    //提示用户选择不正确。
                    System.out.println("你的子菜单选择不正确");
            }
        }

    }


    //答题总结
    private void summary(int total, int rightNum) {
        if(total == 0){
                System.out.println("大哥，好歹答一题吧");
        }else {
            double accuracy = (double) rightNum / total * 100;
            String formattedAccuracy = String.format("%.1f%%", accuracy);
            System.out.println("本次共答" + total + "题，正确题数:" + rightNum + "，正确率: " + formattedAccuracy);
        }
    }


    //测试玩法
    private void Test() throws NoTestRecordException, NoNoteException {
        boolean rtnTop = false; //是否返回上一级
        int choice = -1;
        while(!rtnTop) {
            //提示用户输入
            view.showTestingSubMenu();
            choice = InputUtil.getInt("请选择>");
            //
            switch(choice) {
                case 1:
                    int qNum = getQuestionNum();
                    System.out.println("本次测试共"+qNum+"题");
                    List<BaseTerm> baseTerms = testingService.getTestingData(qNum);
                    Map<String,String> map = new HashMap<>();
                    LocalDateTime startTime = LocalDateTime.now();
                    for (BaseTerm baseTerm : baseTerms) {
                        String key = baseTerm.getEn();
                        String value = "";
                        for (String s : baseTerm.getCn()) {
                            value = value  + s + ",";
                        }
                        int lastIndex = value.lastIndexOf(",");
                        if (lastIndex != -1) {
                            value = value.substring(0, lastIndex);
                        }
                        value = value.toString().trim();
                        map.put(key,value);
                    }
                    Set<String> keys = map.keySet();

                    TestRecord record = new TestRecord();
                    record.setTotalNum(qNum);
                    List<TestData> dataList = new ArrayList<>();
                    int total = 0;
                    int rightNum = 0;
                    for (String key : keys) {
                        String answer = InputUtil.getString("【退出:e或者q】    ※〖" + key + "〗的中文是：");
                        String[] rAnswers = map.get(key).split(",");
                        boolean isRight = judgeAnswer(answer, rAnswers);
                        if (answer.equals("e") || answer.equals("q")){
                            if(total == 0){
                                System.out.println("大哥，好歹答一题吧");
                            }else {
                                LocalDateTime endTime = LocalDateTime.now();
                                Duration duration = Duration.between(startTime, endTime);
                                Long totalTime = duration.getSeconds();
                                double accuracy = (double) rightNum / total * 100;
                                String formattedAccuracy = String.format("%.1f%%", accuracy);
                                System.out.println("本次共答" + total + "题，正确题数:" + rightNum + "，正确率: " + formattedAccuracy +
                                        "用时" + totalTime + "秒");
                                record.setTotalTime(totalTime);
                                record.setTime(LocalDateTime.now());
                                record.setDoNum(total);
                                record.setRightNum(rightNum);
                                record.setWrongNum(total - rightNum);
                                record.setDataList(dataList);
                                testingService.saveTestRecord(record);
                            }
                            break;
                        }else if(isRight){
                            System.out.println("√ 回答正确, 完整解释是：【" + map.get(key) + "】");
                            rightNum ++;
                            total ++;
                            TestData data = new TestData(total,key,map.get(key), true);
                            dataList.add(data);
                        }else if (!isRight){
                            System.out.println("〤 回答错误, 完整解释是：【" + map.get(key) + "】");
                            total ++;
                            TestData data = new TestData(total,key,map.get(key), false);
                            dataList.add(data);
                            noteService.saveNote(key,map.get(key));
                        }

                    }
                    LocalDateTime endTime = LocalDateTime.now();
                    Duration duration = Duration.between(startTime, endTime);
                    Long totalTime = duration.getSeconds();
                    double accuracy = (double) rightNum / total * 100;
                    String formattedAccuracy = String.format("%.1f%%", accuracy);
                    System.out.println("本次共答" + total + "题，正确题数:" + rightNum + "，正确率: " + formattedAccuracy +
                            "用时" + totalTime + "秒");
                    record.setTotalTime(totalTime);
                    record.setTime(LocalDateTime.now());
                    record.setDoNum(total);
                    record.setRightNum(rightNum);
                    record.setWrongNum(total-rightNum);
                    record.setDataList(dataList);
                    testingService.saveTestRecord(record);
                    break;
                case 2:

                    List<TestRecord> review = testingService.reviewTestRecord();
                    for (TestRecord testRecord : review) {
                        testRecord.printRecord();
                    }
                    break;
                case 0:
                    rtnTop = true;
                    break;
                default:
                    //提示用户选择不正确。
                    System.out.println("你的菜单选择不正确");
            }
        }
    }

    private int getQuestionNum(){
        int qNum = InputUtil.getInt("请输入要测试的题目数量[5 ~ 100]>");
        if (qNum<5||qNum>100){
            System.out.println("测试题数量在5-100之间");
            qNum = getQuestionNum();
        }
        return qNum;
    }

    private void note() throws NoNoteException {
        boolean rtnTop = false; //是否返回上一级
        int choice = -1;
        while(!rtnTop) {
            //提示用户输入
            view.showNoteMenu();
            choice = InputUtil.getInt("请选择具体的功能>");
            //
            switch(choice) {

                //练习生词
                case 1:
                    List<Note> notes = noteService.getNote();
                    if (notes == null || notes.size() == 0){
                        System.out.println("无生词可练习");
                        break;
                    }
                    int max = notes.size();
                    int noteNum = getNoteNum(max);

                    int total = 0;

                    for (int i = 0;i<noteNum;i++){
                        Note note = notes.get(i);
                        String answer = InputUtil.getString("【退出:e或者q】    ※〖" + note.getEn() + "〗的中文是：");
                        boolean isRight = false;
                        String[] rAnswers = note.getCn().split(",");
                        for (String rAnswer : rAnswers) {
                            if (rAnswer.equals(answer)){
                                isRight = true;
                                break;
                            }
                        }
                        if (answer.equals("e") || answer.equals("q")){
                            if(total == 0){
                                System.out.println("大哥，好歹答一题吧");
                            }else {
                                System.out.println("答题结束");
                                noteService.clearNote();
                            }
                            break;

                        }else if(isRight){
                            System.out.println("√ 回答正确, 完整解释是：【" + note.getCn() + "】");
                            note.setRightNum(note.getRightNum()+1);
                            noteService.updateNote(note);
                            noteService.clearNote();
                            total ++;
                        }else if (!isRight){
                            System.out.println("〤 回答错误, 完整解释是：【" + note.getCn() + "】");
                            noteService.clearNote();
                            total ++;
                        }
                    }

                    break;

                //查看生词本
                case 2:

                    noteService.clearNote();
                    List<Note> notes2 = noteService.getNote();
                    int i = 1;
                    if (notes2!=null){
                        for (Note note : notes2) {
                            System.out.println(i +". " + note.getEn() + " : " + note.getCn()+" 答对次数:" + note.getRightNum());
                            i++;
                        }
                    }else {
                        System.out.println("生词本没有记录");
                    }

                    break;

                //返回上一级
                case 0:
                    rtnTop = true;
                    break;

                default:
                    //提示用户选择不正确。
                    System.out.println("你的子菜单选择不正确");
            }
        }
    }

    //获取用户输入的生词数量
    private int getNoteNum(int max){
        int noteNum = InputUtil.getInt("共【"+ max +"】个生词，请输入本轮要复习的数量:");
        if (noteNum < 0 || noteNum > max){
            System.out.println("输入的数量不正确");
            return getNoteNum(max);
        }
        return noteNum;
    }


    //判断答案是否正确
    private boolean judgeAnswer(String answer, String[] rAnswers){
        boolean isRight = false;
        for (String rAnswer : rAnswers) {
            if (rAnswer.equals(answer)){
                isRight = true;
                break;
            }
        }
        return isRight;
    }
}
