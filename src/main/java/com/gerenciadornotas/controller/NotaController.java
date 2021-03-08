package com.gerenciadornotas.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.gerenciadornotas.entity.Nota;
import com.gerenciadornotas.repository.NotaRepository;

@RestController
@RequestMapping("/api/notas")
public class NotaController {

	@Autowired
	NotaRepository notaRep;

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Nota salvarNota(@RequestBody @Valid Nota nota) {
		notaRep.save(nota);
		return nota;
	}

	@GetMapping
	public List<Nota> buscarNotas(Nota nota) {
		return notaRep.findAll();
	}

	@GetMapping("{id}")
	public Nota buscarnota(@PathVariable Integer id) {
		return notaRep.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Nota não encontrada"));
	}

	@DeleteMapping("{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deletar(@PathVariable Integer id) {
		notaRep.findById(id).map(nota -> {
			notaRep.delete(nota);
			return Void.TYPE;
		}).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Nota não encontrada"));
	}

	@PutMapping("{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void atualizar( @PathVariable Integer id, @RequestBody @Valid Nota notaAtualizada) {
		notaRep
		.findById(id)
		.map(nota ->{
			notaAtualizada.setId(nota.getId());
			return notaRep.save(notaAtualizada);
		})
		.orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Nota não encontrada") );
	}

}
