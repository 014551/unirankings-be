## Server
Distributor ID:	Ubuntu  
Description:	Ubuntu 20.04.4 LTS  
Release:	20.04  
Codename:	focal

---

## Database

### Installing PostgreSQL

* [Guide](https://www.digitalocean.com/community/tutorials/how-to-install-postgresql-on-ubuntu-20-04-quickstart)
```shell
sudo apt update
sudo apt install postgresql postgresql-contrib
sudo systemctl start postgresql.service
```

### Using

* Run psql as `postgres` user
  * `sudo -u postgres psql`
* Switch to `postgres` account on the Ubuntu server
  * `sudo -i -u postgres`
* Run postgresql
  * `psql`
* Run postgresql with specific user
  * `psql -U username`

### Allowing connections with password
[Guide](https://stackoverflow.com/questions/18664074/getting-error-peer-authentication-failed-for-user-postgres-when-trying-to-ge)

1. Go to `nano /etc/postgresql/[version]/main/pg_hba.conf`
2. Allow md5 connection for `postgres` and `all` users by changing
```
local   all             all                                     peer
```
to
```
local   all             all                                     md5
```

### Allowing connections from remote addresses
* Change in `/etc/postgresql/12/main/postgresql.conf`
```
listen_address 'localhost'
```
to
```
listen_address '*'
```

### Start listening for specific IP addresses to connect
* Add 'host' record with remote IP address to `/etc/postgresql/[version]/main/pg_hba.conf`
```
host    all             all             78.45.199.227/0           md5
```

### Cloud service specific
* If your service is running in Cloud, make sure Virtual Machine is allowed to accept connection on Database port 

### Setup
* [Guide](https://medium.com/coding-blocks/creating-user-database-and-adding-access-on-postgresql-8bfcd2f4a91e)
* `psql` and then
```sql
CREATE DATABASE unirankings;
CREATE USER unirankings WITH ENCRYPTED PASSWORD 'password';
GRANT ALL PRIVILEGES ON DATABASE unirankings TO unirankings;
```

---

## Certificate

### LetsEncrypt certificate

* `fullchain.p12` is the name of the file that contains certificate created with LetsEncrypt

### Self-signed certificate

* `unirankings.p12` is the name of the file that contains self-signed certificate

### Specification

* Name of the file containing the certificate needs to be part specified in the env variable `CERTIFICATE_FILE_NAME`
```shell
export CERTIFICATE_FILE_NAME=fullchain.p12
```

---

## Remote deployment

### Steps
1. Build the app with 
```
mvn clean install
```
2. Move the required files into remote
```
scp Dockerfile docker-compose.yml ./target/UniversitiesRankings-1.0-SNAPSHOT.jar remote:~
```
3. Connect to remote and run
```shell
docker-compose up
```

---

## Redis

### Overview

[Redis](https://redis.io/) is used as a caching tool.

### Setup

Redis is installed in a separate Docker container. It is configured in `docker-compose.yml`.

### Redis with SpringBoot

SpringBoot Cache modules are used to integrate Redis into the application.

### Redis CLI

Commands:
* `redis-cli` - to start the CLI experience.
* `brew services start redis` - to start `redis` service with `brew`
* `brew services stop redis` - to stop `redis` service with `brew`
* `brew services info redis` - `redis` service information

### Start Redis

* `redis-server` - run the server with default conf

---

