package tutorial.lovecode.ui.activities;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabSelectListener;

import tutorial.lovecode.R;
import tutorial.lovecode.ui.MainView;
import tutorial.lovecode.ui.PresenterManager;
import tutorial.lovecode.ui.fragments.MainViewPresenter;

public class MainActivity extends AppCompatActivity implements MainView {

    private static final String CLASS_TAG = "MainActivity";
    private MainViewPresenter mMainViewPresenter;

    private BottomBar mBottomBar;
    private int mLastSelectedTab;
    public static final String SELECTED_TAB_KEY = "selectedTabKey";

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        // Always call the superclass so it can save the view hierarchy state
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putInt(SELECTED_TAB_KEY,  mLastSelectedTab);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mBottomBar = (BottomBar) findViewById(R.id.bottomBar);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mMainViewPresenter = (MainViewPresenter) PresenterManager.getInstance().get(CLASS_TAG);
        if(mMainViewPresenter == null){
            mMainViewPresenter = new MainViewPresenter();
        }
        mMainViewPresenter.bindView(this);
        mBottomBar.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelected(@IdRes int tabId) {
                if(mLastSelectedTab != tabId){
                    mMainViewPresenter.navigationSelected(tabId);
                    mLastSelectedTab = tabId;
                }
            }
        });
    }

    @Override
    public void displayFragment(Fragment fragment, String tag) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction ft = fragmentManager.beginTransaction();

        if(fragmentManager.findFragmentByTag(tag) != null){
            fragment = fragmentManager.findFragmentByTag(tag);
            ft.replace(R.id.frame_layout, fragment, tag);
            ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        } else {
            ft.replace(R.id.frame_layout, fragment, tag);
            ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
            ft.addToBackStack(tag);
        }
        ft.commit();
    }

    @Override
    public Context getViewContext() {
        return getApplicationContext();
    }
}
