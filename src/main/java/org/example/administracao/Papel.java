/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.example.administracao;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import java.util.List;
import java.util.Objects;

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
    
    protected String nomePapel;
    public abstract String getNomePapel(); // para retonar na serializacao
    
    @JsonIgnore
    public abstract List<? extends Permissao> getPermissoes(); // retorna as permissoes. Nao tem o atributo pq sera exclusivo da classe filha que implementa Papel.
    
    public Papel() {} // construtor padrao para o Jackson

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 89 * hash + Objects.hashCode(this.nomePapel);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Papel other = (Papel) obj;
        return Objects.equals(this.nomePapel, other.nomePapel);
    }

    
}
