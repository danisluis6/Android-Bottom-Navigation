package tutorial.lovecode.ui;


import android.content.Context;
import android.support.v4.app.Fragment;

public interface MainView {

    void displayFragment(Fragment fragment, String tag);

    Context getViewContext();
}
