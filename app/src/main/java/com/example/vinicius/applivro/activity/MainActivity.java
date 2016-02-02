package com.example.vinicius.applivro.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.example.vinicius.applivro.DAO.LivroCRUD;
import com.example.vinicius.applivro.R;
import com.example.vinicius.applivro.adapter.LivroAdapter;
import com.example.vinicius.applivro.domain.Livro;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LivroCRUD livroCRUD = new LivroCRUD(this);
        try {
            List<Livro> lista = livroCRUD.buscarTodos();

            ListView lvPrincipal = (ListView) findViewById(R.id.lvPrincial);
            lvPrincipal.setAdapter(new LivroAdapter(this, lista));


        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, "Não foi possivel carregar a lista.", Toast.LENGTH_SHORT).show();
        }
    }

    public void chamaCadastro(View view){
        Intent intent = new Intent(this, AddUpdateActivity.class);
        startActivity(intent);
    }

    public void chamaConsulta(View view){
        Intent intent = new Intent(this, ConsultaActivity.class);
        startActivity(intent);
    }
}
