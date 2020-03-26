package ru.yksvelobos.appliancecontrollerserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;
import ru.yksvelobos.appliancecontrollerserver.entityDb.BaseEntity;

import java.io.Serializable;

/**
 * @author Ilia Sobolevsky on 25.03.2020
 */
@NoRepositoryBean
public interface BaseRepository <T extends BaseEntity, ID extends Serializable>
        extends CrudRepository<T, ID> {

    void delete(T entity);
}