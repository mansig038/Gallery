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

import java.util.ArrayList;
import java.util.List;

import gallery.mansi.R;
import gallery.mansi.recentImagesModel.Photo;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ImageViewHolder> {
    private Context context;
    private List<Photo> photo;

    public MyAdapter(Context context, List<Photo> photo) {
        this.context = context;
        this.photo = photo;
    }

    @NonNull
    @Override
    public ImageViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.row_photos, viewGroup, false);
        return new ImageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ImageViewHolder myViewHolder, int i) {
        myViewHolder.id.setText(photo.get(i).getId());
        Glide.with(context).load(photo.get(i).getUrlS()).into(myViewHolder.image);
    }

    @Override
    public int getItemCount() {
        return photo.size();
    }

    public void updateList(List<Photo> newList) {
        photo = new ArrayList<>();
        photo.addAll(newList);
        notifyDataSetChanged();
    }

    class ImageViewHolder extends RecyclerView.ViewHolder {
        private ImageView image;
        private TextView id;

        ImageViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.image);
            id = itemView.findViewById(R.id.idImage);
        }
    }
}

