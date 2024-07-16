package com.alura_cursos.forohub.controller;

import com.alura_cursos.forohub.topico.*;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/topicos")
public class TopicoController {
    //No es recomendable
    @Autowired
    private TopicoRepository topicoRepository;

    @PostMapping
    public void registrarTopico(@RequestBody @Valid DatosResgistroTopicoDTO datosResgistroTopicoDTO){
        topicoRepository.save(new Topico(datosResgistroTopicoDTO));
    }

    @GetMapping("/adminlist")
    public List<Topico> listarTopicos(){
        return topicoRepository.findAll();
    }

    @GetMapping
    public Page<DatosListadoTopico> listadoTopicos(@PageableDefault(size=5) Pageable paginacion){
        //Retorna con  No activos
        //return topicoRepository.findAll(paginacion).map(DatosListadoTopico::new);
        //Retorna sin desactivados
        return topicoRepository.findByActivoTrue(paginacion).map(DatosListadoTopico::new);

    }



    @PutMapping
    @Transactional
    public void actualizarTopico(@RequestBody @Valid DatosActualizarTopico datosActualizarTopico){
        Topico topico = topicoRepository.getReferenceById(datosActualizarTopico.idUsuario());
        topico.actualizarDatos(datosActualizarTopico);

    }

    @DeleteMapping("/admindelete/{idUsuario}")
    //La siguiente linea para que haga efectos en mi DB
    @Transactional
    public void eliminarTopico(@PathVariable Long idUsuario){
        Topico topico = topicoRepository.getReferenceById(idUsuario);
        topicoRepository.delete(topico);
    }

    @DeleteMapping("/{idUsuario}")
    @Transactional
    public void eliminarTopicoUsuario(@PathVariable Long idUsuario){
        Topico topico = topicoRepository.getReferenceById(idUsuario);
        topico.desactivarTopico();
    }
}
