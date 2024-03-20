package com.mycompany.group234.model.jointable;

import com.sap.olingo.jpa.metadata.core.edm.annotation.EdmIgnore;
import lombok.Data;
import javax.persistence.*;

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

@Entity(name = "UITemplateAllScreens")
@Table(schema = "\"ExclusiveAccess\"", name = "\"UITemplateAllScreens\"")
@Data
public class UITemplateAllScreens{

 	@Id
    @Column(name = "\"Id\"")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
	
	@Column(name = "\"TemplateId\"")
	private Integer templateId;

    
    @Column(name = "\"TeScreenId\"")
    private Integer teScreenId;
 
}