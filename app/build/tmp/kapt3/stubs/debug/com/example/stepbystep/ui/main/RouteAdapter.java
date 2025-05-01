package com.example.stepbystep.ui.main;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001\u0017B?\u0012\u000e\b\u0002\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004\u0012\u0012\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\b0\u0007\u0012\u0014\b\u0002\u0010\t\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\n0\u0007\u00a2\u0006\u0002\u0010\u000bJ\b\u0010\f\u001a\u00020\rH\u0016J\u0018\u0010\u000e\u001a\u00020\b2\u0006\u0010\u000f\u001a\u00020\u00022\u0006\u0010\u0010\u001a\u00020\rH\u0016J\u0018\u0010\u0011\u001a\u00020\u00022\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\rH\u0016J\u0014\u0010\u0015\u001a\u00020\b2\f\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004R\u001a\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\b0\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\t\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\n0\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0018"}, d2 = {"Lcom/example/stepbystep/ui/main/RouteAdapter;", "Landroidx/recyclerview/widget/RecyclerView$Adapter;", "Lcom/example/stepbystep/ui/main/RouteAdapter$RouteViewHolder;", "routes", "", "Lcom/example/stepbystep/domain/model/Route;", "onClick", "Lkotlin/Function1;", "", "onLongClick", "", "(Ljava/util/List;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;)V", "getItemCount", "", "onBindViewHolder", "holder", "position", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "updateRoutes", "newRoutes", "RouteViewHolder", "app_debug"})
public final class RouteAdapter extends androidx.recyclerview.widget.RecyclerView.Adapter<com.example.stepbystep.ui.main.RouteAdapter.RouteViewHolder> {
    @org.jetbrains.annotations.NotNull()
    private java.util.List<com.example.stepbystep.domain.model.Route> routes;
    @org.jetbrains.annotations.NotNull()
    private final kotlin.jvm.functions.Function1<com.example.stepbystep.domain.model.Route, kotlin.Unit> onClick = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlin.jvm.functions.Function1<com.example.stepbystep.domain.model.Route, java.lang.Boolean> onLongClick = null;
    
    public RouteAdapter(@org.jetbrains.annotations.NotNull()
    java.util.List<com.example.stepbystep.domain.model.Route> routes, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super com.example.stepbystep.domain.model.Route, kotlin.Unit> onClick, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super com.example.stepbystep.domain.model.Route, java.lang.Boolean> onLongClick) {
        super();
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public com.example.stepbystep.ui.main.RouteAdapter.RouteViewHolder onCreateViewHolder(@org.jetbrains.annotations.NotNull()
    android.view.ViewGroup parent, int viewType) {
        return null;
    }
    
    @java.lang.Override()
    public void onBindViewHolder(@org.jetbrains.annotations.NotNull()
    com.example.stepbystep.ui.main.RouteAdapter.RouteViewHolder holder, int position) {
    }
    
    @java.lang.Override()
    public int getItemCount() {
        return 0;
    }
    
    public final void updateRoutes(@org.jetbrains.annotations.NotNull()
    java.util.List<com.example.stepbystep.domain.model.Route> newRoutes) {
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u00a8\u0006\u0007"}, d2 = {"Lcom/example/stepbystep/ui/main/RouteAdapter$RouteViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "binding", "Lcom/example/stepbystep/databinding/ItemRouteBinding;", "(Lcom/example/stepbystep/databinding/ItemRouteBinding;)V", "getBinding", "()Lcom/example/stepbystep/databinding/ItemRouteBinding;", "app_debug"})
    public static final class RouteViewHolder extends androidx.recyclerview.widget.RecyclerView.ViewHolder {
        @org.jetbrains.annotations.NotNull()
        private final com.example.stepbystep.databinding.ItemRouteBinding binding = null;
        
        public RouteViewHolder(@org.jetbrains.annotations.NotNull()
        com.example.stepbystep.databinding.ItemRouteBinding binding) {
            super(null);
        }
        
        @org.jetbrains.annotations.NotNull()
        public final com.example.stepbystep.databinding.ItemRouteBinding getBinding() {
            return null;
        }
    }
}