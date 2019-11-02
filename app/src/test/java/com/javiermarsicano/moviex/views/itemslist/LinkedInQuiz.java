package com.javiermarsicano.moviex.views.itemslist;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
* This must be executed with the following command:
* java com.javiermarsicano.moviex.common.mvp.LinkedInQuiz 1 2 "3"
* */
public class LinkedInQuiz {
    public static void main(String[] args) {
        Pattern p = Pattern.compile("([ab]?)");
        Matcher m = p.matcher("cccc");
        System.out.println(m.matches());
        System.out.println(m.lookingAt());


        List list = new ArrayList();
        list.add(2);
        list.add("Hello World");
        System.out.println(list.get(0) instanceof Integer); //true
        System.out.println(list.get(1) instanceof Object); //true

        String text = (String) list.get(1);
        System.out.println(text.substring(1,3)); //prints "el"

        System.out.println(args[2]); //prints 3
    }

    static Exception print() throws Exception {
        throw new Exception();
    }
}
