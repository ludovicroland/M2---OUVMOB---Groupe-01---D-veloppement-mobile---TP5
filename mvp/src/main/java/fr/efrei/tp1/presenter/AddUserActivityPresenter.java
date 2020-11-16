package fr.efrei.tp1.presenter;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import fr.efrei.tp1.bo.User;
import fr.efrei.tp1.repository.UserRepository;

public final class AddUserActivityPresenter
{

  public interface View
  {

    void displayToast();

    void resetForm();

  }

  //The tag used into this screen for the logs
  public static final String TAG = AddUserActivityPresenter.class.getSimpleName();

  private final View view;

  public AddUserActivityPresenter(View view)
  {
    this.view = view;
  }

  public void addUser(Context context, String userName, String userPhoneNumber, String userAddress,
      String aboutUser)
  {
    //We display the properties into the logcat
    displayUserEntries(userName, userPhoneNumber, userAddress, aboutUser);

    //We check if all entries are valid (not null and not empty)
    final boolean canAddUser = checkFormEntries(userName, userPhoneNumber, userAddress, aboutUser);

    if (canAddUser == true)
    {
      //We add the user to the list and we reset the form
      saveUser(context, userName, userPhoneNumber, userAddress, aboutUser);
      view.resetForm();
    }
    else
    {
      //we display a log error and a Toast
      Log.w(AddUserActivityPresenter.TAG, "Cannot add the user");
      view.displayToast();
    }
  }

  private void saveUser(Context context, String userName, String userPhoneNumber,
      String userAddress, String aboutUser)
  {
    UserRepository.getInstance(context).addUser(new User(userName, userPhoneNumber, userAddress, aboutUser));
  }

  private boolean checkFormEntries(String userName, String userPhoneNumber, String userAddress,
      String aboutUser)
  {
    return TextUtils.isEmpty(userName) == false && TextUtils.isEmpty(userPhoneNumber) == false && TextUtils.isEmpty(userAddress) == false && TextUtils.isEmpty(aboutUser) == false;
  }

  private void displayUserEntries(String userName, String userPhoneNumber, String userAddress,
      String aboutUser)
  {
    Log.d(AddUserActivityPresenter.TAG, "name : '" + userName + "'");
    Log.d(AddUserActivityPresenter.TAG, "phone number : '" + userPhoneNumber + "'");
    Log.d(AddUserActivityPresenter.TAG, "address : '" + userAddress + "'");
    Log.d(AddUserActivityPresenter.TAG, "about : '" + aboutUser + "'");
  }

}
