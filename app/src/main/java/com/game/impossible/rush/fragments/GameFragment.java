package com.game.impossible.rush.fragments;

import android.animation.Animator;
import android.animation.Animator.AnimatorListener;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewPropertyAnimator;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.game.impossible.rush.R;
import com.game.impossible.rush.manager.SharedPreferencesManager;

public class GameFragment extends BaseFragment {

    public static final String TAG = GameFragment.class.getSimpleName();

    public static final String BUNDLE_SCORE = "bundle_score";

    private static final long DURATION = 2000;
    private static final long ROTATION_DURATION = 65;

    private TextView mCurrentScoreTextView;
    private ImageButton mRotateButton;
    private ImageView mSquare;
    private ImageView mBall;

    private int mTopDistance;

    private int mRed;
    private int mBlu;
    private int mGreen;
    private int mYellow;

    private int mCurrentColor;
    private int mCurrentSquareColor;
    private int mScore;
    protected int mCnt;

    private ViewPropertyAnimator mBallViewAnimator;


    public static GameFragment newInstance() {
        return new GameFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        if (savedInstanceState != null && savedInstanceState.containsKey(BUNDLE_SCORE)) {
            mScore = savedInstanceState.getInt(BUNDLE_SCORE);
        }
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mContentView = inflater.inflate(R.layout.game_fragment, container, false);

        mBall = (ImageView) mContentView.findViewById(R.id.ball);
        mSquare = (ImageView) mContentView.findViewById(R.id.game_coloured_sqaure);
        mCurrentScoreTextView = (TextView) mContentView.findViewById(R.id.game_current_score_value);


        getRes();
        setupListeners();

        setBallColor((int) (Math.random() * 4));
        setSquareColor((int) (Math.random() * 4));


        return mContentView;
    }

    private void getRes() {
        mTopDistance = (int) getResources().getDimension(R.dimen.game_square_margin_top);

        mRed = getResources().getColor(R.color.red);
        mBlu = getResources().getColor(R.color.blu);
        mGreen = getResources().getColor(R.color.green);
        mYellow = getResources().getColor(R.color.yellow);
    }

    private void setupListeners() {
        mContentView.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                mCnt++;
                if (mCnt == 4) {
                    mCnt = 0;
                }
                setSquareColor((int) (Math.random() * 4));

                mSquare.animate();

            }
        });
    }


    private void setBallColor(int colorCode) {
        mCurrentColor = colorCode;
        int color;
        switch (colorCode) {
            case 0:
                color = mBlu;
                break;
            case 1:
                color = mRed;
                break;
            case 2:
                color = mYellow;
                break;
            case 3:
                color = mGreen;
                break;

            default:
                color = mRed;
                break;
        }
        ((GradientDrawable) mBall.getBackground()).setColor(color);
    }

    private void setSquareColor(int colorCode) {
        mCurrentSquareColor = colorCode;
        int color;
        switch (colorCode) {
            case 0:
                color = mBlu;
                break;
            case 1:
                color = mRed;
                break;
            case 2:
                color = mYellow;
                break;
            case 3:
                color = mGreen;
                break;

            default:
                color = mRed;
                break;
        }
        ((GradientDrawable) mSquare.getBackground()).setColor(color);
    }


    private void moveBall() {
        //This animation makes the ball go down...
        mBallViewAnimator = mBall.animate().translationY(mTopDistance).setDuration(DURATION).setListener(new AnimatorListener() {

            @Override
            public void onAnimationStart(Animator animation) {
            }

            @Override
            public void onAnimationRepeat(Animator animation) {
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                //...when it reaches the square, it will be positioned to the top...
                mBall.setTranslationY(0);

                if (mCurrentColor == mCurrentSquareColor) {
                    mCurrentScoreTextView.setText("" + (++mScore));
                } else {
                    // if the users looses, I check the best score and I update it if necessary
                    if (SharedPreferencesManager.getInstance().getBestScore() < mScore) {
                        SharedPreferencesManager.getInstance().setBestScore(mScore);
                    }
                    mScore = 0;
                    mCurrentScoreTextView.setText("" + mScore);
                }
                //... it changes color and starts again
                setBallColor((int) (Math.random() * 4));
                setSquareColor((int) (Math.random() * 4));

                //Let's put some randomness for the speed
                int rand = (int) (Math.random() * 500);
                mBall.animate().translationY(mTopDistance).setDuration(DURATION + rand).setListener(this);
            }

            @Override
            public void onAnimationCancel(Animator animation) {
            }
        });

    }

    @Override
    public void onResume() {
        super.onResume();
        moveBall();
    }

    @Override
    public void onPause() {
        super.onPause();
        mBallViewAnimator.cancel();
        if (SharedPreferencesManager.getInstance().getBestScore() < mScore) {
            SharedPreferencesManager.getInstance().setBestScore(mScore);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putInt(BUNDLE_SCORE, mScore);
        super.onSaveInstanceState(outState);
    }
}
