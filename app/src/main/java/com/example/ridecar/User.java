package com.example.ridecar;

public class User {
    public String nome;
    public String cpf;
    public String email;
    public String celular;

    public User(){

    }

    public User (String nome, String cpf, String email, String celular){
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
        this.celular = celular;
    }
}
