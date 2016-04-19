package kr.howdyho.wondercalendar;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

/**
 * Created by Hyun on 2016-04-19.
 */
public class HomeActivity extends Activity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.temp_home);
    }


    // For Preventing from Going back to Log In Page
    /*@Override
    public void onBackPressed() {
        //super.onBackPressed();
    }
    */
}
