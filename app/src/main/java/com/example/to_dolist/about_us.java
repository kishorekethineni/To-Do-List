package com.example.to_dolist;

import android.app.Fragment;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class about_us extends android.support.v4.app.Fragment {


    View v;

    TextView follow;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v=inflater.inflate(R.layout.fragment_about_us, container, false);
        follow=(TextView)v.findViewById(R.id.followme);
        follow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent browser=new Intent(Intent.ACTION_VIEW,Uri.parse("https://twitter.com/kishorec111"));
                startActivity(browser);
            }
        });

        return v;
    }

}
