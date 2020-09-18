package tgh2020.viola;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.Random;

import androidx.constraintlayout.widget.ConstraintLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity {



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

        Memory.addMemory(new Memory(Memory.GREEN, "MochiMochi", null, "Welcome!"));
        Memory.addMemory(new Memory(Memory.GREEN, "Mochi", null, "Hello"));
        Memory.addMemory(new Memory(Memory.YELLOW, "Mochi", null, "Welcome!"));
        Memory.addMemory(new Memory(Memory.GREEN, "MochiMochi", null, "Welcome!"));
        Memory.addMemory(new Memory(Memory.YELLOW, "MochiMochi", null, "Welcome!"));
        Memory.addMemory(new Memory(Memory.PINK, "MochiMochi", null, "Welcome!"));
        Memory.addMemory(new Memory(Memory.PINK, "MochiMochi", null, "Welcome!"));

        // 問題：　以下のスニペットの中から反復するパターンを見出し、
        // Memoryオブジェクトのコレクションに対するループ式として成り立つようにリファクタリングしてください。
        //
        // また、DetailActivityにMemoryオブジェクトを渡すのであれば、
        // Memoryクラスはjava.io.Serializableインタフェースを実装するよう修正する必要があります。
        RelativeLayout mainLayout = findViewById(R.id.mainLayout);

        int leftMarginArray[] = {120,200,250,400,500,600,700};
        int topMarginArray[] = {700,500,800,400,700,750,800};
        int i = 0;

        for(Memory value : Memory.getMemories())
        {
            if (i >= 7) {
                break;
            }

            RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(100, 100);
            ImageView ball0 = new ImageView(this);
            ball0.setImageResource(R.drawable.ball);
            value.getColor();
            if (value.getColor() == Memory.GREEN) {
                ball0.setColorFilter(Color.parseColor("#bceb34"));
            }
            else if (value.getColor() == Memory.PINK) {
                ball0.setColorFilter(Color.parseColor("#eb34ba"));
            }
            else {
                ball0.setColorFilter(Color.parseColor("#ebd534"));
            }

            params.leftMargin = leftMarginArray[i];
            params.topMargin = topMarginArray[i];
            mainLayout.addView(ball0, params);

            i = i + 1;

            ball0.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(MainActivity.this, DetailActivity.class);
                    intent.putExtra("memory", 0);
                    startActivity(intent);
                }
            });


        }

    }
}
//import android.content.Intent;
//import android.os.Bundle;
//import android.view.View;
//import android.widget.Button;
//import android.widget.ImageView;
//
//public class MainActivity extends AppCompatActivity {
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//
//        Button input = findViewById(R.id.btnInput);
//        input.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(MainActivity.this, InputActivity.class);
//                startActivity(intent);
//            }
//        });
//        Button detail = findViewById(R.id.btnDetail);
//        detail.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                Intent intent = new Intent(MainActivity.this, DetailActivity.class);
//                startActivity(intent);
//            }
//        });
//
//        ImageView imageView1 = findViewById(R.id.tree);
//        imageView1.setImageResource(R.drawable.tree);
//
//    }
//
//
//}