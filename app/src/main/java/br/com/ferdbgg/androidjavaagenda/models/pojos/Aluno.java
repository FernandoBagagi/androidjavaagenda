package br.com.ferdbgg.androidjavaagenda.models.pojos;

import java.io.Serial;
import java.io.Serializable;

import br.com.ferdbgg.androidjavaagenda.models.enums.GeneroEnum;

public record Aluno(
        int id,
        String nome,
        String email,
        String telefone,
        GeneroEnum genero
) implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
}
