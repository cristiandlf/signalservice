package cl.ckrchile.responsibleservice.dtos;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
public class ResponsibleEditDTO {

    @NotNull
    @Min(1)
    private Long responsible_id;
    @NotBlank
    private String name;
    private String name_fantasy;
    private String identity_card;
    private String address;
    private LocalDate date_of_entry;
    @NotBlank
    private String phone;
    private String contact;
    @NotBlank
    private String email;
    private Boolean isActive;
}
