package org.generation.gamestory.controller;

import java.util.List;

import org.generation.gamestory.model.Jogo;
import org.generation.gamestory.repository.JogoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/jogo")
@CrossOrigin(origins="*")
public class JogoController {

	@Autowired
	public JogoRepository repository;
	
	@GetMapping
	public ResponseEntity<List<Jogo>> getAll(){
		return ResponseEntity.ok(repository.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Jogo> getById(@PathVariable Long id){
		return repository.findById(id)
				.map(resp -> ResponseEntity.ok(resp))
				.orElse(ResponseEntity.notFound().build());
	}
	
	@GetMapping("/nome/{nome}")
	public ResponseEntity<List<Jogo>> getByName(@PathVariable String nome){
		return ResponseEntity.ok(repository.findAllByNomeIgnoreCase(nome));
	}
	
	@PostMapping
	public ResponseEntity<Jogo> post (@RequestBody Jogo jogo){
		return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(jogo));
	}
	
	@PutMapping
	public ResponseEntity<Jogo> put (@RequestBody Jogo jogo){
		return ResponseEntity.status(HttpStatus.OK).body(repository.save(jogo));
	}
	
	@DeleteMapping("/{id}")
	public void delete (@PathVariable Long id) {
		repository.deleteById(id);
	}
		
}
