package arturvasilov.udacity.nanodegree.databindingtest;

import android.databinding.BindingAdapter;
import android.support.annotation.NonNull;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

/**
 * @author Artur Vasilov
 */
public class Adapters {

    @BindingAdapter("app:imageUrl")
    public static void setImageUrl(@NonNull ImageView imageView, @NonNull String url) {
        Picasso.with(imageView.getContext()).load(url).into(imageView);
    }

}
