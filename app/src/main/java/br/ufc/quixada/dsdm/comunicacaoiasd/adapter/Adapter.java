package br.ufc.quixada.dsdm.comunicacaoiasd.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import br.ufc.quixada.dsdm.comunicacaoiasd.R;
import br.ufc.quixada.dsdm.comunicacaoiasd.model.Contato;

/**
 * Created by jonas_000 on 12/01/2016.
 */
public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    private List<Contato> itens;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView dep;
        public TextView resp;


        public ViewHolder(View v) {
            super(v);
            dep  = (TextView) v.findViewById(R.id.dep);
            resp  = (TextView) v.findViewById(R.id.resp);
        }
    }

    public Adapter(List<Contato> itens) {
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
        Contato contato = itens.get(position);
        holder.dep.setText(contato.getDep());
        holder.resp.setText(contato.getResp());
    }

    @Override
    public int getItemCount() {
        return itens.size();
    }
}
