package kr.howdyho.wondercalendar;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.amazonaws.auth.CognitoCachingCredentialsProvider;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBMapper;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBQueryExpression;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.PaginatedQueryList;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.*;
import com.amazonaws.services.dynamodbv2.model.*;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;

import org.w3c.dom.Text;

public class MainActivity extends Activity {

    private DynamoDBMapper mapper;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        // AWS Credential Setting
        CognitoCachingCredentialsProvider sCredential = AwsUtil.getCredProvider(getApplicationContext());

        AmazonDynamoDBClient ddbClient = new AmazonDynamoDBClient(sCredential);
        mapper = new DynamoDBMapper(ddbClient);
        ddbClient.setRegion(Region.getRegion(Regions.AP_NORTHEAST_2));

        // Log-In
        Button logIn = (Button)findViewById(R.id.login);

        // Button Clicked
        logIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Make temp User for saving User's Input
                AwsUserInfo tempUser = new AwsUserInfo();

                // Get Text Info
                EditText id = (EditText) findViewById(R.id.userId);
                EditText pw = (EditText) findViewById(R.id.userPass);

                //System.out.println("Main id : " + id.getText().toString());
                //System.out.println("Main pw : " + pw.getText().toString());

                // Check if there are "" in ID or PW space
                if(!id.getText().toString().equals("") && !pw.getText().toString().equals("")) {
                    tempUser.setUserID(id.getText().toString());
                    tempUser.setUserPW(pw.getText().toString());
                    //tempUser.setDBClient(ddbClient);

                    new AwsLogIn().execute(tempUser);


                } else {
                    Toast toast = Toast.makeText(getApplicationContext(), "아이디와 비밀번호를 입력해주세요.", Toast.LENGTH_LONG);
                    toast.show();
                }
            }
        });

    }

    private class AwsLogIn extends AsyncTask<AwsUserInfo, String,Void > {

        private String InputId;
        private String InputPw;

        @Override
        protected Void doInBackground(AwsUserInfo... infos) {

            // DynamoDBMapper mapper = new DynamoDBMapper(ddbClient[0]);

            System.out.println("AwsLogin AsyncTask");

            InputId = infos[0].getUserID();
            InputPw = infos[0].getUserPW();

            //System.out.println("InputID : " + InputId);
            //System.out.println("InputPW : " + InputPw);

            AwsUserInfo infoToFind = new AwsUserInfo();
            infoToFind.setUserID(InputId);
            infoToFind.setUserPW(InputPw);

            DynamoDBQueryExpression queryExpression = new DynamoDBQueryExpression()
                    .withHashKeyValues(infoToFind)
                    .withConsistentRead(false);

            PaginatedQueryList<AwsUserInfo> result = mapper.query(AwsUserInfo.class, queryExpression);

            if(result.size() < 1) {
                publishProgress("id");
            }

            else {
                if (InputPw.equals(result.get(0).getUserPW())) {
                    startActivity(new Intent(getApplication(), HomeActivity.class));
                } else {
                    publishProgress("pw");
                }
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(String... i) {
            if(i[0].equals("id")) {
                Toast toast = Toast.makeText(getApplicationContext(), "존재하지 않는 아이디입니다.", Toast.LENGTH_LONG);
                toast.show();
            } else {
                Toast toast = Toast.makeText(getApplicationContext(), "비밀번호가 잘못되었습니다.", Toast.LENGTH_LONG);
                toast.show();
            }
        }
    }
}
