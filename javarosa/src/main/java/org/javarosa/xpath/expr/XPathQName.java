/*
 * Copyright (C) 2009 JavaRosa
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */

package org.javarosa.xpath.expr;

import org.javarosa.core.util.externalizable.*;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class XPathQName implements Externalizable {
	public String namespace;
	public String name;

	public XPathQName () { } //for deserialization
	
	public XPathQName (String qname) {
		int sep = (qname == null ? -1 : qname.indexOf(":"));
		if (sep == -1) {
			init(null, qname);
		} else {
			init(qname.substring(0, sep), qname.substring(sep + 1));
		}
	}

	public XPathQName (String namespace, String name) {
		init(namespace, name);
	}

	private void init (String namespace, String name) {
		if (name == null ||
				(name != null && name.length() == 0) ||
				(namespace != null && namespace.length() == 0))
			throw new IllegalArgumentException("Invalid QName");

		this.namespace = namespace;
		this.name = name;
	}

	public String toString () {
		return (namespace == null ? name : namespace + ":" + name);
	}
	
	public boolean equals (Object o) {
		if (o instanceof XPathQName) {
			XPathQName x = (XPathQName)o;
			return ExtUtil.equals(namespace, x.namespace) && name.equals(x.name);
		} else {
			return false;
		}
	}
	
	public void readExternal(DataInputStream in, PrototypeFactory pf) throws IOException, DeserializationException {
		namespace = (String) ExtUtil.read(in, new ExtWrapNullable(String.class));
		name = ExtUtil.readString(in);
	}

	public void writeExternal(DataOutputStream out) throws IOException {
		ExtUtil.write(out, new ExtWrapNullable(namespace));
		ExtUtil.writeString(out, name);
	}
}
