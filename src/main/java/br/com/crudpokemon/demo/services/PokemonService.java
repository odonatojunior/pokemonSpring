package br.com.crudpokemon.demo.services;

import br.com.crudpokemon.demo.model.Pokemon;
import br.com.crudpokemon.demo.repository.PokemonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PokemonService {
    @Autowired
    PokemonRepository repository;

    public Pokemon save(Pokemon pokemon){
        return repository.save(pokemon);
    }

    public Pokemon findById(long id) throws Exception{
        return repository.findById(id).orElseThrow(() -> new Exception("Not found"));
    }

    public Pokemon update(Pokemon pokemon) throws Exception{
        Pokemon found = repository.findById(pokemon.getId()).orElseThrow(() -> new Exception("Not found"));
        found.setLevel(pokemon.getLevel());
        found.setName(pokemon.getName());
        found.setType(pokemon.getType());
        return repository.save(found);
    }

    public List<Pokemon> findAll(){
        return repository.findAll();
    }

    public void delete(Long id) throws Exception{
        Pokemon found = repository.findById(id).orElseThrow(() -> new Exception("Not found"));
        repository.delete(found);
    }



}
