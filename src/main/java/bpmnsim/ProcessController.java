package bpmnsim;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;



@RestController
public class ProcessController {

    private static Logger log = LoggerFactory.getLogger(ProcessController.class);

    private final ProcessService processService;

    @Autowired
    public ProcessController(ProcessService processService) {
        this.processService  = processService;
    }

    @Transactional
    @RequestMapping(value = "/process", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
    public void execProcess(
            @RequestBody(required = true) ProcessScheduling processScheduling,
            @RequestParam(required = true) String processName) {
        processService.runProcess(processName, processScheduling);

    }

    @RequestMapping(value = "/setprocess", method = RequestMethod.POST, produces = "application/json", consumes = "application/xml")
    public void setProcess(
            @RequestBody(required = true) String processModel) {
        processService.saveProcess(processModel);

    }





}
