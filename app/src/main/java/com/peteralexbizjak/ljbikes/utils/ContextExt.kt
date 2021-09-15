package com.peteralexbizjak.ljbikes.utils

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import androidx.core.content.ContextCompat
import com.google.android.gms.maps.model.BitmapDescriptor
import com.google.android.gms.maps.model.BitmapDescriptorFactory

internal fun Context.bitmapDescriptorFromVector(resourceID: Int): BitmapDescriptor {
    val vectorDrawable = ContextCompat.getDrawable(this, resourceID)
    vectorDrawable!!.setBounds(
        0,
        0,
        vectorDrawable.intrinsicWidth,
        vectorDrawable.intrinsicHeight
    )
    val bitmap = Bitmap.createBitmap(
        vectorDrawable.intrinsicWidth,
        vectorDrawable.intrinsicHeight,
        Bitmap.Config.ARGB_8888
    )
    vectorDrawable.draw(Canvas(bitmap))
    return BitmapDescriptorFactory.fromBitmap(bitmap)
}