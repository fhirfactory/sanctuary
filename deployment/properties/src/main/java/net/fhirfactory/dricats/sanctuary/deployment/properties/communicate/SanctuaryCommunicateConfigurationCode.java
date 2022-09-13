package net.fhirfactory.dricats.sanctuary.deployment.properties.communicate;

import net.fhirfactory.pegacorn.deployment.communicate.matrix.CommunicateSubsystemIntegrationConfigurationCode;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class SanctuaryCommunicateConfigurationCode extends CommunicateSubsystemIntegrationConfigurationCode {

    @Override
    public String getEmailAddressDomainName() {
        return "act.gov.au";
    }

    @Override
    public String getSynapseServerName() {
        return "lingo.aether.health.gov.au";
    }

    @Override
    public String getSynapseServerAdminUserName()
    {
        return("CommunicateSynapseAdmin");
        // TODO read from environment variable
    }

    @Override
    public String getSynapseServerAdminPassword(){
        return("ClaudeAndClara01");
        // TODO read from environment variable
    }

    @Override
    public String getWhispersHomeServerAccessToken() {
        return ("FromWhispersToHomeServer");
    }

    @Override
    public String getWhispersAccessToken() {
        return ("FromHomeServerToWhispers");
    }

    @Override
    public String getIrisHomeServerAccessToken() {
        return null;
    }

    @Override
    public String getIrisAccessToken() {
        return null;
    }
}
