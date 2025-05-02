package com.example.stepbystep.ui.profile;

/**
 * ViewModel para la pantalla de perfil del usuario.
 *
 * Gestiona:
 * 1. Información personal del atleta (nombre, descripción)
 * 2. Estado de modo edición/visualización
 * 3. Cálculo y formateo de estadísticas globales de todas las actividades
 *
 * @param context Contexto necesario para acceder a las preferencias y base de datos
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000^\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0019\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0006\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0016\u0010=\u001a\u00020>2\f\u0010?\u001a\b\u0012\u0004\u0012\u00020A0@H\u0002J\u0010\u0010B\u001a\u00020\u00072\u0006\u0010C\u001a\u00020DH\u0002J\b\u0010E\u001a\u00020>H\u0002J\u0006\u0010F\u001a\u00020>J\u0016\u0010G\u001a\u00020>2\u0006\u0010H\u001a\u00020\u00072\u0006\u0010I\u001a\u00020\u0007R\u001c\u0010\u0005\u001a\u0010\u0012\f\u0012\n \b*\u0004\u0018\u00010\u00070\u00070\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001c\u0010\t\u001a\u0010\u0012\f\u0012\n \b*\u0004\u0018\u00010\u00070\u00070\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\f\u001a\b\u0012\u0004\u0012\u00020\r0\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\r0\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00110\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001c\u0010\u0013\u001a\u0010\u0012\f\u0012\n \b*\u0004\u0018\u00010\r0\r0\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00070\u0018\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001aR\u0017\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u00070\u0018\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u001aR\u0017\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u00070\u0018\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u001aR\u0017\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020\u00070\u0018\u00a2\u0006\b\n\u0000\u001a\u0004\b \u0010\u001aR\u0017\u0010!\u001a\b\u0012\u0004\u0012\u00020\r0\u0018\u00a2\u0006\b\n\u0000\u001a\u0004\b\"\u0010\u001aR\u0017\u0010#\u001a\b\u0012\u0004\u0012\u00020\r0\u0018\u00a2\u0006\b\n\u0000\u001a\u0004\b$\u0010\u001aR\u001f\u0010%\u001a\u0010\u0012\f\u0012\n \b*\u0004\u0018\u00010\r0\r0\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b&\u0010\'R\u0017\u0010(\u001a\b\u0012\u0004\u0012\u00020\u00070\u0018\u00a2\u0006\b\n\u0000\u001a\u0004\b)\u0010\u001aR\u0017\u0010*\u001a\b\u0012\u0004\u0012\u00020\u00070\u0018\u00a2\u0006\b\n\u0000\u001a\u0004\b+\u0010\u001aR\u0017\u0010,\u001a\b\u0012\u0004\u0012\u00020\u00070\u0018\u00a2\u0006\b\n\u0000\u001a\u0004\b-\u0010\u001aR\u0017\u0010.\u001a\b\u0012\u0004\u0012\u00020\u00110\u0018\u00a2\u0006\b\n\u0000\u001a\u0004\b.\u0010\u001aR\u0017\u0010/\u001a\b\u0012\u0004\u0012\u00020\u00070\u0018\u00a2\u0006\b\n\u0000\u001a\u0004\b0\u0010\u001aR\u000e\u00101\u001a\u000202X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u00103\u001a\b\u0012\u0004\u0012\u00020\r0\u0018\u00a2\u0006\b\n\u0000\u001a\u0004\b4\u0010\u001aR\u0017\u00105\u001a\b\u0012\u0004\u0012\u00020\u00070\u0018\u00a2\u0006\b\n\u0000\u001a\u0004\b6\u0010\u001aR\u0017\u00107\u001a\b\u0012\u0004\u0012\u00020\u00070\u0018\u00a2\u0006\b\n\u0000\u001a\u0004\b8\u0010\u001aR\u0017\u00109\u001a\b\u0012\u0004\u0012\u00020\u00070\u0018\u00a2\u0006\b\n\u0000\u001a\u0004\b:\u0010\u001aR\u000e\u0010;\u001a\u00020<X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006J"}, d2 = {"Lcom/example/stepbystep/ui/profile/ProfileViewModel;", "Landroidx/lifecycle/ViewModel;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "_athleteDescription", "Landroidx/lifecycle/MutableLiveData;", "", "kotlin.jvm.PlatformType", "_athleteName", "_averageDistance", "_averagePace", "_editButtonIcon", "", "_editButtonText", "_firstActivityDate", "_isEditMode", "", "_maxDistance", "_totalActivities", "_totalDistance", "_totalDuration", "_totalElevationGain", "athleteDescription", "Landroidx/lifecycle/LiveData;", "getAthleteDescription", "()Landroidx/lifecycle/LiveData;", "athleteName", "getAthleteName", "averageDistance", "getAverageDistance", "averagePace", "getAveragePace", "editButtonIcon", "getEditButtonIcon", "editButtonText", "getEditButtonText", "editFieldBackground", "getEditFieldBackground", "()Landroidx/lifecycle/MutableLiveData;", "firstActivityDate", "getFirstActivityDate", "formattedActiveDate", "getFormattedActiveDate", "formattedTotalActivities", "getFormattedTotalActivities", "isEditMode", "maxDistance", "getMaxDistance", "repository", "Lcom/example/stepbystep/data/repository/RouteRepository;", "totalActivities", "getTotalActivities", "totalDistance", "getTotalDistance", "totalDuration", "getTotalDuration", "totalElevationGain", "getTotalElevationGain", "userPrefs", "Lcom/example/stepbystep/data/local/UserPreferences;", "calculateStatistics", "", "routes", "", "Lcom/example/stepbystep/domain/model/Route;", "formatLongDuration", "durationMs", "", "setEmptyStatistics", "toggleEditMode", "updateProfile", "name", "description", "app_debug"})
public final class ProfileViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull()
    private final com.example.stepbystep.data.repository.RouteRepository repository = null;
    @org.jetbrains.annotations.NotNull()
    private final com.example.stepbystep.data.local.UserPreferences userPrefs = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.MutableLiveData<java.lang.String> _athleteName = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<java.lang.String> athleteName = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.MutableLiveData<java.lang.String> _athleteDescription = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<java.lang.String> athleteDescription = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.MutableLiveData<java.lang.Boolean> _isEditMode = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<java.lang.Boolean> isEditMode = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.MutableLiveData<java.lang.Integer> editFieldBackground = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.MutableLiveData<java.lang.Integer> _editButtonText = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<java.lang.Integer> editButtonText = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.MutableLiveData<java.lang.Integer> _editButtonIcon = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<java.lang.Integer> editButtonIcon = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.MutableLiveData<java.lang.Integer> _totalActivities = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<java.lang.Integer> totalActivities = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.MutableLiveData<java.lang.String> _firstActivityDate = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<java.lang.String> firstActivityDate = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.MutableLiveData<java.lang.String> _totalDistance = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<java.lang.String> totalDistance = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.MutableLiveData<java.lang.String> _averageDistance = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<java.lang.String> averageDistance = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.MutableLiveData<java.lang.String> _maxDistance = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<java.lang.String> maxDistance = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.MutableLiveData<java.lang.String> _totalDuration = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<java.lang.String> totalDuration = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.MutableLiveData<java.lang.String> _averagePace = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<java.lang.String> averagePace = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.MutableLiveData<java.lang.String> _totalElevationGain = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<java.lang.String> totalElevationGain = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<java.lang.String> formattedTotalActivities = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<java.lang.String> formattedActiveDate = null;
    
    public ProfileViewModel(@org.jetbrains.annotations.NotNull()
    android.content.Context context) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.lang.String> getAthleteName() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.lang.String> getAthleteDescription() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.lang.Boolean> isEditMode() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.MutableLiveData<java.lang.Integer> getEditFieldBackground() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.lang.Integer> getEditButtonText() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.lang.Integer> getEditButtonIcon() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.lang.Integer> getTotalActivities() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.lang.String> getFirstActivityDate() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.lang.String> getTotalDistance() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.lang.String> getAverageDistance() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.lang.String> getMaxDistance() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.lang.String> getTotalDuration() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.lang.String> getAveragePace() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.lang.String> getTotalElevationGain() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.lang.String> getFormattedTotalActivities() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.lang.String> getFormattedActiveDate() {
        return null;
    }
    
    /**
     * Alterna entre modo edición y modo visualización
     */
    public final void toggleEditMode() {
    }
    
    /**
     * Actualiza el perfil del usuario con nuevos datos y guarda
     * los cambios en las preferencias
     *
     * @param name Nuevo nombre del atleta
     * @param description Nueva descripción o biografía
     */
    public final void updateProfile(@org.jetbrains.annotations.NotNull()
    java.lang.String name, @org.jetbrains.annotations.NotNull()
    java.lang.String description) {
    }
    
    /**
     * Calcula las estadísticas globales a partir de todas las rutas.
     * Actualiza los LiveData para que la UI se refresque automáticamente.
     *
     * @param routes Lista de rutas sobre las que calcular estadísticas
     */
    private final void calculateStatistics(java.util.List<com.example.stepbystep.domain.model.Route> routes) {
    }
    
    /**
     * Establece valores por defecto para las estadísticas cuando no hay rutas
     */
    private final void setEmptyStatistics() {
    }
    
    /**
     * Formatea una duración larga en formato horas, minutos y segundos
     *
     * @param durationMs Duración en milisegundos
     * @return Cadena formateada (ej: "2h 30m 45s")
     */
    private final java.lang.String formatLongDuration(long durationMs) {
        return null;
    }
}