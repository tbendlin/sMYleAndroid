package com.example.theodorabendlin.mysmileandroid.utils;

import java.util.List;

public class ListUtil {

    public static int getCount(List list) {
        if (list != null) {
            return list.size();
        }

        return 0;
    }

    public static boolean isEmpty(List list) {
        return getCount(list) == 0;
    }
}
