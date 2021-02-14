# vlsm-calculator-api

```bash
curl -H "Content-Type: application/json" -H "Accept: application/json" --data @body.json http://localhost:8080/
```

## Input Exemple
```json
{
    "cidr": "192.168.1.0/24",
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
        "name": "INFORMATIQUE",
        "neededSize": 4,
        "allocatedSize": 6,
        "address": "192.168.1.0",
        "mask": "/29",
        "decMask": "255.255.255.248",
        "broadcast": "192.168.1.7",
        "firstUsableAddress": "192.168.1.1",
        "lastUsableAddress": "192.168.1.6"
    },
    {
        "name": "COMPTABILITE",
        "neededSize": 6,
        "allocatedSize": 6,
        "address": "192.168.1.8",
        "mask": "/29",
        "decMask": "255.255.255.248",
        "broadcast": "192.168.1.15",
        "firstUsableAddress": "192.168.1.9",
        "lastUsableAddress": "192.168.1.14"
    },
    {
        "name": "RH",
        "neededSize": 10,
        "allocatedSize": 14,
        "address": "192.168.1.16",
        "mask": "/28",
        "decMask": "255.255.255.240",
        "broadcast": "192.168.1.31",
        "firstUsableAddress": "192.168.1.17",
        "lastUsableAddress": "192.168.1.30"
    },
    {
        "name": "COMMERCIAL",
        "neededSize": 24,
        "allocatedSize": 30,
        "address": "192.168.1.32",
        "mask": "/27",
        "decMask": "255.255.255.224",
        "broadcast": "192.168.1.63",
        "firstUsableAddress": "192.168.1.33",
        "lastUsableAddress": "192.168.1.62"
    },
    {
        "name": "OPERATIONNELS",
        "neededSize": 104,
        "allocatedSize": 126,
        "address": "192.168.1.64",
        "mask": "/25",
        "decMask": "255.255.255.128",
        "broadcast": "192.168.1.191",
        "firstUsableAddress": "192.168.1.65",
        "lastUsableAddress": "192.168.1.190"
    },
    {
        "name": "TELEPHONIE",
        "neededSize": 148,
        "allocatedSize": 254,
        "address": "192.168.1.192",
        "mask": "/24",
        "decMask": "255.255.255.0",
        "broadcast": "192.168.2.191",
        "firstUsableAddress": "192.168.1.193",
        "lastUsableAddress": "192.168.2.190"
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
docker run -d begr/vlsm:latest
```

Enjoy ;-)

