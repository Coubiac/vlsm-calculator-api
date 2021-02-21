# vlsm-calculator-api

![Java CI with Maven](https://github.com/Coubiac/vlsm-calculator-api/workflows/Java%20CI%20with%20Maven/badge.svg)
![Code Coverage](https://raw.githubusercontent.com/Coubiac/vlsm-calculator-api/main/.github/badges/jacoco.svg)
[![Codacy Badge](https://api.codacy.com/project/badge/Grade/dd9f8446c28645e9988b2b04bae2eb98)](https://app.codacy.com/gh/Coubiac/vlsm-calculator-api?utm_source=github.com&utm_medium=referral&utm_content=Coubiac/vlsm-calculator-api&utm_campaign=Badge_Grade)

```bash
curl -H "Content-Type: application/json" -H "Accept: application/json" --data @body.json http://localhost:8080/
```

## Input @body.json Exemple
```json
{
    "cidr": "192.168.1.0/23",
    "subnets": 
    [
        {
            "name": "RH",
            "neededSize": 10
        },
        {
            "name": "COMPTABILITE",
            "neededSize": 6
        },
                {
            "name": "COMMERCIAL",
            "neededSize": 24
        },
        {
            "name": "OPERATIONNELS",
            "neededSize": 104
        },
        {
            "name": "INFORMATIQUE",
            "neededSize": 4
        },
        {
            "name": "TELEPHONIE",
            "neededSize": 148
        }

    ]
}
```

## output

```json
[
    {
        "name": "TELEPHONIE",
        "neededSize": 148,
        "allocatedSize": 254,
        "address": "192.168.0.0",
        "mask": "/24",
        "decMask": "255.255.255.0",
        "broadcast": "192.168.0.255",
        "firstUsableAddress": "192.168.0.1",
        "lastUsableAddress": "192.168.0.254"
    },
    {
        "name": "OPERATIONNELS",
        "neededSize": 104,
        "allocatedSize": 126,
        "address": "192.168.1.0",
        "mask": "/25",
        "decMask": "255.255.255.128",
        "broadcast": "192.168.1.127",
        "firstUsableAddress": "192.168.1.1",
        "lastUsableAddress": "192.168.1.126"
    },
    {
        "name": "COMMERCIAL",
        "neededSize": 24,
        "allocatedSize": 30,
        "address": "192.168.1.128",
        "mask": "/27",
        "decMask": "255.255.255.224",
        "broadcast": "192.168.1.159",
        "firstUsableAddress": "192.168.1.129",
        "lastUsableAddress": "192.168.1.158"
    },
    {
        "name": "RH",
        "neededSize": 10,
        "allocatedSize": 14,
        "address": "192.168.1.160",
        "mask": "/28",
        "decMask": "255.255.255.240",
        "broadcast": "192.168.1.175",
        "firstUsableAddress": "192.168.1.161",
        "lastUsableAddress": "192.168.1.174"
    },
    {
        "name": "COMPTABILITE",
        "neededSize": 6,
        "allocatedSize": 6,
        "address": "192.168.1.176",
        "mask": "/29",
        "decMask": "255.255.255.248",
        "broadcast": "192.168.1.183",
        "firstUsableAddress": "192.168.1.177",
        "lastUsableAddress": "192.168.1.182"
    },
    {
        "name": "INFORMATIQUE",
        "neededSize": 4,
        "allocatedSize": 6,
        "address": "192.168.1.184",
        "mask": "/29",
        "decMask": "255.255.255.248",
        "broadcast": "192.168.1.191",
        "firstUsableAddress": "192.168.1.185",
        "lastUsableAddress": "192.168.1.190"
    }
]
```

## How to install

### You can compile
You need java jdk 11 to compile.
```shell
git clone https://github.com/Coubiac/vlsm-calculator-api.git
cd vlsm-calculator-api.git
./mvnw clean install
java -jar target/vlsm*.jar
```
### Or you can use docker
```shell
docker run -d -p 8080:8080 begr/vlsm:latest
```

Enjoy ;-)
