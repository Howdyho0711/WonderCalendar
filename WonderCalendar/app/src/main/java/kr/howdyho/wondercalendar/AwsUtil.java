package kr.howdyho.wondercalendar;
import android.content.Context;
import com.amazonaws.auth.CognitoCachingCredentialsProvider;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.*;
import com.amazonaws.services.dynamodbv2.model.*;

/**
 * Created by Hyun on 2016-04-16.
 */
public class AwsUtil {

    private static CognitoCachingCredentialsProvider sCredentialsProvider;

    public static CognitoCachingCredentialsProvider getCredProvider (Context context) {
        if(sCredentialsProvider == null) {
            sCredentialsProvider = new CognitoCachingCredentialsProvider(
                    context.getApplicationContext(),
                    "080227813135", // AWS Account ID
                    "ap-northeast-1:ed237a7d-156f-4385-b021-3092081d75ce",// Identity Pool ID
                    "arn:aws:iam::080227813135:role/Cognito_WonderCalendarAuth_Role", // Auth Role
                    "arn:aws:iam::080227813135:role/Cognito_WonderCalendarUnauth_Role", // Unauth Role
                    Regions.AP_NORTHEAST_1 // Region
            );
        }
        return sCredentialsProvider;
    }
}
