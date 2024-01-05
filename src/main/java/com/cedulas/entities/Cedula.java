package com.cedulas.entities;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity(name = "tcedula")
@Getter
@Setter
public class Cedula extends PanacheEntityBase {
    @Id
    private String cedula;
}