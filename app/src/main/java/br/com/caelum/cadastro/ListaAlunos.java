package br.com.caelum.cadastro;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.*;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.text.Normalizer;
import java.util.List;

import br.com.caelum.cadastro.dao.AlunoDao;
import br.com.caelum.cadastro.modelo.Aluno;

public class ListaAlunos extends Activity {

    private ListView lista;
    private Aluno aluno;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listagem_alunos);

        lista = (ListView) findViewById(R.id.lista);

        registerForContextMenu(lista);

        lista.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapter, View view, int posicao, long id) {

                Aluno alunoClicado = (Aluno) adapter.getItemAtPosition(posicao);

                Intent irParaFormulario = new Intent(ListaAlunos.this, Formulario.class);
                irParaFormulario.putExtra("alunoSelecionado", alunoClicado );

                startActivity(irParaFormulario);

                //Toast.makeText(ListaAlunos.this, "Clique na posicao" + posicao, Toast.LENGTH_SHORT).show();
            }
        });

        lista.setOnItemLongClickListener(new OnItemLongClickListener() {

            @Override
            public boolean onItemLongClick(AdapterView<?> adapter, View view, int posicao, long id) {

                //Toast.makeText(ListaAlunos.this, "Clique longo em " + adapter.getItemIdAtPosition(posicao), Toast.LENGTH_SHORT).show();
                aluno = (Aluno) adapter.getItemAtPosition(posicao);

                return false;
            }
        });
    }


    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {

        menu.add("Ligar");
        menu.add("Enviar SMS");
        menu.add("Navegar no site");
        MenuItem deletar = menu.add("Deletar");
        deletar.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {

                AlunoDao alunoDao = new AlunoDao(ListaAlunos.this);
                alunoDao.deletar(aluno);
                alunoDao.close();

                carregaLista();

                return false;
            }
        });

        menu.add("Ver no mapa");
        menu.add("Enviar e-mail");

        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    protected void onResume() {
        super.onResume();

        carregaLista();
    }

    private void carregaLista() {
        AlunoDao alunoDao = new AlunoDao(this);
        List<Aluno> alunos = alunoDao.getLista();
        alunoDao.close();

        int layout = android.R.layout.simple_list_item_1;

        ArrayAdapter<Aluno> adapter = new ArrayAdapter<>(this,layout, alunos);

        lista.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.listagem_alunos, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int itemClicado = item.getItemId();

        switch (itemClicado) {

            case R.id.novo:

                Intent irParaFormulario = new Intent(this, Formulario.class);
                startActivity(irParaFormulario);
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}
