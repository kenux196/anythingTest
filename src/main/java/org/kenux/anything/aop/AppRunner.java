package org.kenux.anything.aop;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Slf4j
@Service
public class AppRunner implements ApplicationRunner {

    private final EventService eventService;

    @Override
    public void run(ApplicationArguments args) throws Exception {

        log.info("AppRunner - run");

        eventService.createEvent();
        eventService.publishEvent();
        eventService.deleteEvent();
    }
}