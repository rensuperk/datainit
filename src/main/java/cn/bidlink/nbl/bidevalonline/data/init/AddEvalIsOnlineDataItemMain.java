package cn.bidlink.nbl.bidevalonline.data.init;

import org.apache.commons.lang3.StringUtils;
import org.nutz.dao.Chain;
import org.nutz.dao.Cnd;
import org.nutz.dao.Sqls;
import org.nutz.dao.entity.Record;
import org.nutz.dao.impl.NutDao;
import org.nutz.dao.pager.Pager;
import org.nutz.dao.sql.Sql;
import org.nutz.ioc.Ioc;
import org.nutz.ioc.impl.NutIoc;
import org.nutz.ioc.loader.json.JsonLoader;
import org.nutz.lang.ContinueLoop;
import org.nutz.lang.Each;
import org.nutz.lang.ExitLoop;
import org.nutz.lang.LoopException;
import org.nutz.trans.Atom;
import org.nutz.trans.Trans;

import javax.sql.DataSource;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Created by renkai on 2017/2/17.
 */
public class AddEvalIsOnlineDataItemMain {
    public static void main(String[] args) {
        Ioc ioc = new NutIoc(new JsonLoader("oaDao.js"));
        DataSource ds = ioc.get(DataSource.class);
        final NutDao oaDao = new NutDao(ds);
        Trans.exec(new Atom() {
            public void run() {
                insertSysDataitem(oaDao);
                }
            });
        Trans.exec(new Atom() {
            public void run() {
                insertDataitem(oaDao);
                }
            });


    }
    public static void insertDataitem(final NutDao oaDao){
        oaDao.each("app_set_form", Cnd.where("FORM_KEY", "in", "'app-set-bidding-process-bid-notice-form','app-set-bidding-process-alteration-notice-form'"), new Each<Record>() {
            public void invoke(int index, Record ele, int length) throws ExitLoop, ContinueLoop, LoopException {
                insertDataItem( oaDao, ele);
            }
        });

    }
    public static void insertDataItem(NutDao oaDao,Record ele){
        String formid = ele.getString("ID");
        String sysFormId = ele.getString("SYS_FORM_ID");
        String appId = ele.getString("APP_ID");
        String templateTypeId = ele.getString("TEMPLATE_TYPE_ID");
        String createUserId = ele.getString("CREATE_USER_ID");
        String tenantId = ele.getString("TENANT_ID");
        long orgCode = ele.getLong("ORG_CODE");
        String sysDataItemId = null;
        long sortNo = 0l;
        Record dataItem = oaDao.fetch("app_set_data_item", Cnd.where("form_id", "=", formid).and("field_key", "=", "isBidEvakOnline"));
        if(dataItem == null){
            List<Record> records = oaDao.query("sys_app_set_data_item",Cnd.where("form_id", "=", sysFormId).and("field_key", "=", "isBidEvakOnline"),new Pager(1,1));
            if(records != null && !records.isEmpty()){
                Record sysDataItem = records.get(0);
                sysDataItemId = sysDataItem.getString("ID");
                sortNo = sysDataItem.getLong("SORT_NO");
            }
            Chain add = Chain.make("ID", StringUtils.replace(UUID.randomUUID().toString(), "-", ""))
                    .add("APP_ID", appId)
                    .add("TEMPLATE_TYPE_ID", templateTypeId)
                    .add("FORM_ID", formid)
                    .add("FIELD_KEY", "isBidEvakOnline")
                    .add("FIELD_NAME", "是否在线评标")
                    .add("IS_SHOW", 1)
                    .add("IS_REQUIRED", 0)
                    .add("IS_APPROVAL", 0)
                    .add("TYPE", "dic")
                    .add("DICTIONARY_KEY", "015")
                    .add("SORT_NO", sortNo)
                    .add("SYSTEM_STATUS", 1)
                    .add("CREATE_TIME", new Date())
                    .add("SYS_DATA_ITEM_ID", sysDataItemId)
                    .add("TENANT_ID", tenantId)
                    .add("CREATE_USER_ID", createUserId)
                    .add("ORG_CODE", orgCode)
                    .add("IS_EDITABLE", 1)
                    ;
            System.out.println("formId="+formid+"下,没有这个数据项,添加中...");
            oaDao.insert("app_set_data_item",add);
        }else {
            System.out.println("formId="+formid+"下,已经有了这个数据项");
        }

    }
    public static void insertSysDataitem(final NutDao oaDao){
        oaDao.each("sys_app_set_form", Cnd.where("form_key", "in", "'app-set-bidding-process-bid-notice-form','app-set-bidding-process-alteration-notice-form'"), new Each<Record>() {
            public void invoke(int index, Record ele, int length) throws ExitLoop, ContinueLoop, LoopException {
//                System.out.println(ele);
                final String sysformId = ele.getString("id");
                final String sysappId = ele.getString("app_id");
                String systemplatetypeid = ele.getString("template_type_id");
                Record fetch = oaDao.fetch("sys_app_set_data_item", Cnd.where("form_id", "=", sysformId).and("field_key", "=", "isBidEvakOnline"));
                if (fetch == null) {
                    System.out.println("没有这个字段,开始插入");
//                    System.out.println("sysappId="+sysappId+",systemplatetypeid="+systemplatetypeid+",sysformId="+sysformId);
                    insertSysDataitem(oaDao,sysappId,systemplatetypeid,sysformId);
                }else {
                    System.out.println("sysformId="+sysformId+"存在数据项,无需添加");
                }

            }
        });
    }
    public static void insertSysDataitem(NutDao oaDao,String appId,String tempateTypeId,String formId){
        Chain add = Chain.make("ID", StringUtils.replace(UUID.randomUUID().toString(), "-", ""))
                .add("APP_ID", appId)
                .add("TEMPLATE_TYPE_ID", tempateTypeId)
                .add("FORM_ID", formId)
                .add("FIELD_KEY", "isBidEvakOnline")
                .add("FIELD_NAME", "是否在线评标")
                .add("IS_SHOW", 1)
                .add("IS_REQUIRED", 0)
                .add("IS_APPROVAL", 0)
                .add("TYPE", "dictionary")
                .add("DICTIONARY_KEY", "015")
                .add("SORT_NO", getSortNo(oaDao, formId))
                .add("SYSTEM_STATUS", 1)
                .add("IS_TENANTABLE", 1)
                .add("IS_TENANT_EDITABLE", 1)
                .add("CREATE_TIME", new Date());
        oaDao.insert("sys_app_set_data_item",add);

    }
    static Integer getSortNo(NutDao oaDao,String formId){
        Sql sql = Sqls.fetchInt("select MAX(SORT_NO) from sys_app_set_data_item where FORM_ID =@formId");
        sql.setParam("formId",formId);
        oaDao.execute(sql);
        return sql.getInt() + 1;
    }
}
