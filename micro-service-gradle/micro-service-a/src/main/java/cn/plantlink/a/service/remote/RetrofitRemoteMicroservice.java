package cn.plantlink.a.service.remote;

import cn.plantlink.a.config.RemoteServiceProperties;
import io.opentracing.Span;
import io.opentracing.Tracer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

import javax.annotation.PostConstruct;

/**
 * TODO opentracing jaeger sapns issue, just 2 spans
 */
@Service
public class RetrofitRemoteMicroservice {

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

        Retrofit dClientRetrofit = new Retrofit.Builder()
                .baseUrl(remoteServiceProperties.getD())
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        dClientApi = dClientRetrofit.create(MicroserviceDClientApi.class);
    }

    public String bClientRest() throws Exception {
        return bClientApi.rest().execute().body();
    }

    public String cClientRest() throws Exception {
        return cClientApi.rest().execute().body();
    }

    // traced work happens between start() and deactivate();
    public String dClientRest() throws Exception {

        Span serverSpan = tracer.activeSpan();
        Span span = tracer.buildSpan("localSpan")
                .asChildOf(serverSpan.context())
                .start();

        try {
            return dClientApi.rest().execute().body();
        } finally {
            span.finish();
        }
    }
}
