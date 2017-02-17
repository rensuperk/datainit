package cn.bidlink.nbl.bidevalonline.data.init;

import org.apache.commons.lang3.StringUtils;
import org.nutz.dao.Chain;
import org.nutz.dao.Cnd;
import org.nutz.dao.entity.Record;
import org.nutz.dao.impl.NutDao;
import org.nutz.ioc.Ioc;
import org.nutz.ioc.impl.NutIoc;
import org.nutz.ioc.loader.json.JsonLoader;
import org.nutz.lang.ContinueLoop;
import org.nutz.lang.Each;
import org.nutz.lang.ExitLoop;
import org.nutz.lang.LoopException;

import javax.sql.DataSource;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Created by renkai on 2017/2/9.
 */
public class AddEvalMethodOtherDictionaryMail {
    public static void main(String[] args) {
        Ioc ioc = new NutIoc(new JsonLoader("oaDao.js"));
        DataSource ds = ioc.get(DataSource.class);
        NutDao oaDao = new NutDao(ds);
        addSysDic(oaDao);
        addDic(oaDao);

    }
    public static void addDic(final NutDao oaDao){
        int each = oaDao.each("dictionary", Cnd.where("CODE", "=", "013"), new Each<Record>() {
            public void invoke(int index, Record ele, int length) throws ExitLoop, ContinueLoop, LoopException {
                String tenantId = ele.getString("TENANT_ID");
                Record record = oaDao.fetch("dictionary", Cnd.where("CODE", "=", "013009").and("TENANT_ID","=",tenantId));
                if(record == null){
                    System.out.println("添加"+tenantId);
                    Chain make = Chain.make("ID", StringUtils.replace(UUID.randomUUID().toString(),"-",""))
                            .add("value","其他")
                            .add("PCODE","013")
                            .add("CODE","013009")
                            .add("CREATE_TIME",new Date())
                            .add("IS_SYSTEM",1)
                            .add("IS_EDITABLE",0)
                            .add("IS_ADDCHILDRENABLE",0)
                            .add("TENANT_ID",tenantId)
                            ;
                    oaDao.insert("dictionary",make);
                    System.out.println("添加租户数据字典"+tenantId);
                }else{
                    System.out.println("无需添加租户数据字典"+tenantId);
                }
            }
        });

    }
    public static void addSysDic(NutDao oaDao){
        Record record = oaDao.fetch("sys_dictionary", Cnd.where("CODE", "=", "013009"));
        if(record == null){
            Chain make = Chain.make("ID", StringUtils.replace(UUID.randomUUID().toString(),"-",""))
                    .add("value","其他")
                    .add("PCODE","013")
                    .add("CODE","013009")
                    .add("SYSTEM_STATUS",1)
                    .add("CREATE_TIME",new Date())
                    .add("IS_TENANT_EDITABLE",0)
                    .add("IS_TENANTABLE",1)
                    .add("IS_ADDCHILDRENABLE",0)
                    ;
            oaDao.insert("sys_dictionary",make);
            System.out.println("sys_dictionary添加租户数据字典");
        }else{
            System.out.println("sys_dictionary无需添加租户数据字典");
        }
    }
}
