/*
 * Copyright (C) 2023 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.inventory.data

/**
 * Repository that provides insert, update, delete, and retrieve of [Item] from a given data source.
 */
import kotlinx.coroutines.flow.Flow

/**
 * Interface yang menyediakan metode untuk melakukan operasi CRUD (Create, Read, Update, Delete)
 * terhadap data [Item] dari sumber data tertentu (misalnya database).
 */
interface ItemsRepository {
    /**
    * Mengambil semua item secara streaming dari sumber data yang diberikan.
    * Menggunakan Flow untuk memberikan data secara real-time.
    */
    fun getAllItemsStream(): Flow<List<Item>>

    /**
     * Mengambil satu item berdasarkan ID secara streaming.
     * Jika item ditemukan, akan dikembalikan dalam bentuk Flow yang berisi Item,
     * jika tidak ditemukan, akan mengembalikan null.
     */
    fun getItemStream(id: Int): Flow<Item?>

    /**
     * Menyisipkan item ke dalam sumber data.
     * Metode ini dijalankan secara asinkron (suspend) untuk menghindari pemblokiran UI.
     */
    suspend fun insertItem(item: Item)

    /**
     * Menghapus item dari sumber data.
     * Metode ini dijalankan secara asinkron untuk operasi penghapusan.
     */
    suspend fun deleteItem(item: Item)

    /**
     * Memperbarui item yang sudah ada di sumber data.
     * Metode ini dijalankan secara asinkron untuk menghindari pemblokiran UI.
     */
    suspend fun updateItem(item: Item)
}
