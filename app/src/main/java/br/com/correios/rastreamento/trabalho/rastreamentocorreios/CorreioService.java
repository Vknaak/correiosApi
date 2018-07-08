package br.com.correios.rastreamento.trabalho.rastreamentocorreios;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface CorreioService {

    @GET("{codRastreamento}")
    Call<Encomenda> getRastreamento(@Path("codRastreamento") String codRastreamento);
}