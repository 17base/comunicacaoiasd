package br.ufc.quixada.dsdm.comunicacaoiasd.adapter;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import br.ufc.quixada.dsdm.comunicacaoiasd.R;
import br.ufc.quixada.dsdm.comunicacaoiasd.app.AppController;
import br.ufc.quixada.dsdm.comunicacaoiasd.model.YouTubeVideo;

/**
 * Created by jonas_000 on 12/01/2016.
 */
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;

public class AdapterVideo extends RecyclerView.Adapter<AdapterVideo.ViewHolder> {

    private List<YouTubeVideo> itens;
    private static final String TAG = AdapterVideo.class.getSimpleName();


    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView title;
        public TextView description;
        public TextView date;
        public NetworkImageView imageView;

        public ViewHolder(View v) {
            super(v);

            title = (TextView) v.findViewById(R.id.title);
            description = (TextView) v.findViewById(R.id.description);
            date = (TextView) v.findViewById(R.id.date);
            imageView = (NetworkImageView) v.findViewById(R.id.thumbnail);
        }
    }

    public AdapterVideo(List<YouTubeVideo> itens) {
        this.itens = itens;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_row_video, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ImageLoader imageLoader = AppController.getInstance().getImageLoader();

        YouTubeVideo video = itens.get(position);
        Log.d(TAG, video.getTitle());
        holder.title.setText(video.getTitle());
        holder.description.setText(video.getDescription());
        holder.date.setText(video.getPublishedAt());
        holder.imageView.setImageUrl(video.getThumbnailMedium(), imageLoader);
    }

    @Override
    public int getItemCount() {
        return itens.size();
    }
}