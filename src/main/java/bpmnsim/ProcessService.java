package bpmnsim;

import org.kie.api.KieBase;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.manager.RuntimeEnvironmentBuilder;
import org.kie.api.runtime.manager.RuntimeManager;
import org.kie.api.runtime.manager.RuntimeManagerFactory;
import org.kie.api.runtime.process.ProcessInstance;
import org.kie.internal.runtime.manager.context.EmptyContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

@Service
public class ProcessService {

    private static Logger log = LoggerFactory.getLogger(ProcessService.class);

    private final KieContainer kieContainer;

    private RuntimeManager runtimeManager;

    @Autowired
    public ProcessService(KieContainer kieContainer) {
        log.info("Initialising a process session.");
        this.kieContainer = kieContainer;
        this.runtimeManager = createRuntimeManager(kieContainer.getKieBase("bpmnProcessKbase"));
    }



    public void runProcess(String processName) {

        KieSession kieSession = runtimeManager.getRuntimeEngine(EmptyContext.get()).getKieSession();
        try{
            long startTime = System.currentTimeMillis();
            ProcessInstance instance = kieSession.startProcess(processName);
            long endTime = System.currentTimeMillis();
            long duration = endTime - startTime;
            System.out.println(" Instance with ID: " + instance.getId() + " took " + duration + " ms to complete");
        }catch (Exception e){

            e.printStackTrace();
        }
        finally {
            kieSession.dispose();
        }
    }

    private  RuntimeManager createRuntimeManager(KieBase kbase)
    {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("org.jbpm.persistence.jpa");
        RuntimeEnvironmentBuilder builder = RuntimeEnvironmentBuilder.Factory.get()
                .newDefaultBuilder().entityManagerFactory(emf)
                .knowledgeBase(kbase);
        return RuntimeManagerFactory.Factory.get()
                .newPerProcessInstanceRuntimeManager(builder.get(), "com.sample:example:1.0");

    }


}
