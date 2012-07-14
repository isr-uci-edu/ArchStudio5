<?xml version="1.0" encoding="UTF-8"?>
<xsl:transform version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform" 
	xmlns:cq="http://www.eclipse.org/buckminster/CQuery-1.0">

	<xsl:output method="xml" indent="yes" encoding="UTF-8"/>

	<xsl:param name="value"/>

	<xsl:template match="*">
		<xsl:copy>
			<xsl:copy-of select="@*"/>
			<xsl:apply-templates/>
		</xsl:copy>
	</xsl:template>
	
	<xsl:template match="//cq:componentQuery/cq:advisorNode[1]">
		<xsl:copy>
			<xsl:copy-of select="@*" />
			<xsl:attribute name="branchTagPath">
    				<xsl:value-of select="$value" />
  			</xsl:attribute>
			<xsl:apply-templates/>
		</xsl:copy> 
	</xsl:template>

</xsl:transform>
