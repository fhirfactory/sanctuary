package net.fhirfactory.dricats.sanctuary.mitaf.pas.processingplant.configuration;

import net.fhirfactory.pegacorn.deployment.properties.configurationfilebased.common.segments.ports.interact.InteractClusteredServerPortSegment;
import net.fhirfactory.pegacorn.deployment.properties.configurationfilebased.mitaf.MITaFSubsystemPropertyFile;

public class MITaFIIEConfigurationFile extends MITaFSubsystemPropertyFile {
    private InteractClusteredServerPortSegment interactIngresADTMessaging;

    //
    // Constructor(s)
    //

    public MITaFIIEConfigurationFile(){
        super();
        this.interactIngresADTMessaging = new InteractClusteredServerPortSegment();
    }

    //
    // Getters and Setters
    //

    public InteractClusteredServerPortSegment getInteractIngresADTMessaging() {
        return interactIngresADTMessaging;
    }

    public void setInteractIngresADTMessaging(InteractClusteredServerPortSegment interactIngresADTMessaging) {
        this.interactIngresADTMessaging = interactIngresADTMessaging;
    }

    //
    // To String
    //

    @Override
    public String toString() {
        return "MITaFIIEConfigurationFile{" +
                "interactIngresADTMessaging=" + interactIngresADTMessaging +
                ", petasosSubscriptionsEndpoint=" + getPetasosSubscriptionsEndpoint() +
                ", edgeAsk=" + getEdgeAsk() +
                ", petasosAuditServicesEndpoint=" + getPetasosAuditServicesEndpoint() +
                ", petasosInterceptionEndpoint=" + getPetasosInterceptionEndpoint() +
                ", petasosTaskServicesEndpoint=" + getPetasosTaskServicesEndpoint() +
                ", multiuseInfinispanEndpoint=" + getMultiuseInfinispanEndpoint() +
                ", petasosMetricsEndpoint=" + getPetasosMetricsEndpoint() +
                ", petasosIPCMessagingEndpoint=" + getPetasosIPCMessagingEndpoint() +
                ", edgeAnswer=" + getEdgeAnswer() +
                ", petasosTopologyDiscoveryEndpoint=" + getPetasosTopologyDiscoveryEndpoint() +
                ", defaultServicePortLowerBound=" + getDefaultServicePortLowerBound() +
                ", loadBalancer=" + getLoadBalancer() +
                ", volumeMounts=" + getVolumeMounts() +
                ", debugProperties=" + getDebugProperties() +
                ", hapiAPIKey=" + getHapiAPIKey() +
                ", javaDeploymentParameters=" + getJavaDeploymentParameters() +
                ", kubeReadinessProbe=" + getKubeReadinessProbe() +
                ", kubeLivelinessProbe=" + getKubeLivelinessProbe() +
                ", prometheusPort=" + getPrometheusPort() +
                ", jolokiaPort=" + getJolokiaPort() +
                ", subsystemInstant=" + getSubsystemInstant() +
                ", deploymentMode=" + getDeploymentMode() +
                ", deploymentSites=" + getDeploymentSites() +
                ", subsystemImageProperties=" + getSubsystemImageProperties() +
                ", trustStorePassword=" + getTrustStorePassword() +
                ", keyPassword=" + getKeyPassword() +
                ", deploymentZone=" + getDeploymentZone() +
                '}';
    }
}
