package com.parkitalia.android.extra;

import android.content.Context;
import android.widget.Toast;

public class Util
{

    public static void Toast(Context context, String text)
    {
        Toast.makeText(context, text, Toast.LENGTH_SHORT).show();

    }
}
