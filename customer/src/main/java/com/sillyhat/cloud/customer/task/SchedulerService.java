package com.sillyhat.cloud.customer.task;

import com.sillyhat.cloud.customer.model.Customer;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

@Slf4j
@Service
@AllArgsConstructor
public class SchedulerService {

    private static SimpleDateFormat sdfTime = new SimpleDateFormat( "yyyyMMddHHmmss");

    @Scheduled(cron="0/2 * * * * ? ")
    public void scheduleTestInfoLog(){
        log.info("schedule test info log. time : {}",sdfTime.format(new Date()));
    }

    @Scheduled(cron="0/8 * * * * ? ")
    public void scheduleTestDTOInfoLog(){
        Random random = new Random();
        Long id = random.nextLong();
        log.info("schedule test dto info log. time : {} ; dto : {}",sdfTime.format(new Date()),Customer.builder().id(id).email("test@gmail.com").build());
    }

    @Scheduled(cron="0 0/3 * * * ? ")
    public void scheduleTestErrorLog(){
        try {
            Long.parseLong("xxxxx");
        } catch (Exception e){
            log.info("schedule test error log. time : {}",sdfTime.format(new Date()));
            log.error("format number error",e);
        }

    }
}
