package com.mycompany.group234.model;


import lombok.Data;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;


 
import com.mycompany.group234.model.Document;
import com.mycompany.group234.model.HeaderBar;
import com.mycompany.group234.model.AppConfiguration;
import com.mycompany.group234.model.Architecture;
import com.mycompany.group234.model.UITemplate;
import com.mycompany.group234.model.Server;
import com.mycompany.group234.model.TemplateScreen;
import com.mycompany.group234.model.Menu_Nav;
import com.mycompany.group234.model.UIField;
import com.mycompany.group234.model.FrontendScreen;
import com.mycompany.group234.model.BuildTool;
import com.mycompany.group234.model.UIComp;
import com.mycompany.group234.model.FrontendApp;
import com.mycompany.group234.model.PackageManagement;
import com.mycompany.group234.model.MenuItem;
import com.mycompany.group234.model.jointable.FrontendAppMetaTags;
import com.mycompany.group234.model.jointable.FrontendAppSelectedScreenIds;
import com.mycompany.group234.model.complex.BasicDetails;
import com.mycompany.group234.model.complex.AdvancedDetails;
import com.mycompany.group234.enums.Position;
import com.mycompany.group234.converter.PositionConverter;
import com.mycompany.group234.converter.DurationConverter;
import com.mycompany.group234.converter.UUIDToByteConverter;
import com.mycompany.group234.converter.UUIDToStringConverter;

import com.sap.olingo.jpa.metadata.core.edm.annotation.EdmFunction;
import lombok.Data;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.time.Duration;
import java.util.Date;
import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.DiscriminatorValue;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Lob;
import com.sap.olingo.jpa.metadata.core.edm.annotation.EdmMediaStream;

@Entity(name = "UIComp")
@Table(name = "\"UIComp\"", schema =  "\"ExclusiveAccess\"")
@Data
                        
public class UIComp {
	public UIComp () {   
  }
	  
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "\"CompId\"", nullable = true )
  private Long compId;
	  
  @Column(name = "\"Name\"", nullable = true )
  private String name;
  
	  
  @Column(name = "\"Title\"", nullable = true )
  private String title;
  
	  
  @Column(name = "\"EntityTypeName\"", nullable = true )
  private String entityTypeName;
  
  
  
  
   
	
@OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.REMOVE, CascadeType.MERGE})
@JoinTable(
            name="\"UICompFields\"",
            joinColumns = @JoinColumn( name="\"CompId\""),
            inverseJoinColumns = @JoinColumn( name="\"FieldId\""), schema = "\"ExclusiveAccess\"")
private List<UIField> fields = new ArrayList<>();
  
  
  
  
  
  
  
  
  
  @Override
  public String toString() {
	return "UIComp [" 
  + "CompId= " + compId  + ", " 
  + "Name= " + name  + ", " 
  + "Title= " + title  + ", " 
  + "EntityTypeName= " + entityTypeName 
 + "]";
	}
	
}