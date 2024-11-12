package com.example.inventory.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

/**
 * Database class with a singleton Instance object.
 */
// Mendefinisikan kelas database utama untuk aplikasi
@Database(entities = [Item::class], version = 1, exportSchema = false)
abstract class InventoryDatabase : RoomDatabase() {

    // Fungsi abstrak untuk mendapatkan instance dari ItemDao.
    // Room akan menyediakan implementasinya secara otomatis.
    abstract fun itemDao(): ItemDao

    //membuat instance singleton dari database
    companion object {

        @Volatile
        private var Instance: InventoryDatabase? = null

        // Fungsi untuk mendapatkan instance dari database, dengan memastikan hanya ada satu instance yang digunakan
        fun getDatabase(context: Context): InventoryDatabase {
            // Jika instance sudah ada, kembalikan instance yang sudah ada
            return Instance ?: synchronized(this) {
                // Jika instance belum ada, buat instance baru menggunakan Room dan atur agar hanya dibuat sekali
                Room.databaseBuilder(context, InventoryDatabase::class.java, "item_database")
                    .build() // Membuat instance database Room
                    .also { Instance = it } // Setelah instance dibuat, simpan instance dalam variabel Instance
            }
        }
    }
}