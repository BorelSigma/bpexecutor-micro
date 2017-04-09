package bpmnsim;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.kie.api.io.ResourceType;
import org.kie.api.runtime.Globals;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieRuntime;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.manager.RuntimeEnvironmentBuilder;
import org.kie.api.runtime.manager.RuntimeManager;
import org.kie.api.runtime.manager.RuntimeManagerFactory;
import org.kie.api.runtime.process.ProcessInstance;
import org.kie.internal.KnowledgeBase;
import org.kie.internal.KnowledgeBaseFactory;
import org.kie.internal.builder.KnowledgeBuilder;
import org.kie.internal.builder.KnowledgeBuilderFactory;
import org.kie.internal.io.ResourceFactory;
import org.kie.internal.runtime.manager.context.EmptyContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.HashMap;
import java.util.Map;


@Service
public class ProcessService {

    private static Logger log = LogManager.getLogger();

    private RuntimeManager runtimeManager;

    @Autowired
    private KieContainer kieContainer;

    @Autowired
    private ProcessRepository repository;

    public void runProcess(String processName, ProcessScheduling scheduling) {

        if(repository.getProcessModel() == null){
            System.out.println("process model not set");
            return;
        }
        this.runtimeManager = createRuntimeManager( repository.getProcessModel());
        KieSession kieSession = runtimeManager.getRuntimeEngine(EmptyContext.get()).getKieSession();
        //kieContainer.getKieBase("bpmnProcessKbase").getProcesses().stream().forEach(p -> System.out.println(p.getId()));

        try{
            long startTime = System.currentTimeMillis();
            ProcessInstance instance = kieSession.startProcess(processName, scheduling.getVariables());
            long endTime = System.currentTimeMillis();
            long duration = endTime - startTime;
            log.info(instance.getId()+","+duration);
        }catch (Exception e){
            e.printStackTrace();
        }
        finally {
            kieSession.dispose();
            runtimeManager.close();
        }
    }

    /**
     * kcontext.setVariable("output", new String("./results/"));

     kcontext.setVariable("music_data_type", new String("audio/midi"));

     kcontext.setVariable("input", new String("./music_files/"));

     kcontext.setVariable("output", new String("./results/"));

     kcontext.setVariable("tmp_dir", new String("./tmp/"));

     kcontext.setVariable("normalize_webservice_url", schedule.get("normalize_webservice_url"));
     kcontext.setVariable("limiter_webservice_url", schedule.get("limiter_webservice_url"));
     kcontext.setVariable("sample_rate_webservice_url", schedule.get("sample_rate_webservice_url"));
     kcontext.setVariable("channels_webservice_url", schedule.get("sample_rate_webservice_url"));
     kcontext.setVariable("fading_webservice_url", schedule.get("sample_rate_webservice_url"));
     kcontext.setVariable("sample_size_webservice_url", new String("http://localhost:8080/AlterMusicWS/services/AlterMusic"));

     kcontext.setVariable("limiter_webservice_url", new String("http://localhost:8080/AlterMusicWS/services/AlterMusic"));

     kcontext.setVariable("sample_rate_webservice_url", new String("http://localhost:8080/AlterMusicWS/services/AlterMusic"));

     kcontext.setVariable("channels_webservice_url", new String("http://localhost:8080/AlterMusicWS/services/AlterMusic"));

     kcontext.setVariable("fading_webservice_url", new String("http://localhost:8080/AlterMusicWS/services/AlterMusic"));

     kcontext.setVariable("sample_size_webservice_url", new String("http://localhost:8080/AlterMusicWS/services/AlterMusic"));


     */

    private  RuntimeManager createRuntimeManager(String resource)
    {
        KnowledgeBuilder kbuilder = KnowledgeBuilderFactory.newKnowledgeBuilder();
        kbuilder.add(ResourceFactory.newByteArrayResource(resource.getBytes()), ResourceType.BPMN2);
        KnowledgeBase kbase = KnowledgeBaseFactory.newKnowledgeBase();
        kbase.addKnowledgePackages(kbuilder.getKnowledgePackages());

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("org.jbpm.persistence.jpa");
        RuntimeEnvironmentBuilder builder = RuntimeEnvironmentBuilder.Factory.get()
                .newDefaultBuilder().entityManagerFactory(emf)
                .knowledgeBase(kbase).addAsset(ResourceFactory.newByteArrayResource(resource.getBytes()), ResourceType.BPMN2);

        return RuntimeManagerFactory.Factory.get()
                .newPerProcessInstanceRuntimeManager(builder.get(), "com.sample:example:1.0");

    }


    public void saveProcess(String processModel) {
        repository.saveProcess(processModel);
    }
}
