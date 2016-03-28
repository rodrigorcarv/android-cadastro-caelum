package br.com.caelum.cadastro;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;

import br.com.caelum.cadastro.dao.AlunoDao;
import br.com.caelum.cadastro.modelo.Aluno;

/**
 * Created by rodrigo on 21/03/16.
 */
public class Formulario extends Activity{

    private FormularioHelper formularioHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.formulario);

        formularioHelper = new FormularioHelper(this);

        Button botao = (Button) findViewById(R.id.botao);

        botao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Aluno aluno = formularioHelper.pegaAlunoDoFormulario();

                AlunoDao alunoDao = new AlunoDao(Formulario.this);
                alunoDao.salva(aluno);
                alunoDao.close();

                finish();
            }
        });


    }
}
