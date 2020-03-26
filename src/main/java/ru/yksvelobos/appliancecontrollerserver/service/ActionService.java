package ru.yksvelobos.appliancecontrollerserver.service;

import com.google.common.collect.Streams;
import org.springframework.stereotype.Component;
import ru.yksvelobos.appliancecontrollerserver.entity.Action;
import ru.yksvelobos.appliancecontrollerserver.entityDb.ActionEntity;
import ru.yksvelobos.appliancecontrollerserver.repository.ActionRepository;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.Objects.requireNonNull;

/**
 * Service to work with actions
 *
 * @author Ilia Sobolevsky
 */
@Component
public class ActionService {
    private final ActionRepository actionRepository;

    public ActionService(ActionRepository actionRepository) {
        this.actionRepository = actionRepository;
    }

    /**
     * Find all actions
     *
     * @return List of actions
     */
    public List<Action> findAllActions() {
        return Streams.stream(actionRepository.findAll())
                .map(actionEntity -> Action.builder()
                        .withId(actionEntity.getId())
                        .withName(actionEntity.getName())
                        .withOrder(actionEntity.getOrderBy())
                        .withDuration(actionEntity.getDuration())
                        .withDeleteDate(actionEntity.getDeleteDate())
                        .build())
                .collect(Collectors.toList());    }

    /**
     * Saving new action
     * @param action action
     * @return created action
     */
    public Action save(Action action) {
        requireNonNull(action, "action is null");

        ActionEntity actionEntity = new ActionEntity();
        actionEntity.setId(action.getId());
        actionEntity.setName(action.getName());
        actionEntity.setOrderBy(action.getOrder());
        actionEntity.setDuration(action.getDuration());
        
        ActionEntity savedActionEntity = actionRepository.save(actionEntity);
        return Action.builder().from(savedActionEntity).build();
    }

    /**
     * Find actions by Id
     * @param action_id Action identifier
     * @return Found action
     */
    public Action findById(Long action_id) {
        requireNonNull(action_id, "action_id is null");

        ActionEntity foundActionAntity = actionRepository.findById(action_id)
                .orElseThrow(() ->
                new RuntimeException("Action with action_id = "+ action_id + "not found"));

        return Action.builder().from(foundActionAntity).build();
    }
}
