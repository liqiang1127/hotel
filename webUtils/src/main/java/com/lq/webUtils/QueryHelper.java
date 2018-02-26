package com.lq.webUtils;

import java.util.ArrayList;
import java.util.List;

public class QueryHelper {
	private List<Object> parameters;
	private String fromClause = "";
	private String whereClause = "";
	private String orderClause = "";
	
	public static String ORDER_BY_DESC = "desc";
	public static String ORDER_BY_ASC = "asc";
	
	public QueryHelper(Class clazz,String alias) {
		fromClause = "from "+" "+ clazz.getSimpleName() +" "+ alias;
	}
	public void addCondition(String condition,Object...params){
		if(whereClause.length()>1){
			whereClause += " and "+condition;
		}else{
			whereClause += "  where "+condition;
		}
		if(parameters==null){
			parameters = new ArrayList<Object>();
		}
		if(params!=null){
			for(Object param:params){
				parameters.add(param);
			}
		}
	}
	public void addOrder(String property,String order){
		if(orderClause.length()>1){
			orderClause += ", "+property + " " + order;
		}else{
			orderClause += "  order by "+property + " " + order;
		}
	}
	public String getHql(){
		return fromClause + whereClause + orderClause;
	}
	public String getCountHql(){
		return "select count(*) "+fromClause + whereClause;
	}
	public List<Object> getParameters() {
		return parameters;
	}
	
	
}
