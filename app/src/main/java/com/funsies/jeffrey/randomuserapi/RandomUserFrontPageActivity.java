package com.funsies.jeffrey.randomuserapi;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.funsies.jeffrey.randomuserapi.data.Person;
import com.funsies.jeffrey.randomuserapi.data.RandomUserResponse;
import com.funsies.jeffrey.randomuserapi.retrofit.RandomUserService;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import timber.log.Timber;

public class RandomUserFrontPageActivity extends AppCompatActivity {

    private static final Integer AMOUNT_OF_RANDOM_PEOPLE_TO_LOAD = 40;
    private static final int NUMBER_OF_COLUMNS_IN_GRID = 2;
    private static final String RANDOM_USER_API_BASE_URL = "https://randomuser.me/";

    @BindView(R.id.random_user_front_page_recyclerView)
    RecyclerView randomUserRecyclerView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_random_user_front_page);
        ButterKnife.bind(this);

        Retrofit randomUserRetrofitInstance = new Retrofit.Builder()
                .baseUrl(RANDOM_USER_API_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        RandomUserService randomUserService = randomUserRetrofitInstance
                .create(RandomUserService.class);

        randomUserService.getRandomPeople(AMOUNT_OF_RANDOM_PEOPLE_TO_LOAD)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::populateRecyclerView,
                        this::handleErrorGettingRandomPeople);
    }

    private void populateRecyclerView(RandomUserResponse response) {
        List<Person> listOfPersons = response.getResults();

        if (listOfPersons == null || listOfPersons.isEmpty()) {
            Timber.d("There was a response, but it was empty.");
            return;
        }

        Timber.d("Got people");

        RandomUserFrontPageAdapter randomUserFrontPageAdapter =
                new RandomUserFrontPageAdapter(listOfPersons, this);

        randomUserRecyclerView
                .setLayoutManager(new GridLayoutManager(RandomUserFrontPageActivity.this,
                        NUMBER_OF_COLUMNS_IN_GRID));

        randomUserRecyclerView.setAdapter(randomUserFrontPageAdapter);
    }

    private void handleErrorGettingRandomPeople(Throwable t) {
        Timber.e(t);
        Toast.makeText(this, "Error getting random people", Toast.LENGTH_SHORT).show();
    }
}
