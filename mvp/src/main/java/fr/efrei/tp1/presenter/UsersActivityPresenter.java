package fr.efrei.tp1.presenter;

import java.util.List;

import android.content.Context;

import fr.efrei.tp1.bo.User;
import fr.efrei.tp1.repository.UserRepository;

public final class UsersActivityPresenter
{

  public interface View
  {

    void updateRecyclerView(List<User> users);

  }

  public enum OrderState
  {
    Order, NotOrder
  }

  private OrderState currentOrderState = OrderState.NotOrder;

  private List<User> users;

  private final View view;

  public UsersActivityPresenter(View view)
  {
    this.view = view;
  }

  public void updateList(Context context, OrderState orderState)
  {
    currentOrderState = orderState;
    updateList(context);
  }

  public void updateList(Context context)
  {
    users = currentOrderState == OrderState.NotOrder ? UserRepository.getInstance(context).getUsers() : UserRepository.getInstance(context).sortUsersByName();
    view.updateRecyclerView(users);
  }

}
