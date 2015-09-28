package me.dipsikha.initialcommit;

/**
 * Created by dipsikhahalder on 9/27/15.
 */

import android.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

/**
 * Intermediate view that is displayed when a user clicks on painting on the feed
 */
public class DetailViewFragment extends Fragment {
    public static final String TAG = "DETAIL";


    private static final String CAPTION = "CAPTION";
    private static final String LOCATION = "LOCATION";
    private static final String URL = "URL";


    private ImageButton mCancelButton;
    private TextView mLocation;
    private ImageView mPainting;
    private TextView mCaption;

    public static Fragment newInstance(Painting painting) {
        DetailViewFragment detailViewFragment = new DetailViewFragment();
        Bundle pinData = new Bundle();
        pinData.putString(CAPTION, painting.getCaption());
        pinData.putString(LOCATION, painting.getLocation());
        pinData.putString(URL, painting.getUrl());
        detailViewFragment.setArguments(pinData);
        return detailViewFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.detail_view_fragment, container, false);
        mLocation = (TextView) view.findViewById(R.id.location);
        mCaption = (TextView) view.findViewById(R.id.caption);
        mPainting = (ImageView) view.findViewById(R.id.painting);
        mCancelButton = (ImageButton) view.findViewById(R.id.cancel);
        mCancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment f = getActivity().getFragmentManager().findFragmentByTag(TAG);
                if (f != null)
                    getActivity().getFragmentManager().beginTransaction().remove(f).commit();

            }
        });

        Bundle data = getArguments();
        if (data.getString(URL) != null) {
            Picasso.with(getActivity().getApplicationContext())
                    .load(data.getString(URL)).into(mPainting);
        }
        if (data.getString(LOCATION) != null) {
            mLocation.setText(data.getString(LOCATION));

        }
        if (data.getString(CAPTION) != null) {
            mCaption.setText(data.getString(CAPTION));
        }
        return view;
    }

}
