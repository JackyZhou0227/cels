package com.kclm.cels.view.impl;

import com.kclm.cels.view.IView;

public class ViewImpl implements IView {

    //欢迎页面
    @Override
    public void welcome() {
        System.out.println("--------------------------------- ---------------------------------");
        System.out.println("                           欢迎使用【CELS】                          ");
        System.out.println("                  Computer English Learning Software               ");
        System.out.println("                       计算机英语单词和词汇学习软件                      ");
        System.out.println("                              作者:ZYH                              ");
        System.out.println("                        邮箱：1143075643@qq.com                     ");
        System.out.println("-------------------------------------------------------------------");
    }

    //主菜单
    @Override
    public void mainMenu() {
        System.out.println("-------------------------------------------------------------------");
        System.out.println("  主菜单  ");
        System.out.println("# 1.\t浏览基库【单词与词汇】");
        System.out.println("# 2.\t做游戏学习单词【随机性】");
        System.out.println("# 3.\t测试自己的水平【随机性】");
        System.out.println("# 4.\t生词本【随机性】");
        System.out.println("# 0.\t退出");
        System.out.println("-------------------------------------------------------------------");
    }


    //浏览基库子菜单
    @Override
    public void showBrowserMenu() {
        System.out.println("-------------------------------------------------------------------");
        System.out.println(" ->1.浏览单词");
        System.out.println(" ->2.浏览词汇");
        System.out.println(" ->0.返回上一级");
        System.out.println("-------------------------------------------------------------------");
    }

    //游戏子菜单
    @Override
    public void showGameSubMenu() {
        System.out.println("-------------------------------------------------------------------");
        System.out.println(" ->1.提示英文，回答中文");
        System.out.println(" ->2.提示中文，回答英文");
        System.out.println(" ->0.返回上一级");
        System.out.println("-------------------------------------------------------------------");
    }

    //测试子菜单
    @Override
    public void showTestingSubMenu() {
        System.out.println("-------------------------------------------------------------------");
        System.out.println(" ->1.开始测试");
        System.out.println(" ->2.我的测试记录");
        System.out.println(" ->0.返回上一级");
        System.out.println("-------------------------------------------------------------------");
    }

    @Override
    public void showNoteMenu() {
        System.out.println("-------------------------------------------------------------------");
        System.out.println(" ->1.练习生词");
        System.out.println(" ->2.查看生词本");
        System.out.println(" ->0.返回上一级");
        System.out.println("-------------------------------------------------------------------");
    }


}
