package com.example.adam.timemanagerultimate;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import at.markushi.ui.CircleButton;
import roboguice.fragment.RoboFragment;


public class HomeFragment extends RoboFragment {
    Context thiscontext;




    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);
        thiscontext = container.getContext();

        final Animation animRotate = AnimationUtils.loadAnimation(getActivity(), R.anim.anim_rotate);
        CircleButton btnScale = (CircleButton) rootView.findViewById(R.id.button2);

        btnScale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.startAnimation(animRotate);
                v.getResources();
                CircleButton btnScale = (CircleButton) v.findViewById(R.id.button2);
            }
        });

        // Inflate the layout for this fragment
        return rootView;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}
