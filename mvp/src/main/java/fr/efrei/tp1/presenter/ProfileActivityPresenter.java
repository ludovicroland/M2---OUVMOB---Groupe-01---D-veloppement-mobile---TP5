package fr.efrei.tp1.presenter;

import android.content.Context;
import android.text.TextUtils;

import fr.efrei.tp1.preferences.AppPreferences;

public final class ProfileActivityPresenter
{

  public interface View
  {

    void updateName(String name);

  }

  private final View view;

  public ProfileActivityPresenter(View view)
  {
    this.view = view;
  }

  public void save(Context context, String name)
  {
    AppPreferences.saveUserLogin(context, name);
  }

  public void restoreData(Context context)
  {
    //We retrieve the name store into the shared preferences
    final String userLogin = AppPreferences.getUserLogin(context);

    //if the name is not null we restore it
    if (TextUtils.isEmpty(userLogin) == false)
    {
      view.updateName(userLogin);
    }
  }

}
