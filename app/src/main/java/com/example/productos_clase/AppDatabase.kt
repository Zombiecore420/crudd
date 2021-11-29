package com.example.productos_clase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Producto::class], version = 1)
abstract class AppDatabase: RoomDatabase() {
    abstract fun productos(): ProductosDao

    companion object{
        @Volatile
        private var INSTANCE:AppDatabase? = null

        fun getDatabase(context:Context):AppDatabase{
            var tempinstance = INSTANCE

            if(tempinstance != null){
                return tempinstance

            }
            synchronized(this){
                var instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "app_database"
                ).build()
                    INSTANCE= instance
                     return instance
            }
        }
    }

}