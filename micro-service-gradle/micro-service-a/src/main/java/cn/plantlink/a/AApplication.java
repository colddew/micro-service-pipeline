package cn.plantlink.a;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;

@SpringBootApplication
//@EnableConfigurationProperties({MicroserviceALocalServiceProperties.class, MicroserviceARemoteServiceProperties.class})
public class AApplication {

    private static final Logger logger = LoggerFactory.getLogger(AApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(AApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
        return args -> {

            logger.info("inspect the beans provided by Spring Boot:");

            String[] beanNames = ctx.getBeanDefinitionNames();
            Arrays.sort(beanNames);

            for (String beanName : beanNames) {
                logger.info(beanName);
            }
        };
    }
}
