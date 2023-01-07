package com.toy.cosmos.auth.value;

public class Regex {

    public static final String EMAIL = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";

    public static final String MOBILE = "/^\\d{3}-\\d{3,4}-\\d{4}$/";

    public static final String PHONE = "/^\\d{2,4}-\\d{3,4}-\\d{4}$/";

    public static final String DATE_yyyy_MM_dd = "^((19|20)\\d\\d)?([- /.])?(0[1-9]|1[012])([- /.])?(0[1-9]|[12][0-9]|3[01])$";

    public static final String yyyyMMdd = "^([12]\\d{3}(0[1-9]|1[0-2])(0[1-9]|[12]\\d|3[01]))$";
}
