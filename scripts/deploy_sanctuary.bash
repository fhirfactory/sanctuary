cd ~/Development/Code/Projects/azure/aether-petasos/interzonerepeater/
mkdir -p /volumes/workspace0/aether/deployment/configfiles/subsystem/aether-petasos-interzonerepeater/
cp ~/Development/Code/Projects/azure/aether-host-files/LocalMicroK8sWorkstations/configfiles/subsystem/aether-petasos-interzonerepeater/systemconfig-one-site-a-microk8s.yaml /volumes/workspace0/aether/deployment/configfiles/subsystem/aether-petasos-interzonerepeater/
microk8s.helm3 upgrade aether-petasos-interzonerepeater --install --namespace site-a -f /volumes/workspace0/aether/deployment/configfiles/subsystem/aether-petasos-interzonerepeater/systemconfig-one-site-a-microk8s.yaml ./install/kubernetes/helm

sleep 30

cd ~/Development/Code/Projects/azure/aether-ponos-manager/
mkdir -p /volumes/workspace0/aether/deployment/configfiles/subsystem/aether-ponos-manager/
cp ~/Development/Code/Projects/azure/aether-host-files/LocalMicroK8sWorkstations/configfiles/subsystem/aether-ponos-manager/systemconfig-microk8s-site-a.yaml /volumes/workspace0/aether/deployment/configfiles/subsystem/aether-ponos-manager/
microk8s.helm3 upgrade aether-ponos-manager --install --namespace site-a -f /volumes/workspace0/aether/deployment/configfiles/subsystem/aether-ponos-manager/systemconfig-microk8s-site-a.yaml ./install/kubernetes/helm/

sleep 15

cd  ~/Development/Code/Projects/azure/aether-hestia-audit-im/
mkdir -p /volumes/workspace0/aether/deployment/configfiles/subsystem/aether-hestia-audit-im/
cp ~/Development/Code/Projects/azure/aether-host-files/LocalMicroK8sWorkstations/configfiles/subsystem/aether-hestia-audit-im/systemconfig-microk8s-site-a.yaml /volumes/workspace0/aether/deployment/configfiles/subsystem/aether-hestia-audit-im/
microk8s.helm3 upgrade aether-hestia-audit-im --install --namespace site-a -f /volumes/workspace0/aether/deployment/configfiles/subsystem/aether-hestia-audit-im/systemconfig-microk8s-site-a.yaml ./install/kubernetes/helm/

sleep 45

cd  ~/Development/Code/Projects/azure/aether-itops/im
mkdir -p /volumes/workspace0/aether/deployment/configfiles/subsystem/aether-itops-im/
cp ~/Development/Code/Projects/azure/aether-host-files/LocalMicroK8sWorkstations/configfiles/subsystem/aether-itops-im/systemconfig-microk8s-site-a.yaml /volumes/workspace0/aether/deployment/configfiles/subsystem/aether-itops-im/
microk8s.helm3 upgrade aether-itops-im --install --namespace site-a -f /volumes/workspace0/aether/deployment/configfiles/subsystem/aether-itops-im/systemconfig-microk8s-site-a.yaml ./install/kubernetes/helm/

sleep 15

