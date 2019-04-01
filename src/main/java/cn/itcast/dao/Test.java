package cn.itcast.dao;

import java.util.ArrayList;

/**
 * @ClassName:Test
 * @Despriction: //TODO
 * @Author:zhaoxianfu
 * @Date:Created 2018/11/22  11:26
 * @Version1.0
 **/
public class Test {
    public static void main(String[] args) {
        Student student1 = new Student();
        Student student2 = new Student();
        ArrayList<Student> list = new ArrayList<>();
        list.add(student1);
        list.add(student2);

        list.forEach(student -> student.setAge(22));
        System.out.println(list);
    }
}
