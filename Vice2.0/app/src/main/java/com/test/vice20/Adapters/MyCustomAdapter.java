package com.test.vice20.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.test.vice20.Models.Item;
import com.test.vice20.R;

import java.util.Collections;
import java.util.List;

/**
 * Created by kitty on 8/1/16.
 */
public class MyCustomAdapter extends BaseAdapter {

    private List<Item> data;
    private Context context;
    private ViewHolder viewHolder;

    public MyCustomAdapter(Context context) {
        this.data = Collections.EMPTY_LIST;
        this.context = context;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int i) {
        return data.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.list_item, viewGroup, false);
            viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }

        final Item currentArticle = data.get(i);

        viewHolder.titleTextView.setText(currentArticle.getTitle());
        viewHolder.categoryTextView.setText(currentArticle.getCategory());
        viewHolder.previewTextView.setText(currentArticle.getPreview());
        Picasso.with(context).load(currentArticle.getImage()).into(viewHolder.imageView);

        return view;
    }

    private class ViewHolder {
        ImageView imageView;
        TextView titleTextView;
        TextView categoryTextView;
        TextView previewTextView;

        public ViewHolder(View itemLayout) {
            this.imageView = (ImageView) itemLayout.findViewById(R.id.list_item_image);
            this.titleTextView = (TextView) itemLayout.findViewById(R.id.list_item_title);
            this.categoryTextView = (TextView) itemLayout.findViewById(R.id.list_item_category);
            this.previewTextView = (TextView) itemLayout.findViewById(R.id.list_item_preview);
        }
    }

    public void setData(List<Item> newData) {
        this.data = newData;
        this.notifyDataSetChanged();
    }
}
