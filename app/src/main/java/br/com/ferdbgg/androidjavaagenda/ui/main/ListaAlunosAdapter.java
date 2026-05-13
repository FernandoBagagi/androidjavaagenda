package br.com.ferdbgg.androidjavaagenda.ui.main;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ListaAlunosAdapter extends RecyclerView.Adapter<ListaAlunosAdapter.ListaAlunoViewHolder> {

    private final List<String> listaDeAlunos;

    public ListaAlunosAdapter(List<String> listaDeAlunos) {
        this.listaDeAlunos = listaDeAlunos;
    }

    @NonNull
    @Override
    public ListaAlunoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        final var view = LayoutInflater
                .from(parent.getContext())
                .inflate(
                        android.R.layout.simple_list_item_1,
                        parent,
                        false);

        return new ListaAlunoViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ListaAlunoViewHolder holder, int position) {
        final var nomeDoAluno = listaDeAlunos.get(position);
        holder.nomeAluno.setText(nomeDoAluno);
    }

    @Override
    public int getItemCount() {
        return listaDeAlunos.size();
    }

    public static class ListaAlunoViewHolder extends RecyclerView.ViewHolder {
        TextView nomeAluno;

        public ListaAlunoViewHolder(@NonNull View itemView) {
            super(itemView);
            // text1 é o ID padrão do TextView dentro do simple_list_item_1
            nomeAluno = itemView.findViewById(android.R.id.text1);
        }
    }
}
