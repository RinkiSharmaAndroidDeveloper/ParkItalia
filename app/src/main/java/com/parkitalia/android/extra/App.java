package com.parkitalia.android.extra;
//http://indotesting.com/parkme/webservices/user/sign_up?
public class App
{
    public static String BASE_URL = "http://indotesting.com/parkme/";
    public static String SIGN_UP = "webservices/user/sign_up?";
    public static String SIGN_IN = "webservices/user/sign_in?";

    public static String FACEBOOK_URL = BASE_URL + "fb_login_user/fb_user?";

    public static String GET_TERMS_SERVICE = BASE_URL + "termservice/get_termservice?id=";

    public static String GET_PRIVACY = BASE_URL + "privacy/get_privacy?id=";

    public static String WHATS_NEW = BASE_URL + "whatsnew/get_whatsnew?id=";

    public static String SEND_FEEDBACK = BASE_URL + "webservices/feedback/add_feedback?user_id=%s&message=%s";

}
