package cn.plantlink.b.controller;

import cn.plantlink.b.service.remote.RemoteMicroservice;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/b")
public class BController {

    private static final Logger logger = LoggerFactory.getLogger(BController.class);

    @Autowired
    private RemoteMicroservice remoteMicroservice;

    @RequestMapping({"", "/"})
    public String index() {
        String hello = "hello world b";
        logger.info(hello);
        return hello;
    }

    @RequestMapping("/rest")
    public String rest() {
        String dClientRest = remoteMicroservice.dClientRest();
        logger.info(dClientRest);
        return String.format("[b = %s, d = %s]", "b-rest", dClientRest);
    }
}
