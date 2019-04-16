package cn.plantlink.d.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/d")
public class DController {

    private static final Logger logger = LoggerFactory.getLogger(DController.class);

    @RequestMapping({"", "/"})
    public String index() {
        String hello = "hello world d";
        logger.info(hello);
        return hello;
    }

    @RequestMapping("/rest")
    public String rest() {
        String dRest = "dRest-rest";
        logger.info(dRest);
        return dRest;
    }
}
