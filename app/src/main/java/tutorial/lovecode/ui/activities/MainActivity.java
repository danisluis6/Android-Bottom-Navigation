package tutorial.lovecode.ui.activities;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import org.json.JSONObject;

import io.branch.indexing.BranchUniversalObject;
import io.branch.referral.Branch;
import io.branch.referral.BranchError;
import io.branch.referral.util.LinkProperties;
import tutorial.lovecode.R;
import tutorial.lovecode.ui.fragments.ItemOneFragment;
import tutorial.lovecode.ui.fragments.ItemThreeFragment;
import tutorial.lovecode.ui.fragments.ItemTwoFragment;

/**
 * @link(https://dashboard.branch.io/create)
 * => Create a new app Android (NavigationBottomDemo)
 * Activate Mode(LIVE -> TEST)
 * Automatically generate Branch Key. Please be visited in here https://dashboard.branch.io/account-settings/app
 * - We have configure some attributes such as:
 * + App Name
 * + Your App ID
 * + Branch Secret
 * + Branch Key
 * So for users: We just take care of Branch Secret and Branch Key. That's all.
 * It's done when Save Settings. Read more in here: https://docs.branch.io/pages/dashboard/integrate/
 *
 * @link(https://dashboard.branch.io/link-settings)
 * Use these settings to control the default behavior of your deep links on Android => Go to Link Settings.
 * - Android URI Scheme: navigatebottomexample:// (Fill out this form. We get it from AndroidManifest.xml)
 * - Custom URL: http://examplelorence.com/bottomnavigationbar (Just random url and put it in here)
 * - Android Package Name: Get from AndroidManifest.xml and put it in here
 *
 * Finally result: we got link domain: 8a6g.test-app.link from clicking button save.
 * So please save it 8a6g.test-app.link and copy it and paste clipart.
 *
 * We already received warning as: iOS app URL missing! We don’t know where to redirect. Please visit Link Settings.(Don't care about that)
 *
 * @link(https://docs.branch.io/pages/dashboard/integrate/)
 * - We configure guiding and run app Android with result received below
 * BranchConfigTest: deep link data: {"+clicked_branch_link":false,"+is_first_session":false}
 *
 * @link(https://docs.branch.io/pages/dashboard/integrate/)
 * - Feature: Enable a Deepview
 * - Head over this website: https://dashboard.branch.io/web/deepviews
 * - Toggle Enabled for branch_default for iOS and Android (Notice this)
 * - It's done now.
 *
 * @link(https://docs.branch.io/pages/web/deep-views/)
 * - Explore DeepView in Branch IO
 * - What is this?
 *
 * @link(https://docs.branch.io/pages/deep-linking/routing/) [NO QUICK LINK FOR TESTING)
 * - We need to explore Deep Link Routing
 * -
 *
 * @link(https://dashboard.branch.io/quick-links/qlc/define)
 * You just need to fill out an application (several fields)
 * - Name of link: My First Link
 * - * DEEP LINKING (Custom in here: https://docs.branch.io/pages/links/integrate/)
 *   * * Notice this title Callback values
 *   * * +is_first_session false(default)
 *   * * +clicked_branch_link(default)
 *
 * - Test with link: https://8a6g.app.link/uiQjhp5TBJ => Go to Deep Views
 * - Redirect => URL Website
 * - Redirect => Create Object => Return about this.
 *
 * Test: https://8a6g.test-app.link/aMxvnCLUBJ
 * WHen activate mode:
 * - LIVE : WEBSITE (show link => message domain (text message))
 * - TEST : There are two options for you
 * => Open Application (OK Not bug)
 * => Open WebView ()
 *
 *
 * https://8a6g.test-app.link/ucQT94cWBJ
 *
 * => No App installed in device => It goes to website and get link to show users "How to install Application"
 * => If we have installed application. So, there are two selections for users
 * + Open application
 * + Go to website and get new version from application.
 *
 * Done research about Branch IO Android
 */


/**
 * Test deep link
 *
 * - Create a deep link from the Branch Dashboard
 *
 * - Delete your app from the device(Purpose to get file apk(Link Settings) express DeepView )
 *
 * - Compile your app to your device
 *
 * - Paste deep link in Google Hangouts (Using google browser and get link to download app)
 *
 * - Click on the deep link to open your app (Click Link again and open application again)
 *
 */

/**
 * @link(https://docs.branch.io/pages/apps/android/)
 *
 * - Back to this website. Follow tutorial and read instruction.
 * * Implement features
 *      - Create content reference
 *
 *
 */


public class MainActivity extends AppCompatActivity {

    @Override
    public void onStart() {
        super.onStart();
        Branch branch = Branch.getInstance();

/*        branch.initSession(new Branch.BranchUniversalReferralInitListener() {
            @Override
            public void onInitFinished(BranchUniversalObject branchUniversalObject, LinkProperties linkProperties, BranchError error) {
                if (error == null) {
                    // params are the deep linked params associated with the link that the user clicked -> was re-directed to this app
                    // params will be empty if no data found
                    // ... insert custom logic here ...
                } else {
                    Log.i("MyApp", error.getMessage());
                }
            }
        }, this.getIntent().getData(), this);*/

        branch.initSession(new Branch.BranchReferralInitListener() {
            @Override
            public void onInitFinished(JSONObject referringParams, BranchError error) {
                if (error == null) {
                    // params are the deep linked params associated with the link that the user clicked before showing up
                    Log.i("BranchConfigTest", "deep link data: " + referringParams.toString());
                } else {
                    Log.e("MyApp", error.getMessage());
                }
            }
        }, this.getIntent().getData(), this);

    }

    @Override
    public void onNewIntent(Intent intent) {
        this.setIntent(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView bottomNavigationView = (BottomNavigationView)
                findViewById(R.id.navigation);

        bottomNavigationView.setOnNavigationItemSelectedListener
                (new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        Fragment selectedFragment = null;
                        switch (item.getItemId()) {
                            case R.id.action_item1:
                                selectedFragment = ItemOneFragment.newInstance();
                                break;
                            case R.id.action_item2:
                                selectedFragment = ItemTwoFragment.newInstance();
                                break;
                            case R.id.action_item3:
                                selectedFragment = ItemThreeFragment.newInstance();
                                break;
                        }
                        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                        transaction.replace(R.id.frame_layout, selectedFragment);
                        transaction.commit();
                        return true;
                    }
                });

        //Manually displaying the first fragment - one time only
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_layout, ItemOneFragment.newInstance());
        transaction.commit();
    }
}
