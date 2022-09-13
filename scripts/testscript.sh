#! /bin/bash

source "./util/getopts_long.bash"

while getopts_long ':u: user: a: f: fullName:' OPTKEY; do
    case "${OPTKEY}" in
        'u' | 'user') username=${OPTARG};;
        'a') age=${OPTARG};;
        'f' | 'fullName') fullname=${OPTARG};;
    esac
done
echo "Username: $username";
echo "Age: $age";
echo "Full Name: $fullname";