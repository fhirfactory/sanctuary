#! /bin/bash

source "./util/getopts_long.sh"

while getopts_long 'u:a:f: user: age: fullName:' OPTKEY; do
    case "${OPTKEY}" in
        'u' | 'user') username=${OPTARG};;
        'a' | 'age') age=${OPTARG};;
        'f' | 'fullName') fullname=${OPTARG};;
    esac
done
if [[ -n $age ]]
then
  echo "Age exists"
fi
echo "Username: $username";
echo "Age: $age";
echo "Full Name: $fullname";