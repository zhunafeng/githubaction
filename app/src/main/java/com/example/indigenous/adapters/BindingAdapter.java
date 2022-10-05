package com.example.indigenous.adapters;

import android.os.Build;
import android.text.Html;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.core.text.HtmlCompat;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;

public class BindingAdapter {
    @androidx.databinding.BindingAdapter("imageFromUrl")
    public static void bindImageFromUrl(ImageView imageView,String imageUrl){
        if (!TextUtils.isEmpty(imageUrl)) {
            Glide.with(imageView.getContext())
                    .load(imageUrl)
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .into(imageView);
        }
    }

    @androidx.databinding.BindingAdapter("renderHtml")
    public static void rendHTML(TextView textView,String str){
        if (str == null){
            textView.setText("");
        }else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                textView.setText(Html.fromHtml(str, HtmlCompat.FROM_HTML_MODE_COMPACT));
                textView.setMovementMethod(LinkMovementMethod.getInstance());
            }
        }
    }

}
