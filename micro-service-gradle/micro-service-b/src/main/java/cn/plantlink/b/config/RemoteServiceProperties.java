package cn.plantlink.b.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "remote.base.url")
public class RemoteServiceProperties {

    private String d;

    public String getD() {
        return d;
    }

    public void setD(String d) {
        this.d = d;
    }
}
