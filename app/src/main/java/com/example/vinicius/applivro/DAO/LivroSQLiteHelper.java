package com.example.vinicius.applivro.DAO;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Vinicius on 02/02/2016.
 */
public class LivroSQLiteHelper extends SQLiteOpenHelper {
    //nome do bando e versão do banco
    private static final String BANCO_DADOS = "BdLivro";
    private static final int VERSAO = 1;

    //variaveis com as clunas da tabela
    private static final String TABELA_LIVRO = "livro";
    private static final String id_LIVRO = "_id";
    private static final String TITULO = "titulo";
    private static final String AUTOR = "autor";
    private static final String EDITORA = "editora";

    //variavel para a criação da tabela
    private static final String CREATE_TBLIVRO = "create table " + TABELA_LIVRO
            + "(" + id_LIVRO + " integer primary key autoincrement, "
            + TITULO + " text not null, "
            + AUTOR + " text not null, "
            + EDITORA + " text not null); ";

    //construtor OBRIGATORIO
    public LivroSQLiteHelper(Context context) {
        super(context, BANCO_DADOS, null, VERSAO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TBLIVRO);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //caso a estrutura da tabela mude, destrua e crie outro
        db.execSQL("DROP TABLE IF EXISTS " + TABELA_LIVRO);
        onCreate(db);
    }
}
