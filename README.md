# QuakeParser API #

### Installation Guide ###

**QuakeParser API use Redis as Cache Manager! See the Install Redis section to continue this guide.**


Clone the Repo and run the command:

```
#!shell

mvn clean install spring-boot:run
```

To test the API use this REST API end point

**GET**        <host>:9010/game             - *Get all games keys*

**GET**        <host>:9010/game/{id}        - *Get game data*


### Install Redis Section ###
*Linux*

```
#!shell

wget http://download.redis.io/releases/redis-3.2.8.tar.gz
tar xzf redis-3.2.8.tar.gz
cd redis-3.2.8
make
```

*Windows*
Download and install Redis from https://redis.io/download

*Run Redis*
Redis Server:

```
#!shell

cd src
./redis-server
```


Redis Client:

```
#!shell

cd src
./redis-cli
```