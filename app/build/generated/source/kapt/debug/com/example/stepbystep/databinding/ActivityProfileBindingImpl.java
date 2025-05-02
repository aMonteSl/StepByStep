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
        sViewsWithIds.put(R.id.appBarLayout, 12);
        sViewsWithIds.put(R.id.toolbar, 13);
    }
    // views
    @NonNull
    private final androidx.coordinatorlayout.widget.CoordinatorLayout mboundView0;
    @NonNull
    private final android.widget.TextView mboundView10;
    @NonNull
    private final android.widget.TextView mboundView11;
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
        super(bindingComponent, root, 13
            , (com.google.android.material.appbar.AppBarLayout) bindings[12]
            , (com.google.android.material.button.MaterialButton) bindings[3]
            , (android.widget.EditText) bindings[2]
            , (android.widget.EditText) bindings[1]
            , (com.google.android.material.appbar.MaterialToolbar) bindings[13]
            );
        this.btnEditProfile.setTag(null);
        this.etAthleteDescription.setTag(null);
        this.etAthleteName.setTag(null);
        this.mboundView0 = (androidx.coordinatorlayout.widget.CoordinatorLayout) bindings[0];
        this.mboundView0.setTag(null);
        this.mboundView10 = (android.widget.TextView) bindings[10];
        this.mboundView10.setTag(null);
        this.mboundView11 = (android.widget.TextView) bindings[11];
        this.mboundView11.setTag(null);
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
                mDirtyFlags = 0x4000L;
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
            mDirtyFlags |= 0x2000L;
        }
        notifyPropertyChanged(BR.viewModel);
        super.requestRebind();
    }

    @Override
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
            case 0 :
                return onChangeViewModelEditButtonText((androidx.lifecycle.LiveData<java.lang.Integer>) object, fieldId);
            case 1 :
                return onChangeViewModelAthleteDescription((androidx.lifecycle.LiveData<java.lang.String>) object, fieldId);
            case 2 :
                return onChangeViewModelEditButtonIcon((androidx.lifecycle.LiveData<java.lang.Integer>) object, fieldId);
            case 3 :
                return onChangeViewModelTotalDuration((androidx.lifecycle.LiveData<java.lang.String>) object, fieldId);
            case 4 :
                return onChangeViewModelAthleteName((androidx.lifecycle.LiveData<java.lang.String>) object, fieldId);
            case 5 :
                return onChangeViewModelFormattedActiveDate((androidx.lifecycle.LiveData<java.lang.String>) object, fieldId);
            case 6 :
                return onChangeViewModelTotalDistance((androidx.lifecycle.LiveData<java.lang.String>) object, fieldId);
            case 7 :
                return onChangeViewModelAverageDistance((androidx.lifecycle.LiveData<java.lang.String>) object, fieldId);
            case 8 :
                return onChangeViewModelAveragePace((androidx.lifecycle.LiveData<java.lang.String>) object, fieldId);
            case 9 :
                return onChangeViewModelMaxDistance((androidx.lifecycle.LiveData<java.lang.String>) object, fieldId);
            case 10 :
                return onChangeViewModelIsEditMode((androidx.lifecycle.LiveData<java.lang.Boolean>) object, fieldId);
            case 11 :
                return onChangeViewModelTotalElevationGain((androidx.lifecycle.LiveData<java.lang.String>) object, fieldId);
            case 12 :
                return onChangeViewModelFormattedTotalActivities((androidx.lifecycle.LiveData<java.lang.String>) object, fieldId);
        }
        return false;
    }
    private boolean onChangeViewModelEditButtonText(androidx.lifecycle.LiveData<java.lang.Integer> ViewModelEditButtonText, int fieldId) {
        if (fieldId == BR._all) {
            synchronized(this) {
                    mDirtyFlags |= 0x1L;
            }
            return true;
        }
        return false;
    }
    private boolean onChangeViewModelAthleteDescription(androidx.lifecycle.LiveData<java.lang.String> ViewModelAthleteDescription, int fieldId) {
        if (fieldId == BR._all) {
            synchronized(this) {
                    mDirtyFlags |= 0x2L;
            }
            return true;
        }
        return false;
    }
    private boolean onChangeViewModelEditButtonIcon(androidx.lifecycle.LiveData<java.lang.Integer> ViewModelEditButtonIcon, int fieldId) {
        if (fieldId == BR._all) {
            synchronized(this) {
                    mDirtyFlags |= 0x4L;
            }
            return true;
        }
        return false;
    }
    private boolean onChangeViewModelTotalDuration(androidx.lifecycle.LiveData<java.lang.String> ViewModelTotalDuration, int fieldId) {
        if (fieldId == BR._all) {
            synchronized(this) {
                    mDirtyFlags |= 0x8L;
            }
            return true;
        }
        return false;
    }
    private boolean onChangeViewModelAthleteName(androidx.lifecycle.LiveData<java.lang.String> ViewModelAthleteName, int fieldId) {
        if (fieldId == BR._all) {
            synchronized(this) {
                    mDirtyFlags |= 0x10L;
            }
            return true;
        }
        return false;
    }
    private boolean onChangeViewModelFormattedActiveDate(androidx.lifecycle.LiveData<java.lang.String> ViewModelFormattedActiveDate, int fieldId) {
        if (fieldId == BR._all) {
            synchronized(this) {
                    mDirtyFlags |= 0x20L;
            }
            return true;
        }
        return false;
    }
    private boolean onChangeViewModelTotalDistance(androidx.lifecycle.LiveData<java.lang.String> ViewModelTotalDistance, int fieldId) {
        if (fieldId == BR._all) {
            synchronized(this) {
                    mDirtyFlags |= 0x40L;
            }
            return true;
        }
        return false;
    }
    private boolean onChangeViewModelAverageDistance(androidx.lifecycle.LiveData<java.lang.String> ViewModelAverageDistance, int fieldId) {
        if (fieldId == BR._all) {
            synchronized(this) {
                    mDirtyFlags |= 0x80L;
            }
            return true;
        }
        return false;
    }
    private boolean onChangeViewModelAveragePace(androidx.lifecycle.LiveData<java.lang.String> ViewModelAveragePace, int fieldId) {
        if (fieldId == BR._all) {
            synchronized(this) {
                    mDirtyFlags |= 0x100L;
            }
            return true;
        }
        return false;
    }
    private boolean onChangeViewModelMaxDistance(androidx.lifecycle.LiveData<java.lang.String> ViewModelMaxDistance, int fieldId) {
        if (fieldId == BR._all) {
            synchronized(this) {
                    mDirtyFlags |= 0x200L;
            }
            return true;
        }
        return false;
    }
    private boolean onChangeViewModelIsEditMode(androidx.lifecycle.LiveData<java.lang.Boolean> ViewModelIsEditMode, int fieldId) {
        if (fieldId == BR._all) {
            synchronized(this) {
                    mDirtyFlags |= 0x400L;
            }
            return true;
        }
        return false;
    }
    private boolean onChangeViewModelTotalElevationGain(androidx.lifecycle.LiveData<java.lang.String> ViewModelTotalElevationGain, int fieldId) {
        if (fieldId == BR._all) {
            synchronized(this) {
                    mDirtyFlags |= 0x800L;
            }
            return true;
        }
        return false;
    }
    private boolean onChangeViewModelFormattedTotalActivities(androidx.lifecycle.LiveData<java.lang.String> ViewModelFormattedTotalActivities, int fieldId) {
        if (fieldId == BR._all) {
            synchronized(this) {
                    mDirtyFlags |= 0x1000L;
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
        androidx.lifecycle.LiveData<java.lang.Integer> viewModelEditButtonText = null;
        java.lang.String viewModelTotalElevationGainGetValue = null;
        androidx.lifecycle.LiveData<java.lang.String> viewModelAthleteDescription = null;
        java.lang.Integer viewModelEditButtonIconGetValue = null;
        androidx.lifecycle.LiveData<java.lang.Integer> viewModelEditButtonIcon = null;
        androidx.lifecycle.LiveData<java.lang.String> viewModelTotalDuration = null;
        java.lang.String viewModelFormattedTotalActivitiesGetValue = null;
        androidx.lifecycle.LiveData<java.lang.String> viewModelAthleteName = null;
        androidx.lifecycle.LiveData<java.lang.String> viewModelFormattedActiveDate = null;
        java.lang.String viewModelAthleteDescriptionGetValue = null;
        androidx.lifecycle.LiveData<java.lang.String> viewModelTotalDistance = null;
        androidx.lifecycle.LiveData<java.lang.String> viewModelAverageDistance = null;
        androidx.lifecycle.LiveData<java.lang.String> viewModelAveragePace = null;
        java.lang.String viewModelFormattedActiveDateGetValue = null;
        java.lang.String viewModelAveragePaceGetValue = null;
        boolean androidxDatabindingViewDataBindingSafeUnboxViewModelIsEditModeGetValue = false;
        java.lang.Integer viewModelEditButtonTextGetValue = null;
        java.lang.String viewModelTotalDistanceGetValue = null;
        java.lang.Boolean viewModelIsEditModeGetValue = null;
        java.lang.String viewModelAthleteNameGetValue = null;
        java.lang.String contextGetStringViewModelEditButtonText = null;
        androidx.lifecycle.LiveData<java.lang.String> viewModelMaxDistance = null;
        androidx.lifecycle.LiveData<java.lang.Boolean> viewModelIsEditMode = null;
        java.lang.String viewModelAverageDistanceGetValue = null;
        java.lang.String viewModelMaxDistanceGetValue = null;
        int androidxDatabindingViewDataBindingSafeUnboxViewModelEditButtonTextGetValue = 0;
        com.example.stepbystep.ui.profile.ProfileViewModel viewModel = mViewModel;
        androidx.lifecycle.LiveData<java.lang.String> viewModelTotalElevationGain = null;
        java.lang.String viewModelTotalDurationGetValue = null;
        androidx.lifecycle.LiveData<java.lang.String> viewModelFormattedTotalActivities = null;

        if ((dirtyFlags & 0x7fffL) != 0) {


            if ((dirtyFlags & 0x6001L) != 0) {

                    if (viewModel != null) {
                        // read viewModel.editButtonText
                        viewModelEditButtonText = viewModel.getEditButtonText();
                    }
                    updateLiveDataRegistration(0, viewModelEditButtonText);


                    if (viewModelEditButtonText != null) {
                        // read viewModel.editButtonText.getValue()
                        viewModelEditButtonTextGetValue = viewModelEditButtonText.getValue();
                    }


                    // read androidx.databinding.ViewDataBinding.safeUnbox(viewModel.editButtonText.getValue())
                    androidxDatabindingViewDataBindingSafeUnboxViewModelEditButtonTextGetValue = androidx.databinding.ViewDataBinding.safeUnbox(viewModelEditButtonTextGetValue);


                    // read context.getString(androidx.databinding.ViewDataBinding.safeUnbox(viewModel.editButtonText.getValue()))
                    contextGetStringViewModelEditButtonText = getRoot().getContext().getString(androidxDatabindingViewDataBindingSafeUnboxViewModelEditButtonTextGetValue);
            }
            if ((dirtyFlags & 0x6002L) != 0) {

                    if (viewModel != null) {
                        // read viewModel.athleteDescription
                        viewModelAthleteDescription = viewModel.getAthleteDescription();
                    }
                    updateLiveDataRegistration(1, viewModelAthleteDescription);


                    if (viewModelAthleteDescription != null) {
                        // read viewModel.athleteDescription.getValue()
                        viewModelAthleteDescriptionGetValue = viewModelAthleteDescription.getValue();
                    }
            }
            if ((dirtyFlags & 0x6004L) != 0) {

                    if (viewModel != null) {
                        // read viewModel.editButtonIcon
                        viewModelEditButtonIcon = viewModel.getEditButtonIcon();
                    }
                    updateLiveDataRegistration(2, viewModelEditButtonIcon);


                    if (viewModelEditButtonIcon != null) {
                        // read viewModel.editButtonIcon.getValue()
                        viewModelEditButtonIconGetValue = viewModelEditButtonIcon.getValue();
                    }
            }
            if ((dirtyFlags & 0x6008L) != 0) {

                    if (viewModel != null) {
                        // read viewModel.totalDuration
                        viewModelTotalDuration = viewModel.getTotalDuration();
                    }
                    updateLiveDataRegistration(3, viewModelTotalDuration);


                    if (viewModelTotalDuration != null) {
                        // read viewModel.totalDuration.getValue()
                        viewModelTotalDurationGetValue = viewModelTotalDuration.getValue();
                    }
            }
            if ((dirtyFlags & 0x6010L) != 0) {

                    if (viewModel != null) {
                        // read viewModel.athleteName
                        viewModelAthleteName = viewModel.getAthleteName();
                    }
                    updateLiveDataRegistration(4, viewModelAthleteName);


                    if (viewModelAthleteName != null) {
                        // read viewModel.athleteName.getValue()
                        viewModelAthleteNameGetValue = viewModelAthleteName.getValue();
                    }
            }
            if ((dirtyFlags & 0x6020L) != 0) {

                    if (viewModel != null) {
                        // read viewModel.formattedActiveDate
                        viewModelFormattedActiveDate = viewModel.getFormattedActiveDate();
                    }
                    updateLiveDataRegistration(5, viewModelFormattedActiveDate);


                    if (viewModelFormattedActiveDate != null) {
                        // read viewModel.formattedActiveDate.getValue()
                        viewModelFormattedActiveDateGetValue = viewModelFormattedActiveDate.getValue();
                    }
            }
            if ((dirtyFlags & 0x6040L) != 0) {

                    if (viewModel != null) {
                        // read viewModel.totalDistance
                        viewModelTotalDistance = viewModel.getTotalDistance();
                    }
                    updateLiveDataRegistration(6, viewModelTotalDistance);


                    if (viewModelTotalDistance != null) {
                        // read viewModel.totalDistance.getValue()
                        viewModelTotalDistanceGetValue = viewModelTotalDistance.getValue();
                    }
            }
            if ((dirtyFlags & 0x6080L) != 0) {

                    if (viewModel != null) {
                        // read viewModel.averageDistance
                        viewModelAverageDistance = viewModel.getAverageDistance();
                    }
                    updateLiveDataRegistration(7, viewModelAverageDistance);


                    if (viewModelAverageDistance != null) {
                        // read viewModel.averageDistance.getValue()
                        viewModelAverageDistanceGetValue = viewModelAverageDistance.getValue();
                    }
            }
            if ((dirtyFlags & 0x6100L) != 0) {

                    if (viewModel != null) {
                        // read viewModel.averagePace
                        viewModelAveragePace = viewModel.getAveragePace();
                    }
                    updateLiveDataRegistration(8, viewModelAveragePace);


                    if (viewModelAveragePace != null) {
                        // read viewModel.averagePace.getValue()
                        viewModelAveragePaceGetValue = viewModelAveragePace.getValue();
                    }
            }
            if ((dirtyFlags & 0x6200L) != 0) {

                    if (viewModel != null) {
                        // read viewModel.maxDistance
                        viewModelMaxDistance = viewModel.getMaxDistance();
                    }
                    updateLiveDataRegistration(9, viewModelMaxDistance);


                    if (viewModelMaxDistance != null) {
                        // read viewModel.maxDistance.getValue()
                        viewModelMaxDistanceGetValue = viewModelMaxDistance.getValue();
                    }
            }
            if ((dirtyFlags & 0x6400L) != 0) {

                    if (viewModel != null) {
                        // read viewModel.isEditMode
                        viewModelIsEditMode = viewModel.isEditMode();
                    }
                    updateLiveDataRegistration(10, viewModelIsEditMode);


                    if (viewModelIsEditMode != null) {
                        // read viewModel.isEditMode.getValue()
                        viewModelIsEditModeGetValue = viewModelIsEditMode.getValue();
                    }


                    // read androidx.databinding.ViewDataBinding.safeUnbox(viewModel.isEditMode.getValue())
                    androidxDatabindingViewDataBindingSafeUnboxViewModelIsEditModeGetValue = androidx.databinding.ViewDataBinding.safeUnbox(viewModelIsEditModeGetValue);
            }
            if ((dirtyFlags & 0x6800L) != 0) {

                    if (viewModel != null) {
                        // read viewModel.totalElevationGain
                        viewModelTotalElevationGain = viewModel.getTotalElevationGain();
                    }
                    updateLiveDataRegistration(11, viewModelTotalElevationGain);


                    if (viewModelTotalElevationGain != null) {
                        // read viewModel.totalElevationGain.getValue()
                        viewModelTotalElevationGainGetValue = viewModelTotalElevationGain.getValue();
                    }
            }
            if ((dirtyFlags & 0x7000L) != 0) {

                    if (viewModel != null) {
                        // read viewModel.formattedTotalActivities
                        viewModelFormattedTotalActivities = viewModel.getFormattedTotalActivities();
                    }
                    updateLiveDataRegistration(12, viewModelFormattedTotalActivities);


                    if (viewModelFormattedTotalActivities != null) {
                        // read viewModel.formattedTotalActivities.getValue()
                        viewModelFormattedTotalActivitiesGetValue = viewModelFormattedTotalActivities.getValue();
                    }
            }
        }
        // batch finished
        if ((dirtyFlags & 0x6001L) != 0) {
            // api target 1

            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.btnEditProfile, contextGetStringViewModelEditButtonText);
        }
        if ((dirtyFlags & 0x6004L) != 0) {
            // api target 1

            this.btnEditProfile.setIcon(androidx.databinding.adapters.Converters.convertColorToDrawable(viewModelEditButtonIconGetValue));
        }
        if ((dirtyFlags & 0x6002L) != 0) {
            // api target 1

            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.etAthleteDescription, viewModelAthleteDescriptionGetValue);
        }
        if ((dirtyFlags & 0x6400L) != 0) {
            // api target 1

            this.etAthleteDescription.setEnabled(androidxDatabindingViewDataBindingSafeUnboxViewModelIsEditModeGetValue);
            this.etAthleteName.setEnabled(androidxDatabindingViewDataBindingSafeUnboxViewModelIsEditModeGetValue);
        }
        if ((dirtyFlags & 0x4000L) != 0) {
            // api target 1

            androidx.databinding.adapters.ViewBindingAdapter.setBackground(this.etAthleteDescription, androidx.databinding.adapters.Converters.convertColorToDrawable(getColorFromResource(etAthleteDescription, android.R.color.transparent)));
            androidx.databinding.adapters.ViewBindingAdapter.setBackground(this.etAthleteName, androidx.databinding.adapters.Converters.convertColorToDrawable(getColorFromResource(etAthleteName, android.R.color.transparent)));
        }
        if ((dirtyFlags & 0x6010L) != 0) {
            // api target 1

            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.etAthleteName, viewModelAthleteNameGetValue);
        }
        if ((dirtyFlags & 0x6100L) != 0) {
            // api target 1

            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.mboundView10, viewModelAveragePaceGetValue);
        }
        if ((dirtyFlags & 0x6800L) != 0) {
            // api target 1

            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.mboundView11, viewModelTotalElevationGainGetValue);
        }
        if ((dirtyFlags & 0x7000L) != 0) {
            // api target 1

            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.mboundView4, viewModelFormattedTotalActivitiesGetValue);
        }
        if ((dirtyFlags & 0x6020L) != 0) {
            // api target 1

            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.mboundView5, viewModelFormattedActiveDateGetValue);
        }
        if ((dirtyFlags & 0x6040L) != 0) {
            // api target 1

            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.mboundView6, viewModelTotalDistanceGetValue);
        }
        if ((dirtyFlags & 0x6080L) != 0) {
            // api target 1

            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.mboundView7, viewModelAverageDistanceGetValue);
        }
        if ((dirtyFlags & 0x6200L) != 0) {
            // api target 1

            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.mboundView8, viewModelMaxDistanceGetValue);
        }
        if ((dirtyFlags & 0x6008L) != 0) {
            // api target 1

            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.mboundView9, viewModelTotalDurationGetValue);
        }
    }
    // Listener Stub Implementations
    // callback impls
    // dirty flag
    private  long mDirtyFlags = 0xffffffffffffffffL;
    /* flag mapping
        flag 0 (0x1L): viewModel.editButtonText
        flag 1 (0x2L): viewModel.athleteDescription
        flag 2 (0x3L): viewModel.editButtonIcon
        flag 3 (0x4L): viewModel.totalDuration
        flag 4 (0x5L): viewModel.athleteName
        flag 5 (0x6L): viewModel.formattedActiveDate
        flag 6 (0x7L): viewModel.totalDistance
        flag 7 (0x8L): viewModel.averageDistance
        flag 8 (0x9L): viewModel.averagePace
        flag 9 (0xaL): viewModel.maxDistance
        flag 10 (0xbL): viewModel.isEditMode
        flag 11 (0xcL): viewModel.totalElevationGain
        flag 12 (0xdL): viewModel.formattedTotalActivities
        flag 13 (0xeL): viewModel
        flag 14 (0xfL): null
    flag mapping end*/
    //end
}