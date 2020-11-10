package com.example.metome;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.metome.Adapters.SliderAdapter;

public class MainActivity extends AppCompatActivity {
    private ViewPager vpMain;
    private LinearLayout llMain;
    private SliderAdapter sliderAdapter;

    private TextView[] mDots;

    private Button btnNext;
    private Button btnPrev;

    private int CurrentPage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        vpMain = (ViewPager) findViewById(R.id.vpMain);
        llMain = (LinearLayout) findViewById(R.id.llMain);

        btnNext = (Button) findViewById(R.id.btnNext);
        btnPrev = (Button) findViewById(R.id.btnPrev);


        //add adapter to the view pager
        sliderAdapter = new SliderAdapter(this);
        vpMain.setAdapter(sliderAdapter);

        addDotsIndicator(0);
        //vpMain.addOnAdapterChangeListener((ViewPager.OnAdapterChangeListener) viewListener);

        //OnClickListener
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vpMain.setCurrentItem(CurrentPage +1);
                Intent loginSignupIntent = new Intent(MainActivity.this, LoginSignUpActivity.class);
                startActivity(loginSignupIntent);
            }
        });

        btnPrev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vpMain.setCurrentItem(CurrentPage -1);
            }
        });

    }

    //dots in the end of layout, count number of items that we need
    public void addDotsIndicator(int position){

        mDots = new TextView[3];
        llMain.removeAllViews();

        for(int i = 0; i<mDots.length; i++){
            mDots[i] = new TextView(this);
            mDots[i].setText(Html.fromHtml("&#8226"));
            mDots[i].setTextSize(45);
            mDots[i].setTextColor(getResources().getColor(R.color.colorAccent));
            llMain.addView(mDots[i]);
        }

        if(mDots.length > 0){
            mDots[position].setTextColor(getResources().getColor(R.color.colorPrimaryDark));

        }


    }
    //to know in which page we are "dots"

    ViewPager.OnPageChangeListener viewListener = new ViewPager.OnPageChangeListener() {

        @Override                //position, positionOffset , positionOffsetPixels
        public void onPageScrolled(int p, float pOffset, int positionOffsetPixels) {

        }

        @Override                //position
        public void onPageSelected(int i) {
            addDotsIndicator(i);

            CurrentPage = i;
            //if current page is 0 we set next btn true
            if(i==0){
                btnNext.setEnabled(true);
                btnPrev.setEnabled(false);
           //     btnPrev.setVisibility(View.invisible);
                btnNext.setText("Next");
              //  btnPrev.setText("");

                //if current page = length of dots
            }else if(i==mDots.length -1){
                btnNext.setEnabled(true);
                btnPrev.setEnabled(true);
                btnPrev.setVisibility(View.VISIBLE);

                btnNext.setText("Next");
                btnPrev.setText("Back");


                // if we are not in the first or last page
            }else {

                btnNext.setEnabled(true);
                btnPrev.setEnabled(true);
                btnPrev.setVisibility(View.VISIBLE);

                btnNext.setText("Finish");
                btnPrev.setText("Back");

                Intent intent = new Intent(MainActivity.this, LoginSignUpActivity.class);
                startActivity(intent);

            }

        }


        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };


}