package com.alura_cursos.forohub.topico;

import jakarta.validation.constraints.NotNull;

public record DatosActualizarTopico(
        @NotNull
        Long idUsuario,
        String nombre,
        String titulo) {



}
