package com.example.buensaborback.domain.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@ToString
@Builder
public class Localidad extends Base{

    private String nombre;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "provincia_id")
    private Provincia provincia;

    @OneToMany(mappedBy = "localidad",cascade = CascadeType.ALL)
    @Builder.Default
    private Set<Domicilio> domicilios = new HashSet<>();
}
