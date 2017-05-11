package cn.bidlink.nbl.q2.data.init;

import cn.bidlink.nbl.data.utils.DataSourceUtils;
import org.nutz.dao.Cnd;
import org.nutz.dao.Sqls;
import org.nutz.dao.entity.Record;
import org.nutz.dao.impl.FileSqlManager;
import org.nutz.dao.impl.NutDao;
import org.nutz.dao.sql.Sql;
import org.nutz.trans.Atom;
import org.nutz.trans.Trans;

/**
 * Created by renkai on 2017/5/8.
 */
public class UpdateIsShowUtils {

    public static void main(String[] args) {
        Trans.exec(new Atom() {
            public void run() {
                NutDao oaDao = DataSourceUtils.oaDao();
                NutDao templateDao = DataSourceUtils.templateDao();
                oaDao.setSqlManager(new FileSqlManager("sqls/updateIsShowByFormKeyAndFieldKey.sqls"));
                templateDao.setSqlManager(new FileSqlManager("sqls/updateIsShowByFormKeyAndFieldKey.sqls"));
                update(oaDao,"app-set-bidding-process-bid-notice-form","bidcalltype");
                update(oaDao,"app-set-bidding-process-bid-notice-form","bidcallprop");
                update(oaDao,"app-set-bidding-process-alteration-notice-form","bidcalltype");
                update(oaDao,"app-set-bidding-process-alteration-notice-form","bidcallprop");
//                updateApprovalInstance(templateDao,"\"key\":\"bidCallType\",\"isShow\":1","\"key\":\"bidCallType\",\"isShow\":0");
//                updateApprovalInstance(templateDao,"\"key\":\"bidCallProp\",\"isShow\":1","\"key\":\"bidCallProp\",\"isShow\":0");
            }
        });
    }
    public static void update(NutDao oaDao,String formKey,String fieldKey){


        Sql update = oaDao.sqls().create("update");
        update.setParam("fieldKey",fieldKey);
        update.setParam("formKey",formKey);
        oaDao.execute(update);
        Sql update_sys = oaDao.sqls().create("update_sys");
        update_sys.setParam("fieldKey",fieldKey);
        update_sys.setParam("formKey",formKey);
        oaDao.execute(update_sys);
        Sql select = oaDao.sqls().create("select").setCallback(Sqls.callback.records());
        select.setParam("fieldKey",fieldKey);
        select.setParam("formKey",formKey);
        oaDao.execute(select);
        System.out.println(select.getList(Record.class));

    }
    public static void updateApprovalInstance(NutDao templateDao, String oldString,String newString){
        Cnd like = Cnd.where("BUSINESS_DATA", "like", "%"+oldString+"%").and("id","=","2d6cc59a66fb49848d735ec6731fe4fd");
        Sql select_approval_instance = templateDao.sqls().create("select_approval_instance").setCondition(like).setCallback(Sqls.callback.records());
        templateDao.execute(select_approval_instance);
        System.out.println(select_approval_instance.getList(Record.class));
//        for (Record record : select_approval_instance.getList(Record.class)) {
//            String id = record.getString("ID");
//            String BUSINESS_DATA = record.getString("BUSINESS_DATA");
//            String replace = BUSINESS_DATA.replace(oldString, newString);
//            Sql update_approval_instance = templateDao.sqls().create("update_approval_instance");
//            update_approval_instance.setParam("ID",id);
//            update_approval_instance.setParam("BUSINESS_DATA",replace);
//            templateDao.execute(update_approval_instance);
//        }

    }
}
