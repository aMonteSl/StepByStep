package com.example.stepbystep.databinding;
import com.example.stepbystep.R;
import com.example.stepbystep.BR;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class ActivityRouteDetailBindingImpl extends ActivityRouteDetailBinding  {

    @Nullable
    private static final androidx.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.appBarLayout, 9);
        sViewsWithIds.put(R.id.mapView, 10);
        sViewsWithIds.put(R.id.routeImage, 11);
        sViewsWithIds.put(R.id.chartElevation, 12);
    }
    // views
    @NonNull
    private final androidx.coordinatorlayout.widget.CoordinatorLayout mboundView0;
    @NonNull
    private final android.widget.TextView mboundView2;
    @NonNull
    private final android.widget.TextView mboundView3;
    @NonNull
    private final android.widget.TextView mboundView4;
    @NonNull
    private final android.widget.TextView mboundView5;
    @NonNull
    private final android.widget.TextView mboundView7;
    @NonNull
    private final android.widget.TextView mboundView8;
    // variables
    // values
    // listeners
    // Inverse Binding Event Handlers

    public ActivityRouteDetailBindingImpl(@Nullable androidx.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 13, sIncludes, sViewsWithIds));
    }
    private ActivityRouteDetailBindingImpl(androidx.databinding.DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 7
            , (com.google.android.material.appbar.AppBarLayout) bindings[9]
            , (com.github.mikephil.charting.charts.LineChart) bindings[12]
            , (com.google.android.gms.maps.MapView) bindings[10]
            , (android.widget.ImageView) bindings[11]
            , (com.google.android.material.card.MaterialCardView) bindings[6]
            , (com.google.android.material.appbar.MaterialToolbar) bindings[1]
            );
        this.mboundView0 = (androidx.coordinatorlayout.widget.CoordinatorLayout) bindings[0];
        this.mboundView0.setTag(null);
        this.mboundView2 = (android.widget.TextView) bindings[2];
        this.mboundView2.setTag(null);
        this.mboundView3 = (android.widget.TextView) bindings[3];
        this.mboundView3.setTag(null);
        this.mboundView4 = (android.widget.TextView) bindings[4];
        this.mboundView4.setTag(null);
        this.mboundView5 = (android.widget.TextView) bindings[5];
        this.mboundView5.setTag(null);
        this.mboundView7 = (android.widget.TextView) bindings[7];
        this.mboundView7.setTag(null);
        this.mboundView8 = (android.widget.TextView) bindings[8];
        this.mboundView8.setTag(null);
        this.routeImageCard.setTag(null);
        this.toolbar.setTag(null);
        setRootTag(root);
        // listeners
        invalidateAll();
    }

    @Override
    public void invalidateAll() {
        synchronized(this) {
                mDirtyFlags = 0x100L;
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
        if (BR.viewModel == variableId) {
            setViewModel((com.example.stepbystep.ui.routedetail.RouteDetailViewModel) variable);
        }
        else {
            variableSet = false;
        }
            return variableSet;
    }

    public void setViewModel(@Nullable com.example.stepbystep.ui.routedetail.RouteDetailViewModel ViewModel) {
        this.mViewModel = ViewModel;
        synchronized(this) {
            mDirtyFlags |= 0x80L;
        }
        notifyPropertyChanged(BR.viewModel);
        super.requestRebind();
    }

    @Override
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
            case 0 :
                return onChangeViewModelFormattedElevationGain((androidx.lifecycle.LiveData<java.lang.String>) object, fieldId);
            case 1 :
                return onChangeViewModelFormattedDistance((androidx.lifecycle.LiveData<java.lang.String>) object, fieldId);
            case 2 :
                return onChangeViewModelFormattedElevation((androidx.lifecycle.LiveData<java.lang.String>) object, fieldId);
            case 3 :
                return onChangeViewModelFormattedDuration((androidx.lifecycle.LiveData<java.lang.String>) object, fieldId);
            case 4 :
                return onChangeViewModelRouteImageVisibility((androidx.lifecycle.LiveData<java.lang.Integer>) object, fieldId);
            case 5 :
                return onChangeViewModelFormattedDate((androidx.lifecycle.LiveData<java.lang.String>) object, fieldId);
            case 6 :
                return onChangeViewModelRoute((androidx.lifecycle.LiveData<com.example.stepbystep.domain.model.Route>) object, fieldId);
        }
        return false;
    }
    private boolean onChangeViewModelFormattedElevationGain(androidx.lifecycle.LiveData<java.lang.String> ViewModelFormattedElevationGain, int fieldId) {
        if (fieldId == BR._all) {
            synchronized(this) {
                    mDirtyFlags |= 0x1L;
            }
            return true;
        }
        return false;
    }
    private boolean onChangeViewModelFormattedDistance(androidx.lifecycle.LiveData<java.lang.String> ViewModelFormattedDistance, int fieldId) {
        if (fieldId == BR._all) {
            synchronized(this) {
                    mDirtyFlags |= 0x2L;
            }
            return true;
        }
        return false;
    }
    private boolean onChangeViewModelFormattedElevation(androidx.lifecycle.LiveData<java.lang.String> ViewModelFormattedElevation, int fieldId) {
        if (fieldId == BR._all) {
            synchronized(this) {
                    mDirtyFlags |= 0x4L;
            }
            return true;
        }
        return false;
    }
    private boolean onChangeViewModelFormattedDuration(androidx.lifecycle.LiveData<java.lang.String> ViewModelFormattedDuration, int fieldId) {
        if (fieldId == BR._all) {
            synchronized(this) {
                    mDirtyFlags |= 0x8L;
            }
            return true;
        }
        return false;
    }
    private boolean onChangeViewModelRouteImageVisibility(androidx.lifecycle.LiveData<java.lang.Integer> ViewModelRouteImageVisibility, int fieldId) {
        if (fieldId == BR._all) {
            synchronized(this) {
                    mDirtyFlags |= 0x10L;
            }
            return true;
        }
        return false;
    }
    private boolean onChangeViewModelFormattedDate(androidx.lifecycle.LiveData<java.lang.String> ViewModelFormattedDate, int fieldId) {
        if (fieldId == BR._all) {
            synchronized(this) {
                    mDirtyFlags |= 0x20L;
            }
            return true;
        }
        return false;
    }
    private boolean onChangeViewModelRoute(androidx.lifecycle.LiveData<com.example.stepbystep.domain.model.Route> ViewModelRoute, int fieldId) {
        if (fieldId == BR._all) {
            synchronized(this) {
                    mDirtyFlags |= 0x40L;
            }
            return true;
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
        androidx.lifecycle.LiveData<java.lang.String> viewModelFormattedElevationGain = null;
        java.lang.Integer viewModelRouteImageVisibilityGetValue = null;
        androidx.lifecycle.LiveData<java.lang.String> viewModelFormattedDistance = null;
        java.lang.String viewModelFormattedDistanceGetValue = null;
        androidx.lifecycle.LiveData<java.lang.String> viewModelFormattedElevation = null;
        java.lang.String viewModelRouteDescription = null;
        java.lang.String viewModelRouteName = null;
        androidx.lifecycle.LiveData<java.lang.String> viewModelFormattedDuration = null;
        com.example.stepbystep.domain.model.Route viewModelRouteGetValue = null;
        androidx.lifecycle.LiveData<java.lang.Integer> viewModelRouteImageVisibility = null;
        androidx.lifecycle.LiveData<java.lang.String> viewModelFormattedDate = null;
        int androidxDatabindingViewDataBindingSafeUnboxViewModelRouteImageVisibilityGetValue = 0;
        java.lang.String viewModelFormattedDurationGetValue = null;
        java.lang.String viewModelFormattedElevationGetValue = null;
        java.lang.String viewModelFormattedDateGetValue = null;
        java.lang.String viewModelFormattedElevationGainGetValue = null;
        com.example.stepbystep.ui.routedetail.RouteDetailViewModel viewModel = mViewModel;
        androidx.lifecycle.LiveData<com.example.stepbystep.domain.model.Route> viewModelRoute = null;

        if ((dirtyFlags & 0x1ffL) != 0) {


            if ((dirtyFlags & 0x181L) != 0) {

                    if (viewModel != null) {
                        // read viewModel.formattedElevationGain
                        viewModelFormattedElevationGain = viewModel.getFormattedElevationGain();
                    }
                    updateLiveDataRegistration(0, viewModelFormattedElevationGain);


                    if (viewModelFormattedElevationGain != null) {
                        // read viewModel.formattedElevationGain.getValue()
                        viewModelFormattedElevationGainGetValue = viewModelFormattedElevationGain.getValue();
                    }
            }
            if ((dirtyFlags & 0x182L) != 0) {

                    if (viewModel != null) {
                        // read viewModel.formattedDistance
                        viewModelFormattedDistance = viewModel.getFormattedDistance();
                    }
                    updateLiveDataRegistration(1, viewModelFormattedDistance);


                    if (viewModelFormattedDistance != null) {
                        // read viewModel.formattedDistance.getValue()
                        viewModelFormattedDistanceGetValue = viewModelFormattedDistance.getValue();
                    }
            }
            if ((dirtyFlags & 0x184L) != 0) {

                    if (viewModel != null) {
                        // read viewModel.formattedElevation
                        viewModelFormattedElevation = viewModel.getFormattedElevation();
                    }
                    updateLiveDataRegistration(2, viewModelFormattedElevation);


                    if (viewModelFormattedElevation != null) {
                        // read viewModel.formattedElevation.getValue()
                        viewModelFormattedElevationGetValue = viewModelFormattedElevation.getValue();
                    }
            }
            if ((dirtyFlags & 0x188L) != 0) {

                    if (viewModel != null) {
                        // read viewModel.formattedDuration
                        viewModelFormattedDuration = viewModel.getFormattedDuration();
                    }
                    updateLiveDataRegistration(3, viewModelFormattedDuration);


                    if (viewModelFormattedDuration != null) {
                        // read viewModel.formattedDuration.getValue()
                        viewModelFormattedDurationGetValue = viewModelFormattedDuration.getValue();
                    }
            }
            if ((dirtyFlags & 0x190L) != 0) {

                    if (viewModel != null) {
                        // read viewModel.routeImageVisibility
                        viewModelRouteImageVisibility = viewModel.getRouteImageVisibility();
                    }
                    updateLiveDataRegistration(4, viewModelRouteImageVisibility);


                    if (viewModelRouteImageVisibility != null) {
                        // read viewModel.routeImageVisibility.getValue()
                        viewModelRouteImageVisibilityGetValue = viewModelRouteImageVisibility.getValue();
                    }


                    // read androidx.databinding.ViewDataBinding.safeUnbox(viewModel.routeImageVisibility.getValue())
                    androidxDatabindingViewDataBindingSafeUnboxViewModelRouteImageVisibilityGetValue = androidx.databinding.ViewDataBinding.safeUnbox(viewModelRouteImageVisibilityGetValue);
            }
            if ((dirtyFlags & 0x1a0L) != 0) {

                    if (viewModel != null) {
                        // read viewModel.formattedDate
                        viewModelFormattedDate = viewModel.getFormattedDate();
                    }
                    updateLiveDataRegistration(5, viewModelFormattedDate);


                    if (viewModelFormattedDate != null) {
                        // read viewModel.formattedDate.getValue()
                        viewModelFormattedDateGetValue = viewModelFormattedDate.getValue();
                    }
            }
            if ((dirtyFlags & 0x1c0L) != 0) {

                    if (viewModel != null) {
                        // read viewModel.route
                        viewModelRoute = viewModel.getRoute();
                    }
                    updateLiveDataRegistration(6, viewModelRoute);


                    if (viewModelRoute != null) {
                        // read viewModel.route.getValue()
                        viewModelRouteGetValue = viewModelRoute.getValue();
                    }


                    if (viewModelRouteGetValue != null) {
                        // read viewModel.route.getValue().description
                        viewModelRouteDescription = viewModelRouteGetValue.getDescription();
                        // read viewModel.route.getValue().name
                        viewModelRouteName = viewModelRouteGetValue.getName();
                    }
            }
        }
        // batch finished
        if ((dirtyFlags & 0x182L) != 0) {
            // api target 1

            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.mboundView2, viewModelFormattedDistanceGetValue);
        }
        if ((dirtyFlags & 0x188L) != 0) {
            // api target 1

            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.mboundView3, viewModelFormattedDurationGetValue);
        }
        if ((dirtyFlags & 0x184L) != 0) {
            // api target 1

            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.mboundView4, viewModelFormattedElevationGetValue);
        }
        if ((dirtyFlags & 0x181L) != 0) {
            // api target 1

            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.mboundView5, viewModelFormattedElevationGainGetValue);
        }
        if ((dirtyFlags & 0x1a0L) != 0) {
            // api target 1

            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.mboundView7, viewModelFormattedDateGetValue);
        }
        if ((dirtyFlags & 0x1c0L) != 0) {
            // api target 1

            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.mboundView8, viewModelRouteDescription);
            this.toolbar.setTitle(viewModelRouteName);
        }
        if ((dirtyFlags & 0x190L) != 0) {
            // api target 1

            this.routeImageCard.setVisibility(androidxDatabindingViewDataBindingSafeUnboxViewModelRouteImageVisibilityGetValue);
        }
    }
    // Listener Stub Implementations
    // callback impls
    // dirty flag
    private  long mDirtyFlags = 0xffffffffffffffffL;
    /* flag mapping
        flag 0 (0x1L): viewModel.formattedElevationGain
        flag 1 (0x2L): viewModel.formattedDistance
        flag 2 (0x3L): viewModel.formattedElevation
        flag 3 (0x4L): viewModel.formattedDuration
        flag 4 (0x5L): viewModel.routeImageVisibility
        flag 5 (0x6L): viewModel.formattedDate
        flag 6 (0x7L): viewModel.route
        flag 7 (0x8L): viewModel
        flag 8 (0x9L): null
    flag mapping end*/
    //end
}