package br.com.ferdbgg.androidjavaagenda.ui.formulario;

import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.IntentCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.textfield.TextInputEditText;

import br.com.ferdbgg.androidjavaagenda.R;
import br.com.ferdbgg.androidjavaagenda.models.enums.GeneroEnum;
import br.com.ferdbgg.androidjavaagenda.models.enums.ModoFormulario;
import br.com.ferdbgg.androidjavaagenda.models.pojos.Aluno;

public class FormularioAlunoActivity extends AppCompatActivity {

    private FormularioViewModel viewModel;
    private Button botao;
    private RadioGroup generoGroup;
    private static final int CLEAR_ID_VALUE_RADIO_BUTTON = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        EdgeToEdge.enable(this);

        setContentView(R.layout.activity_formulario_aluno);

        ViewCompat.setOnApplyWindowInsetsListener(
                findViewById(R.id.formulario_aluno),
                this::addSystemBarPadding
        );

        final MaterialToolbar toolbar = findViewById(R.id.formulario_aluno_top_app_bar);
        setSupportActionBar(toolbar);

        botao = findViewById(R.id.formulario_aluno_botao_salvar);
        botao.setOnClickListener(this::onClickBotaoListener);

        generoGroup = findViewById(R.id.formulario_aluno_radio_group);
        generoGroup.setOnCheckedChangeListener(this::radioGroupListener);

        viewModel = new ViewModelProvider(this).get(FormularioViewModel.class);
        viewModel.idStringTituloAppbar.observe(this, this::setTitleAppbar);
        viewModel.idStringTextoBotao.observe(this, this::setTextoBotao);

        final Aluno alunoEdicao = getAlunoEdicao();
        if (ModoFormulario.EDICAO.equals(getModoFormulario()) && alunoEdicao != null) {
            edicao(alunoEdicao);
        } else {
            criacao();
        }
        marcarOpcaoRadioGroup();

    }

    private WindowInsetsCompat addSystemBarPadding(View view, WindowInsetsCompat insets) {

        final var systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());

        view.setPadding(
                systemBars.left,
                0,
                systemBars.right,
                systemBars.bottom
        );

        return insets;

    }

    private void onClickBotaoListener(View view) {
        // TODO: validar formulário
        final Aluno alunoSalvar = getAlunoDadosFormulario();
        // TODO: persistir aluno
        // TODO: voltar pra tela anterior
    }

    private Aluno getAlunoDadosFormulario() {

        final Integer id = viewModel.isEdicao()
                ? Integer.parseInt(encontrarEPegarValorCampo(R.id.formulario_aluno_id_text))
                : 0;
        final String nome = encontrarEPegarValorCampo(R.id.formulario_aluno_nome_text);
        final String email = encontrarEPegarValorCampo(R.id.formulario_aluno_email_text);
        final String telefone = encontrarEPegarValorCampo(R.id.formulario_aluno_telefone_text);
        final GeneroEnum genero = viewModel.generoSelecionado.getValue();

        return new Aluno(id, nome, email, telefone, genero);

    }

    private String encontrarEPegarValorCampo(int campoId) {

        final TextInputEditText campo = findViewById(campoId);
        final Editable textoCampo = campo.getText();
        return textoCampo == null ? "" : textoCampo.toString();

    }

    private void radioGroupListener(RadioGroup group, int checkedId) {
        if (checkedId == R.id.formulario_aluno_radio_feminino) {
            viewModel.mudarGeneroSelecionado(GeneroEnum.FEMININO);
        } else if (checkedId == R.id.formulario_aluno_radio_nao_binario) {
            viewModel.mudarGeneroSelecionado(GeneroEnum.NAO_BINARE);
        } else {
            viewModel.mudarGeneroSelecionado(GeneroEnum.MASCULINO);
        }
    }

    private void setTitleAppbar(int idString) {
        final var actionBar = getSupportActionBar();
        if (actionBar != null) {
            final String label = getString(idString);
            actionBar.setTitle(label);
        }
    }

    private void setTextoBotao(int idString) {
        final String texto = getString(idString);
        botao.setText(texto);
    }

    private Aluno getAlunoEdicao() {
        return IntentCompat.getSerializableExtra(getIntent(), "aluno", Aluno.class);
    }
    //TODO: transformar essas keys em constantes
    private ModoFormulario getModoFormulario() {
        return IntentCompat
                .getSerializableExtra(getIntent(), "modoFormulario", ModoFormulario.class);
    }

    private void edicao(Aluno aluno) {
        viewModel.modoFormulario.setValue(ModoFormulario.EDICAO);
        viewModel.mudarGeneroSelecionado(aluno.genero());
        preencherCamposFomulario(aluno);
    }

    private void preencherCamposFomulario(Aluno aluno) {
        encontrarEPreencherCampo(R.id.formulario_aluno_id_text, String.valueOf(aluno.id()));
        encontrarEPreencherCampo(R.id.formulario_aluno_nome_text, aluno.nome());
        encontrarEPreencherCampo(R.id.formulario_aluno_email_text, aluno.email());
        encontrarEPreencherCampo(R.id.formulario_aluno_telefone_text, aluno.telefone());
    }

    private void encontrarEPreencherCampo(int campoId, String valor) {
        final TextInputEditText campo = findViewById(campoId);
        campo.setText(valor);
    }

    private void criacao() {
        esconderCampoId();
        viewModel.modoFormulario.setValue(ModoFormulario.CRIACAO);
        viewModel.mudarGeneroSelecionado(GeneroEnum.MASCULINO);
    }

    private void esconderCampoId() {
        final View campoId = findViewById(R.id.formulario_aluno_id_layout);
        campoId.setVisibility(View.GONE);
    }

    private void marcarOpcaoRadioGroup() {
        final int idRadio = getIdRadioButtonGeneroSelecionado();
        if(CLEAR_ID_VALUE_RADIO_BUTTON == idRadio) {
            generoGroup.clearCheck();
        } else {
            generoGroup.check(idRadio);
        }
    }

    private int getIdRadioButtonGeneroSelecionado() {
        return switch (viewModel.generoSelecionado.getValue()) {
            case MASCULINO -> R.id.formulario_aluno_radio_masculino;
            case FEMININO -> R.id.formulario_aluno_radio_feminino;
            case NAO_BINARE -> R.id.formulario_aluno_radio_nao_binario;
            case null -> CLEAR_ID_VALUE_RADIO_BUTTON;
        };
    }

}