package com.peiron.JunioTFGapi.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "Compras")
public class Compra {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private LocalDate fechaCompra;

    @Column
    private String descripcion;

    @ManyToMany
    @JoinTable(
            name = "compra_recurso",
            joinColumns = @JoinColumn(name = "compra_id"),
            inverseJoinColumns = @JoinColumn(name = "recurso_id")
    )
    private List<Recurso> compraRecurso;

    @ManyToOne
    @JoinColumn(name = "crianza_id")
    @JsonBackReference
    private Crianza compraCrianza;
}
