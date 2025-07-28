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
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "personal_cost_dashboard_db"
                )
                    .fallbackToDestructiveMigration() // ⬅️ Wipe + rebuild DB on version change
                    .build()

                INSTANCE = instance
                instance
            }
        }
    }
}
