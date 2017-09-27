package com.example.leakypete.operationgreentampon;

import android.opengl.GLSurfaceView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.leakypete.operationgreentampon.models.MyGLSurfaceView;

public class OpenGLActivity extends AppCompatActivity {
    private GLSurfaceView mGLView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open_gl);

        mGLView = new MyGLSurfaceView(this);
        setContentView(mGLView);

    }
}
