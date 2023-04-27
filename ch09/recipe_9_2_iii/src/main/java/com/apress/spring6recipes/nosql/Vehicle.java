package com.apress.spring6recipes.nosql;

import java.io.Serializable;

public record Vehicle (String vehicleNo, String color, int wheel, int seat)
				implements Serializable {

}
