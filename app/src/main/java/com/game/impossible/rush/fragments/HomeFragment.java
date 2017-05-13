package com.game.impossible.rush.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;

import com.game.impossible.rush.R;

public class HomeFragment extends BaseFragment {

    public static final String TAG = HomeFragment.class.getSimpleName();

    private Button mNewGameButton;
    private Button mScoreButton;

    public static HomeFragment newInstance() {
        return new HomeFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        mContentView = inflater.inflate(R.layout.home_fragment, container, false);

        mNewGameButton = (Button) mContentView.findViewById(R.id.home_new_game_btn);
        mScoreButton = (Button) mContentView.findViewById(R.id.home_score_btn);

        setupListeners();
        return mContentView;
    }

    private void setupListeners() {

        mNewGameButton.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {

                mBridge.switchTo(GameFragment.TAG);
            }
        });

        mScoreButton.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                mBridge.switchTo(ScoreFragment.TAG);

            }
        });
    }

}