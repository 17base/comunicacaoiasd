package br.ufc.quixada.dsdm.comunicacaoiasd.fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import br.ufc.quixada.dsdm.comunicacaoiasd.R;
import br.ufc.quixada.dsdm.comunicacaoiasd.adapter.ExpandableListAdapter;


/**
 * A simple {@link Fragment} subclass.
 */
public class ItinerarioFragment extends Fragment {
    Context context;
    RecyclerView recyclerview;
    ExpandableListAdapter.Item mes;

    public ItinerarioFragment() {
        // Required empty public constructor
    }

    public ItinerarioFragment(Context context) {
        this.context = context;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_itinerario, container, false);

        recyclerview = (RecyclerView) view.findViewById(R.id.recycler_view_itinerario);
        recyclerview.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
        List<ExpandableListAdapter.Item> data = new ArrayList<>();

        mes = new ExpandableListAdapter.Item(ExpandableListAdapter.HEADER, "Janeiro");
        mes.invisibleChildren = new ArrayList<>();
        mes.invisibleChildren.add(new ExpandableListAdapter.Item(ExpandableListAdapter.CHILD, "19 | Messejana"));
        mes.invisibleChildren.add(new ExpandableListAdapter.Item(ExpandableListAdapter.CHILD, "20 | Paupina"));
        mes.invisibleChildren.add(new ExpandableListAdapter.Item(ExpandableListAdapter.CHILD, "21 | Santa Maria"));

        data.add(mes);

        mes = new ExpandableListAdapter.Item(ExpandableListAdapter.HEADER, "Fevereiro");
        mes.invisibleChildren = new ArrayList<>();
        mes.invisibleChildren.add(new ExpandableListAdapter.Item(ExpandableListAdapter.CHILD, "19 | Messejana"));
        mes.invisibleChildren.add(new ExpandableListAdapter.Item(ExpandableListAdapter.CHILD, "20 | Paupina"));
        mes.invisibleChildren.add(new ExpandableListAdapter.Item(ExpandableListAdapter.CHILD, "21 | Santa Maria"));

        data.add(mes);

        mes = new ExpandableListAdapter.Item(ExpandableListAdapter.HEADER, "Março");
        mes.invisibleChildren = new ArrayList<>();
        mes.invisibleChildren.add(new ExpandableListAdapter.Item(ExpandableListAdapter.CHILD, "19 | Messejana"));
        mes.invisibleChildren.add(new ExpandableListAdapter.Item(ExpandableListAdapter.CHILD, "20 | Paupina"));
        mes.invisibleChildren.add(new ExpandableListAdapter.Item(ExpandableListAdapter.CHILD, "21 | Santa Maria"));

        data.add(mes);

        recyclerview.setAdapter(new ExpandableListAdapter(data));

        return view;
    }

}
