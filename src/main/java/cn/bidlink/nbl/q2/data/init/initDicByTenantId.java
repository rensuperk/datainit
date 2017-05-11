package cn.bidlink.nbl.q2.data.init;

import cn.bidlink.nbl.data.utils.DataSourceUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.nutz.dao.Chain;
import org.nutz.dao.Cnd;
import org.nutz.dao.entity.Record;
import org.nutz.dao.impl.NutDao;
import org.nutz.trans.Atom;
import org.nutz.trans.Trans;

import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Created by renkai on 2017/5/10.
 */
public class initDicByTenantId {
    static Logger logger = Logger.getLogger(initDicByTenantId.class);

    public static void main(String[] args) {

        Trans.exec(new Atom() {
            public void run() {
                Record user = getUser("fecd420066124a2ab478b3781c84f315");
                NutDao oaDao = DataSourceUtils.oaDao();
                init(oaDao,user,"0");
            }
        });

    }

    static Record getUser(String tenantId){
        if(StringUtils.isBlank(tenantId)){

            logger.error("tenantId is blank!!!");
            return null;
        }
        NutDao userDao = DataSourceUtils.userDao();
        return userDao.fetch("user", Cnd.where("ID","=",tenantId).and("TENANT_ID","=",tenantId).and("type","in",new Integer[]{1,2}));
    }
    static void init( NutDao oaDao, Record user, String pcode){
        if(user == null){
            logger.error("租户不存在");
            return;
        }
        String tenantId = user.getString("TENANT_ID");
        List<Record> query = oaDao.query("sys_dictionary", Cnd.where("pcode", "=", pcode).and("is_Tenantable", "=", 1));
        for (Record sysdictionary : query) {
            String code = sysdictionary.getString("code");
            Record haschild = oaDao.fetch("dictionary", Cnd.where("pcode", "=", code));
            if(haschild != null){
                init(oaDao,user,code);
            }
            Record hascode = oaDao.fetch("dictionary", Cnd.where("code", "=", code).and("TENANT_ID","=",tenantId));
            if(hascode != null){
                logger.info("不插入的数据 : code = " +sysdictionary.getString("code") + "; pcode = " + sysdictionary.getString("pcode") + "; value = " + sysdictionary.getString("value"));
                continue;
            }
            logger.info("插入的数据 : code = " +sysdictionary.getString("code") + "; pcode = " + sysdictionary.getString("pcode") + "; value = " + sysdictionary.getString("value"));
            Chain chain = Chain.make("ID", StringUtils.replace(UUID.randomUUID().toString(), "-", ""));
            chain.add("value",sysdictionary.getString("value"));
            chain.add("PCODE",sysdictionary.getString("PCODE"));
            chain.add("CODE",sysdictionary.getString("CODE"));
            chain.add("CUSTOMIZE_CODE",sysdictionary.getString("CUSTOMIZE_CODE"));
            chain.add("IS_SYSTEM",1);
            chain.add("IS_EDITABLE",sysdictionary.getInt("IS_TENANT_EDITABLE"));
            chain.add("IS_ADDCHILDRENABLE",sysdictionary.getInt("IS_ADDCHILDRENABLE"));
            chain.add("TENANT_ID",user.getString("TENANT_ID"));
            chain.add("CREATE_USER_ID",user.getString("ID"));
            chain.add("CREATE_TIME", new Date());
            chain.add("ORG_CODE",user.getLong("ORG_CODE"));
            oaDao.insert("dictionary",chain);
        }

    }
}

