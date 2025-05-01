package com.example.stepbystep.ui.routedetail;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 +2\u00020\u0001:\u0001+B\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010\u000f\u001a\u00020\u0010H\u0002J\u0012\u0010\u0011\u001a\u00020\u00102\b\u0010\u0012\u001a\u0004\u0018\u00010\u0013H\u0014J\u0010\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0017H\u0016J\b\u0010\u0018\u001a\u00020\u0010H\u0014J\b\u0010\u0019\u001a\u00020\u0010H\u0016J\u0010\u0010\u001a\u001a\u00020\u00152\u0006\u0010\u001b\u001a\u00020\u001cH\u0016J\b\u0010\u001d\u001a\u00020\u0010H\u0014J\b\u0010\u001e\u001a\u00020\u0010H\u0014J\u0010\u0010\u001f\u001a\u00020\u00102\u0006\u0010 \u001a\u00020\u0013H\u0014J\b\u0010!\u001a\u00020\u0010H\u0014J\b\u0010\"\u001a\u00020\u0010H\u0014J\"\u0010#\u001a\u00020\u00102\u0018\u0010$\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\'\u0012\u0004\u0012\u00020\'0&0%H\u0002J\u0016\u0010(\u001a\u00020\u00102\f\u0010)\u001a\b\u0012\u0004\u0012\u00020*0%H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082.\u00a2\u0006\u0002\n\u0000R\u001b\u0010\t\u001a\u00020\n8BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\r\u0010\u000e\u001a\u0004\b\u000b\u0010\f\u00a8\u0006,"}, d2 = {"Lcom/example/stepbystep/ui/routedetail/RouteDetailActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "()V", "binding", "Lcom/example/stepbystep/databinding/ActivityRouteDetailBinding;", "googleMap", "Lcom/google/android/gms/maps/GoogleMap;", "mapView", "Lcom/google/android/gms/maps/MapView;", "viewModel", "Lcom/example/stepbystep/ui/routedetail/RouteDetailViewModel;", "getViewModel", "()Lcom/example/stepbystep/ui/routedetail/RouteDetailViewModel;", "viewModel$delegate", "Lkotlin/Lazy;", "exportGpx", "", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onCreateOptionsMenu", "", "menu", "Landroid/view/Menu;", "onDestroy", "onLowMemory", "onOptionsItemSelected", "item", "Landroid/view/MenuItem;", "onPause", "onResume", "onSaveInstanceState", "outState", "onStart", "onStop", "setupChart", "chartData", "", "Lkotlin/Pair;", "", "setupMap", "points", "Lcom/google/android/gms/maps/model/LatLng;", "Companion", "app_debug"})
public final class RouteDetailActivity extends androidx.appcompat.app.AppCompatActivity {
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String EXTRA_ROUTE_ID = "extra_route_id";
    private com.example.stepbystep.databinding.ActivityRouteDetailBinding binding;
    @org.jetbrains.annotations.NotNull()
    private final kotlin.Lazy viewModel$delegate = null;
    private com.google.android.gms.maps.MapView mapView;
    @org.jetbrains.annotations.Nullable()
    private com.google.android.gms.maps.GoogleMap googleMap;
    @org.jetbrains.annotations.NotNull()
    public static final com.example.stepbystep.ui.routedetail.RouteDetailActivity.Companion Companion = null;
    
    public RouteDetailActivity() {
        super();
    }
    
    private final com.example.stepbystep.ui.routedetail.RouteDetailViewModel getViewModel() {
        return null;
    }
    
    @java.lang.Override()
    protected void onCreate(@org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    @java.lang.Override()
    public boolean onCreateOptionsMenu(@org.jetbrains.annotations.NotNull()
    android.view.Menu menu) {
        return false;
    }
    
    private final void setupMap(java.util.List<com.google.android.gms.maps.model.LatLng> points) {
    }
    
    private final void setupChart(java.util.List<kotlin.Pair<java.lang.Float, java.lang.Float>> chartData) {
    }
    
    @java.lang.Override()
    public boolean onOptionsItemSelected(@org.jetbrains.annotations.NotNull()
    android.view.MenuItem item) {
        return false;
    }
    
    private final void exportGpx() {
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
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0005"}, d2 = {"Lcom/example/stepbystep/ui/routedetail/RouteDetailActivity$Companion;", "", "()V", "EXTRA_ROUTE_ID", "", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}