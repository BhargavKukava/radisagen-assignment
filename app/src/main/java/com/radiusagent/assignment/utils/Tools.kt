package com.radiusagent.assignment.utils

import android.content.Context
import android.graphics.drawable.Drawable
import androidx.core.app.ActivityCompat

class Tools {
    companion object{
        fun getDrawableByName(context: Context,name:String): Drawable?{
            val resID =context.resources.getIdentifier(name.replace("-","_"), "drawable", context.packageName)
            return ActivityCompat.getDrawable(context,resID)
        }
    }
}