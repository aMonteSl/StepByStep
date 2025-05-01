package com.example.stepbystep.util;

/**
 * Utilidad para formatear valores numéricos como distancias, tiempos y elevaciones.
 *
 * Provee métodos para convertir valores numéricos a cadenas de texto formateadas
 * con la unidad apropiada y nivel de precisión según el contexto de uso.
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0006\n\u0002\u0010\u0007\n\u0002\b\u0004\n\u0002\u0010\t\n\u0002\b\u0005\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0007J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0007H\u0007J\u0010\u0010\b\u001a\u00020\u00042\u0006\u0010\t\u001a\u00020\u0006H\u0007J\u0010\u0010\n\u001a\u00020\u00042\u0006\u0010\u000b\u001a\u00020\fH\u0007J\u0010\u0010\r\u001a\u00020\u00042\u0006\u0010\u000e\u001a\u00020\u0006H\u0007J\u0010\u0010\r\u001a\u00020\u00042\u0006\u0010\u000e\u001a\u00020\u0007H\u0007J\u0010\u0010\u000f\u001a\u00020\u00042\u0006\u0010\u0010\u001a\u00020\u0006H\u0007J\u0010\u0010\u000f\u001a\u00020\u00042\u0006\u0010\u0010\u001a\u00020\u0007H\u0007\u00a8\u0006\u0011"}, d2 = {"Lcom/example/stepbystep/util/StringFormatUtils;", "", "()V", "formatDistance", "", "distanceInMeters", "", "", "formatDistanceKm", "distanceKm", "formatDuration", "timeMs", "", "formatElevation", "elevationMeters", "formatElevationGain", "elevationGainMeters", "app_debug"})
public final class StringFormatUtils {
    @org.jetbrains.annotations.NotNull()
    public static final com.example.stepbystep.util.StringFormatUtils INSTANCE = null;
    
    private StringFormatUtils() {
        super();
    }
    
    /**
     * Formatea valores de distancia en metros a las unidades apropiadas
     * - Muestra "Xm" para distancias menores a 1km
     * - Muestra "X.X km" para distancias de 1km o más
     *
     * @param distanceInMeters Distancia en metros
     * @return Cadena formateada con la unidad apropiada
     */
    @kotlin.jvm.JvmStatic()
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String formatDistance(float distanceInMeters) {
        return null;
    }
    
    /**
     * Versión sobrecargada para valores de distancia en metros de tipo Double
     *
     * @param distanceInMeters Distancia en metros
     * @return Cadena formateada con la unidad apropiada
     */
    @kotlin.jvm.JvmStatic()
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String formatDistance(double distanceInMeters) {
        return null;
    }
    
    /**
     * Formatea valores de distancia que ya están en kilómetros
     * - Muestra metros con precisión decimal para distancias muy cortas
     * - Muestra kilómetros con 2 decimales para mayor precisión
     *
     * @param distanceKm Distancia en kilómetros
     * @return Cadena formateada con la unidad apropiada
     */
    @kotlin.jvm.JvmStatic()
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String formatDistanceKm(double distanceKm) {
        return null;
    }
    
    /**
     * Formatea tiempo en milisegundos a una duración legible para humanos
     * - Muestra "Xh" para horas completas
     * - Muestra "Xh Ym" para horas con minutos
     * - Muestra "Xm Ys" para tiempos menores a 1 hora
     *
     * @param timeMs Tiempo en milisegundos
     * @return Cadena con la duración formateada
     */
    @kotlin.jvm.JvmStatic()
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String formatDuration(long timeMs) {
        return null;
    }
    
    /**
     * Formatea elevación en metros con la unidad apropiada
     *
     * @param elevationMeters Elevación en metros
     * @return Cadena formateada (ej: "125 m")
     */
    @kotlin.jvm.JvmStatic()
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String formatElevation(float elevationMeters) {
        return null;
    }
    
    /**
     * Versión sobrecargada para valores de elevación de tipo Double
     *
     * @param elevationMeters Elevación en metros
     * @return Cadena formateada (ej: "125 m")
     */
    @kotlin.jvm.JvmStatic()
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String formatElevation(double elevationMeters) {
        return null;
    }
    
    /**
     * Formatea ganancia de elevación con signo positivo para indicar ganancia
     *
     * @param elevationGainMeters Ganancia de elevación en metros
     * @return Cadena formateada (ej: "+125 m")
     */
    @kotlin.jvm.JvmStatic()
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String formatElevationGain(float elevationGainMeters) {
        return null;
    }
    
    /**
     * Versión sobrecargada para valores de ganancia de elevación de tipo Double
     *
     * @param elevationGainMeters Ganancia de elevación en metros
     * @return Cadena formateada (ej: "+125 m")
     */
    @kotlin.jvm.JvmStatic()
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String formatElevationGain(double elevationGainMeters) {
        return null;
    }
}