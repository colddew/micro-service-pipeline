package cn.plantlink.b.service.remote;

import retrofit2.Call;
import retrofit2.http.GET;

public interface MicroserviceDClientApi {

    @GET("/api/v1/d/rest")
    Call<String> rest();
}
