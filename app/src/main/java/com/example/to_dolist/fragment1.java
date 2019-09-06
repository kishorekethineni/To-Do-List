package com.example.to_dolist;



import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ViewFlipper;

public class fragment1 extends Fragment {
    ViewFlipper vp;

    public fragment1() {

    }
CardView cv2,cv3,cv4,cv5,cv6;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment1, container, false);
        vp = v.findViewById(R.id.flipper);
        int image[] = {R.drawable.vp, R.drawable.vp2, R.drawable.vp3};
        for (int i = 0; i < image.length; i++) {
            flippingimage(image[i]);

        }
        cv2=v.findViewById(R.id.cv2);
        cv3=v.findViewById(R.id.cv3);
        cv4=v.findViewById(R.id.cv4);
        cv5=v.findViewById(R.id.cv5);
        cv6=v.findViewById(R.id.cv6);
        cv2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i2= new Intent(getActivity(),Main2Activity.class);
                startActivity(i2);
            }
        });
        cv3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i2= new Intent(getActivity(),Main3Activity.class);
                startActivity(i2);
            }
        });
        cv4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().beginTransaction().replace(R.id.fragment_container,new about_us()).commit();
            }
        });
        cv5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().beginTransaction().replace(R.id.fragment_container, new contactus()).commit();

            }
        });
        cv6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i2= new Intent(getActivity(),MainActivityfire.class);
                startActivity(i2);
            }
        });
        return v;
    }

    public void flippingimage(int image) {
        ImageView imageView = new ImageView(getActivity());
        imageView.setBackgroundResource(image);
        vp.addView(imageView);
        vp.setFlipInterval(2300);
        vp.setAutoStart(true);
        vp.setInAnimation(getActivity(), android.R.anim.slide_in_left);
        vp.setInAnimation(getActivity(), android.R.anim.slide_out_right);

    }
}