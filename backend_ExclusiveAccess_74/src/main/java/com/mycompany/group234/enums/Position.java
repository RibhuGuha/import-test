package com.mycompany.group234.enums;

import com.sap.olingo.jpa.metadata.core.edm.annotation.EdmEnumeration;

@EdmEnumeration	  
public enum Position{
	    Vertical,
	    Horizontal; 
    public int value(Position position) {
        return position.ordinal();
    }
    public static Position getPosition(int ordinal) {
        for(Position position : Position.values())
                if(position.ordinal() == ordinal)
                        return position;
        return null;
    }
}


