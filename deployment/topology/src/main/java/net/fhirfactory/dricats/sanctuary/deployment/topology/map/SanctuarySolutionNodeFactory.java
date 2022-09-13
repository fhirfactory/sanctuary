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
package net.fhirfactory.dricats.sanctuary.deployment.topology.map;

import net.fhirfactory.pegacorn.deployment.topology.factories.archetypes.SolutionNodeTopologyFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.ApplicationScoped;

/**
 *
 * @author Mark A. Hunter
 */
@ApplicationScoped
public class SanctuarySolutionNodeFactory extends SolutionNodeTopologyFactory {
	private static final Logger LOG = LoggerFactory.getLogger(SanctuarySolutionNodeFactory.class);

	private static String SOLUTION_VERSION = "1.0.0";
	private static String SOLUTION_NAME = "Sanctuary";


	@Override
	protected Logger specifyLogger(){return(LOG);}
	
	public SanctuarySolutionNodeFactory() {
		super();
	}

	@Override
	protected String specifySystemName(){
		return(SOLUTION_NAME);
	}

	@Override
	protected String specifySystemVersion(){
		return(SOLUTION_VERSION);
	}


}
