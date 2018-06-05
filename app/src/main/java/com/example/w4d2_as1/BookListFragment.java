package com.example.w4d2_as1;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.example.w4d2_as1.Entities.BookResponse;
import com.example.w4d2_as1.Entities.Item;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * A simple {@link Fragment} subclass.
 */
public class BookListFragment extends Fragment {

    //DUMMY THIS OUT WHEN DONE
    private static final String TAG = BookListFragment.class.getSimpleName()+"_TAG";
    private static final String API_KEY = "DUMMY";

    private String query;

    private BookAPI bookAPI;

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private ProgressBar progress;

    ArrayList<BookItem> list = new ArrayList<>();

    public BookListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Bundle bundle = this.getArguments();
        query = bundle.getString("SEARCH_TERM");
        return inflater.inflate(R.layout.fragment_book_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        progress = view.findViewById(R.id.progress);
        list = getResults(query, API_KEY);
        initElements(view);
    }

    private void initElements(View view){
        recyclerView = view.findViewById(R.id.recyclerView);
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        adapter = new BookAdapter(list);
        recyclerView.setAdapter(adapter);
    }

    private ArrayList<BookItem> getResults(String query, String API_KEY){
        showProgress(true);

        getBookAPI().searchForBook(query, API_KEY).enqueue(new Callback<BookResponse>() {
            @Override
            public void onResponse(Call<BookResponse> call, Response<BookResponse> response) {
                if(response.isSuccessful()){
                    BookResponse bookResponse = response.body();

                    Log.d(TAG, "onResponse: " + bookResponse.getTotalItems().toString());
                    for(Item item:bookResponse.getItems()) {
                        if(item != null){
                            String tmpTitle = item.getVolumeInfo().getTitle();
                            List<String> tmpAuthorsList = item.getVolumeInfo().getAuthors();

                            StringBuilder sb = new StringBuilder();
                            String tmpAuthors = "";
                            if (tmpAuthorsList != null) {
                                for(int j=0; j<tmpAuthorsList.size(); j++){
                                    sb.append(tmpAuthorsList.get(j));
                                    sb.append("");
                                }

                                tmpAuthors = sb.toString();
                            }

                            String tmpCoverURL = item.getVolumeInfo().getImageLinks().getThumbnail();

                            BookItem tmp = new BookItem(tmpTitle, tmpAuthors, tmpCoverURL);
                            list.add(tmp);
                        }
                    }
                }
                showProgress(false);

            }

            @Override
            public void onFailure(Call<BookResponse> call, Throwable t) {

            }
        });

        return list;

    }

    private void showProgress(boolean isEnabled){
        if(isEnabled){
            progress.setVisibility(View.VISIBLE);
        }
        else{
            progress.setVisibility(View.GONE);
        }
    }

    private Retrofit prepareRetrofitClient(){
        return new Retrofit.Builder()
                .baseUrl(BookAPI.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    private BookAPI getBookAPI(){
        if(bookAPI == null){
            bookAPI = prepareRetrofitClient().create(BookAPI.class);
        }
        return bookAPI;
    }
}
