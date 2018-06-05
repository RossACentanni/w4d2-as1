package com.example.w4d2_as1;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity implements SearchFragment.SearchButtonListener {

    public static final String API_KEY = "AIzaSyCKNt0w4nLCTqxRJzGE2EHybYYYTIrIJSw";

    FragmentManager fragmentManager;
    SearchFragment searchFrag;
    BookListFragment listFrag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragmentManager = getSupportFragmentManager();
        searchFrag = new SearchFragment();

        fragmentManager.beginTransaction()
                .replace(R.id.container, searchFrag)
                .commit();
    }

    @Override
    public void ShowResults(String query) {
        listFrag = new BookListFragment();

        Bundle bundle = new Bundle();
        bundle.putString("SEARCH_TERM", query);
        listFrag.setArguments(bundle);

        fragmentManager.beginTransaction()
                .replace(R.id.container, listFrag)
                .commit();
    }
}
