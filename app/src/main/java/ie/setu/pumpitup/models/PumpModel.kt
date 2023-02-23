package ie.setu.pumpitup.models

import android.net.Uri

data class PumpModel(var station: String = "",
                     var petrol: Double = 0.0,
                     var diesel: Double = 0.0,
                     var eircode: String = "",
                     var address: String = "",
                     var image: String = ""

                                                    )
