package com.example.apara.criminalintent;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class DialogFragment extends android.support.v4.app.DialogFragment {

    private static final String PHOTO_PATH = "path";

    private String mPhotoPath;


    public DialogFragment() {
    }

    public static DialogFragment newInstance(String path) {
        Bundle args = new Bundle();
        args.putSerializable(PHOTO_PATH, path);

        DialogFragment fragment = new DialogFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (getArguments() != null) {
            mPhotoPath = getArguments().getString(PHOTO_PATH);
        }
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_dialog, container, false);
        ImageView fullImage = v.findViewById(R.id.image_preview);
        Bitmap bitmap = PictureUtils.getScaledBitmap(mPhotoPath, getActivity());
        fullImage.setImageBitmap(bitmap);
        fullImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDialog().dismiss();
            }
        });
        return v;
    }


}
