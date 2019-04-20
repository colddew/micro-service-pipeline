package cn.plantlink.c.service.remote;

import cn.plantlink.c.config.RemoteServiceProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class RemoteMicroservice {

    @Autowired
    private RemoteServiceProperties remoteServiceProperties;

    @Autowired
    private RestTemplate restTemplate;

    public String dClientRest() {
        return restTemplate.getForObject(remoteServiceProperties.getD() + "/api/v1/d/rest", String.class);
    }
}
