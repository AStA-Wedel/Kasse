package org.fhw.asta.kasse.client.widget.userlist;


import org.fhw.asta.kasse.shared.model.Person;

import com.google.gwt.cell.client.Cell;
import com.google.gwt.cell.client.ClickableTextCell;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.view.client.HasData;

public class UserListWidgetImpl extends Composite implements UserListWidget{

	@UiField(provided=true) CellTable<Person> cellTable;
	@UiField Button printButton;
	
	private PrenameColumn prenameColumn;
	private SurnameColumn surnameColumn;
	private MatrNrColumn matrNrColumn;
	
	interface UserListWidgetImplUiBinder extends
	UiBinder<Widget, UserListWidgetImpl> {
	}
	
	private static UserListWidgetImplUiBinder uiBinder = GWT
			.create(UserListWidgetImplUiBinder.class);
	
	
	private void initializeCellTable()
	{
		cellTable = new CellTable<Person>();
		
		ClickableTextCell openOverlayCell = new ClickableTextCell();
		
		prenameColumn = new PrenameColumn(openOverlayCell);
		surnameColumn = new SurnameColumn(openOverlayCell);
		matrNrColumn = new MatrNrColumn(openOverlayCell);

		
		cellTable.addColumn(matrNrColumn,"MatrNr");
		cellTable.addColumn(prenameColumn,"Vorname");
		cellTable.addColumn(surnameColumn,"Name");
		
				
		cellTable.setColumnWidth(matrNrColumn,"20%");
		cellTable.setColumnWidth(prenameColumn,"40%");
		cellTable.setColumnWidth(surnameColumn,"40%");
		
		
		
		}
	
	public UserListWidgetImpl() {
		initializeCellTable();
		
		initWidget(uiBinder.createAndBindUi(this));

	}
	
	@Override
	public HasData<Person> getUserList() {
		return cellTable;
	}

	@Override
	public Column<Person, String> getMatrNrColumn() {
		return matrNrColumn;
	}

	@Override
	public Column<Person, String> getPrenameColumn() {
		return prenameColumn;
	}

	@Override
	public Column<Person, String> getSurnameColumn() {
		return surnameColumn;
	}
	
	static private class SurnameColumn extends Column<Person, String>{

		public SurnameColumn(Cell<String> cell) {
			super(cell);
		}

		@Override
		public String getValue(Person object) {
			return object.getSurname();
		}
		
	}

	static private class PrenameColumn extends Column<Person, String>{

		public PrenameColumn(Cell<String> cell) {
			super(cell);
		}

		@Override
		public String getValue(Person object) {
			return object.getPrename();
		}
		
	}
	
	static private class MatrNrColumn extends Column<Person, String>{

		public MatrNrColumn(Cell<String> cell) {
			super(cell);
		}

		@Override
		public String getValue(Person object) {
			return String.valueOf(object.getMatrNo());
		}
		
	}

	@Override
	public HasClickHandlers getPrintButton() {
		return printButton;
	}
}
