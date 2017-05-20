package com.seal_de.common;

import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sealde on 5/18/17.
 */
public class StringUtilTest {
    @Test
    public void listToString() {
        List<String> list = new ArrayList<String>();
        list.add("这是第一个");
        list.add("this is two");
        list.add("localhost:8080/question/listToString");
        String s = StringUtils.join(list, ",");
        Assert.assertEquals("这是第一个,this is two,localhost:8080/question/listToString", s);
    }

    @Test
    public void splitString() {
        String s = "这是第一个,this is two,localhost:8080/question/listToString";
        String[] strings = StringUtils.split(s, ",");
        Assert.assertEquals(strings.length, 3);
        Assert.assertEquals(strings[0], "这是第一个");
    }
}
