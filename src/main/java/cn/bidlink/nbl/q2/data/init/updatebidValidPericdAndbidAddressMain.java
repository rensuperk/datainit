package cn.bidlink.nbl.q2.data.init;

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
import org.nutz.trans.Atom;
import org.nutz.trans.Trans;

import javax.sql.DataSource;

/**
 * Created by renkai on 2017/2/17.
 * 修改投标有效期和投标地点的显示
 * Formkey = "app-set-bidding-process-bid-notice-form"
 * DataitemKey = "bidValidPericd"和"bidAddress"
 *
 */
public class updatebidValidPericdAndbidAddressMain {
    public static void main(String[] args) {
        Ioc ioc = new NutIoc(new JsonLoader("oaDao.js"));
        DataSource ds = ioc.get(DataSource.class);
        final NutDao oaDao = new NutDao(ds);
        Trans.exec(new Atom() {
            public void run() {
               update(oaDao,false);

            }

            });
        Trans.exec(new Atom() {
            public void run() {
                update(oaDao,true);

            }
        });
    }

    public static void update(final NutDao oaDao,boolean isSys){
        if(isSys){
            updateEach(oaDao,"sys_app_set_form","sys_app_set_data_item");
        }else{
            updateEach(oaDao,"app_set_form","app_set_data_item");
        }
    }

    private static void updateEach(final NutDao oaDao, String formName, final String dataitemName) {
        oaDao.each(formName,Cnd.where("FORM_KEY","in",new String[] {"app-set-bidding-process-bid-notice-form","app-set-bidding-process-alteration-notice-form"}), new Each<Record>() {
            public void invoke(int index, Record ele, int length) throws ExitLoop, ContinueLoop, LoopException {
                System.out.println(ele.getString("ID"));
                oaDao.each(dataitemName,Cnd.wrap("where FORM_ID='"+ele.getString("ID") +"' AND FIELD_KEY IN ('bidValidPericd','bidAddress')"), new Each<Record>() {

                    public void invoke(int index, Record ele, int length) throws ExitLoop, ContinueLoop, LoopException {
                        Chain is_show = Chain.make("is_show", false);
                        oaDao.update(dataitemName,is_show,Cnd.where("ID","=",ele.getString("ID")).and("is_show","=",true));
                        System.out.println(ele);
                    }
                });
            }
        });
    }

    public static void update(NutDao oaDao){

    }

}
