package cl.ckrchile.responsibleservice.services;

import cl.ckrchile.responsibleservice.dtos.ResponsibleCreateDTO;
import cl.ckrchile.responsibleservice.dtos.ResponsibleDTO;
import cl.ckrchile.responsibleservice.dtos.ResponsibleEditDTO;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IResponsibleService {

    @Transactional
    ResponsibleDTO crear(ResponsibleCreateDTO responsibleCreateDTO);

    @Transactional
    ResponsibleDTO editar(ResponsibleEditDTO responsibleEditDTO);

    ResponsibleDTO ver(Long responsible_id);

    List<ResponsibleDTO> listarTodosResponsables();


    List<ResponsibleDTO> listaTodosResponsablesConPaginacion(Pageable pageable);

    List<ResponsibleDTO>listarTodosResponsablesActivos();

    List<ResponsibleDTO>ListarTodoslosResponsablesConPaginacionActivos(Pageable pageable);
}
