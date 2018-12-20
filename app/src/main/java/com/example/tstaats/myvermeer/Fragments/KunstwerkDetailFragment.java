package com.example.tstaats.myvermeer.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tstaats.myvermeer.MainActivity;
import com.example.tstaats.myvermeer.R;

public class KunstwerkDetailFragment extends Fragment {

    private static final String TAG = "KunstwerkDetailFragment";

    private MainActivity mainActivity;
    private Button backBtn;
    private ImageView iv;
    private TextView titleTv;
    private TextView artistTv;
    private TextView imageValueTv;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        Log.d(TAG, "onCreateView: is called");
        View view = inflater.inflate(R.layout.fragment_kunstwerkdetail, container, false);

        backBtn = view.findViewById(R.id.button_back_kunstdetail);
        iv = view.findViewById(R.id.imageView_art_detail);
        titleTv = view.findViewById(R.id.text_kunstdetail_title);
        artistTv = view.findViewById(R.id.text_kunstdetail_artist);
        imageValueTv = view.findViewById(R.id.text_kunstdetail_imageValue);

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainActivity.fragmentSwitcher(new GalleryFragment(), "GalleryFragment");
            }
        });



        if (getArguments() != null){
            String artistName = getArguments().getString("artistName");
            String imageName = getArguments().getString("imageName");
            int imageId = getArguments().getInt("imageId");
            int imageValue = getArguments().getInt("imageValue");

            iv.setImageResource(imageId);
            titleTv.setText(imageName);
            artistTv.setText(artistName);
            imageValueTv.setText(imageValue + "€");
        } else {

            iv.setImageResource(R.drawable.aftersunset);
            titleTv.setText("Mountain");
            artistTv.setText("Mao");

        }


        return view;

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mainActivity = (MainActivity) getActivity();
    }
}
