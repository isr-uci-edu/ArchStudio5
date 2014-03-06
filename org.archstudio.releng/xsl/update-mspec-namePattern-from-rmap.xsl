<?xml version="1.0" encoding="UTF-8"?>
<xsl:transform version="1.0" 
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
	xmlns:rm="http://www.eclipse.org/buckminster/RMap-1.0"
	xmlns:md="http://www.eclipse.org/buckminster/MetaData-1.0">

	<xsl:output method="xml" indent="yes" encoding="UTF-8"/>
	
	<xsl:template match="*">
		<xsl:copy>	
			<xsl:copy-of select="@*"/>
			<xsl:apply-templates/>
		</xsl:copy>
	</xsl:template>
	
	<xsl:template match="//md:mspec/md:mspecNode[1]">
		<xsl:copy>
			<xsl:copy-of select="@*" />
			<xsl:attribute name="namePattern">
				<xsl:value-of select="document('component.rmap')//rm:rmap/rm:locator[1]/@pattern"/>
			</xsl:attribute>
			<xsl:apply-templates/>
		</xsl:copy> 
	</xsl:template>

</xsl:transform>
