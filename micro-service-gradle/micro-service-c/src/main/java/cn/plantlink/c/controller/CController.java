package cn.plantlink.c.controller;

import cn.plantlink.c.remote.RemoteMicroservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/c")
public class CController {

    @Autowired
    private RemoteMicroservice remoteMicroservice;

    @RequestMapping({"", "/"})
    public String index() {
        return "hello world c";
    }

    @RequestMapping("/rest")
    public String rest() throws Exception {
        return String.format("[c = %s, d = %s]", "c-rest", remoteMicroservice.dClientRest());
    }
}
