package cadastro.caelum.com.br.cadastrocaelum;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.*;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class ListaAlunos extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listagem_alunos);

        String [] nomes = {"Ana", "Jose", "Felipe"};

        int layout = android.R.layout.simple_list_item_1;

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,layout, nomes);

        ListView lista = (ListView) findViewById(R.id.lista);

        lista.setAdapter(adapter);

        lista.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapter, View view, int posicao, long id) {

                Toast.makeText(ListaAlunos.this, "Clique na posicao" + posicao, Toast.LENGTH_SHORT).show();
            }
        });

        lista.setOnItemLongClickListener(new OnItemLongClickListener() {

            @Override
            public boolean onItemLongClick(AdapterView<?> adapter, View view, int posicao, long id) {

                Toast.makeText(ListaAlunos.this, "Clique longo em " + adapter.getItemIdAtPosition(posicao), Toast.LENGTH_SHORT).show();


                return true;
            }
        });



    }

}
