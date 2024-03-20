package com.mycompany.group234.function;

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
import com.sap.olingo.jpa.metadata.core.edm.annotation.EdmFunction;
import com.sap.olingo.jpa.metadata.core.edm.annotation.EdmParameter;
import com.sap.olingo.jpa.metadata.core.edm.mapper.extension.ODataFunction;
import com.mycompany.group234.repository.PackageManagementRepository;
import com.mycompany.group234.repository.Menu_NavRepository;
import com.mycompany.group234.repository.ArchitectureRepository;
import com.mycompany.group234.repository.ServerRepository;
import com.mycompany.group234.repository.UIFieldRepository;
import com.mycompany.group234.repository.FrontendAppMetaTagsRepository;
import com.mycompany.group234.repository.MenuItemRepository;
import com.mycompany.group234.repository.DocumentRepository;
import com.mycompany.group234.repository.UICompRepository;
import com.mycompany.group234.repository.UITemplateRepository;
import com.mycompany.group234.repository.FrontendScreenRepository;
import com.mycompany.group234.repository.BuildToolRepository;
import com.mycompany.group234.repository.AppConfigurationRepository;
import com.mycompany.group234.repository.FrontendAppSelectedScreenIdsRepository;
import com.mycompany.group234.repository.FrontendAppRepository;
import com.mycompany.group234.repository.HeaderBarRepository;
import com.mycompany.group234.repository.TemplateScreenRepository;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

@Component
public class JavaFunctions implements ODataFunction {


    
    
}
   
