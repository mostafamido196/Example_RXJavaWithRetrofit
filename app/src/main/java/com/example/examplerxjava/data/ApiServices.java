package com.example.examplerxjava.data;

import com.example.examplerxjava.pojo.PostModel;

import java.util.List;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiServices {
    String URI = "https://jsonplaceholder.typicode.com/posts";

    @GET("posts")
    Observable<List<PostModel>> getPosts();

}
