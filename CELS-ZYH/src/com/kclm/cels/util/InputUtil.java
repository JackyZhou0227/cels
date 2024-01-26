package com.kclm.cels.util;

import java.util.Scanner;

/*******************************
 * toolkit class
 */
public class InputUtil {

    private static Scanner scanner = new Scanner(System.in);

    /**********************
     * 获取用户输入的整数，如果输入的类型不是int，则重新输入
     * @param prompt 提示信息
     * @return 用户输入的整形
     */
    public static int getInt(String prompt) {
        System.out.print(prompt);
        //
        try {
            String s = scanner.nextLine();
            int result = Integer.parseInt(s);
            return result;
        } catch (Exception e) {
            System.out.println("输入的不是整数");
            //read buffer data
            return getInt(prompt);
        }
    }


    /**********************
     * 获取用户输入的一个字母，统一转换为大写
     * @param prompt 提示信息
     * @return 用户输入的字母（大写形式）
     */
    public static char getLetter(String prompt) {
        System.out.print(prompt);

        try {
            String input = scanner.nextLine(); // 读取一行输入
            if (input.length() != 1 || Character.isDigit(input.charAt(0))) {
                System.out.println("请输入一个字母");
                return getLetter(prompt);
            }
            return Character.toUpperCase(input.charAt(0)); // 转换为大写并返回
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return getLetter(prompt); // 重新获取输入
        }
    }

    /**********************
     * 获取用户输入的字符串，如果是空字符串或直接换行，则重新获取输入
     * @param prompt 提示信息
     * @return 用户输入的字符串
     */
    public static String getString(String prompt) {
        System.out.print(prompt);
        String input = scanner.nextLine();
        if (input == null || input.equals("")){
            System.out.println("输入的是空白字符");
            return getString(prompt);
        }
        return input; // 读取一行输入

    }
    public static void release() {
        if(scanner != null) {
            scanner.close();
        }
    }
}
