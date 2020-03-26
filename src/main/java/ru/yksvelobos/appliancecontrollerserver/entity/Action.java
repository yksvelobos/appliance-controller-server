package ru.yksvelobos.appliancecontrollerserver.entity;

import org.joda.time.DateTime;
import ru.yksvelobos.appliancecontrollerserver.entityDb.ActionEntity;

/**
 * Action entity
 *
 * @author Ilia Sobolevsky
 */
public class Action {
    private Long id;
    private String name;
    private Integer order;
    private Long duration;
    private DateTime deleteDate;

    public Action(Long id, String name, Integer order, Long duration, DateTime deleteDate) {
        this.id = id;
        this.name = name;
        this.order = order;
        this.duration = duration;
        this.deleteDate = deleteDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    public Long getDuration() {
        return duration;
    }

    public void setDuration(Long duration) {
        this.duration = duration;
    }

    public DateTime getDeleteDate() {
        return deleteDate;
    }

    public void setDeleteDate(DateTime deleteDate) {
        this.deleteDate = deleteDate;
    }

    public static ActionBuilder builder() {
        return new ActionBuilder();
    }

    public static final class ActionBuilder {

        private Long id;
        private String name;
        private Integer order;
        private Long duration;
        private DateTime deleteDate;

        public ActionBuilder from(ActionEntity actionEntity) {
            this.id = actionEntity.getId();
            this.name = actionEntity.getName();
            this.order = actionEntity.getOrderBy();
            this.duration = actionEntity.getDuration();
            this.deleteDate = actionEntity.getDeleteDate();
            return this;
        }

        public ActionBuilder withId(Long id) {
            this.id = id;
            return this;
        }

        public ActionBuilder withName(String name) {
            this.name = name;
            return this;
        }

        public ActionBuilder withOrder(Integer order) {
            this.order = order;
            return this;
        }

        public ActionBuilder withDuration(Long duration) {
            this.duration = duration;
            return this;
        }

        public ActionBuilder withDeleteDate(DateTime deleteDate) {
            this.deleteDate = deleteDate;
            return this;
        }

        public Action build() {
            return new Action(id, name, order, duration, deleteDate);
        }
    }
}
