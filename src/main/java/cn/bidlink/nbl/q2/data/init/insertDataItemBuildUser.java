package cn.bidlink.nbl.q2.data.init;

import cn.bidlink.nbl.data.utils.DataSourceUtils;
import org.apache.commons.lang3.StringUtils;
import org.nutz.dao.Chain;
import org.nutz.dao.Cnd;
import org.nutz.dao.Sqls;
import org.nutz.dao.entity.Record;
import org.nutz.dao.impl.NutDao;
import org.nutz.dao.sql.Sql;
import org.nutz.trans.Atom;
import org.nutz.trans.Trans;

import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Created by renkai on 2017/4/13.
 */
public class insertDataItemBuildUser {
    private static int sortNo ;
    private static NutDao oaDao;
    private static String sysDataItemId;

    public static void main(String[] args) {
        oaDao = DataSourceUtils.oaDao();
        final List<Record> sysAppSetDataItems = getFormList("sys_app_set_form");
        final Record sysAppSetDataItem = sysAppSetDataItems.get(0);
        Record sysDataItem = getSysDataItem(sysAppSetDataItem);
        if(sysDataItem == null){
            sortNo = getSysSortNo( sysAppSetDataItem.getString("ID"));

        }else {
            sortNo = sysDataItem.getInt("SORT_NO");
        }
        Trans.exec(new Atom() {
            public void run() {
                insertSysDataItem(sysAppSetDataItem);
                List<Record> appSetDataItem = getFormList("app_set_form");
                for (Record record : appSetDataItem) {
                    insertDataItem(record);
                }
            }
        });



    }

    public static Record getSysDataItem(Record record){
        return oaDao.fetch("sys_app_set_data_item", Cnd.where("FIELD_KEY", "=", "buildUser").and("FORM_ID","=",record.getString("ID")));
    }
    public static List<Record> getFormList(String tableName){
        return oaDao.query(tableName, Cnd.where("form_key","=","app-set-project-form"));
    }

    /**
     * app-set-project-form
     * @param record
     */
    public static void insertSysDataItem(Record record){
        Record sys_app_set_data_item = oaDao.fetch("sys_app_set_data_item", Cnd.where("FIELD_KEY", "=", "buildUser").and("FORM_ID","=",record.getString("ID")));
        Chain add = Chain.make("APP_ID", record.getString("APP_ID"))
                .add("TEMPLATE_TYPE_ID", record.getString("TEMPLATE_TYPE_ID"))
                .add("FORM_ID", record.getString("ID"))
                .add("FIELD_KEY", "buildUser")
                .add("FIELD_NAME", "项目建造人")
                .add("IS_SHOW", 0)
                .add("IS_REQUIRED", 0)
                .add("IS_APPROVAL", 0)
                .add("TYPE", "string")
                .add("SORT_NO", sortNo)
                .add("SYSTEM_STATUS", 1)
                .add("IS_TENANTABLE", 1)
                .add("IS_TENANT_EDITABLE", 1)
                .add("CREATE_TIME", new Date());
        if(sys_app_set_data_item ==null){
            sysDataItemId =  StringUtils.replace(UUID.randomUUID().toString(), "-", "");
            add.add("ID", sysDataItemId);
            oaDao.insert("sys_app_set_data_item",add);
        }else {
            sysDataItemId = sys_app_set_data_item.getString("ID");
            oaDao.update("sys_app_set_data_item",add, Cnd.where("ID", "=", sysDataItemId));
        }

    }
    static Integer getSysSortNo(String formId){
        Sql sql = Sqls.fetchInt("select MAX(SORT_NO) from sys_app_set_data_item where FORM_ID =@formId");
        sql.setParam("formId",formId);
        oaDao.execute(sql);
        return sql.getInt() + 1;
    }
    public static void insertDataItem(Record record){
        Record app_set_data_item = oaDao.fetch("app_set_data_item", Cnd.where("FIELD_KEY", "=", "buildUser").and("FORM_ID","=",record.getString("ID")));

        Chain add = Chain.make("APP_ID", record.getString("APP_ID"))
                .add("TEMPLATE_TYPE_ID", record.getString("TEMPLATE_TYPE_ID"))
                .add("FORM_ID", record.getString("ID"))
                .add("FIELD_KEY", "buildUser")
                .add("FIELD_NAME", "项目开发人")
                .add("IS_SHOW", 0)
                .add("IS_REQUIRED", 0)
                .add("IS_SYSTEM", 0)
                .add("IS_APPROVAL", 0)
                .add("TYPE", "string")
                .add("SYSTEM_STATUS", 1)
                .add("SORT_NO", sortNo)
//                .add("SORT_NO", sortNo)
                .add("CREATE_TIME", new Date())
                .add("SYS_DATA_ITEM_ID", sysDataItemId)
                .add("TENANT_ID", record.getString("TENANT_ID"))
                .add("CREATE_USER_ID", record.getString("CREATE_USER_ID"))
                .add("ORG_CODE", record.getString("ORG_CODE"))
                .add("IS_EDITABLE", 1)
                ;
        if(app_set_data_item ==null){
            add.add("ID", StringUtils.replace(UUID.randomUUID().toString(), "-", ""));
            oaDao.insert("app_set_data_item",add);
        }else {
            oaDao.update("app_set_data_item",add, Cnd.where("FIELD_KEY", "=", "buildUser").and("FORM_ID","=",record.getString("ID")));
        }
    }
}
