package br.com.caelum.cadastro;

import android.widget.EditText;
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

    public FormularioHelper(Formulario formulario) {


        editNome = (EditText) formulario.findViewById(R.id.nome);
        editSite = (EditText) formulario.findViewById(R.id.site);
        editTelefone = (EditText) formulario.findViewById(R.id.telefone);
        editEndereco = (EditText) formulario.findViewById(R.id.endereco);
        rantingNota = (RatingBar) formulario.findViewById(R.id.nota);
    }

    public Aluno pegaAlunoDoFormulario() {

        Aluno aluno = new Aluno();
        aluno.setNome(editTextToString(editNome));
        aluno.setSite(editTextToString(editSite));
        aluno.setEndereco(editTextToString(editEndereco));
        aluno.setTelefone(editTextToString(editTelefone));
        aluno.setNota(Double.valueOf(rantingNota.getRating()));

        return aluno;
    }

    private String editTextToString(EditText editText) {
        return editText.getText().toString();
    }

    public void colocaAlunoFormulario(Aluno aluno) {

        editNome.setText(aluno.getNome());
        editSite.setText(aluno.getSite());
        editTelefone.setText(aluno.getTelefone());
        editEndereco.setText(aluno.getEndereco());
        rantingNota.setRating(aluno.getNota().floatValue());
    }
}
