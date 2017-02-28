package cn.bidlink.nbl.bidevalonline.data.init;

import com.alibaba.fastjson.JSONArray;
import org.apache.commons.lang3.StringUtils;
import org.nutz.dao.Chain;
import org.nutz.dao.Cnd;
import org.nutz.dao.Sqls;
import org.nutz.dao.entity.Record;
import org.nutz.dao.impl.NutDao;
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
import java.util.*;

/**
 * Created by renkai on 2017/2/18.
 * 只需要测试跑一遍
 */
public class UpdateBidcallEditDataItemMain {
    private static NutDao oaDao = null;
    private static Map<String,List<Record>> map = new HashMap();
    public static void main(String[] args) {
        Ioc ioc = new NutIoc(new JsonLoader("oaDao.js"));
        DataSource ds = ioc.get(DataSource.class);
        oaDao  = new NutDao(ds);
        Trans.exec(new Atom() {
            public void run() {
                updateDataItem();
            }
        });
    }

    public static void updateDataItem(){
        oaDao.each("app_set_form", Cnd.where("FORM_KEY", "=", "app-set-bidding-process-bid-document-form"), new Each<Record>() {
            public void invoke(int index, Record ele, int length) throws ExitLoop, ContinueLoop, LoopException {
                List<Record> sysDataItemBysysFormId = getSysDataItemBysysFormId(ele.getString("SYS_FORM_ID"));
                boolean  checkDataItemIsRight= checkDataItemIsRight("app_set_data_item", ele.getString("ID"));
                if(!checkDataItemIsRight){
                    remove(ele.getString("ID"));
                    System.out.println("删除dataitembyformId = " + ele.getString("ID"));
                    for (Record sysDataItem : sysDataItemBysysFormId) {
                        insertDataItem(ele,sysDataItem);
                        System.out.println("新增dataitembyformId = " + ele.getString("ID"));
                    }

                }
            }
        });
    }
    public static List<Record> getSysDataItemBysysFormId(String sysFormId){
        if(map.containsKey(sysFormId)){
            return map.get(sysFormId);
        }else{
            List<Record> list = oaDao.query("sys_app_set_data_item", Cnd.where("form_id", "=", sysFormId).and("field_key", "in", "'isBidEvakOnline','bidCallFiles','bidMode','bidMethod','priceTemplate','bidFiles'"));
            if(list == null || list.isEmpty()){
                list = getSysDataitem();
            }
            map.put(sysFormId,list);
            return list;
        }

    }
    public static void remove(String formId){
        Sql sql = Sqls.create("delete from app_set_data_item where form_id=@formId");
        sql.setParam("formId",formId);
        oaDao.execute(sql);
        System.out.println("formId = " + formId + "删除了" + sql.getUpdateCount() + "条数据!");
    }
    public static boolean checkDataItemIsRight(String tableName,String formId) {
        if(!"app_set_data_item".equals(tableName) && !"sys_app_set_data_item".equals(tableName)){
            throw new IllegalArgumentException("wrong tableName");
        }
        int count = oaDao.count(tableName, Cnd.where("form_id", "=", formId).and("field_key", "in", "'isBidEvakOnline','bidCallFiles','bidMode','bidMethod','priceTemplate','bidFiles'"));
        if(count == 6){
            System.out.println(tableName+"数据正确,不需更新formId="+formId);
            return  true;
        }else {
//            System.out.println(tableName+"数据错误,需更新formId="+formId);
            return false;
        }
    }
    public static void insertDataItem(Record formrecord,Record sysdataItem){

            Chain add = Chain.make("ID", StringUtils.replace(UUID.randomUUID().toString(), "-", ""))
                    .add("APP_ID", formrecord.getString("APP_ID"))
                    .add("TEMPLATE_TYPE_ID", formrecord.getString("TEMPLATE_TYPE_ID"))
                    .add("FORM_ID", formrecord.getString("ID"))
                    .add("FIELD_KEY", sysdataItem.getString("FIELD_KEY"))
                    .add("FIELD_NAME", sysdataItem.getString("FIELD_NAME"))
                    .add("IS_SHOW", sysdataItem.getInt("IS_SHOW"))
                    .add("IS_REQUIRED", sysdataItem.getInt("IS_REQUIRED"))
                    .add("IS_APPROVAL", sysdataItem.getInt("IS_APPROVAL"))
                    .add("TYPE", sysdataItem.getString("TYPE"))
                    .add("DICTIONARY_KEY", sysdataItem.getString("DICTIONARY_KEY"))
                    .add("SORT_NO", sysdataItem.getInt("SORT_NO"))
                    .add("SYSTEM_STATUS", sysdataItem.getInt("SYSTEM_STATUS"))
                    .add("CREATE_TIME", new Date())
                    .add("SYS_DATA_ITEM_ID", sysdataItem.getString("ID"))
                    .add("TENANT_ID", formrecord.getString("TENANT_ID"))
                    .add("CREATE_USER_ID", formrecord.getString("CREATE_USER_ID"))
                    .add("ORG_CODE", formrecord.getLong("ORG_CODE"))
                    .add("IS_EDITABLE", sysdataItem.getInt("IS_TENANT_EDITABLE"))
                    ;
            oaDao.insert("app_set_data_item",add);
    }

