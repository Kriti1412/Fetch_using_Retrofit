package com.example.task1;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import io.reactivex.Observable;


public interface requestInterface {

   String s = "http://www.androidbegin.com/tutorial/";
   @GET("jsonparsetutorial.txt")

   Call<apiRepoList> register();
}
