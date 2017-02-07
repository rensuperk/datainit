/*
 * Copyright (c) 2001-2014 Bidlink(Beijing) E-Biz Tech Co.,Ltd.
 * All rights reserved.
 * 必联（北京）电子商务科技有限公司 版权所有 
 */
package cn.bidlink.nbl.approval.data.init;

import cn.bidlink.framework.core.model.BaseModel;
import cn.bidlink.nbl.approval.model.TemplateApprovalNode;
import cn.bidlink.nbl.user.model.User;
import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Name;
import org.nutz.dao.entity.annotation.Table;

import java.io.Serializable;
import java.util.Date;
import java.util.List;


/**
 * <code>TemplateApproval</code>数据库映射.
 *
 * @table 	: template_approval   
 * @version : Ver 1.0
 * @author	: <a href="micheal@ebnew.com">micheal</a>
 * @date	:  2015-09-08 下午16:09:41  
 */
@Table("template_approval")
public class TemplateApproval extends BaseModel implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
     * @描述:     
     * @字段:ID CHAR(32)  
     */	@Name(casesensitive = false)
	private String id;

	/**
     * @描述:流程模板名称
     * @字段:CUSTOM_NAME VARCHAR(200)
     */
	@Column("CUSTOM_NAME")
	private String customName;

	/**
     * @描述:定义过要启动的流程模板id
     * @字段:CUSTOM_ID VARCHAR(32)
     */@Column("CUSTOM_ID")
	private String customId;

	/**
     * @描述:交易平台模板id
     * @字段:TEMPLATE_ID VARCHAR(32)
     */@Column("TEMPLATE_ID")
	private String templateId;

	/**
     * @描述:工作流模板请求唯一标示
     * @字段:CUSTOM_UNIQUE_ID VARCHAR(32)
     */@Column("CUSTOM_UNIQUE_ID")
	private String customUniqueId;

	/**
     * @描述:租户id
     * @字段:TENANT_ID CHAR(32)
     */@Column("TENANT_ID")
	private String tenantId;


	/**
     * @描述:状态(0=无效,1=有效,2=停用)
     * @字段:STATUS TINYINT(3)
     */@Column("STATUS")
	private Integer status;
	/**
     * @描述:模板类型
     * @字段: TYPE INT(10)
     */@Column("TYPE")
	private Integer type;


	/**
     * @描述:流程类型 0 财务 1，业务 2 项目 3 文档
     * @字段:TYPE TINYINT(3)
     */@Column("ORG_CODE")
	private Long orgCode;


	/**
     * @描述:
     * @字段:CREATE_USER_ID CHAR(32)
     */@Column("CREATE_USER_ID")
	private String createUserId;

	/**
     * @描述:
     * @字段:CREATE_USER_NAME CHAR(100)
     */@Column("CREATE_USER_NAME")
	private String createUserName;

	/**
     * @描述:
     * @字段:CREATE_TIME DATETIME(19)
     */@Column("CREATE_TIME")
	private Date createTime;

	/** 非数据库字段，查询时使用 */
	private Date createTimeBegin;

	/** 非数据库字段，查询时使用 */
	private Date createTimeEnd;

	/**
     * @描述:
     * @字段:UPDATE_USER_ID CHAR(32)
     */@Column("UPDATE_USER_ID")
	private String updateUserId;

	/**
     * @描述:
     * @字段:UPDATE_USER_NAME CHAR(100)
     */@Column("UPDATE_USER_NAME")
	private String updateUserName;

	/**
     * @描述:
     * @字段:UPDATE_TIME DATETIME(19)
     */@Column("UPDATE_TIME")
	private Date updateTime;

	/** 非数据库字段，查询时使用 */
	private Date updateTimeBegin;

	/** 非数据库字段，查询时使用 */
	private Date updateTimeEnd;

	public String getCreateUserId() {
		return createUserId;
	}

	public void setCreateUserId(String createUserId) {
		this.createUserId = createUserId;
	}

	public String getCreateUserName() {
		return createUserName;
	}

	public void setCreateUserName(String createUserName) {
		this.createUserName = createUserName;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getCreateTimeBegin() {
		return createTimeBegin;
	}

	public void setCreateTimeBegin(Date createTimeBegin) {
		this.createTimeBegin = createTimeBegin;
	}

	public Date getCreateTimeEnd() {
		return createTimeEnd;
	}

	public void setCreateTimeEnd(Date createTimeEnd) {
		this.createTimeEnd = createTimeEnd;
	}

	public String getUpdateUserId() {
		return updateUserId;
	}

	public void setUpdateUserId(String updateUserId) {
		this.updateUserId = updateUserId;
	}

	public String getUpdateUserName() {
		return updateUserName;
	}

	public void setUpdateUserName(String updateUserName) {
		this.updateUserName = updateUserName;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Date getUpdateTimeBegin() {
		return updateTimeBegin;
	}

	public void setUpdateTimeBegin(Date updateTimeBegin) {
		this.updateTimeBegin = updateTimeBegin;
	}

	public Date getUpdateTimeEnd() {
		return updateTimeEnd;
	}

	public void setUpdateTimeEnd(Date updateTimeEnd) {
		this.updateTimeEnd = updateTimeEnd;
	}
	/**
	 * 非数据库字段
	 */
	private List<TemplateApprovalNode> nodeList;

	public List<TemplateApprovalNode> getNodeList() {
		return nodeList;
	}

	public void setNodeList(List<TemplateApprovalNode> nodeList) {
		this.nodeList = nodeList;
	}

	/**
	 *
	 */
	public TemplateApproval(){
	}

	/**
	 * @param id
	 */
	public TemplateApproval(String id){
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
	 * @param customName 流程模板名称
	 */
	public void setCustomName(String customName) {
		this.customName = customName;
	}

	/**
	 * @return 流程模板名称
	 */
	public String getCustomName() {
		return this.customName;
	}

	/**
	 * @param customId 定义过要启动的流程模板id
	 */
	public void setCustomId(String customId) {
		this.customId = customId;
	}

	/**
	 * @return 定义过要启动的流程模板id
	 */
	public String getCustomId() {
		return this.customId;
	}

	/**
	 * @param templateId 交易平台模板id
	 */
	public void setTemplateId(String templateId) {
		this.templateId = templateId;
	}

	/**
	 * @return 交易平台模板id
	 */
	public String getTemplateId() {
		return this.templateId;
	}

	/**
	 * @param customUniqueId 工作流模板请求唯一标示
	 */
	public void setCustomUniqueId(String customUniqueId) {
		this.customUniqueId = customUniqueId;
	}

	/**
	 * @return 工作流模板请求唯一标示
	 */
	public String getCustomUniqueId() {
		return this.customUniqueId;
	}

	/**
	 * @param tenantId 租户id
	 */
	public void setTenantId(String tenantId) {
		this.tenantId = tenantId;
	}

	/**
	 * @return 租户id
	 */
	public String getTenantId() {
		return this.tenantId;
	}

	/**
	 * @param status 状态(0=无效,1=有效,2=停用)
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}

	/**
	 * @return 状态(0=无效,1=有效,2=停用)
	 */
	public Integer getStatus() {
		return this.status;
	}

	/**
	 * @param type 流程类型 0 财务 1，业务 2 项目 3 文档
	 */
	public void setType(Integer type) {
		this.type = type;
	}

	/**
	 * @return 流程类型 0 财务 1，业务 2 项目 3 文档
	 */
	public Integer getType() {
		return this.type;
	}


	public Long getOrgCode() {
		return orgCode;
	}

	public void setOrgCode(Long orgCode) {
		this.orgCode = orgCode;
	}

	public void createTemplateApproval(User user){
		Date createTime = new Date();
		this.createTime = createTime;
		this.createUserId = user.getId();
		this.createUserName = user.getName();
		this.tenantId = user.getTenantId();
		this.orgCode = user.getOrgCode();
		this.tenantId = user.getTenantId();
	}
	public void updateTemplateApproval(User user){
		Date updateTime = new Date();
		this.updateTime = updateTime;
		this.updateUserId = user.getId();
		this.updateUserName = user.getName();
	}

}
