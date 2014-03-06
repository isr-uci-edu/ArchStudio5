<?xml version="1.0" encoding="UTF-8"?>
<xsl:transform version="1.0" 
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform" 
	xmlns:md="http://www.eclipse.org/buckminster/MetaData-1.0">

	<xsl:output method="text" encoding="UTF-8"/>

	<xsl:strip-space elements="*"/>

	<xsl:template match="//md:mspec">
		<xsl:value-of select="@url"/>
	</xsl:template>

</xsl:transform>
