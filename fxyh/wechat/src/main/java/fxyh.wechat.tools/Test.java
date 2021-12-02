package fxyh.wechat.tools;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan("fxyh.wechat")
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})

public class Test {
    static Logger logger = LoggerFactory.getLogger(Test.class);

    public static void main(String[] args) {
        SpringApplication.run(Test.class, args);
        System.out.println("test");
        logger.debug("test");
        logger.info("test");
        System.out.println("test");
    }
}
