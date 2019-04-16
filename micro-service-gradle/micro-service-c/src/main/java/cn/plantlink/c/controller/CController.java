package cn.plantlink.c.controller;

import cn.plantlink.c.remote.RemoteMicroservice;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/c")
public class CController {

    private static final Logger logger = LoggerFactory.getLogger(CController.class);

    @Autowired
    private RemoteMicroservice remoteMicroservice;

    @RequestMapping({"", "/"})
    public String index() {
        String hello = "hello world c";
        logger.info(hello);
        return hello;
    }

    @RequestMapping("/rest")
    public String rest() throws Exception {
        String dClientRest = remoteMicroservice.dClientRest();
        logger.info(dClientRest);
        return String.format("[c = %s, d = %s]", "c-rest", dClientRest);
    }
}
