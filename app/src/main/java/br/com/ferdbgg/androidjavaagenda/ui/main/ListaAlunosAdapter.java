package br.com.ferdbgg.androidjavaagenda.ui.main;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import br.com.ferdbgg.androidjavaagenda.models.pojos.Aluno;

public class ListaAlunosAdapter extends RecyclerView.Adapter<ListaAlunosAdapter.ListaAlunoViewHolder> {

    private final List<Aluno> alunos;
    private final OnItemListaAlunoClickListener listener;

    public ListaAlunosAdapter(
            List<Aluno> alunos,
            OnItemListaAlunoClickListener listener
    ) {
        this.alunos = alunos;
        this.listener = listener;
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

        final var aluno = alunos.get(position);

        holder.nomeAluno.setText(aluno.nome());

        holder.itemView.setOnClickListener(v -> {
            if (listener != null) {
                // Avisa a Activity e manda o aluno clicado
                listener.onItemClick(aluno);
            }
        });

    }

    @Override
    public int getItemCount() {
        return alunos.size();
    }

    public interface OnItemListaAlunoClickListener {
        void onItemClick(Aluno aluno);
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
