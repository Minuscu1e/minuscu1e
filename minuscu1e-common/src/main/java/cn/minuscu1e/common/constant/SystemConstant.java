package cn.minuscu1e.common.constant;

public class SystemConstant {

    public static final String SYSTEM_NAME_URL = "/minuscu1e";
    public static final String SYSTEM_API_VERSION = "/v1";
    public static final String SYSTEM_BASE_URL = SYSTEM_NAME_URL + SYSTEM_API_VERSION;

    public static final String SYSTEM_ID_PREFIX = "SF";
    public static final String DOT = ".";
    public static final String URL_SEPARATOR = "/";
    public static final String IMAGE = "image";

    public static final String DOWNLOAD_CONTENT_TYPE = "application/octet-stream";

    public static final Long SUCCESS_CODE = 200L;
    public static final String SUCCESS_MSG = "Operation success";


    public static final Long UPDATE_FAIL_CODE = 401L;
    public static final String UPDATE_FAIL_MSG = "Update fail.";

    public static final Long SERVER_ERROR_CODE = 500L;
    public static final String SERVER_ERROR_MSG = "Server error.";


    public static final Long FAIL_CODE = 400L;
    public static final String FAIL_MSG = "Operation fail.";
    public static final Long ERROR_PARAM_CODE = 400001L;
    public static final String ERROR_PARAM_MSG = "Unsupported parameter.";
    public static final String FAIL_LOGIN_MSG = "Login fail.";
    public static final String PERMISSION_DENIED = "Permission denied.";
    public static final String NOT_FOUND_USER = "Not found user.";
}
