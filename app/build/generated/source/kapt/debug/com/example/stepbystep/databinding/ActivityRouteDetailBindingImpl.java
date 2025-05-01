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
        super(bindingComponent, root, 1
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
                mDirtyFlags = 0x4L;
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
            mDirtyFlags |= 0x2L;
        }
        notifyPropertyChanged(BR.viewModel);
        super.requestRebind();
    }

    @Override
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
            case 0 :
                return onChangeViewModelRoute((androidx.lifecycle.LiveData<com.example.stepbystep.domain.model.Route>) object, fieldId);
        }
        return false;
    }
    private boolean onChangeViewModelRoute(androidx.lifecycle.LiveData<com.example.stepbystep.domain.model.Route> ViewModelRoute, int fieldId) {
        if (fieldId == BR._all) {
            synchronized(this) {
                    mDirtyFlags |= 0x1L;
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
        java.lang.String viewModelRouteImagePath = null;
        java.lang.String stringFormatUtilsFormatDurationViewModelRouteDuration = null;
        java.lang.String viewModelRouteDescription = null;
        java.lang.String viewModelRouteName = null;
        java.lang.String viewModelRouteDate = null;
        com.example.stepbystep.domain.model.Route viewModelRouteGetValue = null;
        java.lang.String dateFormatUtilsFormatDateViewModelRouteDate = null;
        java.lang.String stringFormatUtilsFormatElevationGainViewModelRouteElevationGain = null;
        java.lang.String stringFormatUtilsFormatDistanceKmViewModelRouteDistance = null;
        double viewModelRouteElevationGain = 0.0;
        long viewModelRouteDuration = 0;
        boolean viewModelRouteImagePathJavaLangObjectNull = false;
        java.lang.String stringFormatUtilsFormatElevationViewModelRouteElevation = null;
        double viewModelRouteElevation = 0.0;
        int viewModelRouteImagePathJavaLangObjectNullViewVISIBLEViewGONE = 0;
        com.example.stepbystep.ui.routedetail.RouteDetailViewModel viewModel = mViewModel;
        androidx.lifecycle.LiveData<com.example.stepbystep.domain.model.Route> viewModelRoute = null;
        double viewModelRouteDistance = 0.0;

        if ((dirtyFlags & 0x7L) != 0) {



                if (viewModel != null) {
                    // read viewModel.route
                    viewModelRoute = viewModel.getRoute();
                }
                updateLiveDataRegistration(0, viewModelRoute);


                if (viewModelRoute != null) {
                    // read viewModel.route.getValue()
                    viewModelRouteGetValue = viewModelRoute.getValue();
                }


                if (viewModelRouteGetValue != null) {
                    // read viewModel.route.getValue().imagePath
                    viewModelRouteImagePath = viewModelRouteGetValue.getImagePath();
                    // read viewModel.route.getValue().description
                    viewModelRouteDescription = viewModelRouteGetValue.getDescription();
                    // read viewModel.route.getValue().name
                    viewModelRouteName = viewModelRouteGetValue.getName();
                    // read viewModel.route.getValue().date
                    viewModelRouteDate = viewModelRouteGetValue.getDate();
                    // read viewModel.route.getValue().elevationGain
                    viewModelRouteElevationGain = viewModelRouteGetValue.getElevationGain();
                    // read viewModel.route.getValue().duration
                    viewModelRouteDuration = viewModelRouteGetValue.getDuration();
                    // read viewModel.route.getValue().elevation
                    viewModelRouteElevation = viewModelRouteGetValue.getElevation();
                    // read viewModel.route.getValue().distance
                    viewModelRouteDistance = viewModelRouteGetValue.getDistance();
                }


                // read viewModel.route.getValue().imagePath != null
                viewModelRouteImagePathJavaLangObjectNull = (viewModelRouteImagePath) != (null);
                // read DateFormatUtils.formatDate(viewModel.route.getValue().date)
                dateFormatUtilsFormatDateViewModelRouteDate = com.example.stepbystep.util.DateFormatUtils.formatDate(viewModelRouteDate);
                // read StringFormatUtils.formatElevationGain(viewModel.route.getValue().elevationGain)
                stringFormatUtilsFormatElevationGainViewModelRouteElevationGain = com.example.stepbystep.util.StringFormatUtils.formatElevationGain(viewModelRouteElevationGain);
                // read StringFormatUtils.formatDuration(viewModel.route.getValue().duration)
                stringFormatUtilsFormatDurationViewModelRouteDuration = com.example.stepbystep.util.StringFormatUtils.formatDuration(viewModelRouteDuration);
                // read StringFormatUtils.formatElevation(viewModel.route.getValue().elevation)
                stringFormatUtilsFormatElevationViewModelRouteElevation = com.example.stepbystep.util.StringFormatUtils.formatElevation(viewModelRouteElevation);
                // read StringFormatUtils.formatDistanceKm(viewModel.route.getValue().distance)
                stringFormatUtilsFormatDistanceKmViewModelRouteDistance = com.example.stepbystep.util.StringFormatUtils.formatDistanceKm(viewModelRouteDistance);
            if((dirtyFlags & 0x7L) != 0) {
                if(viewModelRouteImagePathJavaLangObjectNull) {
                        dirtyFlags |= 0x10L;
                }
                else {
                        dirtyFlags |= 0x8L;
                }
            }


                // read viewModel.route.getValue().imagePath != null ? View.VISIBLE : View.GONE
                viewModelRouteImagePathJavaLangObjectNullViewVISIBLEViewGONE = ((viewModelRouteImagePathJavaLangObjectNull) ? (android.view.View.VISIBLE) : (android.view.View.GONE));
        }
        // batch finished
        if ((dirtyFlags & 0x7L) != 0) {
            // api target 1

            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.mboundView2, stringFormatUtilsFormatDistanceKmViewModelRouteDistance);
            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.mboundView3, stringFormatUtilsFormatDurationViewModelRouteDuration);
            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.mboundView4, stringFormatUtilsFormatElevationViewModelRouteElevation);
            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.mboundView5, stringFormatUtilsFormatElevationGainViewModelRouteElevationGain);
            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.mboundView7, dateFormatUtilsFormatDateViewModelRouteDate);
            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.mboundView8, viewModelRouteDescription);
            this.routeImageCard.setVisibility(viewModelRouteImagePathJavaLangObjectNullViewVISIBLEViewGONE);
            this.toolbar.setTitle(viewModelRouteName);
        }
    }
    // Listener Stub Implementations
    // callback impls
    // dirty flag
    private  long mDirtyFlags = 0xffffffffffffffffL;
    /* flag mapping
        flag 0 (0x1L): viewModel.route
        flag 1 (0x2L): viewModel
        flag 2 (0x3L): null
        flag 3 (0x4L): viewModel.route.getValue().imagePath != null ? View.VISIBLE : View.GONE
        flag 4 (0x5L): viewModel.route.getValue().imagePath != null ? View.VISIBLE : View.GONE
    flag mapping end*/
    //end
}