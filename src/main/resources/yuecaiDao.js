{
    "dataSource": {
    "type": "com.alibaba.druid.pool.DruidDataSource",
        "events": {
        "depose": "close"
    },
    "fields": {
        "url": "${yuecaiurl}",
            "username": "${yuecaiusername}",
            "password": "${yuecaipassword}",
            "maxWait": 15000,
            "defaultAutoCommit": false
    }
    }
}