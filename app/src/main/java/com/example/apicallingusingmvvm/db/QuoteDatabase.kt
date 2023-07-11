package com.example.apicallingusingmvvm.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import com.example.apicallingusingmvvm.models.Result
import androidx.room.RoomDatabase

@Database(entities = [Result::class], version = 1)
abstract class QuoteDatabase : RoomDatabase() {

    abstract fun quoteDAO(): QuoteDAO

    companion object {
        private var INSTANCE: QuoteDatabase? = null

        fun getQuote(context: Context): QuoteDatabase {

            if (INSTANCE == null) {
                synchronized(this) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        QuoteDatabase::class.java,
                        "quoteDB"
                    ).build()
                }
            }
            return INSTANCE!!

        }
    }

}