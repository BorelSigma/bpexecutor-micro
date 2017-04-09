package bpmnsim;

import org.springframework.stereotype.Repository;

@Repository
public class ProcessRepository {


    private String processModel;


    public void saveProcess(String processModel){
        this.processModel = processModel;
    }

    public String getProcessModel(){
        return this.processModel;
    }

}
