package com.comred.grid_android_designer;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v4.app.FragmentActivity;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.androidquery.AQuery;

/**
 * Created by husseintaha on 5/31/15.
 * Screen class is an activity class to make the user interface layouts more realible and easy to manage.
 */
public abstract class Screen extends FragmentActivity {

    //percent value every grid unit is about 5% from screen, should be the same as the photoshop grid images
    private final float percent = 0.05f;
    //sp unit for the texts.
    public float SP;
    // Grid units for height and width.
    private float HGRID, WGRID;
    //screen width and height
    private int screenWidth, screenHeight;
    //not user for now, number of horizontal grid units and vertical.
    private float nHGrids = 0, nVGrids = 0;
    //scaled density of the display screen.
    private float scaledDensity;
    //the android query for the view
    private AQuery ajax;
    //if the status bar is hidden or not.
    private Boolean withTitleBar = true;

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
    }


    @Override
    protected void onStart() {
        super.onStart();
        setScreenWH();
        setSPUnit();
        ajax = new AQuery(this);
        onLayoutCreate();
    }


    /**
     * set with title bar or not
     *
     * @param b value for status bar
     */
    public void setWithTitleBar(Boolean b) {
        withTitleBar = b;
    }

    private int getStatusBarHeight() {
        int result = 0;
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }

    private void adjustHeight() {
        if (withTitleBar) {
            screenHeight -= getStatusBarHeight();
        }
    }

    private final void setScreenWH() {
        DisplayMetrics metrics = getResources().getDisplayMetrics();
        screenWidth = metrics.widthPixels;
        screenHeight = metrics.heightPixels;
        adjustHeight();
        WGRID = screenWidth * percent;
        HGRID = screenHeight * percent;
        nHGrids = screenHeight / HGRID;
        nVGrids = screenWidth / WGRID;
        scaledDensity = metrics.scaledDensity;
    }

    private final void setSPUnit() {
        SP = WGRID / scaledDensity;
    }


    /**
     *
     * should override this method to set views layouts.
     */
    public void onLayoutCreate() {
    }


    /**
     * set textSize for the views containing text.
     *
     * @param resId --> resource id for the view
     * @param size --> text size in function of SP units
     */
    public void setTextSize(int resId, float size) {
        AQuery a = ajax.id(resId);
        if (a != null && a.getView() != null && a.getView() instanceof TextView) {
            a.textSize(size);
        }
    }

    private void setWidthHeight(View v, int width, int height) {
        v.getLayoutParams().width = width;
        v.getLayoutParams().height = height;
    }


    /**
     * @param resId --> resource id for the view
     * @param width --> width of the view in number of grid units
     * @param height --> height of the view in number of grid units
     */
    public void setViewWidthHeight(int resId, float width, float height) {
        AQuery a = ajax.id(resId);
        width *= WGRID;
        height *= HGRID;
        if (a != null && a.getView() != null) {
            setWidthHeight(a.getView(), Math.round(width), Math.round(height));
        }
    }

    private final void setViewMargin(View v, int left, int top, int right, int bottom) {
        ViewGroup.LayoutParams p = v.getLayoutParams();
        if (p instanceof LinearLayout.LayoutParams) {
            LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) p;
            lp.setMargins(left, top, right, bottom);
        } else if (p instanceof RelativeLayout.LayoutParams) {
            RelativeLayout.LayoutParams lp = (RelativeLayout.LayoutParams) p;
            lp.setMargins(left, top, right, bottom);
        } else if (p instanceof TableRow.LayoutParams) {
            TableRow.LayoutParams lp = (TableRow.LayoutParams) p;
            lp.setMargins(left, top, right, bottom);
        }
    }


    /**
     * set view margins.
     * @param resId --> resource id for the view
     * @param left --> margin left of the view in number of grid units
     * @param top --> margin top of the view in number of grid units
     * @param right --> margin right of the view in number of grid units
     * @param bottom --> margin bottom of the view in number of grid units
     */
    public void setMargin(int resId, float left, float top, float right, float bottom) {
        top *= HGRID;
        right *= WGRID;
        bottom *= HGRID;
        left *= WGRID;
        int xLeft = Math.round(left),
                yTop = Math.round(top),
                xRight = Math.round(right),
                yBottom = Math.round(bottom);
        AQuery a = ajax.id(resId);
        if (a != null && a.getView() != null) {
            setViewMargin(a.getView(), xLeft, yTop, xRight, yBottom);
        }
    }

    /**
     * set view top and right margins
     * @param resId --> resource id for the view
     * @param top --> margin top of the view in number of grid units
     * @param right --> margin right of the view in number of grid units
     */
    public void topRight(int resId, float top, float right) {
        top *= HGRID;
        right *= WGRID;
        int yTop = Math.round(top),
                xRight = Math.round(right);
        AQuery a = ajax.id(resId);
        if (a != null && a.getView() != null) {
            setViewMargin(a.getView(),
                    screenWidth - xRight - a.getView().getLayoutParams().width,
                    yTop, 0, 0);
        }

    }

    /**
     * set view right and bottom margins
     * @param resId --> resource id for the view
     * @param right --> margin right of the view in number of grid units
     * @param bottom --> margin bottom of the view in number of grid units
     */
    public void rightBottom(int resId, float right, float bottom) {
        right *= WGRID;
        bottom *= HGRID;
        int yBottom = Math.round(bottom),
                xRight = Math.round(right);
        AQuery a = ajax.id(resId);
        if (a != null && a.getView() != null) {
            setViewMargin(a.getView(),
                    screenWidth - xRight - a.getView().getLayoutParams().width,
                    screenHeight - yBottom - a.getView().getLayoutParams().height, 0, 0);
        }

    }

    /**
     * set view left and top margins
     * @param resId --> resource id for the view
     * @param left --> margin left of the view in number of grid units
     * @param top --> margin top of the view in number of grid units
     */
    public void leftTop(int resId, float left, float top) {
        top *= HGRID;
        left *= WGRID;
        int xLeft = Math.round(left),
                yTop = Math.round(top);
        AQuery a = ajax.id(resId);
        if (a != null && a.getView() != null) {
            setViewMargin(a.getView(), xLeft, yTop, 0, 0);
        }

    }

    /**
     * set view left and bottom margins
     * @param resId --> resource id for the view
     * @param left --> margin left of the view in number of grid units
     * @param bottom --> margin bottom of the view in number of grid units
     */
    public void leftBottom(int resId, float left, float bottom) {
        bottom *= HGRID;
        left *= WGRID;
        int yBottom = Math.round(bottom),
                xLeft = Math.round(left);
        AQuery a = ajax.id(resId);
        if (a != null && a.getView() != null) {

            setViewMargin(a.getView(), xLeft,
                    screenHeight - yBottom - a.getView().getLayoutParams().height, 0, 0);
        }
    }
}
