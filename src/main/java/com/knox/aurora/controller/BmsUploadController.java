package com.knox.aurora.controller;

import com.alibaba.fastjson.JSON;
import com.knox.aurora.common.exception.ApiAsserts;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * 文件上传
 *
 * @author Knox
 * @date 2020/11/29
 */
@Slf4j
@Api(tags = "上传", value = "UploadController")
@RestController
@RequestMapping("/upload")
public class BmsUploadController extends BaseController {

    public static final String ACCESS_KEY = "QeVlxSNnxDnEAkZZsFG2-mEOrl7GzzhGnHhHiFUm";
    public static final String SECRET_KEY = "btvTk_P4rq4-_NTqhlB9se_A1A4yrmJzygRI0LwX";

    @PostMapping("/file")
    @ApiOperation(value = "文件上传")
    public Object upload(@RequestParam("my-file") MultipartFile file) {
        if (ObjectUtils.isEmpty(file)) {
            ApiAsserts.fail("文件为空");
        }
        // 文件名
        String filename = file.getOriginalFilename();
        // 文件路径
        String filePath = "D:\\temp";

        File newFile = new File(filePath, Objects.requireNonNull(filename));
        if (!newFile.exists()) {
            log.info("检测到文件保存路径不存在，系统将为你自动创建目录");
            newFile.mkdirs();
        }
        try {
            file.transferTo(newFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Map<String, Object> map = new HashMap<>(16);
        Map<String, Object> map2 = new HashMap<>(16);
        Map<String, Object> map3 = new HashMap<>(16);
        map3.put("avatar.png", "https://b3logfile.com/file/2020/12/avatar-5ddf504a.png");
        map2.put("succMap", map3);
        map2.put("errFiles", new ArrayList<>());

        map.put("msg", "ok");
        map.put("code", 200);
        map.put("data", map2);

        return JSON.toJSON(map);
    }
}
