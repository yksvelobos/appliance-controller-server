package ru.yksvelobos.appliancecontrollerserver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import ru.yksvelobos.appliancecontrollerserver.entity.Action;
import ru.yksvelobos.appliancecontrollerserver.repository.ActionRepository;
import ru.yksvelobos.appliancecontrollerserver.repository.WorkProgramRepository;

/**
 * @author Ilia Sobolevsky on 25.03.2020
 */
@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
public class SaveCurrentActionStrategy implements Strategy<Action> {
    private final WorkProgramRepository workProgramRepository;
    private final ActionRepository actionRepository;

    @Autowired
    public SaveCurrentActionStrategy(WorkProgramRepository workProgramRepository, ActionRepository actionRepository) {
        this.workProgramRepository = workProgramRepository;
        this.actionRepository = actionRepository;
    }

    @Override
    public void execute(Action actionEntity) {
       System.out.println("action: " + actionEntity.getName());
    }
}
