package cn.plantlink.a.service.remote;

import retrofit2.Call;
import retrofit2.http.GET;

public interface MicroserviceCClientApi {

    @GET("/api/v1/c/rest")
    Call<String> rest();
}
