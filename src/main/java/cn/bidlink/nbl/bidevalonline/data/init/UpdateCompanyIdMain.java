package cn.bidlink.nbl.bidevalonline.data.init;

import org.apache.log4j.Logger;
import org.nutz.dao.Chain;
import org.nutz.dao.Cnd;
import org.nutz.dao.entity.Record;
import org.nutz.dao.impl.NutDao;
import org.nutz.ioc.Ioc;
import org.nutz.ioc.impl.NutIoc;
import org.nutz.ioc.loader.json.JsonLoader;

import javax.sql.DataSource;
import java.util.*;

/**
 * Created by renkai on 2017/1/19.
 * 更新那些不正确公司id
 */
public class UpdateCompanyIdMain {
    public static Logger logger = Logger.getLogger(UpdateCompanyIdMain.class);
    public static void main(String[] args) throws ClassNotFoundException {

        Ioc ioc = new NutIoc(new JsonLoader("biddingDao.js"));
        DataSource ds = ioc.get(DataSource.class);
        NutDao biddingDao = new NutDao(ds);

        Ioc userioc = new NutIoc(new JsonLoader("userDao.js"));
        DataSource userds = userioc.get(DataSource.class);
        NutDao userDao = new NutDao(userds);
        Set<String> projectSet = new HashSet<String>();
        Map<String, Object> orgMap = new HashMap<String, Object>();
        update("project_purchaser",biddingDao,userDao,projectSet,orgMap);
    }
    public static  void  update(String tableName,NutDao biddingDao,NutDao userDao,Set<String> key,Map<String, Object> nameMap){
        List<Record> purchaser_project = biddingDao.query(tableName, Cnd.where("TENANT_ID", "is not", null));
        int i = 0;
        for (Record record : purchaser_project) {
            String tenant_id = record.getString("TENANT_ID");
            Object company_id = record.get("COMPANY_ID");
//            logger.info("查询"+company_id+" | "+tenant_id);
            if(company_id == null){
                if(!nameMap.containsKey(tenant_id)) {
                    Record record1 = userDao.fetch("user", Cnd.where("ID", "=", tenant_id));
                    if (record1 != null) {
                        nameMap.put(tenant_id, record1.get("OLD_COMP_ID"));
                    }
                }
                company_id = nameMap.get(tenant_id);
                if(company_id == null){
                    continue;
                }
                if(!key.contains(company_id+tenant_id)){
                    biddingDao.update(tableName,
                            Chain.make("COMPANY_ID",company_id),
                            Cnd.where("TENANT_ID", "=", tenant_id));
                    i++;
                    logger.info("更新"+i+"次:"+company_id+" | "+tenant_id);
                    key.add(company_id+tenant_id);
                }
            }
        }
    }
}
