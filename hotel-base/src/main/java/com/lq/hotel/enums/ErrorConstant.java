package com.lq.hotel.enums;

public enum ErrorConstant {
	system_error(99, "系统错误"), 
	department_contain_employee(1, "部门中有员工"),
	exist(2,"已经存在"),
	not_exist(3,"不存在"),
	already_reserved(4,"该种类房间已被预订完了"),
	parameter_error(5,"参数错误"),
	login_fail(6, "账号密码错误"),
	Invalid_key(7,"秘钥无效"),
	hotel_state_error(8, "您所在的酒店不在营业状态"),
	co_state_error(9, "合作状态不正常"),
	no_privilege(10,"您没有权限");
	
	
	
	
	
	//========================================================================
    private Integer code;  
    private String info;  
    //自定义的构造函数，参数数量，名字随便自己取  
    //构造器默认也只能是private, 从而保证构造函数只能在内部使用   
    private ErrorConstant(Integer code, String info)  
    {  
        this.code = code;
        this.info = info;  
    }  
  
    public Integer getCode()  
    {  
        return code;  
    }  
  
    public void setCode(Integer code)  
    {  
        this.code = code;
    }  
  
    public String getInfo()  
    {  
        return info;  
    }  
  
    public void setInfo(String info)  
    {  
        this.info = info;  
    }  
    //重新toString方法，默认的toString方法返回的就是枚举变量的名字，和name()方法返回值一样  
    @Override  
    public String toString()  
    {  
        return this.code+":"+this.info;  
          
    }  
}
