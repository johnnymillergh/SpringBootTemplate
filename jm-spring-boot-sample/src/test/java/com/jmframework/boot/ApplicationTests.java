package com.jmframework.boot;

import com.jmframework.boot.jmspringbootstarter.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Description: ApplicationTests, change description here.
 *
 * @author Johnny Miller (鍾俊), email: johnnysviva@outlook.com
 * @date 2019-05-02 01:11
 **/
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationTests {
    @Autowired
    UserMapper userMapper;

    public void test() {
        log.info("Hi from test");
    }
}
