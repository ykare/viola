package tgh2020.viola;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button input = findViewById(R.id.btnInput);
        input.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, InputActivity.class);
                startActivity(intent);
            }
        });
        Button detail = findViewById(R.id.btnDetail);
        detail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO: とりあえず保存されている記憶の小瓶の中からランダムに表示
                if (Memory.getMemories().isEmpty()) {
                    Toast.makeText(MainActivity.this, "記憶の小瓶が空っぽです", Toast.LENGTH_LONG).show();
                    return;
                }
                int size = Memory.getMemories().size();
                Memory memory = Memory.getMemories().get(new Random().nextInt(size));
                Intent intent = new Intent(MainActivity.this, DetailActivity.class);
                intent.putExtra("memory", memory);
                startActivity(intent);
            }
        });

    }
}