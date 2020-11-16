package fr.efrei.tp1.view;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import fr.efrei.tp1.R;
import fr.efrei.tp1.preferences.AppPreferences;
import fr.efrei.tp1.viewmodel.ProfileActivityViewModel;

final public class ProfileActivity
    extends AppCompatActivity
    implements OnClickListener
{

  //The tag used into this screen for the logs
  public static final String TAG = ProfileActivity.class.getSimpleName();

  private EditText loginEditText;

  private ProfileActivityViewModel viewModel;


  @Override
  protected void onCreate(Bundle savedInstanceState)
  {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_profile);

    //We retrieve the EditText References
    loginEditText = findViewById(R.id.login);

    //We add the listener on the "save" button
    findViewById(R.id.save).setOnClickListener(this);

    viewModel = new ViewModelProvider(this).get(ProfileActivityViewModel.class);

    observeName();
  }

  private void observeName()
  {
    viewModel.name.observe(this, new Observer<String>() {
      @Override
      public void onChanged(String name)
      {
        loginEditText.setText(name);
      }
    });
  }

  @Override
  public void onClick(View v)
  {
    saveLogin();
  }

  private void saveLogin()
  {
    viewModel.saveLogin(loginEditText.getText().toString());

    //we return to the previous screen
    onBackPressed();
  }

}