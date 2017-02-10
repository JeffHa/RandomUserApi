package com.funsies.jeffrey.randomuserapi;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.funsies.jeffrey.randomuserapi.data.Person;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

class RandomUserFrontPageAdapter extends
        RecyclerView.Adapter<RandomUserFrontPageAdapter.RandomUserFrontPageViewHolder> {

    private LayoutInflater inflater;
    private List<Person> listOfRandomPeople;
    private Context context;

    RandomUserFrontPageAdapter(List<Person> listOfRandomPeople, Context context) {
        this.listOfRandomPeople = listOfRandomPeople;
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public RandomUserFrontPageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.viewholder_random_user_front_page, parent, false);
        return new RandomUserFrontPageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final RandomUserFrontPageViewHolder holder, int position) {
        Person personBeingBoundToViewHolder = listOfRandomPeople.get(position);

        holder.userNameTextView.setText(personBeingBoundToViewHolder.getName().toString());

        Glide.with(context)
                .load(personBeingBoundToViewHolder.getPicture().getMedium())
                .into(holder.userImageView);
    }

    @Override
    public int getItemCount() {
        return listOfRandomPeople.size();
    }

    class RandomUserFrontPageViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener {

        @BindView(R.id.vh_random_user_name_textView)
        TextView userNameTextView;

        @BindView(R.id.vh_random_user_imageView)
        ImageView userImageView;

        RandomUserFrontPageViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            Intent intent = new Intent(view.getContext(),
                    RandomUserDetailedPageActivity.class);
            intent.putExtra(RandomUserDetailedPageActivity.KEY_FOR_PERSON_EXTRA,
                    listOfRandomPeople.get(getAdapterPosition()));

            view.getContext().startActivity(intent);
        }
    }
}
