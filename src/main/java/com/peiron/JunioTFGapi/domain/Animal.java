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
@Entity(name = "Animales")

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")

public class Animal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate fechaLlegada;

    @Column
    private int numero;


    @Column
    private String sexo;

    @Column
    private BigDecimal peso;

    @ManyToOne
    @JoinColumn(name = "tipoAnimal_id")
    private TipoAnimal animalTipoAnimal;

    @ManyToOne
    @JoinColumn(name = "crianza_id")
    @JsonBackReference(value = "crianza-animales")
    private Crianza animalCrianza;
}
