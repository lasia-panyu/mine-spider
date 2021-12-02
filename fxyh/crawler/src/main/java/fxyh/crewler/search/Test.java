package fxyh.crewler.search;

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;


@MapperScan("fxyh.crewler.mapper")
@ComponentScan("fxyh.crewler")
@SpringBootApplication()
@EnableScheduling
public class Test {

    static Logger logger = LoggerFactory.getLogger(Test.class);


    public static void main(String[] args) throws Exception {
        SpringApplication.run(Test.class, args);
    }

}
