package ru.yksvelobos.appliancecontrollerserver.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import ru.yksvelobos.appliancecontrollerserver.entity.Action;
import ru.yksvelobos.appliancecontrollerserver.service.ActionService;

import java.util.List;

/**
 * @author Ilia Sobolevsky on 24.03.2020
 */
@RestController
@RequestMapping(value = {"/action"})
public class ActionController {

    private final ActionService actionService;

    @Autowired
    public ActionController(ActionService actionService) {
        this.actionService = actionService;
    }

    @RequestMapping(value = "getActions", method = RequestMethod.GET)
    @ResponseBody
    List<Action> getActions() {
        return actionService.findAllActions();
    }

    @RequestMapping(value = "/upsertAction", method = RequestMethod.POST)
    @ResponseBody
    Action upsertAction(@RequestBody Action action) {
        return actionService.save(action);
    }
}
