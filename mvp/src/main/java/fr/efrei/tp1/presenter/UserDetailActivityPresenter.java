package fr.efrei.tp1.presenter;

import fr.efrei.tp1.bo.User;

public final class UserDetailActivityPresenter
{

  public interface View
  {

    void updateName(String name);

    void updatePhoneNumber(String phoneNumber);

    void updateAddress(String address);

    void updateAbout(String about);

    void updateActionBarTitle(String name);

  }

  private final User user;

  private final View view;

  public UserDetailActivityPresenter(View view, User user)
  {
    this.view = view;
    this.user = user;

    updateUI();
  }

  private void updateUI()
  {
    view.updateName(user.name);
    view.updateAddress(user.address);
    view.updatePhoneNumber(user.phoneNumber);
    view.updateAbout(user.about);
    view.updateActionBarTitle(user.name);
  }

}
