package arturvasilov.udacity.nanodegree.databindingtest;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.support.annotation.NonNull;

/**
 * @author Artur Vasilov
 */
public class Person extends BaseObservable {

    private final String mName;

    public Person(@NonNull String name) {
        mName = name;
    }

    @Bindable
    public String getName() {
        return mName;
    }

    @Bindable
    public String getImageUrl() {
        return "http://www.iconarchive.com/download/i47226/icons-land/vista-people/Person-Male-Light.ico";
    }
}
