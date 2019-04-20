package cn.plantlink.a.controller;

import cn.plantlink.a.service.AService;
import cn.plantlink.a.service.remote.RemoteMicroservice;
import cn.plantlink.a.service.remote.RetrofitRemoteMicroservice;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/a")
public class AController {

    private static final Logger logger = LoggerFactory.getLogger(AController.class);

    @Autowired
    private AService aService;

    @Autowired
    private RemoteMicroservice remoteMicroservice;

    @Autowired
    private RetrofitRemoteMicroservice retrofitRemoteMicroservice;

    @RequestMapping({"", "/"})
    public String index() {
        String hello = "hello world a";
        logger.info(hello);
        return hello;
    }

    @RequestMapping("/message")
    public String getMessage() {
        String message = aService.getMessage();
        logger.info(message);
        return message;

    }

    @RequestMapping("/rest")
    public String rest() throws Exception {
        String bClientRest = remoteMicroservice.bClientRest();
        String cClientRest = remoteMicroservice.cClientRest();
        String dClientRest = retrofitRemoteMicroservice.dClientRest();
        String format = String.format("[a = %s, b = %s , c = %s, d = %s]", "a-rest", bClientRest, cClientRest, dClientRest);
        logger.info(format);
        return format;
    }
}
