package cn.plantlink.a.service.remote;

import retrofit2.Call;
import retrofit2.http.GET;

public interface MicroserviceBClientApi {

    @GET("/api/v1/b/rest")
    Call<String> rest();
}
