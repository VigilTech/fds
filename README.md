# Fundamentals of Distributed System
Workshop Project Material

In order to use this material you are going to need to ...

* Install a local docker system 
* Download and run Nats streaming 


### Install Docker

Follow the instructions at ... 
https://docs.docker.com/get-docker/

### Download and Run Nats.io

>$ docker pull nats-streaming

 
Then to run on Windows
> docker run -d -p 4222:4222 -p 8222:8222 nats-streaming nats-streaming-server -p 4222 -m 8222
 
or on Mac / Linux

> docker run -d -p 4222:4222 -p 8222:8222 nats-streaming -p 4222 -m 8222

Too check that it is running

> docker ps
