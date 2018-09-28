package deamon999.gmail.com.mytaxes;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Taxes {

    @GET("/v1/declaration/")
    Call<TaxesResualt> getPersons(@Query("q") String search);

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://public-api.nazk.gov.ua/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();
}
