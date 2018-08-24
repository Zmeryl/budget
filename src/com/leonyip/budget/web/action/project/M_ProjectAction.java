package com.leonyip.budget.web.action.project;

import java.util.Date;
import java.util.List;

import com.leonyip.budget.domain.department.B_Department;
import com.leonyip.budget.domain.project.M_Milestone;
import com.leonyip.budget.domain.project.M_MilestoneDetail;
import com.leonyip.budget.domain.project.M_MilestoneDetailFact;
import com.leonyip.budget.domain.project.M_Project;
import com.leonyip.budget.domain.user.B_User;
import com.leonyip.budget.service.department.B_DepartmentService;
import com.leonyip.budget.service.user.B_UserService;
import com.leonyip.budget.util.DateUtil;
import com.leonyip.budget.util.dict.BudgetDict;
import com.leonyip.budget.web.action.function.S_CheckFunctionAction;
import com.leonyip.core.dao.support.Page;

public class M_ProjectAction extends M_BaseProjectAction {

	private static final long serialVersionUID = -2680601318014813410L;
	
	private static final String SYS_RES_NAME = "prj";
	
	private long prjId;
	
	private String prjName;
	
	private long deptId;
	
	private String deptName;
	
	private long managerId;
	
	private String managerName;
	
	private Date beginDatePlan;
	
	private Date endDatePlan;
	
	private double sumPricePlan;
	
	private Date beginDateFact;
	
	private Date endDateFact;
	
	private double sumPriceFact;
	
	private String prjDesc;
	
	private B_DepartmentService departmentService;
	
	private B_UserService userService;
	
	public String execute() {
		if( (!new S_CheckFunctionAction().checkUserFunction(SYS_RES_NAME, "viewbyuser")) 
				&& (!new S_CheckFunctionAction().checkUserFunction(SYS_RES_NAME, "view"))
				&& (!new S_CheckFunctionAction().checkUserFunction(SYS_RES_NAME, "viewbydept"))){
			this.setRequestAttribute("_error_message", "您无权查看该页面！");
			return "error_function";
		}
		
		if(pageNo < 1){
			pageNo = 1;
		}
		Page page ;
		B_User user = (B_User)this.getSessionObj(BudgetDict.SESSION_USER);
		if(searchType == null || searchType.equals("") || searchType.equals("1")){
			//如果是查看全部的权限
			if( new S_CheckFunctionAction().checkUserFunction(SYS_RES_NAME, "view")){
				page = projectService.getAllProjectPage(pageNo);
			}else if(new S_CheckFunctionAction().checkUserFunction(SYS_RES_NAME, "viewbydept")){
				page = projectService.getProjectPageByDept(pageNo, user.getDeptRole().getDept().getDeptId());
			}else if(new S_CheckFunctionAction().checkUserFunction(SYS_RES_NAME, "viewbyuser")){
				page = projectService.getProjectPageByUserId(pageNo, user.getUserId());
			}else{
				page = new Page();
			}
		}else if(searchType.equals("2")){
			page = projectService.getProjectPageByKind(pageNo, searchValue);
		}else{
			page = projectService.getProjectPageByKeywords(pageNo, searchValue);
		}
		
		List<B_Department> deptList = departmentService.getAll();
		
		this.setRequestAttribute("deptList", deptList);
		this.setRequestAttribute("page", page);
		this.setRequestAttribute("searchValue", searchValue);
		return SUCCESS;
	}
	
	public String showAddProjectJSP(){
		
		String action = "add";
		if( !new S_CheckFunctionAction().checkUserFunction(SYS_RES_NAME, action)){
			this.setRequestAttribute("_error_message", "您无权查看该页面！");
			return "error_function";
		}
		
		String result = "showAddProjectJSP";
		List<B_Department> deptList = departmentService.getAll();
		this.setRequestAttribute("deptList", deptList);
		
		return result;
	}
	
