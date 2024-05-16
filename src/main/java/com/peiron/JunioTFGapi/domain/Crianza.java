package com.peiron.JunioTFGapi.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "Crianzas")
public class Crianza {

    //en esta clase tener solo fecha inicio y fecha fin y en el resto de tablas tener un id de crianza para saber aque crianza pertenece

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDate fechaInicio;

    @Column
    private LocalDate fechaFin;

    @ManyToOne
    @JoinColumn(name = "animal_id")
    private Animal CrianzaAnimal;

    @OneToMany
    @JoinColumn(name = "compra_id")
    private List<Compra> CrianzaCompra;

    /**
    @ManyToOne
    @JoinColumn(name = "recurso_id")
    private Recurso crianzaRecurso;
    */

    @ManyToOne
    @JoinColumn(name = "veterinaro_id")
    private Veterinario CrianzaVeterinario;

}
//la dejo a nulo y le meto if en caso de necesitar.