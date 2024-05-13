package com.peiron.JunioTFGapi.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity(name = "Unidades")
public class Unidad {

    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String nombre;
}
