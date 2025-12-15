package com.example.alcoolougasolina;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.textfield.TextInputEditText;

public class MainActivity extends AppCompatActivity {

    private TextInputEditText editPrecoAlcool, editPrecoGasolina;
    private TextView textResultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        editPrecoAlcool = findViewById(R.id.editPrecoAlcool);
        editPrecoGasolina = findViewById(R.id.editPrecoGasolina);
        textResultado = findViewById(R.id.textResultado);
    }

    public void calcularPreco(View view){

        //recuperar valores
        String precoAlcool = editPrecoAlcool.getText().toString();
        String precoGasolina = editPrecoGasolina.getText().toString();

        //validar os campos
        Boolean camposValidados = validarCampos(precoAlcool, precoGasolina);

        if(camposValidados){

            //convertendo valores
            Double valorAlcool = Double.parseDouble(precoAlcool);
            Double valorGasosa = Double.parseDouble(precoGasolina);

            textResultado.setText(calculo(valorAlcool, valorGasosa));


        }else {
            textResultado.setText("Preencha os preços primeiro!");
        }



    }

    public Boolean validarCampos(String precoAlcool, String precoGasolina){

        Boolean camposValidados = true;

        if(precoAlcool == null || precoAlcool.equals("") || precoGasolina == null || precoGasolina.equals("")){
            camposValidados = false;
        }

        return camposValidados;

    }

    public String calculo(Double valorAlcool, Double valorGasosa){
        String texto = "";

        Double resultado = valorAlcool / valorGasosa;

        if(resultado >= 0.7){
            texto = "Melhor utilizar Gasolina";
        }else {
            texto = "Melhor utilizar Álcool";
        }

        return texto;
    }
}