	public String addProject() throws Exception{
		if(!checkName(prjName)){
			this.setRequestAttribute("_error_message", "同名项目已存在，请检查后重新操作！");
			return "error_function";
		}
		
		String result = "addProject";
		
		if(DateUtil.isBeforeTheDay(new Date(), beginDatePlan)){
			result = "error_date";
			this.setRequestAttribute("_error_message", "项目开始时间早于当前时间，请修改后重新添加！");
			return result;
		}
		if(DateUtil.isBeforeTheDay(beginDatePlan, endDatePlan)){
			result = "error_date";
			this.setRequestAttribute("_error_message", "项目结束时间早于开始时间，请修改后重新添加！");
			return result;
		}
		
		B_Department dept = departmentService.get(deptId);
		B_User manager = userService.get(managerId);
		
		M_Project prj = new M_Project();
		prj.setPrjName(prjName);
		prj.setDept(dept);
		prj.setDeptName(dept.getDeptName());
		prj.setManager(manager);
		prj.setManagerName(manager.getUserDetailList().get(0).getRealName());
		prj.setBeginDatePlan(beginDatePlan);
		prj.setEndDatePlan(endDatePlan);
		prj.setSumPricePlan(0);
		//初次添加项目时 默认与预期计划相同
		prj.setBeginDateFact(beginDatePlan);
		prj.setEndDateFact(endDatePlan);
		prj.setSumPriceFact(0);
		prj.setPrjDesc(prjDesc);
		
		projectService.save(prj);
		
		return result;
	}
	
	public String showEditProjectJSP(){
		String action = "edit";
		if( !new S_CheckFunctionAction().checkUserFunction(SYS_RES_NAME, action)){
			this.setRequestAttribute("_error_message", "您无权查看该页面！");
			return "error_function";
		}
		
		String result = "showEditProjectJSP";
		M_Project prj = projectService.get(prjId);
		if(DateUtil.isAfterTheDay(prj.getBeginDatePlan(), new Date())){
			result = "error_date";
			this.setRequestAttribute("_error_message", "项目已经开始，无法修改项目属性");
			return result;
		}
		this.setRequestAttribute("prj", prj);
		List<B_Department> deptList = departmentService.getAll();
		this.setRequestAttribute("deptList", deptList);
		
		return result;
	}
	
	public String updateProject(){
//		if(!checkName(prjName)){
//			this.setRequestAttribute("_error_message", "同名项目已存在，请检查后重新操作！");
//			return "error_function";
//		}
		
		String result = "updateProject";
		
		if(DateUtil.isBeforeTheDay(new Date(), beginDatePlan)){
			result = "error_date";
			this.setRequestAttribute("_error_message", "项目开始时间早于当前时间，请修改后重新添加！");
			return result;
		}
		if(DateUtil.isBeforeTheDay(beginDatePlan, endDatePlan)){
			result = "error_date";
			this.setRequestAttribute("_error_message", "项目结束时间早于开始时间，请修改后重新添加！");
			return result;
		}
		
		B_Department dept = departmentService.get(deptId);
		B_User manager = userService.get(managerId);
		
		/******************
		M_Project prj = new M_Project();
		prj.setPrjId(prjId);
		prj.setPrjName(prjName);
		prj.setDept(dept);
		prj.setDeptName(dept.getDeptName());
		prj.setManager(manager);
		prj.setManagerName(manager.getUserDetailList().get(0).getRealName());
		prj.setBeginDatePlan(beginDatePlan);
		prj.setEndDatePlan(endDatePlan);
		prj.setSumPricePlan(sumPricePlan);
		prj.setBeginDateFact(beginDateFact);
		prj.setEndDateFact(endDateFact);
		prj.setSumPriceFact(sumPriceFact);
		prj.setPrjDesc(prjDesc);
		projectService.save(prj);
		********************/
		
		M_Project prj = projectService.get(prjId);
		prj.setPrjName(prjName);
		prj.setDept(dept);
		prj.setDeptName(dept.getDeptName());
		prj.setManager(manager);
		prj.setManagerName(manager.getUserDetailList().get(0).getRealName());
		prj.setBeginDatePlan(beginDatePlan);
		prj.setEndDatePlan(endDatePlan);
		prj.setPrjDesc(prjDesc);
		
		projectService.save(prj);
		
		return result;
	}
	