cd ~/Development/Code/Projects/azure/aether-mitaf-iie/
mkdir -p /volumes/workspace0/aether/deployment/configfiles/subsystem/aether-mitaf-iie/
mkdir -p /volumes/workspace0/aether/deployment/transforms/subsystem/aether-mitaf-iie/
cp ~/Development/Code/Projects/azure/aether-host-files/LocalMicroK8sWorkstations/configfiles/subsystem/aether-mitaf-iie/systemconfig-microk8s-site-a.yaml /volumes/workspace0/aether/deployment/configfiles/subsystem/aether-mitaf-iie/
cp ../aether-host-files/subsystem/aether-mitaf-iie/*.ftl /volumes/workspace0/aether/deployment/transforms/subsystem/aether-mitaf-iie/
microk8s.helm3 upgrade aether-mitaf-iie --install --namespace site-a -f /volumes/workspace0/aether/deployment/configfiles/subsystem/aether-mitaf-iie/systemconfig-microk8s-site-a.yaml install/kubernetes/helm

sleep 15

cd ~/Development/Code/Projects/azure/aether-mitaf-kestral/
mkdir -p /volumes/workspace0/aether/deployment/configfiles/subsystem/aether-mitaf-kestral/
mkdir -p /volumes/workspace0/aether/deployment/transforms/subsystem/aether-mitaf-kestral/
cp ../aether-host-files/LocalMicroK8sWorkstations/configfiles/subsystem/aether-mitaf-kestral/systemconfig-microk8s-site-a.yaml /volumes/workspace0/aether/deployment/configfiles/subsystem/aether-mitaf-kestral/
cp ../aether-host-files/subsystem/aether-mitaf-kestral/*.ftl /volumes/workspace0/aether/deployment/transforms/subsystem/aether-mitaf-kestral/
microk8s.helm3 upgrade aether-mitaf-kestral --install --namespace site-a -f /volumes/workspace0/aether/deployment/configfiles/subsystem/aether-mitaf-kestral/systemconfig-microk8s-site-a.yaml install/kubernetes/helm

sleep 15

cd ~/Development/Code/Projects/azure/aether-mitaf-pathologyreferencelab-capital/
mkdir -p /volumes/workspace0/aether/deployment/configfiles/subsystem/aether-mitaf-pathologyreferencelab-capital/
mkdir -p /volumes/workspace0/aether/deployment/transforms/subsystem/aether-mitaf-pathologyreferencelab-capital/
cp ../aether-host-files/LocalMicroK8sWorkstations/configfiles/subsystem/aether-mitaf-pathologyreferencelab-capital/systemconfig-microk8s-site-a.yaml /volumes/workspace0/aether/deployment/configfiles/subsystem/aether-mitaf-pathologyreferencelab-capital/
cp ../aether-host-files/subsystem/aether-mitaf-pathologyreferencelab-capital/*.ftl /volumes/workspace0/aether/deployment/transforms/subsystem/aether-mitaf-pathologyreferencelab-capital/
microk8s.helm3 upgrade aether-mitaf-pathologyreferencelab-capital --install --namespace site-a -f /volumes/workspace0/aether/deployment/configfiles/subsystem/aether-mitaf-pathologyreferencelab-capital/systemconfig-microk8s-site-a.yaml install/kubernetes/helm

sleep 15

cd ~/Development/Code/Projects/azure/aether-mitaf-pathologyreferencelab-laverty/
mkdir -p /volumes/workspace0/aether/deployment/configfiles/subsystem/aether-mitaf-pathologyreferencelab-laverty/
mkdir -p /volumes/workspace0/aether/deployment/transforms/subsystem/aether-mitaf-pathologyreferencelab-laverty/
cp ../aether-host-files/LocalMicroK8sWorkstations/configfiles/subsystem/aether-mitaf-pathologyreferencelab-laverty/systemconfig-microk8s-site-a.yaml /volumes/workspace0/aether/deployment/configfiles/subsystem/aether-mitaf-pathologyreferencelab-laverty/
cp ../aether-host-files/subsystem/aether-mitaf-pathologyreferencelab-laverty/*.ftl /volumes/workspace0/aether/deployment/transforms/subsystem/aether-mitaf-pathologyreferencelab-laverty/
microk8s.helm3 upgrade aether-mitaf-pathologyreferencelab-laverty --install --namespace site-a -f /volumes/workspace0/aether/deployment/configfiles/subsystem/aether-mitaf-pathologyreferencelab-laverty/systemconfig-microk8s-site-a.yaml install/kubernetes/helm

cd ~/Development/Code/Projects/azure/aether-mitaf-sunquest/
mkdir -p /volumes/workspace0/aether/deployment/configfiles/subsystem/aether-mitaf-sunquest/
mkdir -p /volumes/workspace0/aether/deployment/transforms/subsystem/aether-mitaf-sunquest/
cp ../aether-host-files/LocalMicroK8sWorkstations/configfiles/subsystem/aether-mitaf-sunquest/systemconfig-microk8s-site-a.yaml /volumes/workspace0/aether/deployment/configfiles/subsystem/aether-mitaf-sunquest/
cp ../aether-host-files/subsystem/aether-mitaf-sunquest/*.ftl /volumes/workspace0/aether/deployment/transforms/subsystem/aether-mitaf-sunquest/
microk8s.helm3 upgrade aether-mitaf-sunquest --install --namespace site-a -f /volumes/workspace0/aether/deployment/configfiles/subsystem/aether-mitaf-sunquest/systemconfig-microk8s-site-a.yaml install/kubernetes/helm

cd ~/Development/Code/Projects/azure/aether-mitaf-bridges/
mkdir -p /volumes/workspace0/aether/deployment/configfiles/subsystem/aether-mitaf-bridges/
mkdir -p /volumes/workspace0/aether/deployment/transforms/subsystem/aether-mitaf-bridges/
cp ../aether-host-files/LocalMicroK8sWorkstations/configfiles/subsystem/aether-mitaf-bridges/systemconfig-microk8s-site-a.yaml /volumes/workspace0/aether/deployment/configfiles/subsystem/aether-mitaf-bridges/
cp ../aether-host-files/subsystem/aether-mitaf-bridges/*.ftl /volumes/workspace0/aether/deployment/transforms/subsystem/aether-mitaf-bridges/
microk8s.helm3 upgrade aether-mitaf-bridges --install --namespace site-a -f /volumes/workspace0/aether/deployment/configfiles/subsystem/aether-mitaf-bridges/systemconfig-microk8s-site-a.yaml install/kubernetes/helm

cd ~/Development/Code/Projects/azure/aether-mitaf-aria/
mkdir -p /volumes/workspace0/aether/deployment/configfiles/subsystem/aether-mitaf-aria/
mkdir -p /volumes/workspace0/aether/deployment/transforms/subsystem/aether-mitaf-aria/
cp ../aether-host-files/LocalMicroK8sWorkstations/configfiles/subsystem/aether-mitaf-aria/systemconfig-microk8s-site-a.yaml /volumes/workspace0/aether/deployment/configfiles/subsystem/aether-mitaf-aria/
cp ../aether-host-files/subsystem/aether-mitaf-aria/*.ftl /volumes/workspace0/aether/deployment/transforms/subsystem/aether-mitaf-aria/
microk8s.helm3 upgrade aether-mitaf-aria --install --namespace site-a -f /volumes/workspace0/aether/deployment/configfiles/subsystem/aether-mitaf-aria/systemconfig-microk8s-site-a.yaml install/kubernetes/helm

cd ~/Development/Code/Projects/azure/aether-mitaf-endobase/
mkdir -p /volumes/workspace0/aether/deployment/configfiles/subsystem/aether-mitaf-endobase/
mkdir -p /volumes/workspace0/aether/deployment/transforms/subsystem/aether-mitaf-endobase/
cp ../aether-host-files/LocalMicroK8sWorkstations/configfiles/subsystem/aether-mitaf-endobase/systemconfig-microk8s-site-a.yaml /volumes/workspace0/aether/deployment/configfiles/subsystem/aether-mitaf-endobase/
cp ../aether-host-files/subsystem/aether-mitaf-endobase/*.ftl /volumes/workspace0/aether/deployment/transforms/subsystem/aether-mitaf-endobase/
microk8s.helm3 upgrade aether-mitaf-endobase --install --namespace site-a -f /volumes/workspace0/aether/deployment/configfiles/subsystem/aether-mitaf-endobase/systemconfig-microk8s-site-a.yaml install/kubernetes/helm

cd ~/Development/Code/Projects/azure/aether


