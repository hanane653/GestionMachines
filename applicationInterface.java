package gestionMachines;

import java.awt.Color;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

@SuppressWarnings("serial")
public class applicationInterface extends JFrame
{
	    JPanel Panneau;
	    
	    JPanel Panel;
	    JPanel Panel2;
	    JPanel Panel3;
	    @SuppressWarnings("unused")
		private Connection connection;
	    @SuppressWarnings("unused")
		private Map<Integer, Object[]> modifiedData;
	    JLabel label;
	    JTextField textField1, textField2, textField3, textField5,textField4;
	    private static final String DB_URL = "jdbc:mysql://localhost:3306/gestion_machines";
	    private static final String DB_USERNAME = "root";
	    private static final String DB_PASSWORD = "";
	    public applicationInterface ()
	    {
	    	
	    	setTitle("application de gestion des machines de la radeef");
	    	setSize(1000,1000);
	    	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        setLocationRelativeTo(null);
	        Panneau = new JPanel();
	        Panneau.setBackground(MyColor.SECONDARY);
	        Panneau.setLayout(new GridBagLayout());
	        GridBagConstraints constraints = new GridBagConstraints();
            JLabel label =new JLabel("Bienvenue dans l'application de gestion des machines la RADEEF ");
            label.setForeground(Color.WHITE);
            constraints.gridx=2;
            constraints.gridy=2;

            label.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 25));
            Panneau.add(label,constraints);
            
            ImageIcon imageIcon = new ImageIcon("C:\\Users\\hp\\Documents\\utilisateur.jpg");
            Image image = imageIcon.getImage();
            int newWidth = 110; 
            int newHeight = 110; 
            Image resizedImage = image.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);

            ImageIcon resizedIcon = new ImageIcon(resizedImage);
   JButton button1 = new JButton(resizedIcon);
      constraints.gridx=0;
      constraints.gridy=0;
      button1.setBackground(new Color(255-255-255));
      button1.setForeground(Color.WHITE);
      button1.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			//interface utilisateur
			JFrame frame_utilisateur = new JFrame();
			frame_utilisateur.setTitle("interface utilisateur");
            frame_utilisateur.setSize(800,800);
            frame_utilisateur.setLocationRelativeTo(null);
            frame_utilisateur.setVisible(true);
            JPanel Panel = new JPanel();
            Panel.setBackground(Color.white);
            ImageIcon imageIcon3 = new ImageIcon("C:\\Users\\hp\\Documents\\radeef.jpg");
            Image image3 = imageIcon3.getImage();
            int newWidth3 = 700; 
            int newHeight3 = 700; 
            Image resizedImage3 = image3.getScaledInstance(newWidth3, newHeight3, Image.SCALE_SMOOTH);
            ImageIcon resizedIcon3= new ImageIcon(resizedImage3);
            JLabel label3 = new JLabel(resizedIcon3);
            GridBagConstraints constraints = new GridBagConstraints();
            constraints.anchor = GridBagConstraints.WEST;
            constraints.insets = new Insets(5, 2, 5, 10);
            JLabel label2 = new JLabel("Entrer vos informations:");
            constraints.gridx = 0;
            constraints.gridy = 0;
            Panel.add(label2,constraints);
            JLabel nameLabel = new JLabel("login");

            constraints.gridx = 1;
            constraints.gridy = 1;
            Panel.add(nameLabel, constraints);
            textField1 = new JTextField(20);
            constraints.gridx = 2;
            constraints.gridy = 1;
            Panel.add(textField1,constraints);
            JLabel Label = new JLabel("mot de passe:");
            constraints.gridx = 0;
            constraints.gridy = 2;
            Panel.add(Label, constraints);
            textField3 = new JTextField(20);
            constraints.gridx = 1;
            constraints.gridy = 2;
            Panel.add(textField3,constraints);
            JButton connexion = new JButton("Connexion");
            connexion.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					//interface dedie a l'utilisateur pour ajouter des reclamations
				JFrame FRAME = new JFrame();
				FRAME.setTitle("interface utilisateur");
				FRAME.setSize(800,800);
				FRAME.setLocationRelativeTo(null);
				
	            JPanel Panel20 = new JPanel();
	            Panel20.setBackground(Color.white);
	            JLabel label0 = new JLabel("bienvenue dans l'iterface utilisateur");
	            Panel20.add(label0);
	            
	            JButton buttonn = new JButton("ajouter une reclamation");
	            Panel20.add(buttonn);
	            FRAME.add(Panel20);
	            buttonn.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						JFrame FRAME0 = new JFrame();
						FRAME0.setTitle("interface reclamation");
						FRAME0.setSize(800,800);
						FRAME0.setLocationRelativeTo(null);
						FRAME0.setVisible(true);
						
						JPanel Panel21 = new JPanel();
			            Panel21.setBackground(Color.white);
			            Panel21.setVisible(true);
			            Panel21.setLayout(new BoxLayout(Panel21, BoxLayout.Y_AXIS));
			            JLabel LABEL0 = new JLabel("description de reclamation");
			            Panel21.add(LABEL0);
			            JTextField TEXT = new JTextField();
			            Panel21.add(TEXT);
			            JLabel LABEL1 = new JLabel("etat de reclamation");
			            Panel21.add(LABEL1);
			            JTextField TEXT1 = new JTextField();
			            Panel21.add(TEXT1);
			            JButton buttonn0 = new JButton("ENREGISTRER");
			            Panel21.add(buttonn0);
			            FRAME0.add(Panel21);
			            buttonn0.addActionListener(new ActionListener() {
							
							@Override
							public void actionPerformed(ActionEvent e) {
								try {
							        Connection conn= DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
							    
							        String query10 = "INSERT INTO reclamation (id_reclamation,`date d'ajout`,etat_reclamation,id_utilisateur,id_technicien,description) VALUES (?,?,?,?,?,?)";
							        PreparedStatement Statement10 = conn.prepareStatement(query10);
							        String description = TEXT.getText();
							        String etat = TEXT1.getText();
							        String id_utilisateur = textField1.getText();
							        
							        String id_technicien = "T56383";
							        Random random = new Random();

							        // Définir la valeur maximale (par exemple 100)
							        int maxValue = 100;

							        // Générer un nombre aléatoire compris entre 1 et maxValue (inclus)
							        int id_reclamation = random.nextInt(maxValue) + 1;
						            Statement10.setLong(1, id_reclamation);
						            Statement10.setString(4, id_utilisateur);
						            
						            Statement10.setString(5, id_technicien);
						            Statement10.setString(6, description);
						            Statement10.setString(3, etat);
						           
						            java.util.Date javaDate = new java.util.Date(); // Date actuelle
						            java.sql.Date sqlDate = new java.sql.Date(javaDate.getTime());
						            Statement10.setDate(2, sqlDate);


						            int rowsAffected = Statement10.executeUpdate();

						            // Vérifier si l'insertion a réussi
						            if (rowsAffected > 0) {
						                JOptionPane.showMessageDialog(null, "Réclamation insérée avec succès !");
						            } else {
						                JOptionPane.showMessageDialog(null, "Erreur d'insertion");
						            }

						            Statement10.close();
						            conn.close();
							    }
							    catch(Exception E){
							    	 E.printStackTrace();				    	
							    }
							    

							
								
								
							}
						} );
			            
			            
			            
			            
			            
			            
			            
			            
						
					}
				});
	            try {
			        Connection conn= DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
			    
			        String query11 = "SELECT nom_prenom,matricule,id_service,`date d'affectation`,mot_de_passe FROM utilisateur ";
			        Statement statement = conn.createStatement();
					ResultSet resultSet = statement.executeQuery(query11);
			        while(resultSet.next())
			        {
			        	 String matricule = resultSet.getString(2);
			        	 
			        	String mot_de_passe = resultSet.getString(5);
			        	 
			        	 String login =textField1.getText();
			        	 String mdp = textField3.getText();
			        	 if( matricule.equals(login) && mot_de_passe.equals(mdp) )
			        	 {
			        		 FRAME.setVisible(true);
			        	 }
			        	 else {
			        		 JOptionPane.showMessageDialog(null,"Mot de passe erroné");
			        	 }
			        	
			        }
			        resultSet.close();
		            conn.close();
			    }
			    catch(Exception E){
			    	 E.printStackTrace();				    	
			    }
			    
				
				}
			});
            Panel.add(connexion);
            Panel.add(label3);


            frame_utilisateur.add(Panel);
            
			
		}
	});
      JLabel label1 = new JLabel("UTILISATEUR");
      label1.setForeground(MyColor.WHITE);
      
      ImageIcon imageIcon2 = new ImageIcon("C:\\Users\\hp\\Documents\\administrateur.png");
      Image image2 = imageIcon2.getImage();
      int newWidth2 = 110; 
      int newHeight2 = 110; 
      Image resizedImage2 = image2.getScaledInstance(newWidth2, newHeight2, Image.SCALE_SMOOTH);
      ImageIcon resizedIcon2= new ImageIcon(resizedImage2);
      JButton button2 = new JButton(resizedIcon2);
      Panneau.add(button1,constraints);
      constraints.gridx=2;
      constraints.gridy=0;
      Panneau.add(button2,constraints);
      button2.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) 
		{
			//interface administrateur
			JFrame frame_administrateur = new JFrame();
			frame_administrateur.setTitle("interface administrateur");
            frame_administrateur.setSize(800,800);
            frame_administrateur.setLocationRelativeTo(null);
            frame_administrateur.setVisible(true);
            JPanel Panel2 = new JPanel();
            Panel2.setBackground(Color.white);
            ImageIcon icon1 = new ImageIcon("C:\\Users\\hp\\Documents\\radeef.jpg");
            JLabel label4 = new JLabel();
            label4.setIcon(icon1);
            GridBagConstraints constraints = new GridBagConstraints();
            constraints.anchor = GridBagConstraints.WEST;
            constraints.insets = new Insets(5, 2, 5, 10);
            JLabel label5 = new JLabel("Entrer vos informations:");
            constraints.gridx = 0;
            constraints.gridy = 0;
            Panel2.add(label5,constraints);
            JLabel namLabel = new JLabel("login");

            constraints.gridx = 0;
            constraints.gridy = 1;
            Panel2.add(namLabel, constraints);
            textField4 = new JTextField(20);
            constraints.gridx = 1;
            constraints.gridy = 1;
            Panel2.add(textField4,constraints);
            JLabel iLabel = new JLabel("mot de passe:");
            constraints.gridx = 0;
            constraints.gridy = 2;
            Panel2.add(iLabel, constraints);
            textField5 = new JTextField(20);
            constraints.gridx = 1;
            constraints.gridy = 2;
            Panel2.add(textField5,constraints);
            JButton connexion2 = new JButton("SE CONNECTER");
          //interface des fonctions de l'administrateur
			JFrame frame =new JFrame();
			frame.setTitle("interface administrateur");
			frame.setSize(500,500);
			 frame.setLocationRelativeTo(null);
	            //frame.setVisible(true);
	            JPanel Panel3 = new JPanel();
	            Panel3.setBackground(Color.white);
	            JLabel LABEL = new JLabel("bienvenue dans l'interface administrateur");
	            Panel3.add(LABEL);
	            JButton button4 = new JButton("ajouter un nouveau utilisateur");
	            Panel3.add(button4);
	            button4.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						//INTERFACE D'AJOUT DE NOUVEAUX UTILISATEURS:
						JFrame newFrame = new JFrame();
						newFrame.setTitle("ajout des utilisateurs");
						newFrame.setSize(500,500);
						newFrame.setLocationRelativeTo(null);
						newFrame.setVisible(true);
						newFrame.setLayout(new BoxLayout(newFrame.getContentPane(), BoxLayout.Y_AXIS));

						JPanel PANEL = new JPanel();
						PANEL.setLayout(new BoxLayout(PANEL, BoxLayout.Y_AXIS));
						
						JLabel labell=new JLabel("ajouter de nouveaux utilisateurs:");
						constraints.gridx = 0;
				        constraints.gridy = 0;
				        PANEL.add(labell,constraints);
						JLabel label11 = new JLabel("nom et prenom");
						constraints.gridx = 0;
				        constraints.gridy = 1;
				        PANEL.add(label11);
						JTextField textfield11 = new JTextField(8);
						   constraints.gridx = 1;
				           constraints.gridy = 1;
				           PANEL.add(textfield11);
				           
						JLabel label12 = new JLabel("matricule");
						constraints.gridx = 0;
				           constraints.gridy = 2;
				           PANEL.add(label12);
						JTextField textfield12 = new JTextField();
						constraints.gridx = 1;
				           constraints.gridy =2;
				           PANEL.add(textfield12);
						JLabel label13 = new JLabel("id_service");
						constraints.gridx = 0;
				           constraints.gridy = 3;
				           PANEL.add(label13);
						JTextField textfield13 = new JTextField();
						constraints.gridx = 1;
				           constraints.gridy = 3;
				           PANEL.add(textfield13);
				           JLabel label131 = new JLabel("mot_de_passe");
							constraints.gridx = 0;
					           constraints.gridy = 3;
					           PANEL.add(label131);
							JTextField textfield131 = new JTextField();
							constraints.gridx = 1;
					           constraints.gridy = 3;
					           PANEL.add(textfield131);
						
				           
						
				           //boutton pour ajouter de nouveaux utilisateurs a la base de données
				           JButton button12 = new JButton("AJOUTER");
				           button12.addActionListener(new ActionListener() {
							
							@Override
							public void actionPerformed(ActionEvent e) {
								 try {
								        Connection conn= DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
								    
								        String query1 = "INSERT INTO utilisateur (nom_prenom,matricule,id_service,`date d'affectation`,mot_de_passe) VALUES (?,?,?,?,?)";
								        PreparedStatement Statement1 = conn.prepareStatement(query1);
								        String nom_prenom = textfield11.getText();
								        String matricule = textfield12.getText();
								        String id_service = textfield13.getText();
								        String mot_de_passe = textfield131.getText();
							            Statement1.setString(1,nom_prenom);
							            Statement1.setString(2, matricule);
							            Statement1.setString(3, id_service);
							            Statement1.setString(5, mot_de_passe);
							            java.util.Date javaDate = new java.util.Date(); // Date actuelle
							            java.sql.Date sqlDate = new java.sql.Date(javaDate.getTime());
							            Statement1.setDate(4, sqlDate);


							            Statement1.executeUpdate();

							            Statement1.close();
							            conn.close();
								    }
								    catch(Exception E){
								    	 E.printStackTrace();				    	
								    }
								    

								
								
							}
						});
				           
				           PANEL.add(button12);
				           newFrame.add(PANEL);

						
						
						
					}
				});
	            JButton button5 = new JButton("modifier la machine d'un utilisateur");
	            Panel3.add(button5);
	            button5.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						 JPanel paneau = new JPanel();
					        JTextField valeur1Field = new JTextField(10);
					        JTextField valeur2Field = new JTextField(10);
					        paneau.add(new JLabel("nom machine:"));
					        paneau.add(valeur1Field);
					        paneau.add(Box.createHorizontalStrut(15)); // Ajouter un espace horizontal entre les champs de texte
					        paneau.add(new JLabel("nouveau utilisateur :"));
					        paneau.add(valeur2Field);

					        // Afficher la boîte de dialogue avec le panneau personnalisé
					        int option = JOptionPane.showOptionDialog(
					                null, // Composant parent (null signifie le centre de l'écran)
					                paneau, // Panneau personnalisé avec les champs de texte
					                "Entrez deux nouvelles valeurs", // Titre de la boîte de dialogue
					                JOptionPane.OK_CANCEL_OPTION, // Type d'options (OK_CANCEL_OPTION pour avoir les boutons "OK" et "Annuler")
					                JOptionPane.INFORMATION_MESSAGE, // Type de message (icône d'information)
					                null, // Icône personnalisée (null signifie icône par défaut)
					                null, // Options personnalisées (null signifie aucune option personnalisée)
					                null // Option sélectionnée par défaut (null signifie aucune option sélectionnée par défaut)
					        );

					        // Vérifier le choix de l'utilisateur
					        if (option == JOptionPane.OK_OPTION) {
					            // L'utilisateur a cliqué sur le bouton "OK"

					            // Récupérer les valeurs saisies par l'utilisateur dans les champs de texte
					            @SuppressWarnings("unused")
								String nouvelleValeur1 = valeur1Field.getText();
					            @SuppressWarnings("unused")
								String nouvelleValeur2 = valeur2Field.getText();}
						try {
				        Connection conn= DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
				        String sql = "UPDATE machine SET id_utilisateur =?  WHERE nom_machine=?";
				        PreparedStatement pstmt = conn.prepareStatement(sql);
				        String nouvelleValeur1 = valeur1Field.getText();
			            String nouvelleValeur2 = valeur2Field.getText();
			            // Remplacer les valeurs des paramètres dans la requête SQL
			            // Par exemple, si vous souhaitez modifier la colonne 'nom_colonne' à une nouvelle valeur 'nouvelle_valeur'
			            pstmt.setString(1, nouvelleValeur2);
			            pstmt.setString(2, nouvelleValeur1);

			            // Spécifier la condition pour la mise à jour (par exemple, WHERE id = 1)
			            // Vous devez définir correctement cette condition pour identifier la ligne que vous souhaitez mettre à jour
			            // Exemple : pstmt.setInt(2, 1);

			            // Exécuter la mise à jour
			            int rowsAffected = pstmt.executeUpdate();

			            // Vérifier si la mise à jour a été effectuée avec succès
			            if (rowsAffected > 0) {
			                System.out.println("Mise à jour réussie : " + rowsAffected + " ligne(s) modifiée(s).");
			            } else {
			                System.out.println("Aucune ligne n'a été modifiée.");
			            }

			            // Fermer les ressources
			            pstmt.close();
			            conn.close();
						}
						catch(Exception E) {
							E.getStackTrace();
							
						}
						
					}
				});
	            JButton button15 = new JButton("ajouter une machine ");
	            button15.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						JFrame frame4 = new JFrame();
						frame4.setTitle("ajout des Services");
						frame4.setSize(500,500);
						frame4.setLocationRelativeTo(null);
						frame4.setVisible(true);
						frame4.setLayout(new BoxLayout(frame4.getContentPane(), BoxLayout.Y_AXIS));

						JPanel PANEL3 = new JPanel();
						PANEL3.setLayout(new BoxLayout(PANEL3, BoxLayout.Y_AXIS));
						
						JLabel labell3=new JLabel("ajouter de nouveaux machnines:");
						constraints.gridx = 0;
				        constraints.gridy = 0;
				        PANEL3.add(labell3,constraints);
						JLabel label24 = new JLabel("nom de machine");
						constraints.gridx = 0;
				        constraints.gridy = 1;
				        PANEL3.add(label24);
						JTextField textfield24 = new JTextField(8);
						   constraints.gridx = 1;
				           constraints.gridy = 1;
				           PANEL3.add(textfield24);
				           
						JLabel label25 = new JLabel("port_reseau");
						constraints.gridx = 0;
				           constraints.gridy = 2;
				           PANEL3.add(label25);
						JTextField textfield25 = new JTextField();
						constraints.gridx = 1;
				           constraints.gridy =2;
				           PANEL3.add(textfield25);
				           
							JLabel label26 = new JLabel("adresse_ip");
							constraints.gridx = 0;
					           constraints.gridy = 2;
					           PANEL3.add(label26);
							JTextField textfield26 = new JTextField();
							constraints.gridx = 1;
					           constraints.gridy =2;
					           PANEL3.add(textfield26);
					           JLabel label27 = new JLabel("switch_connecte");
								constraints.gridx = 0;
						           constraints.gridy = 2;
						           PANEL3.add(label27);
								JTextField textfield27 = new JTextField();
								constraints.gridx = 1;
						           constraints.gridy =2;
						           PANEL3.add(textfield27);
						           JLabel label28 = new JLabel("type_machine");
									constraints.gridx = 0;
							           constraints.gridy = 2;
							           PANEL3.add(label28);
									JTextField textfield28 = new JTextField();
									constraints.gridx = 1;
							           constraints.gridy =2;
							           PANEL3.add(textfield28);
							           JLabel label29 = new JLabel("etat");
										constraints.gridx = 0;
								           constraints.gridy = 2;
								           PANEL3.add(label29);
										JTextField textfield29 = new JTextField();
										constraints.gridx = 1;
								           constraints.gridy =2;
								           PANEL3.add(textfield29);
								           JLabel label30 = new JLabel("id_utilisateur");
											constraints.gridx = 0;
									           constraints.gridy = 2;
									           PANEL3.add(label30);
											JTextField textfield30 = new JTextField();
											constraints.gridx = 1;
									           constraints.gridy =2;
									           PANEL3.add(textfield30);
									           
						JButton button04 = new JButton("ajouter");
						PANEL3.add(button04);
						frame4.add(PANEL3);
						button04.addActionListener(new ActionListener() {
							
							@Override
							public void actionPerformed(ActionEvent e) {
								
								try {
							        Connection conn= DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
							    
							        String query6 = "INSERT INTO machine (nom_machine,port_reseau,adresse_ip,switch_connecte,type_machine,etat,id_utilisateur,`date d'affectation`) VALUES (?,?,?,?,?,?,?,?)";
							        PreparedStatement Statement5 = conn.prepareStatement(query6);
							        String nom_machine = textfield24.getText();
							        String port_reseau = textfield25.getText();
							        String adresse_ip = textfield26.getText();
							        String switch_connecte = textfield27.getText();
							        String type_machine = textfield28.getText();
							        String etat = textfield29.getText();
							        String id_utilisateur = textfield30.getText();

							        

						            Statement5.setString(1,nom_machine);
						            Statement5.setString(2, port_reseau);
						            Statement5.setString(3, adresse_ip);
						            Statement5.setString(4, switch_connecte);
						            Statement5.setString(5, type_machine);
						            Statement5.setString(6, etat);
						            Statement5.setString(7, id_utilisateur);
						            
						            java.util.Date javaDate = new java.util.Date(); // Date actuelle
						            java.sql.Date sqlDate1 = new java.sql.Date(javaDate.getTime());
						            Statement5.setDate(8,sqlDate1);
						            

						            int rowsAffected = Statement5.executeUpdate();

						            // Vérifier si l'insertion a réussi
						            if (rowsAffected > 0) {
						                JOptionPane.showMessageDialog(null, "Machine insérée avec succès !");
						            } else {
						                JOptionPane.showMessageDialog(null, "Erreur d'insertion");
						            }

						            Statement5.close();
						            conn.close();
							    }
							    catch(Exception E){
							    	 E.printStackTrace();				    	
							    }
							}
						});
						
					}
				});
	            Panel3.add(button15);
	            
	            JButton button6 = new JButton("supprimer un utilisateur");
	            Panel3.add(button6);
	            JButton button7 = new JButton("liste des machines des utilisateurs");
	            Panel3.add(button7);
	            button7.addActionListener(new ActionListener() {
					
					@Override
					
						public void actionPerformed(ActionEvent e) {
							
							JFrame FrameM = new JFrame();
							FrameM.setTitle("liste des machines");
							FrameM.setSize(800,800);
							FrameM.setLocationRelativeTo(null);
							
				            JPanel PanelM = new JPanel();
				            
				            FrameM.add(PanelM);
				            DefaultTableModel tableModel;
				        
									 List<String> machines = new ArrayList<>();
									 try {
										 Connection cone= DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
								            // Créer la requête SQL
								            String sql = "SELECT nom_machine,port_reseau,adresse_ip,switch_connecte,type_machine,etat,id_utilisateur,`date d'affectation` FROM machine";

								            // Exécuter la requête SQL
								            Statement statement = cone.createStatement();
								            ResultSet resultSet = statement.executeQuery(sql);

								            // Parcourir les résultats de la requête et ajouter les réclamations à la liste
								            while (resultSet.next()) {
								                String nom_machine = resultSet.getString(1);
								                String port_reseau = resultSet.getString(2);
								                String adresse_ip = resultSet.getString(3);
								                
								                String switch_connecte = resultSet.getString(4);
								                String type_machine = resultSet.getString(5);
								                String etat = resultSet.getString(6);
								                String id_utilisateur = resultSet.getString(7);
								                String date_affectation = resultSet.getString(8);
								                		
								                machines.add(nom_machine);
								                machines.add(port_reseau);
								                machines.add(adresse_ip);
								               
								                machines.add(switch_connecte);
								                machines.add(type_machine);
								                machines.add(etat);
								                machines.add(id_utilisateur);
								                machines.add(date_affectation);
								            }

								            // Fermer les ressources
								            resultSet.close();
								            statement.close();
								        } catch (SQLException E) {
								            E.printStackTrace();
								        }
									 String[] columnNames = {"nom machine", "port reseau","adresse ip", "switch connecte","type machine","etat de machine","id utilisateur","date d'affectation"};


									    tableModel = new DefaultTableModel(columnNames, 0); // 0 indique qu'il n'y a aucune ligne au départ

									 // Ajouter les réclamations au modèle du tableau
									 for (int i = 0; i < machines.size(); i += 8) { // Comme chaque réclamation a 5 éléments (id_reclamation, date_ajout, etat_reclamation, id_utilisateur, description)
									     String nom_machine = machines.get(i);
									     String port_reseau = machines.get(i + 1);
									     
									     String adresse_ip = machines.get(i + 2);
									     String switch_connecte = machines.get(i + 3);
									     String type_machine = machines.get(i + 4);
									     String etat = machines.get(i + 5);
									     String id_utilisateur = machines.get(i + 6);
									     String date_affectation = machines.get(i + 7);

									     tableModel.addRow(new Object[]{nom_machine, port_reseau, adresse_ip,switch_connecte,type_machine,etat,id_utilisateur,date_affectation});
									 }

									 // Appliquer le modèle du tableau au JTable
									 JTable table = new JTable(tableModel);
									
									
									 // Ajouter le JTable dans la fenêtre
									 JScrollPane scrollPane = new JScrollPane(table);
									 FrameM.add(scrollPane);
									 PanelM.add(scrollPane);
									  FrameM.add(PanelM);

								        // Rendre le JFrame FrameT visible après avoir ajouté le panneau
								        FrameM.setVisible(true);
						
					}
				});
	            JButton button8 = new JButton("les observations du techniciens");
	            Panel3.add(button8);
	            button8.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						
						JFrame FrameA = new JFrame();
						FrameA.setTitle("interface Gestion des observations");
						FrameA.setSize(800,800);
						FrameA.setLocationRelativeTo(null);
						
			            JPanel PanelA = new JPanel();
			            
			            FrameA.add(PanelA);
			            DefaultTableModel tableModel;
			        
								 List<String> observations = new ArrayList<>();
								 try {
									 Connection con= DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
							            // Créer la requête SQL
							            String sql = "SELECT id_observation,`date d'ajout`,id_technicien,id_administrateur,description FROM observation";

							            // Exécuter la requête SQL
							            Statement statement = con.createStatement();
							            ResultSet resultSet = statement.executeQuery(sql);

							            // Parcourir les résultats de la requête et ajouter les réclamations à la liste
							            while (resultSet.next()) {
							                String id_observation = resultSet.getString(1);
							                String date_ajout = resultSet.getString(2);
							                String id_administrateur = resultSet.getString(4);
							                
							                String id_technicien = resultSet.getString(3);
							                String description = resultSet.getString(5);
							                		
							                observations.add(id_observation);
							                observations.add(date_ajout);
							                observations.add(id_administrateur);
							               
							                observations.add(id_technicien);
							                observations.add(description);
							            }

							            // Fermer les ressources
							            resultSet.close();
							            statement.close();
							        } catch (SQLException E) {
							            E.printStackTrace();
							        }
								 String[] columnNames = {"ID observation", "Date d'ajout","ID TECHNICIEN", "ID ADMINISTRATEUR","Description"};


								    tableModel = new DefaultTableModel(columnNames, 0); // 0 indique qu'il n'y a aucune ligne au départ

								 // Ajouter les réclamations au modèle du tableau
								 for (int i = 0; i < observations.size(); i += 5) { // Comme chaque réclamation a 5 éléments (id_reclamation, date_ajout, etat_reclamation, id_utilisateur, description)
								     String id_observation = observations.get(i);
								     String date_ajout = observations.get(i + 1);
								     
								     String id_technicien = observations.get(i + 2);
								     String id_administrateur = observations.get(i + 3);
								     String description = observations.get(i + 4);

								     tableModel.addRow(new Object[]{id_observation, date_ajout, id_technicien,id_administrateur,description});
								 }

								 // Appliquer le modèle du tableau au JTable
								 JTable table = new JTable(tableModel);
								
								
								 // Ajouter le JTable dans la fenêtre
								 JScrollPane scrollPane = new JScrollPane(table);
								 FrameA.add(scrollPane);
								 PanelA.add(scrollPane);
								  FrameA.add(PanelA);

							        // Rendre le JFrame FrameT visible après avoir ajouté le panneau
							        FrameA.setVisible(true);
					}
				});
	            JButton button9 = new JButton("ajouter un service");
	            button9.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						JFrame frame4 = new JFrame();
						frame4.setTitle("ajout des Services");
						frame4.setSize(500,500);
						frame4.setLocationRelativeTo(null);
						frame4.setVisible(true);
						frame4.setLayout(new BoxLayout(frame4.getContentPane(), BoxLayout.Y_AXIS));

						JPanel PANEL3 = new JPanel();
						PANEL3.setLayout(new BoxLayout(PANEL3, BoxLayout.Y_AXIS));
						
						JLabel labell3=new JLabel("ajouter de nouveaux services:");
						constraints.gridx = 0;
				        constraints.gridy = 0;
				        PANEL3.add(labell3,constraints);
						JLabel label21 = new JLabel("nom de service");
						constraints.gridx = 0;
				        constraints.gridy = 1;
				        PANEL3.add(label21);
						JTextField textfield21 = new JTextField(8);
						   constraints.gridx = 1;
				           constraints.gridy = 1;
				           PANEL3.add(textfield21);
				           
						JLabel label22 = new JLabel("id de service");
						constraints.gridx = 0;
				           constraints.gridy = 2;
				           PANEL3.add(label22);
						JTextField textfield22 = new JTextField();
						constraints.gridx = 1;
				           constraints.gridy =2;
				           PANEL3.add(textfield22);
				           
							JLabel label23 = new JLabel("id de division");
							constraints.gridx = 0;
					           constraints.gridy = 2;
					           PANEL3.add(label23);
							JTextField textfield23 = new JTextField();
							constraints.gridx = 1;
					           constraints.gridy =2;
					           PANEL3.add(textfield23);
						JButton button03 = new JButton("ajouter");
						PANEL3.add(button03);
						frame4.add(PANEL3);
						button03.addActionListener(new ActionListener() {
							
							@Override
							public void actionPerformed(ActionEvent e) {
								
								try {
							        Connection conn= DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
							    
							        String query4 = "INSERT INTO service (nom_service,id_service,id_division) VALUES (?,?,?)";
							        PreparedStatement Statement4 = conn.prepareStatement(query4);
							        String nom_service = textfield21.getText();
							        String id_service = textfield22.getText();
							        String id_division = textfield23.getText();
							        

						            Statement4.setString(1,nom_service);
						            Statement4.setString(2, id_service);
						            Statement4.setString(3, id_division);
						            

						            int rowsAffected = Statement4.executeUpdate();

						            // Vérifier si l'insertion a réussi
						            if (rowsAffected > 0) {
						                JOptionPane.showMessageDialog(null, "Service insérée avec succès !");
						            } else {
						                JOptionPane.showMessageDialog(null, "Erreur d'insertion");
						            }

						            Statement4.close();
						            conn.close();
							    }
							    catch(Exception E){
							    	 E.printStackTrace();				    	
							    }
							}
						});
						
					}
				});
	            Panel3	.add(button9);
	            JButton button10 = new JButton("ajouter un Departement");
	            button10.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						JFrame frame1 = new JFrame();
						frame1.setTitle("ajout des départements");
						frame1.setSize(500,500);
						frame1.setLocationRelativeTo(null);
						frame1.setVisible(true);
						frame1.setLayout(new BoxLayout(frame1.getContentPane(), BoxLayout.Y_AXIS));

						JPanel PANEL1 = new JPanel();
						PANEL1.setLayout(new BoxLayout(PANEL1, BoxLayout.Y_AXIS));
						
						JLabel labell1=new JLabel("ajouter de nouveaux departements:");
						constraints.gridx = 0;
				        constraints.gridy = 0;
				        PANEL1.add(labell1,constraints);
						JLabel label16 = new JLabel("nom de departement");
						constraints.gridx = 0;
				        constraints.gridy = 1;
				        PANEL1.add(label16);
						JTextField textfield16 = new JTextField(8);
						   constraints.gridx = 1;
				           constraints.gridy = 1;
				           PANEL1.add(textfield16);
				           
						JLabel label17 = new JLabel("id de departement");
						constraints.gridx = 0;
				           constraints.gridy = 2;
				           PANEL1.add(label17);
						JTextField textfield17 = new JTextField();
						constraints.gridx = 1;
				           constraints.gridy =2;
				           PANEL1.add(textfield17);
						JButton button00 = new JButton("ajouter");
						PANEL1.add(button00);
						frame1.add(PANEL1);
						button00.addActionListener(new ActionListener() {
							
							@Override
							public void actionPerformed(ActionEvent e) {
								
								try {
							        Connection conn= DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
							    
							        String query2 = "INSERT INTO departement (id_departement,nom_departement) VALUES (?,?)";
							        PreparedStatement Statement2 = conn.prepareStatement(query2);
							        String nom_departement = textfield16.getText();
							        String id_departement = textfield17.getText();
							        

						            Statement2.setString(2,nom_departement);
						            Statement2.setString(1, id_departement);
						            

						            Statement2.executeUpdate();

						            Statement2.close();
						            conn.close();
							    }
							    catch(Exception E){
							    	 E.printStackTrace();				    	
							    }
							}
						});
						
						
					}
				});
	            Panel3.add(button10);
	            JButton button0 = new JButton("ajouter une division");
	            button0.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						JFrame frame2 = new JFrame();
						frame2.setTitle("ajout des divisions");
						frame2.setSize(500,500);
						frame2.setLocationRelativeTo(null);
						frame2.setVisible(true);
						frame2.setLayout(new BoxLayout(frame2.getContentPane(), BoxLayout.Y_AXIS));

						JPanel PANEL2= new JPanel();
						PANEL2.setLayout(new BoxLayout(PANEL2, BoxLayout.Y_AXIS));
						
						JLabel labell2=new JLabel("ajouter de nouveaux divisions:");
						constraints.gridx = 0;
				        constraints.gridy = 0;
				        PANEL2.add(labell2,constraints);
						JLabel label18 = new JLabel("nom de division");
						constraints.gridx = 0;
				        constraints.gridy = 1;
				        PANEL2.add(label18);
						JTextField textfield18 = new JTextField(8);
						   constraints.gridx = 1;
				           constraints.gridy = 1;
				           PANEL2.add(textfield18);
				           
						JLabel label19 = new JLabel("id de division");
						constraints.gridx = 0;
				           constraints.gridy = 2;
				           PANEL2.add(label19);
						JTextField textfield19 = new JTextField();
						constraints.gridx = 1;
				           constraints.gridy =2;
				           PANEL2.add(textfield19);
				           JLabel label20= new JLabel("id de departement");
							constraints.gridx = 0;
					           constraints.gridy = 2;
					           PANEL2.add(label20);
							JTextField textfield20 = new JTextField();
							constraints.gridx = 1;
					           constraints.gridy =2;
					           PANEL2.add(textfield20);
						JButton button01 = new JButton("ajouter");
						PANEL2.add(button01);
						frame2.add(PANEL2);
						button01.addActionListener(new ActionListener() {
							
							@Override
							public void actionPerformed(ActionEvent e) {
								
								try {
							        Connection conn= DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
							    
							        String query3 = "INSERT INTO division (nom_division,id_division,id_departement) VALUES (?,?,?)";
							        PreparedStatement Statement3 = conn.prepareStatement(query3);
							        String nom_division = textfield18.getText();
							        String id_division = textfield19.getText();
							        String id_departement = textfield20.getText();
							        

						            Statement3.setString(1,nom_division);
						            Statement3.setString(2,id_division);
						            Statement3.setString(3, id_departement);
						            

						            //Statement3.executeUpdate();
						         // Exécuter la requête d'insertion
						            int rowsAffected = Statement3.executeUpdate();

						            // Vérifier si l'insertion a réussi
						            if (rowsAffected > 0) {
						                JOptionPane.showMessageDialog(null, "Division insérée avec succès !");
						            } else {
						                JOptionPane.showMessageDialog(null, "Erreur d'insertion");
						            }


						            Statement3.close();
						            conn.close();
							    }
							    catch(Exception E){
							    	 E.printStackTrace();				    	
							    }
							}
						});
						
						
						
					}
				});
	            
	            Panel3.add(button0);
	            
	            frame.add(Panel3);
	            
            connexion2.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					System.out.println("Button clicked");
					
					
			            
			            
				    try {
				        Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
				      Statement statement = connection.createStatement();
				        String query = "SELECT nom_administrateur, id_administrateur,mot_de_passe FROM administrateur";
				        ResultSet resultSet = statement.executeQuery(query);
				        while(resultSet.next())
				        {
				        	 @SuppressWarnings("unused")
							String nom = resultSet.getString(1);
				        	 String id_administrateur = resultSet.getString(2);
				        	String mot_de_passe = resultSet.getString(3);
				        	 
				        	 String login =textField4.getText();
				        	 String mdp = textField5.getText();
				        	 if( id_administrateur.equals(login) && mot_de_passe.equals(mdp) )
				        	 {
				        		 frame.setVisible(true);
				        	 }
				        	 else {
				        		 JOptionPane.showMessageDialog(null,"Mot de passe erroné");
				        	 }
				        	
				        }
				        resultSet.close();
					    
			        	 connection.close();
				    }
				    catch(Exception E){
				    	 E.printStackTrace();				    	
				    }
				    

					
				}
			});
            
            Panel2.add(connexion2);
            Panel2.add(label5);
            frame_administrateur.add(Panel2);
            
            
			
		}
	});
      JLabel Label1 = new JLabel("ADMINISTRATEUR");
      Label1.setForeground(MyColor.WHITE);
      constraints.gridx=0;
      constraints.gridy=1;
      Panneau.add(label1,constraints);
      constraints.gridx=2;
      constraints.gridy=1;
      Panneau.add(Label1,constraints);
      
      ImageIcon imageIcon4 = new ImageIcon("C:\\Users\\hp\\Documents\\technicien.png");
      Image image4 = imageIcon4.getImage();
      int newWidth4 = 110; 
      int newHeight4= 110; 
      Image resizedImage4 = image4.getScaledInstance(newWidth4, newHeight4, Image.SCALE_SMOOTH);
      ImageIcon resizedIcon4= new ImageIcon(resizedImage4);
      JButton technicien = new JButton(resizedIcon4);
      technicien.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			//interface technicien
			JFrame frame_technicien = new JFrame();
			frame_technicien.setTitle("interface technicien");
			frame_technicien.setSize(800,800);
			frame_technicien.setLocationRelativeTo(null);
			frame_technicien.setVisible(true);
            JPanel PanelT = new JPanel();
            PanelT.setBackground(Color.white);
            
            PanelT.setLayout(new BoxLayout(PanelT, BoxLayout.Y_AXIS));
            
            JLabel labelT = new JLabel("Entrer vos informations:");
            constraints.gridx = 0;
            constraints.gridy = 0;
            PanelT.add(labelT);
            JLabel namLabell = new JLabel("login");

            constraints.gridx = 0;
            constraints.gridy = 1;
            PanelT.add(namLabell);
            JTextField textField44= new JTextField(20);
            constraints.gridx = 1;
            constraints.gridy = 1;
            PanelT.add(textField44);
            JLabel TLabel = new JLabel("mot de passe:");
            constraints.gridx = 0;
            constraints.gridy = 2;
            PanelT.add(TLabel);
            JTextField textField55 = new JTextField(20);
            constraints.gridx = 1;
            constraints.gridy = 2;
            PanelT.add(textField55,constraints);
            JButton connex= new JButton("SE CONNECTER");
            PanelT.add(connex);
            connex.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					JFrame FrameT = new JFrame();
					FrameT.setTitle("interface Gestion des reclamations");
					FrameT.setSize(800,800);
					FrameT.setLocationRelativeTo(null);
					
		            JPanel PanelR = new JPanel();
		            JButton recevoir = new JButton("afficher la liste des reclamations");
		            PanelR.add(recevoir);
		            FrameT.add(PanelR);
		            @SuppressWarnings("unused")
					DefaultTableModel tableModel;
		            recevoir.addActionListener(new ActionListener() {
						
						@Override
						public void actionPerformed(ActionEvent e) {
							 List<String> reclamations = new ArrayList<>();
							 try {
								 Connection conn= DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
						            // Créer la requête SQL
						            String sql = "SELECT id_reclamation,`date d'ajout`,etat_reclamation,id_utilisateur,id_technicien,description FROM reclamation";

						            // Exécuter la requête SQL
						            Statement statement = conn.createStatement();
						            ResultSet resultSet = statement.executeQuery(sql);

						            // Parcourir les résultats de la requête et ajouter les réclamations à la liste
						            while (resultSet.next()) {
						                String id_reclamation = resultSet.getString(1);
						                String date_ajout = resultSet.getString(2);
						                String etat_reclamation = resultSet.getString(3);
						                String id_utilisateur = resultSet.getString(4);
						                String id_technicien = resultSet.getString(5);
						                String description = resultSet.getString(6);
						                		
						                reclamations.add(id_reclamation);
						                reclamations.add(date_ajout);
						                reclamations.add(etat_reclamation);
						                reclamations.add(id_utilisateur);
						                reclamations.add(id_technicien);
						                reclamations.add(description);
						            }

						            // Fermer les ressources
						            resultSet.close();
						            statement.close();
						        } catch (SQLException E) {
						            E.printStackTrace();
						        }
							 String[] columnNames = {"ID Réclamation", "Date d'ajout", "État Réclamation", "ID Utilisateur","ID TECHNICIEN", "Description"};

							    DefaultTableModel tableModel;

							    tableModel = new DefaultTableModel(columnNames, 0); // 0 indique qu'il n'y a aucune ligne au départ

							 // Ajouter les réclamations au modèle du tableau
							 for (int i = 0; i < reclamations.size(); i += 6) { // Comme chaque réclamation a 5 éléments (id_reclamation, date_ajout, etat_reclamation, id_utilisateur, description)
							     String id_reclamation = reclamations.get(i);
							     String date_ajout = reclamations.get(i + 1);
							     String etat_reclamation = reclamations.get(i + 2);
							     String id_utilisateur = reclamations.get(i + 3);
							     String id_technicien = reclamations.get(i + 4);
							     String description = reclamations.get(i + 5);

							     tableModel.addRow(new Object[]{id_reclamation, date_ajout, etat_reclamation, id_utilisateur, id_technicien,description});
							 }

							 // Appliquer le modèle du tableau au JTable
							 JTable table = new JTable(tableModel);
							 ListSelectionModel selectionModel = table.getSelectionModel();
							 selectionModel.addListSelectionListener(new ListSelectionListener() {
							     @Override
							     public void valueChanged(ListSelectionEvent e) {
							         if (!e.getValueIsAdjusting()) {
							             int selectedRow = table.getSelectedRow();
							             int selectedColumn = table.getSelectedColumn();

							             // Vérifiez si une cellule est sélectionnée (selectedRow >= 0 et selectedColumn >= 0)
							             if (selectedRow >= 0 && selectedColumn >= 0) {
							                 // Récupérez la valeur de la cellule sélectionnée à partir du modèle du tableau
							                 Object selectedValue = table.getValueAt(selectedRow, selectedColumn);
							                 
							                 // Effectuez l'action souhaitée en fonction de la cellule sélectionnée
							                 System.out.println("Cellule sélectionnée : " + selectedValue);
							             }
							         }
							     }
							 });
							 // Ajouter le JTable dans la fenêtre
							 JScrollPane scrollPane = new JScrollPane(table);
							 FrameT.add(scrollPane);
							 PanelR.add(scrollPane);
							  FrameT.add(PanelR);

						        // Rendre le JFrame FrameT visible après avoir ajouté le panneau
						        FrameT.setVisible(true);
						        JButton enregistrer = new JButton("Enregistrer les modifications");
					            PanelR.add(enregistrer);
						        enregistrer.addActionListener(new ActionListener() {
					                @Override
					                public void actionPerformed(ActionEvent e) {
					                    try {
					                        Connection conn = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
					                        String updateSql = "UPDATE reclamation SET `date d'ajout` = ?, etat_reclamation = ?, id_utilisateur = ?, id_technicien = ?, description = ? WHERE id_reclamation = ?";
					                        PreparedStatement pstmt = conn.prepareStatement(updateSql);

					                        // Parcourir le modèle de la table pour récupérer les modifications
					                        for (int row = 0; row < tableModel.getRowCount(); row++) {
					                            String id_reclamation = (String) tableModel.getValueAt(row, 0);
					                            String date_ajout = (String) tableModel.getValueAt(row, 1);
					                            String etat_reclamation = (String) tableModel.getValueAt(row, 2);
					                            String id_utilisateur = (String) tableModel.getValueAt(row, 3);
					                            String id_technicien = (String) tableModel.getValueAt(row, 4);
					                            String description = (String) tableModel.getValueAt(row, 5);

					                            pstmt.setString(1, date_ajout);
					                            pstmt.setString(2, etat_reclamation);
					                            pstmt.setString(3, id_utilisateur);
					                            pstmt.setString(4, id_technicien);
					                            pstmt.setString(5, description);
					                            pstmt.setString(6, id_reclamation);

					                            int rowsAffected = pstmt.executeUpdate();
					                            if (rowsAffected > 0) {
					                            	JPanel panel00 = new JPanel();
					                            	JButton boutton = new JButton("ajouter une observation");
					                            	panel00.add(boutton);
					                            	
					                            	 JOptionPane optionPane = new JOptionPane(panel00, JOptionPane.INFORMATION_MESSAGE, JOptionPane.DEFAULT_OPTION);

					                                 // Ajouter le bouton personnalisé à la boîte de dialogue
					                                 JDialog dialog = optionPane.createDialog(null, "Succès");
					                            	 boutton.addActionListener(new ActionListener() {
														
														@Override
														public void actionPerformed(ActionEvent e) {
															// L'utilisateur a cliqué sur le bouton 
						                            		 System.out.println("bouton cliqué.");
						                            		 String userInput = JOptionPane.showInputDialog(null, "Entrez votre texte :");

						                            		 try {
						     							        String query3 = "INSERT INTO observation (id_observation,`date d'ajout`,id_technicien,id_administrateur,description) VALUES (?,?,?,?,?)";
						     							        PreparedStatement Statement3 = conn.prepareStatement(query3);
						     							        String technicien = textField44.getText();
						     							        String id_administrateur = "A346451";
						     							       Random random = new Random();

						     							       
						     							        int randomInt = random.nextInt(101);
						     							        
						     							        Statement3.setLong(1,randomInt);
						     							       java.util.Date javaDate = new java.util.Date(); // Date actuelle
													            java.sql.Date sqlDate = new java.sql.Date(javaDate.getTime());
													            Statement3.setDate(2, sqlDate);
						     						            Statement3.setString(3,technicien);
						     						            Statement3.setString(4,id_administrateur);
						     						            Statement3.setString(5, userInput);
						     						            

						     						            //Statement3.executeUpdate();
						     						         // Exécuter la requête d'insertion
						     						            @SuppressWarnings("unused")
																int rowAffected = Statement3.executeUpdate();

						     						            // Vérifier si l'insertion a réussi
						     						            if (rowsAffected > 0) {
						     						                JOptionPane.showMessageDialog(null, "observation insérée avec succès !");
						     						            } else {
						     						                JOptionPane.showMessageDialog(null, "Erreur d'insertion");
						     						            }


						     						            Statement3.close();
						     						            conn.close();
						     							    }
						     							    catch(Exception E){
						     							    	 E.printStackTrace();				    	
						     							    }
						                                     
														}
														 
													}); 
					                                 dialog.setVisible(true);
					                            } 
					                        }

					                        pstmt.close();
					                        conn.close();
					                    } catch (SQLException ex) {
					                        ex.printStackTrace();
					                    }
					                }
					            });
						        		
						        	
						        PanelR.add(enregistrer);
						        FrameT.add(PanelR);    
						            
						}
					});
		            
		            
		            
		            PanelR.setBackground(Color.white);
		            
		            
					try {
				        Connection conn= DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
				    
				        String query11 = "SELECT nom_technicien,id_technicien,mot_de_passe FROM technicien ";
				        Statement statement = conn.createStatement();
						ResultSet resultSet = statement.executeQuery(query11);
				        while(resultSet.next())
				        {
				        	 String id_technicien = resultSet.getString(2);
				        	 
				        	String mot_de_passe = resultSet.getString(3);
				        	 
				        	 String login =textField44.getText();
				        	 String mdp = textField55.getText();
				        	 if( id_technicien.equals(login) && mot_de_passe.equals(mdp) )
				        	 {
				        		 FrameT.setVisible(true);
				        	 }
				        	 else {
				        		 JOptionPane.showMessageDialog(null,"Mot de passe erroné");
				        	 }
				        	
				        }
				        resultSet.close();
			            conn.close();
				    }
				    catch(Exception E){
				    	 E.printStackTrace();				    	
				    }
					
				}
			});
            frame_technicien.add(PanelT);
            
            
			
		}
	});
      Panneau.add(technicien);
      JLabel Label111 = new JLabel("TECHNICIEN");
      Label111.setForeground(MyColor.WHITE);
      constraints.gridx=3;
      constraints.gridy=1;
      Panneau.add(Label111,constraints);
      

      constraints.gridx = -5;
      constraints.gridy = -5;
      constraints.anchor = GridBagConstraints.WEST;
      //Panneau.add(label,constraints);
      add(Panneau);

 
   this.setVisible(true);
	    	
	    }

		
		
 	    
	    
	     
	    

public static void main(String[] args) 
{
	new applicationInterface ();
}
}