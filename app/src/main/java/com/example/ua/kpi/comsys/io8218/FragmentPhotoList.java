package com.example.ua.kpi.comsys.io8218;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.constraintlayout.widget.Guideline;
import androidx.fragment.app.Fragment;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class FragmentPhotoList extends Fragment {
    private static final int RESULT_LOAD_IMAGE = 2;

    private View view;
    private ScrollView scroll;
    private LinearLayout linear;
    private ArrayList<ImageView> images;
    private ArrayList<ArrayList<Object>> addedPhotos;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.photolist, container, false);

        scroll = view.findViewById(R.id.scroll_photoList);
        linear = view.findViewById(R.id.linear);

        images = new ArrayList<>();
        addedPhotos = new ArrayList<>();

        FloatingActionButton addPhotoButton = view.findViewById(R.id.addPhotoButton);
        addPhotoButton.setOnClickListener(v -> {
            Intent gallery = new Intent(Intent.ACTION_GET_CONTENT);
            gallery.setType("image/*");
            startActivityForResult(gallery, RESULT_LOAD_IMAGE);
        });

        correctLocationOfImage();

        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RESULT_LOAD_IMAGE && resultCode == Activity.RESULT_OK) {
            Uri imageUri = data.getData();
            addImage(linear, scroll, imageUri, images, addedPhotos);
        }
    }

    private void addImage(LinearLayout linear,  ScrollView scroll, Uri imageUri, ArrayList<ImageView> images, ArrayList<ArrayList<Object>> addedPhotos) {
        ImageView newImage = new ImageView(view.getContext());
        newImage.setImageURI(imageUri);
        //newImage.setBackgroundResource(R.color.img_back);

        newImage.setScaleType(ImageView.ScaleType.CENTER_CROP);
        ConstraintLayout.LayoutParams params = new ConstraintLayout.LayoutParams(ConstraintLayout.LayoutParams.MATCH_CONSTRAINT,
                ConstraintLayout.LayoutParams.MATCH_CONSTRAINT);
        params.setMargins(3, 3, 3, 3);
        params.dimensionRatio = "1";
        newImage.setLayoutParams(params);
        newImage.setId(newImage.hashCode());

        ConstraintLayout layout = null;
        ConstraintSet set = null;
        if (images.size() > 0) {
            layout = (ConstraintLayout) getConstraintArrayList(0, addedPhotos);
            set = (ConstraintSet) getConstraintArrayList(1, addedPhotos);

            set.clone(layout);

            set.setMargin(newImage.getId(), ConstraintSet.START, 3);
            set.setMargin(newImage.getId(), ConstraintSet.TOP, 3);
            set.setMargin(newImage.getId(), ConstraintSet.END, 3);
            set.setMargin(newImage.getId(), ConstraintSet.BOTTOM, 3);
        }

        if (images.size() % 10 != 0) {
            layout.addView(newImage);
        }

        switch (images.size() % 10) {
            case 0: {
                addedPhotos.add(new ArrayList<>());

                ConstraintLayout constraint = new ConstraintLayout(view.getContext());
                addedPhotos.get(addedPhotos.size()-1).add(constraint);
                constraint.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT));
                linear.addView(constraint);

                Guideline vertical25 = makeGuideline(ConstraintLayout.LayoutParams.VERTICAL, 0.25f);
                Guideline vertical50 = makeGuideline(ConstraintLayout.LayoutParams.VERTICAL, 0.5f);
                Guideline vertical75 = makeGuideline(ConstraintLayout.LayoutParams.VERTICAL, 0.75f);

                Guideline horizontal25 = makeGuideline(ConstraintLayout.LayoutParams.HORIZONTAL, 1f);
                Guideline horizontal50 = makeGuideline(ConstraintLayout.LayoutParams.HORIZONTAL, 1f);
                Guideline horizontal75 = makeGuideline(ConstraintLayout.LayoutParams.HORIZONTAL, 1f);

                constraint.addView(vertical25, 0);
                constraint.addView(vertical50, 1);
                constraint.addView(vertical75, 2);
                constraint.addView(horizontal25, 3);
                constraint.addView(horizontal50, 4);
                constraint.addView(horizontal75, 5);

                constraint.addView(newImage);

                ConstraintSet constraintSet = new ConstraintSet();
                addedPhotos.get(addedPhotos.size()-1).add(constraintSet);
                constraintSet.clone(constraint);

                constraintSet.connect(newImage.getId(), ConstraintSet.START, ConstraintSet.PARENT_ID, ConstraintSet.START);
                constraintSet.connect(newImage.getId(), ConstraintSet.TOP, ConstraintSet.PARENT_ID, ConstraintSet.TOP);
                constraintSet.connect(newImage.getId(), ConstraintSet.END, vertical25.getId(), ConstraintSet.START);
                constraintSet.connect(newImage.getId(), ConstraintSet.BOTTOM, horizontal25.getId(), ConstraintSet.TOP);

                constraintSet.applyTo(constraint);
                break;
            }

            case 1: {
                set.setGuidelinePercent(layout.getChildAt(3).getId(), 0.5f);
                set.setGuidelinePercent(layout.getChildAt(4).getId(), 1f);
                set.setGuidelinePercent(layout.getChildAt(5).getId(), 1f);

                set.connect(newImage.getId(), ConstraintSet.START, layout.getChildAt(0).getId(), ConstraintSet.END);
                set.connect(newImage.getId(), ConstraintSet.TOP, ConstraintSet.PARENT_ID, ConstraintSet.TOP);
                set.connect(newImage.getId(), ConstraintSet.END, layout.getChildAt(2).getId(), ConstraintSet.START);
                set.connect(newImage.getId(), ConstraintSet.BOTTOM, layout.getChildAt(4).getId(), ConstraintSet.TOP);

                set.applyTo(layout);
                break;
            }

            case 2: {
                set.connect(newImage.getId(), ConstraintSet.START, layout.getChildAt(2).getId(), ConstraintSet.END);
                set.connect(newImage.getId(), ConstraintSet.TOP, ConstraintSet.PARENT_ID, ConstraintSet.TOP);
                set.connect(newImage.getId(), ConstraintSet.END, ConstraintSet.PARENT_ID, ConstraintSet.END);
                set.connect(newImage.getId(), ConstraintSet.BOTTOM, layout.getChildAt(3).getId(), ConstraintSet.TOP);

                set.applyTo(layout);
                break;
            }

            case 3: {
                set.connect(newImage.getId(), ConstraintSet.START, ConstraintSet.PARENT_ID, ConstraintSet.START);
                set.connect(newImage.getId(), ConstraintSet.TOP, layout.getChildAt(3).getId(), ConstraintSet.BOTTOM);
                set.connect(newImage.getId(), ConstraintSet.END, layout.getChildAt(0).getId(), ConstraintSet.START);
                set.connect(newImage.getId(), ConstraintSet.BOTTOM, layout.getChildAt(4).getId(), ConstraintSet.TOP);

                set.applyTo(layout);
                break;
            }

            case 4: {
                set.connect(newImage.getId(), ConstraintSet.START, layout.getChildAt(2).getId(), ConstraintSet.END);
                set.connect(newImage.getId(), ConstraintSet.TOP, layout.getChildAt(3).getId(), ConstraintSet.BOTTOM);
                set.connect(newImage.getId(), ConstraintSet.END, ConstraintSet.PARENT_ID, ConstraintSet.END);
                set.connect(newImage.getId(), ConstraintSet.BOTTOM, layout.getChildAt(4).getId(), ConstraintSet.TOP);

                set.applyTo(layout);
                break;
            }

            case 5: {
                set.setGuidelinePercent(layout.getChildAt(3).getId(), 0.25f);
                set.setGuidelinePercent(layout.getChildAt(4).getId(), 0.5f);
                set.setGuidelinePercent(layout.getChildAt(5).getId(), 0.75f);

                set.connect(newImage.getId(), ConstraintSet.START, ConstraintSet.PARENT_ID, ConstraintSet.START);
                set.connect(newImage.getId(), ConstraintSet.TOP, layout.getChildAt(4).getId(), ConstraintSet.BOTTOM);
                set.connect(newImage.getId(), ConstraintSet.END, layout.getChildAt(1).getId(), ConstraintSet.START);
                set.connect(newImage.getId(), ConstraintSet.BOTTOM, ConstraintSet.PARENT_ID, ConstraintSet.BOTTOM);

                set.applyTo(layout);
                break;
            }

            case 6: {
                set.connect(newImage.getId(), ConstraintSet.START, layout.getChildAt(1).getId(), ConstraintSet.START);
                set.connect(newImage.getId(), ConstraintSet.TOP, layout.getChildAt(4).getId(), ConstraintSet.BOTTOM);
                set.connect(newImage.getId(), ConstraintSet.END, layout.getChildAt(2).getId(), ConstraintSet.START);
                set.connect(newImage.getId(), ConstraintSet.BOTTOM, layout.getChildAt(5).getId(), ConstraintSet.TOP);

                set.applyTo(layout);
                break;
            }

            case 7: {
                set.connect(newImage.getId(), ConstraintSet.START, layout.getChildAt(2).getId(), ConstraintSet.END);
                set.connect(newImage.getId(), ConstraintSet.TOP, layout.getChildAt(4).getId(), ConstraintSet.BOTTOM);
                set.connect(newImage.getId(), ConstraintSet.END, ConstraintSet.PARENT_ID, ConstraintSet.END);
                set.connect(newImage.getId(), ConstraintSet.BOTTOM, layout.getChildAt(5).getId(), ConstraintSet.TOP);

                set.applyTo(layout);
                break;
            }

            case 8: {
                set.connect(newImage.getId(), ConstraintSet.START, layout.getChildAt(1).getId(), ConstraintSet.START);
                set.connect(newImage.getId(), ConstraintSet.TOP, layout.getChildAt(5).getId(), ConstraintSet.BOTTOM);
                set.connect(newImage.getId(), ConstraintSet.END, layout.getChildAt(2).getId(), ConstraintSet.START);
                set.connect(newImage.getId(), ConstraintSet.BOTTOM, ConstraintSet.PARENT_ID, ConstraintSet.BOTTOM);

                set.applyTo(layout);
                break;
            }

            case 9: {
                set.connect(newImage.getId(), ConstraintSet.START, layout.getChildAt(2).getId(), ConstraintSet.END);
                set.connect(newImage.getId(), ConstraintSet.TOP, layout.getChildAt(5).getId(), ConstraintSet.BOTTOM);
                set.connect(newImage.getId(), ConstraintSet.END, ConstraintSet.PARENT_ID, ConstraintSet.END);
                set.connect(newImage.getId(), ConstraintSet.BOTTOM, ConstraintSet.PARENT_ID, ConstraintSet.BOTTOM);

                set.applyTo(layout);
                break;
            }
        }

        images.add(newImage);
        scroll.post(() -> scroll.fullScroll(View.FOCUS_DOWN));
    }

    private Guideline makeGuideline(int orientation, float percent){
        Guideline guideline = new Guideline(view.getContext());
        guideline.setId(guideline.hashCode());

        ConstraintLayout.LayoutParams guidelineParams =
                new ConstraintLayout.LayoutParams(ConstraintLayout.LayoutParams.WRAP_CONTENT,
                        ConstraintLayout.LayoutParams.WRAP_CONTENT);
        guidelineParams.orientation = orientation;

        guideline.setLayoutParams(guidelineParams);

        guideline.setGuidelinePercent(percent);

        return guideline;
    }

    public void correctLocationOfImage() {
        for (ImageView image: images) {
            image.setScaleType(ImageView.ScaleType.CENTER_CROP);
        }
    }

    private Object getConstraintArrayList(int index, ArrayList<ArrayList<Object>> list){
        return list.get(list.size()-1).get(index);
    }
}
