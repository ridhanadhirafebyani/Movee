package id.sch.smktelkom_mlg.privateassignment.xirpl527.readed;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.List;


/**
 * Created by Ridha Nadhira Febyan on 14-May-17.
 */

public class ReadAdapter extends RecyclerView.Adapter<ReadAdapter.ViewHolder> {
    private List<ItemList> listItems;
    private Context context;

    public ReadAdapter(List<ItemList> listItems, Context context) {
        this.listItems = listItems;
        this.context = context;
    }

    @Override
    public ReadAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_list, parent, false);
        return new ReadAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        final ItemList itemList = listItems.get(position);

        holder.textViewJudul.setText(itemList.getJudul());
        holder.textViewDesc.setText(itemList.getDesc());

        Glide.with(context)
                .load("http://image.tmdb.org/t/p/w500" + itemList.getImageUrl())
                .into(holder.imageView);

        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(context, "Detail for " + itemList.getJudul(), Toast.LENGTH_LONG).show();
                Intent singleBlogIntent = new Intent(context, DetailActivity.class);
                singleBlogIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                singleBlogIntent.putExtra("blog_id", position);
                context.startActivity(singleBlogIntent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listItems.size();


    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView textViewJudul;
        public TextView textViewDesc;
        public ImageView imageView;
        public LinearLayout linearLayout;


        public ViewHolder(View itemView) {
            super(itemView);

            textViewJudul = (TextView) itemView.findViewById(R.id.textViewJudul);
            textViewDesc = (TextView) itemView.findViewById(R.id.textViewDesc);
            imageView = (ImageView) itemView.findViewById(R.id.imageView);
            linearLayout = (LinearLayout) itemView.findViewById(R.id.linearLayout);

        }
    }
}
