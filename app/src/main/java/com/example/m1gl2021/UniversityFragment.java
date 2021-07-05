package com.example.m1gl2021;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.example.m1gl2021.databinding.FragmentUniversityBinding;

public class UniversityFragment extends Fragment implements OnMapReadyCallback {

    private GoogleMap mMap;
    private FragmentUniversityBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_university, container, false);

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        return view;
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng isiDakar = new LatLng(14.6864849, -17.4547941);
        mMap.addMarker(new MarkerOptions().position(isiDakar).title("ISI").snippet("Contact: +221338221981, Site: http://groupeisi.com/"));

        LatLng ucadDakar = new LatLng(14.692776, -17.4617744);
        mMap.addMarker(new MarkerOptions().position(ucadDakar).title("UCAD").snippet("Contact: +221784946215, Site: http://www.ucad.sn/"));

        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(isiDakar, 65));
        CircleOptions co = new CircleOptions().center(isiDakar).radius(500).fillColor(Color.BLUE).strokeColor(Color.BLACK).strokeWidth(3);
        mMap.addCircle(co);
        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                if(marker.getTitle().equals("ISI")){
                   Intent intent  = new Intent(Intent.ACTION_DIAL, Uri.parse("tel: 781272661"));
                   startActivity(intent);
                }else {
                    Intent intent  = new Intent(Intent.ACTION_SENDTO, Uri.parse("sms: 781272661"));
                    intent.putExtra("sms_body", "Hello Berose");
                    startActivity(intent);
                }
                return false;
            }
        });
    }
}