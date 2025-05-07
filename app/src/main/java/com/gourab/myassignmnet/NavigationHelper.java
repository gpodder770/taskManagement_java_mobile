package com.gourab.myassignmnet;

import android.app.Activity;
import android.view.View;
import android.widget.Button;

public class NavigationHelper {
    public static void setupBackButton(Activity activity, int buttonId) {
        View view = activity.findViewById(buttonId); // ✅ Fix: Use View to get button
        if (view instanceof Button) {
            Button backButton = (Button) view;
            backButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    activity.finish(); // ✅ Closes the current activity
                }
            });
        }
    }
}
