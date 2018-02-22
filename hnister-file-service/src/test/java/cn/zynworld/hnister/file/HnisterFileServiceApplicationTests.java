package cn.zynworld.hnister.file;

import com.qiniu.util.Auth;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class HnisterFileServiceApplicationTests {

	@Value("${qiniu.ak}")
	private String QINIU_AK;
	@Value("${qiniu.sk}")
	private String QINIU_SK;

	@Test
	public void contextLoads() {
	}

	@Test
	//测试创建 upToken
	public void testGetUpToken() throws Exception {
		String bucket = "hnister";
		Auth auth = Auth.create(QINIU_AK, QINIU_SK);
		String upToken = auth.uploadToken(bucket);
		System.out.println(upToken);
		//4U_uRrZmWTmJin40vwot4Jv9CEU4oTL72VZrw72y:MttGE7WdR04kDUboAGbw1DQyvso=:eyJzY29wZSI6ImhuaXN0ZXIiLCJkZWFkbGluZSI6MTUxOTExMTE4N30=
	}
}
