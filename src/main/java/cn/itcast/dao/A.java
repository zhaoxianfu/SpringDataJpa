package cn.itcast.dao;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName:A
 * @Despriction: //TODO
 * @Author:zhaoxianfu
 * @Date:Created 2018/11/22  11:04
 * @Version1.0
 **/
public class A {
    public static void main(String[] args) {

        List<String> strList = new ArrayList<>();
        strList.add("shaochen");
        strList.add("shaoChen");
        strList.add("cool");
        strList.add("bean");
        strList.add("java");

        for (String s : strList) {
            if (s.equals("java")){
                int i = strList.indexOf(s);
                strList.set(i,s+"111");
            }
        }
        System.out.println(strList);
    }
}
