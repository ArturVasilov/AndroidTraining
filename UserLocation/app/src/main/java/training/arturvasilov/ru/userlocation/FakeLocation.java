package training.arturvasilov.ru.userlocation;

import android.content.Context;
import android.location.Location;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.support.annotation.NonNull;

/**
 * @author Artur Vasilov
 */
public class FakeLocation {

    private static final String FAKE_PROVIDER_NAME = "fake";

    public static void attachFakeProvider(Context context) {
        LocationManager manager = detachFakeProvider(context);
        manager.addTestProvider(FAKE_PROVIDER_NAME, false,
                false, false, false, false, false, false,
                android.location.Criteria.POWER_LOW,
                android.location.Criteria.ACCURACY_FINE);

        manager.setTestProviderEnabled(FAKE_PROVIDER_NAME, true);
        manager.setTestProviderStatus(FAKE_PROVIDER_NAME, LocationProvider.AVAILABLE, null, System.currentTimeMillis());

        Location newLocation = new Location(FAKE_PROVIDER_NAME);
        newLocation.setLatitude(100);
        newLocation.setLongitude(100);
        newLocation.setAccuracy(500);
        manager.setTestProviderLocation(FAKE_PROVIDER_NAME, newLocation);
    }

    @NonNull
    public static LocationManager detachFakeProvider(Context context) {
        LocationManager manager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        manager.removeTestProvider(FAKE_PROVIDER_NAME);
        return manager;
    }
}
