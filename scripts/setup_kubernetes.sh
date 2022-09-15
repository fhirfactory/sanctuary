#!/bin/bash

microk8s.kubectl create namespace site-a
microk8s.kubectl config use-context microk8s
microk8s.kubectl config set-context site-a --namespace=site-a --cluster=microk8s --user=admin
microk8s.kubectl create serviceaccount jgroups-kubeping-service-account -n site-a
microk8s.kubectl apply -f ./yaml/kube-ping-service-account-site-a.yaml