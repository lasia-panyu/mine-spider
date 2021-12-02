package fxyh.wechat.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/")
@RestController

public class TestController {
    static Logger logger = LoggerFactory.getLogger(TestController.class);

    @RequestMapping(value = "/queryGamesDetail", method = RequestMethod.GET)
    public String queryGamesDetail() {
        logger.debug("test");
        logger.info("test");
        logger.error("test");
        return "1111";

    }
}
