package tutorial.lovecode.ui.fragments;

import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.util.Log;

import tutorial.lovecode.R;
import tutorial.lovecode.ui.BasePresenter;
import tutorial.lovecode.ui.MainView;
import tutorial.lovecode.ui.MainViewData;

public class MainViewPresenter extends BasePresenter<MainViewData,MainView> {

    private final String TAG = this.getClass().toString();
    private final String FRAGMENT_TAG_HOME = "FragmentTagHome";
    private final String FRAGMENT_TAG_TWO = "FragmentTagTwo";
    private final String FRAGMENT_TAG_THREE = "FragmentTagThree";

    public MainViewPresenter(){
    }

    @Override
    public void bindView(@NonNull MainView view) {
        super.bindView(view);
    }

    @Override
    public void unbindView(){
        super.unbindView();
    }

    @Override
    protected void updateView() {

    }


    public void navigationSelected(@IdRes int menuItemId){
        switch (menuItemId){
            case R.id.item1:
                Log.d(TAG, "Home tab selected");
                // These methods get from MainView
                view().displayFragment(createItemOneFragment(),FRAGMENT_TAG_HOME);
                break;
            case R.id.item2:
                Log.d(TAG, "Find tab selected");
                view().displayFragment(createItemTwoFragment(),FRAGMENT_TAG_TWO);
                break;
            case R.id.item3:
                Log.d(TAG, "Bookmarks tab selected");
                view().displayFragment(createItemThreeFragment(), FRAGMENT_TAG_THREE);
                break;
        }
    }

    private Fragment createItemOneFragment(){
        Fragment ItemOne = new ItemOneFragment();
        return ItemOne;
    }

    private Fragment createItemTwoFragment(){
        Fragment ItemTwo = new ItemTwoFragment();
        return ItemTwo;
    }

    private Fragment createItemThreeFragment(){
        Fragment ItemThree = new ItemThreeFragment();
        return ItemThree;
    }
}
