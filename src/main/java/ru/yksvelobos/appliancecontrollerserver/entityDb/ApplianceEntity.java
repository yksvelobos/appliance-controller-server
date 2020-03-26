package ru.yksvelobos.appliancecontrollerserver.entityDb;

import com.fasterxml.jackson.annotation.JsonBackReference;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.annotations.WhereJoinTable;
import org.joda.time.DateTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToOne;
import javax.persistence.Table;


/**
 * @author Ilia Sobolevsky
 */
@Entity
@Table(name = "appliance")
public class ApplianceEntity implements BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column
    private String name;
    @Column
    private DateTime deleteDate;

    public WorkProgramEntity getWorkProgramEntity() {
        return workProgramEntity;
    }

    public void setWorkProgramEntity(WorkProgramEntity workProgramEntity) {
        this.workProgramEntity = workProgramEntity;
    }

    @JsonBackReference
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "work_program_id", referencedColumnName = "id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private WorkProgramEntity workProgramEntity;

//    @JoinTable(
//            name = "work_history",
//            joinColumns = @JoinColumn(name = "appliance_id"),
//            inverseJoinColumns = @JoinColumn(name = "action_id")
//    )
//    @WhereJoinTable(clause = "end_date_time is null")
//    private ActionEntity currentAction;
//
//    public ActionEntity getCurrentAction() {
//        return currentAction;
//    }
//
//    public void setCurrentAction(ActionEntity currentAction) {
//        this.currentAction = currentAction;
//    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    @Override
    public DateTime getDeleteDate() {
        return deleteDate;
    }

    @Override
    public void setDeleteDate(DateTime deleteDate) {
        this.deleteDate = deleteDate;
    }
}
