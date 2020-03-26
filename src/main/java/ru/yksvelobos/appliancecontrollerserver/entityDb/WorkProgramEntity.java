package ru.yksvelobos.appliancecontrollerserver.entityDb;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.joda.time.DateTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.util.Set;

/**
 * @author Ilia Sobolevsky on 24.03.2020
 */
@Entity
@Table(name = "work_program")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class WorkProgramEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column
    private String name;

    @Column
    private DateTime deleteDate;

    @JsonBackReference
    @OneToOne(mappedBy = "workProgramEntity")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private ApplianceEntity applianceEntity;


    @JsonManagedReference
    @OneToMany(cascade = CascadeType.ALL, mappedBy="workProgramEntity")
    private Set<ActionEntity> actions;

    public ApplianceEntity getApplianceEntity() {
        return applianceEntity;
    }

    public void setApplianceEntity(ApplianceEntity applianceEntity) {
        this.applianceEntity = applianceEntity;
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

    public Set<ActionEntity> getActions() {
        return actions;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDeleteDate(DateTime deleteDate) {
        this.deleteDate = deleteDate;
    }

    public void setActions(Set<ActionEntity> actions) {
        this.actions = actions;
    }
}
