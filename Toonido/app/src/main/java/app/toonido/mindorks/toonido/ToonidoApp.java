package app.toonido.mindorks.toonido;

import android.app.Application;

import com.parse.Parse;

/**
 * Created by janisharali on 30/09/15.
 */
public class ToonidoApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        // Enable Local Datastore.
        Parse.enableLocalDatastore(this);
        Parse.initialize(this, ToonidoConstants.PARSE_APPLICATION_ID, ToonidoConstants.PARSE_CLIENT_KEY);
    }
}
