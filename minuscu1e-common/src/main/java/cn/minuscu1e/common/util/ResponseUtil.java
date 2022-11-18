package cn.minuscu1e.common.util;

import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class ResponseUtil {

    /**
     * 请求头的共有设置
     *
     * @param response
     * @param isDownload
     */
    public static void downloadOrPreviewSetting(HttpServletResponse response,
                                                String filename,
                                                Long filesize,
                                                String contentType,
                                                boolean isDownload) throws UnsupportedEncodingException {
        String inLineOrAttach;
        inLineOrAttach = isDownload ? "attachment" : "inline";
        response.setCharacterEncoding("UTF-8");
        response.addHeader("Content-Disposition", inLineOrAttach + ";filename=" + URLEncoder.encode(filename, "UTF-8"));
        response.addHeader("Content-Length", String.valueOf(filesize));

        response.setContentType(contentType);
    }
}
