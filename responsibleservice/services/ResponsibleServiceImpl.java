package cl.ckrchile.responsibleservice.services;

import cl.ckrchile.responsibleservice.daos.entities.ResponsibleEntity;
import cl.ckrchile.responsibleservice.daos.repositories.IResponsibleRepository;
import cl.ckrchile.responsibleservice.dtos.ResponsibleCreateDTO;
import cl.ckrchile.responsibleservice.dtos.ResponsibleDTO;
import cl.ckrchile.responsibleservice.dtos.ResponsibleEditDTO;
import cl.ckrchile.responsibleservice.errors.ResourceNotFoundException;
import cl.ckrchile.responsibleservice.mappers.IResponsibleMapper;
import cl.ckrchile.responsibleservice.security.jwt.JwtUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class ResponsibleServiceImpl implements IResponsibleService{
    @Autowired
    private IResponsibleMapper mapper;

    @Autowired
    private IResponsibleRepository repository;

    @Autowired
    private HttpServletRequest request;
    @Autowired
    private JwtUtils jwtUtils;

    @Override
    @Transactional
    public ResponsibleDTO crear(ResponsibleCreateDTO responsibleCreateDTO) {
        String user_name = jwtUtils.getUserFromJwtTokenRequest(request);
        Long user_id = jwtUtils.getUserIDFromJwtTokenRequest(request);
        ResponsibleEntity responsibleEntity = mapper.getResponsibleEntityFromResponsibleCreateDTO(responsibleCreateDTO);
        responsibleEntity = repository.save(responsibleEntity);
        return mapper.getResponsibleDTOFromResponsibleEntity(responsibleEntity);
    }

    @Override
    @Transactional
    public ResponsibleDTO editar(ResponsibleEditDTO responsibleEditDTO){
        String user_name = jwtUtils.getUserFromJwtTokenRequest(request);
        Long user_id = jwtUtils.getUserIDFromJwtTokenRequest(request);
        ResponsibleEntity responsibleEntity = mapper.getResponsibleEntityFromResponsibleEditDTO(responsibleEditDTO);
        responsibleEntity = repository.save(responsibleEntity);
        return mapper.getResponsibleDTOFromResponsibleEntity(responsibleEntity);

    }

    @Override
    public ResponsibleDTO ver(Long responsible_id){
        Optional<ResponsibleEntity> responsibleEntityOptional = repository.findById(responsible_id);
        if (responsibleEntityOptional.isPresent()){
            return mapper.getResponsibleDTOFromResponsibleEntity(responsibleEntityOptional.get());
        }
        throw new ResourceNotFoundException("No existe información para el responsable");
    }

    @Override
    public List<ResponsibleDTO> listarTodosResponsables(){
        List<ResponsibleEntity> responsibleEntities= repository.findAll();
        return mapper.getResponsibleDTOSFromResponsibleEntities(responsibleEntities);
    }



    @Override
    public List<ResponsibleDTO> listaTodosResponsablesConPaginacion(Pageable pageable) {
        Page<ResponsibleEntity> responsibleEntities= repository.findAll(pageable);
        return mapper.getResponsibleDTOSFromPageResponsibleEntities(responsibleEntities);

    }

    @Override
   public List<ResponsibleDTO>listarTodosResponsablesActivos(){
        List<ResponsibleEntity>responsibleEntities=repository.findAllByIsActivoTrue();
        return mapper.getResponsibleDTOSFromResponsibleEntities(responsibleEntities);

   }

   @Override
   public  List<ResponsibleDTO>ListarTodoslosResponsablesConPaginacionActivos(Pageable pageable){
        Page<ResponsibleEntity>responsibleEntities = repository.findAllByIsActivoTrue(pageable);
        return  mapper.getResponsibleDTOSFromPageResponsibleEntities(responsibleEntities);



   }
}
