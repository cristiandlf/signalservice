package cl.ckrchile.responsibleservice.mappers;

import cl.ckrchile.responsibleservice.daos.entities.ResponsibleEntity;
import cl.ckrchile.responsibleservice.dtos.ResponsibleCreateDTO;
import cl.ckrchile.responsibleservice.dtos.ResponsibleDTO;
import cl.ckrchile.responsibleservice.dtos.ResponsibleEditDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.data.domain.Page;

import java.util.List;


@Mapper(componentModel = "spring")
public interface IResponsibleMapper {
    @Mapping(target = "idResponsable" , ignore=true)
    @Mapping(target = "nombreResponsable" , source = "name")
    @Mapping(target = "nombreFantasiaResp" , source = "name_fantasy")
    @Mapping(target = "rut" , source = "identity_card")
    @Mapping(target = "direccion" , source = "address")
    @Mapping(target = "fechaIngreso" , source = "date_of_entry")
    @Mapping(target = "telefono" , source = "phone")
    @Mapping(target = "contacto" , source = "contact")
    @Mapping(target = "email" , source = "email")
    @Mapping(target = "isActivo" , source = "isActive")
    public ResponsibleEntity getResponsibleEntityFromResponsibleCreateDTO(ResponsibleCreateDTO responsibleCreateDTO);


    @Mapping(target = "idResponsable" , source="responsible_id")
    @Mapping(target = "nombreResponsable" , source = "name")
    @Mapping(target = "nombreFantasiaResp" , source = "name_fantasy")
    @Mapping(target = "rut" , source = "identity_card")
    @Mapping(target = "direccion" , source = "address")
    @Mapping(target = "fechaIngreso" , source = "date_of_entry")
    @Mapping(target = "telefono" , source = "phone")
    @Mapping(target = "contacto" , source = "contact")
    @Mapping(target = "email" , source = "email")
    @Mapping(target = "isActivo" , ignore = true)
    public ResponsibleEntity getResponsibleEntityFromResponsibleEditDTO(ResponsibleEditDTO responsibleEditDTO);
    public List<ResponsibleEntity> getResponsibleEntitiesFromResponsibleEditDTOS(List<ResponsibleDTO> variableDTOS);


    @Mapping(target = "responsible_id" , source = "idResponsable")
    @Mapping(target = "name" , source = "nombreResponsable")
    @Mapping(target = "name_fantasy" , source = "nombreFantasiaResp")
    @Mapping(target = "identity_card" , source = "rut")
    @Mapping(target = "address" , source = "direccion")
    @Mapping(target = "date_of_entry" , source = "fechaIngreso")
    @Mapping(target = "phone" , source = "telefono")
    @Mapping(target = "contact" , source = "contacto")
    @Mapping(target = "email" , source = "email")
    @Mapping(target = "isActive" , source = "isActivo")
    public ResponsibleDTO getResponsibleDTOFromResponsibleEntity(ResponsibleEntity responsibleEntity);
    public List<ResponsibleDTO> getResponsibleDTOSFromResponsibleEntities(List<ResponsibleEntity> responsibleEntities);


    @Mapping(target = "responsible_id" , source = "idResponsable")
    @Mapping(target = "name" , source = "nombreResponsable")
    @Mapping(target = "name_fantasy" , source = "nombreFantasiaResp")
    @Mapping(target = "identity_card" , source = "rut")
    @Mapping(target = "address" , source = "direccion")
    @Mapping(target = "date_of_entry" , source = "fechaIngreso")
    @Mapping(target = "phone" , source = "telefono")
    @Mapping(target = "contact" , source = "contacto")
    @Mapping(target = "email" , source = "email")
    @Mapping(target = "isActive" , source = "isActivo")
    public List<ResponsibleDTO> getResponsibleDTOSFromPageResponsibleEntities(Page<ResponsibleEntity> responsibleEntities);
}

