// Generated by view binder compiler. Do not edit!
package com.example.fyp_robot_dog_androidx.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.fyp_robot_dog_androidx.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivityEmailVersificationBinding implements ViewBinding {
  @NonNull
  private final LinearLayout rootView;

  @NonNull
  public final EditText code1;

  @NonNull
  public final EditText code2;

  @NonNull
  public final EditText code3;

  @NonNull
  public final EditText code4;

  @NonNull
  public final TextView tvText;

  private ActivityEmailVersificationBinding(@NonNull LinearLayout rootView, @NonNull EditText code1,
      @NonNull EditText code2, @NonNull EditText code3, @NonNull EditText code4,
      @NonNull TextView tvText) {
    this.rootView = rootView;
    this.code1 = code1;
    this.code2 = code2;
    this.code3 = code3;
    this.code4 = code4;
    this.tvText = tvText;
  }

  @Override
  @NonNull
  public LinearLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityEmailVersificationBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityEmailVersificationBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_email_versification, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityEmailVersificationBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.code1;
      EditText code1 = ViewBindings.findChildViewById(rootView, id);
      if (code1 == null) {
        break missingId;
      }

      id = R.id.code2;
      EditText code2 = ViewBindings.findChildViewById(rootView, id);
      if (code2 == null) {
        break missingId;
      }

      id = R.id.code3;
      EditText code3 = ViewBindings.findChildViewById(rootView, id);
      if (code3 == null) {
        break missingId;
      }

      id = R.id.code4;
      EditText code4 = ViewBindings.findChildViewById(rootView, id);
      if (code4 == null) {
        break missingId;
      }

      id = R.id.tvText;
      TextView tvText = ViewBindings.findChildViewById(rootView, id);
      if (tvText == null) {
        break missingId;
      }

      return new ActivityEmailVersificationBinding((LinearLayout) rootView, code1, code2, code3,
          code4, tvText);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
