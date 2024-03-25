package cl.ckrchile.responsibleservice.controllers;

import cl.ckrchile.responsibleservice.dtos.ResponsibleCreateDTO;
import cl.ckrchile.responsibleservice.dtos.ResponsibleDTO;
import cl.ckrchile.responsibleservice.dtos.ResponsibleEditDTO;
import cl.ckrchile.responsibleservice.services.IResponsibleService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;

import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/v1/responsible")
@CrossOrigin
public class ResponsibleController {

    @Autowired
    private IResponsibleService servicio;

    @PostMapping
    //RS-CU01: Crear Responsable
    public ResponseEntity<ResponsibleDTO> crear(@RequestHeader("Authorization")String authorization, @Valid @RequestBody ResponsibleCreateDTO responsibleCreateDTO) {
        return ResponseEntity.ok(servicio.crear(responsibleCreateDTO));
    }

    @PutMapping
    //RS-CU02: Editar Responsable
    public ResponseEntity<ResponsibleDTO>editar(@RequestHeader("Authorization")String authorization, @Valid @RequestBody ResponsibleEditDTO responsibleEditDTO){

        return ResponseEntity.ok(servicio.editar(responsibleEditDTO));
    }

    @GetMapping("/{id}")
    //RS-CU03: ver Responsable
    public ResponseEntity<ResponsibleDTO> ver(@RequestHeader("Authorization")String authorization,@PathVariable @Min(1) Long id){
        return ResponseEntity.ok(servicio.ver(id));
    }

    @GetMapping("/all")
    //RS-CU04: Listar todos los Responsable
    public ResponseEntity<List<ResponsibleDTO>> ListarTodoslosResponsables(@RequestHeader("Authorization")String authorizationHeade){
        return new ResponseEntity<List<ResponsibleDTO>>(servicio.listarTodosResponsables(), HttpStatus.OK);
    }


    @GetMapping("page")
    //RS-CU05: Listar todos los Responsable con paginacion
    public ResponseEntity<List<ResponsibleDTO>>listarTodosResponsableConPaginacion(@RequestHeader("Authorization")String authorizationHeader,@PageableDefault(page = 0, size = 10) Pageable pageable){
        return new ResponseEntity<List<ResponsibleDTO>>(servicio.listaTodosResponsablesConPaginacion(pageable),HttpStatus.OK);
    }

    @GetMapping("/all/enable")
    //RS-CU06: Listar todos los Responsables activos
    public ResponseEntity<List<ResponsibleDTO>> ListarTodoslosResponsablesActivos(@RequestHeader("Authorization")String authorizationHeade){

        return new ResponseEntity<List<ResponsibleDTO>>(servicio.listarTodosResponsablesActivos(),HttpStatus.OK);
    }

    @GetMapping("/page/enable")
    //RS-CU07: Listar todos los responsables con paginación activos
    public ResponseEntity<List<ResponsibleDTO>>ListarTodoslosResponsablesConPaginacionActivos(@RequestHeader("Authorization")String authorizationHeade,@PageableDefault(page = 0, size = 10) Pageable pageable){
        return new ResponseEntity<List<ResponsibleDTO>>(servicio.ListarTodoslosResponsablesConPaginacionActivos(pageable),HttpStatus.OK);

    }


}
