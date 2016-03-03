package Controllers;

import java.awt.Container;

import project.CustomTable;
import Views.ReportsView;


public class ReportsController {
	public ReportsView view;
	private Container previousView;
	
	public ReportsController(ReportsView view, Container previousView) {
		this.view = view;
		this.previousView = previousView;
		//view.table = new CustomTable(view);
	}
	
	

}
//
//

