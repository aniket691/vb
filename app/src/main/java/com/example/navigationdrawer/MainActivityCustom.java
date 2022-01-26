package com.example.navigationdrawer;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.navigationdrawer.R.id;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;

import java.util.HashMap;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class MainActivityCustom extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private AppBarConfiguration mAppBarConfiguration;
    private NavController navController;
    private DrawerLayout drawer;

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main_custom);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = findViewById(id.drawer);
        NavigationView navigation_view = findViewById(id.navigation_view);

        navController = Navigation.findNavController(this, id.nav_host_fragment);

        NavigationUI.setupWithNavController(navigation_view, navController);
        NavigationUI.setupActionBarWithNavController(MainActivityCustom.this,
                navController, drawer);

        navigation_view.setNavigationItemSelectedListener(MainActivityCustom.this);
        View headerView = navigation_view.getHeaderView(0);

        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
        if (account != null) updateUI(account, headerView, navigation_view);


    }

    private void updateUI(GoogleSignInAccount account, View headerView, NavigationView navigationView) {

        TextView name = (TextView) headerView.findViewById(R.id.name);
        TextView email = (TextView) headerView.findViewById(R.id.email);
        name.setText(account.getDisplayName());
        email.setText(account.getEmail());
    }


    @Override
    public boolean onSupportNavigateUp() {
        return super.onSupportNavigateUp() || NavigationUI.navigateUp(navController, drawer);
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        drawer.closeDrawers();
        switch (menuItem.getItemId()) {
            case id.nav_home:
                Toast.makeText(getApplicationContext(), "Hello", Toast.LENGTH_SHORT).show();
                navController.navigate(id.mainFragment);
                break;

            case id.about:
                Toast.makeText(getApplicationContext(), "Hello", Toast.LENGTH_SHORT).show();
                navController.navigate(id.aboutFragment);
                break;


            case id.team:
                Toast.makeText(getApplicationContext(), "Hello", Toast.LENGTH_SHORT).show();
                navController.navigate(id.teamFragment);
                break;

            case id.contact:
                Toast.makeText(getApplicationContext(), "Hello", Toast.LENGTH_SHORT).show();
                navController.navigate(id.contactFragment);
                break;


            case id.logout:
                GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                        .requestEmail()
                        .requestIdToken("1049772544995-u20nnnu5fohved981mdqoish31mmq62p.apps.googleusercontent.com")
                        .build();
                // Build a GoogleSignInClient with the options specified by gso.
                GoogleSignInClient mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
                endActivity(mGoogleSignInClient);
                break;
        }
        return true;
    }

    private void endActivity(GoogleSignInClient mGoogleSignInClient) {
        mGoogleSignInClient.signOut();
        startActivity(new Intent(getApplicationContext(), SignIn.class));
        finish();
    }
}
