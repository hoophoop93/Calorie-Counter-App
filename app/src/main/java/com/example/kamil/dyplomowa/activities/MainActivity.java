package com.example.kamil.dyplomowa.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kamil.dyplomowa.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private List<String> arrayList = new ArrayList<String>();
    private TextView textView;
    private Random random = new Random();
    public static int countClickHistoryMeals=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        arrayList.add("Truskawki zawierają więcej witaminy C niż cytryny czy grejpfruty, podobnie czerwona papryka.");
        arrayList.add("Chili poprawia nastrój. Ostry smak wywołuje wydzielanie endorfin – hormonów szczęścia. ");
        arrayList.add("Borówki mogą pomagać w zwalczaniu zapalenia pęcherza moczowego i mają właściwości antybakteryjne. ");
        arrayList.add("Bobu nie powinny w dużej ilości jeść osoby zażywające leki przeciwdepresyjne. Połączenie może doprowadzić do skoku ciśnienia. ");
        arrayList.add("Cebula zapobiega powstawaniu choroby wieńcowej, ponieważ może obniżać poziom cholesterolu. ");
        arrayList.add("Odpowiednia dieta, bogata w magnez, witaminę E i B6, może zmniejszyć dolegliwości menstruacyjne. ");
        arrayList.add("Unikanie czekolady, cytrusów, serów i używek wpływa na zmniejszenie występowania i nasilenia bólów migrenowych. ");
        arrayList.add("Herbata czarna pita podczas posiłku mięsnego powoduje ograniczenie wchłaniania żelaza do organizmu. ");
        arrayList.add("Cholesterol zawarty w jajkach nie wpływa na podwyższenie cholesterolu całkowitego w organizmie. ");
        arrayList.add("Tabliczka ciemnej czekolady (125g) zawiera więcej kofeiny niż filiżanka kawy instant.");
        arrayList.add("Dieta zawierająca produkty zasadotwórcze (np. mleko, warzywa) wspomaga rzucenie palenia papierosów, obniżając " +
                "eliminację nikotyny z organizmu, co opóźnia powstawanie głodu nikotynowego. ");
        arrayList.add("Niedobór snu wzmaga uczucie głodu i chęć jedzenia.");
        arrayList.add("O wadze ciała decyduje przede wszystkim ilość przyswajanych kalorii a nie rodzaj prowadzonej diety.");
        arrayList.add("Post i lekkie głodzenie korzystnie wpływają na naszą pamięć.");
        arrayList.add("Mężczyźni łatwiej przechodzą post lub lekkie głodówki, gdyż nie myślą o jedzeniu tak często jak kobiety.");
        arrayList.add("Od cukru można się uzależnić w stopniu podobnym do uzależnienia od nikotyny.");
        arrayList.add("Osoby z nadwagą słabiej smakują cukier, z tego powodu częściej sięgają po słodycze.");
        arrayList.add("Udowodniono, że spożywanie jajek na śniadanie korzystniej wpływa na sylwetkę, niż spożywanie dżemu lub marmolady.");
        arrayList.add("Sport sam w sobie nie jest w stanie zredukować masy ciała, niezbędne jest przestrzeganie zasad zdrowego odżywiania.");
        arrayList.add("Włoscy naukowcy dowiedli, że regularne picie kawy jest w stanie obniżyć ryzyko zachorowania na raka wątroby nawet o 55%.");

        dynamicalText.start();

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

  /*  @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }*/

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_calculator) {
            Toast.makeText(this, "Kalkulatory", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(getApplicationContext(), Calculators.class);
            startActivity(intent);
        } else if (id == R.id.nav_search) {
            Toast.makeText(this, "Znajdź produkt", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(getApplicationContext(), ProductCategory.class);
            startActivity(intent);
        } else if (id == R.id.nav_add) {
            Toast.makeText(this, "Dodaj produkt", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(getApplicationContext(), SelectProductTable.class);
            startActivity(intent);
        } else if (id == R.id.nav_log) {
            Toast.makeText(this, "Dziennik aktywności", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(getApplicationContext(), LogActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_aboutUs) {
            Toast.makeText(this, "O nas", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(getApplicationContext(), AboutAplication.class);
            startActivity(intent);
        }
        else if (id == R.id.nav_history) {
            countClickHistoryMeals++;
            Toast.makeText(this, "Historia posiłków", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(getApplicationContext(), Meals.class);
            startActivity(intent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    Thread dynamicalText = new Thread() {

        @Override
        public void run() {
            String result = arrayList.get(random.nextInt(arrayList.size()));
            textView = (TextView) findViewById(R.id.textView);
            textView.setText(result);
            try {
                while (!isInterrupted()) {
                    Thread.sleep(9000);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Collections.shuffle(arrayList);
                            String result = arrayList.get(random.nextInt(arrayList.size()));
                            textView = (TextView) findViewById(R.id.textView);
                            textView.setText(result);
                        }
                    });
                }
            } catch (InterruptedException e) {
            }
        }
    };


}
