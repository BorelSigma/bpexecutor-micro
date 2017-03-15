package bpmnsim;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class ProcessController {

    private static Logger log = LoggerFactory.getLogger(ProcessController.class);

    private final ProcessService processService;

    @Autowired
    public ProcessController(ProcessService busPassService) {
        this.processService  = busPassService;
    }

    @Transactional
    @RequestMapping(value = "/process", method = RequestMethod.POST, produces = "application/json")
    public void getQuestions(
            @RequestParam(required = true) String processName) {


        log.debug("process "  + processName + " requested");

        processService.runProcess(processName);

    }

}
