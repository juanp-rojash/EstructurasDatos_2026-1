package com.example.demo.Model.Entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "usuarios")
public class Usuario {

    @Id
    private String Id;

    private String Nombre;
    private String Apellido;
    private String Email;
    private String Password;
    private int Edad;

}
