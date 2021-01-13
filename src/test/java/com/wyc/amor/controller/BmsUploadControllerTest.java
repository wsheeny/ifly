package com.wyc.amor.controller;

import com.qiniu.util.Auth;
import org.junit.jupiter.api.Test;

class BmsUploadControllerTest {
    public static final String AccessKey = "QeVlxSNnxDnEAkZZsFG2-mEOrl7GzzhGnHhHiFUm";
    public static final String SecretKey = "btvTk_P4rq4-_NTqhlB9se_A1A4yrmJzygRI0LwX";


    @Test
    void s() {
        String bucket = "wangzh1guo";
        Auth auth = Auth.create(AccessKey, SecretKey);
        String upToken = auth.uploadToken(bucket);
        System.out.println(upToken);
    }

}
