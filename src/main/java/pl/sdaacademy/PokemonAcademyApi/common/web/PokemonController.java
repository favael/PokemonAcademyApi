package pl.sdaacademy.PokemonAcademyApi.common.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.sdaacademy.PokemonAcademyApi.pokemon_details.repository.PokemonDetails;
import pl.sdaacademy.PokemonAcademyApi.pokemon_details.service.PokemonDetailsService;
import pl.sdaacademy.PokemonAcademyApi.pokemon_list_item.repository.PokemonList;
import pl.sdaacademy.PokemonAcademyApi.pokemon_list_item.service.PokemonListService;
import pl.sdaacademy.PokemonAcademyApi.registration.repository.PokemonApiUser;
import pl.sdaacademy.PokemonAcademyApi.registration.service.PokemonApiUserDto;
import pl.sdaacademy.PokemonAcademyApi.registration.service.PokemonUserApiService;

import java.util.List;

@RequestMapping("/pokemons")
@RestController
public class PokemonController {

    private final PokemonDetailsService pokemonDetailsService;
    private final PokemonListService pokemonListService;
    private final PokemonUserApiService pokemonUserApiService;

    @Autowired
    public PokemonController(PokemonDetailsService pokemonDetailsService,
                             PokemonListService pokemonListService,
                             PokemonUserApiService pokemonUserApiService) {
        this.pokemonDetailsService = pokemonDetailsService;
        this.pokemonListService = pokemonListService;
        this.pokemonUserApiService = pokemonUserApiService;
    }

    @GetMapping("/{name}")
    @CrossOrigin
    public PokemonDetails getPokemon(@PathVariable String name) {
        return pokemonDetailsService.getPokemonDetails(name);
    }

    @GetMapping
    @CrossOrigin
    public List<PokemonDetails> getPokemons(@RequestParam List<String> name) {
        return pokemonDetailsService.getPokemonDetails(name);
    }

    @GetMapping("/list")
    @CrossOrigin
    public PokemonList getPokemonsList(@RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "10") int size) {
        return pokemonListService.getPokemonListItem(page, size);
    }

    @PostMapping("/signup")
    @CrossOrigin
    public PokemonApiUserDto addUser(PokemonApiUser pokemonApiUser) {
        return pokemonUserApiService.addUser(pokemonApiUser);
    }
}
