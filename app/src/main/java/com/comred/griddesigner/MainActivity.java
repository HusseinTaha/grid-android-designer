package com.comred.griddesigner;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;

import com.comred.grid_android_designer.Screen;


public class MainActivity extends Screen {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setWithTitleBar(false);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }


    @Override
    public void onLayoutCreate() {
        super.onLayoutCreate();

        setViewWidthHeight(R.id.button1, 3.9f, 1.4f);
        setViewWidthHeight(R.id.button2, 3.9f, 1.4f);
        setViewWidthHeight(R.id.button3, 3.9f, 1.4f);
        setViewWidthHeight(R.id.button4, 3.9f, 1.4f);

        setTextSize(R.id.button1, SP * 0.6f);
        setTextSize(R.id.button2, SP * 0.6f);
        setTextSize(R.id.button3, SP * 0.6f);
        setTextSize(R.id.button4, SP * 0.6f);

        leftTop(R.id.button1, 3.0f, 3.5f);
        topRight(R.id.button2, 5.75f, 1.6f);
        leftBottom(R.id.button3, 7.1f, 1.2f);
        rightBottom(R.id.button4, 2.5f, 0.5f);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
