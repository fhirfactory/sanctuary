# Sanctuary Installation (on Ubuntu 20.04+)

## OS / Platform Required Modules

Execute the script:

    sudo ./scripts/setup_os.sh 

which executes the following commands:

`sudo apt-get update`

`sudo apt-get upgrade`

`sudo apt-get openjdk-11-jdk git maven` 

`sudo apt-get docker.io`

`sudo usermod -aG docker $(whoami)`

`sudo snap install microk8s --classic`

`sudo usermod -aG microk8s $(whoami)`

`sudo reboot`

Note that this script WILL reboot the VM / Machine!!!!

## microk8s setup 

Execute the script: 

    ./scripts/setup_kubernetes.sh

which executes the following commands:

`microk8s enable dashboard`

`microk8s enable dns`

`microk8s enable ingress`

`microk8s enable rbac`

`microk8s enable registry:size=40Gi`

`microk8s enable metallb:10.123.123.0-10.123.123.200`

`microk8s enable helm3`

You can see whether the modules are running by executing:

`microk8s status`

## kubernetes configuration

create/assign a default "storage" location for _Sanctuary_

export SANCTUARY_STORAGE_BASE=path/to/location


