package cn.bidlink.nbl.approval.data.init;

import org.nutz.dao.Cnd;
import org.nutz.dao.impl.NutDao;
import org.nutz.dao.pager.Pager;
import org.nutz.ioc.Ioc;
import org.nutz.ioc.impl.NutIoc;
import org.nutz.ioc.loader.json.JsonLoader;

import javax.sql.DataSource;

/**
 * Created by renkai on 2017/2/6.
 */
public class ApprovalCallbackMain {
    public static void main(String[] args) {
        Ioc ioc = new NutIoc(new JsonLoader("templateDao.js"));
        DataSource ds = ioc.get(DataSource.class);
        NutDao templateDao = new NutDao(ds);

        TemplateApprovalInstance templateApprovalInstance = templateDao.query(TemplateApprovalInstance.class, Cnd.where("PROCESS_ID", "=", 2207153).orderBy("CREATE_TIME", "desc"), new Pager(1, 1)).get(0);

        TemplateApprovalRecord templateApprovalRecord = templateDao.query(TemplateApprovalRecord.class, Cnd.where("PROCESS_ID", "=", templateApprovalInstance.getProcessId()).orderBy("CREATE_TIME", "desc"), new Pager(1, 1)).get(0);

        TemplateApproval templateApproval = templateDao.fetch(new TemplateApproval(templateApprovalInstance.getApprovalId()));

        AbstractCallBackClass abstractCallBackClass = new AbstractCallBackClass();

        abstractCallBackClass.callCallBack(templateApprovalInstance,templateApprovalRecord,templateApproval);
        System.out.println(templateApprovalRecord);

    }
}
