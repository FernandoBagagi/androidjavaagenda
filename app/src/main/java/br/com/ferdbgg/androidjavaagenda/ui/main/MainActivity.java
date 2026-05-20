package br.com.ferdbgg.androidjavaagenda.ui.main;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;

import java.util.List;

import br.com.ferdbgg.androidjavaagenda.R;
import br.com.ferdbgg.androidjavaagenda.models.pojos.Aluno;
import br.com.ferdbgg.androidjavaagenda.ui.formulario.FormularioAlunoActivity;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerAlunosView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        EdgeToEdge.enable(this);

        setContentView(R.layout.activity_main);

        ViewCompat.setOnApplyWindowInsetsListener(
                findViewById(R.id.main),
                this::addSystemBarPadding
        );

        final MaterialToolbar toolbar = findViewById(R.id.main_top_app_bar);
        setSupportActionBar(toolbar);

        recyclerAlunosView = findViewById(R.id.main_lista_alunos);
        // Define como lista vertical
        recyclerAlunosView.setLayoutManager(new LinearLayoutManager(this));

        final MainViewModel viewModel = new ViewModelProvider(this).get(MainViewModel.class);

        viewModel
                .getAlunosLiveData()
                .observe(this, this::alunosLiveDataObserver);

        viewModel.carregarAlunos();

        final ExtendedFloatingActionButton fabAddAluno = findViewById(R.id.main_floating_action_button);
        fabAddAluno.setOnClickListener(this::abrirFormularioEmModoCriacao);

    }

    private WindowInsetsCompat addSystemBarPadding(View view, WindowInsetsCompat insets) {

        Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());

        // Adiciona padding na parte de baixo igual à altura da barra de navegação do celular
        view.setPadding(0, 0, 0, systemBars.bottom);

        return insets;

    }

    private void abrirFormularioEmModoEdicao(Aluno alunoClicado) {

        final var intent = new Intent(this, FormularioAlunoActivity.class);
        intent.putExtra("aluno", alunoClicado);
        startActivity(intent);

    }

    private void abrirFormularioEmModoCriacao(View view) {

        final var intent = new Intent(this, FormularioAlunoActivity.class);
        startActivity(intent);

    }

    private void alunosLiveDataObserver(List<Aluno> alunos) {

        final var listaAlunosAdapter = new ListaAlunosAdapter(
                alunos,
                this::abrirFormularioEmModoEdicao
        );

        recyclerAlunosView.setAdapter(listaAlunosAdapter);

    }

}
