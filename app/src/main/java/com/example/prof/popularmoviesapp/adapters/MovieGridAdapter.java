package com.example.prof.popularmoviesapp.adapters;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.prof.popularmoviesapp.R;
import com.example.prof.popularmoviesapp.model.Movie;

import java.util.List;

public class MovieGridAdapter extends BaseAdapter {

    private final Context mContext;
    private final LayoutInflater mInflater;

    // Movie model
    private final Movie mLock = new Movie();

    private List<Movie> mObjects;

    //constructor
    public MovieGridAdapter(Context context, List<Movie> objects) {
        mContext = context;
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mObjects = objects;
    }



    public Context getContext() {
        return mContext;
    }

    //add object movie
    public void add(Movie object) {
        synchronized (mLock) {
            mObjects.add(object);
        }
        notifyDataSetChanged();
    }

    //clear
    public void clear() {
        synchronized (mLock) {
            mObjects.clear();
        }
        notifyDataSetChanged();
    }

    // set data on movies
    public void setData(List<Movie> data) {
        clear();
        for (Movie movie : data) {
            add(movie);
        }
    }

    // getter and setter
    @Override
    public int getCount() {
        return mObjects.size();
    }

    @Override
    public Movie getItem(int position) {
        return mObjects.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    // view
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        ViewHolder viewHolder;

        if (view == null) {
            view = mInflater.inflate(R.layout.grid_item_movie, parent, false);
            viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);
        }

        final Movie movie = getItem(position);

        String image_url = "http://image.tmdb.org/t/p/w185" + movie.getImage();

        viewHolder = (ViewHolder) view.getTag();

        Glide.with(getContext()).load(image_url).into(viewHolder.imageView);
        viewHolder.titleView.setText(movie.getTitle());

        return view;
    }


    // view holder class
    // holds imageView and titleView
    public static class ViewHolder {
        public final ImageView imageView;
        public final TextView titleView;

        public ViewHolder(View view) {
            imageView = (ImageView) view.findViewById(R.id.grid_item_image);
            titleView = (TextView) view.findViewById(R.id.grid_item_title);
        }
    }
}
