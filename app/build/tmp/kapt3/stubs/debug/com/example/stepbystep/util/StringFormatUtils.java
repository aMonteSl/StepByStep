package com.example.stepbystep.util;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0006\n\u0002\u0010\u0007\n\u0002\b\u0004\n\u0002\u0010\t\n\u0002\b\u0005\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0007J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0007J\u0010\u0010\b\u001a\u00020\u00042\u0006\u0010\t\u001a\u00020\u0006H\u0007J\u0010\u0010\n\u001a\u00020\u00042\u0006\u0010\u000b\u001a\u00020\fH\u0007J\u0010\u0010\r\u001a\u00020\u00042\u0006\u0010\u000e\u001a\u00020\u0006H\u0007J\u000e\u0010\r\u001a\u00020\u00042\u0006\u0010\u000e\u001a\u00020\u0007J\u0010\u0010\u000f\u001a\u00020\u00042\u0006\u0010\u0010\u001a\u00020\u0006H\u0007J\u000e\u0010\u000f\u001a\u00020\u00042\u0006\u0010\u0010\u001a\u00020\u0007\u00a8\u0006\u0011"}, d2 = {"Lcom/example/stepbystep/util/StringFormatUtils;", "", "()V", "formatDistance", "", "distanceInMeters", "", "", "formatDistanceKm", "distanceKm", "formatDuration", "timeMs", "", "formatElevation", "elevationMeters", "formatElevationGain", "elevationGainMeters", "app_debug"})
public final class StringFormatUtils {
    @org.jetbrains.annotations.NotNull()
    public static final com.example.stepbystep.util.StringFormatUtils INSTANCE = null;
    
    private StringFormatUtils() {
        super();
    }
    
    /**
     * Format distance values in meters to appropriate units
     * - Shows as "Xm" for distances under 1km
     * - Shows as "X.X km" for distances 1km or more
     */
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String formatDistance(float distanceInMeters) {
        return null;
    }
    
    /**
     * Overloaded version for Double type distance values in meters
     */
    @kotlin.jvm.JvmStatic()
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String formatDistance(double distanceInMeters) {
        return null;
    }
    
    /**
     * Format distance values that are already in kilometers
     * Mostrar metros con precisión decimal para distancias muy cortas
     * Mostrar kilometraje con 2 decimales para mayor precisión
     */
    @kotlin.jvm.JvmStatic()
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String formatDistanceKm(double distanceKm) {
        return null;
    }
    
    /**
     * Format time in milliseconds to a human-readable duration
     * - Shows "Xh" for whole hours
     * - Shows "Xh Ym" for hours with minutes
     * - Shows "Xm Ys" for times under 1 hour
     */
    @kotlin.jvm.JvmStatic()
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String formatDuration(long timeMs) {
        return null;
    }
    
    /**
     * Format elevation in meters with appropriate unit
     */
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String formatElevation(float elevationMeters) {
        return null;
    }
    
    /**
     * Overloaded version for Double type elevation values
     */
    @kotlin.jvm.JvmStatic()
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String formatElevation(double elevationMeters) {
        return null;
    }
    
    /**
     * Format elevation gain with plus sign to indicate gain
     */
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String formatElevationGain(float elevationGainMeters) {
        return null;
    }
    
    /**
     * Overloaded version for Double type elevation gain values
     */
    @kotlin.jvm.JvmStatic()
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String formatElevationGain(double elevationGainMeters) {
        return null;
    }
}