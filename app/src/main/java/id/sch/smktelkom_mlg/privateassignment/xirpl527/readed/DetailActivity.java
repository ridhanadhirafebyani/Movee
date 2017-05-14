package id.sch.smktelkom_mlg.privateassignment.xirpl527.readed;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class DetailActivity extends AppCompatActivity {
    private static final String URL_DATA = "https://api.themoviedb.org/3/movie/top_rated?api_key=1029a4f1003dd4d03181bb24eda5b026";
    public TextView tvJudul;
    public TextView tvJudulAsli;
    public TextView tvTahun;
    public TextView tvRating;
    public TextView overview;
    public ImageView imageViewDetail;
    public ImageView bg;
    public String url;
    private Integer mPostkey = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mPostkey = getIntent().getExtras().getInt("blog_id");
        loadRecyclerViewData();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uri = Uri.parse(url);
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);

                Snackbar.make(view, "SAVE", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        tvJudul = (TextView) findViewById(R.id.tvJudul);
        tvJudulAsli = (TextView) findViewById(R.id.tvJudulAsli);
        tvTahun = (TextView) findViewById(R.id.tahunRilis);
        tvRating = (TextView) findViewById(R.id.voteAv);
        overview = (TextView) findViewById(R.id.overview);
        imageViewDetail = (ImageView) findViewById(R.id.imageBack);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    private void loadRecyclerViewData() {

        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading data..");
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.GET,
                URL_DATA,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
                        progressDialog.dismiss();
                        try {
                            JSONObject jsonObject = new JSONObject(s);
                            JSONArray array = jsonObject.getJSONArray("results");
                            JSONObject o = array.getJSONObject(mPostkey);

                            String urll = "http://image.tmdb.org/t/p/w500";

                            setTitle(" ");

                            tvJudul.setText(o.getString("title"));
                            tvJudulAsli.setText(o.getString("original_title"));
                            tvRating.setText(o.getString("vote_average"));
                            tvTahun.setText(o.getString("release_date"));
                            overview.setText(o.getString("overview"));
                            url = o.getString("backdrop_path");

                            Glide
                                    .with(DetailActivity.this)
                                    .load("http://image.tmdb.org/t/p/w500" + (o.getString("backdrop_path")))
                                    .into(imageViewDetail);


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}
