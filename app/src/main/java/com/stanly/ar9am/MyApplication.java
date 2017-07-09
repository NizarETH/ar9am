package com.stanly.ar9am;

import android.app.Application;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by nizar on 07/07/17.
 */

public class MyApplication extends Application {

        @Override
        public void onCreate() {
            super.onCreate();
            // The Realm file will be located in Context.getFilesDir() with name "default.realm"
            Realm.init(this);
            RealmConfiguration config = new RealmConfiguration.Builder() .deleteRealmIfMigrationNeeded().build();
            Realm.setDefaultConfiguration(config);
        }


    public static MyApplication getApplication(Context context) {
        return (MyApplication) context.getApplicationContext();
    }
    public void setFragment(FragmentActivity fragmentActivity, Fragment fm, int layout_id){
        ((MainActivity) fragmentActivity).bodyFragment  = fm.getClass().getSimpleName();

        fragmentActivity.getSupportFragmentManager().beginTransaction()
                .setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left, R.anim.slide_in_left, R.anim.slide_out_right)
                .replace(layout_id, fm,  ((MainActivity) fragmentActivity).bodyFragment ).addToBackStack( ((MainActivity) fragmentActivity).bodyFragment ).commitAllowingStateLoss();
    }

    public void setFragmentWithBackAnimation(FragmentActivity fragmentActivity, Fragment fm, int layout_id){
        ((MainActivity) fragmentActivity).bodyFragment  = fm.getClass().getSimpleName();

        fragmentActivity.getSupportFragmentManager().beginTransaction()
                .setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_right, R.anim.slide_in_right, R.anim.slide_out_left)
                .replace(layout_id, fm,  ((MainActivity) fragmentActivity).bodyFragment ).addToBackStack( ((MainActivity) fragmentActivity).bodyFragment ).commit();
    }

    public void setFragmentWithCustomAnimation(FragmentActivity fragmentActivity, Fragment fm, int layout_id, int[] anims){
        String TAG = fm.getClass().getSimpleName();

        fragmentActivity.getSupportFragmentManager().beginTransaction()
                .setCustomAnimations(anims[0], anims[1], anims[2], anims[3])
                .replace(layout_id, fm, TAG).addToBackStack(TAG).commit();
    }

    public void setFragmentWithoutAnimation(FragmentActivity fragmentActivity,Fragment fm, int layout_id){
        ((MainActivity) fragmentActivity).bodyFragment  = fm.getClass().getSimpleName();

        fragmentActivity.getSupportFragmentManager().beginTransaction()
                .replace(layout_id, fm,  ((MainActivity) fragmentActivity).bodyFragment )/*.addToBackStack( ((MainActivity) fragmentActivity).bodyFragment )*/.commit();
    }

    public void refreshFragment(FragmentActivity fragmentActivity,Fragment fm){
        ((MainActivity) fragmentActivity).bodyFragment  = fm.getClass().getSimpleName();

        fragmentActivity.getSupportFragmentManager().beginTransaction().detach(fm).attach(fm)/*.addToBackStack( ((MainActivity) fragmentActivity).bodyFragment )*/.commit();
    }
}

