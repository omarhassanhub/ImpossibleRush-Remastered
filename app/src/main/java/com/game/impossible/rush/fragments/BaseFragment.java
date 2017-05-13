package com.game.impossible.rush.fragments;


import android.app.Activity;
import android.app.Fragment;
import android.os.Handler;
import android.os.Looper;
import android.view.View;

import com.game.impossible.rush.Bridge;
import com.game.impossible.rush.Rush;

public class BaseFragment extends Fragment {


    protected View mContentView;
    protected Bridge mBridge;
    private Handler mHandler;

    protected Rush mBaseActivity;

    @Override

    public void onAttach(Activity activity) {
        mBaseActivity = (Rush) activity;
        mBridge = (Bridge) getActivity();
        mHandler = new Handler();
        super.onAttach(activity);
    }

    Handler getHandler() {
        return mHandler;
    }

    public Bridge getBridge() {

        return mBridge;
    }

    public Handler getHandler(Looper looper) {
        return new Handler(looper);
    }


}
