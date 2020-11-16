package fr.efrei.tp1.viewmodel;

import java.util.List;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import fr.efrei.tp1.bo.User;
import fr.efrei.tp1.repository.UserRepository;

public final class UsersActivityViewModel
    extends AndroidViewModel
{

  public enum OrderState
  {
    Order, NotOrder
  }

  public MutableLiveData<List<User>> users = new MutableLiveData<>();

  private OrderState currentOrderState = OrderState.NotOrder;

  public UsersActivityViewModel(@NonNull Application application)
  {
    super(application);
  }

  public void updateOrder(OrderState orderState)
  {
    currentOrderState = orderState;
    loadUsers();
  }

  public void loadUsers()
  {
    users.postValue(currentOrderState == OrderState.NotOrder ? UserRepository.getInstance(getApplication()).getUsers() : UserRepository.getInstance(getApplication()).sortUsersByName());
  }

}
