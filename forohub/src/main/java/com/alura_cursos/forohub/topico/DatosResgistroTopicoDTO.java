package com.alura_cursos.forohub.topico;

import com.alura_cursos.forohub.curso.Curso;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.sql.Timestamp;



public record DatosResgistroTopicoDTO(
        //Long idUsuario,
        @NotBlank
        //@UniqueElements
        String nombre,
        @NotBlank
        String titulo,
        @NotBlank
        String mensaje,
        @NotNull
        Curso nombreCurso

//        @NotBlank
//        Timestamp fecha_creacion
        ){

        //Todas la validaciones con @Notblank se deben llamar en Controller con @Valid{
}
