package com.example.task1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import java.util.List;
import io.reactivex.disposables.CompositeDisposable;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    public static final String BASE_URL = "http://www.androidbegin.com/tutorial/";
    private RecyclerView mRecyclerView;
    private dataAdapter mAdapter = null;
    private CompositeDisposable mCompositeDisposable;
    private List<apiRepo> l;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mCompositeDisposable = new CompositeDisposable();
        initRecyclerView();
        loadJSON();
    }

    private void loadJSON() {
        requestInterface request = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(requestInterface.class);

        Call<apiRepoList> call = request.register();

        call.enqueue(new Callback<apiRepoList>() {
            @Override
            public void onResponse(Call<apiRepoList> call, Response<apiRepoList> response) {

                if(response.isSuccessful()) {
                    if(response.body()!=null){
                        apiRepoList x = response.body();
                        l = x.getFinalList();
                        mAdapter = new dataAdapter(l);
                        mRecyclerView.setAdapter(mAdapter);
                        for (int i=0;i<10;i++)
                            Log.e("name", String.valueOf(l.get(i).getFlag()));
                    }
                }
                else {
                    Log.e("error","in else part");
                }
            }

            @Override
            public void onFailure(Call<apiRepoList> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Error" + t.getLocalizedMessage(), Toast.LENGTH_LONG).show();

            }
        });
    }

    private void initRecyclerView() {
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mCompositeDisposable.clear();
    }
}
