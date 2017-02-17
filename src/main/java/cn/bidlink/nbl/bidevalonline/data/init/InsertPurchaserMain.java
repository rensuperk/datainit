package cn.bidlink.nbl.bidevalonline.data.init;

import org.apache.commons.lang3.StringUtils;
import org.nutz.dao.Chain;
import org.nutz.dao.Cnd;
import org.nutz.dao.entity.Record;
import org.nutz.dao.impl.NutDao;
import org.nutz.ioc.Ioc;
import org.nutz.ioc.impl.NutIoc;
import org.nutz.ioc.loader.json.JsonLoader;

import javax.sql.DataSource;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Created by renkai on 2017/1/18.
 * 这个方法是同步项目表中的委托机构的数据，插入purchaser表中
 */
public class InsertPurchaserMain {

    public static void main(String[] args) {
        Ioc ioc = new NutIoc(new JsonLoader("biddingDao.js"));
        DataSource ds = ioc.get(DataSource.class);
        NutDao biddingDao = new NutDao(ds);
        List<Record> list = biddingDao.query("project", Cnd.where("BID_CALL_IDS", "is not", null).and("AUDIT_SATATUS", "=", "3"));
        for (Record project : list) {
            String bid_call_ids = project.getString("BID_CALL_IDS");
            String[] bid_call_idslist = bid_call_ids.split(",");
            for (String s : bid_call_idslist) {
                if(StringUtils.isNoneBlank(s)){
                    Record purchaser = biddingDao.fetch("project_purchaser", Cnd.where("PROJECT_ID", "=", project.get("ID")).and("PROJECT_ID", "=", project.get("ID")));
                    if(purchaser == null){
                        Chain make = Chain.make("ID", StringUtils.replace(UUID.randomUUID().toString(),"-",""))
                                .add("PROJECT_ID",project.get("ID"))
                                .add("PURCHASER_ID",s)
                                .add("CREATE_TIME",new Date())
                                .add("CREATE_USER_ID",project.get("CREATE_USER_ID"))
                                .add("ORG_CODE",project.get("ORG_CODE"))
                                .add("TENANT_ID",project.get("TENANT_ID"))
                                .add("IS_TEST",project.get("IS_TEST"))
                                .add("SYSTEM_STATUS",project.get("SYSTEM_STATUS"))
                                ;
                        biddingDao.insert("project_purchaser",make);
                    }
                }

            }
        }


    }
}
