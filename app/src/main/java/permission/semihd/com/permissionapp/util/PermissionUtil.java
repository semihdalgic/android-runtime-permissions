package permission.semihd.com.permissionapp.util;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;

import permission.semihd.com.permissionapp.BuildConfig;
import permission.semihd.com.permissionapp.app.PermissionApp;


/**
 * Created by semihd on 26.04.2016.
 */
public class PermissionUtil {

    private static final String TAG = "PermissionUtil";

    private PermissionUtil() {
    }

    // Permission request codes for onRequestPermissionsResult.
    public static final int REQUEST_CODE_CAMERA_PERMISSION = 1;
    public static final int REQUEST_CODE_RECORD_AUDIO_PERMISSIONS = 2;
    public static final int REQUEST_CODE_PHONE_PERMISSION = 3;
    public static final int REQUEST_CODE_STORAGE_PERMISSION = 4;
    public static final int REQUEST_CODE_SMS_PERMISSIONS = 5;
    public static final int REQUEST_CODE_LOCATION_PERMISSIONS = 6;
    public static final int REQUEST_CODE_DRAW_OVERLAY = 7;
    public static final int REQUEST_CODE_MISSING_PERMISSIONS = 10;

    // Permission groups definitions.
    public static final String[] PERMISSION_GROUP_CAMERA = {Manifest.permission.CAMERA};
    public static final String[] PERMISSION_GROUP_RECORD_AUDIO = {Manifest.permission.RECORD_AUDIO};
    public static final String[] PERMISSION_GROUP_PHONE = {Manifest.permission.READ_PHONE_STATE};
    public static final String[] PERMISSION_GROUP_STORAGE = {Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE};
    public static final String[] PERMISSION_GROUP_SMS = {Manifest.permission.RECEIVE_SMS};
    public static final String[] PERMISSION_GROUP_LOCATION = {Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION};


