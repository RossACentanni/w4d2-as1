package com.example.w4d2_as1;

import com.example.w4d2_as1.Entities.BookResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface BookAPI {

    String BASE_URL = "https://www.googleapis.com/books/v1/";

    //{search term}&key={YOUR_API_KEY}
    @GET("volumes")
    Call<BookResponse> searchForBook(@Query("q") String query, @Query("key") String API_KEY);

}
