package com.peiron.JunioTFGapi.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity(name = "Animales")
public class Animal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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
    private TipoAnimal AnimalTipoAnimal;

    @ManyToOne
    @JoinColumn(name = "crianza_id")
    private Crianza AnimalCrianza;

}
