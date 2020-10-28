package pl.sdaacademy.PokemonAcademyApi.registration.service;

public class UserAlreadyExistInDbException extends RuntimeException{

    public UserAlreadyExistInDbException(String message) {
        super(message);
    }
}
