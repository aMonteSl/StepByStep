package com.example.stepbystep.ui.newroute;

/**
 * Actividad principal para la creación y grabación de nuevas rutas.
 *
 * Esta actividad permite al usuario:
 * - Iniciar/pausar/detener la grabación de una ruta
 * - Visualizar en tiempo real el trazado en un mapa
 * - Ver estadísticas de la ruta (distancia, tiempo, elevación)
 * - Cargar una ruta GPX de referencia
 *
 * Gestiona permisos de ubicación, comunicación con el servicio de rastreo,
 * y la actualización en tiempo real del mapa y estadísticas.
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0090\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000f\u0018\u00002\u00020\u00012\u00020\u00022\u00020\u00032\u00020\u0004B\u0005\u00a2\u0006\u0002\u0010\u0005J\u0010\u0010\u001e\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020!H\u0002J\b\u0010\"\u001a\u00020\u001fH\u0002J\b\u0010#\u001a\u00020\u001fH\u0002J\b\u0010$\u001a\u00020\u001fH\u0016J\b\u0010%\u001a\u00020\u001fH\u0016J\u0012\u0010&\u001a\u00020\u001f2\b\u0010\'\u001a\u0004\u0018\u00010(H\u0014J\u0010\u0010)\u001a\u00020*2\u0006\u0010+\u001a\u00020,H\u0016J\b\u0010-\u001a\u00020\u001fH\u0014J\b\u0010.\u001a\u00020\u001fH\u0016J\u0010\u0010/\u001a\u00020\u001f2\u0006\u00100\u001a\u000201H\u0016J\b\u00102\u001a\u00020\u001fH\u0016J\u0010\u00103\u001a\u00020\u001f2\u0006\u00104\u001a\u000205H\u0016J\u0010\u00106\u001a\u00020*2\u0006\u00107\u001a\u000208H\u0016J\b\u00109\u001a\u00020\u001fH\u0016J\u0010\u0010:\u001a\u00020*2\u0006\u0010+\u001a\u00020,H\u0016J\u0010\u0010;\u001a\u00020\u001f2\u0006\u0010<\u001a\u00020(H\u0014J\b\u0010=\u001a\u00020\u001fH\u0014J\b\u0010>\u001a\u00020\u001fH\u0014J\b\u0010?\u001a\u00020\u001fH\u0002J\b\u0010@\u001a\u00020\u001fH\u0002J\b\u0010A\u001a\u00020\u001fH\u0002J\b\u0010B\u001a\u00020\u001fH\u0002J\b\u0010C\u001a\u00020\u001fH\u0002J\b\u0010D\u001a\u00020\u001fH\u0002J\b\u0010E\u001a\u00020\u001fH\u0002J\b\u0010F\u001a\u00020\u001fH\u0002R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082D\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0011X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0013X\u0082.\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00070\u0015X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0017X\u0082.\u00a2\u0006\u0002\n\u0000R\u001b\u0010\u0018\u001a\u00020\u00198BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u001c\u0010\u001d\u001a\u0004\b\u001a\u0010\u001b\u00a8\u0006G"}, d2 = {"Lcom/example/stepbystep/ui/newroute/NewRouteActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "Lcom/example/stepbystep/util/LocationPermissionManager$PermissionCallback;", "Lcom/example/stepbystep/ui/newroute/RouteMapController$MapCallback;", "Lcom/example/stepbystep/ui/newroute/LocationServiceConnection$LocationCallback;", "()V", "TAG", "", "binding", "Lcom/example/stepbystep/databinding/ActivityNewRouteBinding;", "fusedClient", "Lcom/google/android/gms/location/FusedLocationProviderClient;", "locationCallback", "Lcom/google/android/gms/location/LocationCallback;", "locationRequest", "Lcom/google/android/gms/location/LocationRequest;", "mapController", "Lcom/example/stepbystep/ui/newroute/RouteMapController;", "permissionManager", "Lcom/example/stepbystep/util/LocationPermissionManager;", "selectGpxLauncher", "Landroidx/activity/result/ActivityResultLauncher;", "serviceConnection", "Lcom/example/stepbystep/ui/newroute/LocationServiceConnection;", "viewModel", "Lcom/example/stepbystep/ui/newroute/NewRouteViewModel;", "getViewModel", "()Lcom/example/stepbystep/ui/newroute/NewRouteViewModel;", "viewModel$delegate", "Lkotlin/Lazy;", "loadReferenceGpx", "", "uri", "Landroid/net/Uri;", "logViewModelState", "navigateToSaveScreen", "onBackgroundLocationPermissionDenied", "onBackgroundLocationPermissionGranted", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onCreateOptionsMenu", "", "menu", "Landroid/view/Menu;", "onDestroy", "onForegroundLocationPermissionGranted", "onLocationUpdated", "location", "Landroid/location/Location;", "onLowMemory", "onMapReady", "googleMap", "Lcom/google/android/gms/maps/GoogleMap;", "onOptionsItemSelected", "item", "Landroid/view/MenuItem;", "onPermissionDenied", "onPrepareOptionsMenu", "onSaveInstanceState", "outState", "onStart", "onStop", "openGpxFilePicker", "setupButtons", "setupLocationComponents", "setupObservers", "startForegroundTrackingOnly", "startLocationUpdates", "toggleTrackingState", "updateButtonVisibility", "app_debug"})
public final class NewRouteActivity extends androidx.appcompat.app.AppCompatActivity implements com.example.stepbystep.util.LocationPermissionManager.PermissionCallback, com.example.stepbystep.ui.newroute.RouteMapController.MapCallback, com.example.stepbystep.ui.newroute.LocationServiceConnection.LocationCallback {
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String TAG = "RouteTracking";
    private com.example.stepbystep.databinding.ActivityNewRouteBinding binding;
    @org.jetbrains.annotations.NotNull()
    private final kotlin.Lazy viewModel$delegate = null;
    private com.example.stepbystep.util.LocationPermissionManager permissionManager;
    private com.example.stepbystep.ui.newroute.RouteMapController mapController;
    private com.example.stepbystep.ui.newroute.LocationServiceConnection serviceConnection;
    private com.google.android.gms.location.FusedLocationProviderClient fusedClient;
    private com.google.android.gms.location.LocationRequest locationRequest;
    private com.google.android.gms.location.LocationCallback locationCallback;
    
    /**
     * Launcher para seleccionar archivos GPX como ruta de referencia
     */
    @org.jetbrains.annotations.NotNull()
    private final androidx.activity.result.ActivityResultLauncher<java.lang.String> selectGpxLauncher = null;
    
    public NewRouteActivity() {
        super();
    }
    
    private final com.example.stepbystep.ui.newroute.NewRouteViewModel getViewModel() {
        return null;
    }
    
    /**
     * Inicialización de la actividad
     */
    @java.lang.Override()
    protected void onCreate(@org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    /**
     * Configura los componentes de ubicación para actualizaciones en primer plano
     */
    private final void setupLocationComponents() {
    }
    
    /**
     * Configura los listeners para los botones de la UI
     */
    private final void setupButtons() {
    }
    
    /**
     * Configura los observers para los LiveData del ViewModel
     */
    private final void setupObservers() {
    }
    
    /**
     * Inicia las actualizaciones periódicas de ubicación en primer plano
     */
    private final void startLocationUpdates() {
    }
    
    /**
     * Alterna entre iniciar y detener la grabación de la ruta
     */
    private final void toggleTrackingState() {
    }
    
    /**
     * Navega a la pantalla de guardar ruta
     */
    private final void navigateToSaveScreen() {
    }
    
    /**
     * Actualiza la visibilidad del botón de pausar/reanudar
     */
    private final void updateButtonVisibility() {
    }
    
    /**
     * Abre el selector de archivos para elegir un GPX de referencia
     */
    private final void openGpxFilePicker() {
    }
    
    /**
     * Carga un archivo GPX como ruta de referencia
     */
    private final void loadReferenceGpx(android.net.Uri uri) {
    }
    
    /**
     * Inicia el rastreo en primer plano sin persistencia en segundo plano
     */
    private final void startForegroundTrackingOnly() {
    }
    
    @java.lang.Override()
    public void onForegroundLocationPermissionGranted() {
    }
    
    @java.lang.Override()
    public void onBackgroundLocationPermissionGranted() {
    }
    
    @java.lang.Override()
    public void onBackgroundLocationPermissionDenied() {
    }
    
    @java.lang.Override()
    public void onPermissionDenied() {
    }
    
    @java.lang.Override()
    public void onMapReady(@org.jetbrains.annotations.NotNull()
    com.google.android.gms.maps.GoogleMap googleMap) {
    }
    
    @java.lang.Override()
    public void onLocationUpdated(@org.jetbrains.annotations.NotNull()
    android.location.Location location) {
    }
    
    @java.lang.Override()
    public boolean onCreateOptionsMenu(@org.jetbrains.annotations.NotNull()
    android.view.Menu menu) {
        return false;
    }
    
    @java.lang.Override()
    public boolean onOptionsItemSelected(@org.jetbrains.annotations.NotNull()
    android.view.MenuItem item) {
        return false;
    }
    
    @java.lang.Override()
    public boolean onPrepareOptionsMenu(@org.jetbrains.annotations.NotNull()
    android.view.Menu menu) {
        return false;
    }
    
    @java.lang.Override()
    protected void onStart() {
    }
    
    @java.lang.Override()
    protected void onStop() {
    }
    
    @java.lang.Override()
    protected void onDestroy() {
    }
    
    @java.lang.Override()
    public void onLowMemory() {
    }
    
    @java.lang.Override()
    protected void onSaveInstanceState(@org.jetbrains.annotations.NotNull()
    android.os.Bundle outState) {
    }
    
    /**
     * Registra el estado actual del ViewModel en el log.
     */
    private final void logViewModelState() {
    }
}