package com.alura_cursos.forohub.topico;

import com.alura_cursos.forohub.curso.Curso;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Table(name="topicos_table")
@Entity(name="Topico")

@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of ="id")
public class Topico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUsuario;
    private String nombre;
    @Enumerated(EnumType.STRING)
    private Curso nombreCurso;
    private String titulo;
    private String mensaje;
    @Temporal(TemporalType.TIMESTAMP)
    private Timestamp fecha_creacion;

    @Column(name = "activo1")
    private Boolean activo;

    @PrePersist
    public void prePersist() {
        this.fecha_creacion = new Timestamp(System.currentTimeMillis());
    }

    //Constructor
    public Topico(DatosResgistroTopicoDTO datosResgistroTopicoDTO) {

        //this.idUsuario = datosResgistroTopicoDTO.idUsuario();
        this.activo = true;
        this.nombre = datosResgistroTopicoDTO.nombre();
        this.titulo = datosResgistroTopicoDTO.titulo();
        this.mensaje = datosResgistroTopicoDTO.mensaje();
        this.nombreCurso = datosResgistroTopicoDTO.nombreCurso();

        //this.fecha_creacion = new Timestamp(System.currentTimeMillis());
    }

    public void actualizarDatos(DatosActualizarTopico datosActualizarTopico) {
        this.nombre = datosActualizarTopico.nombre();
        this.titulo = datosActualizarTopico.titulo();
    }

    public void desactivarTopico() {
        this.activo =false;
    }
}
