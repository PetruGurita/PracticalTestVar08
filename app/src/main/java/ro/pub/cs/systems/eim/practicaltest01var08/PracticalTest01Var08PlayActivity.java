package ro.pub.cs.systems.eim.practicaltest01var08;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class PracticalTest01Var08PlayActivity extends AppCompatActivity {

    private Button check;
    private String question;
    private String answer;
    private Button back;
    private EditText q;
    private EditText a;



    private IntentFilter intentFilter = new IntentFilter();
    private boolean serviceStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practical_test01_var08_play);


        serviceStatus = false;
        intentFilter.addAction("lala");

        q = (EditText) findViewById(R.id.show);
        a = (EditText) findViewById(R.id.insert);
        Intent intent = getIntent();
        if (intent != null && intent.getExtras().containsKey("answer")) {
             answer = ((Intent) intent).getStringExtra("answer");
            //a.setText(answer);
        }
        if (intent != null && intent.getExtras().containsKey("question")) {
            question = ((Intent) intent).getStringExtra("question");
            q.setText(question);
        }

       check = (Button) findViewById(R.id.check);

        check.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // your handler code here
                if (a.getText().toString().equals(answer)) {

                    Toast.makeText(PracticalTest01Var08PlayActivity.this, "This is it", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(PracticalTest01Var08PlayActivity.this, "this is not it", Toast.LENGTH_LONG).show();
                }
                if (serviceStatus == false) {
                    Intent intent = new Intent(getApplicationContext(), PracticalTest01Var08Service.class);
                    intent.putExtra("text", a.getText().toString());
                    getApplicationContext().startService(intent);
                    serviceStatus = true;
                }
            }
        });

        back = (Button) findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // your handler code here
                setResult(RESULT_CANCELED, null);
                finish();
            }
        });

    }

    private MessageBroadcastReceiver messageBroadcastReceiver = new MessageBroadcastReceiver();
    private class MessageBroadcastReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            Log.d("[message]", intent.getStringExtra("message"));
        }
    }


    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(messageBroadcastReceiver, intentFilter);
    }


    @Override
    protected void onPause() {
        unregisterReceiver(messageBroadcastReceiver);
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        Intent intent = new Intent(this, PracticalTest01Var08Service.class);
        stopService(intent);
        super.onDestroy();
    }

}
