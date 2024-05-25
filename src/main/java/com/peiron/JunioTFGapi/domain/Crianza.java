package com.peiron.JunioTFGapi.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "Crianzas")
public class Crianza {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private LocalDate fechaInicio;

    @Column
    private LocalDate fechaFin;

    @OneToMany(mappedBy = "animalCrianza")
    @JsonManagedReference(value = "crianza-animales")
    private List<Animal> crianzaAnimal;

    @OneToMany(mappedBy = "compraCrianza")
    @JsonManagedReference
    private List<Compra> crianzaCompra;
/**
    @ManyToMany
    @JoinTable(
            name = "crianza_veterinario",
            joinColumns = @JoinColumn(name = "crianza_id"),
            inverseJoinColumns = @JoinColumn(name = "veterinario_id")
    )
    @JsonBackReference
    private Set<Veterinario> veterinarios;
*/
}