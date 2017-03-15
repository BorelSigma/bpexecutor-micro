package bpmnsim;

import org.springframework.transaction.jta.JtaTransactionManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class PersistenceConfig {

    @Bean
    public JtaTransactionManager manager(){
        JtaTransactionManager manager = new JtaTransactionManager();
        return manager;
    }
}
