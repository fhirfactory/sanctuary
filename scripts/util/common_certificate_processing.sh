#! /bin/bash

function common_certificate_processing() {
	while getopts_long ':certSubject: certPwd: createKeystore: truststorePwd: createPk8ForPostgresClientAuth: altCertSubject: doNOTSecureKeyFileWithAPwd:' OPTKEY; do
	    case "${OPTKEY}" in
	        'certSubject') 
	        	certificate_subject=${OPTARG};;
	        'certPwd')
	        	certificate_password=${OPTARG};;
	        'createKeystore') 
	        	create_keystore=${OPTARG};;
	        'truststorePwd')
	        	truststore_password=${OPTARG};;
        	'createPk8ForPostgresClientAuth')
        		create_for_postgres=${OPTARG};;
        	'altCertSubject')
        		alternate_certificate_subject=${OPTARG};;
        	'doNOTSecureKeyFileWithAOPwd')
        		do_not_secure_keyfile=${OPTARG}
	    esac
	done
	
    if [[ $createPk8ForPostgresClientAuth  = 'Y' || $createPk8ForPostgresClientAuth  = 'y' ]]
        openssl pkcs8 -topk8 -inform PEM -in ($certSubject + ".key") -outform DER -out ($certSubject + ".pk8") -v1 PBE-MD5-DES -passin ("pass:" + $certPwd) -passout ("pass:" + $certPwd)
    }
    if($createKeystore) {
        if(Test-Path ("ca" + $caType + "-root.cer")) {
            keytool -import -keystore ($certSubject + ".jks") -file ("ca" + $caType + ".cer") -alias intermidateCA -storepass $certPwd -noprompt
            keytool -import -keystore ($certSubject + ".jks") -file ("ca" + $caType + "-root.cer") -alias root -storepass $certPwd -noprompt
        } else {
            keytool -import -keystore ($certSubject + ".jks") -file ("ca" + $caType + ".cer") -alias root -storepass $certPwd -noprompt
        }
        openssl pkcs12 -export -in ($certSubject + ".cer") -inkey ($certSubject + ".key") -out ($certSubject + ".p12") -name $certSubject -CAfile ("ca" + $caType + ".cer") -chain -passin ("pass:" + $certPwd) -passout ("pass:" + $certPwd)
        keytool -importkeystore -deststorepass $certPwd -destkeypass $certPwd -destkeystore ($certSubject + ".jks") -srckeystore ($certSubject + ".p12") -srcstoretype PKCS12 -srcstorepass $certPwd -alias $certSubject
        rm ($certSubject + ".p12")
    }
    if(($truststorePwd -ne $null) -and ($truststorePwd -ne "")) {
        if(Test-Path ("ca" + $caType + "-root.cer")) {
            keytool -import -keystore ($certSubject + "-cert.jks") -file ("ca" + $caType + ".cer") -alias intermidateCA -storepass $truststorePwd -noprompt
            keytool -import -keystore ($certSubject + "-cert.jks") -file ("ca" + $caType + "-root.cer") -alias root -storepass $truststorePwd  -noprompt
        } else {
            keytool -import -keystore ($certSubject + "-cert.jks") -file ("ca" + $caType + ".cer") -alias root -storepass $truststorePwd -noprompt
        }
        keytool -import -keystore ($certSubject + "-cert.jks") -file ($certSubject + ".cer") -alias $certSubject -storepass $truststorePwd -noprompt
    }
}