package com.example.leakypete.operationgreentampon;

import android.app.Dialog;
import android.content.Intent;

import android.os.Bundle;

import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;

import android.support.v4.view.ViewPager;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.Toolbar;

import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;

import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.nightonke.boommenu.BoomButtons.ButtonPlaceEnum;
import com.nightonke.boommenu.BoomButtons.OnBMClickListener;
import com.nightonke.boommenu.BoomButtons.TextInsideCircleButton;
import com.nightonke.boommenu.BoomMenuButton;
import com.nightonke.boommenu.ButtonEnum;
import com.nightonke.boommenu.Piece.PiecePlaceEnum;

import java.util.HashMap;


public class HomeActivity extends AppCompatActivity {

    private TabLayout mTablayout;
    private ViewPager mViewPager;
    private HomePageAdapter mConnecPager;
    private FirebaseAuth auth;
    private FirebaseAuth.AuthStateListener authListener;
    private DatabaseReference mDatabase;
    ImageView nigger;
    FirebaseUser user;
    Dialog loginDialog,signDialog;
    Button login,signUp,createAcc;
    TextView forgotPass, linkLogin;
    EditText username,passwordInput,usernameInput,emailInput,passwordInput2,reEnterPasswordInput;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_home);

        auth = FirebaseAuth.getInstance();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        final ImageView profileActivity = (ImageView) toolbar.findViewById(R.id.profile);
        profileActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                user = FirebaseAuth.getInstance().getCurrentUser();
                if (user == null) {
                    LoginDialog();
                }
                else
                {
                    Intent ProfAcitivty = new Intent(HomeActivity.this, ProfAcitivty.class);
                    startActivity(ProfAcitivty);
                    finish();
                    overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);

                }
            }
        });




        mConnecPager = new HomePageAdapter(getSupportFragmentManager());

        nigger = (ImageView) findViewById(R.id.settings);
        mTablayout = (TabLayout) findViewById(R.id.main_tabs);
        mViewPager = (ViewPager) findViewById(R.id.profPager);
        BoomMenuButton bmb = (BoomMenuButton) findViewById(R.id.bmb);
        username = (EditText) findViewById(R.id.input_username);
        passwordInput = (EditText) findViewById(R.id.input_password2);
        forgotPass = (TextView) findViewById(R.id.txt_forgot);

        mDatabase= FirebaseDatabase.getInstance().getReference().child("Users");


        setSupportActionBar(toolbar);
        mTablayout.setupWithViewPager(mViewPager);
        mViewPager.setAdapter(mConnecPager);


        nigger.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {

                PopupMenu niggerdik = new PopupMenu(HomeActivity.this, nigger);
                niggerdik.getMenuInflater().inflate(R.menu.popup_menu, niggerdik.getMenu());

                niggerdik.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {

                        switch (item.getItemId()){
                            case R.id.logout:
                                auth.signOut();
                                Toast.makeText(getApplicationContext(), "Signed Out", Toast.LENGTH_SHORT).show();
                                break;
                        }
                        return true;
                    }
                });
                niggerdik.show();
            }
        });

        bmb.setButtonEnum(ButtonEnum.TextInsideCircle);
        bmb.setPiecePlaceEnum(PiecePlaceEnum.DOT_3_1);
        bmb.setButtonPlaceEnum(ButtonPlaceEnum.SC_3_3);

        for (int i = 0; i < bmb.getPiecePlaceEnum().pieceNumber(); i++) {
            switch(i)
            {
                case 0:
                    TextInsideCircleButton.Builder builder = new TextInsideCircleButton.Builder()
                            .normalImageRes(R.drawable.ic_trend)
                            .normalText("Create a Post").listener(new OnBMClickListener() {
                                @Override
                                public void onBoomButtonClick(int index) {
                                    user = FirebaseAuth.getInstance().getCurrentUser();
                                    if (user == null) {
                                        LoginDialog();
                                    }
                                    else
                                    {
                                        Intent createPostActivity = new Intent(HomeActivity.this, CreatePostActivity.class);
                                        startActivity(createPostActivity);
                                        finish();
                                        overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);
                                    }
                                }
                            });
                    bmb.addBuilder(builder);
                    break;
                case 1:
                    TextInsideCircleButton.Builder builder1 = new TextInsideCircleButton.Builder()
                            .normalImageRes(R.drawable.ic_settings)
                            .normalText("POOP").listener(new OnBMClickListener() {
                                @Override
                                public void onBoomButtonClick(int index) {

                                }
                            });
                    bmb.addBuilder(builder1);
                    break;
                case 2:
                    TextInsideCircleButton.Builder builder2 = new TextInsideCircleButton.Builder()
                            .normalImageRes(R.drawable.ic_close)
                            .normalText("fuckerpusc").listener(new OnBMClickListener() {
                                @Override
                                public void onBoomButtonClick(int index) {

                                }
                            });
                    bmb.addBuilder(builder2);
            }
        }

    }


    public void LoginDialog(){
        loginDialog = new Dialog(HomeActivity.this);
        loginDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        loginDialog.setContentView(R.layout.logindialog);
        loginDialog.setTitle("Login ;)");

        username =  loginDialog.findViewById(R.id.input_username);
        passwordInput = loginDialog.findViewById(R.id.input_password2);
        forgotPass = loginDialog.findViewById(R.id.txt_forgot);
        login =  loginDialog.findViewById(R.id.btn_login);
        signUp = loginDialog.findViewById(R.id.btn_signup);

        login.setEnabled(true);
        signUp.setEnabled(true);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = username.getText().toString();
                final String password = passwordInput.getText().toString();

                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(getApplicationContext(), "Enter email address!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(getApplicationContext(), "Enter password!", Toast.LENGTH_SHORT).show();
                    return;
                }

                //authenticate user
                auth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(HomeActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {

                                if (!task.isSuccessful()) {
                                    // there was an error
                                    if (password.length() < 6) {
                                        passwordInput.setError(getString(R.string.minimum_password));
                                    } else {
                                        Toast.makeText(HomeActivity.this, getString(R.string.auth_failed), Toast.LENGTH_LONG).show();
                                    }
                                    System.out.println(task.getException().getMessage().toString());
                                }
                                else
                                {
                                    loginDialog.cancel();
                                    Toast.makeText(HomeActivity.this, "LOGGED IN", Toast.LENGTH_LONG).show();
                                }
                            }
                        });
            }
        });

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SignUpDialog();
            }
        });

        loginDialog.show();
    }



    public void SignUpDialog(){
        signDialog = new Dialog(HomeActivity.this);
        signDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        signDialog.setContentView(R.layout.signupdialog);
        signDialog.setTitle("Signup ;)");

        usernameInput =  signDialog.findViewById(R.id.input_address);
        emailInput =  signDialog.findViewById(R.id.input_email);
        passwordInput2 = signDialog.findViewById(R.id.input_password2);
        reEnterPasswordInput = signDialog.findViewById(R.id.input_reEnterPassword);
        createAcc = signDialog.findViewById(R.id.btn_signup);
        linkLogin = signDialog.findViewById(R.id.link_login2);

        login.setEnabled(true);
        signUp.setEnabled(true);
        linkLogin.setEnabled(true);

        createAcc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

              if (!validate()) {
                  onSignupFailed();
                  return;
              }

              createAcc.setEnabled(false);

              final String userName = usernameInput.getText().toString().trim();
              final String email = emailInput.getText().toString().trim();
              final String password = passwordInput2.getText().toString().trim();

              // TODO: Implement your own signup logic here.
              auth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                  @Override
                  public void onComplete(@NonNull Task<AuthResult> task) {
                      boolean test = task.isSuccessful();
                      if (task.isSuccessful()){
                          String user_id = auth.getCurrentUser().getUid();

                          DatabaseReference current_user = mDatabase.child(user_id);


                          HashMap<String, String> users = new HashMap<String, String>();
                          users.put("Name", userName);

                          users.put("Email", email);
                          users.put("Password", password);

                          current_user.setValue(users);

                          signDialog.cancel();
                          loginDialog.cancel();

                      }

                  }



              });

              linkLogin.setOnClickListener(new View.OnClickListener() {
                  @Override
                  public void onClick(View view) {
                      signDialog.cancel();
                  }

              });


        /*Query query = mDatabase.orderByChild("Email").equalTo(email);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {

                    Toast.makeText(getBaseContext(), "Email already registered", Toast.LENGTH_LONG).show();
                    onSignupFailed();
                }
                else{
                    mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            boolean test = task.isSuccessful();
                            if (task.isSuccessful()){
                                String user_id = mAuth.getCurrentUser().getUid();

                                DatabaseReference current_user = mDatabase.child(user_id);


                                HashMap<String, String> users = new HashMap<String, String>();
                                users.put("Name", userName);
                                users.put("Email", email);
                                users.put("Password", password);

                                current_user.setValue(users);
                                progressDialog.dismiss();
                                Log.w(TAG, "signInWithEmail:failed", task.getException());
                            }


                        }



                    });
                    onSignupSuccess();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }*/
          }
      });



        signDialog.show();
    }

    public void onSignupFailed() {
        Toast.makeText(getBaseContext(), "Login failed", Toast.LENGTH_LONG).show();

        signUp.setEnabled(true);
    }

    public boolean validate() {
        boolean valid = true;

        String name = usernameInput.getText().toString();
        String email = emailInput.getText().toString();
        String password = passwordInput2.getText().toString();
        String reEnterPassword = reEnterPasswordInput.getText().toString();

        if (name.isEmpty() || name.length() < 3) {
            usernameInput.setError("at least 3 characters");
            valid = false;
        } else {
            usernameInput.setError(null);
        }

        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            emailInput.setError("enter a valid email address");
            valid = false;
        } else {
            emailInput.setError(null);
        }


        if (password.isEmpty() || password.length() < 4 || password.length() > 25) {
            passwordInput2.setError("between 4 and 25 alphanumeric characters");
            valid = false;
        } else {
            passwordInput2.setError(null);
        }

        if (reEnterPassword.isEmpty() || reEnterPassword.length() < 4 || reEnterPassword.length() > 25 || !(reEnterPassword.equals(password))) {
            reEnterPasswordInput.setError("Password Do not match");
            valid = false;
        } else {
            reEnterPasswordInput.setError(null);
        }

        return valid;

    }
}





