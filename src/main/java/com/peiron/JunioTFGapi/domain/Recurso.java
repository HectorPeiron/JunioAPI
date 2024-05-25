package com.peiron.JunioTFGapi.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor


@Entity(name = "Recursos")
public class Recurso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank(message = "El campo no puede estar en blanco")
    @NotNull(message = "El campo no puede estar vacío")
    @Column(nullable = false)
    private String nombre;

    @NotBlank(message = "El campo no puede estar en blanco")
    @NotNull(message = "El campo no puede estar vacío")
    @Column
    private float cantidad;

    @NotBlank(message = "El campo no puede estar en blanco")
    @NotNull(message = "El campo no puede estar vacío")
    @Column
    private float precio;

    @Column
    private String proveedor;

    @Column
    private String descripcion;

    @ManyToOne
    @JoinColumn(name = "tipoRecurso_id")
    private TipoRecurso tipoRecurso;


    @ManyToOne
    @JoinColumn(name = "tipoAnimal_id")
    private TipoAnimal RecursoTipoAnimal;

    @ManyToOne
    @JoinColumn(name = "unidad_id")
    private Unidad RecursoUnidad;

}