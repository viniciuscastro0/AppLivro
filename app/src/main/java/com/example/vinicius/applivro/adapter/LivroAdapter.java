package com.example.vinicius.applivro.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.vinicius.applivro.DAO.LivroCRUD;
import com.example.vinicius.applivro.R;
import com.example.vinicius.applivro.activity.AddUpdateActivity;
import com.example.vinicius.applivro.domain.Livro;

import java.util.List;

/**
 * Created by Vinicius on 02/02/2016.
 */
public class LivroAdapter extends BaseAdapter {
    private Context context;
    private List<Livro> lista;

    public LivroAdapter(Context context, List<Livro> lista){
        this.context = context;
        this.lista = lista;
    }

    @Override
    public int getCount() {
        return lista.size();
    }

    @Override
    public Object getItem(int arg0) {
        return lista.get(arg0);
    }

    @Override
    public long getItemId(int arg0) {
        return lista.get(arg0).getId();
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        Livro livro = lista.get(position);
        final int auxPosition = position;

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        final View layout = inflater.inflate(R.layout.item_lista, null);

        TextView titulo = (TextView) layout.findViewById(R.id.tvTitulo);
        titulo.setText(lista.get(position).getTitulo());

        TextView autor = (TextView) layout.findViewById(R.id.tvAutor);
        autor.setText(lista.get(position).getAutor());

        TextView editora = (TextView) layout.findViewById(R.id.tvEditora);
        editora.setText(lista.get(position).getEditora());

        //BOTÃO ATUALIZAR
        Button btnAtualizar = (Button) layout.findViewById(R.id.btnChamaAtualizar);
        btnAtualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, AddUpdateActivity.class);
                intent.putExtra("titulo", lista.get(auxPosition).getTitulo());
                intent.putExtra("autor", lista.get(auxPosition).getAutor());
                intent.putExtra("editora", lista.get(auxPosition).getEditora());
                intent.putExtra("_id", lista.get(auxPosition).getId());
                context.startActivity(intent);

            }
        });

        //BOTAO DELETAR
        Button btnDeletar = (Button) layout.findViewById(R.id.btnDeletar);
        btnDeletar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LivroCRUD livroCRUD = new LivroCRUD(context);
                try {
                    livroCRUD.deletar(lista.get(auxPosition));
                    layout.setVisibility(View.GONE);
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(context, "Não foi possivel excluir!!!!!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return layout;
    }
}
