package ru.yksvelobos.appliancecontrollerserver.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.yksvelobos.appliancecontrollerserver.entityDb.ApplianceEntity;

/**
 * @author Ilia Sobolevsky on 24.03.2020
 */
@Repository
public interface ApplianceRepository extends CrudRepository<ApplianceEntity, Long> {
}
