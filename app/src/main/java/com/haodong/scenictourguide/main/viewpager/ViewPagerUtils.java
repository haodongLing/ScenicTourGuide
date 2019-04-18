package com.haodong.scenictourguide.main.viewpager;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.haodong.scenictourguide.R;

/**
 * Created by GIGAMOLE on 8/18/16.
 */
public class ViewPagerUtils {

    public static void setupItem(final View view, final LibraryObject libraryObject) {
        final ImageView img = (ImageView) view.findViewById(R.id.main_vp_img);
        img.setImageResource(libraryObject.getRes());
    }

    public static class LibraryObject {

        private int mRes;

        public LibraryObject(final int res) {
            mRes = res;

        }
        public int getRes() {
            return mRes;
        }

        public void setRes(final int res) {
            mRes = res;
        }
    }
}
