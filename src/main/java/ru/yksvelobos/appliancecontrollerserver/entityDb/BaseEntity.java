package ru.yksvelobos.appliancecontrollerserver.entityDb;

import org.joda.time.DateTime;

import javax.persistence.Entity;

/**
 * @author Ilia Sobolevsky on 25.03.2020
 */
public interface BaseEntity {
    Long getId();

    void setId(Long id);

    DateTime getDeleteDate();

    void setDeleteDate(DateTime dateTime);
}
