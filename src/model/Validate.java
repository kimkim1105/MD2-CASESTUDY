package model;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validate {
    public static final String USERNAME_REGEX = "^[a-z0-9]{6,}$";
//    public static final String NAME = "^[a-z0-9]";
    public static final String PASSWORD = "^[A-Z]{1}[a-zA-Z0-9]{5,}$";
    public static final String ROLE = "staff|admin|manager";
    public static final String WORKINGTYPE = "(fulltime|parttime)";
    public static final String STATUS = "(Dang lam viec|Nghi viec)";
//    public static final String ID = "^\\d{1,}";

    public static boolean isvalid(String str, String regex){
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(str);
        return matcher.matches();
    }
}
