package kr.howdyho.wondercalendar;

import android.content.Context;
import android.os.AsyncTask;

import com.amazonaws.auth.CognitoCachingCredentialsProvider;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;

/**
 * Created by Hyun on 2016-04-17.
 */
public class AwsCall extends AsyncTask<DynamoDBMapper, Void, Void> {

    @Override
    protected Void doInBackground(DynamoDBMapper... mapper) {

       // DynamoDBMapper mapper = new DynamoDBMapper(ddbClient[0]);

        //try {
            AwsUserInfo temp = mapper[0].load(AwsUserInfo.class, "wOnDErCAl2NdaRmaSTer");

            System.out.println("Load Item - ID : "+temp.getUserID()+" PW : "+temp.getUserPW());
        //} catch(Exception e) {
        //    System.out.println("Error on Load User Info");
        //}

        return null;
    }

}
