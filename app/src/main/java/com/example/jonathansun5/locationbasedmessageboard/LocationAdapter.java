package com.example.jonathansun5.locationbasedmessageboard;


import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import static com.example.jonathansun5.locationbasedmessageboard.LocationAdapter.mContext;

public class LocationAdapter extends RecyclerView.Adapter {

    public static Context mContext;
    private ArrayList<Bears> mBears;

    public LocationAdapter(Context context, ArrayList<Bears> bears) {
        mContext = context;
        mBears = bears;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // here, we specify what kind of view each cell should have. In our case, all of them will have a view
        // made from comment_cell_layout
        View view = LayoutInflater.from(mContext).inflate(R.layout.bear_cell_layout, parent, false);
        return new BearViewHolder(view);
    }


    // - get element from your dataset at this position
    // - replace the contents of the view with that element
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        // here, we the comment that should be displayed at index `position` in our recylcer view
        // everytime the recycler view is refreshed, this method is called getItemCount() times (because
        // it needs to recreate every cell).
        Bears bear = mBears.get(position);
        ((BearViewHolder) holder).bind(bear);
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mBears.size();
    }
}

class BearViewHolder extends RecyclerView.ViewHolder {

    // each data item is just a string in this case
    public RelativeLayout mBearBubbleLayout;
    public ImageView mLocationPictureImageView;
    public TextView mLocationNameTextView;
    public TextView mDistanceTextView;

    public BearViewHolder(View itemView) {
        super(itemView);
        mBearBubbleLayout = itemView.findViewById(R.id.bear_cell_layout);
        mLocationPictureImageView = mBearBubbleLayout.findViewById(R.id.location_picture);
        mLocationNameTextView = mBearBubbleLayout.findViewById(R.id.location_name);
        mDistanceTextView = mBearBubbleLayout.findViewById(R.id.distance_info);

        mBearBubbleLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mDistanceTextView.getText() == "less than 10 meters away") {
                    Context oContext = mContext;
                    Intent i = new Intent(oContext, CommentFeedActivity.class);
                    i.putExtra("username", LocationActivity.username);
                    i.putExtra("locationName", mLocationNameTextView.getText());
                    oContext.startActivity(i);
                } else {
                    Toast.makeText(mContext, "You must be within 10 meters of a landmark to access its feed", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    void bind(Bears bear) {
        mLocationPictureImageView.setImageResource(bear.landmarkPicture);
        mLocationNameTextView.setText(bear.landmarkName);
        mDistanceTextView.setText(bear.coordinates);
    }
}
