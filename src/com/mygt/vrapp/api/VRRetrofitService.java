package com.mygt.vrapp.api;

import com.mygt.vrapp.api.bean.GameIndexResponse;
import com.mygt.vrapp.api.bean.GameInfoResponse;
import com.mygt.vrapp.api.bean.GameUrlResponse;
import com.mygt.vrapp.api.bean.IndexResponse;
import com.mygt.vrapp.api.bean.MainIndexResponse;
import com.mygt.vrapp.api.bean.MoreGameResponse;
import com.mygt.vrapp.api.bean.SearchGameResponse;
import com.mygt.vrapp.api.bean.SearchIDYSResponse;
import com.mygt.vrapp.api.bean.SearchIDYXResponse;
import com.mygt.vrapp.api.bean.SearchVideoResponse;
import com.mygt.vrapp.api.bean.TypeResponse;
import com.mygt.vrapp.api.bean.VideoByIdResponse;
import com.mygt.vrapp.api.bean.MoreVideoResponse;
import com.mygt.vrapp.api.bean.VideoMoreResponse;

import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created on 2017/5/5.
 *
 * @author pengxinkai
 */

public interface VRRetrofitService {

    /**
     * 1、获取首页精选
     */
    @Headers("api-version:1.0.0")
    @GET("/app/video/index.do")
    Observable<IndexResponse> getHomeIndex();  //

    /**
     * 2、
     */
    @GET("/app/video/videoById.do")
    @Headers("api-version:1.0.0")
    Observable<VideoByIdResponse> getVideoById(@Query("id") String id, @Query("type") String type);   //

    /**
     * 3、影视主页
     */
    @GET("/app/video/mainIndex.do")
    @Headers("api-version:1.0.0")
    Observable<MainIndexResponse> getMainIndex(@Query("type") String type);  //

    /**
     * 4、影视更多
     */
    @GET("/app/video/videoMore.do")
    @Headers("api-version:1.0.0")
    Observable<VideoMoreResponse> getVideoMore(@Query("type") String type, @Query("classification") String classification, @Query("pageNumber") int pageNumber);  //

    /**
     * 5、
     */
    @GET("/app/video/searchVideo.do")
    @Headers("api-version:1.0.0")
    Observable<SearchVideoResponse> searchVideo(@Query("name") String name, @Query("pageNumber") int pageNumber);

    /**
     * 6、 游戏主页
     */
    @GET("/app/game/gameIndex.do")
    @Headers("api-version:1.0.0")
    Observable<GameIndexResponse> getGameIndex();  //

    /**
     * 7.1 单个游戏下载
     */
    @GET("/app/game/findByid.do")
    @Headers("api-version:1.0.0")
    Observable<GameUrlResponse> findGameByIdDownload(@Query("id") String id, @Query("type") String type);

    /**
     * 7.2  单个游戏详情
     */
    @GET("/app/game/findByid.do")
    @Headers("api-version:1.0.0")
    Observable<GameInfoResponse> findGameByIdInfo(@Query("id") String id);  //

    /**
     * 8
     */
    @GET("/app/game/searchGame.do")
    @Headers("api-version:1.0.0")
    Observable<SearchGameResponse> searchGame(@Query("title") String title, @Query("type") String type, @Query("pageNumber") int pageNumber);

    /**
     * 9、
     */
    @GET("/app/advertis/type.do")
    @Headers("api-version:1.0.0")
    Observable<TypeResponse> searchType(@Query("type") String type);

    /**
     * 10.1  影视
     */
    @GET("/app/video/searchId.do")
    @Headers("api-version:1.0.0")
    Observable<SearchIDYSResponse> searchIDYS(@Query("id") String id, @Query("type") String type); //

    /**
     * 10.2  游戏
     */
    @GET("/app/video/searchId.do")
    @Headers("api-version:1.0.0")
    Observable<SearchIDYXResponse> searchIDYX(@Query("id") String id, @Query("type") String type);//


    /**
     * 1.1  首页更多 视频
     * @param type
     */
    @GET("/app/video/more.do")
    @Headers("api-version:1.0.0")
    Observable<MoreVideoResponse> getMoreVideo(@Query("type") int type, @Query("pageNumber") int pageNumber);//

    /**
     * 1.1  首页更多 游戏
     * @param type
     */
    @GET("/app/video/more.do")
    @Headers("api-version:1.0.0")
    Observable<MoreGameResponse> getMoreGame(@Query("type") int type, @Query("pageNumber") int pageNumber); //
}
