package ru.yksvelobos.appliancecontrollerserver.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.yksvelobos.appliancecontrollerserver.entityDb.WorkHistoryEntity;

/**
 * @author Ilia Sobolevsky on 26.03.2020
 */
@Repository
public interface WorkHistoryRepository extends CrudRepository<WorkHistoryEntity, Long> {
}
