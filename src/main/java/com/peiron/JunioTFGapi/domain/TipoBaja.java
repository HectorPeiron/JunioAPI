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

@Entity(name = "TipoBajas")
public class TipoBaja {

    @Id
    @GeneratedValue
    private long id;

    @Column
    private String nombre;
}
