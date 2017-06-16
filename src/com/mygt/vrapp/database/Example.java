package com.mygt.vrapp.database;

import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.List;
import java.util.concurrent.Callable;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created on 2017/5/20.
 *
 * @author lkuan
 */

public class Example {

    private static final String TAG = "Example";

    // 添加历史记录，不用考虑是否超过8个，数据库已经作了处理
    private void addHistory(VRDbVideo dbVideo) {
        Observable.just(dbVideo)
                .observeOn(Schedulers.io())
                .subscribe(new Action1<VRDbVideo>() {
                    @Override
                    public void call(VRDbVideo dbVideo) {
                        Log.d(TAG, "call: " + Thread.currentThread().getName());
                        VRDbManager.getInstance().insertHistory(dbVideo);
                    }
                });
//                .utilnsubscribe();
    }

    // 添加收藏
    private void addCollect(VRDbVideo dbVideo) {
        Observable.just(dbVideo)
                .observeOn(Schedulers.io())
                .subscribe(new Action1<VRDbVideo>() {
                    @Override
                    public void call(VRDbVideo dbVideo) {
                        Log.d(TAG, "call: " + Thread.currentThread().getName());
                        VRDbManager.getInstance().updateCollect(dbVideo);
                    }
                });
//                .unsubscribe();
    }

    // 查询历史记录
    private void queryHistory() {
        Subscription subscription =
                Observable
                        .fromCallable(new Callable<List<VRDbVideo>>() {
                            @Override
                            public List<VRDbVideo> call() throws Exception {
                                return VRDbManager.getInstance().queryHistory();
                            }
                        })
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Subscriber<List<VRDbVideo>>() {
                            @Override
                            public void onCompleted() {
                                Log.d(TAG, "onCompleted: ");
                            }

                            @Override
                            public void onError(Throwable e) {
                                Log.d(TAG, "onError: " + e.toString());
                            }

                            @Override
                            public void onNext(List<VRDbVideo> vrDbVideos) {
                                Log.d(TAG, "onNext: ******");
                            }
                        });

        //取消
        subscription.unsubscribe();
    }

    // 查询收藏
    private void queryCollect() {
        Observable
                .fromCallable(new Callable<List<VRDbVideo>>() {
                    @Override
                    public List<VRDbVideo> call() throws Exception {
                        return VRDbManager.getInstance().queryCollect();
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<VRDbVideo>>() {
                    @Override
                    public void onCompleted() {
                        Log.d(TAG, "onCompleted: ");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "onError: " + e.toString());
                    }

                    @Override
                    public void onNext(List<VRDbVideo> vrDbVideos) {
                        Log.d(TAG, "onNext: ******");
                    }
                });
    }
}
