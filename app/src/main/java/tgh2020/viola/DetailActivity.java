package tgh2020.viola;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import android.widget.ImageView;
import android.widget.TextView;


public class DetailActivity extends AppCompatActivity {




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Memory memory= null;
        Intent intent = getIntent();

        if (intent != null) {
            memory = (Memory) intent.getSerializableExtra("memory");
        } else {
            memory=new Memory(Memory.GREEN,"a",null,"q");
        }

        ImageView imageView = findViewById(R.id.image);
        imageView.setImageURI(memory.getImageUri());

        TextView result = findViewById(R.id.textView2);
        //書いた内容を受け取る
        result.setText(" Title: "+memory.getTitle());

        TextView result1 = findViewById(R.id.textView);
        //書いた内容を受け取る
        result1.setText(" "+memory.getText());



        TextView date = findViewById(R.id.textView8);
        date.setText(" Date: "+memory.getTimestamp());

        Button b = findViewById(R.id.button);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        }
        );


    }
}