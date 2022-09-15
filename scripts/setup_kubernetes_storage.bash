#!/bin/bash

source ./util/getopts_long.sh

while getopts_long 's: storageRoot:' OPTKEY; do
    case "${OPTKEY}" in
        'storageRoot' | 's')
          SANCTUARY_STORAGE_ROOT=${OPTARG};;
    esac
done

if [[ -z "$SANCTUARY_STORAGE_ROOT" ]]
then
  echo "Please specify storage root directory (via --storageRoot or -s command-line option)"
  exit;
else
  echo "Create storage directories under $STORAGE_ROOT"
fi

mkdir -p $SANCTUARY_STORAGE_ROOT/configuration
mkdir -p $SANCTUARY_STORAGE_ROOT/hestia-dam-storage
mkdir -p $SANCTUARY_STORAGE_ROOT/hestia-audit-storage
mkdir -p $SANCTUARY_STORAGE_ROOT/ponos-task-storage

chown $(whoami):docker $SANCTUARY_STORAGE_ROOT/configuration
chown $(whoami):docker $SANCTUARY_STORAGE_ROOT/hestia-dam-storage
chown $(whoami):docker $SANCTUARY_STORAGE_ROOT/hestia-audit-storage
chown $(whoami):docker $SANCTUARY_STORAGE_ROOT/ponos-task-storage

