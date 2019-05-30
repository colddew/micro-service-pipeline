[![Build Status](https://travis-ci.org/colddew/micro-service-pipeline.svg?branch=master)](https://travis-ci.org/colddew/micro-service-pipeline)
[![codecov](https://codecov.io/gh/colddew/micro-service-pipeline/branch/master/graph/badge.svg)](https://codecov.io/gh/colddew/micro-service-pipeline)
[![License](http://img.shields.io/:license-mit-blue.svg?style=flat)](http://doge.mit-license.org)

# Brief

Build the latest microservice CI/CD pipeline architecture with popular technologies

# Tech Stack & Legacy Issue

- [x] SpringBoot

- [x] Sonatype Nexus

- [x] Registry / Harbor

- [x] Gitlab

- [x] Travis CI / Jenkins: pipeline script template & ldap and security matrix

- [x] Docker

- [x] Kubernetes: external storage of cephfs, glusterfs, nfs, etc.

- [x] Istio

- [x] Zipkin / Jaeger

- [x] Kiali

- [x] Grafana / Prometheus / InfluxDB: metrics config, qps, response time, delay time

- [ ] ELK

- [ ] Hystrix / Turbine

- [ ] Etcd / Consul: config center or other ?!

- [ ] Nagios / Zabbix

- [ ] Metrics: graphite, whisper、carbon、graphite-web; kairosdb; statsd; codahale; metrics, gauge、counter、meter、time、histogram

- [ ] Sonar: Checkstyle、FindBugs、Jtes、JUnit、TestNG、Jacoco

# Tips

## install macosx docker desktop

- pay attention to the default versions of docker and k8s should be compatible

- adjust docker memory for k8s or istio if necessary, no less than 6GB

## add k8s support for mac docker

- pull k8s images manually

  rm -rf ~/.kube

  ./script/docker-k8s-images.sh

- check k8s installation status

  kubectl version
  
  kubectl cluster-info
  
  kubectl get nodes
  
- add k8s dashboard

  kubectl apply -f https://raw.githubusercontent.com/kubernetes/dashboard/v1.10.1/src/deploy/recommended/kubernetes-dashboard.yaml

  kubectl proxy
  
  kubectl config set-credentials docker-for-desktop --token=$(kubectl -n kube-system describe secret default| awk '$1=="token:"{print $2}')
  
  visit http://localhost:8001/api/v1/namespaces/kube-system/services/https:kubernetes-dashboard:/proxy/, click `shift + command + .`, select config file and login

## install istio by helm

- init istio

  brew install kubernetes-helm
	
  helm init --history-max 200

  kubectl create namespace istio-system
  
  helm template install/kubernetes/helm/istio-init --name istio-init --namespace istio-system | kubectl apply -f -
  
  kubectl get crds | grep 'istio.io\|certmanager.k8s.io' | wc -l
  
  kubectl get svc -n istio-system
  
  kubectl get pods -n istio-system
  
- collect metrics and logs

  istioctl create -f istio-telemetry.yaml
  
  kubectl -n istio-system port-forward $(kubectl -n istio-system get pod -l app=prometheus -o jsonpath='{.items[0].metadata.name}') 9090:9090 &
  
- add grafana and zipkin support, if you want to use jaeger and you need to delete `--set tracing.provider=zipkin` setting

  helm template install/kubernetes/helm/istio --name istio --namespace istio-system --set tracing.enabled=true --set tracing.ingress.enabled=true --set grafana.enabled=true --set tracing.provider=zipkin | kubectl apply -f -
  
  kubectl -n istio-system port-forward $(kubectl -n istio-system get pod -l app=grafana -o jsonpath='{.items[0].metadata.name}') 3000:3000 &
  
  kubectl -n istio-system port-forward $(kubectl -n istio-system get pod -l app=zipkin -o jsonpath='{.items[0].metadata.name}') 9411:9411 &
  
  kubectl -n istio-system port-forward $(kubectl -n istio-system get pod -l app=jaeger -o jsonpath='{.items[0].metadata.name}') 16686:16686 &
  
- relevant endpoint

  prometheus - `http://localhost:9090/graph`
  
  grafana - `http://localhost:3000/dashboard`
  
  zipkin - `http://localhost:9411`
  
  jaeger - `http://localhost:16686`
  
- config k8s credentials string in `config_base64` file for jenkins, `clusters.cluster.server` should convert to container internal ip
  
  cd ~/.kube
  
  base64 config_k8s > config_k8s_base64
  
## Make Customized Docker Image ( refer to `conf/dind-gradle-Dockerfile` )

  docker build -t cn.plantlink/docker-in-docker-gradle:5.3.1 .
  
  docker tag cn.plantlink/docker-in-docker-gradle:5.3.1 harbor.local/cn.plantlink/docker-in-docker-gradle:5.3.1
  
  docker push harbor.local/cn.plantlink/docker-in-docker-gradle:5.3.1
  
## Build and Deploy microservice
  
- build microservice docker images

  cd micro-service-gradle
    
  ~~gradle micro-service-a:build -x test~~
  
  gradle clean test codeCoverageReport
  
  gradle publishMavenPublicationToMavenRepository
  
  gradle -x test build docker
  
  docker login
  
  docker tag cn.plantlink/micro-service-a:1.0-SNAPSHOT colddew/micro-service-a:1.0-SNAPSHOT
    
  docker push colddew/micro-service-a:1.0-SNAPSHOT
    
  ~~docker run -p 9001:9001 -t cn.plantlink/micro-service-a~~

- deploy microservice docker images

  kubectl run micro-service-a --replicas=2 --labels="app=micro-service" --image cn.plantlink/micro-service-a:1.0-SNAPSHOT --port 9001
  
  kubectl expose deployment --port=9001 micro-service-a --type=LoadBalancer --name=micro-service-a
  
  kubectl scale deployment micro-service-a --replicas=3
  
  kubectl get service micro-service-a -o=wide
  
  kubectl get pods --selector="app=micro-service" -o=wide

## Result Screenshot

![gitlab](https://github.com/colddew/micro-service-pipeline/blob/master/assets/gitlab.png?raw=true)

![sonatype-nexus](https://github.com/colddew/micro-service-pipeline/blob/master/assets/sonatype-nexus.png?raw=true)

![harbor](https://github.com/colddew/micro-service-pipeline/blob/master/assets/harbor.png?raw=true)

![jenkins](https://github.com/colddew/micro-service-pipeline/blob/master/assets/jenkins.png?raw=true)

![kubernetes](https://github.com/colddew/micro-service-pipeline/blob/master/assets/kubernetes.png?raw=true)

![prometheus](https://github.com/colddew/micro-service-pipeline/blob/master/assets/prometheus.png?raw=true)

![jaeger](https://github.com/colddew/micro-service-pipeline/blob/master/assets/jaeger.png?raw=true)

![kiali](https://github.com/colddew/micro-service-pipeline/blob/master/assets/kiali.png?raw=true)

![grafana](https://github.com/colddew/micro-service-pipeline/blob/master/assets/grafana.png?raw=true)