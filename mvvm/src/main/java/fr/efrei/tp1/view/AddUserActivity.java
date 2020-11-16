package fr.efrei.tp1.view;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import fr.efrei.tp1.R;
import fr.efrei.tp1.viewmodel.AddUserActivityViewModel;
import fr.efrei.tp1.viewmodel.AddUserActivityViewModel.Event;

final public class AddUserActivity
    extends AppCompatActivity
    implements OnClickListener
{

  //The tag used into this screen for the logs
  public static final String TAG = AddUserActivity.class.getSimpleName();

  private EditText name;

  private EditText phoneNumber;

  private EditText address;

  private EditText about;

  private AddUserActivityViewModel viewModel;

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

    viewModel = new ViewModelProvider(this).get(AddUserActivityViewModel.class);

    observeEvent();
  }

  private void observeEvent()
  {
    viewModel.event.observe(this, new Observer<Event>()
    {
      @Override
      public void onChanged(Event event)
      {
        if (event == Event.ResetForm)
        {
          resetForm();
        }
        else if (event == Event.DisplayError)
        {
          displayError();
        }
      }
    });
  }

  private void displayError()
  {
    Toast.makeText(this, R.string.cannot_add_user, Toast.LENGTH_SHORT).show();
  }

  @Override
  public void onClick(View v)
  {
    //we first retrieve user's entries
    final String userName = name.getEditableText().toString();
    final String userPhoneNumber = phoneNumber.getEditableText().toString();
    final String userAddress = address.getEditableText().toString();
    final String aboutUser = about.getEditableText().toString();

    viewModel.saveUser(userName, userPhoneNumber, userAddress, aboutUser);
  }

  private void resetForm()
  {
    name.setText(null);
    address.setText(null);
    phoneNumber.setText(null);
    about.setText(null);
  }

}