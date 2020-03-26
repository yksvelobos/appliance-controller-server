package ru.yksvelobos.appliancecontrollerserver.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.yksvelobos.appliancecontrollerserver.entityDb.ActionEntity;

/**
 * @author Ilia Sobolevsky on 24.03.2020
 */
@Repository
public interface ActionRepository extends CrudRepository<ActionEntity, Long> {
}
