#!/bin/bash

sudo apt-get update
sudo apt-get upgrade
sudo apt-get openjdk-11-jdk git maven
sudo apt-get docker.io
sudo usermod -aG docker $(whoami)
sudo snap install microk8s --classic
sudo usermod -aG microk8s $(whoami)
sudo reboot

