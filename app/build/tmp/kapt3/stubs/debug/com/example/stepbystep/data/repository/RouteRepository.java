package com.example.stepbystep.data.repository;

/**
 * Repositorio que actúa como capa intermedia entre la fuente de datos (DAO)
 * y las capas superiores de la aplicación. Proporciona métodos para realizar
 * operaciones con rutas, abstrayendo la lógica de acceso a datos.
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0007\n\u0002\u0010\t\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0016\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\bH\u0086@\u00a2\u0006\u0002\u0010\u000eJ\u000e\u0010\u000f\u001a\u00020\fH\u0086@\u00a2\u0006\u0002\u0010\u0010J\u0016\u0010\u0011\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\bH\u0086@\u00a2\u0006\u0002\u0010\u000eJ\u0016\u0010\u0012\u001a\u00020\b2\u0006\u0010\u0013\u001a\u00020\u0014H\u0086@\u00a2\u0006\u0002\u0010\u0015J\u0016\u0010\u0016\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\bH\u0086@\u00a2\u0006\u0002\u0010\u000eR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001d\u0010\u0005\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00070\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n\u00a8\u0006\u0017"}, d2 = {"Lcom/example/stepbystep/data/repository/RouteRepository;", "", "dao", "Lcom/example/stepbystep/data/local/RouteDao;", "(Lcom/example/stepbystep/data/local/RouteDao;)V", "routes", "Landroidx/lifecycle/LiveData;", "", "Lcom/example/stepbystep/domain/model/Route;", "getRoutes", "()Landroidx/lifecycle/LiveData;", "addRoute", "", "route", "(Lcom/example/stepbystep/domain/model/Route;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "clearAllRoutes", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deleteRoute", "getRouteById", "routeId", "", "(JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "updateRoute", "app_debug"})
public final class RouteRepository {
    @org.jetbrains.annotations.NotNull()
    private final com.example.stepbystep.data.local.RouteDao dao = null;
    
    /**
     * Obtiene todas las rutas almacenadas en la base de datos.
     * Transforma automáticamente las entidades de base de datos en modelos de dominio.
     */
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<java.util.List<com.example.stepbystep.domain.model.Route>> routes = null;
    
    public RouteRepository(@org.jetbrains.annotations.NotNull()
    com.example.stepbystep.data.local.RouteDao dao) {
        super();
    }
    
    /**
     * Obtiene todas las rutas almacenadas en la base de datos.
     * Transforma automáticamente las entidades de base de datos en modelos de dominio.
     */
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.util.List<com.example.stepbystep.domain.model.Route>> getRoutes() {
        return null;
    }
    
    /**
     * Añade una nueva ruta a la base de datos.
     * @param route La ruta de dominio a guardar
     */
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object addRoute(@org.jetbrains.annotations.NotNull()
    com.example.stepbystep.domain.model.Route route, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    /**
     * Actualiza una ruta existente en la base de datos.
     * @param route La ruta con los datos actualizados
     */
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object updateRoute(@org.jetbrains.annotations.NotNull()
    com.example.stepbystep.domain.model.Route route, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    /**
     * Elimina una ruta de la base de datos junto con todos sus puntos.
     * @param route La ruta a eliminar
     */
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object deleteRoute(@org.jetbrains.annotations.NotNull()
    com.example.stepbystep.domain.model.Route route, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    /**
     * Elimina todas las rutas de la base de datos.
     */
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object clearAllRoutes(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    /**
     * Obtiene una ruta específica por su ID.
     * @param routeId El ID de la ruta a buscar
     * @return La ruta encontrada convertida a modelo de dominio
     * @throws IllegalArgumentException Si no se encuentra la ruta con el ID especificado
     */
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object getRouteById(long routeId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.example.stepbystep.domain.model.Route> $completion) {
        return null;
    }
}