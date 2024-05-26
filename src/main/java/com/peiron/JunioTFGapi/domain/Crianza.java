package com.peiron.JunioTFGapi.domain;

import com.fasterxml.jackson.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "Crianzas")
public class Crianza {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate fechaInicio;

    @Column
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate fechaFin;

    @OneToMany(mappedBy = "animalCrianza")
    @JsonManagedReference(value = "crianza-animales")
    private List<Animal> crianzaAnimal;

    @OneToMany(mappedBy = "compraCrianza")
    @JsonManagedReference
    private List<Compra> crianzaCompra;


    @OneToMany(mappedBy = "bajaCrianza")
    @JsonManagedReference(value = "crianza-bajas")
    private List<Baja> crianzaBaja;
}