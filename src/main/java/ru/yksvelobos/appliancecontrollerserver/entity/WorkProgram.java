package ru.yksvelobos.appliancecontrollerserver.entity;

import org.joda.time.DateTime;
import ru.yksvelobos.appliancecontrollerserver.entityDb.WorkProgramEntity;

import java.util.Set;
import java.util.stream.Collectors;

/**
 * Work program entity
 *
 * @author Ilia Sobolevsky
 */
public class WorkProgram {

    private Long id;
    private String name;
    private DateTime deleteDate;
    private Set<Action> actions;

    public WorkProgram(Long id, String name, DateTime deleteDate, Set<Action> actions) {
        this.id = id;
        this.name = name;
        this.deleteDate = deleteDate;
        this.actions = actions;
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

    public Set<Action> getActions() {
        return actions;
    }

    public static WorkProgramBuilder builder() {
        return new WorkProgramBuilder();
    }

    public static final class WorkProgramBuilder {

        private Long id;
        private String name;
        private DateTime deleteDate;
        private Set<Action> actions;

        public WorkProgramBuilder from(WorkProgramEntity workProgramEntity) {
            this.id = workProgramEntity.getId();
            this.name = workProgramEntity.getName();
            this.deleteDate = workProgramEntity.getDeleteDate();
            this.actions = workProgramEntity.getActions().stream()
                    .map(actionEntity -> Action.builder().from(actionEntity).build())
                    .collect(Collectors.toSet());
            return this;
        }

        public WorkProgram build() {
            return new WorkProgram(id, name, deleteDate, actions);
        }
    }
}
