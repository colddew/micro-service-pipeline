package cn.plantlink.b.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/b")
public class BController {

    @RequestMapping({"", "/"})
    public String index() {
        return "hello world b";
    }
}
