<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="2.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" 
                xmlns:fo="http://www.w3.org/1999/XSL/Format" 
                xmlns:xs="http://www.w3.org/2001/XMLSchema" 
                xmlns:fn="http://www.w3.org/2005/xpath-functions">
  <xsl:output method="xml" indent="yes" encoding="UTF-8"/>
  <xsl:param name="pId" as="xs:string" />
  <xsl:param name="pFullname" as="xs:string" />
  <xsl:param name="pEmail" as="xs:string" />
  <xsl:param name="pCell" as="xs:string" />
  <xsl:param name="pPhone" as="xs:string" />
  <xsl:param name="pLocation" as="xs:string" />
  <xsl:param name="pAddress" as="xs:string" />
  <xsl:param name="pBirthdate" as="xs:string" />
  <xsl:param name="pRelocation" as="xs:string" />
  <xsl:param name="pSalary" as="xs:string" />
  <xsl:param name="pDirection" as="xs:string" />
  <xsl:param name="pEducation" as="xs:string" />
  <xsl:param name="pExperience" as="xs:string" />  
  <xsl:param name="pCitizenship" as="xs:string" /> 
  <xsl:param name="pWorkPermit" as="xs:string" />   
  <xsl:param name="pLanguage" as="xs:string" />   
  <xsl:param name="pSkills" as="xs:string" />   
  <xsl:param name="pTrip" as="xs:string" />
  <xsl:param name="pSchedule" as="xs:string" />
  <xsl:param name="pResumeName" as="xs:string" />
 
  <xsl:template match="/">
    <xsl:element name="resume">
      <xsl:element name="id">
        <xsl:value-of select="$pId"/>
      </xsl:element>
      <xsl:element name="fullname">
        <xsl:value-of select="$pFullname"/>
      </xsl:element> 
      <xsl:element name="mail">
        <xsl:value-of select="$pEmail"/>
      </xsl:element>
      <xsl:element name="cell">
        <xsl:value-of select="$pCell"/>
      </xsl:element>
      <xsl:element name="phone">
        <xsl:value-of select="$pPhone"/>
      </xsl:element> 
      <xsl:element name="location">
        <xsl:value-of select="$pLocation"/>
      </xsl:element>      
      <xsl:element name="address">
        <xsl:value-of select="$pAddress"/>
      </xsl:element>  
      <xsl:element name="birthdate">
        <xsl:value-of select="$pBirthdate"/>
      </xsl:element>          
      <xsl:element name="relocation">
        <xsl:value-of select="$pRelocation"/>
      </xsl:element>  
      <xsl:element name="salary">
        <xsl:value-of select="$pSalary"/>
      </xsl:element>   
      <xsl:element name="direction">
        <xsl:value-of select="$pDirection"/>
      </xsl:element> 
      <xsl:element name="area">
        <xsl:element name="item">
          <xsl:element name="name">
            <xsl:value-of select="'not realized'"/>
          </xsl:element>
        </xsl:element>
      </xsl:element>
      <xsl:element name="education">
        <xsl:for-each select="tokenize($pEducation, '##')">
          <xsl:element name="item">
            <xsl:element name="type">
              <xsl:value-of select="replace(., '.+organization#(.+)#specialty.+', '$1')" />  
            </xsl:element>          
            <xsl:element name="year">
              <xsl:value-of select="replace(., 'year#(.+)#organization.+', '$1')" />  
            </xsl:element>          
            <xsl:element name="about">
              <xsl:value-of select="replace(., '.+specialty#(.+)$', '$1')" />  
            </xsl:element> 
          </xsl:element>               
        </xsl:for-each>        
      </xsl:element> 
      <xsl:element name="experience">
        <xsl:for-each select="tokenize($pExperience, '##')">
          <xsl:element name="item">
            <xsl:element name="company">
              <xsl:value-of select="replace(., '.+organization#(.+)#industry.+', '$1')" />  
            </xsl:element>          
            <xsl:element name="fromdate">
              <xsl:value-of select="replace(., 'fromDate#(.+)#toDate#.+', '$1')" />  
            </xsl:element>                
            <xsl:element name="todate">
              <xsl:value-of select="replace(., '.+#toDate#(.+)#period.+', '$1')" />  
            </xsl:element>                          
            <xsl:element name="position">
              <xsl:value-of select="replace(., '.+#position#(.+)#assumption.+', '$1')" />  
            </xsl:element>  
            <xsl:element name="about">
              <xsl:value-of select="replace(., '.+#assumption(.+)', '$1')" />  
            </xsl:element>            
          </xsl:element>            
        </xsl:for-each>  
      </xsl:element> 
      <xsl:element name="citizenship">
        <xsl:value-of select="$pCitizenship"/>
      </xsl:element>      
      <xsl:element name="workPermit">
        <xsl:value-of select="$pWorkPermit"/>
      </xsl:element>            
      <xsl:element name="language">
        <xsl:for-each select="tokenize($pLanguage, '#')">
          <xsl:element name="item">          
            <xsl:element name="name">
              <xsl:value-of select="replace(., '(.+) — .+', '$1')" />  
            </xsl:element>        
            <xsl:element name="type">
              <xsl:value-of select="replace(., '.+ — (.+)', '$1')" />
            </xsl:element>      
          </xsl:element>          
        </xsl:for-each>
      </xsl:element>            
      <xsl:element name="resumeName">
        <xsl:value-of select="$pResumeName"/>
      </xsl:element>                         
      <xsl:element name="additional">
        <xsl:value-of select="'not realized'"/>
      </xsl:element>                                                             
      <xsl:element name="skills">
        <xsl:for-each select="tokenize($pSkills, '#')">
          <xsl:element name="item">
            <xsl:value-of select="." />  
            <xsl:if test="not(position() = last())">
              <xsl:text>&#xa;</xsl:text>
            </xsl:if>
          </xsl:element>
        </xsl:for-each>        
      </xsl:element> 
      <xsl:element name="trip">
        <xsl:value-of select="$pTrip"/>
      </xsl:element>  
      <xsl:element name="schedule">
        <xsl:value-of select="$pSchedule"/>
      </xsl:element>        
    </xsl:element>
  </xsl:template>
  <xsl:template match="//*"/>
</xsl:stylesheet>