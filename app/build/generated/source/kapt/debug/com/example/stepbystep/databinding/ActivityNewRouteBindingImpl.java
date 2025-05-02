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
        super(bindingComponent, root, 11
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
                mDirtyFlags = 0x1000L;
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
            mDirtyFlags |= 0x800L;
        }
        notifyPropertyChanged(BR.viewModel);
        super.requestRebind();
    }

    @Override
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
            case 0 :
                return onChangeViewModelFormattedDistance((androidx.lifecycle.LiveData<java.lang.String>) object, fieldId);
            case 1 :
                return onChangeViewModelReferenceRouteVisible((androidx.lifecycle.LiveData<java.lang.Integer>) object, fieldId);
            case 2 :
                return onChangeViewModelPauseResumeButtonVisible((androidx.lifecycle.LiveData<java.lang.Integer>) object, fieldId);
            case 3 :
                return onChangeViewModelPauseResumeButtonText((androidx.lifecycle.LiveData<java.lang.Integer>) object, fieldId);
            case 4 :
                return onChangeViewModelFormattedTime((androidx.lifecycle.LiveData<java.lang.String>) object, fieldId);
            case 5 :
                return onChangeViewModelFormattedElevationGain((androidx.lifecycle.LiveData<java.lang.String>) object, fieldId);
            case 6 :
                return onChangeViewModelReferenceRouteName((androidx.lifecycle.LiveData<java.lang.String>) object, fieldId);
            case 7 :
                return onChangeViewModelFormattedElevation((androidx.lifecycle.LiveData<java.lang.String>) object, fieldId);
            case 8 :
                return onChangeViewModelStartStopButtonText((androidx.lifecycle.LiveData<java.lang.Integer>) object, fieldId);
            case 9 :
                return onChangeViewModelIsRecording((androidx.lifecycle.LiveData<java.lang.Boolean>) object, fieldId);
            case 10 :
                return onChangeViewModelIsPaused((androidx.lifecycle.LiveData<java.lang.Boolean>) object, fieldId);
        }
        return false;
    }
    private boolean onChangeViewModelFormattedDistance(androidx.lifecycle.LiveData<java.lang.String> ViewModelFormattedDistance, int fieldId) {
        if (fieldId == BR._all) {
            synchronized(this) {
                    mDirtyFlags |= 0x1L;
            }
            return true;
        }
        return false;
    }
    private boolean onChangeViewModelReferenceRouteVisible(androidx.lifecycle.LiveData<java.lang.Integer> ViewModelReferenceRouteVisible, int fieldId) {
        if (fieldId == BR._all) {
            synchronized(this) {
                    mDirtyFlags |= 0x2L;
            }
            return true;
        }
        return false;
    }
    private boolean onChangeViewModelPauseResumeButtonVisible(androidx.lifecycle.LiveData<java.lang.Integer> ViewModelPauseResumeButtonVisible, int fieldId) {
        if (fieldId == BR._all) {
            synchronized(this) {
                    mDirtyFlags |= 0x4L;
            }
            return true;
        }
        return false;
    }
    private boolean onChangeViewModelPauseResumeButtonText(androidx.lifecycle.LiveData<java.lang.Integer> ViewModelPauseResumeButtonText, int fieldId) {
        if (fieldId == BR._all) {
            synchronized(this) {
                    mDirtyFlags |= 0x8L;
            }
            return true;
        }
        return false;
    }
    private boolean onChangeViewModelFormattedTime(androidx.lifecycle.LiveData<java.lang.String> ViewModelFormattedTime, int fieldId) {
        if (fieldId == BR._all) {
            synchronized(this) {
                    mDirtyFlags |= 0x10L;
            }
            return true;
        }
        return false;
    }
    private boolean onChangeViewModelFormattedElevationGain(androidx.lifecycle.LiveData<java.lang.String> ViewModelFormattedElevationGain, int fieldId) {
        if (fieldId == BR._all) {
            synchronized(this) {
                    mDirtyFlags |= 0x20L;
            }
            return true;
        }
        return false;
    }
    private boolean onChangeViewModelReferenceRouteName(androidx.lifecycle.LiveData<java.lang.String> ViewModelReferenceRouteName, int fieldId) {
        if (fieldId == BR._all) {
            synchronized(this) {
                    mDirtyFlags |= 0x40L;
            }
            return true;
        }
        return false;
    }
    private boolean onChangeViewModelFormattedElevation(androidx.lifecycle.LiveData<java.lang.String> ViewModelFormattedElevation, int fieldId) {
        if (fieldId == BR._all) {
            synchronized(this) {
                    mDirtyFlags |= 0x80L;
            }
            return true;
        }
        return false;
    }
    private boolean onChangeViewModelStartStopButtonText(androidx.lifecycle.LiveData<java.lang.Integer> ViewModelStartStopButtonText, int fieldId) {
        if (fieldId == BR._all) {
            synchronized(this) {
                    mDirtyFlags |= 0x100L;
            }
            return true;
        }
        return false;
    }
    private boolean onChangeViewModelIsRecording(androidx.lifecycle.LiveData<java.lang.Boolean> ViewModelIsRecording, int fieldId) {
        if (fieldId == BR._all) {
            synchronized(this) {
                    mDirtyFlags |= 0x200L;
            }
            return true;
        }
        return false;
    }
    private boolean onChangeViewModelIsPaused(androidx.lifecycle.LiveData<java.lang.Boolean> ViewModelIsPaused, int fieldId) {
        if (fieldId == BR._all) {
            synchronized(this) {
                    mDirtyFlags |= 0x400L;
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
        java.lang.Integer viewModelPauseResumeButtonTextGetValue = null;
        androidx.lifecycle.LiveData<java.lang.String> viewModelFormattedDistance = null;
        java.lang.String viewModelFormattedDistanceGetValue = null;
        android.graphics.drawable.Drawable viewModelIsPausedBtnPauseResumeAndroidDrawableIcPlayBtnPauseResumeAndroidDrawableIcPause = null;
        java.lang.String viewModelReferenceRouteNameGetValue = null;
        java.lang.Boolean viewModelIsRecordingGetValue = null;
        java.lang.Boolean viewModelIsPausedGetValue = null;
        androidx.lifecycle.LiveData<java.lang.Integer> viewModelReferenceRouteVisible = null;
        int androidxDatabindingViewDataBindingSafeUnboxViewModelStartStopButtonTextGetValue = 0;
        androidx.lifecycle.LiveData<java.lang.Integer> viewModelPauseResumeButtonVisible = null;
        androidx.lifecycle.LiveData<java.lang.Integer> viewModelPauseResumeButtonText = null;
        boolean androidxDatabindingViewDataBindingSafeUnboxViewModelIsRecordingGetValue = false;
        androidx.lifecycle.LiveData<java.lang.String> viewModelFormattedTime = null;
        java.lang.String viewModelFormattedTimeGetValue = null;
        java.lang.String viewModelFormattedElevationGetValue = null;
        java.lang.String viewModelFormattedElevationGainGetValue = null;
        androidx.lifecycle.LiveData<java.lang.String> viewModelFormattedElevationGain = null;
        androidx.lifecycle.LiveData<java.lang.String> viewModelReferenceRouteName = null;
        java.lang.Integer viewModelPauseResumeButtonVisibleGetValue = null;
        androidx.lifecycle.LiveData<java.lang.String> viewModelFormattedElevation = null;
        androidx.lifecycle.LiveData<java.lang.Integer> viewModelStartStopButtonText = null;
        boolean androidxDatabindingViewDataBindingSafeUnboxViewModelIsPausedGetValue = false;
        java.lang.String contextGetStringViewModelStartStopButtonText = null;
        int androidxDatabindingViewDataBindingSafeUnboxViewModelPauseResumeButtonTextGetValue = 0;
        java.lang.Integer viewModelStartStopButtonTextGetValue = null;
        int androidxDatabindingViewDataBindingSafeUnboxViewModelReferenceRouteVisibleGetValue = 0;
        androidx.lifecycle.LiveData<java.lang.Boolean> viewModelIsRecording = null;
        android.graphics.drawable.Drawable viewModelIsRecordingBtnStartStopAndroidDrawableIcStopBtnStartStopAndroidDrawableIcPlay = null;
        java.lang.String contextGetStringViewModelPauseResumeButtonText = null;
        java.lang.Integer viewModelReferenceRouteVisibleGetValue = null;
        com.example.stepbystep.ui.newroute.NewRouteViewModel viewModel = mViewModel;
        androidx.lifecycle.LiveData<java.lang.Boolean> viewModelIsPaused = null;
        int androidxDatabindingViewDataBindingSafeUnboxViewModelPauseResumeButtonVisibleGetValue = 0;

        if ((dirtyFlags & 0x1fffL) != 0) {


            if ((dirtyFlags & 0x1801L) != 0) {

                    if (viewModel != null) {
                        // read viewModel.formattedDistance
                        viewModelFormattedDistance = viewModel.getFormattedDistance();
                    }
                    updateLiveDataRegistration(0, viewModelFormattedDistance);


                    if (viewModelFormattedDistance != null) {
                        // read viewModel.formattedDistance.getValue()
                        viewModelFormattedDistanceGetValue = viewModelFormattedDistance.getValue();
                    }
            }
            if ((dirtyFlags & 0x1802L) != 0) {

                    if (viewModel != null) {
                        // read viewModel.referenceRouteVisible
                        viewModelReferenceRouteVisible = viewModel.getReferenceRouteVisible();
                    }
                    updateLiveDataRegistration(1, viewModelReferenceRouteVisible);


                    if (viewModelReferenceRouteVisible != null) {
                        // read viewModel.referenceRouteVisible.getValue()
                        viewModelReferenceRouteVisibleGetValue = viewModelReferenceRouteVisible.getValue();
                    }


                    // read androidx.databinding.ViewDataBinding.safeUnbox(viewModel.referenceRouteVisible.getValue())
                    androidxDatabindingViewDataBindingSafeUnboxViewModelReferenceRouteVisibleGetValue = androidx.databinding.ViewDataBinding.safeUnbox(viewModelReferenceRouteVisibleGetValue);
            }
            if ((dirtyFlags & 0x1804L) != 0) {

                    if (viewModel != null) {
                        // read viewModel.pauseResumeButtonVisible
                        viewModelPauseResumeButtonVisible = viewModel.getPauseResumeButtonVisible();
                    }
                    updateLiveDataRegistration(2, viewModelPauseResumeButtonVisible);


                    if (viewModelPauseResumeButtonVisible != null) {
                        // read viewModel.pauseResumeButtonVisible.getValue()
                        viewModelPauseResumeButtonVisibleGetValue = viewModelPauseResumeButtonVisible.getValue();
                    }


                    // read androidx.databinding.ViewDataBinding.safeUnbox(viewModel.pauseResumeButtonVisible.getValue())
                    androidxDatabindingViewDataBindingSafeUnboxViewModelPauseResumeButtonVisibleGetValue = androidx.databinding.ViewDataBinding.safeUnbox(viewModelPauseResumeButtonVisibleGetValue);
            }
            if ((dirtyFlags & 0x1808L) != 0) {

                    if (viewModel != null) {
                        // read viewModel.pauseResumeButtonText
                        viewModelPauseResumeButtonText = viewModel.getPauseResumeButtonText();
                    }
                    updateLiveDataRegistration(3, viewModelPauseResumeButtonText);


                    if (viewModelPauseResumeButtonText != null) {
                        // read viewModel.pauseResumeButtonText.getValue()
                        viewModelPauseResumeButtonTextGetValue = viewModelPauseResumeButtonText.getValue();
                    }


                    // read androidx.databinding.ViewDataBinding.safeUnbox(viewModel.pauseResumeButtonText.getValue())
                    androidxDatabindingViewDataBindingSafeUnboxViewModelPauseResumeButtonTextGetValue = androidx.databinding.ViewDataBinding.safeUnbox(viewModelPauseResumeButtonTextGetValue);


                    // read context.getString(androidx.databinding.ViewDataBinding.safeUnbox(viewModel.pauseResumeButtonText.getValue()))
                    contextGetStringViewModelPauseResumeButtonText = getRoot().getContext().getString(androidxDatabindingViewDataBindingSafeUnboxViewModelPauseResumeButtonTextGetValue);
            }
            if ((dirtyFlags & 0x1810L) != 0) {

                    if (viewModel != null) {
                        // read viewModel.formattedTime
                        viewModelFormattedTime = viewModel.getFormattedTime();
                    }
                    updateLiveDataRegistration(4, viewModelFormattedTime);


                    if (viewModelFormattedTime != null) {
                        // read viewModel.formattedTime.getValue()
                        viewModelFormattedTimeGetValue = viewModelFormattedTime.getValue();
                    }
            }
            if ((dirtyFlags & 0x1820L) != 0) {

                    if (viewModel != null) {
                        // read viewModel.formattedElevationGain
                        viewModelFormattedElevationGain = viewModel.getFormattedElevationGain();
                    }
                    updateLiveDataRegistration(5, viewModelFormattedElevationGain);


                    if (viewModelFormattedElevationGain != null) {
                        // read viewModel.formattedElevationGain.getValue()
                        viewModelFormattedElevationGainGetValue = viewModelFormattedElevationGain.getValue();
                    }
            }
            if ((dirtyFlags & 0x1840L) != 0) {

                    if (viewModel != null) {
                        // read viewModel.referenceRouteName
                        viewModelReferenceRouteName = viewModel.getReferenceRouteName();
                    }
                    updateLiveDataRegistration(6, viewModelReferenceRouteName);


                    if (viewModelReferenceRouteName != null) {
                        // read viewModel.referenceRouteName.getValue()
                        viewModelReferenceRouteNameGetValue = viewModelReferenceRouteName.getValue();
                    }
            }
            if ((dirtyFlags & 0x1880L) != 0) {

                    if (viewModel != null) {
                        // read viewModel.formattedElevation
                        viewModelFormattedElevation = viewModel.getFormattedElevation();
                    }
                    updateLiveDataRegistration(7, viewModelFormattedElevation);


                    if (viewModelFormattedElevation != null) {
                        // read viewModel.formattedElevation.getValue()
                        viewModelFormattedElevationGetValue = viewModelFormattedElevation.getValue();
                    }
            }
            if ((dirtyFlags & 0x1900L) != 0) {

                    if (viewModel != null) {
                        // read viewModel.startStopButtonText
                        viewModelStartStopButtonText = viewModel.getStartStopButtonText();
                    }
                    updateLiveDataRegistration(8, viewModelStartStopButtonText);


                    if (viewModelStartStopButtonText != null) {
                        // read viewModel.startStopButtonText.getValue()
                        viewModelStartStopButtonTextGetValue = viewModelStartStopButtonText.getValue();
                    }


                    // read androidx.databinding.ViewDataBinding.safeUnbox(viewModel.startStopButtonText.getValue())
                    androidxDatabindingViewDataBindingSafeUnboxViewModelStartStopButtonTextGetValue = androidx.databinding.ViewDataBinding.safeUnbox(viewModelStartStopButtonTextGetValue);


                    // read context.getString(androidx.databinding.ViewDataBinding.safeUnbox(viewModel.startStopButtonText.getValue()))
                    contextGetStringViewModelStartStopButtonText = getRoot().getContext().getString(androidxDatabindingViewDataBindingSafeUnboxViewModelStartStopButtonTextGetValue);
            }
            if ((dirtyFlags & 0x1a00L) != 0) {

                    if (viewModel != null) {
                        // read viewModel.isRecording
                        viewModelIsRecording = viewModel.isRecording();
                    }
                    updateLiveDataRegistration(9, viewModelIsRecording);


                    if (viewModelIsRecording != null) {
                        // read viewModel.isRecording.getValue()
                        viewModelIsRecordingGetValue = viewModelIsRecording.getValue();
                    }


                    // read androidx.databinding.ViewDataBinding.safeUnbox(viewModel.isRecording.getValue())
                    androidxDatabindingViewDataBindingSafeUnboxViewModelIsRecordingGetValue = androidx.databinding.ViewDataBinding.safeUnbox(viewModelIsRecordingGetValue);
                if((dirtyFlags & 0x1a00L) != 0) {
                    if(androidxDatabindingViewDataBindingSafeUnboxViewModelIsRecordingGetValue) {
                            dirtyFlags |= 0x10000L;
                    }
                    else {
                            dirtyFlags |= 0x8000L;
                    }
                }


                    // read androidx.databinding.ViewDataBinding.safeUnbox(viewModel.isRecording.getValue()) ? @android:drawable/ic_stop : @android:drawable/ic_play
                    viewModelIsRecordingBtnStartStopAndroidDrawableIcStopBtnStartStopAndroidDrawableIcPlay = ((androidxDatabindingViewDataBindingSafeUnboxViewModelIsRecordingGetValue) ? (androidx.appcompat.content.res.AppCompatResources.getDrawable(btnStartStop.getContext(), R.drawable.ic_stop)) : (androidx.appcompat.content.res.AppCompatResources.getDrawable(btnStartStop.getContext(), R.drawable.ic_play)));
            }
            if ((dirtyFlags & 0x1c00L) != 0) {

                    if (viewModel != null) {
                        // read viewModel.isPaused
                        viewModelIsPaused = viewModel.isPaused();
                    }
                    updateLiveDataRegistration(10, viewModelIsPaused);


                    if (viewModelIsPaused != null) {
                        // read viewModel.isPaused.getValue()
                        viewModelIsPausedGetValue = viewModelIsPaused.getValue();
                    }


                    // read androidx.databinding.ViewDataBinding.safeUnbox(viewModel.isPaused.getValue())
                    androidxDatabindingViewDataBindingSafeUnboxViewModelIsPausedGetValue = androidx.databinding.ViewDataBinding.safeUnbox(viewModelIsPausedGetValue);
                if((dirtyFlags & 0x1c00L) != 0) {
                    if(androidxDatabindingViewDataBindingSafeUnboxViewModelIsPausedGetValue) {
                            dirtyFlags |= 0x4000L;
                    }
                    else {
                            dirtyFlags |= 0x2000L;
                    }
                }


                    // read androidx.databinding.ViewDataBinding.safeUnbox(viewModel.isPaused.getValue()) ? @android:drawable/ic_play : @android:drawable/ic_pause
                    viewModelIsPausedBtnPauseResumeAndroidDrawableIcPlayBtnPauseResumeAndroidDrawableIcPause = ((androidxDatabindingViewDataBindingSafeUnboxViewModelIsPausedGetValue) ? (androidx.appcompat.content.res.AppCompatResources.getDrawable(btnPauseResume.getContext(), R.drawable.ic_play)) : (androidx.appcompat.content.res.AppCompatResources.getDrawable(btnPauseResume.getContext(), R.drawable.ic_pause)));
            }
        }
        // batch finished
        if ((dirtyFlags & 0x1808L) != 0) {
            // api target 1

            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.btnPauseResume, contextGetStringViewModelPauseResumeButtonText);
        }
        if ((dirtyFlags & 0x1804L) != 0) {
            // api target 1

            this.btnPauseResume.setVisibility(androidxDatabindingViewDataBindingSafeUnboxViewModelPauseResumeButtonVisibleGetValue);
        }
        if ((dirtyFlags & 0x1c00L) != 0) {
            // api target 1

            this.btnPauseResume.setIcon(viewModelIsPausedBtnPauseResumeAndroidDrawableIcPlayBtnPauseResumeAndroidDrawableIcPause);
        }
        if ((dirtyFlags & 0x1900L) != 0) {
            // api target 1

            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.btnStartStop, contextGetStringViewModelStartStopButtonText);
        }
        if ((dirtyFlags & 0x1a00L) != 0) {
            // api target 1

            this.btnStartStop.setIcon(viewModelIsRecordingBtnStartStopAndroidDrawableIcStopBtnStartStopAndroidDrawableIcPlay);
        }
        if ((dirtyFlags & 0x1802L) != 0) {
            // api target 1

            this.chipReferenceRoute.setVisibility(androidxDatabindingViewDataBindingSafeUnboxViewModelReferenceRouteVisibleGetValue);
        }
        if ((dirtyFlags & 0x1840L) != 0) {
            // api target 1

            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.chipReferenceRoute, viewModelReferenceRouteNameGetValue);
        }
        if ((dirtyFlags & 0x1801L) != 0) {
            // api target 1

            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.tvRouteDistance, viewModelFormattedDistanceGetValue);
        }
        if ((dirtyFlags & 0x1810L) != 0) {
            // api target 1

            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.tvRouteDuration, viewModelFormattedTimeGetValue);
        }
        if ((dirtyFlags & 0x1880L) != 0) {
            // api target 1

            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.tvRouteElevation, viewModelFormattedElevationGetValue);
        }
        if ((dirtyFlags & 0x1820L) != 0) {
            // api target 1

            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.tvRouteElevationGain, viewModelFormattedElevationGainGetValue);
        }
    }
    // Listener Stub Implementations
    // callback impls
    // dirty flag
    private  long mDirtyFlags = 0xffffffffffffffffL;
    /* flag mapping
        flag 0 (0x1L): viewModel.formattedDistance
        flag 1 (0x2L): viewModel.referenceRouteVisible
        flag 2 (0x3L): viewModel.pauseResumeButtonVisible
        flag 3 (0x4L): viewModel.pauseResumeButtonText
        flag 4 (0x5L): viewModel.formattedTime
        flag 5 (0x6L): viewModel.formattedElevationGain
        flag 6 (0x7L): viewModel.referenceRouteName
        flag 7 (0x8L): viewModel.formattedElevation
        flag 8 (0x9L): viewModel.startStopButtonText
        flag 9 (0xaL): viewModel.isRecording
        flag 10 (0xbL): viewModel.isPaused
        flag 11 (0xcL): viewModel
        flag 12 (0xdL): null
        flag 13 (0xeL): androidx.databinding.ViewDataBinding.safeUnbox(viewModel.isPaused.getValue()) ? @android:drawable/ic_play : @android:drawable/ic_pause
        flag 14 (0xfL): androidx.databinding.ViewDataBinding.safeUnbox(viewModel.isPaused.getValue()) ? @android:drawable/ic_play : @android:drawable/ic_pause
        flag 15 (0x10L): androidx.databinding.ViewDataBinding.safeUnbox(viewModel.isRecording.getValue()) ? @android:drawable/ic_stop : @android:drawable/ic_play
        flag 16 (0x11L): androidx.databinding.ViewDataBinding.safeUnbox(viewModel.isRecording.getValue()) ? @android:drawable/ic_stop : @android:drawable/ic_play
    flag mapping end*/
    //end
}