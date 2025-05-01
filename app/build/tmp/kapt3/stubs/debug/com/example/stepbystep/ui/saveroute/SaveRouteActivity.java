package com.example.stepbystep.ui.saveroute;

/**
 * Actividad para guardar una ruta finalizada.
 *
 * Permite al usuario:
 * - Ver una previsualización de la ruta en un mapa
 * - Introducir un nombre y descripción para la ruta
 * - Seleccionar una imagen para asociarla con la ruta
 * - Guardar todos los datos en la base de datos local
 *
 * Recibe los datos de la ruta a través del Intent que la inicia,
 * ya sea desde NewRouteActivity o ImportRouteActivity.
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 -2\u00020\u0001:\u0001-B\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010\u0015\u001a\u00020\u0016H\u0002J\b\u0010\u0017\u001a\u00020\u0016H\u0002J\u0012\u0010\u0018\u001a\u00020\u00162\b\u0010\u0019\u001a\u0004\u0018\u00010\u001aH\u0014J\b\u0010\u001b\u001a\u00020\u0016H\u0014J\b\u0010\u001c\u001a\u00020\u0016H\u0016J\u0010\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020 H\u0016J\b\u0010!\u001a\u00020\u0016H\u0014J\b\u0010\"\u001a\u00020\u0016H\u0014J\u0010\u0010#\u001a\u00020\u00162\u0006\u0010$\u001a\u00020\u001aH\u0014J\b\u0010%\u001a\u00020\u0016H\u0014J\b\u0010&\u001a\u00020\u0016H\u0014J\b\u0010\'\u001a\u00020\u0016H\u0002J\b\u0010(\u001a\u00020\u0016H\u0002J\u0016\u0010)\u001a\u00020\u00162\f\u0010*\u001a\b\u0012\u0004\u0012\u00020,0+H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082.\u00a2\u0006\u0002\n\u0000R\u0014\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0010\u0010\f\u001a\u0004\u0018\u00010\rX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u000b0\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001b\u0010\u000f\u001a\u00020\u00108BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u0013\u0010\u0014\u001a\u0004\b\u0011\u0010\u0012\u00a8\u0006."}, d2 = {"Lcom/example/stepbystep/ui/saveroute/SaveRouteActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "()V", "binding", "Lcom/example/stepbystep/databinding/ActivitySaveRouteBinding;", "googleMap", "Lcom/google/android/gms/maps/GoogleMap;", "mapView", "Lcom/google/android/gms/maps/MapView;", "pickImage", "Landroidx/activity/result/ActivityResultLauncher;", "", "selectedImageUri", "Landroid/net/Uri;", "storagePermissionRequest", "viewModel", "Lcom/example/stepbystep/ui/saveroute/SaveRouteViewModel;", "getViewModel", "()Lcom/example/stepbystep/ui/saveroute/SaveRouteViewModel;", "viewModel$delegate", "Lkotlin/Lazy;", "checkAndRequestStoragePermission", "", "confirmDiscard", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onDestroy", "onLowMemory", "onOptionsItemSelected", "", "item", "Landroid/view/MenuItem;", "onPause", "onResume", "onSaveInstanceState", "outState", "onStart", "onStop", "openImagePicker", "saveImageToInternalStorage", "setupMap", "points", "", "Lcom/google/android/gms/maps/model/LatLng;", "Companion", "app_debug"})
public final class SaveRouteActivity extends androidx.appcompat.app.AppCompatActivity {
    private com.example.stepbystep.databinding.ActivitySaveRouteBinding binding;
    @org.jetbrains.annotations.NotNull()
    private final kotlin.Lazy viewModel$delegate = null;
    private com.google.android.gms.maps.MapView mapView;
    @org.jetbrains.annotations.Nullable()
    private com.google.android.gms.maps.GoogleMap googleMap;
    @org.jetbrains.annotations.Nullable()
    private android.net.Uri selectedImageUri;
    
    /**
     * Contrato para solicitar permisos de almacenamiento.
     * Maneja el resultado de la solicitud y muestra diálogos
     * explicativos según corresponda.
     */
    @org.jetbrains.annotations.NotNull()
    private final androidx.activity.result.ActivityResultLauncher<java.lang.String> storagePermissionRequest = null;
    
    /**
     * Contrato para seleccionar imágenes de la galería.
     * Maneja el resultado de la selección y actualiza la UI.
     */
    @org.jetbrains.annotations.NotNull()
    private final androidx.activity.result.ActivityResultLauncher<java.lang.String> pickImage = null;
    private static final int STORAGE_PERMISSION_CODE = 100;
    private static final int PICK_IMAGE_REQUEST_CODE = 101;
    @org.jetbrains.annotations.NotNull()
    public static final com.example.stepbystep.ui.saveroute.SaveRouteActivity.Companion Companion = null;
    
    public SaveRouteActivity() {
        super();
    }
    
    private final com.example.stepbystep.ui.saveroute.SaveRouteViewModel getViewModel() {
        return null;
    }
    
    /**
     * Inicializa la actividad, configura las vistas y carga los datos
     * de la ruta desde el intent que la inició.
     */
    @java.lang.Override()
    protected void onCreate(@org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    /**
     * Configura el mapa con la visualización de la ruta.
     * Crea una línea que representa el recorrido y ajusta la cámara
     * para mostrar la ruta completa.
     *
     * @param points Lista de puntos geográficos que componen la ruta
     */
    private final void setupMap(java.util.List<com.google.android.gms.maps.model.LatLng> points) {
    }
    
    /**
     * Muestra un diálogo de confirmación antes de descartar la ruta.
     * Previene la pérdida accidental de datos.
     */
    private final void confirmDiscard() {
    }
    
    /**
     * Verifica y solicita el permiso adecuado para acceder a la galería,
     * según la versión de Android del dispositivo.
     */
    private final void checkAndRequestStoragePermission() {
    }
    
    /**
     * Abre el selector de imágenes del sistema.
     */
    private final void openImagePicker() {
    }
    
    /**
     * Guarda la imagen seleccionada en el almacenamiento interno de la aplicación
     * y actualiza el ViewModel con la ruta del archivo.
     */
    private final void saveImageToInternalStorage() {
    }
    
    /**
     * Maneja las acciones de los elementos del menú.
     */
    @java.lang.Override()
    public boolean onOptionsItemSelected(@org.jetbrains.annotations.NotNull()
    android.view.MenuItem item) {
        return false;
    }
    
    @java.lang.Override()
    protected void onStart() {
    }
    
    @java.lang.Override()
    protected void onResume() {
    }
    
    @java.lang.Override()
    protected void onPause() {
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
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0006"}, d2 = {"Lcom/example/stepbystep/ui/saveroute/SaveRouteActivity$Companion;", "", "()V", "PICK_IMAGE_REQUEST_CODE", "", "STORAGE_PERMISSION_CODE", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}