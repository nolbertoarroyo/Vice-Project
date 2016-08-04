package com.test.vice20.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.test.vice20.Models.Article;
import com.test.vice20.Models.Item;
import com.test.vice20.R;

import java.util.List;

/**
 * Created by kitty on 8/2/16.
 */
public class CustomRecyclerViewAdapter extends RecyclerView.Adapter<CustomRecyclerViewAdapter.SampleViewHolder>{

    private static final String TAG = "Adapter";
    private List<Article> data;

    public class SampleViewHolder extends RecyclerView.ViewHolder {

        private ImageView imageView;
        private TextView titleTextView;
        private TextView categoryTextView;
        private TextView previewTextView;

        public SampleViewHolder(final View itemView) {
            super(itemView);

            this.imageView = (ImageView) itemView.findViewById(R.id.list_item_image);
            this.titleTextView = (TextView) itemView.findViewById(R.id.list_item_title);
            this.categoryTextView = (TextView) itemView.findViewById(R.id.list_item_category);
            this.previewTextView = (TextView) itemView.findViewById(R.id.list_item_preview);

            /**
             * We set a click listener on the item view itself. The whole item not the image or textView.
             *
             * Therefore, whole item is clickable now.
             */
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    // ToDo: need to update to swap to details fragment
                    Log.d(TAG, "Clicked on item: " + getLayoutPosition());
                }
            });
        }
    }

    public CustomRecyclerViewAdapter(List<Article> inComingData){
        this.data = inComingData;
    }

    @Override
    public SampleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // Get context from parent ViewGroup
        Context context = parent.getContext();

        // Get layoutInflater, which will inflate our custom list item layout for us
        LayoutInflater inflater = LayoutInflater.from(context);

        /**
         * Inflate the custom list item layout. The view returned back is our top level
         * view. If you look at step 0, you'll see our top level layout is LinearLayout.
         *
         * We pass this LinearLayout view to our SampleViewHolder so we can pull our
         * ImageView and TextView out of it via their id's
         */
        View listItemLayout = inflater.inflate(R.layout.list_item, parent, false);

        // Return a new SampleViewHolder instance
        SampleViewHolder viewHolder = new SampleViewHolder(listItemLayout);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(SampleViewHolder holder, int position) {
        // Get our data item for the current position from the data list
        Article dataItem = data.get(position);

        /**
         * Pull out the inflated TextView/ImageView references out of our SampleViewHolder
         * instance.
         *
         * Look at the constructor of SampleViewHolder() and note that variable fields
         * 'imageView' and 'textView' are both public ( which is why we don't need a getter ).
         */
        TextView titleTextView = holder.titleTextView;
        TextView categoryTextView = holder.categoryTextView;
        TextView previewTextView = holder.previewTextView;
        ImageView imageView = holder.imageView;

        // put our dataItem string as text into the text view
        holder.titleTextView.setText(dataItem.getTitle());
        holder.categoryTextView.setText(dataItem.getCategory());
        holder.previewTextView.setText(dataItem.getPreview());

        // set the launcher icon as our image resource
        Context context = holder.imageView.getContext();

//        Picasso.with(context).load(dataItem.getImage()).into(imageView);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}
