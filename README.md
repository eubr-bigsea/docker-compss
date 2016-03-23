Running COMPSs cluster
----------------------
This is a small doc on how to run a COMPSs cluster with docker containers.

### Requirements
Make sure you have the following packages
* [docker](https://docs.docker.com/ "docker")
* [docker-compose](https://docs.docker.com/compose/overview/ "docker-compose")

Clone and change directory
```bash
$ git clone https://github.com/eubr-bigsea/docker-compss.git
$ cd docker-compss
```

### Building container
This container isn't yet on docker.hub, so you must build it first with

`docker build -t bigsea/compss .`

### Running a simple containers cluster
With

`docker-compose up`

This will run *kmeans* application within three working nodes containers and one
application container, *compss-master*.

### Monitoring service
*compss-master* run a monitor service, which tracks app running process and is
reachable at

`http://localhost:8080/compss-monitor`

To enable all the COMPSs Monitor features applications must run with the `-m` flag.

Running non-default cluster
===========================

Setting working nodes
---------------------
```bash
$ docker run -p 22:22 --entrypoint /usr/sbin/sshd bigsea/compss -D
```
With this container will listen on port `22`, waiting for ssh connections
which will run application jobs

**I've not found any way to change the default SSH port used by COMPSs**

Setting application node
------------------------
First you need to tell COMPSs who are the working nodes. Change
`projects/projects.xml` and `resources/resources.xml` located at
`configuration/xml/` and configure it according to [COMPSs doc]
(http://compss.bsc.es/releases/compss/latest/docs/COMPSs_User_Manual_App_Exec.pdf)

*projects/projects.xml*
```xml
<Project>
  <Worker Name="compss0">
    <InstallDir>/opt/COMPSs/Runtime/scripts/system/</InstallDir>
    <WorkingDir>/tmp/</WorkingDir>
  </Worker>
  ...
  <Worker Name="compss3">
    <InstallDir>/opt/COMPSs/Runtime/scripts/system/</InstallDir>
    <WorkingDir>/tmp/</WorkingDir>
  </Worker>
</Project>
```

*resources/resources.xml*
```xml
<ResourceList>
  <Resource Name="compss0">
    <Capabilities>
    ...
  </Resource>
  ...
  <Resource Name="compss3">
    <Capabilities>
    ...
  </Resource>
</ResourceList>
```

### Running application
The master node is the one which contain and will run and deploy an application
across all working nodes

```bash
$ docker run
  --port 8080:8080                                               \
  --volume=`pwd`/configuration:/opt/COMPSs/Runtime/configuration \
  --volume=`pwd`/workspace/java/kmeans/jar:/var/workspace        \
  --add-host=compss0:172.17.0.11                                 \
  --add-host=compss1:172.17.0.12                                 \
  --add-host=compss2:172.17.0.13                                 \
  --add-host=compss3:172.17.0.14                                 \
  bigsea/compss                                                  \
  runcompss -m kmeans.KMeans -c 4 -i 10 -n 2000 -d 2 -f 4
```

 * `--port 8080` will expose the monitor service on host, e.g http://localhost:8080
 * `--volume=.../configuration:/opt/...` will map the local directory where the configuration files are set
 * `--volume=.../workspace/java/kmeans/jar:/var/workspace` will map the working directory on container
 * `--add-host` include hosts on `/etc/hosts`
 * `runcompss ...` the command which will run on container, if not provided a bash will be open

If you want to run interactively add flags `-ti` and remove the `runcompss ...` argument


