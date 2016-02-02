package br.ufc.quixada.dsdm.comunicacaoiasd.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

import br.ufc.quixada.dsdm.comunicacaoiasd.R;
import br.ufc.quixada.dsdm.comunicacaoiasd.model.Evento;

/**
 * Created by jonas_000 on 12/01/2016.
 */
public class AdapterEvento extends RecyclerView.Adapter<AdapterEvento.ViewHolder> {

    private List<Evento> itens;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView mes;
        private TextView data;
        private TextView titulo;
        private TextView descricao;
        private TextView organizacao;

        public ViewHolder(View v) {
            super(v);
            titulo = (TextView) v.findViewById(R.id.evento);
            organizacao = (TextView) v.findViewById(R.id.organização);
            data = (TextView) v.findViewById(R.id.date);
        }
    }

    public AdapterEvento(List<Evento> itens) {
        this.itens = itens;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_row_evento, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Evento evento = itens.get(position);
        holder.data.setText(evento.getData());
        holder.titulo.setText(evento.getTitulo());
        holder.organizacao.setText(evento.getOrganizacao());
    }

    @Override
    public int getItemCount() {
        return itens.size();
    }
}
