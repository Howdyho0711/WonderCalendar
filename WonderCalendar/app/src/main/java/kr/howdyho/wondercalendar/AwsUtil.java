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
                    "ap-northeast-1:ed237a7d-156f-4385-b021-3092081d75ce",// Identity Pool ID
                    Regions.AP_NORTHEAST_1 // Region
            );
        }
        return sCredentialsProvider;
    }
}
