package com.lesliefang.janusdemo2;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import pub.devrel.easypermissions.EasyPermissions;

public class MainActivity extends AppCompatActivity {
    String[] perms = {Manifest.permission.CAMERA, Manifest.permission.RECORD_AUDIO};
    EditText etUserName;
    EditText etVideoRoom;
    Button btnStart;
    CheckBox screenck;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        etUserName = findViewById(R.id.et_username);
        etVideoRoom = findViewById(R.id.et_videoroom);
        btnStart = findViewById(R.id.btn_start);
        screenck = findViewById(R.id.screenck);

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userName = etUserName.getText().toString().trim();
                String roomName = etVideoRoom.getText().toString().trim();
                if (roomName.isEmpty()) {
                    Toast.makeText(MainActivity.this, "请输入房间名", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (userName.isEmpty()) {
                    Toast.makeText(MainActivity.this, "请输入用户名", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (!EasyPermissions.hasPermissions(MainActivity.this, perms)) {
                    EasyPermissions.requestPermissions(MainActivity.this, "需要相机和录音权限",
                            100, perms);
                } else {
                    Intent intent = new Intent(MainActivity.this, VideoRoomActivity.class);
                    intent.putExtra("userName", userName);
                    intent.putExtra("roomName", Integer.parseInt(roomName));
                    intent.putExtra("shareScreen", screenck.isChecked());
                    startActivity(intent);
                }
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }
}