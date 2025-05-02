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
        if (BR.displayModel == variableId) {
            setDisplayModel((com.example.stepbystep.ui.main.RouteDisplayModel) variable);
        }
        else {
            variableSet = false;
        }
            return variableSet;
    }

    public void setDisplayModel(@Nullable com.example.stepbystep.ui.main.RouteDisplayModel DisplayModel) {
        this.mDisplayModel = DisplayModel;
        synchronized(this) {
            mDirtyFlags |= 0x1L;
        }
        notifyPropertyChanged(BR.displayModel);
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
        java.lang.String displayModelFormattedDistance = null;
        java.lang.String displayModelFormattedElevation = null;
        com.example.stepbystep.ui.main.RouteDisplayModel displayModel = mDisplayModel;
        java.lang.String displayModelFormattedDuration = null;
        java.lang.String displayModelFormattedDate = null;
        java.lang.String displayModelFormattedElevationGain = null;
        java.lang.String displayModelName = null;

        if ((dirtyFlags & 0x3L) != 0) {



                if (displayModel != null) {
                    // read displayModel.formattedDistance
                    displayModelFormattedDistance = displayModel.getFormattedDistance();
                    // read displayModel.formattedElevation
                    displayModelFormattedElevation = displayModel.getFormattedElevation();
                    // read displayModel.formattedDuration
                    displayModelFormattedDuration = displayModel.getFormattedDuration();
                    // read displayModel.formattedDate
                    displayModelFormattedDate = displayModel.getFormattedDate();
                    // read displayModel.formattedElevationGain
                    displayModelFormattedElevationGain = displayModel.getFormattedElevationGain();
                    // read displayModel.name
                    displayModelName = displayModel.getName();
                }
        }
        // batch finished
        if ((dirtyFlags & 0x3L) != 0) {
            // api target 1

            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.tvRouteDate, displayModelFormattedDate);
            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.tvRouteDistance, displayModelFormattedDistance);
            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.tvRouteDuration, displayModelFormattedDuration);
            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.tvRouteElevation, displayModelFormattedElevation);
            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.tvRouteElevationGain, displayModelFormattedElevationGain);
            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.tvRouteName, displayModelName);
        }
    }
    // Listener Stub Implementations
    // callback impls
    // dirty flag
    private  long mDirtyFlags = 0xffffffffffffffffL;
    /* flag mapping
        flag 0 (0x1L): displayModel
        flag 1 (0x2L): null
    flag mapping end*/
    //end
}