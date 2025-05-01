package com.example.stepbystep.data.mapper;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u000e\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tJ \u0010\n\u001a\u0014\u0012\u0004\u0012\u00020\f\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000e0\r0\u000b2\u0006\u0010\u000f\u001a\u00020\u0007J\n\u0010\u0010\u001a\u00020\u0011*\u00020\u000eJ\n\u0010\u0010\u001a\u00020\u0007*\u00020\tJ\u001c\u0010\u0012\u001a\u0014\u0012\u0004\u0012\u00020\f\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000e0\r0\u000b*\u00020\u0007R\u0016\u0010\u0003\u001a\n \u0005*\u0004\u0018\u00010\u00040\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0013"}, d2 = {"Lcom/example/stepbystep/data/mapper/RouteMapper;", "", "()V", "DATE_FMT", "Ljava/time/format/DateTimeFormatter;", "kotlin.jvm.PlatformType", "fromEntity", "Lcom/example/stepbystep/domain/model/Route;", "rwp", "Lcom/example/stepbystep/data/local/RouteWithPoints;", "toEntity", "Lkotlin/Pair;", "Lcom/example/stepbystep/data/local/RouteEntity;", "", "Lcom/example/stepbystep/data/local/PointEntity;", "route", "toDomain", "Lcom/example/stepbystep/domain/model/Point;", "toEntities", "app_debug"})
public final class RouteMapper {
    private static final java.time.format.DateTimeFormatter DATE_FMT = null;
    @org.jetbrains.annotations.NotNull()
    public static final com.example.stepbystep.data.mapper.RouteMapper INSTANCE = null;
    
    private RouteMapper() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.example.stepbystep.domain.model.Route toDomain(@org.jetbrains.annotations.NotNull()
    com.example.stepbystep.data.local.RouteWithPoints $this$toDomain) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlin.Pair<com.example.stepbystep.data.local.RouteEntity, java.util.List<com.example.stepbystep.data.local.PointEntity>> toEntities(@org.jetbrains.annotations.NotNull()
    com.example.stepbystep.domain.model.Route $this$toEntities) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.example.stepbystep.domain.model.Point toDomain(@org.jetbrains.annotations.NotNull()
    com.example.stepbystep.data.local.PointEntity $this$toDomain) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.example.stepbystep.domain.model.Route fromEntity(@org.jetbrains.annotations.NotNull()
    com.example.stepbystep.data.local.RouteWithPoints rwp) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlin.Pair<com.example.stepbystep.data.local.RouteEntity, java.util.List<com.example.stepbystep.data.local.PointEntity>> toEntity(@org.jetbrains.annotations.NotNull()
    com.example.stepbystep.domain.model.Route route) {
        return null;
    }
}