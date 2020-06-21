package com.example.image_search.View.UI;

import android.os.Bundle;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.image_search.R;
import com.squareup.picasso.Picasso;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ImageDetail_Fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ImageDetail_Fragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "url";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ImageDetail_Fragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ImageDetail_Fragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ImageDetail_Fragment newInstance(String param1, String param2) {
        ImageDetail_Fragment fragment = new ImageDetail_Fragment();
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
        View rootview = inflater.inflate(R.layout.fragment_image_detail, container, false);
        ImageView image_detail = rootview.findViewById(R.id.image);
        // Inflate the layout for this fragment
        Log.e("DETAIL_IMAGE", mParam1);
        Picasso.get().load(mParam1).into(image_detail);
        return rootview;
    }
    public static ImageDetail_Fragment forImage(String url) {
        ImageDetail_Fragment fragment = new ImageDetail_Fragment();
        Bundle args = new Bundle();

        args.putString(ARG_PARAM1, url);
        fragment.setArguments(args);

        return fragment;
    }
}