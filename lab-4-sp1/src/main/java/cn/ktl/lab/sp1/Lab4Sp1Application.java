package cn.ktl.lab.sp1;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Lab4Sp1Application implements CommandLineRunner {
    @Value("${ms.authorization-uri}")
    private String url;
    public static void main(String[] args) {
        SpringApplication.run(Lab4Sp1Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("url: " + url);
    }
}
