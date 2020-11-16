package fr.efrei.tp1.view;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import fr.efrei.tp1.R;
import fr.efrei.tp1.bo.User;
import fr.efrei.tp1.presenter.UserDetailActivityPresenter;

final public class UserDetailActivity
    extends AppCompatActivity
    implements UserDetailActivityPresenter.View
{

  public static final String USER_EXTRA = "userExtra";

  //The tag used into this screen for the logs
  public static final String TAG = UserDetailActivity.class.getSimpleName();

  private UserDetailActivityPresenter presenter;

  private TextView name;

  private TextView phoneNumber;

  private TextView address;

  private TextView about;

  @Override
  protected void onCreate(Bundle savedInstanceState)
  {
    super.onCreate(savedInstanceState);

    //We first set up the layout linked to the activity
    setContentView(R.layout.activity_user_detail);

    //Then we retrieved the widget we will need to manipulate into the
    name = findViewById(R.id.name);
    phoneNumber = findViewById(R.id.phoneNumber);
    address = findViewById(R.id.address);
    about = findViewById(R.id.about);

    //Then we retrieve the extra
    final User user = (User) getIntent().getSerializableExtra(UserDetailActivity.USER_EXTRA);

    presenter = new UserDetailActivityPresenter(this, user);
  }

  @Override
  public void updateName(String name)
  {
    this.name.setText(name);
  }

  @Override
  public void updatePhoneNumber(String phoneNumber)
  {
    this.phoneNumber.setText(phoneNumber);
  }

  @Override
  public void updateAddress(String address)
  {
    this.address.setText(address);
  }

  @Override
  public void updateAbout(String about)
  {
    this.about.setText(about);
  }

  @Override
  public void updateActionBarTitle(String name)
  {
    getSupportActionBar().setTitle(name);
  }

}