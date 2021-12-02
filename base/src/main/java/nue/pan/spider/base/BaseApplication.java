package nue.pan.spider.base;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@ComponentScan("nue.pan.spider")
@EnableJpaRepositories(basePackages = { "nue.pan.spider.entity" })
@EntityScan("nue.pan.spider.entity")
@SpringBootApplication
@EnableAsync
public class BaseApplication {

    public static void main(String[] args) {

        SpringApplication.run(BaseApplication.class, args);
    }

}
