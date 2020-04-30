package yuc1996229.iim.ncku.edu.tw.finalproject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class InfoActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.info);

        Button button = (Button)findViewById(R.id.ok);
        button.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();

                EditText takename = (EditText) findViewById(R.id.name);

                if(takename.getText().toString().matches("")) {
                    Toast toast = Toast.makeText(InfoActivity.this, "Enter your name!!", Toast.LENGTH_LONG);
                    toast.show();
                }else {
                    String Takename = takename.getText().toString();
                    intent.putExtra("Name", Takename );

                    intent.setClass(InfoActivity.this, PhotoActivity.class);
                    startActivity(intent);
                    InfoActivity.this.finish();
                }
            }
        });
    }
}
