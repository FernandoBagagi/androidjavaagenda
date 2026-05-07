package br.com.ferdbgg.androidjavaagenda;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.Arrays;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

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

        Toast.makeText(this, "Esse é um alerta", Toast.LENGTH_LONG).show();

    }
}
