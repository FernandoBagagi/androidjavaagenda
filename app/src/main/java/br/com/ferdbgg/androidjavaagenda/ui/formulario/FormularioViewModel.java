package br.com.ferdbgg.androidjavaagenda.ui.formulario;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import br.com.ferdbgg.androidjavaagenda.R;
import br.com.ferdbgg.androidjavaagenda.models.enums.GeneroEnum;
import br.com.ferdbgg.androidjavaagenda.models.enums.ModoFormulario;

public class FormularioViewModel extends ViewModel {

    public final MutableLiveData<Integer> idStringTituloAppbar = new MutableLiveData<>();
    public final MutableLiveData<Integer> idStringTextoBotao = new MutableLiveData<>();
    public final MutableLiveData<ModoFormulario> modoFormulario = new MutableLiveData<>();
    public final MutableLiveData<GeneroEnum> generoSelecionado = new MutableLiveData<>(GeneroEnum.MASCULINO);

    public void mudarGeneroSelecionado(GeneroEnum genero) {
        generoSelecionado.setValue(genero);
        atualizarTextosDeAcordoComGenero();
    }

    public boolean isEdicao() {
        return ModoFormulario.EDICAO.equals(modoFormulario.getValue());
    }
    private void atualizarTextosDeAcordoComGenero() {

        if (isEdicao()) {
            idStringTituloAppbar.setValue(getLabelAppbarEdicao());
            idStringTextoBotao.setValue(R.string.formulario_aluno_botao_salvar_alteracoes);
        } else {
            idStringTituloAppbar.setValue(getLabelAppbarCriacao());
            idStringTextoBotao.setValue(R.string.formulario_aluno_botao_salvar);
        }

    }

    private Integer getLabelAppbarEdicao() {

        return switch (generoSelecionado.getValue()) {
            case FEMININO -> R.string.formulario_aluno_label_aluna;
            case NAO_BINARE -> R.string.formulario_aluno_label_alune;
            default -> R.string.formulario_aluno_label_aluno;
        };

    }

    private Integer getLabelAppbarCriacao() {

        return switch (generoSelecionado.getValue()) {
            case FEMININO -> R.string.formulario_aluno_label_nova_aluna;
            case NAO_BINARE -> R.string.formulario_aluno_label_novu_alune;
            default -> R.string.formulario_aluno_label_novo_aluno;
        };

    }

}