    static List<Record> getSysDataitem(){
        String json = "[\n" +
                "  {\n" +
//                "    \"ID\": \"1feec876d20044cd842505bfff3c9557\",\n" +
                "    \"APP_ID\": \"4ecfe16dc40c11e598bcfa163e84de1b\",\n" +
                "    \"TEMPLATE_TYPE_ID\": \"e8194728a3d240ffaec84d61c724acab\",\n" +
                "    \"FORM_ID\": \"60df9ed4a69e42088b0871a5714dead1\",\n" +
                "    \"FIELD_KEY\": \"bidCallFiles\",\n" +
                "    \"FIELD_NAME\": \"招标文件\",\n" +
                "    \"IS_SHOW\": 1,\n" +
                "    \"IS_REQUIRED\": 1,\n" +
                "    \"IS_APPROVAL\": 0,\n" +
                "    \"TYPE\": null,\n" +
                "    \"DICTIONARY_KEY\": null,\n" +
                "    \"SORT_NO\": 3,\n" +
                "    \"CREATE_USER_ID\": \"system_import\",\n" +
                "    \"CREATE_TIME\": \"2016-11-01 10:10:44\",\n" +
                "    \"UPDATE_USER_ID\": null,\n" +
                "    \"UPDATE_TIME\": null,\n" +
                "    \"SYSTEM_STATUS\": 1,\n" +
                "    \"IS_TENANTABLE\": 1,\n" +
                "    \"IS_TENANT_EDITABLE\": 0\n" +
                "  },\n" +
                "  {\n" +
//                "    \"ID\": \"b589f26ae31b44b6873a7f41646be3d0\",\n" +
                "    \"APP_ID\": \"4ecfe16dc40c11e598bcfa163e84de1b\",\n" +
                "    \"TEMPLATE_TYPE_ID\": \"e8194728a3d240ffaec84d61c724acab\",\n" +
                "    \"FORM_ID\": \"60df9ed4a69e42088b0871a5714dead1\",\n" +
                "    \"FIELD_KEY\": \"bidFiles\",\n" +
                "    \"FIELD_NAME\": \"评标标准\",\n" +
                "    \"IS_SHOW\": 1,\n" +
                "    \"IS_REQUIRED\": 0,\n" +
                "    \"IS_APPROVAL\": 0,\n" +
                "    \"TYPE\": null,\n" +
                "    \"DICTIONARY_KEY\": null,\n" +
                "    \"SORT_NO\": 6,\n" +
                "    \"CREATE_USER_ID\": \"system_import\",\n" +
                "    \"CREATE_TIME\": \"2016-11-01 10:10:44\",\n" +
                "    \"UPDATE_USER_ID\": null,\n" +
                "    \"UPDATE_TIME\": null,\n" +
                "    \"SYSTEM_STATUS\": 1,\n" +
                "    \"IS_TENANTABLE\": 1,\n" +
                "    \"IS_TENANT_EDITABLE\": 1\n" +
                "  },\n" +
                "  {\n" +
//                "    \"ID\": \"09ca67f956624c26b1d04269aea729d9\",\n" +
                "    \"APP_ID\": \"4ecfe16dc40c11e598bcfa163e84de1b\",\n" +
                "    \"TEMPLATE_TYPE_ID\": \"e8194728a3d240ffaec84d61c724acab\",\n" +
                "    \"FORM_ID\": \"60df9ed4a69e42088b0871a5714dead1\",\n" +
                "    \"FIELD_KEY\": \"bidMethod\",\n" +
                "    \"FIELD_NAME\": \"评标方法\",\n" +
                "    \"IS_SHOW\": 1,\n" +
                "    \"IS_REQUIRED\": 1,\n" +
                "    \"IS_APPROVAL\": 1,\n" +
                "    \"TYPE\": null,\n" +
                "    \"DICTIONARY_KEY\": null,\n" +
                "    \"SORT_NO\": 5,\n" +
                "    \"CREATE_USER_ID\": \"system_import\",\n" +
                "    \"CREATE_TIME\": \"2016-11-01 10:10:44\",\n" +
                "    \"UPDATE_USER_ID\": null,\n" +
                "    \"UPDATE_TIME\": null,\n" +
                "    \"SYSTEM_STATUS\": 1,\n" +
                "    \"IS_TENANTABLE\": 1,\n" +
                "    \"IS_TENANT_EDITABLE\": 0\n" +
                "  },\n" +
                "  {\n" +
//                "    \"ID\": \"3aa3c93e1dd54941a9ee9a685fd7dc59\",\n" +
                "    \"APP_ID\": \"4ecfe16dc40c11e598bcfa163e84de1b\",\n" +
                "    \"TEMPLATE_TYPE_ID\": \"e8194728a3d240ffaec84d61c724acab\",\n" +
                "    \"FORM_ID\": \"60df9ed4a69e42088b0871a5714dead1\",\n" +
                "    \"FIELD_KEY\": \"bidMode\",\n" +
                "    \"FIELD_NAME\": \"在线投标及开标\",\n" +
                "    \"IS_SHOW\": 1,\n" +
                "    \"IS_REQUIRED\": 1,\n" +
                "    \"IS_APPROVAL\": 0,\n" +
                "    \"TYPE\": null,\n" +
                "    \"DICTIONARY_KEY\": null,\n" +
                "    \"SORT_NO\": 1,\n" +
                "    \"CREATE_USER_ID\": \"system_import\",\n" +
                "    \"CREATE_TIME\": \"2016-11-01 10:10:44\",\n" +
                "    \"UPDATE_USER_ID\": null,\n" +
                "    \"UPDATE_TIME\": null,\n" +
                "    \"SYSTEM_STATUS\": 1,\n" +
                "    \"IS_TENANTABLE\": 1,\n" +
                "    \"IS_TENANT_EDITABLE\": 0\n" +
                "  },\n" +
                "  {\n" +
//                "    \"ID\": \"7fd295127eaa449b8c0d2c23d0f362da\",\n" +
                "    \"APP_ID\": \"4ecfe16dc40c11e598bcfa163e84de1b\",\n" +
                "    \"TEMPLATE_TYPE_ID\": \"e8194728a3d240ffaec84d61c724acab\",\n" +
                "    \"FORM_ID\": \"60df9ed4a69e42088b0871a5714dead1\",\n" +
                "    \"FIELD_KEY\": \"isBidEvakOnline\",\n" +
                "    \"FIELD_NAME\": \"在线评标\",\n" +
                "    \"IS_SHOW\": 1,\n" +
                "    \"IS_REQUIRED\": 1,\n" +
                "    \"IS_APPROVAL\": 0,\n" +
                "    \"TYPE\": null,\n" +
                "    \"DICTIONARY_KEY\": null,\n" +
                "    \"SORT_NO\": 2,\n" +
                "    \"CREATE_USER_ID\": \"system_import\",\n" +
                "    \"CREATE_TIME\": \"2016-11-01 10:10:44\",\n" +
                "    \"UPDATE_USER_ID\": null,\n" +
                "    \"UPDATE_TIME\": null,\n" +
                "    \"SYSTEM_STATUS\": 1,\n" +
                "    \"IS_TENANTABLE\": 1,\n" +
                "    \"IS_TENANT_EDITABLE\": 0\n" +
                "  },\n" +
                "  {\n" +
//                "    \"ID\": \"43da4dbb7cfe493bb67216f3741dff8f\",\n" +
                "    \"APP_ID\": \"4ecfe16dc40c11e598bcfa163e84de1b\",\n" +
                "    \"TEMPLATE_TYPE_ID\": \"e8194728a3d240ffaec84d61c724acab\",\n" +
                "    \"FORM_ID\": \"60df9ed4a69e42088b0871a5714dead1\",\n" +
                "    \"FIELD_KEY\": \"priceTemplate\",\n" +
                "    \"FIELD_NAME\": \"开标一览表\",\n" +
                "    \"IS_SHOW\": 1,\n" +
                "    \"IS_REQUIRED\": 1,\n" +
                "    \"IS_APPROVAL\": 0,\n" +
                "    \"TYPE\": null,\n" +
                "    \"DICTIONARY_KEY\": null,\n" +
                "    \"SORT_NO\": 4,\n" +
                "    \"CREATE_USER_ID\": \"system_import\",\n" +
                "    \"CREATE_TIME\": \"2016-11-01 10:10:44\",\n" +
                "    \"UPDATE_USER_ID\": null,\n" +
                "    \"UPDATE_TIME\": null,\n" +
                "    \"SYSTEM_STATUS\": 1,\n" +
                "    \"IS_TENANTABLE\": 1,\n" +
                "    \"IS_TENANT_EDITABLE\": 0\n" +
                "  }\n" +
                "]";
        return JSONArray.parseArray(json,Record.class);
    }

}
