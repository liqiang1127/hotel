package webUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.lq.webEnum.HttpMathod;
import com.lq.webUtils.HttpHelper;

public class HttpTest {
	//如果不能运行提示classNotFound 好像remove掉test文件夹，然后再添加就好了
	@Test
	public void tset1(){
		Map<String,String> map = new HashMap<>();
		map.put("name", "123");
		map.put("password", "lq");
		String jsonStr = JSON.toJSONString(map);
		System.out.println(jsonStr);
		String json =  HttpHelper.sendHttpJsonRequest("http://localhost:8280/hotel-base/employee!login.action",jsonStr);
		System.out.println(json);
	}	
}
