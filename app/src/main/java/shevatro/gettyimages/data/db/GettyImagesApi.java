package shevatro.gettyimages.data.db;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;
import shevatro.gettyimages.data.gson.SearchImages;
import shevatro.gettyimages.other.Setting;

public interface GettyImagesApi {
    @Headers("Api-Key: " + Setting.API_KEY)
    @GET("search/images?page_size=1&fields=thumb")
    Call<SearchImages> images(@Query("phrase") String phrase);

}