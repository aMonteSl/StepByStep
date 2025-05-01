package com.example.stepbystep.databinding;
import com.example.stepbystep.R;
import com.example.stepbystep.BR;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class ActivityNewRouteBindingImpl extends ActivityNewRouteBinding  {

    @Nullable
    private static final androidx.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.appBarLayout, 8);
        sViewsWithIds.put(R.id.toolbar, 9);
        sViewsWithIds.put(R.id.mapView, 10);
        sViewsWithIds.put(R.id.fabSelectReference, 11);
        sViewsWithIds.put(R.id.statsCard, 12);
        sViewsWithIds.put(R.id.buttonsContainer, 13);
    }
    // views
    @NonNull
    private final androidx.constraintlayout.widget.ConstraintLayout mboundView0;
    // variables
    // values
    // listeners
    // Inverse Binding Event Handlers

    public ActivityNewRouteBindingImpl(@Nullable androidx.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 14, sIncludes, sViewsWithIds));
    }
    private ActivityNewRouteBindingImpl(androidx.databinding.DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 8
            , (com.google.android.material.appbar.AppBarLayout) bindings[8]
            , (com.google.android.material.button.MaterialButton) bindings[7]
            , (com.google.android.material.button.MaterialButton) bindings[6]
            , (android.widget.LinearLayout) bindings[13]
            , (com.google.android.material.chip.Chip) bindings[5]
            , (com.google.android.material.floatingactionbutton.FloatingActionButton) bindings[11]
            , (com.google.android.gms.maps.MapView) bindings[10]
            , (com.google.android.material.card.MaterialCardView) bindings[12]
            , (com.google.android.material.appbar.MaterialToolbar) bindings[9]
            , (android.widget.TextView) bindings[1]
            , (android.widget.TextView) bindings[2]
            , (android.widget.TextView) bindings[3]
            , (android.widget.TextView) bindings[4]
            );
        this.btnPauseResume.setTag(null);
        this.btnStartStop.setTag(null);
        this.chipReferenceRoute.setTag(null);
        this.mboundView0 = (androidx.constraintlayout.widget.ConstraintLayout) bindings[0];
        this.mboundView0.setTag(null);
        this.tvRouteDistance.setTag(null);
        this.tvRouteDuration.setTag(null);
        this.tvRouteElevation.setTag(null);
        this.tvRouteElevationGain.setTag(null);
        setRootTag(root);
        // listeners
        invalidateAll();
    }

    @Override
    public void invalidateAll() {
        synchronized(this) {
                mDirtyFlags = 0x200L;
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
            setViewModel((com.example.stepbystep.ui.newroute.NewRouteViewModel) variable);
        }
        else {
            variableSet = false;
        }
            return variableSet;
    }

    public void setViewModel(@Nullable com.example.stepbystep.ui.newroute.NewRouteViewModel ViewModel) {
        this.mViewModel = ViewModel;
        synchronized(this) {
            mDirtyFlags |= 0x100L;
        }
        notifyPropertyChanged(BR.viewModel);
        super.requestRebind();
    }

    @Override
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
            case 0 :
                return onChangeViewModelCurrentDistance((androidx.lifecycle.LiveData<java.lang.Double>) object, fieldId);
            case 1 :
                return onChangeViewModelElapsedTimeMs((androidx.lifecycle.LiveData<java.lang.Long>) object, fieldId);
            case 2 :
                return onChangeViewModelReferenceRouteName((androidx.lifecycle.LiveData<java.lang.String>) object, fieldId);
            case 3 :
                return onChangeViewModelCurrentElevation((androidx.lifecycle.LiveData<java.lang.Double>) object, fieldId);
            case 4 :
                return onChangeViewModelElevationGain((androidx.lifecycle.LiveData<java.lang.Double>) object, fieldId);
            case 5 :
                return onChangeViewModelIsRecording((androidx.lifecycle.LiveData<java.lang.Boolean>) object, fieldId);
            case 6 :
                return onChangeViewModelReferenceRoute((androidx.lifecycle.LiveData<java.util.List<com.google.android.gms.maps.model.LatLng>>) object, fieldId);
            case 7 :
                return onChangeViewModelIsPaused((androidx.lifecycle.LiveData<java.lang.Boolean>) object, fieldId);
        }
        return false;
    }
    private boolean onChangeViewModelCurrentDistance(androidx.lifecycle.LiveData<java.lang.Double> ViewModelCurrentDistance, int fieldId) {
        if (fieldId == BR._all) {
            synchronized(this) {
                    mDirtyFlags |= 0x1L;
            }
            return true;
        }
        return false;
    }
    private boolean onChangeViewModelElapsedTimeMs(androidx.lifecycle.LiveData<java.lang.Long> ViewModelElapsedTimeMs, int fieldId) {
        if (fieldId == BR._all) {
            synchronized(this) {
                    mDirtyFlags |= 0x2L;
            }
            return true;
        }
        return false;
    }
    private boolean onChangeViewModelReferenceRouteName(androidx.lifecycle.LiveData<java.lang.String> ViewModelReferenceRouteName, int fieldId) {
        if (fieldId == BR._all) {
            synchronized(this) {
                    mDirtyFlags |= 0x4L;
            }
            return true;
        }
        return false;
    }
    private boolean onChangeViewModelCurrentElevation(androidx.lifecycle.LiveData<java.lang.Double> ViewModelCurrentElevation, int fieldId) {
        if (fieldId == BR._all) {
            synchronized(this) {
                    mDirtyFlags |= 0x8L;
            }
            return true;
        }
        return false;
    }
    private boolean onChangeViewModelElevationGain(androidx.lifecycle.LiveData<java.lang.Double> ViewModelElevationGain, int fieldId) {
        if (fieldId == BR._all) {
            synchronized(this) {
                    mDirtyFlags |= 0x10L;
            }
            return true;
        }
        return false;
    }
    private boolean onChangeViewModelIsRecording(androidx.lifecycle.LiveData<java.lang.Boolean> ViewModelIsRecording, int fieldId) {
        if (fieldId == BR._all) {
            synchronized(this) {
                    mDirtyFlags |= 0x20L;
            }
            return true;
        }
        return false;
    }
    private boolean onChangeViewModelReferenceRoute(androidx.lifecycle.LiveData<java.util.List<com.google.android.gms.maps.model.LatLng>> ViewModelReferenceRoute, int fieldId) {
        if (fieldId == BR._all) {
            synchronized(this) {
                    mDirtyFlags |= 0x40L;
            }
            return true;
        }
        return false;
    }
    private boolean onChangeViewModelIsPaused(androidx.lifecycle.LiveData<java.lang.Boolean> ViewModelIsPaused, int fieldId) {
        if (fieldId == BR._all) {
            synchronized(this) {
                    mDirtyFlags |= 0x80L;
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
        java.lang.String viewModelIsRecordingBtnStartStopAndroidStringRouteStopBtnStartStopAndroidStringRouteStart = null;
        android.graphics.drawable.Drawable viewModelIsPausedBtnPauseResumeAndroidDrawableIcPlayBtnPauseResumeAndroidDrawableIcPause = null;
        java.lang.String viewModelReferenceRouteNameGetValue = null;
        androidx.lifecycle.LiveData<java.lang.Double> viewModelCurrentDistance = null;
        java.lang.String stringFormatUtilsFormatElevationViewModelCurrentElevation = null;
        java.lang.Boolean viewModelIsRecordingGetValue = null;
        java.lang.Boolean viewModelIsPausedGetValue = null;
        long androidxDatabindingViewDataBindingSafeUnboxViewModelElapsedTimeMsGetValue = 0;
        java.lang.Double viewModelElevationGainGetValue = null;
        androidx.lifecycle.LiveData<java.lang.Long> viewModelElapsedTimeMs = null;
        boolean androidxDatabindingViewDataBindingSafeUnboxViewModelIsRecordingGetValue = false;
        java.lang.String stringFormatUtilsFormatElevationGainViewModelElevationGain = null;
        java.lang.Double viewModelCurrentDistanceGetValue = null;
        boolean viewModelReferenceRouteJavaLangObjectNull = false;
        double androidxDatabindingViewDataBindingSafeUnboxViewModelCurrentDistanceGetValue = 0.0;
        boolean viewModelReferenceRouteJavaLangObjectNullBooleanTrueViewModelReferenceRouteIsEmpty = false;
        boolean viewModelReferenceRouteIsEmpty = false;
        androidx.lifecycle.LiveData<java.lang.String> viewModelReferenceRouteName = null;
        java.lang.String stringFormatUtilsFormatDistanceKmViewModelCurrentDistance = null;
        int viewModelIsRecordingAndroidViewViewVISIBLEAndroidViewViewGONE = 0;
        java.lang.String viewModelIsPausedBtnPauseResumeAndroidStringRouteResumeBtnPauseResumeAndroidStringRoutePause = null;
        boolean androidxDatabindingViewDataBindingSafeUnboxViewModelIsPausedGetValue = false;
        double androidxDatabindingViewDataBindingSafeUnboxViewModelCurrentElevationGetValue = 0.0;
        double androidxDatabindingViewDataBindingSafeUnboxViewModelElevationGainGetValue = 0.0;
        java.lang.Double viewModelCurrentElevationGetValue = null;
        androidx.lifecycle.LiveData<java.lang.Double> viewModelCurrentElevation = null;
        androidx.lifecycle.LiveData<java.lang.Double> viewModelElevationGain = null;
        androidx.lifecycle.LiveData<java.lang.Boolean> viewModelIsRecording = null;
        androidx.lifecycle.LiveData<java.util.List<com.google.android.gms.maps.model.LatLng>> viewModelReferenceRoute = null;
        java.lang.String stringFormatUtilsFormatDurationViewModelElapsedTimeMs = null;
        android.graphics.drawable.Drawable viewModelIsRecordingBtnStartStopAndroidDrawableIcStopBtnStartStopAndroidDrawableIcPlay = null;
        java.util.List<com.google.android.gms.maps.model.LatLng> viewModelReferenceRouteGetValue = null;
        java.lang.Long viewModelElapsedTimeMsGetValue = null;
        com.example.stepbystep.ui.newroute.NewRouteViewModel viewModel = mViewModel;
        int viewModelReferenceRouteJavaLangObjectNullBooleanTrueViewModelReferenceRouteIsEmptyViewGONEViewVISIBLE = 0;
        androidx.lifecycle.LiveData<java.lang.Boolean> viewModelIsPaused = null;

        if ((dirtyFlags & 0x3ffL) != 0) {


            if ((dirtyFlags & 0x301L) != 0) {

                    if (viewModel != null) {
                        // read viewModel.currentDistance
                        viewModelCurrentDistance = viewModel.getCurrentDistance();
                    }
                    updateLiveDataRegistration(0, viewModelCurrentDistance);


                    if (viewModelCurrentDistance != null) {
                        // read viewModel.currentDistance.getValue()
                        viewModelCurrentDistanceGetValue = viewModelCurrentDistance.getValue();
                    }


                    // read androidx.databinding.ViewDataBinding.safeUnbox(viewModel.currentDistance.getValue())
                    androidxDatabindingViewDataBindingSafeUnboxViewModelCurrentDistanceGetValue = androidx.databinding.ViewDataBinding.safeUnbox(viewModelCurrentDistanceGetValue);


                    // read StringFormatUtils.formatDistanceKm(androidx.databinding.ViewDataBinding.safeUnbox(viewModel.currentDistance.getValue()))
                    stringFormatUtilsFormatDistanceKmViewModelCurrentDistance = com.example.stepbystep.util.StringFormatUtils.formatDistanceKm(androidxDatabindingViewDataBindingSafeUnboxViewModelCurrentDistanceGetValue);
            }
            if ((dirtyFlags & 0x302L) != 0) {

                    if (viewModel != null) {
                        // read viewModel.elapsedTimeMs
                        viewModelElapsedTimeMs = viewModel.getElapsedTimeMs();
                    }
                    updateLiveDataRegistration(1, viewModelElapsedTimeMs);


                    if (viewModelElapsedTimeMs != null) {
                        // read viewModel.elapsedTimeMs.getValue()
                        viewModelElapsedTimeMsGetValue = viewModelElapsedTimeMs.getValue();
                    }


                    // read androidx.databinding.ViewDataBinding.safeUnbox(viewModel.elapsedTimeMs.getValue())
                    androidxDatabindingViewDataBindingSafeUnboxViewModelElapsedTimeMsGetValue = androidx.databinding.ViewDataBinding.safeUnbox(viewModelElapsedTimeMsGetValue);


                    // read StringFormatUtils.formatDuration(androidx.databinding.ViewDataBinding.safeUnbox(viewModel.elapsedTimeMs.getValue()))
                    stringFormatUtilsFormatDurationViewModelElapsedTimeMs = com.example.stepbystep.util.StringFormatUtils.formatDuration(androidxDatabindingViewDataBindingSafeUnboxViewModelElapsedTimeMsGetValue);
            }
            if ((dirtyFlags & 0x304L) != 0) {

                    if (viewModel != null) {
                        // read viewModel.referenceRouteName
                        viewModelReferenceRouteName = viewModel.getReferenceRouteName();
                    }
                    updateLiveDataRegistration(2, viewModelReferenceRouteName);


                    if (viewModelReferenceRouteName != null) {
                        // read viewModel.referenceRouteName.getValue()
                        viewModelReferenceRouteNameGetValue = viewModelReferenceRouteName.getValue();
                    }
            }
            if ((dirtyFlags & 0x308L) != 0) {

                    if (viewModel != null) {
                        // read viewModel.currentElevation
                        viewModelCurrentElevation = viewModel.getCurrentElevation();
                    }
                    updateLiveDataRegistration(3, viewModelCurrentElevation);


                    if (viewModelCurrentElevation != null) {
                        // read viewModel.currentElevation.getValue()
                        viewModelCurrentElevationGetValue = viewModelCurrentElevation.getValue();
                    }


                    // read androidx.databinding.ViewDataBinding.safeUnbox(viewModel.currentElevation.getValue())
                    androidxDatabindingViewDataBindingSafeUnboxViewModelCurrentElevationGetValue = androidx.databinding.ViewDataBinding.safeUnbox(viewModelCurrentElevationGetValue);


                    // read StringFormatUtils.formatElevation(androidx.databinding.ViewDataBinding.safeUnbox(viewModel.currentElevation.getValue()))
                    stringFormatUtilsFormatElevationViewModelCurrentElevation = com.example.stepbystep.util.StringFormatUtils.formatElevation(androidxDatabindingViewDataBindingSafeUnboxViewModelCurrentElevationGetValue);
            }
            if ((dirtyFlags & 0x310L) != 0) {

                    if (viewModel != null) {
                        // read viewModel.elevationGain
                        viewModelElevationGain = viewModel.getElevationGain();
                    }
                    updateLiveDataRegistration(4, viewModelElevationGain);


                    if (viewModelElevationGain != null) {
                        // read viewModel.elevationGain.getValue()
                        viewModelElevationGainGetValue = viewModelElevationGain.getValue();
                    }


                    // read androidx.databinding.ViewDataBinding.safeUnbox(viewModel.elevationGain.getValue())
                    androidxDatabindingViewDataBindingSafeUnboxViewModelElevationGainGetValue = androidx.databinding.ViewDataBinding.safeUnbox(viewModelElevationGainGetValue);


                    // read StringFormatUtils.formatElevationGain(androidx.databinding.ViewDataBinding.safeUnbox(viewModel.elevationGain.getValue()))
                    stringFormatUtilsFormatElevationGainViewModelElevationGain = com.example.stepbystep.util.StringFormatUtils.formatElevationGain(androidxDatabindingViewDataBindingSafeUnboxViewModelElevationGainGetValue);
            }
            if ((dirtyFlags & 0x320L) != 0) {

                    if (viewModel != null) {
                        // read viewModel.isRecording
                        viewModelIsRecording = viewModel.isRecording();
                    }
                    updateLiveDataRegistration(5, viewModelIsRecording);


                    if (viewModelIsRecording != null) {
                        // read viewModel.isRecording.getValue()
                        viewModelIsRecordingGetValue = viewModelIsRecording.getValue();
                    }


                    // read androidx.databinding.ViewDataBinding.safeUnbox(viewModel.isRecording.getValue())
                    androidxDatabindingViewDataBindingSafeUnboxViewModelIsRecordingGetValue = androidx.databinding.ViewDataBinding.safeUnbox(viewModelIsRecordingGetValue);
                if((dirtyFlags & 0x320L) != 0) {
                    if(androidxDatabindingViewDataBindingSafeUnboxViewModelIsRecordingGetValue) {
                            dirtyFlags |= 0x800L;
                            dirtyFlags |= 0x20000L;
                            dirtyFlags |= 0x200000L;
                    }
                    else {
                            dirtyFlags |= 0x400L;
                            dirtyFlags |= 0x10000L;
                            dirtyFlags |= 0x100000L;
                    }
                }


                    // read androidx.databinding.ViewDataBinding.safeUnbox(viewModel.isRecording.getValue()) ? @android:string/route_stop : @android:string/route_start
                    viewModelIsRecordingBtnStartStopAndroidStringRouteStopBtnStartStopAndroidStringRouteStart = ((androidxDatabindingViewDataBindingSafeUnboxViewModelIsRecordingGetValue) ? (btnStartStop.getResources().getString(R.string.route_stop)) : (btnStartStop.getResources().getString(R.string.route_start)));
                    // read androidx.databinding.ViewDataBinding.safeUnbox(viewModel.isRecording.getValue()) ? android.view.View.VISIBLE : android.view.View.GONE
                    viewModelIsRecordingAndroidViewViewVISIBLEAndroidViewViewGONE = ((androidxDatabindingViewDataBindingSafeUnboxViewModelIsRecordingGetValue) ? (android.view.View.VISIBLE) : (android.view.View.GONE));
                    // read androidx.databinding.ViewDataBinding.safeUnbox(viewModel.isRecording.getValue()) ? @android:drawable/ic_stop : @android:drawable/ic_play
                    viewModelIsRecordingBtnStartStopAndroidDrawableIcStopBtnStartStopAndroidDrawableIcPlay = ((androidxDatabindingViewDataBindingSafeUnboxViewModelIsRecordingGetValue) ? (androidx.appcompat.content.res.AppCompatResources.getDrawable(btnStartStop.getContext(), R.drawable.ic_stop)) : (androidx.appcompat.content.res.AppCompatResources.getDrawable(btnStartStop.getContext(), R.drawable.ic_play)));
            }
            if ((dirtyFlags & 0x340L) != 0) {

                    if (viewModel != null) {
                        // read viewModel.referenceRoute
                        viewModelReferenceRoute = viewModel.getReferenceRoute();
                    }
                    updateLiveDataRegistration(6, viewModelReferenceRoute);


                    if (viewModelReferenceRoute != null) {
                        // read viewModel.referenceRoute.getValue()
                        viewModelReferenceRouteGetValue = viewModelReferenceRoute.getValue();
                    }


                    // read viewModel.referenceRoute.getValue() == null
                    viewModelReferenceRouteJavaLangObjectNull = (viewModelReferenceRouteGetValue) == (null);
                if((dirtyFlags & 0x340L) != 0) {
                    if(viewModelReferenceRouteJavaLangObjectNull) {
                            dirtyFlags |= 0x8000L;
                    }
                    else {
                            dirtyFlags |= 0x4000L;
                    }
                }
            }
            if ((dirtyFlags & 0x380L) != 0) {

                    if (viewModel != null) {
                        // read viewModel.isPaused
                        viewModelIsPaused = viewModel.isPaused();
                    }
                    updateLiveDataRegistration(7, viewModelIsPaused);


                    if (viewModelIsPaused != null) {
                        // read viewModel.isPaused.getValue()
                        viewModelIsPausedGetValue = viewModelIsPaused.getValue();
                    }


                    // read androidx.databinding.ViewDataBinding.safeUnbox(viewModel.isPaused.getValue())
                    androidxDatabindingViewDataBindingSafeUnboxViewModelIsPausedGetValue = androidx.databinding.ViewDataBinding.safeUnbox(viewModelIsPausedGetValue);
                if((dirtyFlags & 0x380L) != 0) {
                    if(androidxDatabindingViewDataBindingSafeUnboxViewModelIsPausedGetValue) {
                            dirtyFlags |= 0x2000L;
                            dirtyFlags |= 0x80000L;
                    }
                    else {
                            dirtyFlags |= 0x1000L;
                            dirtyFlags |= 0x40000L;
                    }
                }


                    // read androidx.databinding.ViewDataBinding.safeUnbox(viewModel.isPaused.getValue()) ? @android:drawable/ic_play : @android:drawable/ic_pause
                    viewModelIsPausedBtnPauseResumeAndroidDrawableIcPlayBtnPauseResumeAndroidDrawableIcPause = ((androidxDatabindingViewDataBindingSafeUnboxViewModelIsPausedGetValue) ? (androidx.appcompat.content.res.AppCompatResources.getDrawable(btnPauseResume.getContext(), R.drawable.ic_play)) : (androidx.appcompat.content.res.AppCompatResources.getDrawable(btnPauseResume.getContext(), R.drawable.ic_pause)));
                    // read androidx.databinding.ViewDataBinding.safeUnbox(viewModel.isPaused.getValue()) ? @android:string/route_resume : @android:string/route_pause
                    viewModelIsPausedBtnPauseResumeAndroidStringRouteResumeBtnPauseResumeAndroidStringRoutePause = ((androidxDatabindingViewDataBindingSafeUnboxViewModelIsPausedGetValue) ? (btnPauseResume.getResources().getString(R.string.route_resume)) : (btnPauseResume.getResources().getString(R.string.route_pause)));
            }
        }
        // batch finished

        if ((dirtyFlags & 0x4000L) != 0) {

                if (viewModelReferenceRouteGetValue != null) {
                    // read viewModel.referenceRoute.getValue().isEmpty()
                    viewModelReferenceRouteIsEmpty = viewModelReferenceRouteGetValue.isEmpty();
                }
        }

        if ((dirtyFlags & 0x340L) != 0) {

                // read viewModel.referenceRoute.getValue() == null ? true : viewModel.referenceRoute.getValue().isEmpty()
                viewModelReferenceRouteJavaLangObjectNullBooleanTrueViewModelReferenceRouteIsEmpty = ((viewModelReferenceRouteJavaLangObjectNull) ? (true) : (viewModelReferenceRouteIsEmpty));
            if((dirtyFlags & 0x340L) != 0) {
                if(viewModelReferenceRouteJavaLangObjectNullBooleanTrueViewModelReferenceRouteIsEmpty) {
                        dirtyFlags |= 0x800000L;
                }
                else {
                        dirtyFlags |= 0x400000L;
                }
            }


                // read viewModel.referenceRoute.getValue() == null ? true : viewModel.referenceRoute.getValue().isEmpty() ? View.GONE : View.VISIBLE
                viewModelReferenceRouteJavaLangObjectNullBooleanTrueViewModelReferenceRouteIsEmptyViewGONEViewVISIBLE = ((viewModelReferenceRouteJavaLangObjectNullBooleanTrueViewModelReferenceRouteIsEmpty) ? (android.view.View.GONE) : (android.view.View.VISIBLE));
        }
        // batch finished
        if ((dirtyFlags & 0x380L) != 0) {
            // api target 1

            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.btnPauseResume, viewModelIsPausedBtnPauseResumeAndroidStringRouteResumeBtnPauseResumeAndroidStringRoutePause);
            this.btnPauseResume.setIcon(viewModelIsPausedBtnPauseResumeAndroidDrawableIcPlayBtnPauseResumeAndroidDrawableIcPause);
        }
        if ((dirtyFlags & 0x320L) != 0) {
            // api target 1

            this.btnPauseResume.setVisibility(viewModelIsRecordingAndroidViewViewVISIBLEAndroidViewViewGONE);
            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.btnStartStop, viewModelIsRecordingBtnStartStopAndroidStringRouteStopBtnStartStopAndroidStringRouteStart);
            this.btnStartStop.setIcon(viewModelIsRecordingBtnStartStopAndroidDrawableIcStopBtnStartStopAndroidDrawableIcPlay);
        }
        if ((dirtyFlags & 0x340L) != 0) {
            // api target 1

            this.chipReferenceRoute.setVisibility(viewModelReferenceRouteJavaLangObjectNullBooleanTrueViewModelReferenceRouteIsEmptyViewGONEViewVISIBLE);
        }
        if ((dirtyFlags & 0x304L) != 0) {
            // api target 1

            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.chipReferenceRoute, viewModelReferenceRouteNameGetValue);
        }
        if ((dirtyFlags & 0x301L) != 0) {
            // api target 1

            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.tvRouteDistance, stringFormatUtilsFormatDistanceKmViewModelCurrentDistance);
        }
        if ((dirtyFlags & 0x302L) != 0) {
            // api target 1

            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.tvRouteDuration, stringFormatUtilsFormatDurationViewModelElapsedTimeMs);
        }
        if ((dirtyFlags & 0x308L) != 0) {
            // api target 1

            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.tvRouteElevation, stringFormatUtilsFormatElevationViewModelCurrentElevation);
        }
        if ((dirtyFlags & 0x310L) != 0) {
            // api target 1

            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.tvRouteElevationGain, stringFormatUtilsFormatElevationGainViewModelElevationGain);
        }
    }
    // Listener Stub Implementations
    // callback impls
    // dirty flag
    private  long mDirtyFlags = 0xffffffffffffffffL;
    /* flag mapping
        flag 0 (0x1L): viewModel.currentDistance
        flag 1 (0x2L): viewModel.elapsedTimeMs
        flag 2 (0x3L): viewModel.referenceRouteName
        flag 3 (0x4L): viewModel.currentElevation
        flag 4 (0x5L): viewModel.elevationGain
        flag 5 (0x6L): viewModel.isRecording
        flag 6 (0x7L): viewModel.referenceRoute
        flag 7 (0x8L): viewModel.isPaused
        flag 8 (0x9L): viewModel
        flag 9 (0xaL): null
        flag 10 (0xbL): androidx.databinding.ViewDataBinding.safeUnbox(viewModel.isRecording.getValue()) ? @android:string/route_stop : @android:string/route_start
        flag 11 (0xcL): androidx.databinding.ViewDataBinding.safeUnbox(viewModel.isRecording.getValue()) ? @android:string/route_stop : @android:string/route_start
        flag 12 (0xdL): androidx.databinding.ViewDataBinding.safeUnbox(viewModel.isPaused.getValue()) ? @android:drawable/ic_play : @android:drawable/ic_pause
        flag 13 (0xeL): androidx.databinding.ViewDataBinding.safeUnbox(viewModel.isPaused.getValue()) ? @android:drawable/ic_play : @android:drawable/ic_pause
        flag 14 (0xfL): viewModel.referenceRoute.getValue() == null ? true : viewModel.referenceRoute.getValue().isEmpty()
        flag 15 (0x10L): viewModel.referenceRoute.getValue() == null ? true : viewModel.referenceRoute.getValue().isEmpty()
        flag 16 (0x11L): androidx.databinding.ViewDataBinding.safeUnbox(viewModel.isRecording.getValue()) ? android.view.View.VISIBLE : android.view.View.GONE
        flag 17 (0x12L): androidx.databinding.ViewDataBinding.safeUnbox(viewModel.isRecording.getValue()) ? android.view.View.VISIBLE : android.view.View.GONE
        flag 18 (0x13L): androidx.databinding.ViewDataBinding.safeUnbox(viewModel.isPaused.getValue()) ? @android:string/route_resume : @android:string/route_pause
        flag 19 (0x14L): androidx.databinding.ViewDataBinding.safeUnbox(viewModel.isPaused.getValue()) ? @android:string/route_resume : @android:string/route_pause
        flag 20 (0x15L): androidx.databinding.ViewDataBinding.safeUnbox(viewModel.isRecording.getValue()) ? @android:drawable/ic_stop : @android:drawable/ic_play
        flag 21 (0x16L): androidx.databinding.ViewDataBinding.safeUnbox(viewModel.isRecording.getValue()) ? @android:drawable/ic_stop : @android:drawable/ic_play
        flag 22 (0x17L): viewModel.referenceRoute.getValue() == null ? true : viewModel.referenceRoute.getValue().isEmpty() ? View.GONE : View.VISIBLE
        flag 23 (0x18L): viewModel.referenceRoute.getValue() == null ? true : viewModel.referenceRoute.getValue().isEmpty() ? View.GONE : View.VISIBLE
    flag mapping end*/
    //end
}