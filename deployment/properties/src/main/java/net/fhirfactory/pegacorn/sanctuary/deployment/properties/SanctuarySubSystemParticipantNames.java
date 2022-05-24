/*
 * Copyright (c) 2022 Mark A. Hunter
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
package net.fhirfactory.pegacorn.sanctuary.deployment.properties;

import net.fhirfactory.pegacorn.deployment.names.subsystems.SubsystemNames;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class SanctuarySubSystemParticipantNames extends SubsystemNames {

    @Inject
    private SanctuarySolutionSystemNames solutionSystemNames;

    @Override
    protected String specifyDeploymentShortName() {
        return (solutionSystemNames.getSolutionName());
    }

    public String getMITaFPASParticipantName() {
        String mitafIIE = "MITaF.PAS";
        return(mitafIIE);
    }

    public String getMITaFPathologyParticipantName() {
        String mitafKestral = "MITaF.Pathology";
        return(mitafKestral);
    }

    public String getMITaFImagingParticipantName() {
        String mitafSunquest = "MITaF.Imaging";
        return(mitafSunquest);
    }

    public String getITOpsIMSystemName(){
        String itOpsIMName = "sactuary-itops-im";
        return(itOpsIMName);
    }
    
    public String getITOpsIMParticipantName(){
        String itOpsIMName = "ITOps.IM";
        return(itOpsIMName);
    }

    public String getPonosIMParticipantName(){
        String itOpsIMName = "Ponos.IM";
        return(itOpsIMName);
    }
 
}
