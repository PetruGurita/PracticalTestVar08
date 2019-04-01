package ro.pub.cs.systems.eim.practicaltest01var08;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import java.util.Date;
import java.util.Random;

public class ProcessingThread extends Thread {
    private Context context = null;
    private boolean isRunning = true;

    private Random random = new Random();
    private String text;

    public ProcessingThread(Context context, String text) {
        this.context = context;
        this.text = text;
    }

    private void sendMessage() {
        Intent intent = new Intent();
        intent.setAction("lala");
        int idx = new Random().nextInt(text.length());
        String show = "";
        for (int i = 0; i < idx; ++i)
            show += "*";
        show += text.charAt(idx);
        for (int i = idx + 1; i < text.length(); ++i)
            show += "*";
        intent.putExtra("message", show);
        context.sendBroadcast(intent);
    }

    private void sleep() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException interruptedException) {
            interruptedException.printStackTrace();
        }
    }

    public void stopThread() {
        isRunning = false;
    }

    @Override
    public void run() {
        Log.d("[ProcessingThread]", "Thread has started!");
        while (isRunning) {
            sendMessage();
            sleep();
        }
        Log.d("[ProcessingThread]", "Thread has stopped!");


    }
}
