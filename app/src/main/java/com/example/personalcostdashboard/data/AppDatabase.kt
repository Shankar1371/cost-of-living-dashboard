package com.example.personalcostdashboard.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.personalcostdashboard.data.Converters

@Database(entities = [Expense::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)  // If you're using Date or other complex types
abstract class AppDatabase : RoomDatabase() {

    abstract fun expenseDao(): ExpenseDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "personal_cost_dashboard_db"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
