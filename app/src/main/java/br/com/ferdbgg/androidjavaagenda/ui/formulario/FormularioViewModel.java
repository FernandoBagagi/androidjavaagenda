package br.com.ferdbgg.androidjavaagenda.ui.formulario;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import br.com.ferdbgg.androidjavaagenda.R;
import br.com.ferdbgg.androidjavaagenda.models.enums.GeneroEnum;
import br.com.ferdbgg.androidjavaagenda.models.enums.ModoFormulario;

public class FormularioViewModel extends ViewModel {

    public final MutableLiveData<Integer> idStringTituloAppbar = new MutableLiveData<>();
    public final MutableLiveData<Integer> idStringTextoBotao = new MutableLiveData<>();
    public final MutableLiveData<GeneroEnum> generoSelecionado = new MutableLiveData<>(GeneroEnum.MASCULINO);

    private ModoFormulario modoFormulario = ModoFormulario.CRIACAO;

    public void setModoFomularioToCriacao() {
        this.modoFormulario = ModoFormulario.CRIACAO;
    }

    public void setModoFomularioToEdicao() {
        this.modoFormulario = ModoFormulario.EDICAO;
    }

    public boolean isModoEdicao() {
        return ModoFormulario.EDICAO.equals(modoFormulario);
    }

    public void mudarGeneroSelecionado(GeneroEnum genero) {
        generoSelecionado.setValue(genero);
        atualizarTextosDeAcordoComGenero();
    }

    private void atualizarTextosDeAcordoComGenero() {

        if (isModoEdicao()) {
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
            case MASCULINO -> R.string.formulario_aluno_label_aluno;
            case null -> R.string.formulario_aluno_top_app_bar_title;
        };

    }

    private Integer getLabelAppbarCriacao() {

        return switch (generoSelecionado.getValue()) {
            case FEMININO -> R.string.formulario_aluno_label_nova_aluna;
            case NAO_BINARE -> R.string.formulario_aluno_label_novu_alune;
            case MASCULINO -> R.string.formulario_aluno_label_novo_aluno;
            case null -> R.string.formulario_aluno_top_app_bar_title;
        };

    }

}
