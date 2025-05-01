package com.example.stepbystep.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

/**
 * Clase que define la base de datos Room para la aplicación StepByStep.
 * Gestiona las entidades RouteEntity y PointEntity, con versión 1 del schema.
 * Implementa el patrón Singleton para garantizar una única instancia en toda la app.
 */
@Database(entities = [RouteEntity::class, PointEntity::class], version = 1, exportSchema = false)
abstract class RouteRoomDatabase : RoomDatabase() {
    // Método abstracto que proporciona acceso al DAO para operaciones con la BD
    abstract fun routeDao(): RouteDao

    companion object {
        @Volatile  // Asegura que los cambios son visibles inmediatamente para todos los hilos
        private var INSTANCE: RouteRoomDatabase? = null

        /**
         * Método que devuelve la instancia única de la base de datos, creándola si no existe.
         * Utiliza el patrón Singleton con sincronización para evitar problemas en entornos multihilo.
         * 
         * @param context Contexto de la aplicación necesario para crear la BD
         * @return Instancia única de RouteRoomDatabase
         */
        fun getInstance(context: Context): RouteRoomDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,  // Usar applicationContext para evitar memory leaks
                    RouteRoomDatabase::class.java,
                    "stepbystep_database"        // Nombre del archivo de la base de datos
                )
                    // Uncomment for development only
                    // .allowMainThreadQueries()  // Solo habilitar durante desarrollo
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}
