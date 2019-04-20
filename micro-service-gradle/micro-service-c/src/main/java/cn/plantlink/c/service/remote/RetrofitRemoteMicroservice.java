package cn.plantlink.c.service.remote;

import cn.plantlink.c.config.RemoteServiceProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

import javax.annotation.PostConstruct;

@Service
public class RetrofitRemoteMicroservice {

    @Autowired
    private RemoteServiceProperties remoteServiceProperties;

    private MicroserviceDClientApi dClientApi;

    @PostConstruct
    public void init() {
        Retrofit dClientRetrofit = new Retrofit.Builder()
                .baseUrl(remoteServiceProperties.getD())
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        dClientApi = dClientRetrofit.create(MicroserviceDClientApi.class);
    }

    public String dClientRest() throws Exception {
        return dClientApi.rest().execute().body();
    }
}
