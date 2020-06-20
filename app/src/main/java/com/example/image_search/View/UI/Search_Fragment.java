package com.example.image_search.View.UI;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.image_search.R;
import com.example.image_search.Service.Model.ImageDescription;
import com.example.image_search.Service.Model.Response_Data;
import com.example.image_search.View.Adapters.SearchImageListAdapter;
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
    RecyclerView rvHeadline;
    SearchImagesViewModel imagesViewModel;
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
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootview = inflater.inflate(R.layout.fragment_search, container, false);


        return rootview;
    }
    @Override public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        imagesViewModel = ViewModelProviders.of(requireActivity()).get(SearchImagesViewModel.class);
        imagesViewModel.init();
        rvHeadline = view.findViewById(R.id.rvNews);


        imagesAdapter = new SearchImageListAdapter(getActivity(), imagesArrayList);
        rvHeadline.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvHeadline.setAdapter(imagesAdapter);
        rvHeadline.setNestedScrollingEnabled(true);

        imagesViewModel.getImageRepository().observe(getActivity(), newsResponse -> {
            List<ImageDescription> images = newsResponse.getImaghes();

            imagesArrayList.addAll(images);
            Log.e("imagesArrayList", String.valueOf(images.get(0).getImage()));
            imagesAdapter.notifyDataSetChanged();
            // Log.e("response", String.valueOf(newsResponse.getImaghes()));
        });

    }

}