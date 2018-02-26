package com.lq.hotel.action;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;

//import com.lq.hotel.service.UserService;

@ParentPackage("abstract-struts")
@Action(value="strust2Test")//使用convention-plugin插件提供的@Action注解将一个普通java类标注为一个可以处理用户请求的Action，Action的名字为struts2Test
@Namespace("/")//使用convention-plugin插件提供的@Namespace注解为这个Action指定一个命名空间
public class TestAction {
//	/**
//      * 注入userService
//	      */
//	    @Autowired
//	     private UserService userService;
//	
//	    /**
//	      * http://localhost:8080/SSHE/strust2Test!test.action
//	      * MethodName: test
//	      * Description: 
//	      * @author xudp
//	      */
//	     public void test(){
//	         System.out.println("进入TestAction");
//	         userService.test();
//	    }
}
