package com.application.teleshopnative;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SearchActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.widget_search_bar_expanded);
    }


    @Override
    protected boolean useSearchBar() {
        return false;
    }

    @Override
    protected boolean useDrawerToggle() {
        return false;
    }

    @Override
    protected boolean useToolbar() {
        return false;
    }


}
