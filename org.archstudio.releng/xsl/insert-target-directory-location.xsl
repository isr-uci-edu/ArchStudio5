<?xml version="1.0" encoding="UTF-8"?>
<xsl:transform version="1.0" 
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

	<xsl:output method="xml" indent="yes" encoding="UTF-8"/>
	
	<xsl:param name="value"/>

	<xsl:template match="*">
		<xsl:copy>
			<xsl:copy-of select="@*"/>
			<xsl:apply-templates/>
		</xsl:copy>
	</xsl:template>
	
	<xsl:template match="//locations">
		<xsl:copy>
			<xsl:copy-of select="@*" />
			<xsl:apply-templates/>
			<xsl:element name="location">
			    <xsl:attribute name="path"><xsl:value-of select="$value"/></xsl:attribute>
			    <xsl:attribute name="type">Directory</xsl:attribute>
			</xsl:element>
		</xsl:copy> 
	</xsl:template>

</xsl:transform>
