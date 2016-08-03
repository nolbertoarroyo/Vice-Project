package com.test.vice20;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.job.JobInfo;
import android.app.job.JobParameters;
import android.app.job.JobService;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v4.app.NotificationCompat;

import com.test.vice20.Activities.MainActivity;
import com.test.vice20.Interfaces.NewsServiceInterface;
import com.test.vice20.Models.Item;
import com.test.vice20.Models.News;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by kitty on 8/3/16.
 */

@TargetApi(21)
public class NotificationJobService extends JobService {

    private Item latestArticle;
    private NewsServiceInterface newsServiceInterface;

    @Override
    public boolean onStartJob(JobParameters params) {

        setLatestArticle();

        return false;
    }

    @Override
    public boolean onStopJob(JobParameters params) {
        return false;
    }

    public void setLatestArticle() {

        //check internet connectivity
        ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(MainActivity.baseURL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            newsServiceInterface = retrofit.create(NewsServiceInterface.class);

            newsServiceInterface.getTodayList(0).enqueue(new Callback<News>() {
                @Override
                public void onResponse(Call<News> call, Response<News> response) {
                    latestArticle = response.body().data.getItems().get(0);
                    setNotification(latestArticle.getTitle(), latestArticle.getPreview());
                }

                @Override
                public void onFailure(Call<News> call, Throwable t) {
                    setNotification("API call failed", "");
                }
            });
        } else {
            setNotification("No internet connection", "");
        }
    }

    public void setNotification (String title, String preview) {
        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext());
        builder.setSmallIcon(android.R.mipmap.sym_def_app_icon);
        builder.setContentTitle(title);
        builder.setContentText(preview);
        builder.setAutoCancel(true);
        Notification notification = builder.build();
        notificationManager.notify(0, notification);
    }

}
