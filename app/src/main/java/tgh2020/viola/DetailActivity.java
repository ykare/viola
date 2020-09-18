package tgh2020.viola;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.ViewCompat;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


public class DetailActivity extends BaseActivity {




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

        LinearLayout layout = findViewById(R.id.bubble);
        switch(memory.getColor()) {
            case Memory.GREEN:
                ViewCompat.setBackgroundTintList(
                        layout,
                        ColorStateList.valueOf(Color.GREEN));
                break;
            case Memory.PINK:
                ViewCompat.setBackgroundTintList(
                        layout,
                        ColorStateList.valueOf(Color.RED));
                break;
            case Memory.YELLOW:
                ViewCompat.setBackgroundTintList(
                        layout,
                        ColorStateList.valueOf(Color.YELLOW));
                break;
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