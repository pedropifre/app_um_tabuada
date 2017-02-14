package batman.teste;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

import static batman.teste.R.id.botao;

public class MainActivity extends AppCompatActivity {

    private TextView numeroUm = null;
    private TextView numeroDois = null;
    private EditText inputN = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        numeroUm = (TextView) findViewById(R.id.numeroUm);
        numeroDois = (TextView) findViewById(R.id.numeroDois);
        inputN = (EditText) findViewById(R.id.inputN);
        sorteio();


    }

    private void sorteio() {
// teste
        Random  um = new Random();
        Random  dois = new Random();

        int v1 = um.nextInt(10);
        int v2 = dois.nextInt(10);

        numeroUm.setText(String.valueOf(v1));
        numeroDois.setText(String.valueOf(v2));

    }

    public void resposta(View v){
        int digitado = Integer.parseInt(inputN.getText().toString());
        int p1 = Integer.parseInt(numeroUm.getText().toString());
        int p2 = Integer.parseInt(numeroDois.getText().toString());
        int respostaf = p1 + p2;

        String resp = "";

        if(digitado == (p1*p2)){
            resp = "correto";
        }
        else {
            resp ="errado";
        }
        Toast.makeText(this, resp,Toast.LENGTH_SHORT).show();

    }
}
