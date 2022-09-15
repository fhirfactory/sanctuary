#! /bin/bash

function create_certificate_set() {
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

	if [ -z "$certificate_password" ]; then
		echo "\$1 is empty, certPwd password not set!"
		exit
	fi
	if [ $certificate_password == *\$* ]; then
		echo "Please ensure certPwd does not contain $ characters"
		exit
	fi
		if [ -z "$truststore_password" ]; then
		echo "\$1 is empty, truststorePwd password not set!"
		exit
	fi
  if [ $truststore_password == *\$* ]; then
      echo "Please ensure truststorePwd does not contain $ characters"
      exit
  fi
  $rootcert=Get-ChildItem -Path cert:\LocalMachine\My | Where-Object {$_.Subject -eq "CN=localhostRootCA"}
  [System.Security.SecureString]$certPwdSecure = ConvertTo-SecureString -String $certPwd -Force -AsPlainText
  [String[]]$dnsName = $certSubject
  if(($altCertSubject-ne $null) -and ($altCertSubject-ne "")) {
      $dnsName += $altCertSubject
  }
  $cert = New-SelfSignedCertificate -CertStoreLocation Cert:\LocalMachine\My -Subject ('CN=' + $certSubject + ',OU=Health Directorate,O=Government of Australian Capital Territory,street="Level 2, Building C, Callam Offices",street=Easty Street,L=Woden,ST=Australian Capital Territory,postalCode=2606,C=AU') -DnsName $dnsName -KeyExportPolicy Exportable -KeyLength 2048 -KeyUsage DigitalSignature,KeyEncipherment -Signer $rootCert -NotAfter (Get-Date).AddYears(10)
  [String]$certPath = Join-Path -Path 'cert:\LocalMachine\My\' -ChildPath "$($cert.Thumbprint)"
  Export-PfxCertificate -Cert $certPath -FilePath ($certSubject + ".pfx") -Password $certPwdSecure
  Export-Certificate -Cert $certPath -FilePath ($certSubject + ".crt")
  certutil -encode ($certSubject + ".crt") ($certSubject + ".cer")
  rm ($certSubject + ".crt")
  if($doNOTSecureKeyFileWithAPwd) {
      openssl pkcs12 -in ($certSubject + ".pfx") -nocerts -out ($certSubject + ".key") -passin ("pass:" + $certPwd) -nodes
  } else {
      openssl pkcs12 -in ($certSubject + ".pfx") -nocerts -out ($certSubject + ".key") -passin ("pass:" + $certPwd) -passout ("pass:" + $certPwd)
  }
  Common-Cert-Processing -certSubject $certSubject -certPwd $certPwd -createKeystore $createKeystore -truststorePwd $truststorePwd -createPk8ForPostgresClientAuth $createPk8ForPostgresClientAuth -caType ""
}