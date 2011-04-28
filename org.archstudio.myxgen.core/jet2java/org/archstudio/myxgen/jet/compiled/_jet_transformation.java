package org.archstudio.myxgen.jet.compiled;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.jet.JET2Template;
import org.eclipse.jet.JET2TemplateLoader;
import org.eclipse.jet.JET2TemplateLoaderExtension;

public class _jet_transformation implements JET2TemplateLoader,
        JET2TemplateLoaderExtension {

    private JET2TemplateLoader delegate = null;

    private final static Map pathToTemplateOrdinalMap = new HashMap(4);
    static {
        pathToTemplateOrdinalMap.put("templates/MyxBase.java.jet", //$NON-NLS-1$
                new Integer(0));
        pathToTemplateOrdinalMap.put("templates/MyxComp.java.jet", //$NON-NLS-1$
                new Integer(1));
        pathToTemplateOrdinalMap.put("templates/dump.jet", //$NON-NLS-1$
                new Integer(2));
        pathToTemplateOrdinalMap.put("templates/main.jet", //$NON-NLS-1$
                new Integer(3));
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.jet.JET2TemplateLoader#getTemplate(java.lang.String)
     */
    public JET2Template getTemplate(final String templatePath) {
        final Integer ordinal = (Integer)pathToTemplateOrdinalMap.get(templatePath);
        if(ordinal != null) {
           switch (ordinal.intValue()) {
            case 0: // templates/MyxBase.java.jet
                return new org.eclipse.jet.compiled._jet_MyxBasejava();
            case 1: // templates/MyxComp.java.jet
                return new org.eclipse.jet.compiled._jet_MyxCompjava();
            case 2: // templates/dump.jet
                return new org.eclipse.jet.compiled._jet_dump();
            case 3: // templates/main.jet
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
