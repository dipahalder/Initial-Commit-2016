package me.dipsikha.initialcommit;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Displays feed of images from Parse database with my paintings; click to expand into a fragment
 * that displays their location and caption
**/
public class MainActivity extends FragmentActivity {

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);

        recyclerView.setLayoutManager(new GridLayoutManager(MainActivity.this, 2));
        final ParseQuery<ParseObject> query = new ParseQuery<ParseObject>("Painting");
        query.findInBackground(new FindCallback<ParseObject>() {
            public void done(List<ParseObject> objects, ParseException e) {
                if (e == null) {
                    MyAdapter mAdapter = new MyAdapter(objects);
                    recyclerView.setAdapter(mAdapter);
                } else {
                    e.printStackTrace();
                }
            }
        });
    }

    //UX Toast
    @Override
    protected void onStart() {
        super.onStart();
        Context context = getApplicationContext();
        CharSequence text = "Click around to explore!";
        int duration = Toast.LENGTH_LONG;
        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }

    public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
        private List<Painting> itemsData;

        public MyAdapter(List<ParseObject> objects) {
            itemsData = new ArrayList<Painting>();
            for (int i = 0; i < objects.size(); i++) {
                String url = objects.get(i).getParseFile("image").getUrl();
                String caption = objects.get(i).getString("caption");
                String location = objects.get(i).getString("location");
                Painting p = new Painting(url, location, caption);
                itemsData.add(p);
            }
        }

        @Override
        public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemLayoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.grid_preview, null);
            ViewHolder viewHolder = new ViewHolder(itemLayoutView);
            return viewHolder;
        }


        @Override
        public void onBindViewHolder(ViewHolder viewHolder, int position) {
            final Painting p = itemsData.get(position);
            Picasso.with(getApplicationContext())
                    .load(p.getUrl()).into(viewHolder.imgViewIcon);
            viewHolder.imgViewIcon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Fragment fragment = DetailViewFragment.newInstance(p);
                    FragmentTransaction ft = getFragmentManager().beginTransaction();
                    ft.add(R.id.main, fragment, DetailViewFragment.TAG);
                    ft.commit();
                }
            });
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            public ImageView imgViewIcon;

            public ViewHolder(View itemLayoutView) {
                super(itemLayoutView);
                imgViewIcon = (ImageView) itemLayoutView.findViewById(R.id.item_icon);
            }
        }

        @Override
        public int getItemCount() {
            return itemsData.size();
        }
    }
}
