package com.example.stepbystep.databinding;
import com.example.stepbystep.R;
import com.example.stepbystep.BR;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class ActivitySaveRouteBindingImpl extends ActivitySaveRouteBinding  {

    @Nullable
    private static final androidx.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.appBarLayout, 9);
        sViewsWithIds.put(R.id.toolbar, 10);
        sViewsWithIds.put(R.id.mapPreview, 11);
        sViewsWithIds.put(R.id.routeImagePreview, 12);
        sViewsWithIds.put(R.id.btnSelectImage, 13);
        sViewsWithIds.put(R.id.btnDiscard, 14);
    }
    // views
    @NonNull
    private final androidx.coordinatorlayout.widget.CoordinatorLayout mboundView0;
    @NonNull
    private final android.widget.TextView mboundView1;
    @NonNull
    private final android.widget.TextView mboundView2;
    @NonNull
    private final android.widget.TextView mboundView3;
    @NonNull
    private final android.widget.TextView mboundView4;
    @NonNull
    private final com.google.android.material.textfield.TextInputEditText mboundView5;
    // variables
    // values
    // listeners
    // Inverse Binding Event Handlers
    private androidx.databinding.InverseBindingListener etRouteDescriptionandroidTextAttrChanged = new androidx.databinding.InverseBindingListener() {
        @Override
        public void onChange() {
            // Inverse of viewModel.routeDescription.getValue()
            //         is viewModel.routeDescription.setValue((java.lang.String) callbackArg_0)
            java.lang.String callbackArg_0 = androidx.databinding.adapters.TextViewBindingAdapter.getTextString(etRouteDescription);
            // localize variables for thread safety
            // viewModel.routeDescription
            androidx.lifecycle.MutableLiveData<java.lang.String> viewModelRouteDescription = null;
            // viewModel.routeDescription.getValue()
            java.lang.String viewModelRouteDescriptionGetValue = null;
            // viewModel.routeDescription != null
            boolean viewModelRouteDescriptionJavaLangObjectNull = false;
            // viewModel
            com.example.stepbystep.ui.saveroute.SaveRouteViewModel viewModel = mViewModel;
            // viewModel != null
            boolean viewModelJavaLangObjectNull = false;



            viewModelJavaLangObjectNull = (viewModel) != (null);
            if (viewModelJavaLangObjectNull) {


                viewModelRouteDescription = viewModel.getRouteDescription();

                viewModelRouteDescriptionJavaLangObjectNull = (viewModelRouteDescription) != (null);
                if (viewModelRouteDescriptionJavaLangObjectNull) {




                    viewModelRouteDescription.setValue(((java.lang.String) (callbackArg_0)));
                }
            }
        }
    };
    private androidx.databinding.InverseBindingListener etRouteNameandroidTextAttrChanged = new androidx.databinding.InverseBindingListener() {
        @Override
        public void onChange() {
            // Inverse of viewModel.routeName.getValue()
            //         is viewModel.routeName.setValue((java.lang.String) callbackArg_0)
            java.lang.String callbackArg_0 = androidx.databinding.adapters.TextViewBindingAdapter.getTextString(etRouteName);
            // localize variables for thread safety
            // viewModel.routeName.getValue()
            java.lang.String viewModelRouteNameGetValue = null;
            // viewModel.routeName != null
            boolean viewModelRouteNameJavaLangObjectNull = false;
            // viewModel
            com.example.stepbystep.ui.saveroute.SaveRouteViewModel viewModel = mViewModel;
            // viewModel.routeName
            androidx.lifecycle.MutableLiveData<java.lang.String> viewModelRouteName = null;
            // viewModel != null
            boolean viewModelJavaLangObjectNull = false;



            viewModelJavaLangObjectNull = (viewModel) != (null);
            if (viewModelJavaLangObjectNull) {


                viewModelRouteName = viewModel.getRouteName();

                viewModelRouteNameJavaLangObjectNull = (viewModelRouteName) != (null);
                if (viewModelRouteNameJavaLangObjectNull) {




                    viewModelRouteName.setValue(((java.lang.String) (callbackArg_0)));
                }
            }
        }
    };

    public ActivitySaveRouteBindingImpl(@Nullable androidx.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 15, sIncludes, sViewsWithIds));
    }
    private ActivitySaveRouteBindingImpl(androidx.databinding.DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 8
            , (com.google.android.material.appbar.AppBarLayout) bindings[9]
            , (com.google.android.material.button.MaterialButton) bindings[14]
            , (com.google.android.material.button.MaterialButton) bindings[8]
            , (com.google.android.material.button.MaterialButton) bindings[13]
            , (com.google.android.material.textfield.TextInputEditText) bindings[7]
            , (com.google.android.material.textfield.TextInputEditText) bindings[6]
            , (com.google.android.gms.maps.MapView) bindings[11]
            , (android.widget.ImageView) bindings[12]
            , (com.google.android.material.appbar.MaterialToolbar) bindings[10]
            );
        this.btnSave.setTag(null);
        this.etRouteDescription.setTag(null);
        this.etRouteName.setTag(null);
        this.mboundView0 = (androidx.coordinatorlayout.widget.CoordinatorLayout) bindings[0];
        this.mboundView0.setTag(null);
        this.mboundView1 = (android.widget.TextView) bindings[1];
        this.mboundView1.setTag(null);
        this.mboundView2 = (android.widget.TextView) bindings[2];
        this.mboundView2.setTag(null);
        this.mboundView3 = (android.widget.TextView) bindings[3];
        this.mboundView3.setTag(null);
        this.mboundView4 = (android.widget.TextView) bindings[4];
        this.mboundView4.setTag(null);
        this.mboundView5 = (com.google.android.material.textfield.TextInputEditText) bindings[5];
        this.mboundView5.setTag(null);
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
            setViewModel((com.example.stepbystep.ui.saveroute.SaveRouteViewModel) variable);
        }
        else {
            variableSet = false;
        }
            return variableSet;
    }

    public void setViewModel(@Nullable com.example.stepbystep.ui.saveroute.SaveRouteViewModel ViewModel) {
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
                return onChangeViewModelRouteDescription((androidx.lifecycle.MutableLiveData<java.lang.String>) object, fieldId);
            case 1 :
                return onChangeViewModelDistance((androidx.lifecycle.LiveData<java.lang.Double>) object, fieldId);
            case 2 :
                return onChangeViewModelElevationGain((androidx.lifecycle.LiveData<java.lang.Double>) object, fieldId);
            case 3 :
                return onChangeViewModelRouteName((androidx.lifecycle.MutableLiveData<java.lang.String>) object, fieldId);
            case 4 :
                return onChangeViewModelDuration((androidx.lifecycle.LiveData<java.lang.Long>) object, fieldId);
            case 5 :
                return onChangeViewModelIsFormValid((androidx.lifecycle.MediatorLiveData<java.lang.Boolean>) object, fieldId);
            case 6 :
                return onChangeViewModelDate((androidx.lifecycle.LiveData<java.lang.String>) object, fieldId);
            case 7 :
                return onChangeViewModelElevation((androidx.lifecycle.LiveData<java.lang.Double>) object, fieldId);
        }
        return false;
    }
    private boolean onChangeViewModelRouteDescription(androidx.lifecycle.MutableLiveData<java.lang.String> ViewModelRouteDescription, int fieldId) {
        if (fieldId == BR._all) {
            synchronized(this) {
                    mDirtyFlags |= 0x1L;
            }
            return true;
        }
        return false;
    }
    private boolean onChangeViewModelDistance(androidx.lifecycle.LiveData<java.lang.Double> ViewModelDistance, int fieldId) {
        if (fieldId == BR._all) {
            synchronized(this) {
                    mDirtyFlags |= 0x2L;
            }
            return true;
        }
        return false;
    }
    private boolean onChangeViewModelElevationGain(androidx.lifecycle.LiveData<java.lang.Double> ViewModelElevationGain, int fieldId) {
        if (fieldId == BR._all) {
            synchronized(this) {
                    mDirtyFlags |= 0x4L;
            }
            return true;
        }
        return false;
    }
    private boolean onChangeViewModelRouteName(androidx.lifecycle.MutableLiveData<java.lang.String> ViewModelRouteName, int fieldId) {
        if (fieldId == BR._all) {
            synchronized(this) {
                    mDirtyFlags |= 0x8L;
            }
            return true;
        }
        return false;
    }
    private boolean onChangeViewModelDuration(androidx.lifecycle.LiveData<java.lang.Long> ViewModelDuration, int fieldId) {
        if (fieldId == BR._all) {
            synchronized(this) {
                    mDirtyFlags |= 0x10L;
            }
            return true;
        }
        return false;
    }
    private boolean onChangeViewModelIsFormValid(androidx.lifecycle.MediatorLiveData<java.lang.Boolean> ViewModelIsFormValid, int fieldId) {
        if (fieldId == BR._all) {
            synchronized(this) {
                    mDirtyFlags |= 0x20L;
            }
            return true;
        }
        return false;
    }
    private boolean onChangeViewModelDate(androidx.lifecycle.LiveData<java.lang.String> ViewModelDate, int fieldId) {
        if (fieldId == BR._all) {
            synchronized(this) {
                    mDirtyFlags |= 0x40L;
            }
            return true;
        }
        return false;
    }
    private boolean onChangeViewModelElevation(androidx.lifecycle.LiveData<java.lang.Double> ViewModelElevation, int fieldId) {
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
        androidx.lifecycle.MutableLiveData<java.lang.String> viewModelRouteDescription = null;
        double androidxDatabindingViewDataBindingSafeUnboxViewModelDistanceGetValue = 0.0;
        double androidxDatabindingViewDataBindingSafeUnboxViewModelElevationGetValue = 0.0;
        double androidxDatabindingViewDataBindingSafeUnboxViewModelElevationGainGetValue = 0.0;
        java.lang.Double viewModelElevationGainGetValue = null;
        java.lang.String viewModelRouteDescriptionGetValue = null;
        androidx.lifecycle.LiveData<java.lang.Double> viewModelDistance = null;
        androidx.lifecycle.LiveData<java.lang.Double> viewModelElevationGain = null;
        androidx.lifecycle.MutableLiveData<java.lang.String> viewModelRouteName = null;
        androidx.lifecycle.LiveData<java.lang.Long> viewModelDuration = null;
        java.lang.String stringFormatUtilsFormatElevationGainViewModelElevationGain = null;
        java.lang.String stringFormatUtilsFormatDurationViewModelDuration = null;
        java.lang.Long viewModelDurationGetValue = null;
        androidx.lifecycle.MediatorLiveData<java.lang.Boolean> viewModelIsFormValid = null;
        java.lang.String stringFormatUtilsFormatDistanceKmViewModelDistance = null;
        androidx.lifecycle.LiveData<java.lang.String> viewModelDate = null;
        java.lang.String viewModelRouteNameGetValue = null;
        java.lang.Double viewModelDistanceGetValue = null;
        java.lang.String dateFormatUtilsFormatDateViewModelDate = null;
        java.lang.Boolean viewModelIsFormValidGetValue = null;
        long androidxDatabindingViewDataBindingSafeUnboxViewModelDurationGetValue = 0;
        com.example.stepbystep.ui.saveroute.SaveRouteViewModel viewModel = mViewModel;
        java.lang.String stringFormatUtilsFormatElevationViewModelElevation = null;
        boolean androidxDatabindingViewDataBindingSafeUnboxViewModelIsFormValidGetValue = false;
        java.lang.Double viewModelElevationGetValue = null;
        java.lang.String viewModelDateGetValue = null;
        androidx.lifecycle.LiveData<java.lang.Double> viewModelElevation = null;

        if ((dirtyFlags & 0x3ffL) != 0) {


            if ((dirtyFlags & 0x301L) != 0) {

                    if (viewModel != null) {
                        // read viewModel.routeDescription
                        viewModelRouteDescription = viewModel.getRouteDescription();
                    }
                    updateLiveDataRegistration(0, viewModelRouteDescription);


                    if (viewModelRouteDescription != null) {
                        // read viewModel.routeDescription.getValue()
                        viewModelRouteDescriptionGetValue = viewModelRouteDescription.getValue();
                    }
            }
            if ((dirtyFlags & 0x302L) != 0) {

                    if (viewModel != null) {
                        // read viewModel.distance
                        viewModelDistance = viewModel.getDistance();
                    }
                    updateLiveDataRegistration(1, viewModelDistance);


                    if (viewModelDistance != null) {
                        // read viewModel.distance.getValue()
                        viewModelDistanceGetValue = viewModelDistance.getValue();
                    }


                    // read androidx.databinding.ViewDataBinding.safeUnbox(viewModel.distance.getValue())
                    androidxDatabindingViewDataBindingSafeUnboxViewModelDistanceGetValue = androidx.databinding.ViewDataBinding.safeUnbox(viewModelDistanceGetValue);


                    // read StringFormatUtils.formatDistanceKm(androidx.databinding.ViewDataBinding.safeUnbox(viewModel.distance.getValue()))
                    stringFormatUtilsFormatDistanceKmViewModelDistance = com.example.stepbystep.util.StringFormatUtils.formatDistanceKm(androidxDatabindingViewDataBindingSafeUnboxViewModelDistanceGetValue);
            }
            if ((dirtyFlags & 0x304L) != 0) {

                    if (viewModel != null) {
                        // read viewModel.elevationGain
                        viewModelElevationGain = viewModel.getElevationGain();
                    }
                    updateLiveDataRegistration(2, viewModelElevationGain);


                    if (viewModelElevationGain != null) {
                        // read viewModel.elevationGain.getValue()
                        viewModelElevationGainGetValue = viewModelElevationGain.getValue();
                    }


                    // read androidx.databinding.ViewDataBinding.safeUnbox(viewModel.elevationGain.getValue())
                    androidxDatabindingViewDataBindingSafeUnboxViewModelElevationGainGetValue = androidx.databinding.ViewDataBinding.safeUnbox(viewModelElevationGainGetValue);


                    // read StringFormatUtils.formatElevationGain(androidx.databinding.ViewDataBinding.safeUnbox(viewModel.elevationGain.getValue()))
                    stringFormatUtilsFormatElevationGainViewModelElevationGain = com.example.stepbystep.util.StringFormatUtils.formatElevationGain(androidxDatabindingViewDataBindingSafeUnboxViewModelElevationGainGetValue);
            }
            if ((dirtyFlags & 0x308L) != 0) {

                    if (viewModel != null) {
                        // read viewModel.routeName
                        viewModelRouteName = viewModel.getRouteName();
                    }
                    updateLiveDataRegistration(3, viewModelRouteName);


                    if (viewModelRouteName != null) {
                        // read viewModel.routeName.getValue()
                        viewModelRouteNameGetValue = viewModelRouteName.getValue();
                    }
            }
            if ((dirtyFlags & 0x310L) != 0) {

                    if (viewModel != null) {
                        // read viewModel.duration
                        viewModelDuration = viewModel.getDuration();
                    }
                    updateLiveDataRegistration(4, viewModelDuration);


                    if (viewModelDuration != null) {
                        // read viewModel.duration.getValue()
                        viewModelDurationGetValue = viewModelDuration.getValue();
                    }


                    // read androidx.databinding.ViewDataBinding.safeUnbox(viewModel.duration.getValue())
                    androidxDatabindingViewDataBindingSafeUnboxViewModelDurationGetValue = androidx.databinding.ViewDataBinding.safeUnbox(viewModelDurationGetValue);


                    // read StringFormatUtils.formatDuration(androidx.databinding.ViewDataBinding.safeUnbox(viewModel.duration.getValue()))
                    stringFormatUtilsFormatDurationViewModelDuration = com.example.stepbystep.util.StringFormatUtils.formatDuration(androidxDatabindingViewDataBindingSafeUnboxViewModelDurationGetValue);
            }
            if ((dirtyFlags & 0x320L) != 0) {

                    if (viewModel != null) {
                        // read viewModel.isFormValid
                        viewModelIsFormValid = viewModel.isFormValid();
                    }
                    updateLiveDataRegistration(5, viewModelIsFormValid);


                    if (viewModelIsFormValid != null) {
                        // read viewModel.isFormValid.getValue()
                        viewModelIsFormValidGetValue = viewModelIsFormValid.getValue();
                    }


                    // read androidx.databinding.ViewDataBinding.safeUnbox(viewModel.isFormValid.getValue())
                    androidxDatabindingViewDataBindingSafeUnboxViewModelIsFormValidGetValue = androidx.databinding.ViewDataBinding.safeUnbox(viewModelIsFormValidGetValue);
            }
            if ((dirtyFlags & 0x340L) != 0) {

                    if (viewModel != null) {
                        // read viewModel.date
                        viewModelDate = viewModel.getDate();
                    }
                    updateLiveDataRegistration(6, viewModelDate);


                    if (viewModelDate != null) {
                        // read viewModel.date.getValue()
                        viewModelDateGetValue = viewModelDate.getValue();
                    }


                    // read DateFormatUtils.formatDate(viewModel.date.getValue())
                    dateFormatUtilsFormatDateViewModelDate = com.example.stepbystep.util.DateFormatUtils.formatDate(viewModelDateGetValue);
            }
            if ((dirtyFlags & 0x380L) != 0) {

                    if (viewModel != null) {
                        // read viewModel.elevation
                        viewModelElevation = viewModel.getElevation();
                    }
                    updateLiveDataRegistration(7, viewModelElevation);


                    if (viewModelElevation != null) {
                        // read viewModel.elevation.getValue()
                        viewModelElevationGetValue = viewModelElevation.getValue();
                    }


                    // read androidx.databinding.ViewDataBinding.safeUnbox(viewModel.elevation.getValue())
                    androidxDatabindingViewDataBindingSafeUnboxViewModelElevationGetValue = androidx.databinding.ViewDataBinding.safeUnbox(viewModelElevationGetValue);


                    // read StringFormatUtils.formatElevation(androidx.databinding.ViewDataBinding.safeUnbox(viewModel.elevation.getValue()))
                    stringFormatUtilsFormatElevationViewModelElevation = com.example.stepbystep.util.StringFormatUtils.formatElevation(androidxDatabindingViewDataBindingSafeUnboxViewModelElevationGetValue);
            }
        }
        // batch finished
        if ((dirtyFlags & 0x320L) != 0) {
            // api target 1

            this.btnSave.setEnabled(androidxDatabindingViewDataBindingSafeUnboxViewModelIsFormValidGetValue);
        }
        if ((dirtyFlags & 0x301L) != 0) {
            // api target 1

            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.etRouteDescription, viewModelRouteDescriptionGetValue);
        }
        if ((dirtyFlags & 0x200L) != 0) {
            // api target 1

            androidx.databinding.adapters.TextViewBindingAdapter.setTextWatcher(this.etRouteDescription, (androidx.databinding.adapters.TextViewBindingAdapter.BeforeTextChanged)null, (androidx.databinding.adapters.TextViewBindingAdapter.OnTextChanged)null, (androidx.databinding.adapters.TextViewBindingAdapter.AfterTextChanged)null, etRouteDescriptionandroidTextAttrChanged);
            androidx.databinding.adapters.TextViewBindingAdapter.setTextWatcher(this.etRouteName, (androidx.databinding.adapters.TextViewBindingAdapter.BeforeTextChanged)null, (androidx.databinding.adapters.TextViewBindingAdapter.OnTextChanged)null, (androidx.databinding.adapters.TextViewBindingAdapter.AfterTextChanged)null, etRouteNameandroidTextAttrChanged);
        }
        if ((dirtyFlags & 0x308L) != 0) {
            // api target 1

            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.etRouteName, viewModelRouteNameGetValue);
        }
        if ((dirtyFlags & 0x302L) != 0) {
            // api target 1

            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.mboundView1, stringFormatUtilsFormatDistanceKmViewModelDistance);
        }
        if ((dirtyFlags & 0x310L) != 0) {
            // api target 1

            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.mboundView2, stringFormatUtilsFormatDurationViewModelDuration);
        }
        if ((dirtyFlags & 0x380L) != 0) {
            // api target 1

            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.mboundView3, stringFormatUtilsFormatElevationViewModelElevation);
        }
        if ((dirtyFlags & 0x304L) != 0) {
            // api target 1

            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.mboundView4, stringFormatUtilsFormatElevationGainViewModelElevationGain);
        }
        if ((dirtyFlags & 0x340L) != 0) {
            // api target 1

            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.mboundView5, dateFormatUtilsFormatDateViewModelDate);
        }
    }
    // Listener Stub Implementations
    // callback impls
    // dirty flag
    private  long mDirtyFlags = 0xffffffffffffffffL;
    /* flag mapping
        flag 0 (0x1L): viewModel.routeDescription
        flag 1 (0x2L): viewModel.distance
        flag 2 (0x3L): viewModel.elevationGain
        flag 3 (0x4L): viewModel.routeName
        flag 4 (0x5L): viewModel.duration
        flag 5 (0x6L): viewModel.isFormValid
        flag 6 (0x7L): viewModel.date
        flag 7 (0x8L): viewModel.elevation
        flag 8 (0x9L): viewModel
        flag 9 (0xaL): null
    flag mapping end*/
    //end
}