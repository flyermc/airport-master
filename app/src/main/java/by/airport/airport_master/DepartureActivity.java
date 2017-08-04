package by.airport.airport_master;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TableRow;
import android.widget.TextView;

import by.airport.airport_master.helpers.Statuses;
import by.airport.airport_master.utils.Globals;
import by.airport.airport_master.utils.StringUtils;

public class DepartureActivity extends AppCompatActivity {
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_departure);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_action_back);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        ImageView cover = (ImageView) findViewById(R.id.cover_image);
        TextView city = (TextView) findViewById(R.id.city);
        TextView code = (TextView) findViewById(R.id.code);
        TextView company = (TextView) findViewById(R.id.company);
        ImageView company_img = (ImageView) findViewById(R.id.company_img);
        TextView timeScheduled = (TextView) findViewById(R.id.scheduled);
        TextView timeActual = (TextView) findViewById(R.id.actual);
        TextView status = (TextView) findViewById(R.id.status);
        TextView gate = (TextView) findViewById(R.id.gate);


        int imgRes = R.drawable.ic_takeoff;
        city.setCompoundDrawablesWithIntrinsicBounds(imgRes, 0, 0, 0);

        if (city != null) {
            String format_city = StringUtils.replaceSpecialChars(Globals.departureInfo.getCity());
            int resourceId = getResources().getIdentifier(
                    format_city,
                    "string",
                    getPackageName());
            city.setText(Globals.departureInfo.getCity());

            if (resourceId > 0) {
                city.setText(getResources().getString(resourceId));
            }
            int imageResourceID = getResources().getIdentifier(
                    format_city.toLowerCase(),
                    "drawable",
                    getPackageName());
            if (imageResourceID > 0) {
                cover.setImageResource(imageResourceID);
            }
        }

        if (code != null) {
            code.setText(Globals.departureInfo.getCode());
        }

        if (company != null) {
            String format_company = StringUtils.replaceSpecialChars(Globals.departureInfo.getCompany());
            company.setText(Globals.departureInfo.getCompany());
            int imageResourceID = getResources().getIdentifier(
                    format_company.toLowerCase(),
                    "drawable",
                    getPackageName());
            if (imageResourceID > 0) {
                company_img.setVisibility(View.VISIBLE);
                company.setVisibility(View.GONE);
                company_img.setImageResource(imageResourceID);
            } else {
                company.setVisibility(View.VISIBLE);
                company_img.setVisibility(View.GONE);
            }
        }

        if (timeScheduled != null) {
            timeScheduled.setText(Globals.departureInfo.getExpectedTime());
        }

        if (timeActual != null && Globals.departureInfo.getActualTime() != null) {
            timeActual.setText(Globals.departureInfo.getActualTime());
        }

        if (status != null && Globals.departureInfo.getStatus() != null) {
            Statuses tmpStatus = Globals.departureInfo.getStatus();
            int resourceId = getResources()
                    .getIdentifier(StringUtils
                                    .replaceSpecialChars(tmpStatus.name()),
                            "string",
                            getPackageName());

            if (resourceId > 0) {
                status.setText(getResources().getString(resourceId));
            }
        }

        if (gate != null && Globals.departureInfo.getGate() != null) {
            gate.setText(Globals.departureInfo.getGate());
        }
    }
}
