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
        sViewsWithIds.put(R.id.appBarLayout, 10);
        sViewsWithIds.put(R.id.toolbar, 11);
        sViewsWithIds.put(R.id.mapPreview, 12);
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
        super(bindingComponent, root, 9
            , (com.google.android.material.appbar.AppBarLayout) bindings[10]
            , (com.google.android.material.button.MaterialButton) bindings[14]
            , (com.google.android.material.button.MaterialButton) bindings[9]
            , (com.google.android.material.button.MaterialButton) bindings[13]
            , (com.google.android.material.textfield.TextInputEditText) bindings[7]
            , (com.google.android.material.textfield.TextInputEditText) bindings[6]
            , (com.google.android.gms.maps.MapView) bindings[12]
            , (android.widget.ImageView) bindings[8]
            , (com.google.android.material.appbar.MaterialToolbar) bindings[11]
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
        this.routeImagePreview.setTag(null);
        setRootTag(root);
        // listeners
        invalidateAll();
    }

    @Override
    public void invalidateAll() {
        synchronized(this) {
                mDirtyFlags = 0x400L;
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
            mDirtyFlags |= 0x200L;
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
                return onChangeViewModelRouteDescription((androidx.lifecycle.MutableLiveData<java.lang.String>) object, fieldId);
            case 2 :
                return onChangeViewModelFormattedDistance((androidx.lifecycle.LiveData<java.lang.String>) object, fieldId);
            case 3 :
                return onChangeViewModelFormattedElevation((androidx.lifecycle.LiveData<java.lang.String>) object, fieldId);
            case 4 :
                return onChangeViewModelFormattedDuration((androidx.lifecycle.LiveData<java.lang.String>) object, fieldId);
            case 5 :
                return onChangeViewModelImagePreviewVisible((androidx.lifecycle.LiveData<java.lang.Integer>) object, fieldId);
            case 6 :
                return onChangeViewModelRouteName((androidx.lifecycle.MutableLiveData<java.lang.String>) object, fieldId);
            case 7 :
                return onChangeViewModelFormattedDate((androidx.lifecycle.LiveData<java.lang.String>) object, fieldId);
            case 8 :
                return onChangeViewModelIsFormValid((androidx.lifecycle.MediatorLiveData<java.lang.Boolean>) object, fieldId);
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
    private boolean onChangeViewModelRouteDescription(androidx.lifecycle.MutableLiveData<java.lang.String> ViewModelRouteDescription, int fieldId) {
        if (fieldId == BR._all) {
            synchronized(this) {
                    mDirtyFlags |= 0x2L;
            }
            return true;
        }
        return false;
    }
    private boolean onChangeViewModelFormattedDistance(androidx.lifecycle.LiveData<java.lang.String> ViewModelFormattedDistance, int fieldId) {
        if (fieldId == BR._all) {
            synchronized(this) {
                    mDirtyFlags |= 0x4L;
            }
            return true;
        }
        return false;
    }
    private boolean onChangeViewModelFormattedElevation(androidx.lifecycle.LiveData<java.lang.String> ViewModelFormattedElevation, int fieldId) {
        if (fieldId == BR._all) {
            synchronized(this) {
                    mDirtyFlags |= 0x8L;
            }
            return true;
        }
        return false;
    }
    private boolean onChangeViewModelFormattedDuration(androidx.lifecycle.LiveData<java.lang.String> ViewModelFormattedDuration, int fieldId) {
        if (fieldId == BR._all) {
            synchronized(this) {
                    mDirtyFlags |= 0x10L;
            }
            return true;
        }
        return false;
    }
    private boolean onChangeViewModelImagePreviewVisible(androidx.lifecycle.LiveData<java.lang.Integer> ViewModelImagePreviewVisible, int fieldId) {
        if (fieldId == BR._all) {
            synchronized(this) {
                    mDirtyFlags |= 0x20L;
            }
            return true;
        }
        return false;
    }
    private boolean onChangeViewModelRouteName(androidx.lifecycle.MutableLiveData<java.lang.String> ViewModelRouteName, int fieldId) {
        if (fieldId == BR._all) {
            synchronized(this) {
                    mDirtyFlags |= 0x40L;
            }
            return true;
        }
        return false;
    }
    private boolean onChangeViewModelFormattedDate(androidx.lifecycle.LiveData<java.lang.String> ViewModelFormattedDate, int fieldId) {
        if (fieldId == BR._all) {
            synchronized(this) {
                    mDirtyFlags |= 0x80L;
            }
            return true;
        }
        return false;
    }
    private boolean onChangeViewModelIsFormValid(androidx.lifecycle.MediatorLiveData<java.lang.Boolean> ViewModelIsFormValid, int fieldId) {
        if (fieldId == BR._all) {
            synchronized(this) {
                    mDirtyFlags |= 0x100L;
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
        androidx.lifecycle.MutableLiveData<java.lang.String> viewModelRouteDescription = null;
        androidx.lifecycle.LiveData<java.lang.String> viewModelFormattedDistance = null;
        java.lang.String viewModelFormattedDistanceGetValue = null;
        androidx.lifecycle.LiveData<java.lang.String> viewModelFormattedElevation = null;
        androidx.lifecycle.LiveData<java.lang.String> viewModelFormattedDuration = null;
        androidx.lifecycle.LiveData<java.lang.Integer> viewModelImagePreviewVisible = null;
        java.lang.String viewModelRouteDescriptionGetValue = null;
        int androidxDatabindingViewDataBindingSafeUnboxViewModelImagePreviewVisibleGetValue = 0;
        androidx.lifecycle.MutableLiveData<java.lang.String> viewModelRouteName = null;
        androidx.lifecycle.LiveData<java.lang.String> viewModelFormattedDate = null;
        androidx.lifecycle.MediatorLiveData<java.lang.Boolean> viewModelIsFormValid = null;
        java.lang.Integer viewModelImagePreviewVisibleGetValue = null;
        java.lang.String viewModelFormattedDurationGetValue = null;
        java.lang.String viewModelFormattedElevationGetValue = null;
        java.lang.String viewModelRouteNameGetValue = null;
        java.lang.String viewModelFormattedDateGetValue = null;
        java.lang.String viewModelFormattedElevationGainGetValue = null;
        java.lang.Boolean viewModelIsFormValidGetValue = null;
        com.example.stepbystep.ui.saveroute.SaveRouteViewModel viewModel = mViewModel;
        boolean androidxDatabindingViewDataBindingSafeUnboxViewModelIsFormValidGetValue = false;

        if ((dirtyFlags & 0x7ffL) != 0) {


            if ((dirtyFlags & 0x601L) != 0) {

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
            if ((dirtyFlags & 0x602L) != 0) {

                    if (viewModel != null) {
                        // read viewModel.routeDescription
                        viewModelRouteDescription = viewModel.getRouteDescription();
                    }
                    updateLiveDataRegistration(1, viewModelRouteDescription);


                    if (viewModelRouteDescription != null) {
                        // read viewModel.routeDescription.getValue()
                        viewModelRouteDescriptionGetValue = viewModelRouteDescription.getValue();
                    }
            }
            if ((dirtyFlags & 0x604L) != 0) {

                    if (viewModel != null) {
                        // read viewModel.formattedDistance
                        viewModelFormattedDistance = viewModel.getFormattedDistance();
                    }
                    updateLiveDataRegistration(2, viewModelFormattedDistance);


                    if (viewModelFormattedDistance != null) {
                        // read viewModel.formattedDistance.getValue()
                        viewModelFormattedDistanceGetValue = viewModelFormattedDistance.getValue();
                    }
            }
            if ((dirtyFlags & 0x608L) != 0) {

                    if (viewModel != null) {
                        // read viewModel.formattedElevation
                        viewModelFormattedElevation = viewModel.getFormattedElevation();
                    }
                    updateLiveDataRegistration(3, viewModelFormattedElevation);


                    if (viewModelFormattedElevation != null) {
                        // read viewModel.formattedElevation.getValue()
                        viewModelFormattedElevationGetValue = viewModelFormattedElevation.getValue();
                    }
            }
            if ((dirtyFlags & 0x610L) != 0) {

                    if (viewModel != null) {
                        // read viewModel.formattedDuration
                        viewModelFormattedDuration = viewModel.getFormattedDuration();
                    }
                    updateLiveDataRegistration(4, viewModelFormattedDuration);


                    if (viewModelFormattedDuration != null) {
                        // read viewModel.formattedDuration.getValue()
                        viewModelFormattedDurationGetValue = viewModelFormattedDuration.getValue();
                    }
            }
            if ((dirtyFlags & 0x620L) != 0) {

                    if (viewModel != null) {
                        // read viewModel.imagePreviewVisible
                        viewModelImagePreviewVisible = viewModel.getImagePreviewVisible();
                    }
                    updateLiveDataRegistration(5, viewModelImagePreviewVisible);


                    if (viewModelImagePreviewVisible != null) {
                        // read viewModel.imagePreviewVisible.getValue()
                        viewModelImagePreviewVisibleGetValue = viewModelImagePreviewVisible.getValue();
                    }


                    // read androidx.databinding.ViewDataBinding.safeUnbox(viewModel.imagePreviewVisible.getValue())
                    androidxDatabindingViewDataBindingSafeUnboxViewModelImagePreviewVisibleGetValue = androidx.databinding.ViewDataBinding.safeUnbox(viewModelImagePreviewVisibleGetValue);
            }
            if ((dirtyFlags & 0x640L) != 0) {

                    if (viewModel != null) {
                        // read viewModel.routeName
                        viewModelRouteName = viewModel.getRouteName();
                    }
                    updateLiveDataRegistration(6, viewModelRouteName);


                    if (viewModelRouteName != null) {
                        // read viewModel.routeName.getValue()
                        viewModelRouteNameGetValue = viewModelRouteName.getValue();
                    }
            }
            if ((dirtyFlags & 0x680L) != 0) {

                    if (viewModel != null) {
                        // read viewModel.formattedDate
                        viewModelFormattedDate = viewModel.getFormattedDate();
                    }
                    updateLiveDataRegistration(7, viewModelFormattedDate);


                    if (viewModelFormattedDate != null) {
                        // read viewModel.formattedDate.getValue()
                        viewModelFormattedDateGetValue = viewModelFormattedDate.getValue();
                    }
            }
            if ((dirtyFlags & 0x700L) != 0) {

                    if (viewModel != null) {
                        // read viewModel.isFormValid
                        viewModelIsFormValid = viewModel.isFormValid();
                    }
                    updateLiveDataRegistration(8, viewModelIsFormValid);


                    if (viewModelIsFormValid != null) {
                        // read viewModel.isFormValid.getValue()
                        viewModelIsFormValidGetValue = viewModelIsFormValid.getValue();
                    }


                    // read androidx.databinding.ViewDataBinding.safeUnbox(viewModel.isFormValid.getValue())
                    androidxDatabindingViewDataBindingSafeUnboxViewModelIsFormValidGetValue = androidx.databinding.ViewDataBinding.safeUnbox(viewModelIsFormValidGetValue);
            }
        }
        // batch finished
        if ((dirtyFlags & 0x700L) != 0) {
            // api target 1

            this.btnSave.setEnabled(androidxDatabindingViewDataBindingSafeUnboxViewModelIsFormValidGetValue);
        }
        if ((dirtyFlags & 0x602L) != 0) {
            // api target 1

            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.etRouteDescription, viewModelRouteDescriptionGetValue);
        }
        if ((dirtyFlags & 0x400L) != 0) {
            // api target 1

            androidx.databinding.adapters.TextViewBindingAdapter.setTextWatcher(this.etRouteDescription, (androidx.databinding.adapters.TextViewBindingAdapter.BeforeTextChanged)null, (androidx.databinding.adapters.TextViewBindingAdapter.OnTextChanged)null, (androidx.databinding.adapters.TextViewBindingAdapter.AfterTextChanged)null, etRouteDescriptionandroidTextAttrChanged);
            androidx.databinding.adapters.TextViewBindingAdapter.setTextWatcher(this.etRouteName, (androidx.databinding.adapters.TextViewBindingAdapter.BeforeTextChanged)null, (androidx.databinding.adapters.TextViewBindingAdapter.OnTextChanged)null, (androidx.databinding.adapters.TextViewBindingAdapter.AfterTextChanged)null, etRouteNameandroidTextAttrChanged);
        }
        if ((dirtyFlags & 0x640L) != 0) {
            // api target 1

            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.etRouteName, viewModelRouteNameGetValue);
        }
        if ((dirtyFlags & 0x604L) != 0) {
            // api target 1

            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.mboundView1, viewModelFormattedDistanceGetValue);
        }
        if ((dirtyFlags & 0x610L) != 0) {
            // api target 1

            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.mboundView2, viewModelFormattedDurationGetValue);
        }
        if ((dirtyFlags & 0x608L) != 0) {
            // api target 1

            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.mboundView3, viewModelFormattedElevationGetValue);
        }
        if ((dirtyFlags & 0x601L) != 0) {
            // api target 1

            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.mboundView4, viewModelFormattedElevationGainGetValue);
        }
        if ((dirtyFlags & 0x680L) != 0) {
            // api target 1

            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.mboundView5, viewModelFormattedDateGetValue);
        }
        if ((dirtyFlags & 0x620L) != 0) {
            // api target 1

            this.routeImagePreview.setVisibility(androidxDatabindingViewDataBindingSafeUnboxViewModelImagePreviewVisibleGetValue);
        }
    }
    // Listener Stub Implementations
    // callback impls
    // dirty flag
    private  long mDirtyFlags = 0xffffffffffffffffL;
    /* flag mapping
        flag 0 (0x1L): viewModel.formattedElevationGain
        flag 1 (0x2L): viewModel.routeDescription
        flag 2 (0x3L): viewModel.formattedDistance
        flag 3 (0x4L): viewModel.formattedElevation
        flag 4 (0x5L): viewModel.formattedDuration
        flag 5 (0x6L): viewModel.imagePreviewVisible
        flag 6 (0x7L): viewModel.routeName
        flag 7 (0x8L): viewModel.formattedDate
        flag 8 (0x9L): viewModel.isFormValid
        flag 9 (0xaL): viewModel
        flag 10 (0xbL): null
    flag mapping end*/
    //end
}