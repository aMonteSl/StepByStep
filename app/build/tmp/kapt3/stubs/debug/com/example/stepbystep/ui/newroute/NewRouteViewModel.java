package com.example.stepbystep.ui.newroute;

/**
 * ViewModel para la actividad NewRouteActivity.
 *
 * Responsable de:
 * - Mantener el estado de la grabación de la ruta
 * - Calcular estadísticas como distancia, tiempo y elevación
 * - Gestionar la ruta de referencia
 * - Mantener un registro de puntos recopilados
 * - Procesar eventos del servicio de localización
 *
 * Implementa un patrón observable para que la UI pueda reaccionar 
 * a cambios en estos datos.
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000t\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0006\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0019\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0010\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0012\u0018\u00002\u00020\u0001:\u0001lB\u0005\u00a2\u0006\u0002\u0010\u0002J\u0010\u0010N\u001a\u00020O2\u0006\u0010P\u001a\u00020\u0015H\u0002J\u0006\u0010Q\u001a\u00020OJ\u0006\u0010R\u001a\u00020OJ\u000e\u0010S\u001a\u00020O2\u0006\u0010T\u001a\u00020\u000bJ\u0010\u0010U\u001a\u00020O2\u0006\u0010V\u001a\u00020WH\u0002J\u000e\u0010X\u001a\u00020O2\u0006\u0010V\u001a\u00020WJ\u0006\u0010Y\u001a\u00020OJ\u0006\u0010Z\u001a\u00020[J4\u0010\\\u001a\u00020O2\u0006\u0010]\u001a\u00020\u000b2\u0006\u0010^\u001a\u00020\u000e2\u0006\u0010T\u001a\u00020\u000b2\u0006\u0010%\u001a\u00020\u000b2\f\u0010E\u001a\b\u0012\u0004\u0012\u00020\u00150\u0014J\u0006\u0010_\u001a\u00020OJ\u001e\u0010`\u001a\u00020O2\f\u0010:\u001a\b\u0012\u0004\u0012\u00020\u00150\u00142\b\b\u0002\u0010a\u001a\u00020\u0004J\u0010\u0010b\u001a\u00020O2\b\b\u0002\u0010c\u001a\u00020\u0007J\b\u0010d\u001a\u00020OH\u0002J\u0006\u0010e\u001a\u00020OJ\u0006\u0010f\u001a\u00020OJ\u0006\u0010g\u001a\u00020OJ\u0006\u0010h\u001a\u00020OJ\u0006\u0010i\u001a\u00020OJ\u0006\u0010j\u001a\u00020OJ0\u0010k\u001a\u00020O2\b\u0010V\u001a\u0004\u0018\u00010W2\u0006\u0010]\u001a\u00020\u000b2\u0006\u0010^\u001a\u00020\u000e2\u0006\u0010T\u001a\u00020\u000b2\u0006\u0010%\u001a\u00020\u000bR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D\u00a2\u0006\u0002\n\u0000R\u001c\u0010\u0005\u001a\u0010\u0012\f\u0012\n \b*\u0004\u0018\u00010\u00070\u00070\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001c\u0010\t\u001a\u0010\u0012\f\u0012\n \b*\u0004\u0018\u00010\u00070\u00070\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001c\u0010\n\u001a\u0010\u0012\f\u0012\n \b*\u0004\u0018\u00010\u000b0\u000b0\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001c\u0010\f\u001a\u0010\u0012\f\u0012\n \b*\u0004\u0018\u00010\u000b0\u000b0\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001c\u0010\r\u001a\u0010\u0012\f\u0012\n \b*\u0004\u0018\u00010\u000e0\u000e0\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001c\u0010\u000f\u001a\u0010\u0012\f\u0012\n \b*\u0004\u0018\u00010\u000b0\u000b0\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001c\u0010\u0010\u001a\u0010\u0012\f\u0012\n \b*\u0004\u0018\u00010\u00070\u00070\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001c\u0010\u0011\u001a\u0010\u0012\f\u0012\n \b*\u0004\u0018\u00010\u00070\u00070\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u0013\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00150\u00140\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00040\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u0017\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00150\u00140\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001c\u0010\u0018\u001a\u0010\u0012\f\u0012\n \b*\u0004\u0018\u00010\u00070\u00070\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u00070\u001a\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u001cR\u0017\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u00070\u001a\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u001cR\u0017\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020\u000b0\u001a\u00a2\u0006\b\n\u0000\u001a\u0004\b \u0010\u001cR\u0017\u0010!\u001a\b\u0012\u0004\u0012\u00020\u000b0\u001a\u00a2\u0006\b\n\u0000\u001a\u0004\b\"\u0010\u001cR\u0017\u0010#\u001a\b\u0012\u0004\u0012\u00020\u000e0\u001a\u00a2\u0006\b\n\u0000\u001a\u0004\b$\u0010\u001cR\u0017\u0010%\u001a\b\u0012\u0004\u0012\u00020\u000b0\u001a\u00a2\u0006\b\n\u0000\u001a\u0004\b&\u0010\u001cR\u0017\u0010\'\u001a\b\u0012\u0004\u0012\u00020\u00040\u001a\u00a2\u0006\b\n\u0000\u001a\u0004\b(\u0010\u001cR\u0017\u0010)\u001a\b\u0012\u0004\u0012\u00020\u00040\u001a\u00a2\u0006\b\n\u0000\u001a\u0004\b*\u0010\u001cR\u0017\u0010+\u001a\b\u0012\u0004\u0012\u00020\u00040\u001a\u00a2\u0006\b\n\u0000\u001a\u0004\b,\u0010\u001cR\u0017\u0010-\u001a\b\u0012\u0004\u0012\u00020\u00040\u001a\u00a2\u0006\b\n\u0000\u001a\u0004\b.\u0010\u001cR\u0017\u0010/\u001a\b\u0012\u0004\u0012\u00020\u00070\u001a\u00a2\u0006\b\n\u0000\u001a\u0004\b/\u0010\u001cR\u0017\u00100\u001a\b\u0012\u0004\u0012\u00020\u00070\u001a\u00a2\u0006\b\n\u0000\u001a\u0004\b0\u0010\u001cR\u0017\u00101\u001a\b\u0012\u0004\u0012\u00020\u00070\u001a\u00a2\u0006\b\n\u0000\u001a\u0004\b1\u0010\u001cR\u000e\u00102\u001a\u00020\u000bX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0017\u00103\u001a\b\u0012\u0004\u0012\u0002040\u001a\u00a2\u0006\b\n\u0000\u001a\u0004\b5\u0010\u001cR\u0017\u00106\u001a\b\u0012\u0004\u0012\u0002040\u001a\u00a2\u0006\b\n\u0000\u001a\u0004\b7\u0010\u001cR\u000e\u00108\u001a\u00020\u000eX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u00109\u001a\u00020\u000eX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0017\u0010:\u001a\b\u0012\u0004\u0012\u00020<0;\u00a2\u0006\b\n\u0000\u001a\u0004\b=\u0010>R\u001d\u0010?\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00150\u00140\u001a\u00a2\u0006\b\n\u0000\u001a\u0004\b@\u0010\u001cR\u0017\u0010A\u001a\b\u0012\u0004\u0012\u00020\u00040\u001a\u00a2\u0006\b\n\u0000\u001a\u0004\bB\u0010\u001cR\u0017\u0010C\u001a\b\u0012\u0004\u0012\u0002040\u001a\u00a2\u0006\b\n\u0000\u001a\u0004\bD\u0010\u001cR\u001d\u0010E\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00150\u00140\u001a\u00a2\u0006\b\n\u0000\u001a\u0004\bF\u0010\u001cR\u0017\u0010G\u001a\b\u0012\u0004\u0012\u0002040\u001a\u00a2\u0006\b\n\u0000\u001a\u0004\bH\u0010\u001cR\u000e\u0010I\u001a\u00020\u000eX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0017\u0010J\u001a\b\u0012\u0004\u0012\u00020\u00070\u001a\u00a2\u0006\b\n\u0000\u001a\u0004\bK\u0010\u001cR\u0010\u0010L\u001a\u0004\u0018\u00010MX\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006m"}, d2 = {"Lcom/example/stepbystep/ui/newroute/NewRouteViewModel;", "Landroidx/lifecycle/ViewModel;", "()V", "TAG", "", "_autoTrackLocation", "Landroidx/lifecycle/MutableLiveData;", "", "kotlin.jvm.PlatformType", "_buttonsVisible", "_currentDistance", "", "_currentElevation", "_elapsedTimeMs", "", "_elevationGain", "_isPaused", "_isRecording", "_isReferenceRouteVisible", "_referenceRoute", "", "Lcom/google/android/gms/maps/model/LatLng;", "_referenceRouteName", "_routePoints", "_statsVisible", "autoTrackLocation", "Landroidx/lifecycle/LiveData;", "getAutoTrackLocation", "()Landroidx/lifecycle/LiveData;", "buttonsVisible", "getButtonsVisible", "currentDistance", "getCurrentDistance", "currentElevation", "getCurrentElevation", "elapsedTimeMs", "getElapsedTimeMs", "elevationGain", "getElevationGain", "formattedDistance", "getFormattedDistance", "formattedElevation", "getFormattedElevation", "formattedElevationGain", "getFormattedElevationGain", "formattedTime", "getFormattedTime", "isPaused", "isRecording", "isReferenceRouteVisible", "lastElevation", "pauseResumeButtonText", "", "getPauseResumeButtonText", "pauseResumeButtonVisible", "getPauseResumeButtonVisible", "pauseStart", "pausedAccumulated", "points", "", "Lcom/example/stepbystep/ui/newroute/NewRouteViewModel$LocationPoint;", "getPoints", "()Ljava/util/List;", "referenceRoute", "getReferenceRoute", "referenceRouteName", "getReferenceRouteName", "referenceRouteVisible", "getReferenceRouteVisible", "routePoints", "getRoutePoints", "startStopButtonText", "getStartStopButtonText", "startTime", "statsVisible", "getStatsVisible", "timerJob", "Lkotlinx/coroutines/Job;", "addRoutePoint", "", "point", "clearReferenceRoute", "clearRoutePoints", "forceElevationUpdate", "elevation", "logLocationUpdate", "location", "Landroid/location/Location;", "onLocationUpdated", "pauseRecording", "prepareRouteDataForSave", "Landroid/os/Bundle;", "restoreTrackingState", "distance", "timeMs", "resumeRecording", "setReferenceRoute", "name", "startRecording", "forceReset", "startTimer", "stopRecording", "toggleAllVisibility", "toggleAutoTrackLocation", "toggleButtonsVisibility", "toggleReferenceRouteVisibility", "toggleStatsVisibility", "updateTracking", "LocationPoint", "app_debug"})
public final class NewRouteViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String TAG = "RouteTrackingVM";
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.MutableLiveData<java.lang.Boolean> _isRecording = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<java.lang.Boolean> isRecording = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.MutableLiveData<java.lang.Boolean> _isPaused = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<java.lang.Boolean> isPaused = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.MutableLiveData<java.lang.Double> _currentDistance = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<java.lang.Double> currentDistance = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<java.lang.String> formattedDistance = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.MutableLiveData<java.lang.Long> _elapsedTimeMs = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<java.lang.Long> elapsedTimeMs = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<java.lang.String> formattedTime = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.MutableLiveData<java.lang.Double> _currentElevation = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<java.lang.Double> currentElevation = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<java.lang.String> formattedElevation = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.MutableLiveData<java.lang.Double> _elevationGain = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<java.lang.Double> elevationGain = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<java.lang.String> formattedElevationGain = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.MutableLiveData<java.util.List<com.google.android.gms.maps.model.LatLng>> _referenceRoute = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<java.util.List<com.google.android.gms.maps.model.LatLng>> referenceRoute = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.MutableLiveData<java.lang.String> _referenceRouteName = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<java.lang.String> referenceRouteName = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.MutableLiveData<java.lang.Boolean> _isReferenceRouteVisible = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<java.lang.Boolean> isReferenceRouteVisible = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.MutableLiveData<java.lang.Boolean> _statsVisible = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<java.lang.Boolean> statsVisible = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.MutableLiveData<java.lang.Boolean> _buttonsVisible = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<java.lang.Boolean> buttonsVisible = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.MutableLiveData<java.lang.Boolean> _autoTrackLocation = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<java.lang.Boolean> autoTrackLocation = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.MutableLiveData<java.util.List<com.google.android.gms.maps.model.LatLng>> _routePoints = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<java.util.List<com.google.android.gms.maps.model.LatLng>> routePoints = null;
    @org.jetbrains.annotations.NotNull()
    private final java.util.List<com.example.stepbystep.ui.newroute.NewRouteViewModel.LocationPoint> points = null;
    private double lastElevation = 0.0;
    @org.jetbrains.annotations.Nullable()
    private kotlinx.coroutines.Job timerJob;
    private long startTime = 0L;
    private long pausedAccumulated = 0L;
    private long pauseStart = 0L;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<java.lang.Integer> referenceRouteVisible = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<java.lang.Integer> startStopButtonText = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<java.lang.Integer> pauseResumeButtonText = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<java.lang.Integer> pauseResumeButtonVisible = null;
    
    public NewRouteViewModel() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.lang.Boolean> isRecording() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.lang.Boolean> isPaused() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.lang.Double> getCurrentDistance() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.lang.String> getFormattedDistance() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.lang.Long> getElapsedTimeMs() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.lang.String> getFormattedTime() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.lang.Double> getCurrentElevation() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.lang.String> getFormattedElevation() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.lang.Double> getElevationGain() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.lang.String> getFormattedElevationGain() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.util.List<com.google.android.gms.maps.model.LatLng>> getReferenceRoute() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.lang.String> getReferenceRouteName() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.lang.Boolean> isReferenceRouteVisible() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.lang.Boolean> getStatsVisible() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.lang.Boolean> getButtonsVisible() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.lang.Boolean> getAutoTrackLocation() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.util.List<com.google.android.gms.maps.model.LatLng>> getRoutePoints() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.example.stepbystep.ui.newroute.NewRouteViewModel.LocationPoint> getPoints() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.lang.Integer> getReferenceRouteVisible() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.lang.Integer> getStartStopButtonText() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.lang.Integer> getPauseResumeButtonText() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.lang.Integer> getPauseResumeButtonVisible() {
        return null;
    }
    
    /**
     * Restaura completamente el estado de tracking con datos del servicio
     */
    public final void restoreTrackingState(double distance, long timeMs, double elevation, double elevationGain, @org.jetbrains.annotations.NotNull()
    java.util.List<com.google.android.gms.maps.model.LatLng> routePoints) {
    }
    
    /**
     * Inicia la grabación de una nueva ruta.
     * Reinicia todas las métricas y comienza el temporizador.
     */
    public final void startRecording(boolean forceReset) {
    }
    
    /**
     * Pausa la grabación de la ruta.
     */
    public final void pauseRecording() {
    }
    
    /**
     * Reanuda la grabación de la ruta después de una pausa.
     */
    public final void resumeRecording() {
    }
    
    /**
     * Detiene la grabación de la ruta.
     */
    public final void stopRecording() {
    }
    
    /**
     * Actualiza los valores de tracking con los datos recibidos del servicio.
     */
    public final void updateTracking(@org.jetbrains.annotations.Nullable()
    android.location.Location location, double distance, long timeMs, double elevation, double elevationGain) {
    }
    
    /**
     * MÉTODO POTENCIALMENTE PROBLEMÁTICO
     */
    public final void onLocationUpdated(@org.jetbrains.annotations.NotNull()
    android.location.Location location) {
    }
    
    /**
     * Agrega un punto a la ruta y notifica a los observadores
     */
    private final void addRoutePoint(com.google.android.gms.maps.model.LatLng point) {
    }
    
    /**
     * Limpia todos los puntos de la ruta
     */
    public final void clearRoutePoints() {
    }
    
    /**
     * Establece la ruta de referencia a partir de una lista de puntos
     *
     * @param points Lista de puntos LatLng que forman la ruta
     * @param name Nombre de la ruta de referencia
     */
    public final void setReferenceRoute(@org.jetbrains.annotations.NotNull()
    java.util.List<com.google.android.gms.maps.model.LatLng> points, @org.jetbrains.annotations.NotNull()
    java.lang.String name) {
    }
    
    /**
     * Alterna la visibilidad de la ruta de referencia en el mapa
     */
    public final void toggleReferenceRouteVisibility() {
    }
    
    /**
     * Elimina la ruta de referencia
     */
    public final void clearReferenceRoute() {
    }
    
    /**
     * Alterna el modo de seguimiento automático de ubicación
     */
    public final void toggleAutoTrackLocation() {
    }
    
    /**
     * Alterna la visibilidad del panel de estadísticas
     */
    public final void toggleStatsVisibility() {
    }
    
    /**
     * Alterna la visibilidad de los botones de control
     */
    public final void toggleButtonsVisibility() {
    }
    
    /**
     * Alterna la visibilidad de todos los elementos UI
     */
    public final void toggleAllVisibility() {
    }
    
    /**
     * Prepara los datos para la pantalla de guardado de ruta
     * @return Bundle con todos los datos necesarios
     */
    @org.jetbrains.annotations.NotNull()
    public final android.os.Bundle prepareRouteDataForSave() {
        return null;
    }
    
    /**
     * Fuerza la actualización del valor de elevación
     */
    public final void forceElevationUpdate(double elevation) {
    }
    
    /**
     * Registra información de depuración sobre la ubicación actualizada
     */
    private final void logLocationUpdate(android.location.Location location) {
    }
    
    /**
     * Inicia el temporizador para actualizar el tiempo transcurrido
     */
    private final void startTimer() {
    }
    
    /**
     * Clase para almacenar un punto de ubicación con su elevación
     */
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0006\n\u0000\n\u0002\u0010\t\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\u0002\u0010\bJ\t\u0010\u000f\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0010\u001a\u00020\u0005H\u00c6\u0003J\t\u0010\u0011\u001a\u00020\u0007H\u00c6\u0003J\'\u0010\u0012\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0007H\u00c6\u0001J\u0013\u0010\u0013\u001a\u00020\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\u0016\u001a\u00020\u0017H\u00d6\u0001J\t\u0010\u0018\u001a\u00020\u0019H\u00d6\u0001R\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000e\u00a8\u0006\u001a"}, d2 = {"Lcom/example/stepbystep/ui/newroute/NewRouteViewModel$LocationPoint;", "", "latLng", "Lcom/google/android/gms/maps/model/LatLng;", "altitude", "", "timestamp", "", "(Lcom/google/android/gms/maps/model/LatLng;DJ)V", "getAltitude", "()D", "getLatLng", "()Lcom/google/android/gms/maps/model/LatLng;", "getTimestamp", "()J", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "", "toString", "", "app_debug"})
    public static final class LocationPoint {
        @org.jetbrains.annotations.NotNull()
        private final com.google.android.gms.maps.model.LatLng latLng = null;
        private final double altitude = 0.0;
        private final long timestamp = 0L;
        
        public LocationPoint(@org.jetbrains.annotations.NotNull()
        com.google.android.gms.maps.model.LatLng latLng, double altitude, long timestamp) {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final com.google.android.gms.maps.model.LatLng getLatLng() {
            return null;
        }
        
        public final double getAltitude() {
            return 0.0;
        }
        
        public final long getTimestamp() {
            return 0L;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final com.google.android.gms.maps.model.LatLng component1() {
            return null;
        }
        
        public final double component2() {
            return 0.0;
        }
        
        public final long component3() {
            return 0L;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final com.example.stepbystep.ui.newroute.NewRouteViewModel.LocationPoint copy(@org.jetbrains.annotations.NotNull()
        com.google.android.gms.maps.model.LatLng latLng, double altitude, long timestamp) {
            return null;
        }
        
        @java.lang.Override()
        public boolean equals(@org.jetbrains.annotations.Nullable()
        java.lang.Object other) {
            return false;
        }
        
        @java.lang.Override()
        public int hashCode() {
            return 0;
        }
        
        @java.lang.Override()
        @org.jetbrains.annotations.NotNull()
        public java.lang.String toString() {
            return null;
        }
    }
}