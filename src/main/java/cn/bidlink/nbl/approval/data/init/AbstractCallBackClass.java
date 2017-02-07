package cn.bidlink.nbl.approval.data.init;

import cn.bidlink.nbl.approval.model.ApprovalResultDto;
import cn.bidlink.nbl.framework.core.dto.resp.RespDto;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *  回调类
 *	@author micheal@ebnew.com
 *	@date 2016-1-8 下午2:07:14
 */
public  class AbstractCallBackClass {
	private static Logger logger = Logger.getLogger(cn.bidlink.nbl.approval.local.service.impl.AbstractCallBackClass.class);
	public void callCallBack(TemplateApprovalInstance instance, TemplateApprovalRecord record,TemplateApproval templateApproval){

		ApprovalResultDto approvalResultDto = new ApprovalResultDto();
		try {
			BeanUtils.copyProperties(approvalResultDto, instance);
			// 1.正常结束，2. 审批不通过结束
			if(instance.getEndType()==2){
				approvalResultDto.setApprovalResult("0");
			}else{
				approvalResultDto.setApprovalResult("1");
			}
			approvalResultDto.setTemplateId(templateApproval.getTemplateId());
			approvalResultDto.setApprovalUserId(record.getCreateUserId());
			approvalResultDto.setApplyUserId(instance.getCreateUserId());
//			//撤项为审批不通过
			if(instance.getEndType()==0){
				approvalResultDto.setApprovalResult("0");
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("类拷贝异常!");
		}
		try {
			Map<String,String> map = new HashMap<String, String>();

			map.put("key",instance.getDataKey());
			map.put("approvalResultDto",  JSONObject.toJSONString( approvalResultDto) );
			RespDto requestResult = post(instance.getCallbackUrl(), map, RespDto.class);
			if(requestResult.getResStatus()!=201){
				throw new RuntimeException("回调接口异常"+requestResult.getResMsg());
			}
		}catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			throw new RuntimeException("请求接口异常");
		}
	}
	public static final <X> X post(String uri, Map<String, String> reqParams, Class<X> respClazz) throws ClientProtocolException, IOException {
		HttpPost httpPost=new HttpPost(uri);
		HttpClientBuilder builder = HttpClientBuilder.create();
		HttpClient client = builder.build();
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		for(String each:reqParams.keySet()){
			params.add(new BasicNameValuePair(each, reqParams.get(each)));
		}
		UrlEncodedFormEntity entity = new UrlEncodedFormEntity(params, "utf-8");
		httpPost.setEntity(entity);

		HttpResponse result = client.execute(httpPost);
		if(result.getStatusLine().getStatusCode()==200){
			if (result != null) {
				String str = IOUtils.toString(result.getEntity().getContent());
				System.out.println(str);
				httpPost.abort();
				return JSON.parseObject(str, respClazz);
			}
		}else{
			logger.error("tomcat return code ....."+result.getStatusLine().getStatusCode());
			throw new RuntimeException("tomcat return code ....."+result.getStatusLine().getStatusCode());
		}
		return null;
	}
	
}
