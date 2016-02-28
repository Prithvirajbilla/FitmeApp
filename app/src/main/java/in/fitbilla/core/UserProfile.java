package in.fitbilla.core;


import com.google.gson.annotations.SerializedName;

public class UserProfile {

    @SerializedName("user_id")
    private int userId;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
