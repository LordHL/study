package cn.ktl.lab.springmvc;

import com.azure.security.keyvault.secrets.SecretClient;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"cn.ktl.lab.springmvc"})
@MapperScan(basePackages = {"cn.ktl.lab.springmvc.mapper"})
public class Application implements CommandLineRunner {
    @Value("${spring.datasource.url}")
    private String dburl;

    @Value("${azure.redis.host}")
    private String redisUrl;



//    @Value("${onformadevmongocosmos-connectionstring}")
//    private String cs;

//    @Value("${ms.authorization-uri}")
//    private String url;
//    private final SecretClient secretClient;
//
//    public Application(SecretClient secretClient) {
//        this.secretClient = secretClient;
//    }
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("dburl : " + dburl);
        System.out.println("redisUrl : " + redisUrl);
//        System.out.println("redis pwd : " + pwd);
//        System.out.println("cs : " + cs);
//        System.out.println("url : " + url);
    }
}
