package cn.plantlink.a.service;

import cn.plantlink.a.config.MicroserviceAProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AService {

    @Autowired
    private MicroserviceAProperties aProperties;

    public String getMessage() {
        return aProperties.getMessage();
    }
}