package de.unisb.cs.st.javaslicer.variableUsages;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;

import de.unisb.cs.st.javaslicer.variables.Variable;

public class ComplexVariableUsage implements VariableUsages {

    private final Collection<Variable> allUsedVariables;
    private final Map<Variable, Collection<Variable>> definedVariablesAndDependancies;

    public ComplexVariableUsage(final Collection<Variable> allUsedVariables,
            final Map<Variable, Collection<Variable>> definedVariablesAndDependancies) {
        this.allUsedVariables = allUsedVariables;
        this.definedVariablesAndDependancies = definedVariablesAndDependancies;
        assert allSubsets(definedVariablesAndDependancies.values(), allUsedVariables);
    }

    private boolean allSubsets(final Collection<Collection<Variable>> sets, final Collection<Variable> superSet) {
        for (final Collection<Variable> set: sets)
            if (!superSet.containsAll(set))
                return false;
        return true;
    }

    public Collection<? extends Variable> getDefinedVariables() {
        return this.definedVariablesAndDependancies.keySet();
    }

    public Collection<? extends Variable> getUsedVariables() {
        return this.allUsedVariables;
    }

    public Collection<? extends Variable> getUsedVariables(final Variable definedVariable) {
        assert this.definedVariablesAndDependancies.containsKey(definedVariable);
        return this.definedVariablesAndDependancies.get(definedVariable);
    }

    public boolean isCatchBlock() {
        return false;
    }

    public Collection<Long> getCreatedObjects() {
        return Collections.emptySet();
    }

}
