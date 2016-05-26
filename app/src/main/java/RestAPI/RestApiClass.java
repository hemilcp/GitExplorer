package RestAPI;

import org.json.JSONArray;

import retrofit.http.Body;
import retrofit.http.DELETE;
import retrofit.http.GET;
import retrofit.http.Headers;
import retrofit.http.POST;
import retrofit.http.PUT;
import retrofit.http.Query;
/**
 * Created by hemil on 5/25/2016.
 */
public interface RestApiClass {

    @Headers("Content-Type: application/json")
    @GET("/")
    JSONArray getRepoList();


    @Headers("Content-Type: application/json")
    @GET("/")
    JSONArray getCommitList();
}
