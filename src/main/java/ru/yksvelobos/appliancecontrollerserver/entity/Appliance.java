package ru.yksvelobos.appliancecontrollerserver.entity;

import org.joda.time.DateTime;
import ru.yksvelobos.appliancecontrollerserver.entityDb.ApplianceEntity;

/**
 * Appliance entity
 *
 * @author Ilia Sobolevsky
 */
public class Appliance {

    private final Long id;
    private final String name;
    private final DateTime deleteDate;
    private final WorkProgram workProgram;

    public Appliance(Long id, String name, DateTime deleteDate, WorkProgram workProgram) {
        this.id = id;
        this.name = name;
        this.deleteDate = deleteDate;
        this.workProgram = workProgram;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public DateTime getDeleteDate() {
        return deleteDate;
    }

    public WorkProgram getWorkProgram() {
        return workProgram;
    }

    public static ApplianceBuilder builder() {
        return new ApplianceBuilder();
    }

    public static final class ApplianceBuilder {

        private Long id;
        private String name;
        private DateTime deleteDate;
        private WorkProgram workProgram;

        public ApplianceBuilder from(ApplianceEntity applianceEntity) {
            this.id = applianceEntity.getId();
            this.name = applianceEntity.getName();
            this.deleteDate = applianceEntity.getDeleteDate();
            this.workProgram = applianceEntity.getWorkProgramEntity() != null
                    ? WorkProgram.builder().from(applianceEntity.getWorkProgramEntity()).build()
                    : null;
            return this;
        }

        public Appliance build() {
            return new Appliance(id, name, deleteDate, workProgram);
        }
    }
}
