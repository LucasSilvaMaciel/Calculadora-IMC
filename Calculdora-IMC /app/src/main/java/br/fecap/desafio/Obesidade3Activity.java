package br.fecap.desafio;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Obesidade3Activity extends AppCompatActivity {

    private TextView textViewPeso, textViewAltura, textViewIMC, textViewClassificacao, textViewMensagem;
    private ImageView imageViewFeedback;
    private Button buttonFechar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_obesidade_3);

        // Associando os componentes do layout
        textViewPeso = findViewById(R.id.textViewPeso);
        textViewAltura = findViewById(R.id.textViewAltura);
        textViewIMC = findViewById(R.id.textViewIMC);
        textViewClassificacao = findViewById(R.id.textViewClassificacao);
        textViewMensagem = findViewById(R.id.textViewMensagem);
        imageViewFeedback = findViewById(R.id.imageViewFeedback);
        buttonFechar = findViewById(R.id.buttonFechar);

        // Recebendo os dados via Intent
        Intent intent = getIntent();
        if (intent != null) {
            double peso = intent.getDoubleExtra("peso", 0);
            double altura = intent.getDoubleExtra("altura", 0);
            double imc = intent.getDoubleExtra("imc", 0);

            // Exibindo os dados na tela
            textViewPeso.setText(getString(R.string.label_peso, peso));
            textViewAltura.setText(getString(R.string.label_altura, altura));
            textViewIMC.setText(getString(R.string.label_imc, imc));
            textViewClassificacao.setText(getString(R.string.label_classificacao_obesidade_3));

            // Definindo mensagem motivacional
            textViewMensagem.setText(R.string.mensagem_obesidade_3);

            // Definindo imagem relevante para a categoria
            imageViewFeedback.setImageResource(R.drawable.obesidade_3);
        }

        // Botão para voltar à tela principal
        buttonFechar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Obesidade3Activity.this, MainActivity.class);
                startActivity(intent);
                finish(); // Fecha a atividade atual
            }
        });
    }
}
