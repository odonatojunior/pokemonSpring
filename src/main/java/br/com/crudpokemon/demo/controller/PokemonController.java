package br.com.crudpokemon.demo.controller;

import br.com.crudpokemon.demo.model.Pokemon;
import br.com.crudpokemon.demo.services.PokemonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pokemon/v1")
public class PokemonController {

    @Autowired
    private PokemonService service;

    @GetMapping
    public List<Pokemon> findAll(){
        return service.findAll();
    }

    @GetMapping("/{id}")
    public Pokemon findById(@PathVariable("id") long id) throws Exception{
        return service.findById(id);
    }

    @PostMapping
    public Pokemon save(@RequestBody Pokemon pokemon){
        return service.save(pokemon);
    }

    @PutMapping
    public Pokemon update(@RequestBody Pokemon pokemon)throws Exception{
        return service.update(pokemon);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) throws Exception{
        service.delete(id);
        return ResponseEntity.ok().build();
    }
}
