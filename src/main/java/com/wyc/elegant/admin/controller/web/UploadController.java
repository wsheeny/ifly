package com.wyc.elegant.admin.controller.web;

import com.wyc.elegant.admin.common.R;
import com.wyc.elegant.admin.controller.BaseController;
import com.wyc.elegant.admin.exception.MyException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * 文件上传
 *
 * @author Yeeep
 * @date 2020/11/29
 */
@Slf4j
@Api(tags = "上传")
@RestController
@RequestMapping("/upload")
public class UploadController extends BaseController {


    /**
     * 文件上传
     *
     * @param file
     * @return
     */
    @PostMapping("/file")
    @ApiOperation(value = "文件上传", notes = "")
    public R upload(@RequestParam("file") MultipartFile file) {
        if (StringUtils.isEmpty(file)) {
            throw new MyException().code(40000).message("文件为空");
        }
        try {
            // 文件名
            String filename = file.getOriginalFilename();
            // 文件路径
            String filePath = "D:\\temp";

            File newFile = new File(filePath, Objects.requireNonNull(filename));
            if (!newFile.exists()) {
                log.info("检测到文件保存路径不存在，系统将为你自动创建目录");
                newFile.mkdirs();
            }
            file.transferTo(newFile);
            log.info("文件上传成功");
            Map<String, Object> map = new HashMap<>();
            map.put("originalURL", "");
            map.put("url", filePath + "\\" + filename);

            return R.ok().data(map).message("文件上传成功");
        } catch (IOException e) {
            e.printStackTrace();
        }

        return R.error().message("文件上传失败").code(50000);
    }

}
