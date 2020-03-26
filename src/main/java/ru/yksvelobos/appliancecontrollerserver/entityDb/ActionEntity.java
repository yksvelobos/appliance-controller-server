package ru.yksvelobos.appliancecontrollerserver.entityDb;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.joda.time.DateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * @author Ilia Sobolevsky
 */
@Entity
@Table(name = "action")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,property = "id")
public class ActionEntity implements BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column
    private String name;
    @Column
    private Long duration;
    @Column
    private DateTime deleteDate;
    @Column
    private Integer orderBy;

    public Integer getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(Integer orderBy) {
        this.orderBy = orderBy;
    }

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "work_program_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private WorkProgramEntity workProgramEntity;

    public WorkProgramEntity getWorkProgramEntity() {
        return workProgramEntity;
    }

    public void setWorkProgramEntity(WorkProgramEntity workProgram) {
        this.workProgramEntity = workProgram;
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

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getDuration() {
        return duration;
    }

    public void setDuration(Long duration) {
        this.duration = duration;
    }

    public void setDeleteDate(DateTime deleteDate) {
        this.deleteDate = deleteDate;
    }
}
