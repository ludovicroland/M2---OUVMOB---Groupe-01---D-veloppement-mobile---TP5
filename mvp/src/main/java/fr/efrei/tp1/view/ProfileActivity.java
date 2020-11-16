package fr.efrei.tp1.view;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import fr.efrei.tp1.R;
import fr.efrei.tp1.presenter.ProfileActivityPresenter;

final public class ProfileActivity
    extends AppCompatActivity
    implements OnClickListener, ProfileActivityPresenter.View
{

  //The tag used into this screen for the logs
  public static final String TAG = ProfileActivity.class.getSimpleName();

  private EditText loginEditText;

  private ProfileActivityPresenter presenter;


  @Override
  protected void onCreate(Bundle savedInstanceState)
  {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_profile);

    //We retrieve the EditText References
    loginEditText = findViewById(R.id.login);

    //We add the listener on the "save" button
    findViewById(R.id.save).setOnClickListener(this);

    presenter = new ProfileActivityPresenter(this);
    presenter.restoreData(this);
  }

  @Override
  public void onClick(View v)
  {
    saveLogin();
  }

  @Override
  public void updateName(String name)
  {
    loginEditText.setText(name);
  }

  private void saveLogin()
  {
    //We save only if there is something to save
    if (TextUtils.isEmpty(loginEditText.getText()) == false)
    {
      presenter.save(this, loginEditText.getText().toString());
    }

    //we return to the previous screen
    onBackPressed();
  }

}