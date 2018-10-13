package gallery.mansi;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

class MyAdapter extends RecyclerView.Adapter<MyAdapter.ImageViewHolder> {

    private Context context;
    private Photos photos;

    MyAdapter(Context context, Photos photo) {
        this.context = context;
        this.photos = photo;
    }

    @NonNull
    @Override
    public ImageViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.row_photos, viewGroup, false);
        return new ImageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ImageViewHolder myViewHolder, int i) {
        Photo p = photos.getPhoto().get(i);

        Glide.with(context).load(p.getUrlS()).into(myViewHolder.image);
        myViewHolder.id.setText(p.getId());
    }

    @Override
    public int getItemCount() {
        return photos.getPerpage();
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

/* Pattern pattern=Pattern.compile("<img[^>]+src\\s*=\\s*['\"]([^'\"]+)['\"][^>]*>");
        Matcher m=pattern.matcher(list.getUrlS());
        List<String> tokens=new ArrayList<>();
        while (m.find()){
            String token=m.group(1);
            tokens.add(token);
        }*/