package cn.plantlink.a.service.remote;

import cn.plantlink.a.config.MicroserviceAProperties;
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
    private MicroserviceAProperties aProperties;

    private MicroserviceBClientApi bClientApi;
    private MicroserviceCClientApi cClientApi;

    @PostConstruct
    public void init() {
        Retrofit bClientRetrofit = new Retrofit.Builder()
                .baseUrl(aProperties.getBBaseUrl())
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        bClientApi = bClientRetrofit.create(MicroserviceBClientApi.class);

        Retrofit cClientRetrofit = new Retrofit.Builder()
                .baseUrl(aProperties.getCBaseUrl())
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        cClientApi = cClientRetrofit.create(MicroserviceCClientApi.class);
    }

    public String bClientRest() throws Exception {
        return bClientApi.rest().execute().body();
    }

    public String cClientRest() throws Exception {
        return cClientApi.rest().execute().body();
    }
}
