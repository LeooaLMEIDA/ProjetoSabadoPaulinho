package com.example.sabadopizzas;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sabadopizzas.adapter.AdapterSabores;
import com.example.sabadopizzas.models.Pedido;
import com.example.sabadopizzas.utils.Globais;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private Spinner spSabores;
    private ListView lvSabores;
    private Button btRemovePedido;
    private CheckBox cbBorda;
    private CheckBox cbRefrigerante;
    private TextView tvSeuPedido;
    private String saborSelecionado;
    private Double valorPedido;
    private Double valorPizza = 0.00;
    private Double valorBorda = 0.00;
    private Double valorRefrigerante = 0.00;
    private Integer quantidadeSabores;
    private Pedido pedido = new Pedido();
    private Integer itemSelecionado = -1 ;
    private String descQtdSabores;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spSabores = findViewById(R.id.spSabores);
        lvSabores = findViewById(R.id.lvSabores);
        btRemovePedido = findViewById(R.id.btRemovePedido);
        cbBorda = findViewById(R.id.cbBorda);
        cbRefrigerante = findViewById(R.id.cbRefrigerante);
        tvSeuPedido = findViewById(R.id.tvSeuPedido);

        String[] vetorSabores= new String[]{"","Margherita", "Calabresa", "Quatro Queijos",
                "Pepperoni", "Frango com Catupiry", "Portuguesa", "Napolitana", "Muçarela",
                "Alho e Óleo", "Bacon com Milho", "Atum", "Palmito", "Rúcula com Tomate Seco",
                "Camarão", "Provolone", "Vegetariana", "Baiana", "Cheddar com Batata Palha",
                "Frango com Requeijão"};

        ArrayAdapter adapter = new ArrayAdapter(this,
                android.R.layout.simple_list_item_1, vetorSabores);

        spSabores.setAdapter(adapter);

        if (Globais.listaSabores == null) {
            Globais.listaSabores = new ArrayList<>();
        }

        spSabores.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                saborSelecionado = (String) spSabores.getItemAtPosition(i);

                descQtdSabores = "Não é permitido inserir mais de " +
                        String.valueOf(quantidadeSabores) + " sabor(es) no tamanho "
                        + pedido.getTamanho();

                if(!saborSelecionado.equals("")){
                    if (Globais.listaSabores.size() < quantidadeSabores){
                        Globais.listaSabores.add(saborSelecionado);
                        jogarNaLista(Globais.listaSabores);
                        atualizaPedido();
                    }
                    else{
                        Toast.makeText(MainActivity.this, descQtdSabores,
                                Toast.LENGTH_SHORT).show();
                    }
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        lvSabores.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                itemSelecionado = position;
            }
        });
    }

    public void selecionarOpcao(View view) {
        boolean selecionado = ((RadioButton) view).isChecked();

        switch (view.getId()) {
            case R.id.rbTamPequena:
                if (selecionado){
                    pedido.setTamanho("Pequena");
                    quantidadeSabores = 1;
                    valorPizza = 20.00;
                    Toast.makeText(this, "Pizza Pequena - Escolha 1 Sabor", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.rbTamMedia:
                if (selecionado){
                    pedido.setTamanho("Média");
                    quantidadeSabores = 2;
                    valorPizza = 30.00;
                    Toast.makeText(this, "Pizza Média - Escolha 2 Sabores", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.rbTamGrande:
                if (selecionado){
                    pedido.setTamanho("Grande");
                    Toast.makeText(this, "Pizza Grande - Escolha 4 Sabores", Toast.LENGTH_SHORT).show();
                    quantidadeSabores = 4;
                    valorPizza = 40.00;
                }
                break;
        }
        atualizaPedido();
    }

    public void jogarNaLista(ArrayList<String> lista) {
        AdapterSabores adapter = new AdapterSabores(this, lista);
        lvSabores.setAdapter(adapter);
        Toast.makeText(this,"Sabor Salvo com Sucesso",Toast.LENGTH_LONG).show();
        atualizaPedido();
    }

    public void validaBorda(View view) {
        if (cbBorda.isChecked()) {
            pedido.setBorda(true);
            valorBorda = 10.00;
        }
        else{
            pedido.setBorda(false);
            valorBorda = 0.00;
        }
        atualizaPedido();
    }

    public void validaRefrigerante(View view) {
        if (cbRefrigerante.isChecked()){
            pedido.setRefrigerante(true);
            valorRefrigerante = 5.00;
        }
        else{
            pedido.setRefrigerante(false);
            valorRefrigerante = 0.00;
        }
        atualizaPedido();
    }

    public void removeSabor(View view) {
        if (itemSelecionado != -1){
            Globais.listaSabores.remove(itemSelecionado);
            jogarNaLista(Globais.listaSabores);
            itemSelecionado = -1;
        }
        atualizaPedido();
    }

    public void atualizaPedido(){
        String mensagem = "Pizza " + pedido.getTamanho() + " com os sabores: \n";
        for (String sabor : Globais.listaSabores) {
            mensagem += "- " + sabor + "\n";
        }

        if (pedido.isBorda()) {
            mensagem += "Com borda e ";
        }
        else{
            mensagem += "Sem borda e ";
        }

        if (pedido.isRefrigerante()) {
            mensagem += "Com refrigerante \n";
        }
        else{
            mensagem += "Sem refrigerante \n";
        }

        mensagem += "Total Pedido: R$ " + String.valueOf(
                valorPizza + valorBorda + valorRefrigerante);

        tvSeuPedido.setText(mensagem);
    }
}