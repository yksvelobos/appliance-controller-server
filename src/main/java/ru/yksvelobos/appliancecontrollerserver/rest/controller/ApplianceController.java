package ru.yksvelobos.appliancecontrollerserver.rest.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import ru.yksvelobos.appliancecontrollerserver.entity.Appliance;
import ru.yksvelobos.appliancecontrollerserver.service.ApplianceService;

import java.util.List;

/**
 * @author Ilia Sobolevsky on 25.03.2020
 */
@RestController
@RequestMapping(value = {"/appliance"})
public class ApplianceController {
    private final ApplianceService applianceService;

    public ApplianceController(ApplianceService applianceService) {
        this.applianceService = applianceService;
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    @ResponseBody
    Appliance registerAppliance(@RequestParam(value = "name", required = false) String name) {
        return applianceService.register(name);
    }

    @RequestMapping(value = "/findById", method = RequestMethod.GET)
    @ResponseBody
    Appliance findById(@RequestParam(value = "applianceId", required = false) Long applianceId) {
        return applianceService.findById(applianceId);
    }

    @RequestMapping(value = "/findAll", method = RequestMethod.POST)
    @ResponseBody
    List<Appliance> findAll() {
        return applianceService.findAll();
    }

    @RequestMapping(value = "/startWorkProgram", method = RequestMethod.PUT)
    @ResponseBody
    void startWorkProgram(@RequestParam(value = "applianceId") Long applianceId) {
        applianceService.startWorkProgram(applianceId);
    }

    @RequestMapping(value = "/stopWorkProgram", method = RequestMethod.PUT)
    @ResponseBody
    void stopWorkProgram(@RequestParam(value = "applianceId") Long applianceId) {
        applianceService.stopWorkProgram(applianceId);
    }

    @RequestMapping(value = "/addProgram", method = RequestMethod.POST)
    @ResponseBody
    Appliance addProgram(@RequestParam(value = "applianceId") Long applianceId,
                         @RequestParam(value = "programId") Long programId) {
        return applianceService.addProgram(applianceId, programId);
    }
}
