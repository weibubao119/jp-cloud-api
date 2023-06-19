package com.jp.user;

import com.jp.user.manager.WxMessagesManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author : xh
 * @date : Created in 2022/7/26 11:19
 */
@SpringBootTest
public class WxMessagesTest {

    @Autowired
    private WxMessagesManager wxMessagesManager;

    @Test
    public void templateOrder(){
        wxMessagesManager.sendOrderMsg("ovnRI68AF0Iz1QWQF78d5pmfbsiA","O34456090989787232","迷迭香的小店");
    }
}
