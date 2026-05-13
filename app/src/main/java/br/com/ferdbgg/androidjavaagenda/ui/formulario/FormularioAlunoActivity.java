package br.com.ferdbgg.androidjavaagenda.ui.formulario;

import android.os.Bundle;
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

        viewModel = new ViewModelProvider(this).get(FormularioViewModel.class);
        viewModel.tituloAppbar.observe(this, this::setLabelAppbar);
        viewModel.textoBotao.observe(this, this::setLabelBotaoSalvar);

        final var aluno = getAlunoEdicao();
        if (ModoFormulario.EDICAO.equals(getModoFormulario()) && aluno != null) {
            edicao(aluno);
        } else {
            criacao();
        }

        final RadioGroup generoGroup = findViewById(R.id.formulario_aluno_radio_group);
        generoGroup.setOnCheckedChangeListener(this::radioGroupListener);

    }

    private void radioGroupListener(RadioGroup group, int checkedId) {
        viewModel.definirGeneroPorId(checkedId);
    }

    private WindowInsetsCompat addSystemBarPadding(View view, WindowInsetsCompat insets) {

        final var systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());

        view.setPadding(
                systemBars.left,
                systemBars.top,
                systemBars.right,
                systemBars.bottom
        );

        return insets;

    }

    private void criacao() {
        mostrarCampoId(false);
        viewModel.modoFormulario.setValue(ModoFormulario.CRIACAO);
        viewModel.generoSelecionado.setValue(GeneroEnum.MASCULINO);
    }

    private void edicao(Aluno aluno) {
        mostrarCampoId(true);
        viewModel.modoFormulario.setValue(ModoFormulario.EDICAO);
        viewModel.generoSelecionado.setValue(aluno.genero());
        preencherCamposFomulario(aluno);
    }

    private void mostrarCampoId(boolean mostrarCampo) {
        final View campoId = findViewById(R.id.formulario_aluno_id_layout);
        campoId.setVisibility(mostrarCampo ? View.VISIBLE : View.GONE);
    }

    private ModoFormulario getModoFormulario() {
        return IntentCompat
                .getSerializableExtra(getIntent(), "modoFormulario", ModoFormulario.class);
    }

    private Aluno getAlunoEdicao() {
        return IntentCompat.getSerializableExtra(getIntent(), "aluno", Aluno.class);
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

    private void setLabelAppbar(String label) {
        final var actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle(label);
        }
    }

    private void setLabelBotaoSalvar(String label) {
        final Button botao = findViewById(R.id.formulario_aluno_botao_salvar);
        botao.setText(label);
    }

}