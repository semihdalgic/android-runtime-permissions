package permission.semihd.com.permissionapp;

import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import permission.semihd.com.permissionapp.util.PermissionUtil;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnCheckPerms = (Button) findViewById(R.id.btn_check_perms);
        btnCheckPerms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PermissionUtil.requestMissingPermissions(MainActivity.this);
                PermissionUtil.checkDrawOverlayPermission(MainActivity.this, false);
            }
        });

    }

    /**
     * Callback received when a permissions request has been completed.
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {

        if (requestCode == PermissionUtil.REQUEST_CODE_MISSING_PERMISSIONS) {
            for (int i = 0; i < grantResults.length; i++) {
                //CAMERA
                if (PermissionUtil.PERMISSION_GROUP_CAMERA[0].equals(permissions[i]) && grantResults[i] != PackageManager.PERMISSION_GRANTED) {
                    Log.e(TAG, "Camera is not granted");
                } else if (PermissionUtil.PERMISSION_GROUP_CAMERA[0].equals(permissions[i])) {
                    Log.i(TAG, "Camera is granted");
                }
                //RECORD AUDIO
                else if (PermissionUtil.PERMISSION_GROUP_RECORD_AUDIO[0].equals(permissions[i]) && grantResults[i] != PackageManager.PERMISSION_GRANTED) {
                    Log.e(TAG, "Record Audio is not granted");
                } else if (PermissionUtil.PERMISSION_GROUP_RECORD_AUDIO[0].equals(permissions[i])) {
                    Log.i(TAG, "Record Audio is granted");
                }
                //LOCATIONS
                else if (PermissionUtil.PERMISSION_GROUP_LOCATION[0].equals(permissions[i]) && grantResults[i] != PackageManager.PERMISSION_GRANTED) {
                    Log.e(TAG, "Location is not granted");
                } else if (PermissionUtil.PERMISSION_GROUP_LOCATION[0].equals(permissions[i])) {
                    Log.i(TAG, "Location is granted");
                }
                //PHONE
                else if (PermissionUtil.PERMISSION_GROUP_PHONE[0].equals(permissions[i]) && grantResults[i] != PackageManager.PERMISSION_GRANTED) {
                    Log.e(TAG, "Phone is not granted");
                } else if (PermissionUtil.PERMISSION_GROUP_PHONE[0].equals(permissions[i])) {
                    Log.e(TAG, "Phone is granted");
                }
                //SMS
                else if (PermissionUtil.PERMISSION_GROUP_SMS[0].equals(permissions[i]) && grantResults[i] != PackageManager.PERMISSION_GRANTED) {
                    Log.e(TAG, "SMS is not granted");
                } else if (PermissionUtil.PERMISSION_GROUP_SMS[0].equals(permissions[i])) {
                    Log.e(TAG, "SMS is granted");
                }
                //STORAGE
                else if (PermissionUtil.PERMISSION_GROUP_STORAGE[0].equals(permissions[i]) && grantResults[i] != PackageManager.PERMISSION_GRANTED) {
                    Log.e(TAG, "Storage is not granted");
                } else if (PermissionUtil.PERMISSION_GROUP_STORAGE[0].equals(permissions[i])) {
                    Log.e(TAG, "Storage is granted");
                }
            }

        } else {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }
}
