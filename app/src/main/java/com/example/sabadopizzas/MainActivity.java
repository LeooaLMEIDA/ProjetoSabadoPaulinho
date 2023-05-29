package com.example.sabadopizzas;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Spinner  spSabores;
    private ListView lvSabores;
    private Button   btRemovePedido;
    private CheckBox cbBorda;
    private CheckBox cbRefrigerante;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void selecionarOpcao(View view) {
        boolean selecionado = ((RadioButton) view).isChecked();

        switch (view.getId()) {
            case R.id.rbTamPequena:
                if (selecionado)
                    Toast.makeText(this, "Selecionou a PEQUENA!", Toast.LENGTH_SHORT).show();
                break;
            case R.id.rbTamMedia:
                if (selecionado)
                    Toast.makeText(this, "Selecionou a MÉDIA!", Toast.LENGTH_SHORT).show();
                break;
            case R.id.rbTamGrande:
                if (selecionado)
                    Toast.makeText(this, "Selecionou a GRANDE!", Toast.LENGTH_SHORT).show();
                break;
        }
    }

}