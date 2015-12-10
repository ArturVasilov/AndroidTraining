package training.arturvasilov.ru.userlocation;

import android.content.Context;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.List;

/**
 * @author Artur Vasilov
 */
public class LocationUtils {

    @Nullable
    public static Location provideLocation(Context context) {
        LocationManager manager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        List<String> providers = manager.getAllProviders();
        for (String name : providers) {
            LocationProvider provider = manager.getProvider(name);
            //passive has smallest accuracy, but requires nothing (2 in my case)
            //gps requires everything, but has greatest accuracy (1 in my case)
            //network is similar to passive
        }
        return null;
    }

    @Nullable
    public static Location provideBestLocation(Context context) {
        LocationManager manager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        Criteria criteria = new Criteria();
        criteria.setAccuracy(1);
        return provideLocationWithCriteria(context, criteria);
    }

    @Nullable
    public static Location provideLocationWithCriteria(Context context, @NonNull Criteria criteria) {
        LocationManager manager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        //noinspection ResourceType
        return manager.getLastKnownLocation(manager.getBestProvider(criteria, true));
    }

}
