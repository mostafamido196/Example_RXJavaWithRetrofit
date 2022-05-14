package com.example.examplerxjava.ui.main;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.examplerxjava.data.PostsClint;
import com.example.examplerxjava.pojo.PostModel;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class PostViewModel extends ViewModel {
    MutableLiveData<List<PostModel>> postMutableLiveData = new MutableLiveData<>();//like array
    private static final String TAG = "getPosts";

    //لو احنا عايزين أول لما يتم إغلاق الصفحه أو الذهاب الى صفخه اخرى -> دمر الاتصال بين ال observable, observer
    CompositeDisposable compositeDisposable = new CompositeDisposable();

    public void getPosts() {
        Observable<List<PostModel>> observable = PostsClint.getINSTANCE().getPosts()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

//        Observer observer = new Observer() {
//            @Override
//            public void onSubscribe(Disposable d) {
//
//            }
//
//            @Override
//            public void onNext(Object value) {
//                postMutableLiveData.setValue((List<PostModel>) value);
//
//            }
//
//            @Override
//            public void onError(Throwable e) {
//                Log.d(TAG, "onError: " + e.getMessage());
//            }
//
//            @Override
//            public void onComplete() {
//
//            }
//        };

        Disposable disposable = observable.subscribe(o -> postMutableLiveData.setValue(o)
                , e -> Log.d(TAG, "getPosts: " + e.getMessage()));// disposable have a connection between observable, observer

        compositeDisposable.add(disposable);


    }

    //onCleared() will execute when activity (which use this class) are destroyed
    @Override
    protected void onCleared() {
        super.onCleared();

        compositeDisposable.clear();
    }
}
