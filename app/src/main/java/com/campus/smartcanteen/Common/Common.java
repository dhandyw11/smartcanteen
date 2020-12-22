package com.campus.smartcanteen.Common;

import com.campus.smartcanteen.Model.User;

public class Common {
    public static User currentUser;

    public static String convertCodeToStatus (String code) {
        if (code.equals("0")){
            return "Belum Bayar";
        }
        else if (code.equals("1")){
            return "Sudah Bayar";
        }
        else {
            return "Error";
        }
    }
}
