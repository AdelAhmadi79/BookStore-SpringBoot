package com.example.book_store.log;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;


@Getter
@Setter
@Log4j2
@Component
public class LogsManager {
    public static void info(String message) {
        log.info(message);
    }
    public static void debug(String message){
        log.debug(message);
    }


}