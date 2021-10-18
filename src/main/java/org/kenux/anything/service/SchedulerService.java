package org.kenux.anything.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalTime;

@Service
@Slf4j
public class SchedulerService {

//    @Scheduled(initialDelay = 10000, fixedDelay = 10000)
//    public void runAfterTenSecondsRepeatTenSeconds() {
//        log.info("10초 후 실행 => time : " + LocalTime.now());
//    }

//    @Scheduled(fixedRate = 1000)
    public void runEveryTenSecondsOne() {
        log.info("runEveryTenSecondsOne time : " + LocalTime.now());
        log.info("runEveryTenSecondsOne thread: " + Thread.currentThread().getName());
    }
//    @Scheduled(fixedRate = 1000)
    public void runEveryTenSecondsTwo() {
        log.info("runEveryTenSecondsTwo time : " + LocalTime.now());
        log.info("runEveryTenSecondsTwo thread: " + Thread.currentThread().getName());
    }
}