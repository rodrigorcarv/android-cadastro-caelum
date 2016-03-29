package br.com.caelum.cadastro;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;

import java.io.Serializable;

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

        Intent intent = getIntent();
        final Aluno alunoParaSerAlterado = (Aluno) intent.getSerializableExtra("alunoSelecionado");

        formularioHelper = new FormularioHelper(this);

        Button botao = (Button) findViewById(R.id.botao);

        if (alunoParaSerAlterado != null) {
            botao.setText("Alterar");
            formularioHelper.colocaAlunoFormulario(alunoParaSerAlterado);
        }

        botao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Aluno aluno = formularioHelper.pegaAlunoDoFormulario();

                AlunoDao alunoDao = new AlunoDao(Formulario.this);

                if (alunoParaSerAlterado == null) {
                    alunoDao.salva(aluno);
                } else {
                    aluno.setId(alunoParaSerAlterado.getId());
                    alunoDao.altera(aluno);
                }


                alunoDao.salva(aluno);
                alunoDao.close();

                finish();
            }
        });


    }
}
