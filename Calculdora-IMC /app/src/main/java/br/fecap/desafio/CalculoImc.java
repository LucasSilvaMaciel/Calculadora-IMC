package br.fecap.desafio;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class CalculoImc extends AppCompatActivity {

    private EditText editTextPeso, editTextAltura;
    private Button btnCalcular, btnFechar, btnLimpar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculo_imc);

        editTextPeso = findViewById(R.id.editTextDigitePeso);
        editTextAltura = findViewById(R.id.editTextDigiteAltura);
        btnCalcular = findViewById(R.id.buttonCalcularIMC);
        btnFechar = findViewById(R.id.buttonFechar);
        btnLimpar = findViewById(R.id.buttonLimpar);

        btnCalcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calcularIMC();
            }
        });

        btnFechar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CalculoImc.this, MainActivity.class);
                startActivity(intent);
            }
        });

        btnLimpar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editTextPeso.setText("");
                editTextAltura.setText("");
            }
        });
    }

    private void calcularIMC() {
        String pesoStr = editTextPeso.getText().toString();
        String alturaStr = editTextAltura.getText().toString();

        if (pesoStr.isEmpty() || alturaStr.isEmpty()) {
            showAlert("Erro", "Por favor, insira peso e altura válidos.");
            return;
        }

        double peso, altura;

        // Verificando se o peso e altura são números válidos
        try {
            peso = Double.parseDouble(pesoStr);
            altura = Double.parseDouble(alturaStr);
        } catch (NumberFormatException e) {
            showAlert("Erro", "Por favor, insira apenas números decimais.");
            return;
        }

        if (altura == 0) {
            showAlert("Erro", "A altura não pode ser zero.");
            return;
        }

        double imc = peso / (altura * altura);
        String categoria;
        Class<?> activityDestino;

        if (imc < 18.5) {
            categoria = "Abaixo do peso";
            activityDestino = AbaixoDoPesoActivity.class;
        } else if (imc < 25) {
            categoria = "Peso normal";
            activityDestino = PesoNormalActivity.class;
        } else if (imc < 30) {
            categoria = "Sobrepeso";
            activityDestino = SobrepesoActivity.class;
        } else if (imc < 35) {
            categoria = "Obesidade Grau 1";
            activityDestino = Obesidade1Activity.class;
        } else if (imc < 40) {
            categoria = "Obesidade Grau 2";
            activityDestino = Obesidade2Activity.class;
        } else {
            categoria = "Obesidade Grau 3";
            activityDestino = Obesidade3Activity.class;
        }

        Intent intent = new Intent(CalculoImc.this, activityDestino);
        intent.putExtra("peso", peso);
        intent.putExtra("altura", altura);
        intent.putExtra("imc", imc);
        intent.putExtra("categoria", categoria);
        startActivity(intent);
    }

    private void showAlert(String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(CalculoImc.this);
        builder.setTitle(title)
                .setMessage(message)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                    }
                })
                .setCancelable(false);

        AlertDialog alertDialog = builder.create();
        alertDialog.setIcon(android.R.drawable.ic_dialog_alert);
        alertDialog.getWindow().setBackgroundDrawableResource(android.R.color.white);
        alertDialog.show();

        Button positiveButton = alertDialog.getButton(DialogInterface.BUTTON_POSITIVE);
        positiveButton.setTextColor(getResources().getColor(R.color.VerdeClaro));
        positiveButton.setBackgroundColor(getResources().getColor(R.color.Branco));
    }
}