	public String deleteProject(){
		String action = "delete";
		if( !new S_CheckFunctionAction().checkUserFunction(SYS_RES_NAME, action)){
			this.setRequestAttribute("_error_message", "您无权查看该页面！");
			return "error_function";
		}
		
		String result = "deleteProject";
		String[] deleteArray = deleteValue.split(",");
		for(int i = 0; i < deleteArray.length; i++){
			long deleteId = Long.parseLong(deleteArray[i]);
			
			M_Project prj = projectService.get(deleteId);
			if(DateUtil.isAfterTheDay(prj.getBeginDatePlan(), new Date())){
				result = "error_date";
				this.setRequestAttribute("_error_message", "项目已经进入实施状态，无法删除");
				return result;
			}
			
			projectService.removeById(deleteId);
		}
		return result;
	}
	
	@SuppressWarnings("unused")
	public String prjReport(){
		String result = "prjReport";
		M_Project prj = projectService.get(prjId);
		for(M_Milestone milestone : prj.getMilestoneList()){
			for( M_MilestoneDetail detail : milestone.getDetailList()){
				;
			}
			
			for(M_MilestoneDetailFact fact : milestone.getDetailFactList()){
				;
			}
		}
		
		this.setRequestAttribute("prj", prj);
		
		return result;
	}
	
	private boolean checkName(String name){
		if(name == null){
			return true;
		}
		M_Project prj = projectService.getPrjByName(name);
		if(prj == null){
			return true;
		}else{
			return false;
		}
	}
	
	public long getPrjId() {
		return prjId;
	}

	public void setPrjId(long prjId) {
		this.prjId = prjId;
	}

	public String getPrjName() {
		return prjName;
	}

	public void setPrjName(String prjName) {
		this.prjName = prjName;
	}

	public long getDeptId() {
		return deptId;
	}

	public void setDeptId(long deptId) {
		this.deptId = deptId;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public long getManagerId() {
		return managerId;
	}

	public void setManagerId(long managerId) {
		this.managerId = managerId;
	}

	public String getManagerName() {
		return managerName;
	}

	public void setManagerName(String managerName) {
		this.managerName = managerName;
	}

	public Date getBeginDatePlan() {
		return beginDatePlan;
	}

	public void setBeginDatePlan(Date beginDatePlan) {
		this.beginDatePlan = beginDatePlan;
	}

	public Date getEndDatePlan() {
		return endDatePlan;
	}

	public void setEndDatePlan(Date endDatePlan) {
		this.endDatePlan = endDatePlan;
	}

	public double getSumPricePlan() {
		return sumPricePlan;
	}

	public void setSumPricePlan(double sumPricePlan) {
		this.sumPricePlan = sumPricePlan;
	}

	public Date getBeginDateFact() {
		return beginDateFact;
	}

	public void setBeginDateFact(Date beginDateFact) {
		this.beginDateFact = beginDateFact;
	}

	public Date getEndDateFact() {
		return endDateFact;
	}

	public void setEndDateFact(Date endDateFact) {
		this.endDateFact = endDateFact;
	}

	public double getSumPriceFact() {
		return sumPriceFact;
	}

	public void setSumPriceFact(double sumPriceFact) {
		this.sumPriceFact = sumPriceFact;
	}

	public String getPrjDesc() {
		return prjDesc;
	}

	public void setPrjDesc(String prjDesc) {
		this.prjDesc = prjDesc;
	}

	public B_DepartmentService getDepartmentService() {
		return departmentService;
	}

	public void setDepartmentService(B_DepartmentService departmentService) {
		this.departmentService = departmentService;
	}

	public B_UserService getUserService() {
		return userService;
	}

	public void setUserService(B_UserService userService) {
		this.userService = userService;
	}

	
}
