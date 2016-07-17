<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

    <xsl:template match="/">
        <html>
            <body>
                <h2>Information about flights</h2>
                <table border-bottom="1">
                    <tr bgcolor="pink">
                        <th>Airline code / Flight number</th>
                        <th>Flight duration</th>
                        <th>Departure date</th>
                        <th>Arrival date</th>
                        <th>Code departure city</th>
                        <th>Code arrival city</th>
                        <th>Ordinary fare</th>
                        <th>Phone fare</th>
                    </tr>
                    <xsl:for-each select="/SourceDoc/HTML/BODY/TABLE/TR[2]/TD/TABLE/TR/TD/TABLE/TR/TD[3]/TABLE/TR/TD[2]">
                        <tr bgcolor="FFFFCC">
                            <td><xsl:value-of select="B[4]"/></td>
                            <td><xsl:value-of select="text()[last()]"/></td>
                            <td><xsl:value-of select="STRONG"/><xsl:value-of select="B[2]"/></td>
                            <td><xsl:value-of select="STRONG"/><xsl:value-of select="B[3]"/></td>
                            <td><xsl:value-of select="text()[1]"/></td>
                            <td><xsl:value-of select="text()[2]"/></td>
                            <td><xsl:value-of select="../TD[8]/BIG/B/STRONG"/></td>
                            <td><xsl:value-of select="../TD[8]/SPAN[1]"/></td>
                        </tr>
                    </xsl:for-each>
                </table>
            </body>
        </html>
    </xsl:template>


    <!-- <xsl:template match="@*|node()">
         <xsl:copy>
             <xsl:apply-templates select="@*|node()"/>
         </xsl:copy>
     </xsl:template>

     <xsl:template match="/">
         <xsl:apply-templates/>
     </xsl:template>
     <-->

</xsl:stylesheet>