package com.example.stepbystep.util;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0006\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J&\u0010\u0003\u001a\u0014\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00050\u00042\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007J\u0018\u0010\t\u001a\u0004\u0018\u00010\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eJ\u0010\u0010\t\u001a\u00020\n2\u0006\u0010\u000f\u001a\u00020\u0010H\u0002J\u0010\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0014H\u0002J\u0016\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\b0\u00072\u0006\u0010\u0016\u001a\u00020\u0017H\u0002\u00a8\u0006\u0018"}, d2 = {"Lcom/example/stepbystep/util/GpxParser;", "", "()V", "calculateRouteStats", "Lkotlin/Triple;", "", "points", "", "Lcom/example/stepbystep/util/GpxPoint;", "parse", "Lcom/example/stepbystep/util/GpxData;", "context", "Landroid/content/Context;", "uri", "Landroid/net/Uri;", "inputStream", "Ljava/io/InputStream;", "parseTimeToMillis", "", "timeText", "", "parseTrackPoints", "trackPoints", "Lorg/w3c/dom/NodeList;", "app_debug"})
public final class GpxParser {
    @org.jetbrains.annotations.NotNull()
    public static final com.example.stepbystep.util.GpxParser INSTANCE = null;
    
    private GpxParser() {
        super();
    }
    
    @org.jetbrains.annotations.Nullable()
    public final com.example.stepbystep.util.GpxData parse(@org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.NotNull()
    android.net.Uri uri) {
        return null;
    }
    
    private final com.example.stepbystep.util.GpxData parse(java.io.InputStream inputStream) {
        return null;
    }
    
    private final java.util.List<com.example.stepbystep.util.GpxPoint> parseTrackPoints(org.w3c.dom.NodeList trackPoints) {
        return null;
    }
    
    private final long parseTimeToMillis(java.lang.String timeText) {
        return 0L;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlin.Triple<java.lang.Double, java.lang.Double, java.lang.Double> calculateRouteStats(@org.jetbrains.annotations.NotNull()
    java.util.List<com.example.stepbystep.util.GpxPoint> points) {
        return null;
    }
}