To build an update site:

1) Select the appropriate Target Platform
 - Windows / Preferences / Plug-in Development / Target Platform
 - (Note that it may take a long time for the target platform to finish loading)
 
2) Invoke Buckminster's "site.p2" action
 - Right click on "org.archstudio.devbootstrap.p2"
 - Buckminster / Invoke Action...
 - Select "site.p2" from the list
 - Select "/org.archstudio.devbootstrap.p2/res/eclipse/buckminster.properties" using the Workspace button
 - Select "OK"
 
3) Locate the generated update site
 - It will be at ${user.home}/buckminster/*.p2_*/site.p2