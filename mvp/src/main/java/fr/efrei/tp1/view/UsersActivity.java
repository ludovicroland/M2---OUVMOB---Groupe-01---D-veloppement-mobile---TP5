package fr.efrei.tp1.view;

import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.RecyclerView;

import fr.efrei.tp1.R;
import fr.efrei.tp1.adapter.UsersAdapter;
import fr.efrei.tp1.bo.User;
import fr.efrei.tp1.presenter.UsersActivityPresenter;
import fr.efrei.tp1.presenter.UsersActivityPresenter.OrderState;

final public class UsersActivity
    extends AppCompatActivity
    implements OnClickListener, UsersActivityPresenter.View
{

  //The tag used into this screen for the logs
  public static final String TAG = UsersActivity.class.getSimpleName();

  private UsersActivityPresenter presenter;

  private RecyclerView recyclerView;

  @Override
  protected void onCreate(Bundle savedInstanceState)
  {
    super.onCreate(savedInstanceState);

    //We first set up the layout linked to the activity
    setContentView(R.layout.activity_users);

    recyclerView = findViewById(R.id.recyclerView);
    recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

    //We configure the click on the fab
    findViewById(R.id.fab).setOnClickListener(this);

    presenter = new UsersActivityPresenter(this);
  }

  @Override
  protected void onResume()
  {
    super.onResume();

    //We init the list into the onResume method
    //so the list is updated each time the screen goes to foreground
    presenter.updateList(this);
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu)
  {
    //We add the "menu_order" to the current activity
    getMenuInflater().inflate(R.menu.menu_order, menu);
    return super.onCreateOptionsMenu(menu);
  }

  @Override
  public boolean onOptionsItemSelected(@NonNull MenuItem item)
  {
    //We handle the click on a menu item
    if (item.getItemId() == R.id.order)
    {
      presenter.updateList(this, OrderState.Order);
    }
    else if (item.getItemId() == R.id.noOrder)
    {
      presenter.updateList(this, OrderState.NotOrder);
    }
    else if (item.getItemId() == R.id.profile)
    {
      final Intent intent = new Intent(this, ProfileActivity.class);
      startActivity(intent);
    }

    return super.onOptionsItemSelected(item);
  }

  @Override
  public void updateRecyclerView(List<User> users)
  {
    final UsersAdapter usersAdapter = new UsersAdapter(users);
    recyclerView.setAdapter(usersAdapter);
  }

  @Override
  public void onClick(View v)
  {
    //We open the AddUserActivity screen when the user clicks on the FAB
    final Intent intent = new Intent(this, AddUserActivity.class);
    startActivity(intent);
  }

}