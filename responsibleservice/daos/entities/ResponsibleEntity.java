package cl.ckrchile.responsibleservice.daos.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity(name="responsable")
@Data
public class ResponsibleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idResponsable;
    private String nombreResponsable;
    private String nombreFantasiaResp;
    private String rut;
    private String direccion;
    private LocalDate fechaIngreso;
    private String telefono;
    private String contacto;
    private String email;
    private Boolean isActivo;
}
