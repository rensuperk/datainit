package cn.bidlink.nbl.project.data.init;

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
 * Created by renkai on 2017/1/18.
 * 这个程序是用来同步总项表和项目表中的orgName不一致的问题
 */
public class OrgNameSyncMain {
    public static void main(String[] args) throws ClassNotFoundException {
        Ioc ioc = new NutIoc(new JsonLoader("biddingDao.js"));
        DataSource ds = ioc.get(DataSource.class);
        NutDao biddingDao = new NutDao(ds);

        Ioc userioc = new NutIoc(new JsonLoader("userDao.js"));
        DataSource userds = userioc.get(DataSource.class);
        NutDao userDao = new NutDao(userds);


        Set<String> basicSet = new HashSet<String>();
        Set<String> projectSet = new HashSet<String>();
        Map<String,Object> orgMap = new HashMap<String, Object>();
        update("basic_project",biddingDao,userDao,basicSet,orgMap);
        update("project",biddingDao,userDao,projectSet,orgMap);

    }
    public static  void  update(String tableName,NutDao biddingDao,NutDao userDao,Set<String> key,Map<String, Object> nameMap){
        List<Record> basic_project = biddingDao.query(tableName, Cnd.where("ORG_CODE", "is not", null));
        System.out.println(basic_project.size());
        for (Record record : basic_project) {
            long org_code = record.getLong("ORG_CODE");
            String tenant_id = record.getString("TENANT_ID");
            Object org_name = record.get("ORG_NAME");
            if(!nameMap.containsKey(org_code+tenant_id)) {
                Record record1 = userDao.fetch("organization", Cnd.where("AUTH_KEY", "=", org_code).and("TENANT_ID", "=", tenant_id));
                if (record1 != null) {

                    nameMap.put(org_code + tenant_id, record1.get("NAME"));
                }
            }
            Object org_name1 = nameMap.get(org_code + tenant_id);
            if(org_name == null && org_name1 == null){
                continue;
            }
            if(org_name == null || !org_name.equals(org_name1)){
//                判断是否已经修改
                if(!key.contains(org_code+tenant_id+org_name1)){
                        biddingDao.update(tableName,
                                Chain.make("ORG_NAME",org_name1),
                                Cnd.where("ORG_CODE", "=", org_code).and("TENANT_ID", "=", tenant_id));
                    System.out.println("发现有不一样的并更新为"+org_code +"---"+tenant_id + "原来的是:"+org_name+"修改后是"+org_name1);
                    key.add(org_code+tenant_id+org_name1);
                }
            }

        }

    }

}
