package com.example.myapptaskm

import androidx.room.TypeConverter

class CharSequenceConverter {
    @TypeConverter
    fun fromCharSequence(value: CharSequence?): String? {
        return value?.toString()
    }

    @TypeConverter
    fun toCharSequence(value: String?): CharSequence? {
        return value
    }
}
