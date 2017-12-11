package com.example.android.mygarden;

/*
* Copyright (C) 2017 The Android Open Source Project
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
*  	http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.RemoteViews;

import com.example.android.mygarden.ui.MainActivity;


public class PlantWidgetProvider extends AppWidgetProvider {

    private static final String LOG_TAG = PlantWidgetProvider.class.getSimpleName();

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId) {
        Log.v(LOG_TAG, "-> updateAppWidget");

        // Create an Intent to launch MainActivity when clicked
        Intent intent = new Intent(context, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);

        // Construct the RemoteViews object
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.plant_widget);

        // Widgets allow click handlers to only launch pending intents
        views.setOnClickPendingIntent(R.id.widget_plant_image, pendingIntent);

        // TODO (4): Create a PendingIntent for the PlantWateringService and setOnClickPendingIntent for widget_water_button

        // Instruct the widget manager to update the widget
        appWidgetManager.updateAppWidget(appWidgetId, views);
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        Log.v(LOG_TAG, "-> onUpdate");

        // There may be multiple widgets active, so update all of them
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId);
        }
    }

    @Override
    public void onDeleted(Context context, int[] appWidgetIds) {
        Log.v(LOG_TAG, "-> onDeleted");
        // Perform any action when one or more AppWidget instances have been deleted
    }

    @Override
    public void onEnabled(Context context) {
        Log.v(LOG_TAG, "-> onEnabled");
        // Perform any action when an AppWidget for this provider is instantiated
    }

    @Override
    public void onDisabled(Context context) {
        Log.v(LOG_TAG, "-> onDisabled");
        // Perform any action when the last AppWidget instance for this provider is deleted
    }

}