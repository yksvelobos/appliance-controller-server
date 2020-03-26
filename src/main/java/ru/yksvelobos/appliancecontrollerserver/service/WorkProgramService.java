package ru.yksvelobos.appliancecontrollerserver.service;

import com.google.common.collect.Streams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.yksvelobos.appliancecontrollerserver.entity.WorkProgram;
import ru.yksvelobos.appliancecontrollerserver.entityDb.ActionEntity;
import ru.yksvelobos.appliancecontrollerserver.entityDb.WorkProgramEntity;
import ru.yksvelobos.appliancecontrollerserver.repository.ActionRepository;
import ru.yksvelobos.appliancecontrollerserver.repository.WorkProgramRepository;

import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Objects.requireNonNull;

/**
 * Service for work with work program of appliance
 *
 * @author Ilia Sobolevsky on 26.03.2020
 */
@Service
public class WorkProgramService {
    private final WorkProgramRepository workProgramRepository;
    private final ActionRepository actionRepository;

    @Autowired
    public WorkProgramService(WorkProgramRepository workProgramRepository, ActionRepository actionRepository) {
        this.workProgramRepository = workProgramRepository;
        this.actionRepository = actionRepository;
    }

    /**
     * Add action to chain of work program for appliance
     * @param actionId action identifier
     * @param programId program identifier
     * @return Entity of WorkProgram
     */
    public WorkProgram addActionToProgram(Long actionId, Long programId) {
        requireNonNull(actionId, "actionId is null");
        requireNonNull(programId, "programId is null");


        ActionEntity actionEntity = actionRepository.findById(actionId)
                .orElseThrow(() -> new RuntimeException("Action not found"));

        WorkProgramEntity workProgramEntity = workProgramRepository.findById(programId)
                .orElseThrow(() -> new RuntimeException("Action not found"));

        workProgramEntity.setActions(new HashSet<ActionEntity>() {{
            add(actionEntity);
        }});

        return WorkProgram.builder().from(workProgramRepository.save(workProgramEntity)).build();

    }

    /**
     * Create work program by name
     *
     * @param name Name of work program
     * @return Created work program entity
     */
    public WorkProgram createWorkProgram(String name) {
        requireNonNull(name, "programId is null");

        WorkProgramEntity workProgramEntity = new WorkProgramEntity();
        workProgramEntity.setName(name);

        return WorkProgram.builder().from(workProgramRepository.save(workProgramEntity)).build();

    }

    /**
     * Find all work program collection
     *
     * @return Collection of work program
     */
    public List<WorkProgram> findAll() {
        return Streams.stream(workProgramRepository.findAll())
                .map(workProgramEntity -> WorkProgram.builder().from(workProgramEntity).build())
                .collect(Collectors.toList());
    }
}
