package com.prototype.classyBackEnd.log;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;

@Slf4j
public class LogTest {

    @Test
    public void test() {
        log.trace("trace test");
        log.debug("debug test");
        log.info("info test");
        log.warn("warn test");
        log.error("error test");
    }
}
