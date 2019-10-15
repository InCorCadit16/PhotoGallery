package com.incorcadit16.photogallery;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class PhotoGalleryActivity extends SingleFragmentActivity {


    @Override
    Fragment createFragment() {
        return PhotoGalleryFragment.newInstance();
    }

    static Intent newIntent(Context context) {
        return new Intent(context,PhotoGalleryActivity.class);
    }
}
