package com.alura_cursos.forohub.topico;

import java.sql.Timestamp;

public record DatosListadoTopico(
        Long idUsuario,
        String titulo,
        String mensaje,
        Timestamp fechaCreacion) {

    public DatosListadoTopico (Topico topico) {
        this(topico.getIdUsuario(), topico.getTitulo(), topico.getMensaje(), topico.getFecha_creacion());
    }


}
