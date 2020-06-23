package com.example.image_search.View.UI;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.image_search.R;
import com.example.image_search.Service.Entity.ImageDescription;
import com.example.image_search.View.Adapters.FavouritesListAdapter;
import com.example.image_search.View.Adapters.SearchImageListAdapter;
import com.example.image_search.ViewModel.FavouritesViewModel;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Favourite_Fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Favourite_Fragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Favourite_Fragment() {
        // Required empty public constructor
    }
    ArrayList<ImageDescription> favouriteArrayList = new ArrayList<>();
    FavouritesListAdapter favouriteAdapter;
    FavouritesViewModel favouriteViewModel;
    RecyclerView rvHeadline;

    private FavouritesListAdapter.OnItemClickListener favouriteAdapterInterface;
    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Favourite_Fragment.
     */
    // TODO: Rename and change types and number of parameters
    public static Favourite_Fragment newInstance(String param1, String param2) {
        Favourite_Fragment fragment = new Favourite_Fragment();
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
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootview = inflater.inflate(R.layout.fragment_favourite, container, false);
        Toolbar toolbar = getActivity().findViewById(R.id.toolbar);
        toolbar.setTitle("Favourite Image");
        setupAdapter();
        setupRecyclerView(rootview);
        return rootview;
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        InitAndObserveModel();

    }
    private void InitAndObserveModel() {
        favouriteViewModel = ViewModelProviders.of(requireActivity()).get(FavouritesViewModel.class);
        favouriteViewModel.getAllFavourites().observe(getViewLifecycleOwner(), new Observer<List<ImageDescription>>() {
            @Override
            public void onChanged(@Nullable List<ImageDescription> favourites) {
                if (favourites != null) {
                    favouriteAdapter.setImageList(favourites);
                }
                else {
                    Log.e("observerresponse", "NULL");
                }
            }
        });
    }
    public void setupAdapter(){
        favouriteAdapterInterface = new FavouritesListAdapter.OnItemClickListener(){
            @Override
            public void onItemClick(ImageDescription imageDescription) {
                favouriteViewModel.onToggleClicked(imageDescription);
            }
        };
        favouriteAdapter = new FavouritesListAdapter(getContext(), this, favouriteArrayList, favouriteAdapterInterface );
    }

    public void setupRecyclerView(@NonNull View view){
        rvHeadline = view.findViewById(R.id.recycleView);
        rvHeadline.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvHeadline.setAdapter(favouriteAdapter);
        rvHeadline.setNestedScrollingEnabled(true);
    }
}