package com.example.myproject.controller;

import cn.hutool.core.util.StrUtil;
import com.example.myproject.pojo.Result;
import com.example.myproject.pojo.ResultUtil;
import com.example.myproject.utils.ImageUtil;
import com.example.myproject.utils.QiniuUtil;
import com.example.myproject.utils.ResultEnum;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

@Slf4j
@RestController
@RequestMapping("/api/file")
@Api(description = "文件上传")
public class UploadController {

    /**
     * 在配置文件中配置的文件保存路径
     */
    @Value("${spring.img.location}")
    private String location;

    @Autowired
    private QiniuUtil qiniuUtil;

    private static final Logger logger = LoggerFactory.getLogger(UploadController.class);

    @RequestMapping(value = "/img/upload", method = RequestMethod.PUT)
    @ApiOperation(value = "上传文件")
    public Result<Object> uploadImg(@RequestParam("file") MultipartFile multipartFile) {
        if (multipartFile.isEmpty() || StrUtil.isBlank(multipartFile.getOriginalFilename())) {
            return new ResultUtil<Object>().setErrorResultEnum(ResultEnum.IMG_NOT_EMPTY);
        }
        String contentType = multipartFile.getContentType();
        if (!contentType.contains("")) {
            return new ResultUtil<Object>().setErrorResultEnum(ResultEnum.IMG_FORMAT_ERROR);
        }
        String root_fileName = multipartFile.getOriginalFilename();
        logger.info("上传图片:name={},type={}", root_fileName, contentType);

        //获取路径
        String return_path = "upload_image";
        String filePath = location + return_path;
        logger.info("图片保存路径={}", location);
        String file_name = null;
        try {
            file_name = ImageUtil.saveImg(multipartFile, filePath);
            if (StrUtil.isBlank(file_name)) {
                return new ResultUtil<Object>().setErrorResultEnum(ResultEnum.IMG_NOT_EMPTY);
            }
            return new ResultUtil<Object>().setData(return_path + File.separator + file_name, "图片上传成功");
        } catch (IOException e) {
            return new ResultUtil<Object>().setErrorResultEnum(ResultEnum.IMG_NOT_EMPTY);
        }
    }

    @RequestMapping(value = "/qiniu/upload", method = RequestMethod.POST)
    @ApiOperation(value = "七牛云文件上传")
    public Result<Object> upload(@RequestParam("file") MultipartFile file,
                                 HttpServletRequest request) {

        // IP限流 在线Demo所需 5分钟限1个请求
//        String token1 = redisRaterLimiter.acquireTokenFromBucket("upload:"+IpInfoUtil.getIpAddr(request), 1, 300000);
//        if (StrUtil.isBlank(token1)) {
//            throw new XbootException("上传那么多干嘛，等等再传吧");
//        }

        String imagePath = null;
        String fileName = qiniuUtil.renamePic(file.getOriginalFilename());
        try {
            FileInputStream inputStream = (FileInputStream) file.getInputStream();
            //上传七牛云服务器
            imagePath = qiniuUtil.qiniuInputStreamUpload(inputStream, fileName);
            if (StrUtil.isBlank(imagePath)) {
                return new ResultUtil<Object>().setErrorResultEnum(ResultEnum.IMG_QINIUYUN_EMPTY);
            }
            if (imagePath.contains("error")) {
                return new ResultUtil<Object>().setErrorMsg(imagePath);
            }
        } catch (Exception e) {
            log.error(e.toString());
            return new ResultUtil<Object>().setErrorMsg(e.toString());
        }

        return new ResultUtil<Object>().setData(imagePath);
    }
}
