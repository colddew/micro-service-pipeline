package cn.plantlink.a.service;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
//@SpringBootTest("service.message=Hello")
@SpringBootTest
public class AServiceTest {

    @Autowired
    private AService aService;

    @Test
    public void contextLoads() {
        Assertions.assertThat(aService.message()).isNotNull();
        Assertions.assertThat(aService.message()).isEqualTo("Hello World Test");
    }

    @SpringBootApplication
    static class TestConfiguration {
    }
}