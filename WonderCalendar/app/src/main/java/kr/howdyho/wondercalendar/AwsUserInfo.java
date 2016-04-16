package kr.howdyho.wondercalendar;

import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.*;
/**
 * Created by Hyun on 2016-04-16.
 */
@DynamoDBTable(tableName = "User")
public class AwsUserInfo {
    private String UserID;
    private String UserPW;

    @DynamoDBHashKey(attributeName = "UserId")
    public String getUserID() {
        return UserID;
    }

    public void setUserID(String id) {
        this.UserID = id;
    }

    @DynamoDBAttribute(attributeName = "UserPW")
    public String getUserPW() {
        return UserPW;
    }

    public void setUserPW(String pw) {
        this.UserPW = pw;
    }
    // etc
}
