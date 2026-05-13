package br.com.ferdbgg.androidjavaagenda;

import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.appbar.MaterialToolbar;

public class FormularioAlunoActivity extends AppCompatActivity {

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

}