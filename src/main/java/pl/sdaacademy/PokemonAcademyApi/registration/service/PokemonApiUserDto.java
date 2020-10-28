package pl.sdaacademy.PokemonAcademyApi.registration.service;

public class PokemonApiUserDto {

    private String userName;

    public PokemonApiUserDto(String userName) {
        this.userName = userName;
    }

    public PokemonApiUserDto() {

    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
