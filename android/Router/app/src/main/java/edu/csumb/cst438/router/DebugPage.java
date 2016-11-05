package edu.csumb.cst438.router;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

public class DebugPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_debug_page);
    }

    public void openRecordActivity(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void openMyRoutesActivity(View view) {
        Intent intent = new Intent(this, MyRoutes.class);
        startActivity(intent);
    }

    public void openFriendsActivity(View view) {
        Intent intent = new Intent(this, Friends.class);
        startActivity(intent);
    }

    public void openProfileActivity(View view) {
        Intent intent = new Intent(this, Profile.class);
        startActivity(intent);
    }

    public void openLoginActivity(View view) {
        Intent intent = new Intent(this, Login.class);
        startActivity(intent);
    }

    public void MartinTest(View view) {
        DeBra deBra = new DeBra(((Application) this.getApplication()).getDB());

        //deBra.insertRoute("1", "route", "one", "two", "three", "four");

        Route route = new Route(false, 1, "route", "lat", "lon", 2, "name");
        //deBra.insertRoute(route);
        deBra.deleteRoute(2);
        Log.d("test", deBra.getAllLocalRoutes().toString());

        Route route2 = new Route(false, 2, "route", "lat", "lon", 2, "name");
        //deBra.insertRoute(route2);
        Log.d("test", deBra.getAllLocalRoutes().toString());

    }
}
