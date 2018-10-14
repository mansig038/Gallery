package gallery.mansi.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import gallery.mansi.R;
import gallery.mansi.searchModel.Photo;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.SearchHolder> {
    private Context context;
    private List<Photo> newList;

    public SearchAdapter(Context context, List<Photo> newList) {
        this.context = context;
        this.newList = newList;
    }

    @NonNull
    @Override
    public SearchAdapter.SearchHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.row_photos, viewGroup, false);
        return new SearchAdapter.SearchHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchAdapter.SearchHolder myViewHolder, int i) {
        myViewHolder.id.setText(newList.get(i).getId());
        Glide.with(context).load(newList.get(i).getUrlS()).into(myViewHolder.image);
    }

    @Override
    public int getItemCount() {
        return newList.size();
    }

    class SearchHolder extends RecyclerView.ViewHolder {
        private ImageView image;
        private TextView id;

        SearchHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.image);
            id = itemView.findViewById(R.id.idImage);
        }
    }
}


