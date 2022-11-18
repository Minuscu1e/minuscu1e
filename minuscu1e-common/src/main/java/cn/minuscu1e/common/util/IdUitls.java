package cn.minuscu1e.common.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class IdUitls {

    public static Long generateId() {
        final SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        return Long.parseLong(sdf.format(new Date()) + "0001");
    }
}
