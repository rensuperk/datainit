package cn.bidlink.nbl.bidding.data.init;

import cn.bidlink.nbl.data.utils.DataSourceUtils;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang.time.DateUtils;
import org.nutz.dao.Cnd;
import org.nutz.dao.Sqls;
import org.nutz.dao.entity.Record;
import org.nutz.dao.impl.NutDao;
import org.nutz.dao.impl.sql.NutSql;
import org.nutz.dao.sql.Sql;
import org.nutz.dao.sql.SqlCallback;
import org.nutz.ioc.Ioc;
import org.nutz.ioc.impl.NutIoc;
import org.nutz.ioc.loader.json.JsonLoader;
import org.nutz.trans.Atom;
import org.nutz.trans.Trans;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by renkai on 2017/2/8.
 */
public class Clearb0848DataMain {

    public static void main(String[] args) {
        List<String> tablse = getNblOaTablse();
        final NutDao oaDao = DataSourceUtils.oaDao();

        Trans.exec(new Atom() {
            public void run() {

                clear(oaDao,"50034ae6e76111e59ef090b11c392655");

            }
        });
    }

    public static void clear(NutDao oaDao,String tenantid){
        List<String> tablse = getNblOaTablse();
        for (String table : tablse) {
            boolean exists = oaDao.exists(table);
            if(exists){
                try {
                    List<Record> query = oaDao.query(table, Cnd.where("TENANT_ID", "=", tenantid));

                    if(query != null && !query.isEmpty()){
//                        System.out.println(table+"有"+query.size()+"条数据可以删除");
                        int clear = oaDao.clear(table, Cnd.where("TENANT_ID", "=", tenantid));
                        System.out.println(table+"表有"+query.size()+"条数据可以删除,删除了"+clear+"条数据");
                    }else{
                        System.out.println(table+"没有可以删除的数据");
                    }
//                    for (Record record : query) {
//                        System.out.println(record);
//                    }

                }catch (Exception e){
                    e.printStackTrace();
                    System.out.println(table+"查询失败,可能不存在字段");
                }
            }else {
                System.out.println(table+"表不存在");

            }
        }
    }
    public static List<String> getNblOaTablse(){
        String tableJson = "[\n" +
//                "    {\n" +
//                "        \"Tables_in_nbl_oa\": \"app_set_data_item\"\n" +
//                "    },\n" +
//                "    {\n" +
//                "        \"Tables_in_nbl_oa\": \"app_set_data_item_20161227\"\n" +
//                "    },\n" +
//                "    {\n" +
//                "        \"Tables_in_nbl_oa\": \"app_set_form\"\n" +
//                "    },\n" +
//                "    {\n" +
//                "        \"Tables_in_nbl_oa\": \"app_set_form_20161227\"\n" +
//                "    },\n" +
//                "    {\n" +
//                "        \"Tables_in_nbl_oa\": \"app_set_sort_no_temp\"\n" +
//                "    },\n" +
//                "    {\n" +
//                "        \"Tables_in_nbl_oa\": \"app_set_template_type\"\n" +
//                "    },\n" +
//                "    {\n" +
//                "        \"Tables_in_nbl_oa\": \"app_set_template_type_20161227\"\n" +
//                "    },\n" +
//                "    {\n" +
//                "        \"Tables_in_nbl_oa\": \"archive_base\"\n" +
//                "    },\n" +
//                "    {\n" +
//                "        \"Tables_in_nbl_oa\": \"archive_borrow_record\"\n" +
//                "    },\n" +
//                "    {\n" +
//                "        \"Tables_in_nbl_oa\": \"archive_file\"\n" +
//                "    },\n" +
//                "    {\n" +
//                "        \"Tables_in_nbl_oa\": \"archive_inventory\"\n" +
//                "    },\n" +
//                "    {\n" +
//                "        \"Tables_in_nbl_oa\": \"archive_process_record\"\n" +
//                "    },\n" +
//                "    {\n" +
//                "        \"Tables_in_nbl_oa\": \"bank_account\"\n" +
//                "    },\n" +
//                "    {\n" +
//                "        \"Tables_in_nbl_oa\": \"bid_order\"\n" +
//                "    },\n" +
//                "    {\n" +
//                "        \"Tables_in_nbl_oa\": \"bid_order_count\"\n" +
//                "    },\n" +
//                "    {\n" +
//                "        \"Tables_in_nbl_oa\": \"bid_order_package\"\n" +
//                "    },\n" +
//                "    {\n" +
//                "        \"Tables_in_nbl_oa\": \"bid_order_qualifiedfile\"\n" +
//                "    },\n" +
//                "    {\n" +
//                "        \"Tables_in_nbl_oa\": \"charge\"\n" +
//                "    },\n" +
//                "    {\n" +
//                "        \"Tables_in_nbl_oa\": \"dictionary\"\n" +
//                "    },\n" +
//                "    {\n" +
//                "        \"Tables_in_nbl_oa\": \"dictionary_20161129\"\n" +
//                "    },\n" +
//                "    {\n" +
//                "        \"Tables_in_nbl_oa\": \"dictionary_20170118-1\"\n" +
//                "    },\n" +
//                "    {\n" +
//                "        \"Tables_in_nbl_oa\": \"dictionary_bank\"\n" +
//                "    },\n" +
                "    {\n" +
                "        \"Tables_in_nbl_oa\": \"guarantee\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"Tables_in_nbl_oa\": \"guarantee_bank\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"Tables_in_nbl_oa\": \"notice\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"Tables_in_nbl_oa\": \"notice_content\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"Tables_in_nbl_oa\": \"notice_file\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"Tables_in_nbl_oa\": \"oa_meeting_room\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"Tables_in_nbl_oa\": \"oa_meeting_room_20160922_bak\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"Tables_in_nbl_oa\": \"oa_meeting_room_order\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"Tables_in_nbl_oa\": \"oa_meeting_room_order_20160922_bak\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"Tables_in_nbl_oa\": \"resource_purchaser\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"Tables_in_nbl_oa\": \"resource_supplier\"\n" +
                "    },\n" +
//                "    {\n" +
//                "        \"Tables_in_nbl_oa\": \"sys_app_set_data_item\"\n" +
//                "    },\n" +
//                "    {\n" +
//                "        \"Tables_in_nbl_oa\": \"sys_app_set_data_item_20161215\"\n" +
//                "    },\n" +
//                "    {\n" +
//                "        \"Tables_in_nbl_oa\": \"sys_app_set_form\"\n" +
//                "    },\n" +
//                "    {\n" +
//                "        \"Tables_in_nbl_oa\": \"sys_app_set_form_20161215\"\n" +
//                "    },\n" +
//                "    {\n" +
//                "        \"Tables_in_nbl_oa\": \"sys_app_set_template_type\"\n" +
//                "    },\n" +
//                "    {\n" +
//                "        \"Tables_in_nbl_oa\": \"sys_app_set_template_type_20161215\"\n" +
//                "    },\n" +
//                "    {\n" +
//                "        \"Tables_in_nbl_oa\": \"sys_dictionary\"\n" +
//                "    },\n" +
//                "    {\n" +
//                "        \"Tables_in_nbl_oa\": \"sys_dictionary_20161129\"\n" +
//                "    },\n" +
//                "    {\n" +
//                "        \"Tables_in_nbl_oa\": \"temp_app_set_import_data\"\n" +
//                "    },\n" +
//                "    {\n" +
//                "        \"Tables_in_nbl_oa\": \"website_achievement\"\n" +
//                "    },\n" +
//                "    {\n" +
//                "        \"Tables_in_nbl_oa\": \"website_active_template\"\n" +
//                "    },\n" +
//                "    {\n" +
//                "        \"Tables_in_nbl_oa\": \"website_article\"\n" +
//                "    },\n" +
//                "    {\n" +
//                "        \"Tables_in_nbl_oa\": \"website_certificate\"\n" +
//                "    },\n" +
//                "    {\n" +
//                "        \"Tables_in_nbl_oa\": \"website_channel\"\n" +
//                "    },\n" +
//                "    {\n" +
//                "        \"Tables_in_nbl_oa\": \"website_channel_article_map\"\n" +
//                "    },\n" +
//                "    {\n" +
//                "        \"Tables_in_nbl_oa\": \"website_component\"\n" +
//                "    },\n" +
//                "    {\n" +
//                "        \"Tables_in_nbl_oa\": \"website_dictionary\"\n" +
//                "    },\n" +
//                "    {\n" +
//                "        \"Tables_in_nbl_oa\": \"website_file\"\n" +
//                "    },\n" +
//                "    {\n" +
//                "        \"Tables_in_nbl_oa\": \"website_friend_link\"\n" +
//                "    },\n" +
//                "    {\n" +
//                "        \"Tables_in_nbl_oa\": \"website_info\"\n" +
//                "    },\n" +
//                "    {\n" +
//                "        \"Tables_in_nbl_oa\": \"website_navigation\"\n" +
//                "    }\n" +
                "]";
        ArrayList<String> strings = new ArrayList<String>();
        for (JSONObject o : JSONArray.parseArray(tableJson, JSONObject.class)) {
            strings.add(o.getString("Tables_in_nbl_oa"));
        }
        return strings;
    }

}
