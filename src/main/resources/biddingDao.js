{
    "dataSource": {
    "type": "com.alibaba.druid.pool.DruidDataSource",
        "events": {
        "depose": "close"
    },
    "fields": {
        "url": "${biddingurl}",
            "username": "${biddingusername}",
            "password": "${biddingpassword}",
            "maxWait": 15000,
            "defaultAutoCommit": false
    }
    }
}