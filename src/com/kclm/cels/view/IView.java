package com.kclm.cels.view;

import com.kclm.cels.entity.Word;

import java.util.Set;

public interface IView {

    //欢迎页面
    void welcome();

    //主菜单
    void mainMenu();

    //浏览基库子菜单
    void showBrowserMenu();

    //游戏子菜单
    void showGameSubMenu();

    //测试子菜单
    void showTestingSubMenu();

    void showNoteMenu();
}
