package com.han.community;

import org.junit.Test;

import java.io.*;


public class MainTest {
    @Test
    public static void main(String[] args) {
        File f1 = new File("D:/12345/aa/vueblog/community/src/test/java/com/han/community/a1.txt");
        File f2 = new File("D:/12345/aa/vueblog/community/src/test/java/com/han/community/a2.txt");
        if (f1.exists()&&f2.exists()) {
            FileInputStream input1 = null,input2 = null;
            try {
                input1 = new FileInputStream(f1);
                input2 = new FileInputStream(f2);

                byte[] bytes1 = new byte[11111111];
                byte[] bytes2 = new byte[11111111];

                int len1 = input1.read(bytes1);
                int len2 = input2.read(bytes2);

                System.out.println(new String(bytes1, 0, len1));

                System.out.println(new String(bytes2, 0, len2));
                input1.close();
                input2.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("此文件不存在！");
        }
    }
}
