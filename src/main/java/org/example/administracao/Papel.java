/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.example.administracao;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

/**
 *
 * @author arkham
 */

// as anotacoes a seguir servem para garantir o bom funcionamento da serializacao do JSON.
// Por padrao, o Jackson nao sabe trabalhar muito bem com objetos aninhados, principalmente ao
// juntar com herancas e poliforsmo. Por isso, esses codigos servem para explicitar exatamente
// como fazer essas acoes.


// configura como o Jackson vai interpretar a classe mae
@JsonTypeInfo (
        use = JsonTypeInfo.Id.NAME, // seu nome
        include = JsonTypeInfo.As.EXISTING_PROPERTY, // propriedade existente, visto que papel ja foi declarado na classe mae
        property = "nomePapel", // nome que aparecera como chave no JSON
        visible = true // habilita a visibilidade para que o Jackson consiga estruturar corretamente
)


// configura como o Jackson vai interpretar as classes filhas
// essencial para o Jackson saber como instanciar os objetos aninhados

@JsonSubTypes({
    @JsonSubTypes.Type(value = Administrador.class, name = "ADMINISTRADOR"),
    @JsonSubTypes.Type(value = Organizador.class, name = "ORGANIZADOR"),
    @JsonSubTypes.Type(value = Operador.class, name = "OPERADOR")
})
public abstract class Papel {
    
    String nomePapel;
    public Papel() {} // construtor padrao para o Jackson
    public abstract String getNomePapel(); // para retonar na serializacao
}
