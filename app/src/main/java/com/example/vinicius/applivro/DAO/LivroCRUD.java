package com.example.vinicius.applivro.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.vinicius.applivro.domain.Livro;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vinicius on 02/02/2016.
 */
public class LivroCRUD {
    LivroSQLiteHelper auxLivro;
    private SQLiteDatabase db;

    public LivroCRUD(Context context) {
        auxLivro = new LivroSQLiteHelper(context);
        //permissão de alteração e consulta
        db = auxLivro.getWritableDatabase();
    }

    //INSERIR NO BANCO
    public void inserir(Livro livro) throws Exception {
        ContentValues values = new ContentValues();
        values.put("titulo", livro.getTitulo());
        values.put("autor", livro.getAutor());
        values.put("editora", livro.getEditora());

        db.insert("livro", null, values);
    }

    //BUSCAR TODOS
    public List<Livro> buscarTodos() throws Exception {
        List<Livro> lista = new ArrayList<Livro>();
        String[] colunas = new String[]{"_id ", " titulo ", " autor ", " editora"};

        Cursor cursor = db.query("livro ", colunas, null, null, null, null, "titulo ASC");

        //verificar se teve resultados
        if (cursor.getCount() > 0) {
            //ir para a primeira linha
            cursor.moveToFirst();
            do {
                Livro livro = new Livro();
                //retornar os valores e adiciona na lista
                livro.setId(cursor.getLong(0));
                livro.setTitulo(cursor.getString(1));
                livro.setAutor(cursor.getString(2));
                livro.setEditora(cursor.getString(3));
                lista.add(livro);

            } while (cursor.moveToNext());
        }

        return (lista);
    }

    //BUSCAR POR TITULO
    public List<Livro> buscarTitulo(String titulo) throws Exception {
        List<Livro> lista = new ArrayList<Livro>();
        String[] colunas = new String[]{"_id", "titulo", "autor", "editora"};

        Cursor cursor = db.query("livro", colunas, "titulo= ?", new String[]{titulo + "*"}, null, null, null);
        //Percorre o cursor se tiver registos
        //Se não for nulo move para o primeiro registo
        if (cursor != null && cursor.moveToFirst()) {
            do {
                Livro livroTitulo = new Livro();
                //retornar os valores e adiciona na lista
                livroTitulo.setId(cursor.getInt(0));
                livroTitulo.setTitulo(cursor.getString(1));
                livroTitulo.setAutor(cursor.getString(2));
                livroTitulo.setEditora(cursor.getString(3));
                lista.add(livroTitulo);
            } while (cursor.moveToNext());
        }

        return (lista);
    }

    //BUSCAR POR AUTOR
    public List<Livro> buscarAutor(Livro livro) throws Exception {
        List<Livro> lista = new ArrayList<Livro>();
        String[] colunas = new String[]{"_id ", " titulo ", " autor ", " editora"};
        String where = "select * from livro where autor = " + livro.getAutor();

        Cursor cursor = db.query("livro", colunas, where, null, null, null, "titulo ASC");

        //verificar se teve resultados
        if (cursor.getCount() > 0) {
            //ir para a primeira linha
            cursor.moveToFirst();
            do {
                Livro livroAutor = new Livro();
                //retornar os valores e adiciona na lista
                livroAutor.setId(cursor.getInt(0));
                livroAutor.setTitulo(cursor.getString(1));
                livroAutor.setAutor(cursor.getString(2));
                livroAutor.setEditora(cursor.getString(3));
                lista.add(livroAutor);
            } while (cursor.moveToNext());
        }

        return (lista);
    }

    //BUSCAR POR EDITORA
    public List<Livro> buscarEditora(Livro livro) throws Exception {
        List<Livro> lista = new ArrayList<Livro>();
        String[] colunas = new String[]{"_id ", " titulo ", " autor ", " editora"};
        String where = "select * from livro where editora = " + livro.getEditora();

        Cursor cursor = db.query("livro", colunas, where, null, null, null, "titulo ASC");

        //verificar se teve resultados
        if (cursor.getCount() > 0) {
            //ir para a primeira linha
            cursor.moveToFirst();
            do {
                Livro livroEditora = new Livro();
                //retornar os valores e adiciona na lista
                livroEditora.setId(cursor.getInt(0));
                livroEditora.setTitulo(cursor.getString(1));
                livroEditora.setAutor(cursor.getString(2));
                livroEditora.setEditora(cursor.getString(3));
                lista.add(livroEditora);
            } while (cursor.moveToNext());
        }

        return (lista);
    }

    //ATUALIZAR
    public void atualizar(Livro livro) throws Exception {
        ContentValues values = new ContentValues();
        values.put("titulo", livro.getTitulo());
        values.put("autor", livro.getAutor());
        values.put("editora", livro.getEditora());

        db.update("livro", values, "_id= ?", new String[]{"" + livro.getId()});
    }


    //DELETAR
    public void deletar(Livro livro) throws Exception {
        db.delete("livro", "_id = " + livro.getId(), null);
    }
}
