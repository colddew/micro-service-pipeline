package cn.plantlink.d.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/d")
public class DController {

    @RequestMapping({"", "/"})
    public String index() {
        return "hello world d";
    }

    @RequestMapping("/rest")
    public String rest() {
        return "d-rest";
    }
}
