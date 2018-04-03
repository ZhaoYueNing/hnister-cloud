package cn.zynworld.hnister.file.rest;

import com.qiniu.util.Auth;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by zhaoyuening on 2018/2/22.
 * 负责文件上传的鉴权等操作
 */
@RestController
@RequestMapping(path = "rest")
public class FileUploadRest {

    @Value("${qiniu.ak}")
    private String QINIU_AK;
    @Value("${qiniu.sk}")
    private String QINIU_SK;
    @Value("${qiniu.bucket}")
    private String QINIU_BUCKET;

    //获取七牛云上传token
    @GetMapping(path = "df/upToken")
    public String getUpToken(@RequestParam(name = "key",required = false) String key) {
        Auth auth = Auth.create(QINIU_AK, QINIU_SK);
        String upToken = "";
        if (!StringUtils.isEmpty(key)){
            //请求参数携带key是覆盖上传upToken
            upToken = auth.uploadToken(QINIU_BUCKET,key);
        }else{
            upToken = auth.uploadToken(QINIU_BUCKET);
        }
        return upToken;
    }

}
