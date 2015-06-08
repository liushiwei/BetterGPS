
package com.henghengsir.bettergps;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    
    @Override
    protected void onResume() {
        super.onResume();
        checkIfMockEnabled();
    }
    
    private void checkIfMockEnabled() {
        try {
            int mock_location = Settings.Secure.getInt(getContentResolver(), "mock_location");
            if (mock_location == 0) {
                try {
                    Settings.Secure.putInt(getContentResolver(), "mock_location", 1);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                mock_location = Settings.Secure.getInt(getContentResolver(), "mock_location");
            }

            if (mock_location == 0) {
                showDialog(EnableMockLocationDialogFragment.MOCKDIALOG);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    @Override
    protected Dialog onCreateDialog(int id) {
        // This is not done using a fragment directly as MapActivity does not
        // allow fragments...
        if (id == EnableMockLocationDialogFragment.MOCKDIALOG) {
            return EnableMockLocationDialogFragment.createDialog(this);
        }
        return super.onCreateDialog(id);
    };
}
