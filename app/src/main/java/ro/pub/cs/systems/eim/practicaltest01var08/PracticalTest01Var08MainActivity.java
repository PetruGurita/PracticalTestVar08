package ro.pub.cs.systems.eim.practicaltest01var08;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class PracticalTest01Var08MainActivity extends AppCompatActivity {




    private final static int SECONDARY_ACTIVITY_REQUEST_CODE = 1;
    private EditText riddle, answer;
    private Button check;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practical_test01_var08_main);

        riddle = (EditText) findViewById(R.id.riddle);
        answer = (EditText) findViewById(R.id.answer);
        check = (Button) findViewById(R.id.play);

        check.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
//                    // your handler code here
//
//
                    Intent intent = new Intent(getApplicationContext(), PracticalTest01Var08PlayActivity.class);
                    String r = riddle.getText().toString();
                    String a = answer.getText().toString();
                    intent.putExtra("answer", a);
                    intent.putExtra("question", r);
                    startActivityForResult(intent, SECONDARY_ACTIVITY_REQUEST_CODE);

                }
        });


        //EX 11.
        if (savedInstanceState == null) {
            //Log.d(Constants.TAG, "onCreate() method was invoked without a previous state");
        } else {
            //Log.d(Constants.TAG, "onCreate() method was invoked with a previous state");
            if (savedInstanceState.containsKey("answer")) {
                //EditText usernameEditText = (EditText)findViewById(R.id.username_edit_text);
                //usernameEditText.setText(savedInstanceState.getString(Constants.USERNAME_EDIT_TEXT));
                answer.setText(savedInstanceState.getString("answer"));
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        if (requestCode == SECONDARY_ACTIVITY_REQUEST_CODE) {
            Toast.makeText(this, "The activity returned with result " + resultCode, Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);


        savedInstanceState.putString("answer", answer.getText().toString());

        }


    //EX 11.
    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        if (savedInstanceState.containsKey("answer")) {
            answer.setText(savedInstanceState.getString("answer"));
        }
    }
}
