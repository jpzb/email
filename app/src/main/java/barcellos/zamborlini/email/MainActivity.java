package barcellos.zamborlini.email;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnEnviar = (Button) findViewById(R.id.btnEnviar); // Recebe o botão da tela.

        btnEnviar.setOnClickListener(new View.OnClickListener() {
            // Coloca  o evento para pegar click no botão

            @Override
            public void onClick(View view) {
                EditText etEmail = findViewById(R.id.etEmail); // Pega o editText de email
                String email = etEmail.getText().toString(); // Pega o texto escrito no editText

                EditText etAssunto = findViewById(R.id.etAssunto); // Pega o editText de assunto
                String assunto = etAssunto.getText().toString(); // Pega o texto escrito no editText

                EditText etTexto = findViewById(R.id.etTexto); // Pega o editText de texto
                String texto = etTexto.getText().toString(); // Pega o texto escrito no editText

                Intent i = new Intent(Intent.ACTION_SENDTO); // Cria a intenção

                i.setData(Uri.parse("mailto:"));
                // Colocar a ação a ser realizada para enviar email

                String[] emails = new String[]{email};
                i.putExtra(Intent.EXTRA_EMAIL, emails);
                i.putExtra(Intent.EXTRA_SUBJECT, assunto);
                i.putExtra(Intent.EXTRA_TEXT, texto);

                try{
                    startActivity(Intent.createChooser(i, "Escolha o APP"));
                    // Aparece todos os apps que podem enviar email para o usuário escolher
                }catch (ActivityNotFoundException e){
                    Toast.makeText(MainActivity.this, "Não há nenhum app que posso " +
                            "realizar essa operação", Toast.LENGTH_LONG).show();
                    // Não achou nenhum app de email
                }
            }
        });
    }
}