/*
 * Copyright (c) 2001-2014 Bidlink(Beijing) E-Biz Tech Co.,Ltd.
 * All rights reserved.
 * 必联（北京）电子商务科技有限公司 版权所有 
 */
package cn.bidlink.nbl.approval.data.init;

import cn.bidlink.nbl.user.model.User;
import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Name;
import org.nutz.dao.entity.annotation.Table;

import java.io.Serializable;
import java.util.Date;

/**
 * <code>TemplateApprovalInstance</code>数据库映射.
 *
 * @table 	: template_approval_instance   
 * @version : Ver 1.0
 * @author	: <a href="micheal@ebnew.com">micheal</a>
 * @date	:  2015-11-04 上午11:19:55  
 */
@Table("nbl_template.template_approval_instance")
public class TemplateApprovalInstance implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
     * @描述:主键     
     * @字段:ID CHAR(32)  
     */
	@Name(casesensitive = false)
	private String id;

	/**
     * @描述:模板id
     * @字段:APPROVAL_ID CHAR(32)
     */
	@Column("APPROVAL_ID")
	private String approvalId;

	/**
     * @描述:流程实例id
     * @字段:PROCESS_ID CHAR(32)
     */
	@Column("PROCESS_ID")
	private String processId;

	/**
     * @描述:流程实例名称申请内容
     * @字段:PROCESS_NAME VARCHAR(200)
     */
	@Column("PROCESS_NAME")
	private String processName;

	/**
     * @描述:。0 非正常结束（撤项） 1 流程正常结束 2 审批不通过导致流程结束
     * @字段:END_TYPE TINYINT(3)
     */
	@Column("END_TYPE")
	private Integer endType;

	/**
     * @描述:当前第几级审批
     * @字段:CURRENT_NODE TINYINT(3)
     */
	@Column("CURRENT_NODE")
	private Integer currentNode;

	/**
     * @描述:流程的轮次（同一个businessId下流程的轮次）
     * @字段:ROUND TINYINT(3)
     */
	@Column("ROUND")
	private Integer round;

	/**
     * @描述:流程状态 是否结束0 否1是
     * @字段:STATUS TINYINT(3)
     */
	@Column("STATUS")
	private Integer status;

	/**
     * @描述:业务id
     * @字段:BUSINESS_ID VARCHAR(32)
     */
	@Column("BUSINESS_ID")
	private String businessId;

	/**
     * @描述:详细审批数据(用于展示)
     * @字段:BUSINESS_DATA TEXT(65535)
     */
	@Column("BUSINESS_DATA")
	private String businessData;
	/**
	 * 审批详细数据(用于回调保存)
	 */
	@Column("STORE_DATA")
	private String storeData;
	/**
     * @描述:回调url
     * @字段:CALLBACK_URL VARCHAR(100)
     */
	@Column("CALLBACK_URL")
	private String callbackUrl;

	/**
     * @描述:
     * @字段:CREATE_USER_ID CHAR(32)
     */
	@Column("CREATE_USER_ID")
	private String createUserId;

	/**
     * @描述:
     * @字段:CREATE_USER_NAME VARCHAR(50)
     */
	@Column("CREATE_USER_NAME")
	private String createUserName;

	/**
     * @描述:
     * @字段:CREATE_TIME DATETIME(19)
     */
	@Column("CREATE_TIME")
	private Date createTime;

	/** 非数据库字段，查询时使用 */
	private Date createTimeBegin;

	/** 非数据库字段，查询时使用 */
	private Date createTimeEnd;

	/**
     * @描述:
     * @字段:UPDATE_USER_ID CHAR(32)
     */
	@Column("UPDATE_USER_ID")
	private String updateUserId;

	/**
     * @描述:
     * @字段:UPDATE_USER_NAME VARCHAR(50)
     */
	@Column("UPDATE_USER_NAME")
	private String updateUserName;

	/**
     * @描述:
     * @字段:UPDATE_TIME DATETIME(19)
     */
	@Column("UPDATE_TIME")
	private Date updateTime;

	/** 非数据库字段，查询时使用 */
	private Date updateTimeBegin;

	/** 非数据库字段，查询时使用 */
	private Date updateTimeEnd;

	/**
     * @描述:
     * @字段:TENANT_ID CHAR(32)
     */
	@Column("TENANT_ID")
	private String tenantId;

	/**
     * @描述:
     * @字段:ORG_CODE VARCHAR(32)
     */
	@Column("ORG_CODE")
	private Long orgCode;

	/**
	 * 提交审批节点code(用于选择模板)
	 */
	@Column("APPROVAL_NODE_CODE")
	private String approvalNodeCode;
	/**
	 * 流程路线
	 */
	@Column("WORK_FLOW")
	private String workFlow;
	
