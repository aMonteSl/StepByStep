package com.example.stepbystep.ui.saveroute;

/**
 * ViewModel para la pantalla de guardado de ruta.
 *
 * Se encarga de:
 * - Almacenar temporalmente los datos de la ruta a guardar
 * - Validar el formulario de entrada
 * - Realizar la operación de guardado en la base de datos
 * - Gestionar la ruta de la imagen asociada
 *
 * @param context Contexto necesario para acceder a la base de datos local
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000^\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0013\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0006\n\u0000\n\u0002\u0010\t\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u0002\n\u0002\b\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0006\u0010/\u001a\u000200J\u0010\u00101\u001a\u0002002\b\u00102\u001a\u0004\u0018\u00010\tJJ\u00103\u001a\u0002002\u0006\u0010\u0018\u001a\u00020\u000b2\u0006\u0010\u001a\u001a\u00020\r2\u0006\u0010\u001e\u001a\u00020\u000b2\u0006\u0010\u001c\u001a\u00020\u000b2\f\u00104\u001a\b\u0012\u0004\u0012\u00020\u00130\u00122\n\b\u0002\u00105\u001a\u0004\u0018\u00010\u00072\b\b\u0002\u00106\u001a\u00020$J\b\u00107\u001a\u000200H\u0002R\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\b\u001a\b\u0012\u0004\u0012\u00020\t0\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u000b0\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\f\u001a\b\u0012\u0004\u0012\u00020\r0\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u000b0\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u000b0\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0016\u0010\u0010\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\t0\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u0011\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00130\u00120\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\t0\u0015\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u0017\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u000b0\u0015\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0017R\u0017\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\r0\u0015\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u0017R\u0017\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u000b0\u0015\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u0017R\u0017\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u000b0\u0015\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010\u0017R\u0019\u0010 \u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\t0\u0015\u00a2\u0006\b\n\u0000\u001a\u0004\b!\u0010\u0017R\u0017\u0010\"\u001a\b\u0012\u0004\u0012\u00020$0#\u00a2\u0006\b\n\u0000\u001a\u0004\b\"\u0010%R\u000e\u0010&\u001a\u00020\'X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010(\u001a\b\u0012\u0004\u0012\u00020\t0\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b)\u0010*R\u0017\u0010+\u001a\b\u0012\u0004\u0012\u00020\t0\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b,\u0010*R\u001d\u0010-\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00130\u00120\u0015\u00a2\u0006\b\n\u0000\u001a\u0004\b.\u0010\u0017\u00a8\u00068"}, d2 = {"Lcom/example/stepbystep/ui/saveroute/SaveRouteViewModel;", "Landroidx/lifecycle/ViewModel;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "_altitudes", "Landroidx/lifecycle/MutableLiveData;", "", "_date", "", "_distance", "", "_duration", "", "_elevation", "_elevationGain", "_imagePath", "_routePoints", "", "Lcom/google/android/gms/maps/model/LatLng;", "date", "Landroidx/lifecycle/LiveData;", "getDate", "()Landroidx/lifecycle/LiveData;", "distance", "getDistance", "duration", "getDuration", "elevation", "getElevation", "elevationGain", "getElevationGain", "imagePath", "getImagePath", "isFormValid", "Landroidx/lifecycle/MediatorLiveData;", "", "()Landroidx/lifecycle/MediatorLiveData;", "repository", "Lcom/example/stepbystep/data/repository/RouteRepository;", "routeDescription", "getRouteDescription", "()Landroidx/lifecycle/MutableLiveData;", "routeName", "getRouteName", "routePoints", "getRoutePoints", "saveRoute", "", "setImagePath", "path", "setRouteData", "points", "altitudes", "isImported", "validateForm", "app_debug"})
public final class SaveRouteViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull()
    private final com.example.stepbystep.data.repository.RouteRepository repository = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.MutableLiveData<java.lang.Double> _distance = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<java.lang.Double> distance = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.MutableLiveData<java.lang.Long> _duration = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<java.lang.Long> duration = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.MutableLiveData<java.lang.Double> _elevationGain = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<java.lang.Double> elevationGain = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.MutableLiveData<java.lang.Double> _elevation = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<java.lang.Double> elevation = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.MutableLiveData<java.lang.String> _date = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<java.lang.String> date = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.MutableLiveData<java.lang.String> _imagePath = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<java.lang.String> imagePath = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.MutableLiveData<double[]> _altitudes = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.MutableLiveData<java.lang.String> routeName = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.MutableLiveData<java.lang.String> routeDescription = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.MutableLiveData<java.util.List<com.google.android.gms.maps.model.LatLng>> _routePoints = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<java.util.List<com.google.android.gms.maps.model.LatLng>> routePoints = null;
    
    /**
     * LiveData que indica si el formulario es válido para guardar.
     * Se actualiza automáticamente cuando cambian los campos del formulario.
     */
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.MediatorLiveData<java.lang.Boolean> isFormValid = null;
    
    public SaveRouteViewModel(@org.jetbrains.annotations.NotNull()
    android.content.Context context) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.lang.Double> getDistance() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.lang.Long> getDuration() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.lang.Double> getElevationGain() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.lang.Double> getElevation() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.lang.String> getDate() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.lang.String> getImagePath() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.MutableLiveData<java.lang.String> getRouteName() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.MutableLiveData<java.lang.String> getRouteDescription() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.util.List<com.google.android.gms.maps.model.LatLng>> getRoutePoints() {
        return null;
    }
    
    /**
     * LiveData que indica si el formulario es válido para guardar.
     * Se actualiza automáticamente cuando cambian los campos del formulario.
     */
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.MediatorLiveData<java.lang.Boolean> isFormValid() {
        return null;
    }
    
    /**
     * Establece los datos de la ruta recibidos de la actividad anterior.
     *
     * @param distance Distancia total de la ruta en km
     * @param duration Duración total en milisegundos
     * @param elevationGain Ganancia de elevación total en metros
     * @param elevation Elevación máxima alcanzada en metros
     * @param points Lista de coordenadas que forman la ruta
     * @param altitudes Array con las altitudes de cada punto (opcional)
     * @param isImported Flag que indica si es una ruta importada de GPX
     */
    public final void setRouteData(double distance, long duration, double elevationGain, double elevation, @org.jetbrains.annotations.NotNull()
    java.util.List<com.google.android.gms.maps.model.LatLng> points, @org.jetbrains.annotations.Nullable()
    double[] altitudes, boolean isImported) {
    }
    
    /**
     * Establece la ruta de la imagen asociada a la ruta.
     *
     * @param path Ruta absoluta al archivo de imagen en el almacenamiento interno
     */
    public final void setImagePath(@org.jetbrains.annotations.Nullable()
    java.lang.String path) {
    }
    
    /**
     * Valida que el formulario tenga todos los campos requeridos.
     * Actualiza el LiveData isFormValid según el resultado.
     */
    private final void validateForm() {
    }
    
    /**
     * Guarda la ruta en la base de datos.
     * Convierte los datos temporales a un objeto Route y lo guarda
     * usando el repositorio.
     */
    public final void saveRoute() {
    }
}