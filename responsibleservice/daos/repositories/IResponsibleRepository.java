package cl.ckrchile.responsibleservice.daos.repositories;

import cl.ckrchile.responsibleservice.daos.entities.ResponsibleEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IResponsibleRepository extends JpaRepository<ResponsibleEntity,Long> {
    Page<ResponsibleEntity> findAll(Pageable pageable);

    Page<ResponsibleEntity> findAllByIsActivoTrue(Pageable pageable);

    List<ResponsibleEntity> findAllByIsActivoTrue();



}
