package com.peiron.JunioTFGapi.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
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
//aqui tanto cantidad y en recurso
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private LocalDate fechaCompra;

    @Column
    private String descripcion;

    @ManyToMany
    @JoinTable(name = "recurso_id")
    private List<Recurso> CompraRecurso;

    @ManyToOne
    @JoinColumn(name = "crianza_id")
    private Crianza CompraCrianza;

}


