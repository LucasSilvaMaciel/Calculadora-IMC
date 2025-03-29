package br.fecap.desafio;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Encontrar o botão pelo ID
        Button btnCalculadoraIMC = findViewById(R.id.ButtonCalculadoraIMC);

        // Configurar o clique do botão
        btnCalculadoraIMC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Criar a intenção para abrir a nova atividade
                Intent intent = new Intent(MainActivity.this, CalculoImc.class);
                startActivity(intent);
            }
        });
    }
}