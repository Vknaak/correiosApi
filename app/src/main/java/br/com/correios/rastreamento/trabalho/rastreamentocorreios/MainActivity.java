package br.com.correios.rastreamento.trabalho.rastreamentocorreios;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private EditText edtRastreamento;
    private TextView retorno;
    private Retrofit retrofit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtRastreamento = (EditText) findViewById(R.id.edtRastreamento);
        retorno = (TextView) findViewById(R.id.retorno);

        retrofit = new Retrofit.Builder()
                .baseUrl("https://api.postmon.com.br/v1/rastreio/ect/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public void solicitarEndereco(View view) {

        String codRastreamento = edtRastreamento.getText().toString();
        CorreioService correios = retrofit.create(CorreioService.class);

        Call<Encomenda> call = correios.getRastreamento(codRastreamento);

        call.enqueue(new Callback<Encomenda>() {
            @Override
            public void onResponse(Call<Encomenda> call, Response<Encomenda> response) {
                if (response.isSuccessful()) {
                    Encomenda encomenda = response.body();
                    String strEndereço = "";

                    for (Historico hist : encomenda.gethistorico()) {
                        strEndereço +=
                                "Local: " + hist.getLocal() + "\n" +
                                        "Data: " + hist.getData() + "\n" +
                                        "Situação: " + hist.getSituacao() + "\n\n";
                    }

                    retorno.setText(strEndereço);
                }
            }

            @Override
            public void onFailure(Call<Encomenda> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Não foi possível realizar a requisição", Toast.LENGTH_SHORT).show();
            }
        });
    }
}