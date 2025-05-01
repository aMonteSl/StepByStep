package com.example.stepbystep;

import android.util.SparseArray;
import android.util.SparseIntArray;
import android.view.View;
import androidx.databinding.DataBinderMapper;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import com.example.stepbystep.databinding.ActivityImportRouteBindingImpl;
import com.example.stepbystep.databinding.ActivityMainBindingImpl;
import com.example.stepbystep.databinding.ActivityNewRouteBindingImpl;
import com.example.stepbystep.databinding.ActivityProfileBindingImpl;
import com.example.stepbystep.databinding.ActivityRouteDetailBindingImpl;
import com.example.stepbystep.databinding.ActivitySaveRouteBindingImpl;
import com.example.stepbystep.databinding.ItemRouteBindingImpl;
import java.lang.IllegalArgumentException;
import java.lang.Integer;
import java.lang.Object;
import java.lang.Override;
import java.lang.RuntimeException;
import java.lang.String;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DataBinderMapperImpl extends DataBinderMapper {
  private static final int LAYOUT_ACTIVITYIMPORTROUTE = 1;

  private static final int LAYOUT_ACTIVITYMAIN = 2;

  private static final int LAYOUT_ACTIVITYNEWROUTE = 3;

  private static final int LAYOUT_ACTIVITYPROFILE = 4;

  private static final int LAYOUT_ACTIVITYROUTEDETAIL = 5;

  private static final int LAYOUT_ACTIVITYSAVEROUTE = 6;

  private static final int LAYOUT_ITEMROUTE = 7;

  private static final SparseIntArray INTERNAL_LAYOUT_ID_LOOKUP = new SparseIntArray(7);

  static {
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.example.stepbystep.R.layout.activity_import_route, LAYOUT_ACTIVITYIMPORTROUTE);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.example.stepbystep.R.layout.activity_main, LAYOUT_ACTIVITYMAIN);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.example.stepbystep.R.layout.activity_new_route, LAYOUT_ACTIVITYNEWROUTE);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.example.stepbystep.R.layout.activity_profile, LAYOUT_ACTIVITYPROFILE);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.example.stepbystep.R.layout.activity_route_detail, LAYOUT_ACTIVITYROUTEDETAIL);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.example.stepbystep.R.layout.activity_save_route, LAYOUT_ACTIVITYSAVEROUTE);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.example.stepbystep.R.layout.item_route, LAYOUT_ITEMROUTE);
  }

  @Override
  public ViewDataBinding getDataBinder(DataBindingComponent component, View view, int layoutId) {
    int localizedLayoutId = INTERNAL_LAYOUT_ID_LOOKUP.get(layoutId);
    if(localizedLayoutId > 0) {
      final Object tag = view.getTag();
      if(tag == null) {
        throw new RuntimeException("view must have a tag");
      }
      switch(localizedLayoutId) {
        case  LAYOUT_ACTIVITYIMPORTROUTE: {
          if ("layout/activity_import_route_0".equals(tag)) {
            return new ActivityImportRouteBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for activity_import_route is invalid. Received: " + tag);
        }
        case  LAYOUT_ACTIVITYMAIN: {
          if ("layout/activity_main_0".equals(tag)) {
            return new ActivityMainBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for activity_main is invalid. Received: " + tag);
        }
        case  LAYOUT_ACTIVITYNEWROUTE: {
          if ("layout/activity_new_route_0".equals(tag)) {
            return new ActivityNewRouteBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for activity_new_route is invalid. Received: " + tag);
        }
        case  LAYOUT_ACTIVITYPROFILE: {
          if ("layout/activity_profile_0".equals(tag)) {
            return new ActivityProfileBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for activity_profile is invalid. Received: " + tag);
        }
        case  LAYOUT_ACTIVITYROUTEDETAIL: {
          if ("layout/activity_route_detail_0".equals(tag)) {
            return new ActivityRouteDetailBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for activity_route_detail is invalid. Received: " + tag);
        }
        case  LAYOUT_ACTIVITYSAVEROUTE: {
          if ("layout/activity_save_route_0".equals(tag)) {
            return new ActivitySaveRouteBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for activity_save_route is invalid. Received: " + tag);
        }
        case  LAYOUT_ITEMROUTE: {
          if ("layout/item_route_0".equals(tag)) {
            return new ItemRouteBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for item_route is invalid. Received: " + tag);
        }
      }
    }
    return null;
  }

  @Override
  public ViewDataBinding getDataBinder(DataBindingComponent component, View[] views, int layoutId) {
    if(views == null || views.length == 0) {
      return null;
    }
    int localizedLayoutId = INTERNAL_LAYOUT_ID_LOOKUP.get(layoutId);
    if(localizedLayoutId > 0) {
      final Object tag = views[0].getTag();
      if(tag == null) {
        throw new RuntimeException("view must have a tag");
      }
      switch(localizedLayoutId) {
      }
    }
    return null;
  }

  @Override
  public int getLayoutId(String tag) {
    if (tag == null) {
      return 0;
    }
    Integer tmpVal = InnerLayoutIdLookup.sKeys.get(tag);
    return tmpVal == null ? 0 : tmpVal;
  }

  @Override
  public String convertBrIdToString(int localId) {
    String tmpVal = InnerBrLookup.sKeys.get(localId);
    return tmpVal;
  }

  @Override
  public List<DataBinderMapper> collectDependencies() {
    ArrayList<DataBinderMapper> result = new ArrayList<DataBinderMapper>(1);
    result.add(new androidx.databinding.library.baseAdapters.DataBinderMapperImpl());
    return result;
  }

  private static class InnerBrLookup {
    static final SparseArray<String> sKeys = new SparseArray<String>(3);

    static {
      sKeys.put(0, "_all");
      sKeys.put(1, "route");
      sKeys.put(2, "viewModel");
    }
  }

  private static class InnerLayoutIdLookup {
    static final HashMap<String, Integer> sKeys = new HashMap<String, Integer>(7);

    static {
      sKeys.put("layout/activity_import_route_0", com.example.stepbystep.R.layout.activity_import_route);
      sKeys.put("layout/activity_main_0", com.example.stepbystep.R.layout.activity_main);
      sKeys.put("layout/activity_new_route_0", com.example.stepbystep.R.layout.activity_new_route);
      sKeys.put("layout/activity_profile_0", com.example.stepbystep.R.layout.activity_profile);
      sKeys.put("layout/activity_route_detail_0", com.example.stepbystep.R.layout.activity_route_detail);
      sKeys.put("layout/activity_save_route_0", com.example.stepbystep.R.layout.activity_save_route);
      sKeys.put("layout/item_route_0", com.example.stepbystep.R.layout.item_route);
    }
  }
}
