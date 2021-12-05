package br.com.crudpokemon.demo.services;

import br.com.crudpokemon.demo.model.Pokemon;
import br.com.crudpokemon.demo.repository.PokemonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.Date;
import java.util.List;

@Service
public class PokemonServiceV2 {
    @Autowired
    PokemonRepository repository;

    public Pokemon save(Pokemon pokemon){
        pokemon.setRegisterDate(new Date());
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
        found.setAbility(pokemon.getAbility());
        return repository.save(found);
    }

    public List<Pokemon> findAll(){
        return repository.findAll();
    }

    public void delete(Long id) throws Exception{
        Pokemon found = repository.findById(id).orElseThrow(() -> new Exception("Not found"));
        repository.delete(found);
    }


    @Autowired
    private EntityManager em;

    public List<Pokemon> listByName(String name){
        String hql  = "from Pokemon where name like " + ":name order by name";
        Query query = em.createQuery(hql);
        query.setParameter("name", name + "%");
        List<Pokemon> pokedex = query.getResultList();
        return pokedex;
    }

}