//
//	/**
//     * @描述:回调类名称（业务平台回调）
//     * @字段:CALLBACK_CLASS_NAME VARCHAR(100)
//     */
//	private java.lang.String callbackClassName;

//	/**
//     * @描述:提交类型（业务平台回调）
//     * @字段:SUBMIT_TYPE TINYINT(3)
//     */
//	private java.lang.Integer submitType;
//
//	/**
//     * @描述:javaBean类名（业务平台回调）
//     * @字段:DATA_CLASS_NAME VARCHAR(100)
//     */
//	private java.lang.String dataClassName;
	@Column("DATA_KEY")
	private String dataKey;
	/**
	 * 非数据库字段
	 */
	private String sortColumns;

	/**
	 *
	 */
	public TemplateApprovalInstance(){
	}

	/**
	 * @param id 主键
	 */
	public TemplateApprovalInstance(String id){
		this.id = id;
	}

	/**
	 * @param id 主键
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return 主键
	 */
	public String getId() {
		return this.id;
	}

	/**
	 * @param approvalId 模板id
	 */
	public void setApprovalId(String approvalId) {
		this.approvalId = approvalId;
	}

	/**
	 * @return 模板id
	 */
	public String getApprovalId() {
		return this.approvalId;
	}

	/**
	 * @param processId 流程实例id
	 */
	public void setProcessId(String processId) {
		this.processId = processId;
	}

	/**
	 * @return 流程实例id
	 */
	public String getProcessId() {
		return this.processId;
	}

	/**
	 * @param processName 流程实例名称申请内容
	 */
	public void setProcessName(String processName) {
		this.processName = processName;
	}

	/**
	 * @return 流程实例名称申请内容
	 */
	public String getProcessName() {
		return this.processName;
	}

	/**
	 * @param endType 流程结束状态。2 非正常结束（撤项） 1 流程正常结束 0审批不通过导致流程结束
	 */
	public void setEndType(Integer endType) {
		this.endType = endType;
	}

	/**
	 * @return 流程结束状态。2 非正常结束（撤项） 1 流程正常结束 0审批不通过导致流程结束
	 */
	public Integer getEndType() {
		return this.endType;
	}

	/**
	 * @param currentNode 当前第几级审批
	 */
	public void setCurrentNode(Integer currentNode) {
		this.currentNode = currentNode;
	}

	/**
	 * @return 当前第几级审批
	 */
	public Integer getCurrentNode() {
		return this.currentNode;
	}

	/**
	 * @param round 流程的轮次（同一个businessId下流程的轮次）
	 */
	public void setRound(Integer round) {
		this.round = round;
	}

	/**
	 * @return 流程的轮次（同一个businessId下流程的轮次）
	 */
	public Integer getRound() {
		return this.round;
	}

	/**
	 * @param status 流程状态 是否结束0 否1是
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}

	/**
	 * @return 流程状态 是否结束0 否1是
	 */
	public Integer getStatus() {
		return this.status;
	}

	/**
	 * @param businessId 业务id
	 */
	public void setBusinessId(String businessId) {
		this.businessId = businessId;
	}

	/**
	 * @return 业务id
	 */
	public String getBusinessId() {
		return this.businessId;
	}

	/**
	 * @param businessData 详细审批数据
	 */
	public void setBusinessData(String businessData) {
		this.businessData = businessData;
	}

	/**
	 * @return 详细审批数据
	 */
	public String getBusinessData() {
		return this.businessData;
	}

	/**
	 * @param callbackUrl 回调url
	 */
	public void setCallbackUrl(String callbackUrl) {
		this.callbackUrl = callbackUrl;
	}

	/**
	 * @return 回调url
	 */
	public String getCallbackUrl() {
		return this.callbackUrl;
	}

	/**
	 * @param createUserId
	 */
	public void setCreateUserId(String createUserId) {
		this.createUserId = createUserId;
	}

	/**
	 * @return
	 */
	public String getCreateUserId() {
		return this.createUserId;
	}

	/**
	 * @param createUserName
	 */
	public void setCreateUserName(String createUserName) {
		this.createUserName = createUserName;
	}

	/**
	 * @return
	 */
	public String getCreateUserName() {
		return this.createUserName;
	}

	/**
	 * @param createTime
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	/**
	 * @return
	 */
	public Date getCreateTime() {
		return this.createTime;
	}

	/**
	 * @param createTimeBegin 开始
	 */
    public void setCreateTimeBegin(Date createTimeBegin) {
		this.createTimeBegin = createTimeBegin;
	}

    /**
	 * @return 开始
	 */
	public Date getCreateTimeBegin() {
		return this.createTimeBegin;
	}

	/**
	 * @param createTimeEnd 结束
	 */
	public void setCreateTimeEnd(Date createTimeEnd) {
		this.createTimeEnd = createTimeEnd;
	}

	/**
	 * @return 结束
	 */
	public Date getCreateTimeEnd() {
		return this.createTimeEnd;
	}

	/**
	 * @param updateUserId
	 */
	public void setUpdateUserId(String updateUserId) {
		this.updateUserId = updateUserId;
	}

	/**
	 * @return
	 */
	public String getUpdateUserId() {
		return this.updateUserId;
	}

	/**
	 * @param updateUserName
	 */
	public void setUpdateUserName(String updateUserName) {
		this.updateUserName = updateUserName;
	}

	/**
	 * @return
	 */
	public String getUpdateUserName() {
		return this.updateUserName;
	}

	/**
	 * @param updateTime
	 */
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	/**
	 * @return
	 */
	public Date getUpdateTime() {
		return this.updateTime;
	}

	/**
	 * @param updateTimeBegin 开始
	 */
    public void setUpdateTimeBegin(Date updateTimeBegin) {
		this.updateTimeBegin = updateTimeBegin;
	}

    /**
	 * @return 开始
	 */
	public Date getUpdateTimeBegin() {
		return this.updateTimeBegin;
	}

	/**
	 * @param updateTimeEnd 结束
	 */
	public void setUpdateTimeEnd(Date updateTimeEnd) {
		this.updateTimeEnd = updateTimeEnd;
	}

	/**
	 * @return 结束
	 */
	public Date getUpdateTimeEnd() {
		return this.updateTimeEnd;
	}

	/**
	 * @param tenantId
	 */
	public void setTenantId(String tenantId) {
		this.tenantId = tenantId;
	}

	/**
	 * @return
	 */
	public String getTenantId() {
		return this.tenantId;
	}

	
