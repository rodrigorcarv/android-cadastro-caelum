package br.com.caelum.cadastro.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import br.com.caelum.cadastro.modelo.Aluno;

/**
 * Created by rodrigo on 28/03/16.
 */
public class AlunoDao extends SQLiteOpenHelper {


    private static final String DATA_BASE = "CadastroCaelum";
    private static final int VERSAO = 2;

    public AlunoDao(Context context) {
        super(context, DATA_BASE, null, VERSAO);
    }

    public void salva(Aluno aluno) {

        ContentValues values = new ContentValues();
        values.put("nome", aluno.getNome());
        values.put("site", aluno.getSite());
        values.put("endereco", aluno.getEndereco());
        values.put("nota", aluno.getNota());
        values.put("foto", aluno.getFoto());
        values.put("telefone", aluno.getTelefone());

        getWritableDatabase().insert("Alunos", null, values);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String ddl = "CREATE TABLE Alunos (" +
                "id PRIMARY KEY, " +
                "nome TEXT UNIQUE NOT NULL, " +
                "telefone TEXT, " +
                "endereco TEXT, " +
                "site TEXT, " +
                "foto TEXT, " +
                "nota REAL)";

        db.execSQL(ddl);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String ddl = "DROP TABLE IF EXISTS Alunos";
        db.execSQL(ddl);

        this.onCreate(db);
    }

    public List<Aluno> getLista() {

        String[] colunas = {"id", "nome", "telefone", "endereco", "foto", "nota"};
        Cursor cursor = getWritableDatabase().query("Alunos", colunas, null, null, null, null, null);

        List<Aluno> alunos = new ArrayList<>();

        while(cursor.moveToNext()) {

            Aluno aluno =  new Aluno();
            aluno.setId(cursor.getLong(0));
            aluno.setNome(cursor.getString(1));
            aluno.setTelefone(cursor.getString(2));
            aluno.setEndereco(cursor.getString(3));
            aluno.setFoto(cursor.getString(4));
            aluno.setNota(cursor.getDouble(5));

            alunos.add(aluno);
        }

        return alunos;
    }
}
