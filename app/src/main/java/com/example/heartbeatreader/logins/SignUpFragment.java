package com.example.heartbeatreader.logins;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.heartbeatreader.FirebaseServices;
import com.example.heartbeatreader.ProfileFragment;
import com.example.heartbeatreader.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SignUpFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SignUpFragment extends Fragment {

    private Button signUp ;
    private ImageView gobackimg;
    EditText Password , Email , Cpassword ;
    private FirebaseServices fbs;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public SignUpFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SignUpFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SignUpFragment newInstance(String param1, String param2) {
        SignUpFragment fragment = new SignUpFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sign_up, container, false);
    }
    @Override
    public void onStart() {
        super.onStart();
        connenctcomponents();
    }

    private void connenctcomponents() {
        signUp = getView().findViewById(R.id.btnsignup) ;
        Email = getView().findViewById(R.id.etemail1) ;
        Password = getView().findViewById(R.id.etpass1) ;
        Cpassword = getView().findViewById(R.id.etconpass) ;
        gobackimg=getView().findViewById(R.id.gobackimg);
        fbs = FirebaseServices.getInstance();
        gobackimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gotologin();
            }
        });
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = Email.getText().toString();
                String password = Password.getText().toString();
                String cpassword = Cpassword.getText().toString();
                if(email.trim().isEmpty() || password.trim().isEmpty() || cpassword.trim().isEmpty())
                {
                    Toast.makeText(getActivity(), "fill everything pls", Toast.LENGTH_SHORT).show();
                    return;
                }
                String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

// onClick of button perform this simplest code.
                if (!email.matches(emailPattern))
                {
                    Toast.makeText(getActivity(),"Invalid email address", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (!isValidPassword(password))
                {
                    Toast.makeText(getActivity(),"Invalid Password", Toast.LENGTH_SHORT).show();
                    return;
                }
                fbs.getAuth().createUserWithEmailAndPassword(email , password).addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(getActivity(), "welcome to the new world", Toast.LENGTH_SHORT).show();
                            FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                            ft.replace(R.id.frameMain, new ProfileFragment());
                            ft.commit();
                        } else {
                            Toast.makeText(getActivity(), "Wrong email or password", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            }

        });
    }
    private void gotologin(){
        FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.frameMain, new LoginFragment());
        ft.commit();
    }
    public static boolean isValidPassword(final String password) {

        Pattern pattern;
        Matcher matcher;
        final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{4,}$";
        pattern = Pattern.compile(PASSWORD_PATTERN);
        matcher = pattern.matcher(password);

        return matcher.matches();
    }
}