package br.com.caelum.cadastro;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;

import java.io.File;
import java.io.Serializable;
import java.net.URI;

import br.com.caelum.cadastro.dao.AlunoDao;
import br.com.caelum.cadastro.modelo.Aluno;

/**
 * Created by rodrigo on 21/03/16.
 */
public class Formulario extends Activity {

    private FormularioHelper formularioHelper;
    private String caminhoArquivo;


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

                alunoDao.close();

                finish();
            }
        });

        ImageView foto = formularioHelper.getFoto();

        foto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent irParaCamera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

                caminhoArquivo = Environment.getExternalStorageDirectory().toString()
                          + "/" + System.currentTimeMillis() + ".png";

                File arquivo = new File(caminhoArquivo);

                Uri localImagem = Uri.fromFile(arquivo);
                irParaCamera.putExtra(MediaStore.EXTRA_OUTPUT, localImagem);

                //Aguardando Resultado
                startActivityForResult(irParaCamera, 123);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == 123) {

            if (resultCode == Activity.RESULT_OK) {
                //foto OK
                formularioHelper.carregarImagem(caminhoArquivo);
            } else {
                //foto cancelada
                caminhoArquivo = null;
            }
        }

    }
}
