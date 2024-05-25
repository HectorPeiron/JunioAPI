package com.peiron.JunioTFGapi.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "Animales")
public class Animal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private LocalDate fechaLlegada;

    @Column
    private int numero;

    @Column
    private int bajas;

    @Column
    private String sexo;

    @Column
    private BigDecimal peso;

    @ManyToOne
    @JoinColumn(name = "tipoAnimal_id")
    @JsonBackReference(value = "tipoAnimal-animales")
    private TipoAnimal animalTipoAnimal;

    @ManyToOne
    @JoinColumn(name = "crianza_id")
    @JsonBackReference(value = "crianza-animales")
    private Crianza animalCrianza;
}
