package cn.plantlink.a.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/a")
public class AController {

    @RequestMapping({"", "/"})
    public String index() {
        return "hello world a";
    }
}
