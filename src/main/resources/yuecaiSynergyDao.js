{
    "dataSource": {
    "type": "com.alibaba.druid.pool.DruidDataSource",
        "events": {
        "depose": "close"
    },
    "fields": {
        "url": "${synergyurl}",
            "username": "${synergyusername}",
            "password": "${synergypassword}",
            "maxWait": 15000,
            "defaultAutoCommit": false
    }
    }
}