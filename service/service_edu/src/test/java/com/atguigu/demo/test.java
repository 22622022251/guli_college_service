package com.atguigu.demo;


import java.util.ArrayList;
import java.util.List;

public class test {
    public static void main(String[] args) {
        List<Integer> aList = new ArrayList();
        List<String> bList = new ArrayList();
        System.out.println(aList.getClass());
        System.out.println(aList.getClass() == bList.getClass());
    }
}
