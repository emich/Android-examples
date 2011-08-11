package be.emich.labs.mapviewexample;

import android.os.Bundle;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;

public class MapViewExampleActivity extends MapActivity {
	private MapView mapView;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        mapView = (MapView)findViewById(R.id.mapview);
        mapView.setBuiltInZoomControls(true);
        
        MapController mapController = mapView.getController();
        mapController.setCenter(new GeoPoint(50795700, 4417830));
        mapController.setZoom(18);
    }

	@Override
	protected boolean isRouteDisplayed() {
		return false;
	}
}