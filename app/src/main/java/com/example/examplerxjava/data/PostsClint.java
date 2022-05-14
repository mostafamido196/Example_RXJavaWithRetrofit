package com.example.examplerxjava.data;

import com.example.examplerxjava.pojo.PostModel;

import java.util.List;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.Call;
import retrofit2.Retrofit;
//import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class PostsClint {
    public static final String BASE_URL = "https://jsonplaceholder.typicode.com/";
    private static ApiServices apiServices;
    private static PostsClint INSTANCE;

    private PostsClint() {
    }

    public static PostsClint getINSTANCE() {
        if (INSTANCE == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava3CallAdapterFactory.create())// ده بيحول الCall  اللي راجع إلى RxJava (observable)
                    .build();
            apiServices = retrofit.create(ApiServices.class);

            INSTANCE = new PostsClint();
        }
        return INSTANCE;
    }

    public Observable<List<PostModel>> getPosts(){
        return apiServices.getPosts();
    }
}
