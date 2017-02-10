package com.funsies.jeffrey.randomuserapi;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.funsies.jeffrey.randomuserapi.data.Person;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

public class RandomUserDetailedPageActivity extends AppCompatActivity {

    public static final String KEY_FOR_PERSON_EXTRA = "detailedPersonInfo";

    @BindView(R.id.random_user_detailed_imageView)
    CircleImageView userPortraitCircleImageView;

    @BindView(R.id.random_user_detailed_name_textView)
    TextView nameTextView;

    @BindView(R.id.random_user_detailed_username_textView)
    TextView usernameTextView;

    @BindView(R.id.random_user_detailed_location_textView)
    TextView locationTextView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_random_user_detailed_page);
        ButterKnife.bind(this);
        Person person = getIntent().getParcelableExtra(KEY_FOR_PERSON_EXTRA);

        Glide.with(this)
                .load(person.getPicture().getLarge())
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .skipMemoryCache(true)
                .dontAnimate()
                .into(userPortraitCircleImageView);

        nameTextView.setText(String.format("Name: %s", person.getName().toString()));
        usernameTextView.setText(String.format("Username: %s", person.getLogin().getUsername()));
        locationTextView.setText(String.format("Location: %s", person.getLocation().toString()));
    }
}
