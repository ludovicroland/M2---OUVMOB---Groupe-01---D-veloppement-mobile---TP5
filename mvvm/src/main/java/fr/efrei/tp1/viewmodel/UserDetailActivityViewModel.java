package fr.efrei.tp1.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.SavedStateHandle;
import androidx.lifecycle.ViewModel;

import fr.efrei.tp1.bo.User;
import fr.efrei.tp1.view.UserDetailActivity;

public final class UserDetailActivityViewModel
    extends ViewModel
{

  public MutableLiveData<User> user = new MutableLiveData<>();

  public UserDetailActivityViewModel(SavedStateHandle savedStateHandle)
  {
    final User userExtra = savedStateHandle.get(UserDetailActivity.USER_EXTRA);
    user.postValue(userExtra);
  }

}
