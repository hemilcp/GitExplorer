package RestAPI;

import com.google.gson.JsonArray;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;

import retrofit.http.Body;
import retrofit.http.DELETE;
import retrofit.http.GET;
import retrofit.http.Headers;
import retrofit.http.POST;
import retrofit.http.PUT;
import retrofit.http.Path;
import retrofit.http.Query;
/**
 * Created by hemil on 5/25/2016.
 */
public interface RestApiClass {

    @Headers("Content-Type: application/json")
    @GET("/orgs/netflix/repos")
    JsonArray getRepoList();
  //  List<JSONObject> getRepoList();

    @Headers("Content-Type: application/json")
    @GET("/repos/Netflix/{name}/commits")
  //  JSONArray getCommitList();
    JsonArray getCommitList(@Path("name") String reponame);

}
