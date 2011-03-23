
Bug 286709

https://bugs.eclipse.org/bugs/show_bug.cgi?id=286709

causes a problem here.  In order to build an update site with
categories correctly, do this:

1.  Remove the feature from the category in site.xml
2.  Exit Eclipse
3.  Restart Eclipse
4.  Add the feature back to the category.
5.  Do a 'build all'

