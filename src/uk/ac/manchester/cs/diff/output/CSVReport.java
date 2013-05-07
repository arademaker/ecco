/*******************************************************************************
 * This file is part of ecco.
 * 
 * ecco is distributed under the terms of the GNU Lesser General Public License (LGPL), Version 3.0.
 *  
 * Copyright 2011-2013, The University of Manchester
 *  
 * ecco is free software: you can redistribute it and/or modify it under the terms of the GNU Lesser
 * General Public License as published by the Free Software Foundation, either version 3 of the 
 * License, or (at your option) any later version.
 *  
 * ecco is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even 
 * the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser 
 * General Public License for more details.
 *  
 * You should have received a copy of the GNU Lesser General Public License along with ecco.
 * If not, see http://www.gnu.org/licenses/.
 ******************************************************************************/
package uk.ac.manchester.cs.diff.output;

import uk.ac.manchester.cs.diff.axiom.changeset.CategorisedChangeSet;
import uk.ac.manchester.cs.diff.axiom.changeset.ChangeSet;
import uk.ac.manchester.cs.diff.axiom.changeset.LogicalChangeSet;
import uk.ac.manchester.cs.diff.axiom.changeset.StructuralChangeSet;

/**
 * @author Rafael S. Goncalves <br/>
 * Information Management Group (IMG) <br/>
 * School of Computer Science <br/>
 * University of Manchester <br/>
 */
public class CSVReport {
	private String header, row;
	
	/**
	 * Constructor
	 */
	public CSVReport() {
		header = ""; row = "";
	}
	
	
	/**
	 * Get a CSV-formatted change report
	 * @param changeSet	Change set
	 * @return Change report as a CSV-formatted string
	 */
	public String getReport(ChangeSet changeSet) {
		String report = null;	
		if(changeSet instanceof StructuralChangeSet)
			report = getStructuralChangeSetReport((StructuralChangeSet)changeSet);
		else if(changeSet instanceof LogicalChangeSet)
			report = getLogicalChangeSetReport((LogicalChangeSet)changeSet);
		else if(changeSet instanceof CategorisedChangeSet)
			report = getCategorisedChangeSetReport((CategorisedChangeSet)changeSet);
		return report;
	}
	
	
	/**
	 * Get a CSV report of a structural change set
	 * @param changeSet	Structural change set
	 * @return CSV structural change set report
	 */
	private String getStructuralChangeSetReport(StructuralChangeSet stChangeSet) {
		header += "Ontology 1,Ontology 2,Structurally Equivalent,Structural Additions,Structural Removals,Shared Axioms,Structural Diff Time";
		row += stChangeSet.getOntology1FileName() + ",";
		row += stChangeSet.getOntology2FileName() + ",";
		row += (stChangeSet.isEmpty() ? "true" : "false") + ",";
		row += stChangeSet.getAddedAxioms().size() + ",";
		row += stChangeSet.getRemovedAxioms().size() + ",";
		row += stChangeSet.getShared().size() + ",";
		row += stChangeSet.getDiffTime();	
		return header + "\n" + row;
	}
	
	
	/**
	 * Get a CSV report of a logical change set
	 * @param changeSet	Logical change set
	 * @return CSV logical change set report
	 */
	private String getLogicalChangeSetReport(LogicalChangeSet logChangeSet) {
		header += ",Effectual Additions,Ineffectual Additions,Effectual Removals,Ineffectual Removals,Logical Diff Time";
		row += "," + logChangeSet.getEffectualAdditionAxioms().size();
		row += "," + logChangeSet.getIneffectualAdditionAxioms().size();
		row += "," + logChangeSet.getEffectualRemovalAxioms().size();
		row += "," + logChangeSet.getIneffectualRemovalAxioms().size();
		row += "," + logChangeSet.getDiffTime();
		return header + "\n" + row;
	}
	
	
	/**
	 * Get a CSV report of a categorised change set
	 * @param changeSet	Categorised change set
	 * @return CSV categorised change set report
	 */
	private String getCategorisedChangeSetReport(CategorisedChangeSet catChangeSet) {
		header += ",Strengthening,StrengtheningNT,ExtendedDefinition,ExtendedDefinitionNT,PureAddition,PureAdditionNT,NewDescription,EAC Time,"
				+ "Added Rewrite,Added Standing Redundancy,Added Prospective Redundancy,Added Reshuffle,New,Novel,Pseudo Novel,IAC Time,"
				+ "Weakening,WeakeningRT,Reduced Definition,Reduced Definition RT,Pure Removal,Pure Removal RT,Retired Description,ERC Time," +
				"Removed Rewrite,Removed Standing Redundancy,Removed Prospective Redundancy,Removed Reshuffle,New,Novel,Pseudo Novel,IRC Time";
		// Effectual additions
		row += "," + catChangeSet.getStrengthenings().size();
		row += "," + catChangeSet.getStrengtheningsWithNewTerms().size();
		row += "," + catChangeSet.getAddedModifiedDefinitions().size();
		row += "," + catChangeSet.getAddedModifiedDefinitionsWithNewTerms().size();
		row += "," + catChangeSet.getPureAdditions().size();
		row += "," + catChangeSet.getPureAdditionsWithNewTerms().size();
		row += "," + catChangeSet.getNewDescriptions().size();
		row += "," + catChangeSet.getEffectualAdditionCategorisationTime();
		// Ineffectual additions
		row += "," + catChangeSet.getAddedRewrites().size();
		row += "," + catChangeSet.getAddedRedundancies().size();
		row += "," + catChangeSet.getAddedProspectiveRedundancies().size();
		row += "," + catChangeSet.getAddedReshuffleRedundancies().size();
		row += "," + catChangeSet.getAddedProspectiveNewRedundancies().size();
		row += "," + catChangeSet.getAddedNovelRedundancies().size();
		row += "," + catChangeSet.getAddedPseudoNovelRedundancies().size();
		row += "," + catChangeSet.getIneffectualAdditionCategorisationTime();
		// Effectual removals
		row += "," + catChangeSet.getWeakenings().size();
		row += "," + catChangeSet.getWeakeningsWithRetiredTerms().size();
		row += "," + catChangeSet.getRemovedModifiedDefinitions().size();
		row += "," + catChangeSet.getRemovedModifiedDefinitionsWithRetiredTerms().size();
		row += "," + catChangeSet.getPureRemovals().size();
		row += "," + catChangeSet.getPureRemovalsWithRetiredTerms().size();
		row += "," + catChangeSet.getRetiredDescriptions().size();
		row += "," + catChangeSet.getEffectualRemovalCategorisationTime();
		// Ineffectual removals
		row += "," + catChangeSet.getRemovedRewrites().size();
		row += "," + catChangeSet.getRemovedRedundancies().size();
		row += "," + catChangeSet.getRemovedProspectiveRedundancies().size();
		row += "," + catChangeSet.getRemovedReshuffleRedundancies().size();
		row += "," + catChangeSet.getRemovedProspectiveNewRedundancies().size();
		row += "," + catChangeSet.getRemovedNovelRedundancies().size();
		row += "," + catChangeSet.getRemovedPseudoNovelRedundancies().size();
		row += "," + catChangeSet.getIneffectualRemovalCategorisationTime();
		return header + "\n" + row;
	}
}
