package com.incorcadit16.photogallery;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.Fragment;

public class PhotoPageActivity extends SingleFragmentActivity {
    PhotoPageFragment mFragment;

    static Intent newIntent(Context context, Uri photoPageUri)  {
        Intent i = new Intent(context,PhotoPageActivity.class);
        i.setData(photoPageUri);
        return i;
    }

    @Override
    Fragment createFragment() {
        mFragment = PhotoPageFragment.newInstance(getIntent().getData());
        return mFragment;
    }

    @Override
    public void onBackPressed() {
        if (mFragment.mWebView.canGoBack()) {
            mFragment.mWebView.goBack();
        } else {
            super.onBackPressed();
        }
    }
}
