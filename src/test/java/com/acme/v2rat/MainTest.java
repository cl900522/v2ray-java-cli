package com.acme.v2rat;

import com.acme.v2ray.Main;
import org.junit.Test;

import java.io.IOException;

/**
 * @author: cdchenmingxuan
 * @date: 2019/7/8 20:02
 * @description: v2rayjavacli
 */
public class MainTest {
    @Test
    public void test1() {
        Main.main(new String[]{"https://v2ray.api.lanan.xyz/client/api.php?token=638f0430-40a6-4a43-80f6-87c4eb143e83&s=v2ray.subscribe&pid=8244"});
    }

}
