package cn.ktl.lab.springmvc.command;

/**
 * @Author lin ho
 * Des: TODO
 */

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

//@Component
@Slf4j
public class EnvironmentVariablePrinter implements CommandLineRunner {

    @Override
    public void run(String... args) {
        log.info("System Environment Variables:============================");
        System.getenv().forEach((key, value) ->
                log.info("{} = {}", key, value)
        );

        log.info("System Environment Variables:============================");
        System.getProperties().forEach((key, value) ->
                log.info("{} = {}", key, value)
        );
    }
}
