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
	        'caType')
	          certificate_authority_type=${OPTARG};;
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

  certificate_name=("ca" + $certificate_authority_type + "-root-cer")
  certificate_subject_cer=(${certificate_subject} + ".cer")

  if [[ $createPk8ForPostgresClientAuth  == 'Y' || $createPk8ForPostgresClientAuth  == 'y' ]]
  then
    certificate_subject_key=(${certificate_subject} + ".key")
    certificate_subject_pk8=(${certificate_subject} + ".pk8")
    certificate_password_in=("pass:" + ${certificate_password})
    certificate_password_out=("pass:" + ${certificate_password})
    openssl pkcs8 -topk8 -inform PEM -in ${certificate_subject_key} -outform DER -out ${certificate_subject_pk8} -v1 PBE-MD5-DES -passin ${certificate_password_in} -passout ${certificate_password_out}
  fi
  if [[ $createKeystore == 'Y' || $createKeystore == 'y' ]]
  then
    if [ -a $certificate_name ] # does the certificate file exist?
    then
      certificate_subject_jks=($certificate_subject + ".jks")
      keytool -import -keystore certificate_subject_jks -file $certificate_name -alias intermidateCA -storepass $certificate_password -noprompt
      keytool -import -keystore certificate_subject_jks -file $certificate_name -alias root -storepass $certificate_password -noprompt
    else
      keytool -import -keystore $certificate_subject_jks -file $certificate_name -alias root -storepass $certificate_password -noprompt
    fi
    certificate_subject_p12=(${certificate_subject} + ".p12")
    openssl pkcs12 -export -in $certificate_subject_cer -inkey $certificate_subject_key -out $certificate_subject_p12 -name $certificate_subject -CAfile $certificate_name -chain -passin $certificate_password_in -passout $certificate_password_out
    keytool -importkeystore -deststorepass $certificate_password -destkeypass $certificate_password -destkeystore $certificate_subject_jks -srckeystore $certificate_subject_p12 -srcstoretype PKCS12 -srcstorepass $certificate_password -alias $certificate_subject
    rm $certificate_subject_p12
  fi
  if [[ -n $truststore_password ]]
  then
    certificate_subject_cert_jks=($certificate_subject + "-cert.jks")
    if [[ -a $certificate_name ]]
    then
        keytool -import -keystore $certificate_subject_cert_jks -file $certificate_name -alias intermidateCA -storepass $truststore_password -noprompt
        keytool -import -keystore $certificate_subject_cert_jks -file $certificate_name -alias root -storepass $truststore_password  -noprompt
    else
        keytool -import -keystore $certificate_subject_cert_jks -file $certificate_name -alias root -storepass $truststore_password -noprompt
    fi
    keytool -import -keystore $certificate_subject_cert_jks -file $certificate_subject_cer -alias $certificate_subject -storepass $truststore_password -noprompt
  fi
}