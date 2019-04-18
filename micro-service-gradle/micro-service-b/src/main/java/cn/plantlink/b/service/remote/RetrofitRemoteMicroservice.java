package cn.plantlink.b.service.remote;

import cn.plantlink.b.config.MicroserviceBProperties;
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
    private MicroserviceBProperties bProperties;

    private MicroserviceDClientApi dClientApi;

    @PostConstruct
    public void init() {
        Retrofit dClientRetrofit = new Retrofit.Builder()
                .baseUrl(bProperties.getDBaseUrl())
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        dClientApi = dClientRetrofit.create(MicroserviceDClientApi.class);
    }

    public String dClientRest() throws Exception {
        return dClientApi.rest().execute().body();
    }
}
