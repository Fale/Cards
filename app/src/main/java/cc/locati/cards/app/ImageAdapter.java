package cc.locati.cards.app;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;

import com.caverock.androidsvg.SVGImageView;

public class ImageAdapter extends BaseAdapter {
    private Context mContext;

    // Constructor
    public ImageAdapter(Context c) {
        mContext = c;
    }

    public int getCount() {
        return 1;
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        SVGImageView imageView;
        if (convertView == null) {
            imageView = new SVGImageView(mContext);
            imageView.setImageAsset("letterssvg.svg");
        } else {
            imageView = (SVGImageView) convertView;
        }
        return imageView;
    }

}