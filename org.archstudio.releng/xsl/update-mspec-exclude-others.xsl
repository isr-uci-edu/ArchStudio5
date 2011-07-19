<?xml version="1.0" encoding="UTF-8"?>
<xsl:transform version="1.0" 
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
	xmlns:mspec="http://www.eclipse.org/buckminster/MetaData-1.0">

	<xsl:output method="xml" indent="yes" encoding="UTF-8"/>
	
	<xsl:param name="value"/>

	<xsl:template match="*">
		<xsl:copy>
			<xsl:copy-of select="@*"/>
			<xsl:apply-templates/>
		</xsl:copy>
	</xsl:template>
	
	<xsl:template match="//mspec:mspec/mspec:mspecNode[1]">
		<xsl:copy>
			<xsl:copy-of select="@*"/>
			<xsl:apply-templates/>
		</xsl:copy>
		<xsl:element name="mspec:mspecNode">
			<xsl:attribute name="namePattern">.*</xsl:attribute>
			<xsl:attribute name="exclude">true</xsl:attribute>
		</xsl:element >
	</xsl:template>

</xsl:transform>
