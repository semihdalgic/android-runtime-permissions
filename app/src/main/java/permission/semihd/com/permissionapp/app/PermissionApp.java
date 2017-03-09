package permission.semihd.com.permissionapp.app;


import android.app.Application;
import android.content.Context;

/**
 * Created by semihd on 9.12.2016.
 */
public class PermissionApp extends Application {

    private final String TAG = PermissionApp.class.getSimpleName();
    private static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        setContext(getApplicationContext());

    }

    private static void setContext(Context context) {
        PermissionApp.mContext = context;
    }

    public static Context getContext() {
        return PermissionApp.mContext;
    }
}

