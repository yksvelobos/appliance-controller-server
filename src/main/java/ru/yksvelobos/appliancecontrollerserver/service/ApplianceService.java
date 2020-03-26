package ru.yksvelobos.appliancecontrollerserver.service;

import com.google.common.collect.Streams;
import org.springframework.stereotype.Service;
import ru.yksvelobos.appliancecontrollerserver.ProgramExecutorService;
import ru.yksvelobos.appliancecontrollerserver.Strategy;
import ru.yksvelobos.appliancecontrollerserver.TaskChain;
import ru.yksvelobos.appliancecontrollerserver.Task;
import ru.yksvelobos.appliancecontrollerserver.entity.Appliance;
import ru.yksvelobos.appliancecontrollerserver.entityDb.ApplianceEntity;
import ru.yksvelobos.appliancecontrollerserver.entityDb.WorkProgramEntity;
import ru.yksvelobos.appliancecontrollerserver.repository.ApplianceRepository;
import ru.yksvelobos.appliancecontrollerserver.repository.WorkProgramRepository;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.Objects.requireNonNull;

/**
 * Service to work with appliance
 *
 * @author Ilia Sobolevsky
 */
@Service
public class ApplianceService {
    private final ApplianceRepository applianceRepository;
    private final WorkProgramRepository workProgramRepository;
    private final ProgramExecutorService programExecutorService;
    private final Strategy strategy;

    public ApplianceService(ApplianceRepository applianceRepository,
                            WorkProgramRepository workProgramRepository,
                            ProgramExecutorService programExecutorService,
                            Strategy strategy) {
        this.applianceRepository = applianceRepository;
        this.workProgramRepository = workProgramRepository;
        this.programExecutorService = programExecutorService;
        this.strategy = strategy;
    }

    /**
     * Register new Appliance by name
     * @param name Name of new appliance
     * @return
     */
    public Appliance register(String name) {
        requireNonNull(name, "name is null");
        ApplianceEntity applianceEntity = new ApplianceEntity();

        applianceEntity.setName(name);
        ApplianceEntity registeredAppliance = applianceRepository.save(applianceEntity);
        return Appliance.builder().from(registeredAppliance).build();
    }

    /**
     * Find appliance by id
     * @param id Identifier of appliance
     * @return Appliance entity
     */
    public Appliance findById(Long id) {
       requireNonNull(id, "id is null");

       ApplianceEntity foundAppliance = getApplianceEntity(id);

       return Appliance.builder().from(foundAppliance).build();
    }

    /**
     * Find all appliance
     * @return Collection of appliance
     */
    public List<Appliance> findAll() {
        return Streams.stream(applianceRepository.findAll())
                .map(applianceEntity -> Appliance.builder().from(applianceEntity).build())
                .collect(Collectors.toList());
    }

    /**
     * Add program to appliance
     * @param applianceId appliance Id for add
     * @param programId program Id
     * @return Current Appliance entity
     */
    public Appliance addProgram(Long applianceId, Long programId) {
        requireNonNull(applianceId, "applianceId is null");
        requireNonNull(programId, "programId is null");

        ApplianceEntity applianceEntity = getApplianceEntity(applianceId);

        WorkProgramEntity workProgramEntity = workProgramRepository.findById(programId)
                .orElseThrow(() -> new RuntimeException("Work program not found by programId:= " + programId));

        applianceEntity.setWorkProgramEntity(workProgramEntity);

        ApplianceEntity savedAppliance = applianceRepository.save(applianceEntity);

        return Appliance.builder().from(savedAppliance).build();
    }

    /**
     * Getting started with the program that is linked to this device
     * @param applianceId Identifier of appliance
     */
    public void startWorkProgram(Long applianceId) {
        requireNonNull(applianceId, "applianceId is null");
        Appliance appliance = findById(applianceId);

        if (appliance.getWorkProgram() == null) {
            throw new RuntimeException("Empty work program in appliance with applianceId:=" + applianceId);
        }

        Task task = TaskChain.builder().fromProgram(appliance.getWorkProgram(), strategy).buildTask();

        programExecutorService.beginWork(applianceId, (Runnable) task);
    }

    /**
     * Stop the program that is linked to this device
     * @param applianceId Identifier of appliance
     */
    public void stopWorkProgram(Long applianceId) {
        requireNonNull(applianceId, "applianceId is null");
        programExecutorService.stopWork(applianceId);
    }

    private ApplianceEntity getApplianceEntity(Long applianceId) {
        requireNonNull(applianceId, "applianceId is null");
        return applianceRepository.findById(applianceId)
                .orElseThrow(() ->
                        new RuntimeException("Appliance not found by id applianceId:=" + applianceId));

    }

}
