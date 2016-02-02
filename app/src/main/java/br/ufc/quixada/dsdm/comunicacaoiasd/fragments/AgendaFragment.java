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
import br.ufc.quixada.dsdm.comunicacaoiasd.adapter.ExpandableListAdapterCard;
import br.ufc.quixada.dsdm.comunicacaoiasd.model.Evento;

import static java.util.Arrays.asList;


/**
 * A simple {@link Fragment} subclass.
 */
public class AgendaFragment extends Fragment {

    private Context context;
    private RecyclerView recyclerview;
    List<ExpandableListAdapterCard.Item> data = new ArrayList<>();
    ExpandableListAdapterCard.Item aux;

    public AgendaFragment(Context context) {
        this.context = context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_agenda, container, false);

        recyclerview = (RecyclerView) view.findViewById(R.id.recycler_view_agenda);
        recyclerview.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));

        Evento vazio = new Evento("-", "-", "Não há eventos cadastrados.", "-", "-");

        for (String m : getMeses()) {
            aux = new ExpandableListAdapterCard.Item(ExpandableListAdapterCard.HEADER, m);
            aux.invisibleChildren = new ArrayList<>();
            for (Evento e : getEventos()) {
                if(m == e.getMes()) {
                    aux.invisibleChildren.add(new ExpandableListAdapterCard.Item(ExpandableListAdapterCard.CHILD, e));
                }
            }
            if(aux.invisibleChildren.isEmpty()){
                aux.invisibleChildren.add(new ExpandableListAdapterCard.Item(ExpandableListAdapterCard.CHILD, vazio));
            }
            data.add(aux);
        }

        recyclerview.setAdapter(new ExpandableListAdapterCard(data));

        return view;
    }

    public List<String> getMeses(){
        return asList("Janeiro", "Fevereiro", "Março", "Abril", "Maio", "Junho", "Julho", "Agosto", "Setembro", "Outubro", "Novembro", "Dezembro");
    }

    public List<Evento> getEventos(){
        List<Evento> eventos = new ArrayList<Evento>();
        eventos.add(new Evento("Janeiro", "05/02/2016 a 10/02/2016", "RETIRO", "RETIRO DE CARNAVAL", "Ministerio Jovem"));
        eventos.add(new Evento("Fevereiro", "05/02/2016 a 10/02/2016", "RETIRO", "RETIRO DE CARNAVAL", "Ministerio Jovem"));
        eventos.add(new Evento("Fevereiro", "05/02/2016 a 10/02/2016", "RETIRO", "RETIRO DE CARNAVAL", "Ministerio Jovem"));
        eventos.add(new Evento("Janeiro", "05/02/2016 a 10/02/2016", "RETIRO", "RETIRO DE CARNAVAL", "Ministerio Jovem"));
        eventos.add(new Evento("Março", "05/02/2016 a 10/02/2016", "RETIRO", "RETIRO DE CARNAVAL", "Ministerio Jovem"));
        eventos.add(new Evento("Março", "05/02/2016 a 10/02/2016", "RETIRO", "RETIRO DE CARNAVAL", "Ministerio Jovem"));
        eventos.add(new Evento("Novembro", "05/02/2016 a 10/02/2016", "RETIRO", "RETIRO DE CARNAVAL", "Ministerio Jovem"));
        eventos.add(new Evento("Janeiro", "05/02/2016 a 10/02/2016", "RETIRO", "RETIRO DE CARNAVAL", "Ministerio Jovem"));

        return eventos;
    }

}
