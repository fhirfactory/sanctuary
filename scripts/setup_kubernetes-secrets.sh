#! /bin/bash

source "./util/getopts_long.bash"

# microk8s.kubectl create secret generic sanctuary-authorisation-storage-secrets --from-literal=keyPassword="Peg@corn" --namespace=site-a
# microk8s.kubectl create secret generic sanctuary-authorisation-secrets --from-literal=keyPassword="Peg@cornK3y" --from-literal=truststorePassword="Peg@cornTrustSt0reFB" --from-literal=dbUserKeyPassword="Auth@uth" --namespace=site-a

#microk8s.kubectl create secret generic sanctuary-hestia-audit-db --from-literal=keyPassword="Peg@corn" --namespace=site-a
#microk8s.kubectl create secret generic sanctuary-hestia-audit-dm --from-literal=keyPassword="Peg@cornK3yFG" --from-literal=truststorePassword="Peg@cornTrustSt0reLA" --from-literal=dbUserKeyPassword="Peg@corn" --namespace=site-a
#microk8s.kubectl create secret generic sanctuary-hestia-audit-im --from-literal=keyPassword="Peg@cornK3yFG" --from-literal=truststorePassword="Peg@cornTrustSt0reLA" --from-literal=dbUserKeyPassword="Peg@corn" --namespace=site-a
#microk8s.kubectl create secret generic sanctuary-hestia-dam-db --from-literal=keyPassword="Peg@corn" --namespace=site-a
#microk8s.kubectl create secret generic sanctuary-hestia-dam-dm --from-literal=keyPassword="Peg@cornK3yFG" --from-literal=truststorePassword="Peg@cornTrustSt0reLA" --from-literal=dbUserKeyPassword="Peg@corn" --namespace=site-a
#microk8s.kubectl create secret generic sanctuary-hestia-dam-im --from-literal=keyPassword="Peg@cornK3yFG" --from-literal=truststorePassword="Peg@cornTrustSt0reLA" --from-literal=dbUserKeyPassword="Peg@corn" --namespace=site-a
#microk8s.kubectl create secret generic sanctuary-ponos-db --from-literal=keyPassword="Peg@corn" --namespace=site-a
#microk8s.kubectl create secret generic sanctuary-ponos-dm --from-literal=keyPassword="Peg@cornK3yFG" --from-literal=truststorePassword="Peg@cornTrustSt0reLA" --from-literal=dbUserKeyPassword="Peg@corn" --namespace=site-a
#microk8s.kubectl create secret generic sanctuary-ponos-im --from-literal=keyPassword="Peg@cornK3yFG" --from-literal=truststorePassword="Peg@cornTrustSt0reLA" --from-literal=dbUserKeyPassword="Peg@corn" --namespace=site-a
#microk8s.kubectl create secret generic sanctuary-itops-replica-db --from-literal=keyPassword="Peg@corn" --namespace=site-a
#microk8s.kubectl create secret generic sanctuary-itops-replica-dm --from-literal=keyPassword="Peg@cornK3yFG" --from-literal=truststorePassword="Peg@cornTrustSt0reLA" --from-literal=dbUserKeyPassword="Peg@corn" --namespace=site-a
#microk8s.kubectl create secret generic sanctuary-itops-replica-ui --from-literal=keyPassword="Peg@cornK3yFG" --from-literal=truststorePassword="Peg@cornTrustSt0reLA" --from-literal=dbUserKeyPassword="Peg@corn" --namespace=site-a
#microk8s.kubectl create secret generic sanctuary-itops-im --from-literal=keyPassword="Peg@cornK3yFG" --from-literal=truststorePassword="Peg@cornTrustSt0reLA" --from-literal=dbUserKeyPassword="Peg@corn" --namespace=site-a

#microk8s.kubectl create secret generic sanctuary-mitaf-pas --from-literal=keyPassword="Peg@corn" --namespace=site-a
#microk8s.kubectl create secret generic sanctuary-mitaf-miis --from-literal=keyPassword="Peg@corn" --namespace=site-a
#microk8s.kubectl create secret generic sanctuary-mitaf-lis --from-literal=keyPassword="Peg@corn" --namespace=site-a



create_truststore $1