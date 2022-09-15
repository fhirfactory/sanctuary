microk8s start
microk8s enable dns
microk8s enable ingress
microk8s enable registry:size=40Gi
microk8s enable metallb:10.123.123.0-10.123.123.200
microk8s enable dashboard
microk8s enable rbac
microk8s enable helm3


# Pushing to the insecure registry may fail in some versions of Docker unless the daemon is explicitly
# configured to trust this registry (https://microk8s.io/docs/registry-built-in).

sudo cp ./files/daemon.json /etc/docker/
sudo systemctl restart docker