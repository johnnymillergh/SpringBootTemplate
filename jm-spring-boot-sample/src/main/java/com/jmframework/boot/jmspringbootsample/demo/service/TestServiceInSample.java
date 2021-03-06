package com.jmframework.boot.jmspringbootsample.demo.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jmframework.boot.jmspringbootstarter.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * Description: TestServiceInSample, change description here.
 *
 * @author Johnny Miller (鍾俊), email: johnnysviva@outlook.com
 * @date 2019-05-01 23:10
 **/
@Slf4j
@Service
@RequiredArgsConstructor
public class TestServiceInSample {
    private final UserMapper userMapper;

    public void sayHi() {
        log.info("Hi from sample");
        log.info(userMapper.selectUserPageList(new Page(1, 2)).getRecords().toString());
    }
}
