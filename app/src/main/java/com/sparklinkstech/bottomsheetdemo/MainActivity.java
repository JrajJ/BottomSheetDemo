package com.sparklinkstech.bottomsheetdemo;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.graphics.Rect;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity{
    private View mBottomSheet;
    ImageView iv;
    MyBottomSheetBehavior<View> mBehavior;
    LinearLayout ll;

    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        iv = findViewById(R.id.iv);
        ll = findViewById(R.id.ll);
        //Toolbar toolbar = findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);

        Rect rectangle = new Rect();
        Window windoww = getWindow();
        windoww.getDecorView().getWindowVisibleDisplayFrame(rectangle);
        int statusBarHeight = rectangle.top;
        int contentViewTop =
                windoww.findViewById(Window.ID_ANDROID_CONTENT).getTop();
        int titleBarHeight= contentViewTop - statusBarHeight;
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        int result = getResources().getDimensionPixelSize(resourceId);
        Log.e("*** Elenasys :: ", "StatusBar Height= " + getResources().getDimensionPixelSize(resourceId) + " , TitleBar Height = " + titleBarHeight);
        LinearLayout.LayoutParams margins = new LinearLayout.LayoutParams(ll.getLayoutParams());
        margins.topMargin = result;
        iv.setLayoutParams(margins);

        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Status bar :: Transparent
        Window window = this.getWindow();


        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        //window.setStatusBarColor(Color.TRANSPARENT);
        window.setStatusBarColor(ContextCompat.getColor(getApplicationContext(),
                R.color.colorAccent));
        getSupportActionBar().setBackgroundDrawable(getDrawable(R.drawable.back_gradient));

        //toolbar.setTitle("");



        int peekHeight = getResources().getDimensionPixelSize(R.dimen.bottom_sheet_peek_height); // 96dp
        mBottomSheet = findViewById(R.id.bottomSheet);
        mBehavior = (MyBottomSheetBehavior) MyBottomSheetBehavior.from(mBottomSheet);
        mBehavior.setPeekHeight(peekHeight);
    }
}