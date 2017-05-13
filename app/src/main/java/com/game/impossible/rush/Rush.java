package com.game.impossible.rush;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.KeyEvent;

import com.game.impossible.rush.fragments.BaseFragment;
import com.game.impossible.rush.fragments.GameFragment;
import com.game.impossible.rush.fragments.HomeFragment;
import com.game.impossible.rush.fragments.ScoreFragment;
import com.game.impossible.rush.manager.SharedPreferencesManager;

public class Rush extends Activity implements Bridge {

    private static final int SECTIONS_CONTAINER = R.id.sections_container;


    private Builder mExitDialogBuilder;
    private DialogInterface.OnClickListener mExitDialogListener;
    private String mExitPromptLabel;
    private String mYesLabel;
    private String mNoLabel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rush_layout);
        if(getActionBar() != null){
            getActionBar().hide();
        }


        SharedPreferencesManager.getInstance().setup(this);
        switchTo(HomeFragment.TAG);
        getRes();
        setupExitDialog();

    }

    @Override
    public void switchTo(String section, Object... args){

        BaseFragment frag = null;
        boolean addToBackstack = true;

        if(HomeFragment.TAG.equals(section)){
            frag = HomeFragment.newInstance();

        }else if(GameFragment.TAG.equals(section)){
            frag = GameFragment.newInstance();
        }else if(ScoreFragment.TAG.equals(section)){
            frag = ScoreFragment.newInstance();
        }
        if(frag == null){
            return;
        }
        performReplace(SECTIONS_CONTAINER, frag, section, addToBackstack);
    }

    private void performReplace(int container, BaseFragment f, String tag, boolean performAddToBackStack){
        FragmentTransaction t = getFragmentManager().beginTransaction();

        t= t.replace(container, f);
        t = performAddToBackStack ? t.addToBackStack(tag) : t;
        t.commit();
    }

    private void getRes(){
        mExitPromptLabel = "Do you want to quit the game";
        mYesLabel = "Yes";
        mNoLabel = "No";

    }

    private void setupExitDialog(){
        mExitDialogListener = new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int which){
                switch(which){
                    case DialogInterface.BUTTON_POSITIVE:
                        finish();
                        break;

                    case DialogInterface.BUTTON_NEGATIVE:
                        break;
                }
            }
        };
        mExitDialogBuilder = new AlertDialog.Builder(this);
        mExitDialogBuilder.setMessage(mExitPromptLabel).setPositiveButton(mYesLabel, mExitDialogListener)
                .setNegativeButton(mNoLabel, mExitDialogListener);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event){
        if(keyCode == KeyEvent.KEYCODE_BACK){
            if(getCurrentFragmentName().equals(HomeFragment.TAG)){
                mExitDialogBuilder.show();
                return false;
            }
        }
        return super.onKeyDown(keyCode, event);
    }

    private String getCurrentFragmentName(){

        int backStackEntryCount  = getFragmentManager().getBackStackEntryCount();

        if(backStackEntryCount > 0) {


            return getFragmentManager().getBackStackEntryAt(backStackEntryCount - 1).getName();
        }else{
            return "";
        }
        }

}
