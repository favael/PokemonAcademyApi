package pl.sdaacademy.PokemonAcademyApi.registration.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.sdaacademy.PokemonAcademyApi.registration.repository.PokemonApiUser;
import pl.sdaacademy.PokemonAcademyApi.registration.repository.PokemonApiUserRepository;

@Service
public class PokemonUserApiService {

    private final PasswordEncoder passwordEncoder;
    private final PokemonApiUserRepository pokemonApiUserRepository;

    @Autowired
    public PokemonUserApiService(PokemonApiUserRepository pokemonApiUserRepository, PasswordEncoder passwordEncoder) {
        this.pokemonApiUserRepository = pokemonApiUserRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public PokemonApiUser getPokemonApiUserByLogin(String login) {
        return pokemonApiUserRepository.findById(login)
                .orElseThrow(()->{
                    throw new UserNotFoundException(String.format("User with %s could not be found in db!", login));
                });
    }

    public PokemonApiUserDto addUser(PokemonApiUser pokemonApiUser) {
        pokemonApiUserRepository.findById(pokemonApiUser.getLogin()).ifPresent((user)->{
            throw new UserAlreadyExistInDbException(String.format("User with %s is already exist in db!",
                    pokemonApiUser.getLogin()));
        });
        pokemonApiUser.setPassword(passwordEncoder.encode(pokemonApiUser.getPassword()));
        PokemonApiUser addedUser = pokemonApiUserRepository.save(pokemonApiUser);
        return new PokemonApiUserDto(addedUser.getLogin());
    }
}
