package ru.yksvelobos.appliancecontrollerserver.entityDb;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.joda.time.DateTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * @author Ilia Sobolevsky on 24.03.2020
 */
@Entity
@Table(name = "work_history")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class WorkHistoryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

//    @JsonBackReference
//    @OneToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "appliance_id", referencedColumnName = "id")
//    @OnDelete(action = OnDeleteAction.CASCADE)


//    @Id
//    @Column(name = "appliance_id", insertable = false, updatable = true)
//    private ApplianceEntity applianceEntity;

//    @JsonBackReference
//    @OneToOne(fetch = FetchType.LAZY, optional = false)
//    @JoinColumn(name = "action_id", referencedColumnName = "id")
//    @OnDelete(action = OnDeleteAction.CASCADE)


//    @Id
//    @Column(name = "action_id", insertable = false, updatable = true)
//    private ActionEntity actionEntity;

    @Column
    private DateTime startDeleteDate;

    @Column
    private DateTime endDeleteDate;

    public DateTime getStartDeleteDate() {
        return startDeleteDate;
    }

    public void setStartDeleteDate(DateTime startDeleteDate) {
        this.startDeleteDate = startDeleteDate;
    }

    public DateTime getEndDeleteDate() {
        return endDeleteDate;
    }

    public void setEndDeleteDate(DateTime endDeleteDate) {
        this.endDeleteDate = endDeleteDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

//    public ApplianceEntity getApplianceEntity() {
//        return applianceEntity;
//    }
//
//    public void setApplianceEntity(ApplianceEntity applianceEntity) {
//        this.applianceEntity = applianceEntity;
//    }
//
//    public ActionEntity getActionEntity() {
//        return actionEntity;
//    }
//
//    public void setActionEntity(ActionEntity actionEntity) {
//        this.actionEntity = actionEntity;
//    }
}
