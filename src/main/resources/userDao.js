{
    "dataSource": {
    "type": "com.alibaba.druid.pool.DruidDataSource",
        "events": {
        "depose": "close"
    },
    "fields": {
        "url": "${userurl}",
            "username": "${userusername}",
            "password": "${userpassword}",
            "maxWait": 15000,
            "defaultAutoCommit": false
    }
    }
}