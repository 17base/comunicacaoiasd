package br.ufc.quixada.dsdm.comunicacaoiasd.fragments;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.util.LruCache;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;
import java.util.List;

import br.ufc.quixada.dsdm.comunicacaoiasd.R;
import br.ufc.quixada.dsdm.comunicacaoiasd.adapter.AdapterVideo;
import br.ufc.quixada.dsdm.comunicacaoiasd.adapter.RecyclerItemClickListener;
import br.ufc.quixada.dsdm.comunicacaoiasd.model.SearchResult;
import br.ufc.quixada.dsdm.comunicacaoiasd.model.YouTubeVideo;


public class VideoFragment extends Fragment {

    private Context context;
    private SearchResult searchResult;

    public VideoFragment(Context context, SearchResult searchResult){
        this.context = context;
        this.searchResult = searchResult;
    }

    private static String TAG = VideoFragment.class.getSimpleName();

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapterNotification;
    private RecyclerView.LayoutManager layoutManager;
    private AdapterVideo adapter;

    private List<YouTubeVideo> youTubeVideos;

    private RequestQueue requestQueue;
    private ImageLoader imageLoader;


    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_video, container, false);

        try {
            requestQueue = Volley.newRequestQueue(context);

            recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view_video);
            recyclerView.setHasFixedSize(true);
            layoutManager = new LinearLayoutManager(context);
            recyclerView.setLayoutManager(layoutManager);

            // thumbnails cache
            imageLoader = new ImageLoader(requestQueue, new ImageLoader.ImageCache() {
                private final LruCache<String, Bitmap> cache = new LruCache<String, Bitmap>(5000);

                @Override
                public void putBitmap(String url, Bitmap bitmap) {
                    cache.put(url, bitmap);
                }

                @Override
                public Bitmap getBitmap(String url) {
                    return cache.get(url);
                }
            });

            if (searchResult == null) {
                youTubeVideos = new ArrayList<>();
            }else {
                youTubeVideos = searchResult.getYouTubeVideos();
            }

            adapter = new AdapterVideo(youTubeVideos);
            recyclerView.setAdapter(adapter);

            recyclerView.addOnItemTouchListener(
                    new RecyclerItemClickListener(context, new RecyclerItemClickListener.OnItemClickListener() {
                        @Override
                        public void onItemClick(View view, int position) {

                            if (!youTubeVideos.isEmpty() && youTubeVideos != null) {
                                YouTubeVideo youTubeVideo = youTubeVideos.get(position);
                                context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube://" + youTubeVideo.getVideoId())));
                            }
                        }
                    })
            );

        } catch (Exception e) {
            Log.e(TAG, e.toString());
        }

        return view;
    }

}
