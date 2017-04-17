package bpmnsim;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.util.Arrays;

import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


@SpringBootApplication
public class Application {


    private static Logger log = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {

        ApplicationContext ctx = SpringApplication.run(Application.class, args);

        String[] beanNames = ctx.getBeanDefinitionNames();
        Arrays.sort(beanNames);

        StringBuilder sb = new StringBuilder("Application beans:\n");
        for (String beanName : beanNames) {
            sb.append(beanName + "\n");
        }
        log.info(sb.toString());
    }

    @Bean
    public KieContainer kieContainer() {

        return KieServices.Factory.get().getKieClasspathContainer();
    }

    @Bean
    public EntityManagerFactory getEmf(){
        return Persistence.createEntityManagerFactory("org.jbpm.persistence.jpa");
    }
}