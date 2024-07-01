package acsse.csc2a.fmb.gui;

import java.io.File;
import java.util.ArrayList;

import acsse.csc2a.fmb.file.OrchestratorFileHandler;
import acsse.csc2a.fmb.model.DisplayBundle;
import acsse.csc2a.fmb.model.FireworkDisplay;
import acsse.csc2a.fmb.model.FireworkEntity;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Accordion;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class FireworkDisplayPane extends StackPane {
	private VBox layout = null;
	private DisplayBundle displayBundle = null;
	private ArrayList<FireworkEntity> fireworkEntities = null;
	private FireworkDisplay fireworkDisplay = null;
	private MenuBar menuBar = null;
	private BorderPane borderPane = null;
	private FireworkDisplayCanvas canvas = null;

	public FireworkDisplayPane(Stage stage) {
		//Create borderpane and canvas instances
		this.borderPane = new BorderPane();
		this.canvas = new FireworkDisplayCanvas();

		// Create a Menu Bar
		this.menuBar = new MenuBar();

		// Create a Menu (in menuBar) for file-related options
		Menu fileMenu = new Menu("File");
		this.menuBar.getMenus().add(fileMenu);

		// Create a MenuItem (in Menu) for file options
		MenuItem fileMenuItem_Open = new MenuItem("Open");
		fileMenu.getItems().add(fileMenuItem_Open);

		// Add action listener to menu item
		fileMenuItem_Open.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				// Create a file chooser
				FileChooser fileChooser = new FileChooser();

				// Set extension filter
				FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("DAT files (*.dat)", "*.dat");
				fileChooser.getExtensionFilters().add(extFilter);

				// Add title
				fileChooser.setTitle("Choose a file");

				// Set the initial directory
				fileChooser.setInitialDirectory(new File("./data/data/layouts"));
				
				//get file from user
				File file = fileChooser.showOpenDialog(stage);

				// Check if returned file is valid
				if (file != null) {
					//print file selected
					System.out.printf("From File: %s \n \n", file.getPath());
					
					//create display bundle
					CreateDisplayBundle(file);
				}
			}
		});

		// VBox layout for Menu Bar (add MenuBar to VBox)
		this.layout = new VBox();
		this.layout.getChildren().addAll(this.menuBar);
		
		//add VBox to Stack
		this.getChildren().add(this.layout);
	}

	private void CreateDisplayBundle(File file) {
		// Create handler
		OrchestratorFileHandler handler = new OrchestratorFileHandler();
		
		//file path and path to displays
		String fileNameString = file.getPath();
		String rootDisplayFilePath = "data/data/displays";
		
		// Retrieve DisplayBundle
		setDisplayBundle(handler.readLayoutFile(fileNameString, rootDisplayFilePath));
	}

	private void createChildren() {
		//ensure display bundle is not null
		if(this.displayBundle != null) {
			
			//accordian class
			Accordion accordion = new Accordion();

			//Create Titled Pane for fireworks display and pyrotech
			TitledPane paneFD = createDisplayBundle();
			TitledPane pandPT = createPyroTech();
			
			//set firework entities from display bundle
			setFireworkEntities(this.displayBundle.getEntities());

			// Layout in VBox
			VBox vbox1 = new VBox();

			// Titled pane - fireworks
			TitledPane fireworksPane = new TitledPane();
			fireworksPane.setText("Fireworks");
			
			//gridpane an scroll pane for fireworks
			GridPane gridPane = null;
			ScrollPane scrollPane = new ScrollPane();
			
			//in each entity
			for (FireworkEntity fireworkEntity : this.fireworkEntities) {
				//gridpane for fireworks
				gridPane = createFireworks(fireworkEntity);
				
				//add fireworks to VBox
				vbox1.getChildren().add(gridPane);
			}
			
			//Add VBox to scroll
			scrollPane.setContent(vbox1);

			//Add scroll to fireworks Titled pane
			fireworksPane.setContent(scrollPane);
			
			//add all titled panes to accordian
			accordion.getPanes().add(paneFD);
			accordion.getPanes().add(pandPT);
			accordion.getPanes().add(fireworksPane);
			
			//set accordian to left of canvas (border)
			this.borderPane.setLeft(accordion);
			
			//remove all nodes from Stack
			this.getChildren().clear();
			
			//add border to stack
			this.getChildren().addAll(this.borderPane);
		}
	}

	private GridPane createFireworks(FireworkEntity fireworkEntity) {
		//grid pane instance
		GridPane grid = new GridPane();

		//Spacing
		grid.setVgap(4);
		
		//padding
		grid.setPadding(new Insets(5,5,5,5));

		// Add Labels to GridPane
		//Display Information
		grid.add(new Label("ID: "), 0, 0);
		grid.add(new Label("Name: "), 0, 1);
		grid.add(new Label("Length: "), 0, 2);
		grid.add(new Label("Colour: "), 0, 3);

		grid.add(new TextField(fireworkEntity.getFirework().getFireworkID()), 1, 0);
		grid.add(new TextField(fireworkEntity.getFirework().getFireworkName()), 1, 1);
		grid.add(new TextField(Double.toString(fireworkEntity.getFirework().getFuseLength())), 1, 2);
		grid.add(new TextField(fireworkEntity.getFirework().getColour().toString()), 1, 3);
		
		return grid;
	}

	private TitledPane createPyroTech() {
		//titled pane istance - to be returned
		TitledPane panePT = new TitledPane();
		panePT.setText("Lead PyroTechnician");

		// Use Grid Pane to hold information
		GridPane grid = new GridPane();

		// Spacing
		grid.setVgap(2);
		
		// Add padding
		grid.setPadding(new Insets(5,5,5,5));

		// Data
		grid.add(new Label("Full Name: "), 0, 0);
		grid.add(new Label("Phone Number: "), 0, 1); 

		grid.add(new TextField(this.displayBundle.getDisplay().getLeadTechnician().getFullName()), 1, 0);
		grid.add(new TextField(this.displayBundle.getDisplay().getLeadTechnician().getPhoneNumber()), 1, 1);

		//Add grid to Titled Pane
		panePT.setContent(grid);

		return panePT;
	}

	private TitledPane createDisplayBundle() {
		//Titled Pane
		TitledPane paneFD = new TitledPane();
		paneFD.setText("Firework Display");

		// Use Grid Pane to hold information
		GridPane grid = new GridPane();

		// Spacing
		grid.setVgap(2);
		
		// Add padding
		grid.setPadding(new Insets(5,5,5,5));

		//Data
		grid.add(new Label("ID: "), 0, 0); 
		grid.add(new Label("Name: "), 1, 0);
		grid.add(new Label("Theme: "), 2, 0);

		grid.add(new TextField(this.displayBundle.getDisplay().getDisplayID()), 0, 1);
		grid.add(new TextField(this.displayBundle.getDisplay().getDisplayName()), 1, 1);
		grid.add(new TextField(this.displayBundle.getDisplay().getDisplayTheme()), 2,1);
		
		//Add child node
		paneFD.setContent(grid);

		return paneFD;
	}


	/**
	 * @param displayBundle the displayBundle to set
	 */
	public void setDisplayBundle(DisplayBundle displayBundle) {
		this.displayBundle = displayBundle;
		//create Titled Panes and Accordian
		createChildren();
	}


	/**
	 * @return the layout
	 */
	public VBox getLayout() {
		return this.layout;
	}


	/**
	 * @param layout the layout to set
	 */
	public void setLayout(VBox layout) {
		this.layout = layout;
	}


	/**
	 * @return the displayBundle
	 */
	public DisplayBundle getDisplayBundle() {
		return this.displayBundle;
	}

	/**
	 * @return the fireworkEntities
	 */
	public ArrayList<FireworkEntity> getFireworkEntities() {
		return this.fireworkEntities;
	}

	/**
	 * @param fireworkEntities the fireworkEntities to set
	 */
	public void setFireworkEntities(ArrayList<FireworkEntity> fireworkEntities) {
		//move canvas to center of border
		this.borderPane.setCenter(this.canvas);
		this.fireworkEntities = fireworkEntities;
		
		//set entities in canvas class
		this.canvas.setFireworkEntities(fireworkEntities);
	}

	/**
	 * @return the fireworkDisplay
	 */
	public FireworkDisplay getFireworkDisplay() {
		return fireworkDisplay;
	}

	/**
	 * @param fireworkDisplay the fireworkDisplay to set
	 */
	public void setFireworkDisplay(FireworkDisplay fireworkDisplay) {
		this.fireworkDisplay = fireworkDisplay;
	}

	/**
	 * @return the menuBar
	 */
	public MenuBar getMenuBar() {
		return menuBar;
	}

	/**
	 * @return the borderPane
	 */
	public BorderPane getBorderPane() {
		return borderPane;
	}

	/**
	 * @return the canvas
	 */
	public FireworkDisplayCanvas getCanvas() {
		return canvas;
	}

}