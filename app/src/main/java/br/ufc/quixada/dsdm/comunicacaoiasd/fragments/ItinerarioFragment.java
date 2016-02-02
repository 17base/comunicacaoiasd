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
import java.util.Arrays;
import java.util.List;

import br.ufc.quixada.dsdm.comunicacaoiasd.R;
import br.ufc.quixada.dsdm.comunicacaoiasd.adapter.ExpandableListAdapter;
import br.ufc.quixada.dsdm.comunicacaoiasd.adapter.ExpandableListAdapterCard;
import br.ufc.quixada.dsdm.comunicacaoiasd.model.Evento;

import static java.util.Arrays.asList;


/**
 * A simple {@link Fragment} subclass.
 */
public class ItinerarioFragment extends Fragment {
    private Context context;
    private RecyclerView recyclerview;
    ExpandableListAdapter.Item aux;

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

        for (String m : getMeses()) {
            aux = new ExpandableListAdapter.Item(ExpandableListAdapterCard.HEADER, m);
            aux.invisibleChildren = new ArrayList<>();
            aux.invisibleChildren.add(new ExpandableListAdapter.Item(ExpandableListAdapter.CHILD, "01 | Quarta  | Messejana"));
            aux.invisibleChildren.add(new ExpandableListAdapter.Item(ExpandableListAdapter.CHILD, "04 | Sábado  | Messejana"));
            aux.invisibleChildren.add(new ExpandableListAdapter.Item(ExpandableListAdapter.CHILD, "05 | Domingo | Messejana"));

            if(aux.invisibleChildren.isEmpty()){
                aux.invisibleChildren.add(new ExpandableListAdapter.Item(ExpandableListAdapterCard.CHILD, "Não disponível."));
            }
            data.add(aux);
        }

        recyclerview.setAdapter(new ExpandableListAdapter(data));

        return view;
    }

    public List<String> getMeses(){
        return asList("Janeiro", "Fevereiro", "Março", "Abril", "Maio", "Junho", "Julho", "Agosto", "Setembro", "Outubro", "Novembro", "Dezembro");
    }

}
