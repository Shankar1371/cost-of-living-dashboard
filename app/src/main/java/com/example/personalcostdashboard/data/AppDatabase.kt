package com.example.personalcostdashboard.data

import android.content.Context
import androidx.room.*
import com.example.personalcostdashboard.ui.Settings.UserSettings
import com.example.personalcostdashboard.data.Converters
import com.example.personalcostdashboard.ui.Settings.SettingsDao

@Database(
    entities = [Expense::class, UserSettings::class],
    version = 2,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun expenseDao(): ExpenseDao
    abstract fun settingsDao(): SettingsDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            // Return the cached instance if available, otherwise build a new one
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "personal_cost_dashboard_db"
                )
                    // Recreate the database if a migration isn't supplied
                    .fallbackToDestructiveMigration()
                    .build()

                // Store the instance for future use
                INSTANCE = instance
                instance
            }
        }
    }
}
