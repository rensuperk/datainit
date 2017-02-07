{
    "dataSource": {
    "type": "com.alibaba.druid.pool.DruidDataSource",
        "events": {
        "depose": "close"
    },
    "fields": {
        "url": "${templateurl}",
            "username": "${templateusername}",
            "password": "${templatepassword}",
            "maxWait": 15000,
            "defaultAutoCommit": false
    }
    }
}
