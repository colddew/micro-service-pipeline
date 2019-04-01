# brief

build the latest microservice CI/CD architecture with popular technologies

# tech stack

- [ ] SpringBoot

- [ ] Docker

- [x] Kubernetes

- [ ] Istio

- [ ] Zipkin / Jaeger

- [ ] Grafana

- [ ] Jenkins

- [ ] Gitlab

- [ ] Nexus

- [ ] Granafa

# details

## add k8s support for mac docker

- pull k8s images manually

  rm -rf ~/.kube

  ./docker-k8s-images.sh

- check k8s installation

  kubectl version
  
  kubectl cluster-info
  
  kubectl get nodes
  
- add k8s dashboard

  kubectl apply -f https://raw.githubusercontent.com/kubernetes/dashboard/v1.10.1/src/deploy/recommended/kubernetes-dashboard.yaml

  kubectl proxy
  
  TOKEN=$(kubectl -n kube-system describe secret default| awk '$1=="token:"{print $2}')
  
  kubectl config set-credentials docker-for-desktop --token="${TOKEN}"
  
  visit http://localhost:8001/api/v1/namespaces/kube-system/services/https:kubernetes-dashboard:/proxy/, click `shift + command + .`, select config file and login
