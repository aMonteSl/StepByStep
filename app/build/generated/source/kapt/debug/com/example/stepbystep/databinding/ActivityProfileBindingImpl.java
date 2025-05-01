package com.example.stepbystep.databinding;
import com.example.stepbystep.R;
import com.example.stepbystep.BR;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class ActivityProfileBindingImpl extends ActivityProfileBinding  {

    @Nullable
    private static final androidx.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.appBarLayout, 11);
        sViewsWithIds.put(R.id.toolbar, 12);
        sViewsWithIds.put(R.id.btnEditProfile, 13);
    }
    // views
    @NonNull
    private final androidx.coordinatorlayout.widget.CoordinatorLayout mboundView0;
    @NonNull
    private final android.widget.TextView mboundView10;
    @NonNull
    private final android.widget.TextView mboundView3;
    @NonNull
    private final android.widget.TextView mboundView4;
    @NonNull
    private final android.widget.TextView mboundView5;
    @NonNull
    private final android.widget.TextView mboundView6;
    @NonNull
    private final android.widget.TextView mboundView7;
    @NonNull
    private final android.widget.TextView mboundView8;
    @NonNull
    private final android.widget.TextView mboundView9;
    // variables
    // values
    // listeners
    // Inverse Binding Event Handlers

    public ActivityProfileBindingImpl(@Nullable androidx.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 14, sIncludes, sViewsWithIds));
    }
    private ActivityProfileBindingImpl(androidx.databinding.DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 11
            , (com.google.android.material.appbar.AppBarLayout) bindings[11]
            , (com.google.android.material.button.MaterialButton) bindings[13]
            , (android.widget.EditText) bindings[2]
            , (android.widget.EditText) bindings[1]
            , (com.google.android.material.appbar.MaterialToolbar) bindings[12]
            );
        this.etAthleteDescription.setTag(null);
        this.etAthleteName.setTag(null);
        this.mboundView0 = (androidx.coordinatorlayout.widget.CoordinatorLayout) bindings[0];
        this.mboundView0.setTag(null);
        this.mboundView10 = (android.widget.TextView) bindings[10];
        this.mboundView10.setTag(null);
        this.mboundView3 = (android.widget.TextView) bindings[3];
        this.mboundView3.setTag(null);
        this.mboundView4 = (android.widget.TextView) bindings[4];
        this.mboundView4.setTag(null);
        this.mboundView5 = (android.widget.TextView) bindings[5];
        this.mboundView5.setTag(null);
        this.mboundView6 = (android.widget.TextView) bindings[6];
        this.mboundView6.setTag(null);
        this.mboundView7 = (android.widget.TextView) bindings[7];
        this.mboundView7.setTag(null);
        this.mboundView8 = (android.widget.TextView) bindings[8];
        this.mboundView8.setTag(null);
        this.mboundView9 = (android.widget.TextView) bindings[9];
        this.mboundView9.setTag(null);
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
            setViewModel((com.example.stepbystep.ui.profile.ProfileViewModel) variable);
        }
        else {
            variableSet = false;
        }
            return variableSet;
    }

    public void setViewModel(@Nullable com.example.stepbystep.ui.profile.ProfileViewModel ViewModel) {
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
                return onChangeViewModelAthleteDescription((androidx.lifecycle.LiveData<java.lang.String>) object, fieldId);
            case 1 :
                return onChangeViewModelTotalDuration((androidx.lifecycle.LiveData<java.lang.String>) object, fieldId);
            case 2 :
                return onChangeViewModelFirstActivityDate((androidx.lifecycle.LiveData<java.lang.String>) object, fieldId);
            case 3 :
                return onChangeViewModelAthleteName((androidx.lifecycle.LiveData<java.lang.String>) object, fieldId);
            case 4 :
                return onChangeViewModelTotalActivities((androidx.lifecycle.LiveData<java.lang.Integer>) object, fieldId);
            case 5 :
                return onChangeViewModelTotalDistance((androidx.lifecycle.LiveData<java.lang.String>) object, fieldId);
            case 6 :
                return onChangeViewModelAverageDistance((androidx.lifecycle.LiveData<java.lang.String>) object, fieldId);
            case 7 :
                return onChangeViewModelAveragePace((androidx.lifecycle.LiveData<java.lang.String>) object, fieldId);
            case 8 :
                return onChangeViewModelMaxDistance((androidx.lifecycle.LiveData<java.lang.String>) object, fieldId);
            case 9 :
                return onChangeViewModelIsEditMode((androidx.lifecycle.LiveData<java.lang.Boolean>) object, fieldId);
            case 10 :
                return onChangeViewModelTotalElevationGain((androidx.lifecycle.LiveData<java.lang.String>) object, fieldId);
        }
        return false;
    }
    private boolean onChangeViewModelAthleteDescription(androidx.lifecycle.LiveData<java.lang.String> ViewModelAthleteDescription, int fieldId) {
        if (fieldId == BR._all) {
            synchronized(this) {
                    mDirtyFlags |= 0x1L;
            }
            return true;
        }
        return false;
    }
    private boolean onChangeViewModelTotalDuration(androidx.lifecycle.LiveData<java.lang.String> ViewModelTotalDuration, int fieldId) {
        if (fieldId == BR._all) {
            synchronized(this) {
                    mDirtyFlags |= 0x2L;
            }
            return true;
        }
        return false;
    }
    private boolean onChangeViewModelFirstActivityDate(androidx.lifecycle.LiveData<java.lang.String> ViewModelFirstActivityDate, int fieldId) {
        if (fieldId == BR._all) {
            synchronized(this) {
                    mDirtyFlags |= 0x4L;
            }
            return true;
        }
        return false;
    }
    private boolean onChangeViewModelAthleteName(androidx.lifecycle.LiveData<java.lang.String> ViewModelAthleteName, int fieldId) {
        if (fieldId == BR._all) {
            synchronized(this) {
                    mDirtyFlags |= 0x8L;
            }
            return true;
        }
        return false;
    }
    private boolean onChangeViewModelTotalActivities(androidx.lifecycle.LiveData<java.lang.Integer> ViewModelTotalActivities, int fieldId) {
        if (fieldId == BR._all) {
            synchronized(this) {
                    mDirtyFlags |= 0x10L;
            }
            return true;
        }
        return false;
    }
    private boolean onChangeViewModelTotalDistance(androidx.lifecycle.LiveData<java.lang.String> ViewModelTotalDistance, int fieldId) {
        if (fieldId == BR._all) {
            synchronized(this) {
                    mDirtyFlags |= 0x20L;
            }
            return true;
        }
        return false;
    }
    private boolean onChangeViewModelAverageDistance(androidx.lifecycle.LiveData<java.lang.String> ViewModelAverageDistance, int fieldId) {
        if (fieldId == BR._all) {
            synchronized(this) {
                    mDirtyFlags |= 0x40L;
            }
            return true;
        }
        return false;
    }
    private boolean onChangeViewModelAveragePace(androidx.lifecycle.LiveData<java.lang.String> ViewModelAveragePace, int fieldId) {
        if (fieldId == BR._all) {
            synchronized(this) {
                    mDirtyFlags |= 0x80L;
            }
            return true;
        }
        return false;
    }
    private boolean onChangeViewModelMaxDistance(androidx.lifecycle.LiveData<java.lang.String> ViewModelMaxDistance, int fieldId) {
        if (fieldId == BR._all) {
            synchronized(this) {
                    mDirtyFlags |= 0x100L;
            }
            return true;
        }
        return false;
    }
    private boolean onChangeViewModelIsEditMode(androidx.lifecycle.LiveData<java.lang.Boolean> ViewModelIsEditMode, int fieldId) {
        if (fieldId == BR._all) {
            synchronized(this) {
                    mDirtyFlags |= 0x200L;
            }
            return true;
        }
        return false;
    }
    private boolean onChangeViewModelTotalElevationGain(androidx.lifecycle.LiveData<java.lang.String> ViewModelTotalElevationGain, int fieldId) {
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
        java.lang.String viewModelTotalElevationGainGetValue = null;
        androidx.lifecycle.LiveData<java.lang.String> viewModelAthleteDescription = null;
        androidx.lifecycle.LiveData<java.lang.String> viewModelTotalDuration = null;
        int viewModelIsEditModeEtAthleteNameAndroidColorTransparentEtAthleteNameAndroidColorTransparent = 0;
        androidx.lifecycle.LiveData<java.lang.String> viewModelFirstActivityDate = null;
        java.lang.String viewModelFirstActivityDateGetValue = null;
        androidx.lifecycle.LiveData<java.lang.String> viewModelAthleteName = null;
        androidx.lifecycle.LiveData<java.lang.Integer> viewModelTotalActivities = null;
        java.lang.String viewModelAthleteDescriptionGetValue = null;
        androidx.lifecycle.LiveData<java.lang.String> viewModelTotalDistance = null;
        androidx.lifecycle.LiveData<java.lang.String> viewModelAverageDistance = null;
        androidx.lifecycle.LiveData<java.lang.String> viewModelAveragePace = null;
        java.lang.String viewModelAveragePaceGetValue = null;
        boolean androidxDatabindingViewDataBindingSafeUnboxViewModelIsEditModeGetValue = false;
        int viewModelIsEditModeEtAthleteDescriptionAndroidColorTransparentEtAthleteDescriptionAndroidColorTransparent = 0;
        java.lang.String viewModelTotalDistanceGetValue = null;
        java.lang.Boolean viewModelIsEditModeGetValue = null;
        java.lang.String viewModelAthleteNameGetValue = null;
        androidx.lifecycle.LiveData<java.lang.String> viewModelMaxDistance = null;
        androidx.lifecycle.LiveData<java.lang.Boolean> viewModelIsEditMode = null;
        java.lang.String mboundView3AndroidStringTotalActivitiesViewModelTotalActivities = null;
        java.lang.String viewModelAverageDistanceGetValue = null;
        java.lang.String mboundView4AndroidStringActiveSinceViewModelFirstActivityDate = null;
        java.lang.Integer viewModelTotalActivitiesGetValue = null;
        java.lang.String viewModelMaxDistanceGetValue = null;
        com.example.stepbystep.ui.profile.ProfileViewModel viewModel = mViewModel;
        androidx.lifecycle.LiveData<java.lang.String> viewModelTotalElevationGain = null;
        java.lang.String viewModelTotalDurationGetValue = null;

        if ((dirtyFlags & 0x1fffL) != 0) {


            if ((dirtyFlags & 0x1801L) != 0) {

                    if (viewModel != null) {
                        // read viewModel.athleteDescription
                        viewModelAthleteDescription = viewModel.getAthleteDescription();
                    }
                    updateLiveDataRegistration(0, viewModelAthleteDescription);


                    if (viewModelAthleteDescription != null) {
                        // read viewModel.athleteDescription.getValue()
                        viewModelAthleteDescriptionGetValue = viewModelAthleteDescription.getValue();
                    }
            }
            if ((dirtyFlags & 0x1802L) != 0) {

                    if (viewModel != null) {
                        // read viewModel.totalDuration
                        viewModelTotalDuration = viewModel.getTotalDuration();
                    }
                    updateLiveDataRegistration(1, viewModelTotalDuration);


                    if (viewModelTotalDuration != null) {
                        // read viewModel.totalDuration.getValue()
                        viewModelTotalDurationGetValue = viewModelTotalDuration.getValue();
                    }
            }
            if ((dirtyFlags & 0x1804L) != 0) {

                    if (viewModel != null) {
                        // read viewModel.firstActivityDate
                        viewModelFirstActivityDate = viewModel.getFirstActivityDate();
                    }
                    updateLiveDataRegistration(2, viewModelFirstActivityDate);


                    if (viewModelFirstActivityDate != null) {
                        // read viewModel.firstActivityDate.getValue()
                        viewModelFirstActivityDateGetValue = viewModelFirstActivityDate.getValue();
                    }


                    // read @android:string/active_since
                    mboundView4AndroidStringActiveSinceViewModelFirstActivityDate = mboundView4.getResources().getString(R.string.active_since, viewModelFirstActivityDateGetValue);
            }
            if ((dirtyFlags & 0x1808L) != 0) {

                    if (viewModel != null) {
                        // read viewModel.athleteName
                        viewModelAthleteName = viewModel.getAthleteName();
                    }
                    updateLiveDataRegistration(3, viewModelAthleteName);


                    if (viewModelAthleteName != null) {
                        // read viewModel.athleteName.getValue()
                        viewModelAthleteNameGetValue = viewModelAthleteName.getValue();
                    }
            }
            if ((dirtyFlags & 0x1810L) != 0) {

                    if (viewModel != null) {
                        // read viewModel.totalActivities
                        viewModelTotalActivities = viewModel.getTotalActivities();
                    }
                    updateLiveDataRegistration(4, viewModelTotalActivities);


                    if (viewModelTotalActivities != null) {
                        // read viewModel.totalActivities.getValue()
                        viewModelTotalActivitiesGetValue = viewModelTotalActivities.getValue();
                    }


                    // read @android:string/total_activities
                    mboundView3AndroidStringTotalActivitiesViewModelTotalActivities = mboundView3.getResources().getString(R.string.total_activities, viewModelTotalActivitiesGetValue);
            }
            if ((dirtyFlags & 0x1820L) != 0) {

                    if (viewModel != null) {
                        // read viewModel.totalDistance
                        viewModelTotalDistance = viewModel.getTotalDistance();
                    }
                    updateLiveDataRegistration(5, viewModelTotalDistance);


                    if (viewModelTotalDistance != null) {
                        // read viewModel.totalDistance.getValue()
                        viewModelTotalDistanceGetValue = viewModelTotalDistance.getValue();
                    }
            }
            if ((dirtyFlags & 0x1840L) != 0) {

                    if (viewModel != null) {
                        // read viewModel.averageDistance
                        viewModelAverageDistance = viewModel.getAverageDistance();
                    }
                    updateLiveDataRegistration(6, viewModelAverageDistance);


                    if (viewModelAverageDistance != null) {
                        // read viewModel.averageDistance.getValue()
                        viewModelAverageDistanceGetValue = viewModelAverageDistance.getValue();
                    }
            }
            if ((dirtyFlags & 0x1880L) != 0) {

                    if (viewModel != null) {
                        // read viewModel.averagePace
                        viewModelAveragePace = viewModel.getAveragePace();
                    }
                    updateLiveDataRegistration(7, viewModelAveragePace);


                    if (viewModelAveragePace != null) {
                        // read viewModel.averagePace.getValue()
                        viewModelAveragePaceGetValue = viewModelAveragePace.getValue();
                    }
            }
            if ((dirtyFlags & 0x1900L) != 0) {

                    if (viewModel != null) {
                        // read viewModel.maxDistance
                        viewModelMaxDistance = viewModel.getMaxDistance();
                    }
                    updateLiveDataRegistration(8, viewModelMaxDistance);


                    if (viewModelMaxDistance != null) {
                        // read viewModel.maxDistance.getValue()
                        viewModelMaxDistanceGetValue = viewModelMaxDistance.getValue();
                    }
            }
            if ((dirtyFlags & 0x1a00L) != 0) {

                    if (viewModel != null) {
                        // read viewModel.isEditMode
                        viewModelIsEditMode = viewModel.isEditMode();
                    }
                    updateLiveDataRegistration(9, viewModelIsEditMode);


                    if (viewModelIsEditMode != null) {
                        // read viewModel.isEditMode.getValue()
                        viewModelIsEditModeGetValue = viewModelIsEditMode.getValue();
                    }


                    // read androidx.databinding.ViewDataBinding.safeUnbox(viewModel.isEditMode.getValue())
                    androidxDatabindingViewDataBindingSafeUnboxViewModelIsEditModeGetValue = androidx.databinding.ViewDataBinding.safeUnbox(viewModelIsEditModeGetValue);
                if((dirtyFlags & 0x1a00L) != 0) {
                    if(androidxDatabindingViewDataBindingSafeUnboxViewModelIsEditModeGetValue) {
                            dirtyFlags |= 0x4000L;
                            dirtyFlags |= 0x10000L;
                    }
                    else {
                            dirtyFlags |= 0x2000L;
                            dirtyFlags |= 0x8000L;
                    }
                }


                    // read androidx.databinding.ViewDataBinding.safeUnbox(viewModel.isEditMode.getValue()) ? @android:color/transparent : @android:color/transparent
                    viewModelIsEditModeEtAthleteNameAndroidColorTransparentEtAthleteNameAndroidColorTransparent = ((androidxDatabindingViewDataBindingSafeUnboxViewModelIsEditModeGetValue) ? (getColorFromResource(etAthleteName, android.R.color.transparent)) : (getColorFromResource(etAthleteName, android.R.color.transparent)));
                    // read androidx.databinding.ViewDataBinding.safeUnbox(viewModel.isEditMode.getValue()) ? @android:color/transparent : @android:color/transparent
                    viewModelIsEditModeEtAthleteDescriptionAndroidColorTransparentEtAthleteDescriptionAndroidColorTransparent = ((androidxDatabindingViewDataBindingSafeUnboxViewModelIsEditModeGetValue) ? (getColorFromResource(etAthleteDescription, android.R.color.transparent)) : (getColorFromResource(etAthleteDescription, android.R.color.transparent)));
            }
            if ((dirtyFlags & 0x1c00L) != 0) {

                    if (viewModel != null) {
                        // read viewModel.totalElevationGain
                        viewModelTotalElevationGain = viewModel.getTotalElevationGain();
                    }
                    updateLiveDataRegistration(10, viewModelTotalElevationGain);


                    if (viewModelTotalElevationGain != null) {
                        // read viewModel.totalElevationGain.getValue()
                        viewModelTotalElevationGainGetValue = viewModelTotalElevationGain.getValue();
                    }
            }
        }
        // batch finished
        if ((dirtyFlags & 0x1801L) != 0) {
            // api target 1

            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.etAthleteDescription, viewModelAthleteDescriptionGetValue);
        }
        if ((dirtyFlags & 0x1a00L) != 0) {
            // api target 1

            this.etAthleteDescription.setEnabled(androidxDatabindingViewDataBindingSafeUnboxViewModelIsEditModeGetValue);
            androidx.databinding.adapters.ViewBindingAdapter.setBackground(this.etAthleteDescription, androidx.databinding.adapters.Converters.convertColorToDrawable(viewModelIsEditModeEtAthleteDescriptionAndroidColorTransparentEtAthleteDescriptionAndroidColorTransparent));
            this.etAthleteName.setEnabled(androidxDatabindingViewDataBindingSafeUnboxViewModelIsEditModeGetValue);
            androidx.databinding.adapters.ViewBindingAdapter.setBackground(this.etAthleteName, androidx.databinding.adapters.Converters.convertColorToDrawable(viewModelIsEditModeEtAthleteNameAndroidColorTransparentEtAthleteNameAndroidColorTransparent));
        }
        if ((dirtyFlags & 0x1808L) != 0) {
            // api target 1

            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.etAthleteName, viewModelAthleteNameGetValue);
        }
        if ((dirtyFlags & 0x1c00L) != 0) {
            // api target 1

            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.mboundView10, viewModelTotalElevationGainGetValue);
        }
        if ((dirtyFlags & 0x1810L) != 0) {
            // api target 1

            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.mboundView3, mboundView3AndroidStringTotalActivitiesViewModelTotalActivities);
        }
        if ((dirtyFlags & 0x1804L) != 0) {
            // api target 1

            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.mboundView4, mboundView4AndroidStringActiveSinceViewModelFirstActivityDate);
        }
        if ((dirtyFlags & 0x1820L) != 0) {
            // api target 1

            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.mboundView5, viewModelTotalDistanceGetValue);
        }
        if ((dirtyFlags & 0x1840L) != 0) {
            // api target 1

            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.mboundView6, viewModelAverageDistanceGetValue);
        }
        if ((dirtyFlags & 0x1900L) != 0) {
            // api target 1

            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.mboundView7, viewModelMaxDistanceGetValue);
        }
        if ((dirtyFlags & 0x1802L) != 0) {
            // api target 1

            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.mboundView8, viewModelTotalDurationGetValue);
        }
        if ((dirtyFlags & 0x1880L) != 0) {
            // api target 1

            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.mboundView9, viewModelAveragePaceGetValue);
        }
    }
    // Listener Stub Implementations
    // callback impls
    // dirty flag
    private  long mDirtyFlags = 0xffffffffffffffffL;
    /* flag mapping
        flag 0 (0x1L): viewModel.athleteDescription
        flag 1 (0x2L): viewModel.totalDuration
        flag 2 (0x3L): viewModel.firstActivityDate
        flag 3 (0x4L): viewModel.athleteName
        flag 4 (0x5L): viewModel.totalActivities
        flag 5 (0x6L): viewModel.totalDistance
        flag 6 (0x7L): viewModel.averageDistance
        flag 7 (0x8L): viewModel.averagePace
        flag 8 (0x9L): viewModel.maxDistance
        flag 9 (0xaL): viewModel.isEditMode
        flag 10 (0xbL): viewModel.totalElevationGain
        flag 11 (0xcL): viewModel
        flag 12 (0xdL): null
        flag 13 (0xeL): androidx.databinding.ViewDataBinding.safeUnbox(viewModel.isEditMode.getValue()) ? @android:color/transparent : @android:color/transparent
        flag 14 (0xfL): androidx.databinding.ViewDataBinding.safeUnbox(viewModel.isEditMode.getValue()) ? @android:color/transparent : @android:color/transparent
        flag 15 (0x10L): androidx.databinding.ViewDataBinding.safeUnbox(viewModel.isEditMode.getValue()) ? @android:color/transparent : @android:color/transparent
        flag 16 (0x11L): androidx.databinding.ViewDataBinding.safeUnbox(viewModel.isEditMode.getValue()) ? @android:color/transparent : @android:color/transparent
    flag mapping end*/
    //end
}