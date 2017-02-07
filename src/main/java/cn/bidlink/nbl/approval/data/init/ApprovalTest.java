package cn.bidlink.nbl.approval.data.init;

import org.junit.Test;
import org.nutz.dao.impl.NutDao;
import org.nutz.ioc.Ioc;
import org.nutz.ioc.impl.NutIoc;
import org.nutz.ioc.loader.json.JsonLoader;

import javax.sql.DataSource;

/**
 * Created by renkai on 2017/2/6.
 */

public class ApprovalTest {
//    private TemplateApprovalInstance templateApprovalInstance;
//    private TemplateApproval templateApproval;
//    private TemplateApprovalRecord templateApprovalRecord;
//    @Before
//    public void setUser(){
//        Ioc ioc = new NutIoc(new JsonLoader("templateDao.js"));
//        DataSource ds = ioc.get(DataSource.class);
//        NutDao templateDao = new NutDao(ds);
//        Ioc uioc = new NutIoc(new JsonLoader("userDao.js"));
//        DataSource uds = uioc.get(DataSource.class);
//        NutDao userDao = new NutDao(uds);
//         templateApprovalInstance = new TemplateApprovalInstance();
//        templateApprovalInstance.setId("3c0c7965e5324bef83d204c28c52b841");
//        templateApprovalInstance = templateDao.fetch(templateApprovalInstance);
////        JSONObject jsonObject = new JSONObject(re);
//         templateApprovalRecord = new TemplateApprovalRecord("3900cbb3951d415bb9ba16c0f920a30f");
//        templateApprovalRecord= templateDao.fetch(templateApprovalRecord);
//         templateApproval = templateDao.fetch(new TemplateApproval(templateApprovalInstance.getApprovalId()));
//        Record re = userDao.fetch("user", Cnd.where("ID", "=", templateApprovalRecord.getCreateUserId()));
//        User user = new User();
//        user.setId(re.getString("ID"));
//        user.setName(re.getString("NAME"));
//        user.setOrgCode(re.getLong("ORG_CODE"));
//        user.setTenantId(re.getString("TENANT_ID"));
//        user.setOldCompanyId(re.getLong("OLD_COMPANY_ID"));
//        user.setOldId(re.getLong("OLD_ID"));
//        user.setIsTest(0);
//        RequestContextListener listener = new RequestContextListener();
//        MockServletContext context = new MockServletContext();
//        MockHttpServletRequest request = new MockHttpServletRequest(context);
//        listener.requestInitialized(new ServletRequestEvent(context, request));
//        HttpSession session = new MockHttpSession();
//        request.setSession(session);
//        request.getSession().setAttribute(AuthContent.USER_LOGIN,user);
//        UserContext.setCurrentUser(user);
//    }
    @Test
    public void test(){
        Ioc ioc = new NutIoc(new JsonLoader("templateDao.js"));
        DataSource ds = ioc.get(DataSource.class);
        NutDao templateDao = new NutDao(ds);
        Ioc uioc = new NutIoc(new JsonLoader("userDao.js"));
        DataSource uds = uioc.get(DataSource.class);
        NutDao userDao = new NutDao(uds);
        TemplateApprovalInstance templateApprovalInstance = new TemplateApprovalInstance();
        templateApprovalInstance.setId("3c0c7965e5324bef83d204c28c52b841");
        templateApprovalInstance = templateDao.fetch(templateApprovalInstance);
//        JSONObject jsonObject = new JSONObject(re);
        TemplateApprovalRecord templateApprovalRecord = new TemplateApprovalRecord("3900cbb3951d415bb9ba16c0f920a30f");
        templateApprovalRecord= templateDao.fetch(templateApprovalRecord);
        TemplateApproval templateApproval = templateDao.fetch(new TemplateApproval(templateApprovalInstance.getApprovalId()));
//        Record re = userDao.fetch("user", Cnd.where("ID", "=", templateApprovalRecord.getCreateUserId()));
//        User user = new User();
//        user.setId(re.getString("ID"));
//        user.setName(re.getString("NAME"));
//        user.setOrgCode(re.getLong("ORG_CODE"));
//        user.setTenantId(re.getString("TENANT_ID"));
//        user.setOldCompanyId(re.getLong("OLD_COMPANY_ID"));
//        user.setOldId(re.getLong("OLD_ID"));
//        user.setIsTest(0);
//        RequestContextListener listener = new RequestContextListener();
//        MockServletContext context = new MockServletContext();
//        MockHttpServletRequest request = new MockHttpServletRequest(context);
//        listener.requestInitialized(new ServletRequestEvent(context, request));
//        HttpSession session = new MockHttpSession();
//        request.setSession(session);
//        request.getSession().setAttribute(AuthContent.USER_LOGIN,user);
//        UserContext.setCurrentUser(user);
        AbstractCallBackClass abstractCallBackClass = new AbstractCallBackClass();

        abstractCallBackClass.callCallBack(templateApprovalInstance,templateApprovalRecord,templateApproval);
    }
}
