package cn.itcast.dao;

import java.util.ArrayList;

/**
 * @ClassName:B
 * @Despriction: //TODO
 * @Author:zhaoxianfu
 * @Date:Created 2018/11/22  11:49
 * @Version1.0
 **/
public class B {
    public static void main(String[] args) {
        Integer a=1;
        Integer b=200;
        Integer c=3;
        ArrayList<Integer> list = new ArrayList<>();
        list.add(a);
        list.add(b);
        list.add(c);

        list.forEach(s->{
            if (s.equals(200)){
                s=s+11;
            }
        });
        System.out.println(list);
    }
}
