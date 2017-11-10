package com.kami.lepau;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by Holy on 09-Nov-17.
 */

public class FragmentSignIn extends Fragment {
    private EditText emailEditText;
    private EditText passwordEditText;
    private Button signInButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_signin, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        emailEditText = (EditText) getView().findViewById(R.id.fragmentSignIn_emailEditText);
        passwordEditText = (EditText) getView().findViewById(R.id.fragmentSignIn_passwordEditText);
        signInButton = (Button) getView().findViewById(R.id.fragmentSignIn_signInButton);

        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MenuActivity.class);
                startActivity(intent);
            }
        });
    }
}