    /**
     * This methods requests permission if the permission is not granted. Otherwise it returns true that means user already granted permission.
     *
     * @param activity
     * @param requestCode
     * @param permissions
     * @return
     */
    private static boolean requestPermission(Activity activity, int requestCode, String... permissions) {
        boolean result = true;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            for (String permission : permissions) {
                if (!hasPermission(permission)) {
                    // Should we show an explanation?
                    if (ActivityCompat.shouldShowRequestPermissionRationale(activity, permission)) {
                        ActivityCompat.requestPermissions(activity, permissions, requestCode);
                    } else {
                        ActivityCompat.requestPermissions(activity, permissions, requestCode);
                    }
                    result = false;
                    break;
                }
            }
        }
        return result;
    }

    /**
     * This methods requests permission if the permission is not granted. Otherwise it returns true that means user already granted permission.
     *
     * @param fragment
     * @param requestCode
     * @param permissions
     * @return
     */
    private static boolean requestPermission(Fragment fragment, int requestCode, String... permissions) {
        boolean result = true;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            for (String permission : permissions) {
                if (!hasPermission(permission)) {
                    // Should we show an explanation?
                    if (fragment.shouldShowRequestPermissionRationale(permission)) {
                        fragment.requestPermissions(permissions, requestCode);
                    } else {
                        fragment.requestPermissions(permissions, requestCode);
                    }
                    result = false;
                    break;
                }
            }
        }
        return result;
    }

    /**
     * This methods requests permission if the permission is not granted. Otherwise it returns true that means user already granted permission.
     *
     * @param fragment
     * @param requestCode
     * @param permissions
     * @return
     */

    private static boolean requestPermission(android.app.Fragment fragment, int requestCode, String... permissions) {
        boolean result = true;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            for (String permission : permissions) {
                if (!hasPermission(permission)) {
                    // Should we show an explanation?
                    if (fragment.shouldShowRequestPermissionRationale(permission)) {
                        fragment.requestPermissions(permissions, requestCode);
                    } else {
                        fragment.requestPermissions(permissions, requestCode);
                    }
                    result = false;
                    break;
                }
            }
        }
        return result;
    }

    /**
     * HasPermission methods only checks for permission status for given permission list.
     *
     * @param permissions
     * @return
     */
    private static boolean hasPermission(String... permissions) {
        boolean result = true;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            for (String permission : permissions) {
                if (ContextCompat.checkSelfPermission(PermissionApp.getContext(), permission) != PackageManager.PERMISSION_GRANTED) {
                    result = false;
                    break;
                }
            }
        }
        return result;
    }

    public static boolean hasCameraPermission() {
        return hasPermission(PERMISSION_GROUP_CAMERA);
    }

    public static boolean hasPhonePermission() {
        return hasPermission(PERMISSION_GROUP_PHONE);
    }

    public static boolean hasStoragePermission() {
        return hasPermission(PERMISSION_GROUP_STORAGE);
    }

    public static boolean hasSMSPermission() {
        return hasPermission(PERMISSION_GROUP_SMS);
    }

    public static boolean hasRecordAudioPermission() {
        return hasPermission(PERMISSION_GROUP_RECORD_AUDIO);
    }

    /**
     * We need this for GsmCellLocation
     */
    public static boolean hasLocationPermission() {
        return hasPermission(PERMISSION_GROUP_LOCATION);
    }

    /**
     * Checks if we already granted both Contacts and Phone group permissions
     *
     * @param
     * @return boolean
     */
    public static boolean hasPhoneAndContactPermissions() {
        return hasPermission(StringUtil.concatArrays(PERMISSION_GROUP_PHONE, PERMISSION_GROUP_CAMERA));
    }

    public static boolean requestContactPermission(Activity activity) {
        return requestPermission(activity, REQUEST_CODE_CAMERA_PERMISSION, PERMISSION_GROUP_CAMERA);
    }

    public static boolean requestPhonePermission(Activity activity) {
        return requestPermission(activity, REQUEST_CODE_PHONE_PERMISSION, PERMISSION_GROUP_PHONE);
    }

    public static boolean requestStoragePermission(Activity activity) {
        return requestPermission(activity, REQUEST_CODE_STORAGE_PERMISSION, PERMISSION_GROUP_STORAGE);
    }

    public static boolean requestMissingPermissions(Activity activity) {
        String[] missingPermissions = new String[0];
        if (!PermissionUtil.hasCameraPermission()) {
            missingPermissions = StringUtil.concatArrays(missingPermissions, PERMISSION_GROUP_CAMERA);
        }
        if (!PermissionUtil.hasRecordAudioPermission()) {
            missingPermissions = StringUtil.concatArrays(missingPermissions, PERMISSION_GROUP_RECORD_AUDIO);
        }
        if (!PermissionUtil.hasPhonePermission()) {
            missingPermissions = StringUtil.concatArrays(missingPermissions, PERMISSION_GROUP_PHONE);
        }
        if (!PermissionUtil.hasStoragePermission()) {
            missingPermissions = StringUtil.concatArrays(missingPermissions, PERMISSION_GROUP_STORAGE);
        }
        if (!PermissionUtil.hasSMSPermission()) {
            missingPermissions = StringUtil.concatArrays(missingPermissions, PERMISSION_GROUP_SMS);
        }
        if (!PermissionUtil.hasLocationPermission()) {
            missingPermissions = StringUtil.concatArrays(missingPermissions, PERMISSION_GROUP_LOCATION);
        }

        return requestPermission(activity, REQUEST_CODE_MISSING_PERMISSIONS, missingPermissions);
    }

    public static boolean hasMissingPermission() {
        if (!PermissionUtil.hasCameraPermission()) {
            return true;
        }
        if (!PermissionUtil.hasRecordAudioPermission()) {
            return true;
        }
        if (!PermissionUtil.hasPhonePermission()) {
            return true;
        }
        if (!PermissionUtil.hasStoragePermission()) {
            return true;
        }
        if (!PermissionUtil.hasSMSPermission()) {
            return true;
        }
        if (!PermissionUtil.hasLocationPermission()) {
            return true;
        }

        return false;
    }

    public static boolean requestCameraPermission(Fragment fragment) {
        return requestPermission(fragment, REQUEST_CODE_CAMERA_PERMISSION, PERMISSION_GROUP_CAMERA);
    }

    public static boolean requestRecordAudioPermission(Fragment fragment) {
        return requestPermission(fragment, REQUEST_CODE_RECORD_AUDIO_PERMISSIONS, PERMISSION_GROUP_RECORD_AUDIO);
    }

    public static boolean requestPhonePermission(Fragment fragment) {
        return requestPermission(fragment, REQUEST_CODE_PHONE_PERMISSION, PERMISSION_GROUP_PHONE);
    }

    public static boolean requestStoragePermission(Fragment fragment) {
        return requestPermission(fragment, REQUEST_CODE_STORAGE_PERMISSION, PERMISSION_GROUP_STORAGE);
    }

    public static boolean requestCameraPermission(android.app.Fragment fragment) {
        return requestPermission(fragment, REQUEST_CODE_CAMERA_PERMISSION, PERMISSION_GROUP_CAMERA);
    }

    public static boolean requestRecordAudioPermission(android.app.Fragment fragment) {
        return requestPermission(fragment, REQUEST_CODE_RECORD_AUDIO_PERMISSIONS, PERMISSION_GROUP_RECORD_AUDIO);
    }

    public static boolean requestPhonePermission(android.app.Fragment fragment) {
        return requestPermission(fragment, REQUEST_CODE_PHONE_PERMISSION, PERMISSION_GROUP_PHONE);
    }

    public static boolean requestStoragePermission(android.app.Fragment fragment) {
        return requestPermission(fragment, REQUEST_CODE_STORAGE_PERMISSION, PERMISSION_GROUP_STORAGE);
    }

    public static boolean requestSMSPermission(android.app.Fragment fragment) {
        return requestPermission(fragment, REQUEST_CODE_SMS_PERMISSIONS, PERMISSION_GROUP_SMS);
    }

    public static boolean requestLocationPermission(android.app.Fragment fragment) {
        return requestPermission(fragment, REQUEST_CODE_LOCATION_PERMISSIONS, PERMISSION_GROUP_LOCATION);
    }


    /**
     * Check that all given permissions have been granted by verifying that one entry in the
     * given array is of the value {@link PackageManager#PERMISSION_GRANTED}.
     *
     * @param grantResults all the permission request results
     * @return if permission granted
     * @see Activity#onRequestPermissionsResult(int, String[], int[])
     * @see Fragment#onRequestPermissionsResult(int, String[], int[])
     * @see android.app.Fragment#onRequestPermissionsResult(int, String[], int[])
     */
    public static boolean verifyPermission(int[] grantResults) {
        // At least one result must be checked.
        return grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED;
    }

    /**
     * Check that all given permissions have been granted by verifying that each entry in the
     * given array is of the value {@link PackageManager#PERMISSION_GRANTED}.
     *
     * @param grantResults all the permission request results
     * @return if permission granted
     * @see Activity#onRequestPermissionsResult(int, String[], int[])
     * @see Fragment#onRequestPermissionsResult(int, String[], int[])
     * @see android.app.Fragment#onRequestPermissionsResult(int, String[], int[])
     */
    public static boolean verifyPermissions(int[] grantResults) {
        // Verify that each required permission has been granted, otherwise return false.
        for (int result : grantResults) {
            if (result != PackageManager.PERMISSION_GRANTED) {
                return false;
            }
        }

        return true;
    }

    public static void openAppSettingsForPermissions() {
        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS, Uri.fromParts("package", BuildConfig.APPLICATION_ID, null));
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        PermissionApp.getContext().startActivity(intent);
    }

    public static void checkDrawOverlayPermission(Activity activity, boolean forceShow) {
        /** check if we already  have permission to draw over other apps */
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (!Settings.canDrawOverlays(PermissionApp.getContext()) || forceShow) {
                /** if not construct intent to request permission */
                Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION, Uri.parse("package:" + PermissionApp.getContext().getPackageName()));
                /** request permission via start activity for result */
                activity.startActivityForResult(intent, REQUEST_CODE_DRAW_OVERLAY);
            }
        }
    }

    public static boolean hasDrawOverlayPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            return Settings.canDrawOverlays(PermissionApp.getContext());
        }
        return true;
    }
}
