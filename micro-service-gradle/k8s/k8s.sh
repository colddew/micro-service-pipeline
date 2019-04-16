# create namespace, deployment, service, gateway
kubectl apply -f .

# delete namespace, deployment, service, gateway
# kubectl delete -f .

# access gateway
watch -n 1 -d curl -HHost:anmydeMacBook-Pro.local http://localhost:80/micro-service/api/v1/a
watch -n 1 -d curl -HHost:anmydeMacBook-Pro.local http://localhost:80/micro-service/api/v1/a/message
watch -n 1 -d curl -HHost:anmydeMacBook-Pro.local http://localhost:80/micro-service/api/v1/a/rest

# sacle and upgrade micro-service
kubectl scale -n micro-service --replicas=3 deployment/micro-service-a
kubectl get pods -n micro-service -o=wide
kubectl get service micro-service-a -n micro-service -o=wide
kubectl describe deployment -n micro-service
kubectl set image -n micro-service deployment/micro-service-a micro-service-a=cn.plantlink/micro-service-a:1.0.2-SNAPSHOT