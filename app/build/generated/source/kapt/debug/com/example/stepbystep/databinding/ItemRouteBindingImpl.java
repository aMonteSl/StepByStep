package com.example.stepbystep.databinding;
import com.example.stepbystep.R;
import com.example.stepbystep.BR;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class ItemRouteBindingImpl extends ItemRouteBinding  {

    @Nullable
    private static final androidx.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = null;
    }
    // views
    @NonNull
    private final com.google.android.material.card.MaterialCardView mboundView0;
    // variables
    // values
    // listeners
    // Inverse Binding Event Handlers

    public ItemRouteBindingImpl(@Nullable androidx.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 7, sIncludes, sViewsWithIds));
    }
    private ItemRouteBindingImpl(androidx.databinding.DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0
            , (android.widget.TextView) bindings[2]
            , (android.widget.TextView) bindings[3]
            , (android.widget.TextView) bindings[4]
            , (android.widget.TextView) bindings[5]
            , (android.widget.TextView) bindings[6]
            , (android.widget.TextView) bindings[1]
            );
        this.mboundView0 = (com.google.android.material.card.MaterialCardView) bindings[0];
        this.mboundView0.setTag(null);
        this.tvRouteDate.setTag(null);
        this.tvRouteDistance.setTag(null);
        this.tvRouteDuration.setTag(null);
        this.tvRouteElevation.setTag(null);
        this.tvRouteElevationGain.setTag(null);
        this.tvRouteName.setTag(null);
        setRootTag(root);
        // listeners
        invalidateAll();
    }

    @Override
    public void invalidateAll() {
        synchronized(this) {
                mDirtyFlags = 0x2L;
        }
        requestRebind();
    }

    @Override
    public boolean hasPendingBindings() {
        synchronized(this) {
            if (mDirtyFlags != 0) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean setVariable(int variableId, @Nullable Object variable)  {
        boolean variableSet = true;
        if (BR.route == variableId) {
            setRoute((com.example.stepbystep.domain.model.Route) variable);
        }
        else {
            variableSet = false;
        }
            return variableSet;
    }

    public void setRoute(@Nullable com.example.stepbystep.domain.model.Route Route) {
        this.mRoute = Route;
        synchronized(this) {
            mDirtyFlags |= 0x1L;
        }
        notifyPropertyChanged(BR.route);
        super.requestRebind();
    }

    @Override
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
        }
        return false;
    }

    @Override
    protected void executeBindings() {
        long dirtyFlags = 0;
        synchronized(this) {
            dirtyFlags = mDirtyFlags;
            mDirtyFlags = 0;
        }
        java.lang.String dateFormatUtilsFormatDateRouteDate = null;
        double routeElevation = 0.0;
        java.lang.String routeDate = null;
        long routeDuration = 0;
        java.lang.String stringFormatUtilsFormatElevationRouteElevation = null;
        double routeDistance = 0.0;
        com.example.stepbystep.domain.model.Route route = mRoute;
        java.lang.String stringFormatUtilsFormatDurationRouteDuration = null;
        java.lang.String routeName = null;
        java.lang.String stringFormatUtilsFormatElevationGainRouteElevationGain = null;
        double routeElevationGain = 0.0;
        java.lang.String stringFormatUtilsFormatDistanceKmRouteDistance = null;

        if ((dirtyFlags & 0x3L) != 0) {



                if (route != null) {
                    // read route.elevation
                    routeElevation = route.getElevation();
                    // read route.date
                    routeDate = route.getDate();
                    // read route.duration
                    routeDuration = route.getDuration();
                    // read route.distance
                    routeDistance = route.getDistance();
                    // read route.name
                    routeName = route.getName();
                    // read route.elevationGain
                    routeElevationGain = route.getElevationGain();
                }


                // read StringFormatUtils.formatElevation(route.elevation)
                stringFormatUtilsFormatElevationRouteElevation = com.example.stepbystep.util.StringFormatUtils.formatElevation(routeElevation);
                // read DateFormatUtils.formatDate(route.date)
                dateFormatUtilsFormatDateRouteDate = com.example.stepbystep.util.DateFormatUtils.formatDate(routeDate);
                // read StringFormatUtils.formatDuration(route.duration)
                stringFormatUtilsFormatDurationRouteDuration = com.example.stepbystep.util.StringFormatUtils.formatDuration(routeDuration);
                // read StringFormatUtils.formatDistanceKm(route.distance)
                stringFormatUtilsFormatDistanceKmRouteDistance = com.example.stepbystep.util.StringFormatUtils.formatDistanceKm(routeDistance);
                // read StringFormatUtils.formatElevationGain(route.elevationGain)
                stringFormatUtilsFormatElevationGainRouteElevationGain = com.example.stepbystep.util.StringFormatUtils.formatElevationGain(routeElevationGain);
        }
        // batch finished
        if ((dirtyFlags & 0x3L) != 0) {
            // api target 1

            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.tvRouteDate, dateFormatUtilsFormatDateRouteDate);
            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.tvRouteDistance, stringFormatUtilsFormatDistanceKmRouteDistance);
            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.tvRouteDuration, stringFormatUtilsFormatDurationRouteDuration);
            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.tvRouteElevation, stringFormatUtilsFormatElevationRouteElevation);
            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.tvRouteElevationGain, stringFormatUtilsFormatElevationGainRouteElevationGain);
            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.tvRouteName, routeName);
        }
    }
    // Listener Stub Implementations
    // callback impls
    // dirty flag
    private  long mDirtyFlags = 0xffffffffffffffffL;
    /* flag mapping
        flag 0 (0x1L): route
        flag 1 (0x2L): null
    flag mapping end*/
    //end
}