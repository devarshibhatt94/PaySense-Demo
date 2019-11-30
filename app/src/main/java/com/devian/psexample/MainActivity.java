package com.devian.psexample;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.devian.network.APIResponse;
import com.devian.network.PSApiClient;
import com.devian.network.PixaBayAPI;

public class MainActivity extends AppCompatActivity {

    EditText searchText;
    Button searchBtn;
    PixaBayAPI mInterface;
    RecyclerView recyclerView;
    ImageRecyclerAdapter imageRecyclerAdapter;
    ProgressBar progressBar;
    ImageFragment imageFragment;

    ImageClickListener listener = new ImageClickListener() {
        @Override
        public void onImageClick(String url) {
            imageFragment = new ImageFragment();
            Bundle bundle = new Bundle();
            bundle.putString("url", url);
            imageFragment.setArguments(bundle);
            loadFragment(imageFragment);
        }
    };

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mInterface = PSApiClient.getPSClient().create(PixaBayAPI.class);

        searchBtn = (Button) findViewById(R.id.searchBtn);
        searchText = (EditText) findViewById(R.id.searchText);

        searchText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    searchText.clearFocus();
                    InputMethodManager in = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                    in.hideSoftInputFromWindow(searchText.getWindowToken(), 0);
                    return true;
                }
                return false;
            }
        });

        recyclerView = (RecyclerView) findViewById(R.id.imageRecyclerView);
        recyclerView.setAdapter(null);

        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                searchText.clearFocus();
                InputMethodManager in = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                in.hideSoftInputFromWindow(searchText.getWindowToken(), 0);

                if(!Utils.isConnectedToInternet(MainActivity.this)){
                    Toast.makeText(MainActivity.this, "No Internet connection", Toast.LENGTH_SHORT).show();
                    return;
                }

                Call<APIResponse> call = mInterface.getImagesforQuery(searchText.getText().toString(), "photo");
                call.enqueue(new Callback<APIResponse>() {
                    @Override
                    public void onResponse(Call<APIResponse> call, Response<APIResponse> response) {
                        Log.d(TAG, "onResponse: ");
                        APIResponse apiResponse = response.body();
                        imageRecyclerAdapter = new ImageRecyclerAdapter(MainActivity.this, apiResponse.getHits(), listener);
                        recyclerView.setLayoutManager(new GridLayoutManager(MainActivity.this, 3, GridLayoutManager.VERTICAL, false));
                        recyclerView.setAdapter(imageRecyclerAdapter);
                    }

                    @Override
                    public void onFailure(Call<APIResponse> call, Throwable t) {
                        Log.d(TAG, "onFailure: ");
                    }
                });
            }
        });
    }

    @Override
    public void onBackPressed() {
        if (getFragmentManager().getBackStackEntryCount() == 0) {
            this.finish();
        } else {
            getFragmentManager().popBackStack();
        }
    }

    private void loadFragment(Fragment fragment) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        ((DialogFragment) fragment).show(fragmentTransaction, null);
    }
}
