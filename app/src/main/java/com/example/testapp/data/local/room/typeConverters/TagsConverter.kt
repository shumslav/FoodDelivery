package com.example.testapp.data.local.room.typeConverters

import androidx.room.TypeConverter

class TagsConverter {

    @TypeConverter
    fun fromTagsToString(tags: List<String>): String{
        var result = ""
        for (i in tags.indices){
            result+=tags[i]
            if (i != tags.size-1)
                result+="_"
        }
        return result
    }

    @TypeConverter
    fun fromStringToTags(str:String):List<String> = str.split("_")
}