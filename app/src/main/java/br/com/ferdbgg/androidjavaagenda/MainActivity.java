package br.com.ferdbgg.androidjavaagenda;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        ViewCompat.setOnApplyWindowInsetsListener(
                findViewById(R.id.main),
                this::adicionarPaddingSystemBarBottom);

        final MaterialToolbar toolbar = findViewById(R.id.main_top_app_bar);
        setSupportActionBar(toolbar);

        final var nomesAlunos =
                Arrays.asList("Maria", "João", "Antônio", "José", "Ana Clara", "Emília",
                        "Ana Maria", "Felipe", "Alan", "Carla", "Luan", "Bernardo", "Ricardo",
                        "Daniela", "Omar", "Vitória", "Samara", "Roberto", "Damião", "Júlio",
                        "Tadeu", "Tamires", "Talita", "Gabriel", "Fabrício", "Jamile", "Heitor"
                );

        final var listaAlunosView = (ListView) findViewById(R.id.main_lista_alunos);
        listaAlunosView.setAdapter(
                new ArrayAdapter<>(
                        this,
                        android.R.layout.simple_list_item_1,
                        nomesAlunos
                )
        );

        final ExtendedFloatingActionButton fabAddAluno = findViewById(R.id.main_floating_action_button);
        fabAddAluno.setOnClickListener(this::onClickFabAddAluno);

        Toast.makeText(this, "Esse é um alerta", Toast.LENGTH_LONG).show();

    }

    private WindowInsetsCompat adicionarPaddingSystemBarBottom(View view, WindowInsetsCompat insets) {
        Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());

        // Adiciona padding na parte de baixo igual à altura da barra de navegação do celular
        view.setPadding(0, 0, 0, systemBars.bottom);

        return insets;
    }

    private void onClickFabAddAluno(View view) {
        Toast.makeText(MainActivity.this, "Clicou em adicionar!", Toast.LENGTH_SHORT).show();
    }
}
