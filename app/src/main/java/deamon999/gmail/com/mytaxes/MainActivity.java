package deamon999.gmail.com.mytaxes;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView menu_bottom;
    private FragmentManager fragmentManager;
    private SharedPreferences preferences;
    private ArrayList<Item> favorits = new ArrayList<>();
    private Gson gson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        preferences = getPreferences(Activity.MODE_PRIVATE);
        gson = new Gson();
        if (preferences.contains("list")) {
            favorits = gson.fromJson(preferences.getString("list", ""), new TypeToken<List<Item>>(){}.getType());

        }

        menu_bottom = findViewById(R.id.bottom_menu_activity);
        menu_bottom.setItemIconTintList(null);
        fragmentManager = getSupportFragmentManager();


        menu_bottom.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.action_one:
                        BlankFragment mainFragment = new BlankFragment();
                        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                        fragmentTransaction.replace(R.id.fragment_container, mainFragment);
                        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                        fragmentTransaction.addToBackStack("mainPage");
                        fragmentTransaction.commit();
                        break;
                    case R.id.action_two:
                        BlankFragment2 favoritFragment = new BlankFragment2();
                        FragmentTransaction fragmentTransaction1 = fragmentManager.beginTransaction();
                        fragmentTransaction1.replace(R.id.fragment_container, favoritFragment);
                        fragmentTransaction1.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                        fragmentTransaction1.addToBackStack("searchPage");
                        fragmentTransaction1.commit();
                        break;
                }
                return true;
            }
        });

        BlankFragment mainFragment = new BlankFragment();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, mainFragment);
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        fragmentTransaction.commit();
    }

    @Override
    protected void onStop() {
        super.onStop();
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("list", gson.toJson(favorits));
        editor.commit();
    }

    public void onFragmentInteraction(int action) {
        menu_bottom.getMenu().findItem(action).setChecked(true);
    }

    public ArrayList<Item> getFavorits() {
        return favorits;
    }
}
