package com.example.w4d2_as1;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 */
public class SearchFragment extends Fragment implements View.OnClickListener {

    private EditText searchET;
    private Button searchBTN;

    private SearchButtonListener listener;

    public SearchFragment() {
        // Required empty public constructor
    }

    public void onAttach(Context context){
        super.onAttach(context);
        try{
            listener = (SearchButtonListener) getActivity();
        }
        catch(ClassCastException e){

        }
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_search, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        searchET = view.findViewById(R.id.searchET);
        searchBTN = view.findViewById(R.id.searchBTN);
        searchBTN.setOnClickListener(this);
    }

    public interface SearchButtonListener{
        public void ShowResults(String query);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.searchBTN:
                String query = searchET.getText().toString();
                listener.ShowResults(query);

        }
    }
}
