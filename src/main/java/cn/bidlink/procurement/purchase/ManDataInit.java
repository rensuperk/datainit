package cn.bidlink.procurement.purchase;

import org.nutz.dao.Chain;
import org.nutz.dao.Cnd;
import org.nutz.dao.entity.Record;
import org.nutz.dao.impl.NutDao;
import org.nutz.ioc.Ioc;
import org.nutz.ioc.impl.NutIoc;
import org.nutz.ioc.loader.json.JsonLoader;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

public class ManDataInit {
    public static void main(String[] args) {
        ManDataInit manDataInit = new ManDataInit();
        manDataInit.insertProjectSupplier();
    }

    private void insertProjectSupplier(){
        Ioc yuecaiioc = new NutIoc(new JsonLoader("yuecaiDao.js"));
        DataSource yuecaids = yuecaiioc.get(DataSource.class);
        NutDao yuecaidao = new NutDao(yuecaids);

        Ioc yuecaiSynergyioc = new NutIoc(new JsonLoader("yuecaiSynergyDao.js"));
        DataSource yuecaiSynergyds = yuecaiSynergyioc.get(DataSource.class);
        NutDao yuecaiSynergydao = new NutDao(yuecaiSynergyds);
//        for (int j = 0; j < 10; j++) {
            List<Record> query = yuecaidao.query("bmpfjz_supplier_project_bid", Cnd.where("1", "=", "1"));
            List<Record> completedList = yuecaiSynergydao.query("purchase_supplier_project",Cnd.NEW());
            List<Long> ids = new ArrayList<Long>();
            for (Record record : completedList) {
                long id = record.getLong("id");
                ids.add(id);
             }
        System.out.println("查询到数据" + query.size());
            for (int i = 0; i < query.size(); i++) {
                Record recordSource = query.get(i);
                Chain projectSupplier = Chain.make("supplier_name", recordSource.getString("supplier_name"))
                        .add("supplier_type", recordSource.getString("supplier_type"))
                        .add("is_append", recordSource.getString("append_flag"))
                        .add("invite_flag", recordSource.getString("invite_flag"))
                        .add("supplier_source", recordSource.getString(""))
                        .add("deal_status", recordSource.getString("2"))
                        .add("invite_time", recordSource.getString("create_time"))
                        .add("link_man", recordSource.getString("link_man"))
                        .add("link_phone", recordSource.getString("link_phone"))
                        .add("source_id", recordSource.getString("project_id"))
                        .add("project_id", recordSource.getString("project_id"))
                        .add("company_id", recordSource.getString("comp_id"))
                        .add("supplier_id", recordSource.getString("supplier_id"))
                        .add("create_user_id", recordSource.getString("operator"))
                        .add("create_time", recordSource.getString("create_time"))
                        .add("id", recordSource.getString("id"));

                if(ids.contains(recordSource.getLong("id"))){
                    yuecaiSynergydao.update("purchase_supplier_project",projectSupplier,Cnd.where("id","=",recordSource.getString("id")));
                }else {
                    yuecaiSynergydao.insert("purchase_supplier_project",projectSupplier);
                }
                System.out.println("插入" + recordSource.getString("supplier_name") + " id = " + recordSource.getString("id"));
            }
//        }

    }


}
