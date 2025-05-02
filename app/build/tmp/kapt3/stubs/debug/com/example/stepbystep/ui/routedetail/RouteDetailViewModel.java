package com.example.stepbystep.ui.routedetail;

/**
 * ViewModel para la pantalla de detalle de ruta.
 *
 * Se encarga de:
 * - Cargar los datos de la ruta seleccionada
 * - Preparar los datos para el gráfico de elevación
 * - Exportar la ruta a formato GPX para compartir
 *
 * @param context Contexto necesario para acceder a la base de datos y archivos
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000n\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0006\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J(\u0010\"\u001a\u00020\t2\u0006\u0010#\u001a\u00020$2\u0006\u0010%\u001a\u00020$2\u0006\u0010&\u001a\u00020$2\u0006\u0010\'\u001a\u00020$H\u0002J\u000e\u0010(\u001a\u00020)H\u0086@\u00a2\u0006\u0002\u0010*J\u000e\u0010+\u001a\u00020,2\u0006\u0010-\u001a\u00020.J\u0016\u0010/\u001a\u00020,2\f\u00100\u001a\b\u0012\u0004\u0012\u0002010\u0007H\u0002R&\u0010\u0005\u001a\u001a\u0012\u0016\u0012\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\t0\b0\u00070\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u000b0\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R)\u0010\f\u001a\u001a\u0012\u0016\u0012\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\t0\b0\u00070\r\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00110\r\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u000fR\u0017\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00110\r\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u000fR\u0017\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00110\r\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u000fR\u0017\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00110\r\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u000fR\u0017\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u00110\r\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u000fR\u000e\u0010\u001b\u001a\u00020\u001cX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u000b0\r\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u000fR\u0017\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020 0\r\u00a2\u0006\b\n\u0000\u001a\u0004\b!\u0010\u000f\u00a8\u00062"}, d2 = {"Lcom/example/stepbystep/ui/routedetail/RouteDetailViewModel;", "Landroidx/lifecycle/ViewModel;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "_chartData", "Landroidx/lifecycle/MutableLiveData;", "", "Lkotlin/Pair;", "", "_route", "Lcom/example/stepbystep/domain/model/Route;", "chartData", "Landroidx/lifecycle/LiveData;", "getChartData", "()Landroidx/lifecycle/LiveData;", "formattedDate", "", "getFormattedDate", "formattedDistance", "getFormattedDistance", "formattedDuration", "getFormattedDuration", "formattedElevation", "getFormattedElevation", "formattedElevationGain", "getFormattedElevationGain", "repository", "Lcom/example/stepbystep/data/repository/RouteRepository;", "route", "getRoute", "routeImageVisibility", "", "getRouteImageVisibility", "calculateDistance", "lat1", "", "lon1", "lat2", "lon2", "exportGpx", "Ljava/io/File;", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "loadRoute", "", "routeId", "", "prepareChartData", "points", "Lcom/example/stepbystep/domain/model/Point;", "app_debug"})
public final class RouteDetailViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull()
    private final android.content.Context context = null;
    @org.jetbrains.annotations.NotNull()
    private final com.example.stepbystep.data.repository.RouteRepository repository = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.MutableLiveData<com.example.stepbystep.domain.model.Route> _route = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<com.example.stepbystep.domain.model.Route> route = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.MutableLiveData<java.util.List<kotlin.Pair<java.lang.Float, java.lang.Float>>> _chartData = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<java.util.List<kotlin.Pair<java.lang.Float, java.lang.Float>>> chartData = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<java.lang.String> formattedDistance = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<java.lang.String> formattedDuration = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<java.lang.String> formattedElevation = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<java.lang.String> formattedElevationGain = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<java.lang.String> formattedDate = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<java.lang.Integer> routeImageVisibility = null;
    
    public RouteDetailViewModel(@org.jetbrains.annotations.NotNull()
    android.content.Context context) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<com.example.stepbystep.domain.model.Route> getRoute() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.util.List<kotlin.Pair<java.lang.Float, java.lang.Float>>> getChartData() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.lang.String> getFormattedDistance() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.lang.String> getFormattedDuration() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.lang.String> getFormattedElevation() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.lang.String> getFormattedElevationGain() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.lang.String> getFormattedDate() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.lang.Integer> getRouteImageVisibility() {
        return null;
    }
    
    /**
     * Carga la información de la ruta especificada desde el repositorio.
     * También prepara los datos para el gráfico de elevación.
     *
     * @param routeId ID de la ruta a cargar
     */
    public final void loadRoute(long routeId) {
    }
    
    /**
     * Prepara los datos para el gráfico de elevación.
     * Convierte la lista de puntos a pares (distancia, altitud) donde la distancia
     * es acumulativa desde el inicio de la ruta.
     *
     * @param points Lista de puntos geográficos de la ruta
     */
    private final void prepareChartData(java.util.List<com.example.stepbystep.domain.model.Point> points) {
    }
    
    /**
     * Calcula la distancia entre dos coordenadas geográficas usando la fórmula haversine.
     * El resultado se expresa en metros.
     */
    private final float calculateDistance(double lat1, double lon1, double lat2, double lon2) {
        return 0.0F;
    }
    
    /**
     * Exporta la ruta actual a un archivo GPX para compartir.
     * Crea un archivo temporal con toda la información de la ruta y sus puntos.
     *
     * @return Archivo GPX generado
     * @throws IllegalStateException si no hay ruta cargada
     */
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object exportGpx(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.io.File> $completion) {
        return null;
    }
}