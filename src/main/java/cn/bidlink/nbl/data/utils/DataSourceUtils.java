package cn.bidlink.nbl.data.utils;

import org.nutz.dao.impl.NutDao;
import org.nutz.ioc.Ioc;
import org.nutz.ioc.impl.NutIoc;
import org.nutz.ioc.loader.json.JsonLoader;

import javax.sql.DataSource;

/**
 * Created by renkai on 2017/2/28.
 */
public class DataSourceUtils {

    public static NutDao biddingDao(){
        Ioc ioc = new NutIoc(new JsonLoader("biddingDao.js"));
        DataSource ds = ioc.get(DataSource.class);
        NutDao biddingDao = new NutDao(ds);
        return biddingDao;
    }
    public static NutDao oaDao(){
        Ioc ioc = new NutIoc(new JsonLoader("oaDao.js"));
        DataSource ds = ioc.get(DataSource.class);
        NutDao oaDao = new NutDao(ds);
        return oaDao;
    }
    public static NutDao userDao(){
        Ioc ioc = new NutIoc(new JsonLoader("userDao.js"));
        DataSource ds = ioc.get(DataSource.class);
        NutDao userDao = new NutDao(ds);
        return userDao;
    }
    public static NutDao templateDao(){
        Ioc ioc = new NutIoc(new JsonLoader("templateDao.js"));
        DataSource ds = ioc.get(DataSource.class);
        NutDao templateDao = new NutDao(ds);
        return templateDao;
    }
}
