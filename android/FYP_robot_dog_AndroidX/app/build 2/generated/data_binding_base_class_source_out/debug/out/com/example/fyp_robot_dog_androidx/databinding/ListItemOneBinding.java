// Generated by view binder compiler. Do not edit!
package com.example.fyp_robot_dog_androidx.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.fyp_robot_dog_androidx.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ListItemOneBinding implements ViewBinding {
  @NonNull
  private final CardView rootView;

  @NonNull
  public final LinearLayout ToCreateOrderPage;

  private ListItemOneBinding(@NonNull CardView rootView, @NonNull LinearLayout ToCreateOrderPage) {
    this.rootView = rootView;
    this.ToCreateOrderPage = ToCreateOrderPage;
  }

  @Override
  @NonNull
  public CardView getRoot() {
    return rootView;
  }

  @NonNull
  public static ListItemOneBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ListItemOneBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.list_item_one, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ListItemOneBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.ToCreateOrderPage;
      LinearLayout ToCreateOrderPage = ViewBindings.findChildViewById(rootView, id);
      if (ToCreateOrderPage == null) {
        break missingId;
      }

      return new ListItemOneBinding((CardView) rootView, ToCreateOrderPage);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}