package cn.plantlink.c.service.remote;

import cn.plantlink.c.config.MicroserviceCProperties;
import org.springframework.beans.factory.annotation.Autowired;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

import javax.annotation.PostConstruct;

/**
 * TODO opentracing jaeger sapns issue, just 2 spans
 */
public class RetrofitRemoteMicroservice {

    @Autowired
    private MicroserviceCProperties cProperties;

    private MicroserviceDClientApi dClientApi;

    @PostConstruct
    public void init() {
        Retrofit dClientRetrofit = new Retrofit.Builder()
                .baseUrl(cProperties.getDBaseUrl())
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        dClientApi = dClientRetrofit.create(MicroserviceDClientApi.class);
    }

    public String dClientRest() throws Exception {
        return dClientApi.rest().execute().body();
    }
}
