{
    "dataSource": {
    "type": "com.alibaba.druid.pool.DruidDataSource",
        "events": {
        "depose": "close"
    },
    "fields": {
        "url": "${oaurl}",
            "username": "${oausername}",
            "password": "${oapassword}",
            "maxWait": 15000,
            "defaultAutoCommit": false
    }
    }
}