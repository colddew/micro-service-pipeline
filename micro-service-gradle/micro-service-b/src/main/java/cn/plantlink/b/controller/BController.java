package cn.plantlink.b.controller;

import cn.plantlink.b.service.remote.RemoteMicroservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/b")
public class BController {

    @Autowired
    private RemoteMicroservice remoteMicroservice;

    @RequestMapping({"", "/"})
    public String index() {
        return "hello world b";
    }

    @RequestMapping("/rest")
    public String rest() throws Exception {
        return String.format("[b = %s, d = %s]", "b-rest", remoteMicroservice.dClientRest());
    }
}
