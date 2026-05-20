package br.com.ferdbgg.androidjavaagenda.ui.main;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.Arrays;
import java.util.List;

import br.com.ferdbgg.androidjavaagenda.models.enums.GeneroEnum;
import br.com.ferdbgg.androidjavaagenda.models.pojos.Aluno;

public class MainViewModel extends ViewModel {

    private final MutableLiveData<List<Aluno>> alunosLiveData = new MutableLiveData<>();

    public LiveData<List<Aluno>> getAlunosLiveData() {
        return alunosLiveData;
    }

    public void carregarAlunos() {
        //TODO: pegar do banco de dados ou api
        List<Aluno> mockAlunos = Arrays.asList(
                new Aluno(1, "Maria", "maria@email.com", "11999999999", GeneroEnum.FEMININO),
                new Aluno(2, "João", "joao@email.com", "11888888888", GeneroEnum.MASCULINO),
                new Aluno(3, "Alex", "alex@email.com", "11777777777", GeneroEnum.NAO_BINARE)
        );
        alunosLiveData.setValue(mockAlunos);
    }

}
