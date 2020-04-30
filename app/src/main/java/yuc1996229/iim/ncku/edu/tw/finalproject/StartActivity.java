package yuc1996229.iim.ncku.edu.tw.finalproject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class StartActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start);

        TextView textView = (TextView)findViewById(R.id.showname);
        String Takename = getIntent().getStringExtra("Name");
        textView.setText("Hello, " + Takename);

        ImageView imageView = (ImageView)findViewById(R.id.photoplayer);
        //Bitmap bitmap = getIntent().getParcelableExtra("Photo");
        //imageView.setImageBitmap(bitmap);

        Button button = (Button)findViewById(R.id.paint);
        button.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(StartActivity.this, QuestionActivity.class);
                startActivity(intent);
                StartActivity.this.finish();
            }
        });
    }
}
