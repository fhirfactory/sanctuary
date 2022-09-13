/*
 * Copyright (c) 2022 Mark A. Hunter (ACT Health)
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package net.fhirfactory.dricats.sanctuary.mitaf.lms.processingplant.configuration;


import net.fhirfactory.pegacorn.deployment.properties.configurationfilebased.common.segments.ports.interact.InteractClusteredServerPortSegment;
import net.fhirfactory.pegacorn.deployment.properties.configurationfilebased.common.segments.ports.interact.InteractClientPortSegment;
import net.fhirfactory.pegacorn.deployment.properties.configurationfilebased.mitaf.MITaFSubsystemPropertyFile;

public class MITaFLMSConfigurationFile extends MITaFSubsystemPropertyFile {

    InteractClusteredServerPortSegment interactORUIngres;
    InteractClusteredServerPortSegment interactQRYIngres;
    InteractClientPortSegment interactAllEgress;

    public MITaFLMSConfigurationFile(){
        super();
        this.interactORUIngres = new InteractClusteredServerPortSegment();
        this.interactQRYIngres = new InteractClusteredServerPortSegment();
        this.interactAllEgress = new InteractClientPortSegment();
    }

    public InteractClusteredServerPortSegment getInteractORUIngres() {
        return interactORUIngres;
    }

    public void setInteractORUIngres(InteractClusteredServerPortSegment interactORUIngres) {
        this.interactORUIngres = interactORUIngres;
    }

    public InteractClusteredServerPortSegment getInteractQRYIngres() {
        return interactQRYIngres;
    }

    public void setInteractQRYIngres(InteractClusteredServerPortSegment interactQRYIngres) {
        this.interactQRYIngres = interactQRYIngres;
    }

    public InteractClientPortSegment getInteractAllEgress() {
        return interactAllEgress;
    }

    public void setInteractAllEgress(InteractClientPortSegment interactAllEgress) {
        this.interactAllEgress = interactAllEgress;
    }

    @Override
    public String toString() {
        return "MITaFPathologyReferenceLabLavertyConfigurationFile{" +
                "interactIngresORU=" + interactORUIngres +
                "interactIngresQRY=" + interactQRYIngres +
                ", interactEgress=" + interactAllEgress +
                ", edgeAnswer=" + getEdgeAnswer() +
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
                '}';
    }
}
