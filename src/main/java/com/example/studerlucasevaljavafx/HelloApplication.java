package com.example.studerlucasevaljavafx;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

/**
 * JavaFX App
 */
public class tp extends Application
{

    Button buttonHome, buttonShow, buttonDwl, buttonAdd, buttonJeu;
    String pathDownload = System.getProperty("user.dir");
    Stage windowMeteo;
    Scene sceneBoutons, sceneJson, scenePropDetail, scenePropList;

    public static void main(String[] args)
    {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        primaryStage.setTitle("Affichage du menu boutons");

        buttonShow = new Button();
        buttonShow.setText("Consulter les données");
        buttonShow.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                // --------------Fenetre - affichage JSON--------------------------
                primaryStage.setScene(displayFileJsonScene());

                //getHostServices().showDocument("https://download.data.grandlyon.com/ws/grandlyon/pvo_patrimoine_voirie.pvostationvelov/all.json?maxfeatures=100&start=1");
            }
        });


        buttonDwl = new Button();
        buttonDwl.setText("Télécharger le fichier");
        buttonDwl.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                String urlDownloadJson = "https://api.weatherbit.io/v2.0/current?lat=35.7796&lon=-78.6382&key=876c70d7741f4bb384a906aa02a19053&include=minutely";
                InputStream in;
                try {
                    in = new URL(urlDownloadJson).openStream();
                    Files.copy(in, Paths.get(pathDownload + "\\meteo-.json"), StandardCopyOption.REPLACE_EXISTING);
                    System.out.println("bravo tu as téléchargé l'API");
                } catch (IOException eIO){
                    in = InputStream.nullInputStream();
                }
                //getHostServices().showDocument("https://download.data.grandlyon.com/wms/grandlyon?VERSION=1.3.0&SERVICE=WMS&REQUEST=GetMap&transparent=true&WIDTH=700&HEIGHT=821&layers=pvo_patrimoine_voirie.pvostationvelov&FORMAT=image/png&CRS=EPSG:4171&BBOX=45.437,4.568,46.03,5.18");
            }
        });

        buttonAdd = new Button();
        buttonAdd.setText("Mettre la météo à jour");
        buttonAdd.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                // --------------Fenetre - détail--------------------------
                //System.out.println("vous avez appuyé sur add");
                primaryStage.setScene(createDetailScene());
            }
        });


        buttonJeu = new Button();
        buttonJeu.setText("Bonus");
        buttonJeu.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                // --------------Fenetre - Jeu--------------------------
                //primaryStage.setScene(createJeuScene());
            }
        });






        Pane layout = new Pane();
        buttonShow.setLayoutX(250);
        buttonShow.setLayoutY(75);
        layout.getChildren().add(buttonShow);
        buttonDwl.setLayoutX(250);
        buttonDwl.setLayoutY(150);
        layout.getChildren().add(buttonDwl);
        buttonAdd.setLayoutX(250);
        buttonAdd.setLayoutY(225);
        layout.getChildren().add(buttonAdd);
        buttonJeu.setLayoutX(250);
        buttonJeu.setLayoutY(300);
        layout.getChildren().add(buttonJeu);
        sceneBoutons = new Scene(layout, 800, 400);
        primaryStage.setScene(sceneBoutons);
        primaryStage.show();


        buttonHome = new Button();
        buttonHome.setText("Return Home");
        buttonHome.setOnAction(e -> primaryStage.setScene(sceneBoutons));


    }

    protected Scene createDetailScene() {
        Label nomT = new Label("Nom");
        Label prenomT = new Label("Prenom");
        Label villeT = new Label("Ville");
        Label temperatureT = new Label("Temperature");
        Label meteoT = new Label("Meteo");
        TextField nom = new TextField();
        TextField prenom = new TextField();
        TextField ville = new TextField();
        TextField temperature = new TextField();
        TextField meteo = new TextField();
        Pane layoutDetail = new Pane();

        layoutDetail.setPadding(new Insets(20,20,20,20));
        buttonHome.setLayoutX(650);
        buttonHome.setLayoutY(30);


        Button createAccountButton = new Button("Save Proposition");
        createAccountButton.setOnAction(actionEvent -> {
            //System.out.println("Account for user " + nameInput.getText() + " was created succesfully");
            if (nom.getText().isEmpty() || prenom.getText().isEmpty() || ville.getText().isEmpty() || temperature.getText().isEmpty() ||
                    meteo.getText().isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur");
                alert.setHeaderText("Champ vide");
                alert.showAndWait();
            } else {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Météo mis a jour");
                alert.show();
                //MonObjet monObjet = new MonObjet(nom, prenom, ville, temperature, meteo);
                //Gson gson = new Gson();
                //String json = gson.toJson(monObjet);
                //try (FileWriter writer = new FileWriter("monObjet.json")) {
                //    writer.write(json);
                //} catch (IOException e) {
                //    e.printStackTrace();
            }
        });
        nomT.setLayoutX(100);
        nomT.setLayoutY(30);
        layoutDetail.getChildren().add(nomT);
        nom.setLayoutX(100);
        nom.setLayoutY(50);
        layoutDetail.getChildren().add(nom);
        prenomT.setLayoutX(100);
        prenomT.setLayoutY(80);
        layoutDetail.getChildren().add(prenomT);
        prenom.setLayoutX(100);
        prenom.setLayoutY(100);
        layoutDetail.getChildren().add(prenom);
        villeT.setLayoutX(100);
        villeT.setLayoutY(130);
        layoutDetail.getChildren().add(villeT);
        ville.setLayoutX(100);
        ville.setLayoutY(150);
        layoutDetail.getChildren().add(ville);
        temperatureT.setLayoutX(100);
        temperatureT.setLayoutY(180);
        layoutDetail.getChildren().add(temperatureT);
        temperature.setLayoutX(100);
        temperature.setLayoutY(200);
        layoutDetail.getChildren().add(temperature);
        meteoT.setLayoutX(100);
        meteoT.setLayoutY(230);
        layoutDetail.getChildren().add(meteoT);
        meteo.setLayoutX(100);
        meteo.setLayoutY(250);
        layoutDetail.getChildren().add(meteo);
        layoutDetail.getChildren().add(buttonHome);
        createAccountButton.setLayoutX(650);
        createAccountButton.setLayoutY(100);
        layoutDetail.getChildren().add(createAccountButton);
        return new Scene(layoutDetail, 800, 400);
    }

    protected Scene displayFileJsonScene() {
        TextField filterInput = new TextField();
        Pane layoutFileJson = new Pane();

        layoutFileJson.getChildren().add(filterInput);

        layoutFileJson.setPadding(new Insets(20,20,20,20));
        buttonHome.setLayoutX(700);
        buttonHome.setLayoutY(20);
        layoutFileJson.getChildren().add(buttonHome);

        Button filterButton = new Button("Filter");
        filterButton.setLayoutX(150);
        layoutFileJson.getChildren().add(filterButton);
        filterButton.setOnAction(new EventHandler<ActionEvent>(){
            public void handle(ActionEvent t){
                //System.out.println("Filter " + filterInput.getText());
                ListView listJson = buildListJson(filterInput.getText());
                //ListView listJson = buildListJson("Lyon 2");
                //System.out.println("Filter " + filterInput.getText());
            }
        });

        ListView listJson = buildListJson("");


        listJson.setLayoutX(20);
        listJson.setLayoutY(50);
        layoutFileJson.getChildren().remove(listJson);
        layoutFileJson.getChildren().add(listJson);


        return new Scene(layoutFileJson, 800, 400);
    }

    public static String callURL(String myURL) {
        //System.out.println("Requested URL:" + myURL);
        StringBuilder sb = new StringBuilder();
        URLConnection urlConn = null;
        InputStreamReader in = null;
        try {
            URL url = new URL(myURL);
            urlConn = url.openConnection();
            if (urlConn != null)
                urlConn.setReadTimeout(60 * 1000);
            if (urlConn != null && urlConn.getInputStream() != null) {
                in = new InputStreamReader(urlConn.getInputStream(),
                        Charset.defaultCharset());
                BufferedReader bufferedReader = new BufferedReader(in);
                if (bufferedReader != null) {
                    int cp;
                    while ((cp = bufferedReader.read()) != -1) {
                        sb.append((char) cp);
                    }
                    bufferedReader.close();
                }
            }
            in.close();
        } catch (Exception e) {
            throw new RuntimeException("Exception while calling URL:"+ myURL, e);
        }
        return sb.toString();
    }


    protected ListView buildListJson(String filter) {
        //System.out.println("\n\nfilter buildListJson: " +  filter);

        JSONObject jsonObject = new JSONObject();

        String jsonString = callURL("https://api.weatherbit.io/v2.0/current?lat=35.7796&lon=-78.6382&key=876c70d7741f4bb384a906aa02a19053&include=minutely");
        // Replace this try catch block for all below subsequent examples
        try {
            JSONArray jsonArray = new JSONArray("[" + jsonString + "]");
            //System.out.println("\n\njsonArray: " +  jsonArray);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        Text dataJson;
        ListView listJson = new ListView();
        ObservableList DJson = FXCollections.observableArrayList();
        DJson.removeAll();
        listJson.getItems().removeAll();
        listJson.refresh();


        try {
            JSONArray jsonArray = new JSONArray("[" + jsonString + "]");
            JSONObject jsonObjectGlobal = jsonArray.getJSONObject(0);
            JSONArray jsonMeteo = jsonObjectGlobal.getJSONArray("minutely");
            int count = jsonMeteo.length(); // get totalCount of all jsonObjects
            for(int i=0 ; i< count; i++){   // iterate through jsonArray
                JSONObject jsonObjectStation = jsonMeteo.getJSONObject(i);  // get jsonObject @ i position
                //System.out.println("jsonObject " + i + ": " + jsonObjectStation);
                if (filter != "") {
                    String jsonVille = jsonObjectStation.getString("ville");
                    //System.out.println("commune " + i + ": " + jsonVille);
                    if (jsonVille.contains(filter)) {
                        //System.out.println("ville " + filter + ": " + jsonVille);
                        DJson.add(jsonObjectStation);
                    }
                } else {
                    DJson.add(jsonObjectStation);
                }

            }
            dataJson = new Text("\n\njsonFiltrer: " + jsonString);
        } catch (JSONException e) {

            dataJson = new Text(e.getMessage());
            e.printStackTrace();
        }
        //System.out.println("dataJson nb element : " + DJson.size());
        listJson.setItems(DJson);
        listJson.refresh();

        return listJson;
    }


    //protected Scene createJeuScene() {

    //return;
    //}

}
