//tag::all[]
//tag::allButValidation[]
package com.example.specialityCRUD;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@Data
@Entity
@Table(name = "specialty")
public class Specialty implements Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @NotEmpty(message="specialty name is required")
    private String name;

}
