package fr.efrei.tp1.view;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import fr.efrei.tp1.R;
import fr.efrei.tp1.presenter.AddUserActivityPresenter;

final public class AddUserActivity
    extends AppCompatActivity
    implements OnClickListener, AddUserActivityPresenter.View
{

  //The tag used into this screen for the logs
  public static final String TAG = AddUserActivity.class.getSimpleName();

  private EditText name;

  private EditText phoneNumber;

  private EditText address;

  private EditText about;

  private AddUserActivityPresenter presenter;

  @Override
  protected void onCreate(Bundle savedInstanceState)
  {
    super.onCreate(savedInstanceState);

    //We first set up the layout linked to the activity
    setContentView(R.layout.activity_add_user);

    //Then we retrieved the widget we will need to manipulate into the
    name = findViewById(R.id.name);
    phoneNumber = findViewById(R.id.phoneNumber);
    address = findViewById(R.id.address);
    about = findViewById(R.id.about);

    //We configure the click on the save button
    findViewById(R.id.save).setOnClickListener(this);

    presenter = new AddUserActivityPresenter(this);
  }

  @Override
  public void onClick(View v)
  {
    //we first retrieve user's entries
    final String userName = name.getEditableText().toString();
    final String userPhoneNumber = phoneNumber.getEditableText().toString();
    final String userAddress = address.getEditableText().toString();
    final String aboutUser = about.getEditableText().toString();

    presenter.addUser(this, userName, userPhoneNumber, userAddress, aboutUser);
  }

  @Override
  public void displayToast()
  {
    Toast.makeText(this, R.string.cannot_add_user, Toast.LENGTH_SHORT).show();
  }

  @Override
  public void resetForm()
  {
    name.setText(null);
    address.setText(null);
    phoneNumber.setText(null);
    about.setText(null);
  }

}