package net.fhirfactory.pegacorn.sanctuary.deployment.properties.capabilities;


import net.fhirfactory.pegacorn.core.interfaces.capabilities.CapabilityProviderNameServiceInterface;
import net.fhirfactory.pegacorn.core.model.topology.role.ProcessingPlantRoleEnum;
import net.fhirfactory.pegacorn.sanctuary.deployment.properties.SanctuarySubSystemParticipantNames;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class CapabilityProviderNameService
        implements CapabilityProviderNameServiceInterface{

    @Inject
    private SanctuarySubSystemParticipantNames subSystemNames;

    @Override
    public String resolveCapabilityServiceProvider(ProcessingPlantRoleEnum capabilityProviderTitlesEnum) {
        switch(capabilityProviderTitlesEnum){
            case PETASOS_SERVICE_PROVIDER_ITOPS_MANAGEMENT:
                return(subSystemNames.getITOpsIMParticipantName());
            case PETASOS_SERVICE_PROVIDER_AUDIT_MANAGEMENT:
                return(subSystemNames.getHestiaAuditIMParticipant());
            case PETASOS_SERVICE_PROVIDER_AUDIT_STORAGE:
                return("aether-hestia-audit-dm");
            case PETASOS_SERVICE_PROVIDER_SOT_PATIENT_QUERY:
                return(subSystemNames.getMITaFPASParticipantName());
        }
        return(null);
    }
}
