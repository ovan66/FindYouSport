package com.bastian.findyousport.views.main.sportList;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bastian.findyousport.R;
import com.bastian.findyousport.data.FirebaseRef;
import com.bastian.findyousport.data.UserData;
import com.bastian.findyousport.models.Event;
import com.bastian.findyousport.views.Constants;
import com.bastian.findyousport.views.details.DetailsActivity;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;

/**
 * A simple {@link Fragment} subclass.
 */
public class SportListFragment extends Fragment {

    public SportListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_sport_list, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RecyclerView recycler = (RecyclerView) view;

        recycler.setHasFixedSize(true);
        recycler.setLayoutManager(new LinearLayoutManager(getContext()));

        DatabaseReference userData = new FirebaseRef().events();

        FirebaseRecyclerAdapter adapter = new FirebaseRecyclerAdapter<Event, EventHolder>(Event.class, R.layout.list_item_post, EventHolder.class, userData) {
            @Override
            protected void populateViewHolder(EventHolder viewHolder, final Event model, int position) {
                viewHolder.setName(model.getSportName());
                viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getActivity(), DetailsActivity.class);
                        intent.putExtra(Constants.NAME_LOCAL, model.getNameLocal());
                        intent.putExtra(Constants.SPORT_NAME, model.getSportName());
                        intent.putExtra(Constants.PRICE, model.getPrice());
                        intent.putExtra(Constants.SCHEDULES, model.getSchedules());
                        intent.putExtra(Constants.LOCATION, model.getLocation());
                        intent.putExtra(Constants.PHONE_NUM, model.getPhoneNum());
                        intent.putExtra(Constants.FACEBOOK, model.getFacebook());
                        intent.putExtra(Constants.EMAIL, model.getEmail());
                        intent.putExtra(Constants.KEY, model.getKey());
                        startActivity(intent);

                    }
                });
            }
        };

        recycler.setAdapter(adapter);
    }

    public static class EventHolder extends RecyclerView.ViewHolder {

        public EventHolder(View itemView) {
            super(itemView);
        }

        public void setName(String name){
            TextView textView = (TextView) itemView.findViewById(R.id.reciverSportTv);
            textView.setText(name);
        }
    }
}
