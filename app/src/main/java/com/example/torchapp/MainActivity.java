package com.example.torchapp;

import androidx.appcompat.app.AppCompatActivity;

import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.CameraManager;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    // At first the torch is off
    private boolean lightOn = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void setLightOnOff(View view) {
        // Todo: 1. connect camera manager (which manage flashlight)
        CameraManager cameraManager = (CameraManager) getSystemService(CAMERA_SERVICE);


        try {
            // toDo: 2. Ask if there is a camera, which has flashlight
            for(String id : cameraManager.getCameraIdList()){
                CameraCharacteristics cameraChar = cameraManager.getCameraCharacteristics(id);
                if(cameraChar.get(CameraCharacteristics.FLASH_INFO_AVAILABLE)){
                    // toDo: 3. set the flashlight on that camera on (torch mode to true)
                    if(!lightOn) {
                        cameraManager.setTorchMode(id, true);
                    }
                    else {
                        cameraManager.setTorchMode(id, false);
                    }

                    // Toggle the boolean on->off off->on
                    lightOn = !lightOn;

                }
            }
        } catch (CameraAccessException e) {
            e.printStackTrace();
        }







    }
}