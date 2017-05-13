package com.game.impossible.rush.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.game.impossible.rush.R;
import com.game.impossible.rush.manager.SharedPreferencesManager;

public class ScoreFragment extends BaseFragment {


    public static final String TAG = ScoreFragment.class.getSimpleName();

    private TextView mBestScoreTextView;

    public static ScoreFragment newInstance() {

        return new ScoreFragment();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        mContentView = inflater.inflate(R.layout.score_fragment, container, false);
        mBestScoreTextView = (TextView) mContentView.findViewById(R.id.score_value);
        mBestScoreTextView.setText("" + SharedPreferencesManager.getInstance().getBestScore());
        return mContentView;
    }
}