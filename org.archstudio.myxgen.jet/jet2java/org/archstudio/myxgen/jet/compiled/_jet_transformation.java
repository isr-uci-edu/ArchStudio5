package org.archstudio.myxgen.jet.compiled;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.jet.JET2Template;
import org.eclipse.jet.JET2TemplateLoader;
import org.eclipse.jet.JET2TemplateLoaderExtension;

public class _jet_transformation implements JET2TemplateLoader, JET2TemplateLoaderExtension {

	private JET2TemplateLoader delegate = null;

	private final static Map<String, Integer> pathToTemplateOrdinalMap = new HashMap<String, Integer>(3);
	static {
		pathToTemplateOrdinalMap.put("templates/MyxComp.java.jet", //$NON-NLS-1$
				Integer.valueOf(0));
		pathToTemplateOrdinalMap.put("templates/MyxCompStub.java.jet", //$NON-NLS-1$
				Integer.valueOf(1));
		pathToTemplateOrdinalMap.put("templates/main.jet", //$NON-NLS-1$
				Integer.valueOf(2));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jet.JET2TemplateLoader#getTemplate(java.lang.String)
	 */
	public JET2Template getTemplate(final String templatePath) {
		final Integer ordinal = pathToTemplateOrdinalMap.get(templatePath);
		if (ordinal != null) {
			switch (ordinal.intValue()) {
			case 0: // templates/MyxComp.java.jet
				return new org.eclipse.jet.compiled._jet_MyxCompjava();
			case 1: // templates/MyxCompStub.java.jet
				return new org.eclipse.jet.compiled._jet_MyxCompStubjava();
			case 2: // templates/main.jet
				return new org.eclipse.jet.compiled._jet_main();
			default:
				break;
			}
		}
		return this.delegate != null ? this.delegate.getTemplate(templatePath) : null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jet.JET2TemplateLoaderExtension#getDelegateLoader()
	 */
	public JET2TemplateLoader getDelegateLoader() {
		return this.delegate;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.jet.JET2TemplateLoaderExtension#setDelegateLoader(org.eclipse
	 * .jet.JET2TemplateLoader)
	 */
	public void setDelegateLoader(final JET2TemplateLoader loader) {
		this.delegate = loader;
	}
}
