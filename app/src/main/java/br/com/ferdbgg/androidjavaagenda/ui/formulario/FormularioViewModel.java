package br.com.ferdbgg.androidjavaagenda.ui.formulario;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import br.com.ferdbgg.androidjavaagenda.R;
import br.com.ferdbgg.androidjavaagenda.models.enums.GeneroEnum;
import br.com.ferdbgg.androidjavaagenda.models.enums.ModoFormulario;

public class FormularioViewModel extends ViewModel {

    public final MutableLiveData<String> tituloAppbar = new MutableLiveData<>();
    public final MutableLiveData<String> textoBotao = new MutableLiveData<>();
    public final MutableLiveData<ModoFormulario> modoFormulario = new MutableLiveData<>();
    public final MutableLiveData<GeneroEnum> generoSelecionado = new MutableLiveData<>();

    public void definirGeneroPorId(int checkedId) {

        if (checkedId == R.id.formulario_aluno_radio_feminino) {
            generoSelecionado.setValue(GeneroEnum.FEMININO);
        } else if (checkedId == R.id.formulario_aluno_radio_nao_binario) {
            generoSelecionado.setValue(GeneroEnum.NAO_BINARE);
        } else {
            generoSelecionado.setValue(GeneroEnum.MASCULINO);
        }

        atualizarTextosDeAcordoComGenenro();

    }
    private void atualizarTextosDeAcordoComGenenro() {

        if (ModoFormulario.EDICAO.equals(modoFormulario.getValue())) {
            tituloAppbar.setValue(getLabelAppbarEdicao());
            textoBotao.setValue(String.valueOf(R.string.formulario_aluno_botao_salvar_alteracoes));
        } else {
            tituloAppbar.setValue(getLabelAppbarCriacao());
            textoBotao.setValue(String.valueOf(R.string.formulario_aluno_botao_salvar));
        }

    }

    private String getLabelAppbarEdicao() {

        if (GeneroEnum.FEMININO.equals(generoSelecionado.getValue())) {
            return String.valueOf(R.string.formulario_aluno_label_aluna);
        }

        if (GeneroEnum.NAO_BINARE.equals(generoSelecionado.getValue())) {
            return String.valueOf(R.string.formulario_aluno_label_alune);
        }

        return String.valueOf(R.string.formulario_aluno_label_aluno);

    }

    private String getLabelAppbarCriacao() {

        if (GeneroEnum.FEMININO.equals(generoSelecionado.getValue())) {
            return String.valueOf(R.string.formulario_aluno_label_nova_aluna);
        }

        if (GeneroEnum.NAO_BINARE.equals(generoSelecionado.getValue())) {
            return String.valueOf(R.string.formulario_aluno_label_novu_alune);
        }

        return String.valueOf(R.string.formulario_aluno_label_novo_aluno);

    }

}
