package br.ufc.quixada.dsdm.comunicacaoiasd.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import br.ufc.quixada.dsdm.comunicacaoiasd.R;
import br.ufc.quixada.dsdm.comunicacaoiasd.model.Contato;
import br.ufc.quixada.dsdm.comunicacaoiasd.model.Evento;

/**
 * Created by jonas_000 on 12/01/2016.
 */
public class AdapterEvento extends RecyclerView.Adapter<AdapterEvento.ViewHolder> {

    private List<Evento> itens;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private String mes;
        private String data;
        private String titulo;
        private String descricao;
        private String organizacao;

        public ViewHolder(View v) {
            super(v);
        }
    }

    public AdapterEvento(List<Evento> itens) {
        this.itens = itens;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_row, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Evento evento = itens.get(position);
        //holder.dep.setText(contato.getDep());
    }

    @Override
    public int getItemCount() {
        return itens.size();
    }
}
