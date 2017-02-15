package batman.teste;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.Random;

import static batman.teste.R.id.botao;

public class MainActivity extends AppCompatActivity {

    private TextView numeroUm = null;
    private TextView numeroDois = null;
    private EditText inputN = null;
    private EditText edtNome = null;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        numeroUm = (TextView) findViewById(R.id.numeroUm);
        numeroDois = (TextView) findViewById(R.id.numeroDois);
        inputN = (EditText) findViewById(R.id.inputN);
        edtNome = (EditText) findViewById(R.id.edtNome);
        sorteio();


        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    public void registrar(View v) {
        String nome = this.edtNome.getText().toString();
        String url = "http://tabuada.mybluemix.net/Service?acao=registro&nome=" + nome;

        RequestQueue rq = Volley.newRequestQueue(this);

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
//	response	é	a	String	retornada	pelo	servidor!
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, "Erro!", Toast.LENGTH_SHORT).show();
            }
        });
        rq.add(stringRequest);
    }

    private void sorteio() {
        Random um = new Random();
        Random dois = new Random();

        int v1 = um.nextInt(10);
        int v2 = dois.nextInt(10);

        numeroUm.setText(String.valueOf(v1));
        numeroDois.setText(String.valueOf(v2));

    }

    public void resposta(View v) {
        int digitado = Integer.parseInt(inputN.getText().toString());
        int p1 = Integer.parseInt(numeroUm.getText().toString());
        int p2 = Integer.parseInt(numeroDois.getText().toString());
        int respostaf = p1 + p2;
        String nome = this.edtNome.getText().toString();
        String url = "http://tabuada.mybluemix.net/Service?acao=resposta&nome=" + nome + "&n1=" +p1+ "&n2=" +p2+ "&resposta=" +digitado;

        String resp = "";

        if (digitado == (p1 * p2)) {
            resp = "correto";
            sorteio();
        } else {
            resp = "errado";
        }
        Toast.makeText(this, resp, Toast.LENGTH_SHORT).show();

        RequestQueue rq = Volley.newRequestQueue(this);

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
//	response	é	a	String	retornada	pelo	servidor!
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, "Erro!", Toast.LENGTH_SHORT).show();
            }
        });
        rq.add(stringRequest);

    }

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("Main Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        AppIndex.AppIndexApi.start(client, getIndexApiAction());
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(client, getIndexApiAction());
        client.disconnect();
    }
}
