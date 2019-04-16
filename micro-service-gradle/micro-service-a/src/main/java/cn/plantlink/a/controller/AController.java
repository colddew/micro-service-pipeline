package cn.plantlink.a.controller;

import cn.plantlink.a.service.AService;
import cn.plantlink.a.service.remote.RemoteMicroservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/a")
public class AController {

    @Autowired
    private AService aService;

    @Autowired
    private RemoteMicroservice remoteMicroservice;

    @RequestMapping({"", "/"})
    public String index() {
        return "hello world a";
    }

    @RequestMapping("/message")
    public String getMessage() {
        return aService.getMessage();
    }

    @RequestMapping("/rest")
    public String rest() throws Exception {
        return String.format("[a = %s, b = %s , c = %s", "a-rest]", remoteMicroservice.bClientRest(), remoteMicroservice.cClientRest());
    }
}
