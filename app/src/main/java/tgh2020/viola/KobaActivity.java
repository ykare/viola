package tgh2020.viola;

import android.Manifest;
import android.content.ContentResolver;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class KobaActivity extends AppCompatActivity {
    private final int REQUEST_PERMISSION = 10;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_koba);

        if(Build.VERSION.SDK_INT >= 23){
            checkPermission();
        }
        else{
            readData();
        }
    }

    // Permissionの確認
    public void checkPermission() {
        // 既に許可している
        if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) ==
                PackageManager.PERMISSION_GRANTED) {
            readData();
        }
        // 拒否していた場合
        else {
            requestLocationPermission();
        }
    }

    // 許可を求める
    private void requestLocationPermission() {
        if (shouldShowRequestPermissionRationale(
                Manifest.permission.READ_EXTERNAL_STORAGE)) {
            requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                    REQUEST_PERMISSION);

        } else {
            Toast toast = Toast.makeText(this,
                    "許可されないとアプリが実行できません", Toast.LENGTH_SHORT);
            toast.show();

            requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE,},
                    REQUEST_PERMISSION);

        }
    }

    // 結果の受け取り
    @Override
    public void onRequestPermissionsResult(
            int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == REQUEST_PERMISSION) {
            // 使用が許可された
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                readData();
            } else {
                // それでも拒否された時の対応
                Toast toast = Toast.makeText(this,
                        "これ以上なにもできません", Toast.LENGTH_SHORT);
                toast.show();
            }
        }
    }

    // Intent でActivityへ移行
    private void readData() {
        ContentResolver contentResolver = getContentResolver();
        Cursor cursor = null;
        StringBuilder sb = null;

        List<String> paths = new ArrayList<>();
        // 例外を受け取る
        try {
            // images
            cursor = contentResolver.query(
                    MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                    null, null, null, null);

            if (cursor != null && cursor.moveToFirst()) {
                String str = String.format(
                        "MediaStore.Images = %s\n\n", cursor.getCount());

                sb = new StringBuilder(str);

                do {
                    paths.add(cursor.getString(cursor.getColumnIndex(
                            MediaStore.Images.Media.DATA)));

                } while (cursor.moveToNext());

                cursor.close();
            }
        } catch (Exception e) {
            e.printStackTrace();

            Toast toast = Toast.makeText(this,
                    "例外が発生、Permissionを許可していますか？", Toast.LENGTH_SHORT);
            toast.show();

            //MainActivityに戻す
            finish();
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }

        TableLayout yourLayout = (TableLayout) findViewById(R.id.imageTable);

        List<ImageView> imageViews = new ArrayList<>();
        for (int i = 0; i < yourLayout.getChildCount(); i++) {

            View subView = yourLayout.getChildAt(i);

            if (subView instanceof TableRow) {

                TableRow row = (TableRow) subView;
                for (int j = 0; j < row.getChildCount(); j++) {

                    View ImgViewCandidate = row.getChildAt(j);

                    if (ImgViewCandidate instanceof ImageView) {
                        imageViews.add((ImageView) ImgViewCandidate);
                    }

                }
            }

        }

        int k = 0;
        for (ImageView iv : imageViews) {

            if (k >= paths.size()) {
                break;
            }

            File imgFile = new File(paths.get(k));

            if (imgFile.exists()) {

                Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());

                iv.setImageBitmap(myBitmap);

            }
            k++;
        }
    }
}