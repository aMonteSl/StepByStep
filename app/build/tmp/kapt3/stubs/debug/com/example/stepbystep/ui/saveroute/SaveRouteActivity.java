package com.example.stepbystep.ui.saveroute;

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
    @org.jetbrains.annotations.NotNull()
    private final androidx.activity.result.ActivityResultLauncher<java.lang.String> storagePermissionRequest = null;
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
    
    @java.lang.Override()
    protected void onCreate(@org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    private final void setupMap(java.util.List<com.google.android.gms.maps.model.LatLng> points) {
    }
    
    private final void confirmDiscard() {
    }
    
    private final void checkAndRequestStoragePermission() {
    }
    
    private final void openImagePicker() {
    }
    
    private final void saveImageToInternalStorage() {
    }
    
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