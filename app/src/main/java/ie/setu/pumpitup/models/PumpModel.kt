package ie.setu.pumpitup.models

import android.net.Uri
import android.os.Parcelable
import com.google.gson.Gson
import kotlinx.parcelize.Parcelize
import java.io.Serializable
import android.content.Context
import com.google.gson.reflect.TypeToken
import java.io.File
import java.io.IOException

@Parcelize
data class PumpModel( var id: Long = 0,
                      var station: String = "",
                      var petrol: Double = 0.0,
                      var diesel: Double = 0.0,
                      var eircode: String = "",
                      var address: String = "",
                      var image: String = ""

) : Serializable, Parcelable

@Parcelize
data class Location(var lat: Double = 0.0,
                    var lng: Double = 0.0,
                    var zoom: Float = 0f) : Parcelable


