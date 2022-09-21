// Generated by view binder compiler. Do not edit!
package by.ssrlab.NASB.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import by.ssrlab.NASB.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ItemSectionBinding implements ViewBinding {
  @NonNull
  private final FrameLayout rootView;

  @NonNull
  public final ImageView photo;

  @NonNull
  public final TextView tvName;

  private ItemSectionBinding(@NonNull FrameLayout rootView, @NonNull ImageView photo,
      @NonNull TextView tvName) {
    this.rootView = rootView;
    this.photo = photo;
    this.tvName = tvName;
  }

  @Override
  @NonNull
  public FrameLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ItemSectionBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ItemSectionBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.item_section, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ItemSectionBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.photo;
      ImageView photo = ViewBindings.findChildViewById(rootView, id);
      if (photo == null) {
        break missingId;
      }

      id = R.id.tvName;
      TextView tvName = ViewBindings.findChildViewById(rootView, id);
      if (tvName == null) {
        break missingId;
      }

      return new ItemSectionBinding((FrameLayout) rootView, photo, tvName);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
