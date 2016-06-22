package nl.Under_Koen.RubiksCube.Scenes;

import java.io.File;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.stage.FileChooser;
import nl.Under_Koen.RubiksCube.HomeMenu;
import nl.Under_Koen.RubiksCube.Main;
import nl.Under_Koen.RubiksCube.Cube.Cube;
import nl.Under_Koen.RubiksCube.Cube.Row.Row.HRow;
import nl.Under_Koen.RubiksCube.Cube.Row.Row.VRow;
import nl.Under_Koen.RubiksCube.Cube.View.Direction;
import nl.Under_Koen.RubiksCube.Main.OptionsTypes;

public class Game {
	
	private static File oldFile = null;
	private static File saveFolder = new File("/Users/gebruiker/Desktop/Rubik's Cube/Saves/");
	private static File currenctFile = new File("/Users/gebruiker/", ".Game.Save");
	
	public static Scene gameScene() {
		StackPane root = new StackPane();
        Scene main = new Scene(root);
        root.setAlignment(Pos.TOP_LEFT);
        HomeMenu menu = new HomeMenu();
        Cube cube = new Cube();
        cube.loadSave(currenctFile);
        root.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent ke) {
            	if (ke.isShortcutDown()) {
            		if (ke.getCode().equals(KeyCode.S)) {
            			if (ke.isShiftDown()) {
            				saveAs(cube);
            			} else {
            				save(cube);
                    	}
            		}
            		if (ke.getCode().equals(KeyCode.O)) {
            			load(cube);
                    	show(root, 350, 100, cube);
            		}
            		if (ke.getCode().equals(KeyCode.Q)) {
            			currenctFile.delete();
                    	Main.stage.setFullScreen(false);
                    	Main.stage.close();
            		}
            	}
                if (ke.getCode().equals(KeyCode.LEFT)) {
                	if (Main.getOption(OptionsTypes.INVERTEDKEYS).contains("false")) {
                        cube.move(Direction.LEFT);
            		} else {
            			cube.move(Direction.RIGHT);
            		}
                    show(root, 350, 100, cube);	
                }
                if (ke.getCode().equals(KeyCode.RIGHT)) {
                	if (Main.getOption(OptionsTypes.INVERTEDKEYS).contains("false")) {
                        cube.move(Direction.RIGHT);
            		} else {
            			cube.move(Direction.LEFT);
            		}
                	show(root, 350, 100, cube);	
                }
                if (ke.getCode().equals(KeyCode.UP)) {
                	if (Main.getOption(OptionsTypes.INVERTEDKEYS).contains("false")) {
                        cube.move(Direction.UP);
            		} else {
            			cube.move(Direction.DOWN);
            		}
                	show(root, 350, 100, cube);	
                }
                if (ke.getCode().equals(KeyCode.DOWN)) {
                	if (Main.getOption(OptionsTypes.INVERTEDKEYS).contains("false")) {
                        cube.move(Direction.DOWN);
            		} else {
            			cube.move(Direction.UP);
            		}
                	show(root, 350, 100, cube);
                }
            }
        });
        menu.addButton("Load", new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            	load(cube);
            	show(root, 350, 100, cube);
            }
        });
        menu.addButton("Save", new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            	save(cube);
            }
        });
        menu.addButton("Save As", new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            	saveAs(cube);
            }
        });
        menu.addButton("Options", new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            	cube.saveSave(currenctFile);
            	Options.setBack(1);
            	Main.stage.setScene(Options.optionsScene());
            }
        });
        menu.addButton("Reset", new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            	cube.reset();
            	show(root, 350, 100, cube);
            	cube.saveSave(currenctFile);
            }
        });
        menu.addButton("Quit", new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            	currenctFile.delete();
            	Main.stage.setFullScreen(false);
            	Main.stage.close();
            }
        });
        menu.show(root);
        show(root, 350, 100, cube);	
		return main;
	}
	
	private static void save(Cube cube) {
		if (oldFile == null) {
			saveAs(cube);
    	} else {
    		cube.saveSave(oldFile);
    	}
	}
	
	private static void saveAs(Cube cube) {
		FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save Game");
        fileChooser.setInitialDirectory(saveFolder);
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Save", "*.Save"));
        File file = fileChooser.showSaveDialog(Main.stage);
        if (file != null) {
            try {
                cube.saveSave(file);
                oldFile = file;
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        }
	}

	private static void load(Cube cube) {
		FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Load Game");
        fileChooser.setInitialDirectory(saveFolder);
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Save", "*.Save"));
        File file = fileChooser.showOpenDialog(Main.stage);
        if (file != null) {
            try {
            	cube.loadSave(file);
                oldFile = file;
            } catch (Exception ex) {
            	ex.printStackTrace();
            }
        }
	}
	
	private static Group images;
	
	private static void show(StackPane root, int x, int y, Cube cube) {
		if (Main.getOption(OptionsTypes.RENDER).contains("false")) {
			cube.showWide(root, x, y);
		} else {
			cube.showSmall(root, x, y);
		}
		root.getChildren().remove(images);
		images = new Group();
        for (int i = 0; i != 3; i++) {
		    Image image = new Image (Main.class.getResource("/Arrows/Arrow_Right.png").toString());
			ImageView imageView = new ImageView(image);
			imageView.setFitHeight(50);
			imageView.setFitWidth(50);
			imageView.setTranslateX(270);
			imageView.setTranslateY(80 + 70 * i);
			imageView.setCursor(Cursor.HAND);
			if (i == 0) {
				imageView.setOnMousePressed(new EventHandler<MouseEvent>(){
					@Override
					public void handle(MouseEvent event) {
						if (event.getButton() == MouseButton.PRIMARY) {
							try {
								cube.moveRow(HRow.TOP, Direction.RIGHT);
							} catch (Exception e) {
								e.printStackTrace();
							}
			            	show(root, 350, 100, cube);
						}
					}
				});
			}
			if (i == 1) {
				imageView.setOnMousePressed(new EventHandler<MouseEvent>(){
					@Override
					public void handle(MouseEvent event) {
						if (event.getButton() == MouseButton.PRIMARY) {
							try {
								cube.moveRow(HRow.MIDDLE, Direction.RIGHT);
							} catch (Exception e) {
								e.printStackTrace();
							}
			            	show(root, 350, 100, cube);
						}
					}
				});
			}
			if (i == 2) {
				imageView.setOnMousePressed(new EventHandler<MouseEvent>(){
					@Override
					public void handle(MouseEvent event) {
						if (event.getButton() == MouseButton.PRIMARY) {
							try {
								cube.moveRow(HRow.BOTTOM, Direction.RIGHT);
							} catch (Exception e) {
								e.printStackTrace();
							}
			            	show(root, 350, 100, cube);
						}
					}
				});
			}
			images.getChildren().add(imageView);
        }
        for (int i = 0; i != 3; i++) {
		    Image image = new Image (Main.class.getResource("/Arrows/Arrow_Down.png").toString());
			ImageView imageView = new ImageView(image);
			imageView.setFitHeight(50);
			imageView.setFitWidth(50);
			imageView.setTranslateX(65 + 70 * i);
			imageView.setTranslateY(285);
			imageView.setCursor(Cursor.HAND);
			if (i == 1) {
				imageView.setOnMousePressed(new EventHandler<MouseEvent>(){
					@Override
					public void handle(MouseEvent event) {
						try {
							cube.moveRow(VRow.MIDDLE, Direction.DOWN);
						} catch (Exception e) {
							e.printStackTrace();
						}
		            	show(root, 350, 100, cube);
					}
				});
			}
			images.getChildren().add(imageView);
        }
        for (int i = 0; i != 3; i++) {
		    Image image = new Image (Main.class.getResource("/Arrows/Arrow_Up.png").toString());
			ImageView imageView = new ImageView(image);
			imageView.setFitHeight(50);
			imageView.setFitWidth(50);
			imageView.setTranslateX(65 + 70 * i);
			imageView.setTranslateY(15);
			imageView.setCursor(Cursor.HAND);
			if (i == 1) {
				imageView.setOnMousePressed(new EventHandler<MouseEvent>(){
					@Override
					public void handle(MouseEvent event) {
						try {
							cube.moveRow(VRow.MIDDLE, Direction.UP);
						} catch (Exception e) {
							e.printStackTrace();
						}
		            	show(root, 350, 100, cube);
					}
				});
			}
			images.getChildren().add(imageView);
        }
        for (int i = 0; i != 3; i++) {
		    Image image = new Image (Main.class.getResource("/Arrows/Arrow_Left.png").toString());
			ImageView imageView = new ImageView(image);
			imageView.setFitHeight(50);
			imageView.setFitWidth(50);
			imageView.setTranslateY(80 + 70 * i);
			imageView.setCursor(Cursor.HAND);
			if (i == 0) {
				imageView.setOnMousePressed(new EventHandler<MouseEvent>(){
					@Override
					public void handle(MouseEvent event) {
						if (event.getButton() == MouseButton.PRIMARY) {
							try {
								cube.moveRow(HRow.TOP, Direction.LEFT);
							} catch (Exception e) {
								e.printStackTrace();
							}
			            	show(root, 350, 100, cube);
						}
					}
				});
			}
			if (i == 1) {
				imageView.setOnMousePressed(new EventHandler<MouseEvent>(){
					@Override
					public void handle(MouseEvent event) {
						if (event.getButton() == MouseButton.PRIMARY) {
							try {
								cube.moveRow(HRow.MIDDLE, Direction.LEFT);
							} catch (Exception e) {
								e.printStackTrace();
							}
			            	show(root, 350, 100, cube);
						}
					}
				});
			}
			if (i == 2) {
				imageView.setOnMousePressed(new EventHandler<MouseEvent>(){
					@Override
					public void handle(MouseEvent event) {
						if (event.getButton() == MouseButton.PRIMARY) {
							try {
								cube.moveRow(HRow.BOTTOM, Direction.LEFT);
							} catch (Exception e) {
								e.printStackTrace();
							}
			            	show(root, 350, 100, cube);
						}
					}
				});
			}
			images.getChildren().add(imageView);
        }
        images.setTranslateX(x+50);
        images.setTranslateY(y+50);
        root.getChildren().add(images);
	}
}
