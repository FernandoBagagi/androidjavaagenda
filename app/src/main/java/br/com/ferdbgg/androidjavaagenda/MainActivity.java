package br.com.ferdbgg.androidjavaagenda;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        var testeTextView = new TextView(this);
        testeTextView.setText("Teste de TextView");
        setContentView(testeTextView);

        Toast.makeText(this, "Esse é um alerta", Toast.LENGTH_LONG).show();

    }
}
