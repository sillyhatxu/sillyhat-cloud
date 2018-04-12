package com.sillyhat.cloud.customer.task;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

@Slf4j
@Service
@AllArgsConstructor
public class SchedulerService {

    private static SimpleDateFormat sdfTime = new SimpleDateFormat( "yyyyMMddHHmmss");

    @Scheduled(cron="0/2 * * * * ? ")
    public void scheduleTestLog(){
        log.info("schedule test log. time : {}",sdfTime.format(new Date()));
    }
}
