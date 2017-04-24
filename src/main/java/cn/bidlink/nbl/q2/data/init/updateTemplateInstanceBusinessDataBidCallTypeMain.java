package cn.bidlink.nbl.q2.data.init;

import cn.bidlink.nbl.data.utils.DataSourceUtils;
import org.nutz.dao.Chain;
import org.nutz.dao.Cnd;
import org.nutz.dao.Sqls;
import org.nutz.dao.entity.Record;
import org.nutz.dao.impl.NutDao;
import org.nutz.dao.sql.Sql;
import org.nutz.lang.ContinueLoop;
import org.nutz.lang.Each;
import org.nutz.lang.ExitLoop;
import org.nutz.lang.LoopException;

/**
 * Created by renkai on 2017/4/21.
 */
public class updateTemplateInstanceBusinessDataBidCallTypeMain {
    public static void main(String[] args) {
        final NutDao nutDao = DataSourceUtils.templateDao();
//        final NutDao biddingDao = DataSourceUtils.biddingDao();
        int each = nutDao.each("template_approval_instance", Cnd.where("BUSINESS_DATA", "like", "%\"bidCallType\":\"028001\"%")
//                .and("process_id","=",1991228)
                , new Each<Record>() {
            public void invoke(int index, Record ele, int length) throws ExitLoop, ContinueLoop, LoopException {
                String group_id = ele.getString("BUSINESS_ID");

                Sql sql = Sqls.fetchString("select pr.`BID_TYPE_CODE` from nbl_bidding.`project` pr left join nbl_bidding.`package_group` pg on pr.`ID`=pg.`PROJECT_ID` where pg.`ID`='" + group_id + "' ");
                DataSourceUtils.biddingDao().execute(sql);
                String bidTypeCode = sql.getString();
                String replace = !"011002".equals(bidTypeCode) ? "招标公告" : "投标邀请函";
                String id = ele.getString("id");
                String business_data = ele.getString("BUSINESS_DATA");
                String new_busidata = business_data.replaceAll("\"bidCallType\":\"028001\"","\"bidCallType\":\""+replace+"\"");
                DataSourceUtils.templateDao().update("template_approval_instance", Chain.make("BUSINESS_DATA",new_busidata), Cnd.where("ID","=",id));
            }
        });
    }

}
