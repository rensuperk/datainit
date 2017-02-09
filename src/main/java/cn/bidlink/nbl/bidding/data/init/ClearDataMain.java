package cn.bidlink.nbl.bidding.data.init;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.nutz.dao.Cnd;
import org.nutz.dao.entity.Record;
import org.nutz.dao.impl.NutDao;
import org.nutz.ioc.Ioc;
import org.nutz.ioc.impl.NutIoc;
import org.nutz.ioc.loader.json.JsonLoader;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by renkai on 2017/2/8.
 */
public class ClearDataMain {

    public static void main(String[] args) {
        List<String> tablse = getTablse();
        Ioc ioc = new NutIoc(new JsonLoader("biddingDao.js"));
        DataSource ds = ioc.get(DataSource.class);
        NutDao biddingDao = new NutDao(ds);
        for (String table : tablse) {
            boolean exists = biddingDao.exists(table);
            if(exists){
                try {
                    List<Record> query = biddingDao.query(table, Cnd.where("TENANT_ID", "=", "5185b412e76111e59ef090b11c392655").and("CREATE_TIME", "<", "2017-1-1"));
                    if(query != null && !query.isEmpty()){
                        int clear = biddingDao.clear(table, Cnd.where("TENANT_ID", "=", "5185b412e76111e59ef090b11c392655").and("CREATE_TIME", "<", "2017-1-1"));
                        System.out.println(table+"表有"+query.size()+"条数据可以删除,删除了"+clear+"条数据");
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


    public static List<String> getTablse(){
        String tableJson = "[\n" +
//                "    {\n" +
//                "        \"Tables_in_nbl_bidding\": \"bak.bid_order\"\n" +
//                "    },\n" +
                "    {\n" +
                "        \"Tables_in_nbl_bidding\": \"basic_project\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"Tables_in_nbl_bidding\": \"basic_project_copy\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"Tables_in_nbl_bidding\": \"basic_project_type\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"Tables_in_nbl_bidding\": \"bid\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"Tables_in_nbl_bidding\": \"bid_file\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"Tables_in_nbl_bidding\": \"bid_history\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"Tables_in_nbl_bidding\": \"bid_invite\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"Tables_in_nbl_bidding\": \"bid_open_eval_person\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"Tables_in_nbl_bidding\": \"bid_order\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"Tables_in_nbl_bidding\": \"bid_order_count\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"Tables_in_nbl_bidding\": \"bid_order_package\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"Tables_in_nbl_bidding\": \"bid_product\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"Tables_in_nbl_bidding\": \"bid_quote_response\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"Tables_in_nbl_bidding\": \"bid_quote_template\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"Tables_in_nbl_bidding\": \"bid_quote_template_data\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"Tables_in_nbl_bidding\": \"bid_quote_template_use\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"Tables_in_nbl_bidding\": \"bid_response\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"Tables_in_nbl_bidding\": \"bidcall_files\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"Tables_in_nbl_bidding\": \"bidcall_inviter\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"Tables_in_nbl_bidding\": \"biddecide\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"Tables_in_nbl_bidding\": \"biddecide_product\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"Tables_in_nbl_bidding\": \"bideval\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"Tables_in_nbl_bidding\": \"bideval_file\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"Tables_in_nbl_bidding\": \"bideval_report\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"Tables_in_nbl_bidding\": \"bideval_report_copy\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"Tables_in_nbl_bidding\": \"bideval_report_copy_20170109\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"Tables_in_nbl_bidding\": \"bideval_report_expert_opinion\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"Tables_in_nbl_bidding\": \"bideval_response\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"Tables_in_nbl_bidding\": \"bideval_score\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"Tables_in_nbl_bidding\": \"bideval_template\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"Tables_in_nbl_bidding\": \"bideval_template_data\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"Tables_in_nbl_bidding\": \"bideval_template_expert_use\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"Tables_in_nbl_bidding\": \"bideval_template_tender_use\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"Tables_in_nbl_bidding\": \"bideval_template_use\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"Tables_in_nbl_bidding\": \"bidopen\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"Tables_in_nbl_bidding\": \"bidopen_bidder_decrypt\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"Tables_in_nbl_bidding\": \"bidopen_file\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"Tables_in_nbl_bidding\": \"bidopen_tender_decrypt\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"Tables_in_nbl_bidding\": \"bidpub\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"Tables_in_nbl_bidding\": \"committee\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"Tables_in_nbl_bidding\": \"committee_expert\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"Tables_in_nbl_bidding\": \"committee_extract\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"Tables_in_nbl_bidding\": \"committee_tender_represent\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"Tables_in_nbl_bidding\": \"communication\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"Tables_in_nbl_bidding\": \"communication_reply\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"Tables_in_nbl_bidding\": \"notice\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"Tables_in_nbl_bidding\": \"notice_file\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"Tables_in_nbl_bidding\": \"package_auth\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"Tables_in_nbl_bidding\": \"package_contract\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"Tables_in_nbl_bidding\": \"package_document\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"Tables_in_nbl_bidding\": \"package_evaluate\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"Tables_in_nbl_bidding\": \"package_file\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"Tables_in_nbl_bidding\": \"package_group\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"Tables_in_nbl_bidding\": \"package_product\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"Tables_in_nbl_bidding\": \"package_product_copy\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"Tables_in_nbl_bidding\": \"packages\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"Tables_in_nbl_bidding\": \"portal_file\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"Tables_in_nbl_bidding\": \"project\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"Tables_in_nbl_bidding\": \"project_budget\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"Tables_in_nbl_bidding\": \"project_budget_subentry\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"Tables_in_nbl_bidding\": \"project_copy\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"Tables_in_nbl_bidding\": \"project_copy_20161110\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"Tables_in_nbl_bidding\": \"project_file\"\n" +
                "    },\n" +
//                "    {\n" +
//                "        \"Tables_in_nbl_bidding\": \"project_number_format\"\n" +
//                "    },\n" +
                "    {\n" +
                "        \"Tables_in_nbl_bidding\": \"project_purchaser\"\n" +
                "    },\n" +
//                "    {\n" +
//                "        \"Tables_in_nbl_bidding\": \"project_type\"\n" +
//                "    },\n" +
//                "    {\n" +
//                "        \"Tables_in_nbl_bidding\": \"project_type_function\"\n" +
//                "    },\n" +
//                "    {\n" +
//                "        \"Tables_in_nbl_bidding\": \"project_type_process\"\n" +
//                "    },\n" +
//                "    {\n" +
//                "        \"Tables_in_nbl_bidding\": \"project_type_process_node\"\n" +
//                "    },\n" +
                "    {\n" +
                "        \"Tables_in_nbl_bidding\": \"purchase_agreement\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"Tables_in_nbl_bidding\": \"room\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"Tables_in_nbl_bidding\": \"room_user\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"Tables_in_nbl_bidding\": \"tender_project_file\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"Tables_in_nbl_bidding\": \"tender_project_node_type_relation\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"Tables_in_nbl_bidding\": \"v1_bid_quote_response\"\n" +
                "    }\n" +
                "]";
        ArrayList<String> strings = new ArrayList<String>();
        for (JSONObject o : JSONArray.parseArray(tableJson, JSONObject.class)) {
            strings.add(o.getString("Tables_in_nbl_bidding"));
        }
        return strings;
    }

}