//	/**
//	 * @param callbackClassName 回调类名称（业务平台回调）
//	 */
//	public void setCallbackClassName(java.lang.String callbackClassName) {
//		this.callbackClassName = callbackClassName;
//	}
//	
//	/**
//	 * @return 回调类名称（业务平台回调）
//	 */
//	public java.lang.String getCallbackClassName() {
//		return this.callbackClassName;
//	}
	
//	/**
//	 * @param submitType 提交类型（业务平台回调）
//	 */
//	public void setSubmitType(java.lang.Integer submitType) {
//		this.submitType = submitType;
//	}
//	
//	/**
//	 * @return 提交类型（业务平台回调）
//	 */
//	public java.lang.Integer getSubmitType() {
//		return this.submitType;
//	}
//	
//	/**
//	 * @param dataClassName javaBean类名（业务平台回调）
//	 */
//	public void setDataClassName(java.lang.String dataClassName) {
//		this.dataClassName = dataClassName;
//	}
//	
//	/**
//	 * @return javaBean类名（业务平台回调）
//	 */
//	public java.lang.String getDataClassName() {
//		return this.dataClassName;
//	}

	public Long getOrgCode() {
		return orgCode;
	}

	public void setOrgCode(Long orgCode) {
		this.orgCode = orgCode;
	}

	public String getSortColumns() {
		return sortColumns;
	}

	public void setSortColumns(String sortColumns) {
		this.sortColumns = sortColumns;
	}

	public String getDataKey() {
		return dataKey;
	}

	public void setDataKey(String dataKey) {
		this.dataKey = dataKey;
	}
	public void createInstance(User user){
		Date date = new Date();
		this.createTime = date;
		this.createUserId = user.getId();
		this.createUserName = user.getName();
	}

	public String getApprovalNodeCode() {
		return approvalNodeCode;
	}

	public void setApprovalNodeCode(String approvalNodeCode) {
		this.approvalNodeCode = approvalNodeCode;
	}

	public String getStoreData() {
		return storeData;
	}

	public void setStoreData(String storeData) {
		this.storeData = storeData;
	}

	public String getWorkFlow() {
		return workFlow;
	}

	public void setWorkFlow(String workFlow) {
		this.workFlow = workFlow;
	}
}
