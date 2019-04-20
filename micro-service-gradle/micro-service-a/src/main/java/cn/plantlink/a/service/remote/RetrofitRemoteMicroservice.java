package cn.plantlink.a.service.remote;

import cn.plantlink.a.config.RemoteServiceProperties;
import io.opentracing.Tracer;
import io.opentracing.contrib.okhttp3.TracingInterceptor;
import okhttp3.OkHttpClient;
import okhttp3.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

import javax.annotation.PostConstruct;

@Service
public class RetrofitRemoteMicroservice {

    private static final Logger logger = LoggerFactory.getLogger(RetrofitRemoteMicroservice.class);

    @Autowired
    private RemoteServiceProperties remoteServiceProperties;

    @Autowired
    private Tracer tracer;

    private MicroserviceBClientApi bClientApi;
    private MicroserviceCClientApi cClientApi;
    private MicroserviceDClientApi dClientApi;

    @PostConstruct
    public void init() {

        Retrofit bClientRetrofit = new Retrofit.Builder()
                .baseUrl(remoteServiceProperties.getB())
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        bClientApi = bClientRetrofit.create(MicroserviceBClientApi.class);

        Retrofit cClientRetrofit = new Retrofit.Builder()
                .baseUrl(remoteServiceProperties.getC())
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        cClientApi = cClientRetrofit.create(MicroserviceCClientApi.class);

        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.addInterceptor(chain -> {
            Response response = chain.proceed(chain.request());
            // do anything with response here
            logger.info("okhttp3 add new interceptor");
            return response;
        });

        // customized opentracing
        OkHttpClient client = TracingInterceptor.addTracing(builder, tracer);

        Retrofit dClientRetrofit = new Retrofit.Builder()
                .baseUrl(remoteServiceProperties.getD())
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();
        dClientApi = dClientRetrofit.create(MicroserviceDClientApi.class);
    }

    public String bClientRest() throws Exception {
        return bClientApi.rest().execute().body();
    }

    public String cClientRest() throws Exception {
        return cClientApi.rest().execute().body();
    }

    public String dClientRest() throws Exception {
            return dClientApi.rest().execute().body();
    }
}
