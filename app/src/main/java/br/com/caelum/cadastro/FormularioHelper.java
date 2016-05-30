package br.com.caelum.cadastro;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;

import br.com.caelum.cadastro.modelo.Aluno;

/**
 * Created by rodrigo on 28/03/16.
 */
public class FormularioHelper {

    private EditText editNome;
    private EditText editSite;
    private EditText editTelefone;
    private EditText editEndereco;
    private RatingBar rantingNota;
    private ImageView foto;
    private Aluno aluno;

    public FormularioHelper(Formulario formulario) {

        editNome = (EditText) formulario.findViewById(R.id.nome);
        editSite = (EditText) formulario.findViewById(R.id.site);
        editTelefone = (EditText) formulario.findViewById(R.id.telefone);
        editEndereco = (EditText) formulario.findViewById(R.id.endereco);
        rantingNota = (RatingBar) formulario.findViewById(R.id.nota);
        foto = (ImageView) formulario.findViewById(R.id.foto);
        aluno = new Aluno();
    }

    public Aluno pegaAlunoDoFormulario() {

        aluno.setNome(editTextToString(editNome));
        aluno.setSite(editTextToString(editSite));
        aluno.setEndereco(editTextToString(editEndereco));
        aluno.setTelefone(editTextToString(editTelefone));
        aluno.setNota(Double.valueOf(rantingNota.getRating()));

        return aluno;
    }

    public void colocaAlunoFormulario(Aluno alunoParaSerAlterado) {

        aluno = alunoParaSerAlterado;
        editNome.setText(alunoParaSerAlterado.getNome());
        editSite.setText(alunoParaSerAlterado.getSite());
        editTelefone.setText(alunoParaSerAlterado.getTelefone());
        editEndereco.setText(alunoParaSerAlterado.getEndereco());
        rantingNota.setRating(alunoParaSerAlterado.getNota().floatValue());

        if (alunoParaSerAlterado.getFoto() != null ) {
            carregarImagem(alunoParaSerAlterado.getFoto());
        }
    }

    private String editTextToString(EditText editText) {
        return editText.getText().toString();
    }

    public ImageView getFoto() {
        return foto;
    }

    public void carregarImagem(String caminhoArquivo) {

        aluno.setFoto(caminhoArquivo);
        Bitmap imagem = BitmapFactory.decodeFile(caminhoArquivo);

        if (imagem == null) {
            //Criando imagem reduzida
            Bitmap imagemReduzida = Bitmap.createScaledBitmap(imagem, 100, 100, true);
            foto.setImageBitmap(imagemReduzida);
        }
    }
}
