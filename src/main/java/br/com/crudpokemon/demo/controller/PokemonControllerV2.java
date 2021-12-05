package br.com.crudpokemon.demo.controller;


import br.com.crudpokemon.demo.model.Pokemon;
import br.com.crudpokemon.demo.services.PokemonService;
import br.com.crudpokemon.demo.services.PokemonServiceV2;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(value = "Pokemon Endpoint V2", tags = {"Person", "Model", "Endpoint"})

@RestController
@RequestMapping("/pokemon/v2")
public class PokemonControllerV2 {

    @Autowired
    private PokemonServiceV2 service;

    @ApiOperation(value="Get all Pokemon", response = Pokemon.class)
    @GetMapping
    public List<Pokemon> findAll(){
        return service.findAll();
    }

    @ApiOperation(value="Get a single Pokemon based on its Id", response = Pokemon.class)
    @GetMapping("/{id}")
    public Pokemon findById(@PathVariable("id") long id) throws Exception{
        return service.findById(id);
    }

    @ApiOperation(value = "Persist a Pokemon", response = Pokemon.class)
    @PostMapping
    public Pokemon save(@RequestBody Pokemon pokemon){
        return service.save(pokemon);
    }

    @ApiOperation(value="Edit a single Pokemon", response = Pokemon.class)
    @PutMapping
    public Pokemon update(@RequestBody Pokemon pokemon)throws Exception{
        return service.update(pokemon);
    }

    @ApiOperation(value="Delete a single Pokemon", response = Pokemon.class)
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) throws Exception{
        service.delete(id);
        return ResponseEntity.ok().build();
    }

    @ApiOperation(value="Search one or more Pokemon by name", response = Pokemon.class)
    @GetMapping("/query/{name}")
    public List<Pokemon> listByName( @PathVariable("name") String name){
        return service.listByName(name);
    }

}
