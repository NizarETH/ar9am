package com.stanly.ar9am;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.preference.PreferenceManager;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.google.firebase.crash.FirebaseCrash;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.Timer;

import io.realm.Realm;


public class MainActivity extends FragmentActivity {

    private InterstitialAd mInterstitialAd;
    public String bodyFragment;
    private Realm r;
    private LinearLayout list_items;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //FirebaseCrash.report(new Exception("My first Android non-fatal error"));
        mInterstitialAd = new InterstitialAd(this);


        mInterstitialAd.setAdUnitId("ca-app-pub-4398691256924309/7513154274");
        mInterstitialAd.loadAd(new AdRequest.Builder().addTestDevice("03C1A7626D4CC5C38E8A4D2DAC481463").build());

        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                // Load the next interstitial.
            }

            @Override
            public void onAdLoaded() {
                super.onAdLoaded();
                mInterstitialAd.show();
            }

            @Override
            public void onAdFailedToLoad(int i) {
                super.onAdFailedToLoad(i);
                Toast.makeText(getApplicationContext()," error num "+i,Toast.LENGTH_LONG).show();
            }
        });




        r = Realm.getDefaultInstance();
        SwipperFragment swipperFragment = new SwipperFragment();
        bodyFragment = "SwipperFragment";

        ((MyApplication) getApplication()).setFragment(MainActivity.this, swipperFragment, R.id.fragment_container);

        list_items = (LinearLayout) findViewById(R.id.list_items);

        List<girl> girls = r.where(girl.class).findAll();
        int size = girls.size();

        {
            List<girl> allGirls = new ArrayList<>();
            for (int i = 0; i < 3; i++) {
                Random rnd = new Random();
                int index = rnd.nextInt(size);
                allGirls.add(girls.get(index));

            }
            Log.e("result", "" + allGirls);
            LinearLayout item = null;
            for (int i = 0; i < allGirls.size(); i++) {
                item = (LinearLayout) getLayoutInflater().inflate(R.layout.item, null);
                final TextView name = (TextView) item.findViewById(R.id.name);
                String name1 = allGirls.get(i).getName().substring(0, 1).toUpperCase() + allGirls.get(i).getName().substring(1);
                name.setText(name1);
                TextView phone = (TextView) item.findViewById(R.id.tel);
                String phone1 = allGirls.get(i).getTel().substring(0, 1).toUpperCase() + allGirls.get(i).getTel().substring(1);
                phone.setText(phone1);
                TextView city = (TextView) item.findViewById(R.id.city);
                String city1 = allGirls.get(i).getVille().substring(0, 1).toUpperCase() + allGirls.get(i).getVille().substring(1);
                city.setText(city1);
                list_items.addView(item);

                phone.setTag(phone1);
                phone.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mInterstitialAd.loadAd(new AdRequest.Builder().addTestDevice("03C1A7626D4CC5C38E8A4D2DAC481463").build());

                        final String value = (String) v.getTag();
                        View layout = getLayoutInflater().inflate( R.layout.dialog, null);

                        final PopupWindow  pw = new PopupWindow(layout,
                                LinearLayout.LayoutParams.WRAP_CONTENT,
                                LinearLayout.LayoutParams.WRAP_CONTENT, true);
                        // display the popup in the center
                        pw.setOutsideTouchable(true);
                        pw.setBackgroundDrawable(new ColorDrawable(Color.WHITE));
                        pw.setFocusable(true);
                pw.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
                        ((TextView)layout.findViewById(R.id.phone)).setText(value);
                        (layout.findViewById(R.id.copy)).setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                ClipboardManager clipboard = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
                                ClipData clip = ClipData.newPlainText("Tél", value);
                                clipboard.setPrimaryClip(clip);
                            }
                        });

                        pw.setTouchInterceptor(new View.OnTouchListener() {
                            @Override
                            public boolean onTouch(View v, MotionEvent event) {
                                if (event.getAction() == MotionEvent.ACTION_OUTSIDE) {
                                    pw.dismiss();
                                    mInterstitialAd.loadAd(new AdRequest.Builder().addTestDevice("03C1A7626D4CC5C38E8A4D2DAC481463").build());

                                    return true;
                                }
                                return false;
                            }
                        });

                        pw.showAtLocation(v, Gravity.CENTER, 0, 0);
                    }
                });
            }
        }





    }
    @Override
    protected void onDestroy() {

        super.onDestroy();

    }

    @Override
    protected void onResume() {
        super.onResume();
       // mInterstitialAd.loadAd(new AdRequest.Builder().addTestDevice("03C1A7626D4CC5C38E8A4D2DAC481463").build());

    }
}

/*******    ID de l'application : ca-app-pub-4398691256924309~1745822270
 ID du bloc d'annonces : ca-app-pub-4398691256924309/7513154274*****/