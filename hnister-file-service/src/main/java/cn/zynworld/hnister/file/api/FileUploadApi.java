package cn.zynworld.hnister.file.api;

import com.qiniu.util.Auth;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by zhaoyuening on 2018/2/22.
 * 负责文件上传的鉴权等操作
 */
@RestController
public class FileUploadApi {

    @Value("${qiniu.ak}")
    private String QINIU_AK;
    @Value("${qiniu.sk}")
    private String QINIU_SK;
    @Value("${qiniu.bucket}")
    private String QINIU_BUCKET;

    //获取七牛云上传token
    @GetMapping(path = "upToken")
    public String getUpToken() {
        Auth auth = Auth.create(QINIU_AK, QINIU_SK);
        String upToken = auth.uploadToken(QINIU_BUCKET);
        return upToken;
    }

}
