package com.example.image_search.View.UI;

import android.app.SearchManager;
import android.content.Context;
import android.media.Image;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavDirections;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;

import com.example.image_search.R;
import com.example.image_search.Service.Model.ImageDescription;
import com.example.image_search.Service.Model.Response_Data;
import com.example.image_search.View.Adapters.SearchImageListAdapter;
import com.example.image_search.View.Callback.ImageClickCallback;
import com.example.image_search.ViewModel.SearchImagesViewModel;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Search_Fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Search_Fragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    ArrayList<ImageDescription> imagesArrayList = new ArrayList<>();
    SearchImageListAdapter imagesAdapter;
    SearchImagesViewModel imagesViewModel;
    RecyclerView rvHeadline;

    private SearchView searchView = null;
    private SearchView.OnQueryTextListener queryTextListener;

    public Search_Fragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Search_Fragment.
     */
    // TODO: Rename and change types and number of parameters
    public static Search_Fragment newInstance(String param1, String param2) {
        Search_Fragment fragment = new Search_Fragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootview = inflater.inflate(R.layout.fragment_search, container, false);
        Toolbar toolbar = getActivity().findViewById(R.id.toolbar);
        toolbar.setTitle("Seach Images");
        setupAdapter();
        setupRecyclerView(rootview);
        return rootview;
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        InitAndObserveModel();

    }
    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.menu_search, menu);

        // find searchView
        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchManager searchManager = (SearchManager) getActivity().getSystemService(Context.SEARCH_SERVICE);

        searchView = (SearchView) searchItem.getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getActivity().getComponentName()));
        searchView.setMaxWidth(Integer.MAX_VALUE);
        searchView.setQueryHint("Search images ...");
        searchView.setIconifiedByDefault(false);

        queryTextListener = new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextChange(String newText) {
                Log.e("onQueryTextChange", newText);

                return true;
            }
            @Override
            public boolean onQueryTextSubmit(String query) {
                imagesViewModel.onsubmitQuery(query);
                imagesViewModel.getImageRepository().observe(getViewLifecycleOwner(), response_data -> {

                    imagesAdapter.setImageList(response_data.getImaghes());

                });
                searchView.clearFocus();
                return true;
            }
        };
        searchView.setOnQueryTextListener(queryTextListener);

        super.onCreateOptionsMenu(menu, inflater);
    }

    private void InitAndObserveModel() {
        imagesViewModel = ViewModelProviders.of(requireActivity()).get(SearchImagesViewModel.class);
        imagesViewModel.init();
        imagesViewModel.getImageRepository().observe(getViewLifecycleOwner(), response_data -> {

            imagesAdapter.setImageList(response_data.getImaghes());

        });
    }
    public void setupAdapter(){
        imagesAdapter = new SearchImageListAdapter(getContext(), this, imagesArrayList);
    }

    public void setupRecyclerView(@NonNull View view){
        rvHeadline = view.findViewById(R.id.rvNews);
        rvHeadline.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvHeadline.setAdapter(imagesAdapter);
        rvHeadline.setNestedScrollingEnabled(true);
    }


}