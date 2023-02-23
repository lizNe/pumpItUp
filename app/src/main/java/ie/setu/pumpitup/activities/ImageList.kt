package ie.setu.pumpitup.activities

    class ImageHashMap {
        companion object {
            val hashMapToHoldImages:HashMap<String,String> = HashMap<String, String>()

            fun loadImages(){
                hashMapToHoldImages.put("Amber", "amber")
                hashMapToHoldImages.put("Apple Green", "apple")
                hashMapToHoldImages.put("Circle K", "circlek")
                hashMapToHoldImages.put("Inver", "inver")
                hashMapToHoldImages.put("Morris", "morris")
                hashMapToHoldImages.put("Shell", "shell")
                hashMapToHoldImages.put("Texaco", "texaco")
                hashMapToHoldImages.put("Top Oil", "top")
                hashMapToHoldImages.put("Emo", "emo")
            }
            fun getImage( stationName:String): String? {
                return hashMapToHoldImages.get(stationName)
            }
        }
    }
