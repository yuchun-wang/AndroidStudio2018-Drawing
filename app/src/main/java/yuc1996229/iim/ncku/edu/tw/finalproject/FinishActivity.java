package yuc1996229.iim.ncku.edu.tw.finalproject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class FinishActivity extends Activity{

    @Override
    public void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.finish);

        Button button = (Button)findViewById(R.id.endgame);
        button.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(FinishActivity.this, InfoActivity.class);
                startActivity(intent);
                FinishActivity.this.finish();
            }
        });
    }
}
