package nl.pellegroot.restaurant;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;

public class MenuActivity extends AppCompatActivity implements MenuRequest.Callback {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        MenuRequest menReq = new MenuRequest(this);
        menReq.getMenu(this);
    }

    @Override
    public void gotMenu(ArrayList<MenuItem> menu) {

    }

    @Override
    public void gotMenuError(String message) {

    }
}
