package org.openmrs.module.html5forms.api.impl;

import javax.xml.parsers.ParserConfigurationException;

public class CompositeEnketoResult extends EnketoResult {

    private final String modelJson;

    public CompositeEnketoResult(String transform, String modelJson) throws ParserConfigurationException {
        super(transform);
        this.modelJson = modelJson;
    }

    public String getModelAsJson() {
        return this.modelJson;
    }
}
