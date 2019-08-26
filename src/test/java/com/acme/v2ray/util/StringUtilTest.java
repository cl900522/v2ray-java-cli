package com.acme.v2ray.util;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author: cdchenmingxuan
 * @date: 2019/8/26 15:52
 * @description: v2rayjavacli
 */
public class StringUtilTest {
    @Test
    public void test1() {
        boolean number = StringUtil.isNumberInt("1111");
        Assert.assertTrue(number);


        number = StringUtil.isNumberInt("1111aa");
        Assert.assertFalse(number);

        number = StringUtil.isNumberInt("aaa1111");
        Assert.assertFalse(number);


        number = StringUtil.isNumberInt("11aa11");
        Assert.assertFalse(number);
    }
}
