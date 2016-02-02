package com.example.vinicius.applivro.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.vinicius.applivro.DAO.LivroCRUD;
import com.example.vinicius.applivro.R;
import com.example.vinicius.applivro.adapter.LivroAdapter;
import com.example.vinicius.applivro.domain.Livro;

import java.util.List;

public class ConsultaActivity extends AppCompatActivity {
    private Livro livro = new Livro();
    private RadioGroup radioGroup;
    private EditText editText;
    private ListView lvConsulta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consulta);

        radioGroup = (RadioGroup) findViewById(R.id.rdgOpcoes);
        editText = (EditText) findViewById(R.id.edtconsulta);
        lvConsulta = (ListView) findViewById(R.id.lvConsulta);
    }

    public void consultar(View view) {
        LivroCRUD livroCRUD = new LivroCRUD(this);
        try {
            switch (radioGroup.getCheckedRadioButtonId()) {
                case R.id.rbtnTitulo:
                    String titulo = editText.getText().toString();
                    List<Livro> listaTitulo = livroCRUD.buscarTitulo(titulo);
                    lvConsulta.setAdapter(new LivroAdapter(this, listaTitulo));
                    Toast.makeText(this, "Ta passando pela consulta", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.rbtnAutor:
                    livro.setAutor(editText.getText().toString());
                    List<Livro> listaAutor = livroCRUD.buscarAutor(livro);
                    lvConsulta.setAdapter(new LivroAdapter(this, listaAutor));
                    break;
                case R.id.rbtnEditora:
                    livro.setEditora(editText.getText().toString());
                    List<Livro> listaEditora = livroCRUD.buscarEditora(livro);
                    lvConsulta.setAdapter(new LivroAdapter(this, listaEditora));
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, "Não foi possivel realizar a consulta....", Toast.LENGTH_SHORT).show();
        }
    }
}
