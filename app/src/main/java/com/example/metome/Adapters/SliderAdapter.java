package com.example.metome.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.example.metome.R;

public class SliderAdapter extends PagerAdapter {

    Context context;
    LayoutInflater layoutInflater;

    //create constructure to use it in main activity
    public SliderAdapter(Context context){
        this.context = context;
    }

    //create array to store values of slider
    public int[] slide_images = {
            R.drawable.motivation1,
            R.drawable.motivation2,
            R.drawable.motivation3
    };

    // array to store description
    public String[] slide_desc = {
            "Focus on the step in front of you, not the whole staircase." ,
            "Great things never came from comfort zones",
            "Believe in yourself and anything is possible."
    };


    @Override
    public int getCount() {
        return slide_desc.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view ==(RelativeLayout) object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.slide_layout , container , false);

        //initialize all the images and text views in slide layout
        ImageView slideImageView = (ImageView) view.findViewById(R.id.ivSlide1);
        TextView slideDescreption = (TextView) view.findViewById(R.id.tvSlide1);

        //to add image according to the position
        slideImageView.setImageResource(slide_images[position]);
        slideDescreption.setText(slide_desc[position]);

        container.addView(view);

        return view;
    }

    //when reach to the last page it will stop

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        super.destroyItem(container, position, object);
    }
}
