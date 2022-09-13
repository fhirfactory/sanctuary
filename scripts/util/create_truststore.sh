#! /bin/bash

function create_truststore(){
	if [ -z "$1" ]; then
		echo "\$1 is empty, truststore password not set!"
		exit
	fi
	local trust_store_password=$1 
	if [[ "$trust_store_password" == *\$* ]]; then
		echo "Please ensure truststore password does not contain the \$ character"
	fi
	if [ -f "ca-root.cer" ]; then
		keytool -import -keystore truststore.jks -file ca.cer -alias intermidateCA -storepass $truststorePwd -noprompt
        keytool -import -keystore truststore.jks -file ca-root.cer -alias root -storepass $truststorePwd  -noprompt
    else 
    	keytool -import -keystore truststore.jks -file ca.cer -alias intermidateCA -storepass $truststorePwd -noprompt
    fi
    cp truststore.jks truststore-with-default-ca-certs.jks
    keytool -importkeystore -srckeystore $JAVA_HOME\lib\security\cacerts -destkeystore truststore-with-default-ca-certs.jks -srcstorepass 'changeit' -deststorepass $truststorePwd -noprompt
}