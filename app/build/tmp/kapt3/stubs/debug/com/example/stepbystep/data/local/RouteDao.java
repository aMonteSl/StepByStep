package com.example.stepbystep.data.local;

/**
 * Interfaz de acceso a datos (DAO) para las operaciones de base de datos
 * relacionadas con rutas y puntos. Define métodos CRUD y consultas específicas.
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\t\bg\u0018\u00002\u00020\u0001J\u0016\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u00a7@\u00a2\u0006\u0002\u0010\u0006J\u0016\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\bH\u00a7@\u00a2\u0006\u0002\u0010\tJ\u000e\u0010\n\u001a\u00020\u0003H\u00a7@\u00a2\u0006\u0002\u0010\u000bJ\u0016\u0010\f\u001a\u00020\u00032\u0006\u0010\r\u001a\u00020\u000eH\u00a7@\u00a2\u0006\u0002\u0010\u000fJ\u0014\u0010\u0010\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00130\u00120\u0011H\'J\u001c\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00050\u00122\u0006\u0010\r\u001a\u00020\u000eH\u00a7@\u00a2\u0006\u0002\u0010\u000fJ\u0018\u0010\u0015\u001a\u0004\u0018\u00010\b2\u0006\u0010\r\u001a\u00020\u000eH\u00a7@\u00a2\u0006\u0002\u0010\u000fJ\u0018\u0010\u0016\u001a\u0004\u0018\u00010\u00132\u0006\u0010\r\u001a\u00020\u000eH\u00a7@\u00a2\u0006\u0002\u0010\u000fJ\u0016\u0010\u0017\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u00a7@\u00a2\u0006\u0002\u0010\u0006J\u0016\u0010\u0017\u001a\u00020\u000e2\u0006\u0010\u0007\u001a\u00020\bH\u00a7@\u00a2\u0006\u0002\u0010\tJ\u001c\u0010\u0018\u001a\u00020\u00032\f\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u00050\u0012H\u00a7@\u00a2\u0006\u0002\u0010\u001aJ\u0016\u0010\u001b\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\bH\u00a7@\u00a2\u0006\u0002\u0010\t\u00a8\u0006\u001c"}, d2 = {"Lcom/example/stepbystep/data/local/RouteDao;", "", "delete", "", "point", "Lcom/example/stepbystep/data/local/PointEntity;", "(Lcom/example/stepbystep/data/local/PointEntity;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "route", "Lcom/example/stepbystep/data/local/RouteEntity;", "(Lcom/example/stepbystep/data/local/RouteEntity;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deleteAllRoutes", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deletePointsForRoute", "routeId", "", "(JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getAllRoutesWithPoints", "Landroidx/lifecycle/LiveData;", "", "Lcom/example/stepbystep/data/local/RouteWithPoints;", "getPointsForRoute", "getRouteById", "getRouteWithPointsById", "insert", "insertAll", "points", "(Ljava/util/List;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "update", "app_debug"})
@androidx.room.Dao()
public abstract interface RouteDao {
    
    /**
     * Inserta una nueva ruta en la base de datos.
     * @param route La entidad ruta a insertar
     * @return El ID generado para la nueva ruta insertada
     */
    @androidx.room.Insert()
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object insert(@org.jetbrains.annotations.NotNull()
    com.example.stepbystep.data.local.RouteEntity route, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Long> $completion);
    
    /**
     * Actualiza una ruta existente en la base de datos.
     * @param route La entidad ruta con los nuevos datos
     */
    @androidx.room.Update()
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object update(@org.jetbrains.annotations.NotNull()
    com.example.stepbystep.data.local.RouteEntity route, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    /**
     * Elimina una ruta de la base de datos.
     * @param route La entidad ruta a eliminar
     */
    @androidx.room.Delete()
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object delete(@org.jetbrains.annotations.NotNull()
    com.example.stepbystep.data.local.RouteEntity route, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    /**
     * Inserta un nuevo punto en la base de datos.
     * @param point La entidad punto a insertar
     */
    @androidx.room.Insert()
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object insert(@org.jetbrains.annotations.NotNull()
    com.example.stepbystep.data.local.PointEntity point, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    /**
     * Inserta múltiples puntos en la base de datos.
     * @param points Lista de entidades punto a insertar
     */
    @androidx.room.Insert()
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object insertAll(@org.jetbrains.annotations.NotNull()
    java.util.List<com.example.stepbystep.data.local.PointEntity> points, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    /**
     * Elimina un punto de la base de datos.
     * @param point La entidad punto a eliminar
     */
    @androidx.room.Delete()
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object delete(@org.jetbrains.annotations.NotNull()
    com.example.stepbystep.data.local.PointEntity point, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    /**
     * Obtiene todas las rutas junto con sus puntos.
     * @return LiveData con la lista de rutas y sus puntos
     */
    @androidx.room.Transaction()
    @androidx.room.Query(value = "SELECT * FROM routes")
    @org.jetbrains.annotations.NotNull()
    public abstract androidx.lifecycle.LiveData<java.util.List<com.example.stepbystep.data.local.RouteWithPoints>> getAllRoutesWithPoints();
    
    /**
     * Obtiene una ruta por su ID.
     * @param routeId El ID de la ruta a buscar
     * @return La entidad ruta encontrada o null si no existe
     */
    @androidx.room.Query(value = "SELECT * FROM routes WHERE id = :routeId")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getRouteById(long routeId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.example.stepbystep.data.local.RouteEntity> $completion);
    
    /**
     * Obtiene todos los puntos asociados a una ruta.
     * @param routeId El ID de la ruta cuyos puntos se desean obtener
     * @return Lista de entidades punto asociadas a la ruta
     */
    @androidx.room.Query(value = "SELECT * FROM points WHERE routeId = :routeId")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getPointsForRoute(long routeId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.example.stepbystep.data.local.PointEntity>> $completion);
    
    /**
     * Obtiene una ruta junto con sus puntos por su ID.
     * @param routeId El ID de la ruta a buscar
     * @return La entidad RouteWithPoints encontrada o null si no existe
     */
    @androidx.room.Transaction()
    @androidx.room.Query(value = "SELECT * FROM routes WHERE id = :routeId")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getRouteWithPointsById(long routeId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.example.stepbystep.data.local.RouteWithPoints> $completion);
    
    /**
     * Elimina todas las rutas de la base de datos.
     */
    @androidx.room.Query(value = "DELETE FROM routes")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object deleteAllRoutes(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    /**
     * Elimina todos los puntos asociados a una ruta.
     * @param routeId El ID de la ruta cuyos puntos se desean eliminar
     */
    @androidx.room.Query(value = "DELETE FROM points WHERE routeId = :routeId")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object deletePointsForRoute(long routeId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
}