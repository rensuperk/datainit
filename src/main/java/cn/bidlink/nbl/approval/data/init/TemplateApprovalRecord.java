/*
 * Copyright (c) 2001-2014 Bidlink(Beijing) E-Biz Tech Co.,Ltd.
 * All rights reserved.
 * 必联（北京）电子商务科技有限公司 版权所有
 */
package cn.bidlink.nbl.approval.data.init;

import cn.bidlink.framework.core.model.BaseModel;
import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Name;
import org.nutz.dao.entity.annotation.Table;

/**
 * <code>TemplateApprovalRecord</code>数据库映射.
 *
 * @table 	: template_approval_record
 * @version : Ver 1.0
 * @author	: <a href="micheal@ebnew.com">micheal</a>
 * @date	:  2015-09-08 下午16:09:41
 */
@Table("template_approval_record")
public class TemplateApprovalRecord extends BaseModel{
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	/**
     * @描述:
     * @字段:ID CHAR(32)
     */
	@Name(casesensitive =false)
	private String id;

	/**
     * @描述:工作流流程实例id
     * @字段:PROCESS_ID CHAR(32)
     */
	@Column("PROCESS_ID")
	private String processId;

	/**
     * @描述:审批节点的taskId
     * @字段:TASK_ID CHAR(32)
     */
    @Column("TASK_ID")
	private String taskId;

	/**
     * @描述:审批结果(0 不通过 1 通过)
     * @字段:APPROVAL_RESULT TINYINT(3)
     */
    @Column("APPROVAL_RESULT")
	private String approvalResult;

	/**
     * @描述:审批意见
     * @字段:APPROVAL_OPINION VARCHAR(2000)
     */
    @Column("APPROVAL_OPINION")
	private String approvalOpinion;

	/**
     * @描述:审批人
     * @字段:APPROVAL_USER_NAME VARCHAR(32)
     */
    @Column("APPROVAL_USER_NAME")
	private String approvalUserName;

	/**
     * @描述:审批人id
     * @字段:APPROVAL_USER_ID VARCHAR(32)
     */
    @Column("APPROVAL_USER_ID")
	private String approvalUserId;

	/**
     * @描述:审批人职位
     * @字段:APPROVAL_USER_ROLE VARCHAR(100)
     */@Column("APPROVAL_USER_ROLE")
	private String approvalUserRole;

	/**
     * @描述:审批次序
     * @字段:STEP TINYINT(3)
     */@Column("STEP")
	private Integer step;

	/**
     * @描述:
     * @字段:TENANT_ID CHAR(32)
     */@Column("TENANT_ID")
	private String tenantId;

	/**
     * @描述:
     * @字段:CREATE_TIME DATETIME(19)
     */@Column("CREATE_TIME")
	private java.util.Date createTime;

	/** 非数据库字段，查询时使用 */
	private java.util.Date createTimeBegin;

	/** 非数据库字段，查询时使用 */
	private java.util.Date createTimeEnd;

	/**
     * @描述:
     * @字段:CREATE_USER_ID CHAR(32)
     */@Column("CREATE_USER_ID")
	private String createUserId;

	/**
     * @描述:
     * @字段:ORG_CODE VARCHAR(32)
     */@Column("ORG_CODE")
	private Long orgCode;

	/**
	 *
	 */
	public TemplateApprovalRecord(){
	}

	/**
	 * @param id
	 */
	public TemplateApprovalRecord(String id){
		this.id = id;
	}

	/**
	 * @param id
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return
	 */
	public String getId() {
		return this.id;
	}

	/**
	 * @param processId 工作流流程实例id
	 */
	public void setProcessId(String processId) {
		this.processId = processId;
	}

	/**
	 * @return 工作流流程实例id
	 */
	public String getProcessId() {
		return this.processId;
	}

	/**
	 * @param taskId 审批节点的taskId
	 */
	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}

	/**
	 * @return 审批节点的taskId
	 */
	public String getTaskId() {
		return this.taskId;
	}

	public String getApprovalResult() {
		return approvalResult;
	}

	public void setApprovalResult(String approvalResult) {
		this.approvalResult = approvalResult;
	}

	/**
	 * @param approvalOpinion 审批意见
	 */
	public void setApprovalOpinion(String approvalOpinion) {
		this.approvalOpinion = approvalOpinion;
	}

	/**
	 * @return 审批意见
	 */
	public String getApprovalOpinion() {
		return this.approvalOpinion;
	}

	/**
	 * @param approvalUserName 审批人
	 */
	public void setApprovalUserName(String approvalUserName) {
		this.approvalUserName = approvalUserName;
	}

	/**
	 * @return 审批人
	 */
	public String getApprovalUserName() {
		return this.approvalUserName;
	}

	/**
	 * @param approvalUserId 审批人id
	 */
	public void setApprovalUserId(String approvalUserId) {
		this.approvalUserId = approvalUserId;
	}

	/**
	 * @return 审批人id
	 */
	public String getApprovalUserId() {
		return this.approvalUserId;
	}

	/**
	 * @param approvalUserRole 审批人职位
	 */
	public void setApprovalUserRole(String approvalUserRole) {
		this.approvalUserRole = approvalUserRole;
	}

	/**
	 * @return 审批人职位
	 */
	public String getApprovalUserRole() {
		return this.approvalUserRole;
	}

	/**
	 * @param step 审批次序
	 */
	public void setStep(Integer step) {
		this.step = step;
	}

	/**
	 * @return 审批次序
	 */
	public Integer getStep() {
		return this.step;
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

	/**
	 * @param createTime
	 */
	public void setCreateTime(java.util.Date createTime) {
		this.createTime = createTime;
	}

	/**
	 * @return
	 */
	public java.util.Date getCreateTime() {
		return this.createTime;
	}

	/**
	 * @param createTimeBegin 开始
	 */
    public void setCreateTimeBegin(java.util.Date createTimeBegin) {
		this.createTimeBegin = createTimeBegin;
	}

    /**
	 * @return 开始
	 */
	public java.util.Date getCreateTimeBegin() {
		return this.createTimeBegin;
	}

	/**
	 * @param createTimeEnd 结束
	 */
	public void setCreateTimeEnd(java.util.Date createTimeEnd) {
		this.createTimeEnd = createTimeEnd;
	}

	/**
	 * @return 结束
	 */
	public java.util.Date getCreateTimeEnd() {
		return this.createTimeEnd;
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

	public Long getOrgCode() {
		return orgCode;
	}

	public void setOrgCode(Long orgCode) {
		this.orgCode = orgCode;
	}
}
