package ru.yksvelobos.appliancecontrollerserver.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import ru.yksvelobos.appliancecontrollerserver.entity.WorkProgram;
import ru.yksvelobos.appliancecontrollerserver.service.WorkProgramService;

import java.util.List;

/**
 * @author Ilia Sobolevsky on 26.03.2020
 */
@RestController
@RequestMapping(value = {"/workprogram"})
public class WorkProgramController {
    private final WorkProgramService workProgramService;

    @Autowired
    public WorkProgramController(WorkProgramService workProgramService) {
        this.workProgramService = workProgramService;
    }

    @RequestMapping(value = "/add-action-to-program", method = RequestMethod.GET)
    @ResponseBody
    WorkProgram addActionToProgram(@RequestParam(value = "programId") Long programId,
                                   @RequestParam(value = "actionId") Long actionId) {
        return workProgramService.addActionToProgram(programId, actionId);
    }

    @RequestMapping(value = "/create-work-program", method = RequestMethod.POST)
    @ResponseBody
    WorkProgram createWorkProgram(@RequestParam(value = "name", required = false) String name) {
        return workProgramService.createWorkProgram(name);
    }

    @RequestMapping(value = "get-work-wrograms", method = RequestMethod.GET)
    @ResponseBody
    List<WorkProgram> getWorkPrograms() {
        return workProgramService.findAll();
    }

}
