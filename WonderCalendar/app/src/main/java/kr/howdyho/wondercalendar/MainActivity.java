package kr.howdyho.wondercalendar;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import com.amazonaws.auth.CognitoCachingCredentialsProvider;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBMapper;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.*;
import com.amazonaws.services.dynamodbv2.model.*;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;

public class MainActivity extends Activity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        CognitoCachingCredentialsProvider sCredential = AwsUtil.getCredProvider(getApplicationContext());

        AmazonDynamoDBClient ddbClient = new AmazonDynamoDBClient(sCredential);
        DynamoDBMapper mapper = new DynamoDBMapper(ddbClient);
        ddbClient.setRegion(Region.getRegion(Regions.AP_NORTHEAST_1));

        new AwsCall().execute(mapper);
    }
}
