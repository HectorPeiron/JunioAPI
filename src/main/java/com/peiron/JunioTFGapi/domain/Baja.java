package com.peiron.JunioTFGapi.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "Bajas")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")

public class Baja {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate fecha;

    @Column
    private int cantidad;


    @ManyToOne
    @JoinColumn(name = "tipoBaja_id")
    private TipoBaja bajaTipoBaja;

    @ManyToOne
    @JoinColumn(name = "crianza_id")
    @JsonBackReference(value = "crianza-bajas")
    private Crianza bajaCrianza;
}
