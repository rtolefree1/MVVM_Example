package com.tutsplus.mvvm_example;

import android.content.DialogInterface;
import android.content.Intent;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

//import com.google.android.gms.tasks.OnCompleteListener;
//import com.google.android.gms.tasks.Task;
//import com.google.firebase.auth.AuthResult;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.tutsplus.mvvm_example.databinding.ActivityMainBinding;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainViewModel extends BaseObservable {

    //Creating object of Model class
    private Model model;
    private static final String TAG = "MainActivity";
    private FirebaseAuth mAuth;
    private boolean isPasswordVisible = false;
    private MainActivity mainActivity;

    public MainViewModel(){
        model = new Model("","");
    }

    // getter and setter for Email
    @Bindable
    public String getUserEmail(){
        return model.getEmail();
    }

    public void setUserEmail(String email){
        model.setEmail(email);
    }

    // getter and setter for password
    @Bindable
    public String getPassword(){
        return model.getPassword();
    }

    private void inValidPasswordDialogBox() {
        // Create an AlertDialog builder
        AlertDialog.Builder builder = new AlertDialog.Builder(mainActivity);

        // Set the title and message of the dialog
        builder.setTitle("Invalid Password");
        builder.setMessage("Please enter a valid password\n" +
                "Password must be at least 4 characters \n" +
                "long and contain at least one uppercase \n" +
                "letter, one digit, and one special character");
        builder.setCancelable(false);

        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        // Create and show the dialog
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void inValidUserDialogBox() {
        // Create an AlertDialog builder
        AlertDialog.Builder builder = new AlertDialog.Builder(mainActivity);

        // Set the title and message of the dialog
        builder.setTitle("Invalid Email");
        builder.setMessage("Please enter a valid email address\n" +
                "Example format: john.catron@example-pet-store.com");
        builder.setCancelable(false);

        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        // Create and show the dialog
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    public boolean isValidEmail(String email){
//        String regex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
        return email != null && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    private void emptyPasswordDialogBox() {
        // Create an AlertDialog builder
        AlertDialog.Builder builder = new AlertDialog.Builder(mainActivity);

        // Set the title and message of the dialog
        builder.setTitle("Invalid Password - Empty");
        builder.setMessage("Please enter a valid password\n" +
                "Password must be at least 4 characters \n" +
                "long and contain at least one uppercase \n" +
                "letter, one digit, and one special character");
        builder.setCancelable(false);

        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        // Create and show the dialog
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void emptyUserDialogBox() {
        // Create an AlertDialog builder
        AlertDialog.Builder builder = new AlertDialog.Builder(mainActivity);

        // Set the title and message of the dialog
        builder.setTitle("Invalid Email - Empty");
        builder.setMessage("Please enter a valid email address\n" +
                "Example format: john.catron@example-pet-store.com");
        builder.setCancelable(false);

        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        // Create and show the dialog
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    public void setPassword(String password){
        model.setPassword(password);
        notifyPropertyChanged(BR.password);
    }



    public void clearFields(View view) {
        model.setUserName("");
        model.setPassword("");
    }

    public void signUp() {
        Log.d(TAG, "signUp: Registered");
//        Intent intent = new Intent(this, SignUpPage.class);
//        startActivity(intent);
    }

//    @Override
//    public void onClick(View v) {
//        if(isPasswordVisible){
//            binding.PasswordEditTextView.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
//            isPasswordVisible = false;
//        }else{
//            binding.PasswordEditTextView.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
//            isPasswordVisible = true;
//        }
//
//    }

    public void login(View view) {

        assert model.getEmail() != null;
        if (model.getEmail().isEmpty()) {
            emptyUserDialogBox();
            return;
        }

        if (!isValidEmail(model.getUsername().toString())) {
            // if invalid email format, then return
            inValidUserDialogBox();
            return;

        }
        if (model.getPassword().toString().isEmpty()) {
            emptyPasswordDialogBox();
            return;
        }

//        if(!isValidPassword(model.getPassword().toString())){
//            inValidPasswordDialogBox();
//            return;
//        }

        mAuth = FirebaseAuth.getInstance();
        mAuth.getCurrentUser();

//        mAuth.signInWithEmailAndPassword(model.getUsername().toString(), model.getPassword().toString())
//                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
//                    @Override
//                    public void onComplete(@NonNull Task<AuthResult> task) {
//                        if (task.isSuccessful()) {
//// Sign in success, update UI with the signed-in user's information
//                            Log.d(TAG, "signInWithEmail:success");
//                            FirebaseUser user = mAuth.getCurrentUser();
////                            updateUI(user);
//                        } else {
//// If sign in fails, display a message to the user.
//                            Log.w(TAG, "signInWithEmail:failure", task.getException());
//                            Toast.makeText(mainActivity, "Authentication failed.",
//                                    Toast.LENGTH_SHORT).show();
////                            updateUI(null);
//                        }
//                    }
//                });
//        mAuth.signInWithEmailAndPassword(model.getUsername().toString(), model.getPassword().toString()).addOnCompleteListener(this, new OnCompleteListener<AuthResult>()
//        {
//            @Override
//            public void onComplete(@NonNull Task<AuthResult> task)
//            {
////                    Log.d(TAG, "signInWithEmail:success" + task.getResult());
//                if (task.isSuccessful())
//                {
//                    FirebaseUser user = mAuth.getCurrentUser();
//                    Toast.makeText(mainActivity, "Sign-In Successful", Toast.LENGTH_SHORT).show();
//                    Log.d("LoginActivity", "signInWithEmail:success");
////                        Intent intent = new Intent(this, HomePage.class);
////                        intent.putExtra("username", binding.UserNameEditTextView.getText().toString());
////                        intent.putExtra("password", binding.PasswordEditTextView.getText().toString());
//                    clearFields(null);
////                        startActivity(intent);
//                    //updateUI(user);
//                } else
//                {
//                    Log.w("LoginActivity", "signInWithEmail:failure", task.getException());
//                    Toast.makeText(mainActivity, "Authentication Failed: " + task.getException().getMessage(), Toast.LENGTH_LONG).show();
////                        updateUI(null);
//                }
////                });
//
////        Toast.makeText(this, "Login button clicked", Toast.LENGTH_SHORT).show();
////        Log.d(TAG, "Login button clicked, username:" + binding.UserNameEditTextView.getText().toString() + ", password:" + binding.PasswordEditTextView.getText().toString());
//
//            }
//        });
    }
    public boolean isValidPassword(String password)
    {

        //  ^: Start of the string.
        //  (?=.*[A-Z]): At least one uppercase letter.
        //  (?=.*\\d): At least one digit.
        //  (?=.*[@$!%*?&]): At least one special character.
        //  [A-Za-z\\d@$!%*?&]: Allowed characters.
        //  {4,}: Minimum length of 4 characters.
        //  $: End of the string.

        String regex = "^(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{4,}$";

        // Compile the pattern
        Pattern pattern = Pattern.compile(regex);

        // Match the password with the pattern
        Matcher matcher = pattern.matcher(password);

        // Return true if the password matches the pattern, false otherwise
        return matcher.matches();


    }


}
