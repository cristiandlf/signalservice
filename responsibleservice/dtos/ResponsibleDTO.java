package cl.ckrchile.responsibleservice.dtos;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ResponsibleDTO {

    private Long responsible_id;
    private String name;
    private String name_fantasy;
    private String identity_card;
    private String address;
    private LocalDate date_of_entry;
    private String phone;
    private String contact;
    private String email;
    private Boolean isActive;
}
