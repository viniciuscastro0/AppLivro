package com.example.vinicius.applivro.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.vinicius.applivro.DAO.LivroCRUD;
import com.example.vinicius.applivro.R;
import com.example.vinicius.applivro.domain.Livro;

public class AddUpdateActivity extends AppCompatActivity {

    private Livro livro = new Livro();
    private EditText edtTitulo;
    private EditText edtAutor;
    private EditText edtEditora;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_update);

        edtTitulo = (EditText) findViewById(R.id.edtTitulo);
        edtAutor = (EditText) findViewById(R.id.edtAutor);
        edtEditora = (EditText) findViewById(R.id.edtEditora);

        Button btnAdicionar = (Button) findViewById(R.id.btnAdicionar);
        Button btnAtualizar = (Button) findViewById(R.id.btnUpdate);

        Intent intent = getIntent();
        if (intent != null){
            Bundle bundle = intent.getExtras();
            if (bundle != null){
                livro.setId(bundle.getLong("_id"));
                livro.setTitulo(bundle.getString("titulo"));
                livro.setAutor(bundle.getString("autor"));
                livro.setEditora(bundle.getString("editora"));

                edtTitulo.setText(livro.getTitulo());
                edtAutor.setText(livro.getAutor());
                edtEditora.setText(livro.getEditora());

                btnAdicionar.setVisibility(View.GONE);
                btnAtualizar.setVisibility(View.VISIBLE);

            }
        }
    }

    //METODO CHAMADO PELO ONCLICK
    public void adicionar(View view){
        livro.setTitulo(edtTitulo.getText().toString());
        livro.setAutor(edtAutor.getText().toString());
        livro.setEditora(edtEditora.getText().toString());

        LivroCRUD livroCRUD = new LivroCRUD(this);
        try {
            livroCRUD.inserir(livro);
            Toast.makeText(this, "Livro Salvo com sucesso!!!!", Toast.LENGTH_LONG).show();
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, "Não foi possivel salvar.....!!!!", Toast.LENGTH_LONG).show();
        }
    }

    //METODO CHAMADO PELO ONCLICK
    public void atualizar(View view){
        livro.setTitulo(edtTitulo.getText().toString());
        livro.setAutor(edtAutor.getText().toString());
        livro.setEditora(edtEditora.getText().toString());

        LivroCRUD livroCRUD = new LivroCRUD(this);
        try {
            livroCRUD.atualizar(livro);
            Toast.makeText(this, "Livro Atualizado com sucesso!!!!", Toast.LENGTH_LONG).show();
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, "Não foi possivel atualizar.....!!!!", Toast.LENGTH_LONG).show();
        }
    }
}
