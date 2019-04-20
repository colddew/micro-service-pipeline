package cn.plantlink.a.service.remote;

import cn.plantlink.a.config.RemoteServiceProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class RemoteMicroservice {

    @Autowired
    private RemoteServiceProperties remoteServiceProperties;

    @Autowired
    private RestTemplate restTemplate;

    public String bClientRest() {
        return restTemplate.getForObject(remoteServiceProperties.getB() + "/api/v1/b/rest", String.class);
    }

    public String cClientRest() {
        return restTemplate.getForObject(remoteServiceProperties.getC() + "/api/v1/c/rest", String.class);
    }
}