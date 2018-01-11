package tutorial.lovecode.ui.base;

import android.app.Application;

import io.branch.referral.Branch;

/**
 * Created by lorence on 11/01/2018.
 *
 */

public final class CustomApplicationClass extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        // Initialize the Branch object
        Branch.getAutoInstance(this);

    }
}