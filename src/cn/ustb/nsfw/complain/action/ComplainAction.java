package cn.ustb.nsfw.complain.action;

import java.net.URLDecoder;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;


import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.struts2.ServletActionContext;

import cn.ustb.core.action.BaseAction;
import cn.ustb.core.utils.QueryHelper;
import cn.ustb.nsfw.complain.entity.Complain;
import cn.ustb.nsfw.complain.entity.Reply;
import cn.ustb.nsfw.complain.service.ComplainService;

import com.opensymphony.xwork2.ActionContext;

import freemarker.template.utility.StringUtil;


public class ComplainAction extends BaseAction {
	
	@Resource
	private ComplainService complainService;
	
	private Complain complain;//投诉对象
	private Reply reply;//回复对象
	 
	private String startTime;//开始时间
	private String endTime;
	
	private String compState;//搜索的投诉状态
	private String compTitle;//搜索的投诉标题
	
	private Map<String,Object> annualStatisticDataMap;
	
	public String listUI(){
		QueryHelper query = new QueryHelper(Complain.class, "c");
		//先排序，再搜索
		query.addOrderByProperty("state", QueryHelper.ORDER_QUERY_ASC);//升序 ，状态值0：未受理，1：已受理,2:已回复
		query.addOrderByProperty("compTime", QueryHelper.ORDER_QUERY_DESC);//降序，新投诉的在前
		try {
			if(complain != null){
				if(StringUtils.isNotBlank(complain.getCompTitle())){
					complain.setCompTitle(URLDecoder.decode(complain.getCompTitle(), "utf-8"));
					query.addCondition("compTitle like ?", "%"+complain.getCompTitle()+"%");
				}
				if(StringUtils.isNotBlank(complain.getState()))
					query.addCondition("state = ?",complain.getState());
				if(StringUtils.isNotBlank(startTime))
					query.addCondition("compTime > ?",DateUtils.parseDate(startTime, "yyyy-MM-dd HH:mm"));
				if(StringUtils.isNotBlank(endTime))
					query.addCondition("compTime < ?",DateUtils.parseDate(endTime, "yyyy-MM-dd HH:mm"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		pageResult = complainService.returnPageResult(query,pageNo,pageSize);
		ActionContext.getContext().getContextMap().put("complainStateMap",Complain.COMPLAIN_STATE_MAP);//加载所有的信息类型map
		return "listUI";
	}

	public String dealUI(){
		if(complain!=null&&StringUtils.isNotBlank(complain.getCompId())){
			compState = complain.getState();
			compTitle = complain.getCompTitle();
			complain = complainService.findObjectById(complain.getCompId());
			
		}
		return "dealUI";
	}
	public String annualStatisticChartUI(){
		return "annualStatisticChartUI";
	}
	public String getAnnualStatisticData(){
		int year = 0;
		HttpServletRequest request = ServletActionContext.getRequest();
		//1、获取年度
		if(request.getParameter("year") != null){
			year = Integer.parseInt(request.getParameter("year"));
		} else {
			year = Calendar.getInstance().get(Calendar.YEAR);//默认当前年份
		}
		annualStatisticDataMap = new HashMap<String, Object>();
		//2、根据年度获取对应12个月份的投诉数并设置到返回结果中
		annualStatisticDataMap.put("msg", "success");
		annualStatisticDataMap.put("chartData", complainService.getAnnualStatisticData(year));
		return "statisticData";
	}
	
	
	public String deal(){
		//获取原数据
		//更新状态
		//更新回复数据
		Date a = new Date();
		if(complain!=null&&StringUtils.isNoneBlank(complain.getCompId())){
			complain = complainService.findObjectById(complain.getCompId());
			if(reply!=null && StringUtils.isNotBlank(reply.getReplyContent())){
				complain.setState(Complain.COMPLAIN_STATE_REPLIED);
			}else{
				complain.setState(Complain.COMPLAIN_STATE_DONE);
			}
			reply.setComplain(complain);
			reply.setReplyTime(new Timestamp(a.getTime()));
			Set<Reply> set = complain.getReplies();
			//Set set = new Set();//待实验
			set.add(reply);
			complain.setReplies(set);
		}
		complainService.update(complain);
		return "list";
	}
	
	
	public Complain getComplain() {
		return complain;
	}

	public void setComplain(Complain complain) {
		this.complain = complain;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public Reply getReply() {
		return reply;
	}

	public void setReply(Reply reply) {
		this.reply = reply;
	}

	public String getCompState() {
		return compState;
	}

	public void setCompState(String compState) {
		this.compState = compState;
	}

	public String getCompTitle() {
		return compTitle;
	}

	public void setCompTitle(String compTitle) {
		this.compTitle = compTitle;
	}

	public Map<String, Object> getAnnualStatisticDataMap() {
		return annualStatisticDataMap;
	}

	public void setAnnualStatisticDataMap(Map<String, Object> annualStatisticDataMap) {
		this.annualStatisticDataMap = annualStatisticDataMap;
	}
}
