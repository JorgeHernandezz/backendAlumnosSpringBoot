package com.ipn.mx.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "usuarios")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idusuario")
    private int idusuario;

    @Column(name = "nombre", length = 50, nullable = false)
    private String nombre;

    @Column(name = "paterno", length = 50, nullable = false)
    private String paterno;

    @Column(name = "materno", length = 50, nullable = false)
    private String materno;

    @Column(name = "email", length = 50, nullable = false)
    private String email;

    // Getters y setters
}
