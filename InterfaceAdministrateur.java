package gestionMachines;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import gestionMachines.ScaledImage;


@SuppressWarnings({ "serial", "unused" })
public class InterfaceAdministrateur extends JFrame{
	 private static final String DB_URL = "jdbc:mysql://localhost:3306/gestion_machines";
	    private static final String DB_USERNAME = "root";
	    private static final String DB_PASSWORD = "";
	    JPanel imagePanel = new JPanel(new BorderLayout());
	public InterfaceAdministrateur() {
        @SuppressWarnings("unused")
		GridBagConstraints constraints = new GridBagConstraints();
		 ScaledImage imageClose = new ScaledImage(InterfaceAdministrateur.class.getResource("close.png"), 16, 16);
         
		setTitle("Menu Administrateur");
      
        setSize(700,700);
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setUndecorated(true);
        // Créez un JPanel pour le contenu de la fenêtre
        JPanel contentPanel = new JPanel(new BorderLayout());
        //contentPanel.setBackground(MyColor.WHITE);
		getContentPane().add(contentPanel);
		
		
		

        // Créez un JSplitPane pour diviser la fenêtre en deux parties
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        contentPanel.add(splitPane, BorderLayout.CENTER);

        // Créez un JPanel pour le menu à gauche
        JPanel menuPanel = new JPanel(new GridLayout(0, 1));
        // Utilisez GridLayout avec 1 colonne pour empiler les boutons verticalement
        menuPanel.setBackground(MyColor.PLACEHOLDER);
        
        JMenuBar menuBar = createMenuBar(); // Méthode pour créer le menu
        menuPanel.add(menuBar);
        JPanel menuWrapper = new JPanel();
        menuWrapper.setPreferredSize(new Dimension(50, 60)); // Définir la taille préférée ici
        menuWrapper.setBackground(Color.WHITE);
        menuWrapper.add(menuBar);
        
        menuPanel.add(menuWrapper);
        
        splitPane.setLeftComponent(menuPanel);

        // Créez un JPanel pour l'image à droite
       
        imagePanel.setLayout(new BorderLayout());
        imagePanel.setBackground(MyColor.SECONDARY);
        ImageIcon backgroundImage =new ImageIcon("C:\\Users\\hp\\Documents\\radeef.jpg");
        Image originalImage = backgroundImage.getImage();

        // Redimensionner l'image
        int newWidth = 400;
        int newHeight = 350;
        Image resizedImage = originalImage.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);

        // Créer un nouveau ImageIcon à partir de l'image redimensionnée
        ImageIcon resizedIcon = new ImageIcon(resizedImage);
        JLabel backgroundImag = new JLabel(resizedIcon);
        imagePanel.add(backgroundImag, BorderLayout.CENTER);
      
        JLabel label = new JLabel("<html><div style='text-align: center;'>Bienvenue dans l'interface utilisateur : vous pouvez ajouter vos réclamations concernant vos machines</div></html>");
        label.setForeground(Color.WHITE);
        label.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 20));
        imagePanel.add(label,BorderLayout.NORTH);
       
        splitPane.setRightComponent(imagePanel);
        JPanel closePanel = new JPanel(new BorderLayout());
        closePanel.setBackground(MyColor.SECONDARY);
        closePanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        closePanel.setPreferredSize(new Dimension(30, 30));
        JLabel lblClose = new JLabel("");
		lblClose.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int confirmation = JOptionPane.showConfirmDialog(null, "Are you sure you want to close this application?", "Confirmation", JOptionPane.WARNING_MESSAGE);

				if(confirmation == 0) {
					 dispose();
				}
			}
		});
		lblClose.setHorizontalAlignment(SwingConstants.CENTER);
		lblClose.setBounds(567, 5, 24, 24);
		lblClose.setIcon(imageClose.icon);
		closePanel.add(lblClose, BorderLayout.EAST);
        imagePanel.add(closePanel, BorderLayout.NORTH);
		
        // Ajoutez le JPanel à la fenêtre principale
        add(contentPanel);
    }

    private JMenuBar createMenuBar() {
    	ScaledImage icon = new ScaledImage(InterfaceUtilisateur.class.getResource("menu.png"), 18, 18);
        JLabel lblMenuIcon = new JLabel("");
        lblMenuIcon.setIcon(icon.icon);
        JMenuBar menuBar = new JMenuBar();
        JMenu mainMenu = new JMenu("Menu Principal");
        mainMenu.setOpaque(true);
        mainMenu.setBackground(MyColor.SECONDARY);
        mainMenu.setForeground(MyColor.WHITE);
        mainMenu.setIcon(icon.icon); 
        JMenu fileMenu = new JMenu("Gestion Utilisateurs");
        fileMenu.setOpaque(true);
        fileMenu.setBackground(MyColor.SECONDARY);
        fileMenu.setForeground(MyColor.WHITE);
        
        JMenu fileMenu1 = new JMenu("Observations des techniciens");
        fileMenu1.setOpaque(true);
        fileMenu1.setBackground(MyColor.SECONDARY);
        fileMenu1.setForeground(MyColor.WHITE);
        JMenuItem newItem1 = new JMenuItem("liste des observations");
        newItem1.setOpaque(true);
        newItem1.setBackground(MyColor.SECONDARY);
        newItem1.setForeground(MyColor.WHITE);
        
        newItem1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel tableModel;
				 List<String> observations = new ArrayList<>();
				 try {
					 Connection conn= DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
			            // Créer la requête SQL
					 String sql = "SELECT id_observation, `date d'ajout`,id_technicien,id_administrateur, description FROM observation ";
			            // Exécuter la requête SQL
			            Statement statement = conn.createStatement();
			            ResultSet resultSet = statement.executeQuery(sql);

			            // Parcourir les résultats de la requête et ajouter les réclamations à la liste
			            while (resultSet.next()) {
			                String id_observation = resultSet.getString(1);
			                String date_ajout = resultSet.getString(2);
			                String id_technicien = resultSet.getString(3);
			                String id_administrateur = resultSet.getString(4);
			                
			                String description = resultSet.getString(5);
			                		
			                observations.add(id_observation);
			                observations.add(date_ajout);
			               
			                
			                observations.add(id_technicien);
			                observations.add(id_administrateur);
			                observations.add(description);
			            }

			            // Fermer les ressources
			            resultSet.close();
			            statement.close();
			        } catch (SQLException E) {
			            E.printStackTrace();
			        }
				 String[] columnNames = {"ID Observation", "Date d'ajout","ID TECHNICIEN", "ID Administrateur", "Description"};

				    

				    tableModel = new DefaultTableModel(columnNames, 0); // 0 indique qu'il n'y a aucune ligne au départ

				 // Ajouter les réclamations au modèle du tableau
				 for (int i = 0; i < observations.size(); i += 5) { 
				     String id_observation = observations.get(i);
				     String date_ajout = observations.get(i + 1);
				     String id_technicien = observations.get(i + 2);
				     String id_administrateur = observations.get(i + 3);
				   
				     String description = observations.get(i + 4);

				     tableModel.addRow(new Object[]{id_observation, date_ajout,id_technicien,id_administrateur,description});
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
				 imagePanel.add(scrollPane, BorderLayout.CENTER);
				 //add(imagePanel);
				 
				 
				
				
				 setVisible(true);
				
			}
		});
        fileMenu1.add(newItem1);
        JMenuItem newItem = new JMenuItem("Nouveau utilisateur");
        newItem.setOpaque(true);
        newItem.setBackground(MyColor.SECONDARY);
        newItem.setForeground(MyColor.WHITE);
        newItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				//INTERFACE D'AJOUT DE NOUVEAUX UTILISATEURS:
				JFrame newFrame = new JFrame();
		        newFrame.setTitle("Ajout des utilisateurs");
		        newFrame.setSize(500, 500);
		        newFrame.setLocationRelativeTo(null);
		        newFrame.setVisible(true);
		        newFrame.setLayout(new BoxLayout(newFrame.getContentPane(), BoxLayout.Y_AXIS));

		        JPanel PANEL = new JPanel();
		        PANEL.setLayout(new GridBagLayout());
		        PANEL.setBackground(MyColor.SECONDARY);
		        GridBagConstraints constraints = new GridBagConstraints();
		        constraints.fill = GridBagConstraints.HORIZONTAL;
		        constraints.insets = new Insets(5, 10, 0, 10);

		        JLabel labell = new JLabel("Ajouter de nouveaux utilisateurs:");
		        constraints.gridx = 0;
		        constraints.gridy = 0;
		        constraints.anchor = GridBagConstraints.WEST;
		        labell.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 30));
		        labell.setForeground(Color.WHITE);
		        PANEL.add(labell, constraints);

		        JTextField textfield11 = createPlaceholderTextField("Nom et Prénom");
		        constraints.gridy = 1;
		        PANEL.add(textfield11, constraints);
		        textfield11.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

		        JTextField textfield12 = createPlaceholderTextField("Matricule");
		        constraints.gridy = 2;
		        PANEL.add(textfield12, constraints);
		        textfield12.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

		        JTextField textfield13 = createPlaceholderTextField("ID Service");
		        constraints.gridy = 3;
		        PANEL.add(textfield13, constraints);
		        textfield13.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

		        JTextField textfield14 = createPlaceholderTextField("Mot de passe");
		        constraints.gridy = 4;
		        PANEL.add(textfield14, constraints);
		        textfield14.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

		        // Ajouter d'autres JTextField si nécessaire en utilisant le même modèle ci-dessus

		        newFrame.add(PANEL);
		        newFrame.pack();
		    

			

		    
				
		           
				
		           //boutton pour ajouter de nouveaux utilisateurs a la base de données
		           JButton button12 = new JButton("AJOUTER");
		           //button12.setBounds(305, 291, 281, 38);
		           button12.setBackground(MyColor.SECONDARY2);
		           button12.setForeground(MyColor.WHITE);
		           constraints.gridy = 5;
		           PANEL.add(button12,constraints);
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
						        String mot_de_passe = textfield14.getText();
					            Statement1.setString(1,nom_prenom);
					            Statement1.setString(2, matricule);
					            Statement1.setString(3, id_service);
					            Statement1.setString(5, mot_de_passe);
					            java.util.Date javaDate = new java.util.Date(); // Date actuelle
					            java.sql.Date sqlDate = new java.sql.Date(javaDate.getTime());
					            Statement1.setDate(4, sqlDate);


					            int rowsAffected = Statement1.executeUpdate();

					            // Vérifier si l'insertion a réussi
					            if (rowsAffected > 0) {
					                JOptionPane.showMessageDialog(null, "Utilisateur inséré avec succès !");
					            } else {
					                JOptionPane.showMessageDialog(null, "Erreur d'insertion");
					            }

					            Statement1.close();
					            conn.close();
						    }
						    catch(Exception E){
						    	 E.printStackTrace();				    	
						    }
						    

						
						
					}
		           
				});
		           
		           
		           newFrame.add(PANEL);
    
			}
			});
	    
		

        JMenuItem exitItem = new JMenuItem("supprimer un utilisateur");
        exitItem.setOpaque(true);
        exitItem.setBackground(MyColor.SECONDARY);
        exitItem.setForeground(MyColor.WHITE);
        exitItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JFrame newFrame = new JFrame();
		        newFrame.setTitle("suppression des utilisateurs");
		        newFrame.setSize(500, 500);
		        newFrame.setLocationRelativeTo(null);
		        newFrame.setVisible(true);
		        newFrame.setLayout(new BoxLayout(newFrame.getContentPane(), BoxLayout.Y_AXIS));

		        JPanel PANEL = new JPanel();
		        PANEL.setLayout(new GridBagLayout());
		        PANEL.setBackground(MyColor.SECONDARY);
		        GridBagConstraints constraints = new GridBagConstraints();
		        constraints.fill = GridBagConstraints.HORIZONTAL;
		        constraints.insets = new Insets(5, 10, 0, 10);

		        JLabel labell = new JLabel("supprimer un utilisateur:");
		        constraints.gridx = 0;
		        constraints.gridy = 0;
		        constraints.anchor = GridBagConstraints.WEST;
		        labell.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 30));
		        labell.setForeground(Color.WHITE);
		        PANEL.add(labell, constraints);

		        JTextField textfield11 = createPlaceholderTextField("ID de l'utilisateur à supprimer");
		        constraints.gridy = 1;
		        PANEL.add(textfield11, constraints);
		        textfield11.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

		        // Ajouter d'autres JTextField si nécessaire en utilisant le même modèle ci-dessus

		        newFrame.add(PANEL);
		        newFrame.pack();
				
		           //boutton pour supprimer des utilisateurs a la base de données
		           JButton button12 = new JButton("SUPPRIMER");
		           //button12.setBounds(305, 291, 281, 38);
		           button12.setBackground(MyColor.SECONDARY2);
		           button12.setForeground(MyColor.WHITE);
		           constraints.gridy = 2;
		           PANEL.add(button12);
		           button12.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						String id_utilisateur = textfield11.getText();
						try {
				            Connection conn = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
				            

				            // Construire la requête paramétrée pour supprimer les enregistrements
				            String deleteQuery = "DELETE FROM utilisateur WHERE matricule='" + id_utilisateur + "'";				            PreparedStatement pstmt = conn.prepareStatement(deleteQuery);

				            // Exécuter la requête pour supprimer les enregistrements correspondants
				            int rowsAffected = pstmt.executeUpdate();

				            if (rowsAffected > 0) {
				            	JOptionPane.showMessageDialog(null,"Les enregistrements ont été supprimés avec succès.");
				            } else {
				                JOptionPane.showMessageDialog(null,"Aucun enregistrement n'a été supprimé. Veuillez vérifier votre condition.");
				            }

				            pstmt.close();
				            conn.close();
				        } catch (SQLException E) {
				            E.printStackTrace();
				        }
				    }
						
						
					
				});
              				
			}
		});
        fileMenu.add(newItem);
        fileMenu.add(exitItem);

        JMenu helpMenu = new JMenu("Gestion Machines");
        helpMenu.setOpaque(true);
        helpMenu.setBackground(MyColor.SECONDARY);
        helpMenu.setForeground(MyColor.WHITE);
        JMenuItem aboutItem = new JMenuItem("nouvelle machine");
        aboutItem.setOpaque(true);
        aboutItem.setBackground(MyColor.SECONDARY);
        aboutItem.setForeground(MyColor.WHITE);
        aboutItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JFrame newFrame = new JFrame();
		        newFrame.setTitle("Ajout des machines");
		        newFrame.setSize(500, 500);
		        newFrame.setLocationRelativeTo(null);
		        newFrame.setVisible(true);
		        newFrame.setLayout(new BoxLayout(newFrame.getContentPane(), BoxLayout.Y_AXIS));

		        JPanel PANEL = new JPanel();
		        PANEL.setLayout(new GridBagLayout());
		        PANEL.setBackground(MyColor.SECONDARY);
		        GridBagConstraints constraints = new GridBagConstraints();
		        constraints.fill = GridBagConstraints.HORIZONTAL;
		        constraints.insets = new Insets(5, 10, 0, 10);

		        JLabel labell = new JLabel("Ajouter de nouvelles machines:");
		        constraints.gridx = 0;
		        constraints.gridy = 0;
		        constraints.anchor = GridBagConstraints.WEST;
		        labell.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 30));
		        labell.setForeground(Color.WHITE);
		        PANEL.add(labell, constraints);

		        JTextField textfield11 = createPlaceholderTextField("nom de machine");
		        constraints.gridy = 1;
		        PANEL.add(textfield11, constraints);
		        textfield11.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

		        JTextField textfield12 = createPlaceholderTextField("port réseau");
		        constraints.gridy = 2;
		        PANEL.add(textfield12, constraints);
		        textfield12.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

		        JTextField textfield13 = createPlaceholderTextField("adresse IP");
		        constraints.gridy = 3;
		        PANEL.add(textfield13, constraints);
		        textfield13.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

		        JTextField textfield14 = createPlaceholderTextField("switch connecté");
		        constraints.gridy = 4;
		        PANEL.add(textfield14, constraints);
		        textfield14.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		        
		        JTextField textfield15 = createPlaceholderTextField("Etat");
		        constraints.gridy = 5;
		        PANEL.add(textfield15, constraints);
		        textfield15.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		        
		        JTextField textfield16 = createPlaceholderTextField("Type de la Machine");
		        constraints.gridy = 6;
		        PANEL.add(textfield16, constraints);
		        textfield16.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		        
		        JTextField textfield17 = createPlaceholderTextField("ID utilisateur");
		        constraints.gridy = 7;
		        PANEL.add(textfield17, constraints);
		        textfield17.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));




		        // Ajouter d'autres JTextField si nécessaire en utilisant le même modèle ci-dessus

		        newFrame.add(PANEL);
		        newFrame.pack();
		    

			

		    
				
		           
				
		           //boutton pour ajouter de nouveaux utilisateurs a la base de données
		           JButton button12 = new JButton("AJOUTER");
		           //button12.setBounds(305, 291, 281, 38);
		           button12.setBackground(MyColor.SECONDARY2);
		           button12.setForeground(MyColor.WHITE);
		           constraints.gridy = 8;
		           button12.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						try {
					        Connection conn= DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
					    
					        String query6 = "INSERT INTO machine (nom_machine,port_reseau,adresse_ip,switch_connecte,type_machine,etat,id_utilisateur,`date d'affectation`) VALUES (?,?,?,?,?,?,?,?)";
					        PreparedStatement Statement5 = conn.prepareStatement(query6);
					        String nom_machine = textfield11.getText();
					        String port_reseau = textfield12.getText();
					        String adresse_ip = textfield13.getText();
					        String switch_connecte = textfield14.getText();
					        String type_machine = textfield15.getText();
					        String etat = textfield16.getText();
					        String id_utilisateur = textfield17.getText();

					        

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
		           
		           PANEL.add(button12,constraints);				
			}
		});
        
        
        
        helpMenu.add(aboutItem);
        JMenuItem aboutItem2 = new JMenuItem("modifier une machine");
        aboutItem2.setOpaque(true);
        aboutItem2.setBackground(MyColor.SECONDARY);
        aboutItem2.setForeground(MyColor.WHITE);
        aboutItem2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JFrame newFrame = new JFrame();
		        newFrame.setTitle("suppression des divisions");
		        newFrame.setSize(500, 500);
		        newFrame.setLocationRelativeTo(null);
		        newFrame.setVisible(true);
		        newFrame.setLayout(new BoxLayout(newFrame.getContentPane(), BoxLayout.Y_AXIS));

		        JPanel PANEL = new JPanel();
		        PANEL.setLayout(new GridBagLayout());
		        PANEL.setBackground(MyColor.SECONDARY);
		        GridBagConstraints constraints = new GridBagConstraints();
		        constraints.fill = GridBagConstraints.HORIZONTAL;
		        constraints.insets = new Insets(5, 10, 0, 10);

		        JLabel labell = new JLabel("modifier une machine:");
		        constraints.gridx = 0;
		        constraints.gridy = 0;
		        constraints.anchor = GridBagConstraints.WEST;
		        labell.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 30));
		        labell.setForeground(Color.WHITE);
		        PANEL.add(labell, constraints);

		        JTextField textfield11 = createPlaceholderTextField("nom de la machine a modifier");
		        constraints.gridy = 1;
		        PANEL.add(textfield11, constraints);
		        textfield11.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		        

		        JTextField textfield12 = createPlaceholderTextField("ID de nouveau utilisateur");
		        constraints.gridy = 2;
		        PANEL.add(textfield12, constraints);
		        textfield12.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

		        // Ajouter d'autres JTextField si nécessaire en utilisant le même modèle ci-dessus

		        newFrame.add(PANEL);
		        newFrame.pack();
				
		           //boutton pour supprimer des utilisateurs a la base de données
		           JButton button12 = new JButton("MODIFIER");
		           //button12.setBounds(305, 291, 281, 38);
		           button12.setBackground(MyColor.SECONDARY2);
		           button12.setForeground(MyColor.WHITE);
		           constraints.gridy = 3;
		           PANEL.add(button12);	
		           button12.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						
					try {
			        Connection conn= DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
			        String sql = "UPDATE machine SET id_utilisateur =?  WHERE nom_machine=?";
			        PreparedStatement pstmt = conn.prepareStatement(sql);
			        String nouvelleValeur1 = textfield11.getText();
		            String nouvelleValeur2 = textfield12.getText();
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
			}
		});
        
        helpMenu.add(aboutItem2);
        JMenuItem aboutItemm = new JMenuItem("liste des machines");
        aboutItemm.setOpaque(true);
        aboutItemm.setBackground(MyColor.SECONDARY);
        aboutItemm.setForeground(MyColor.WHITE);
        aboutItemm.addActionListener(new ActionListener() {
			
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
						 table.setBackground(Color.WHITE);
						
						 // Ajouter le JTable dans la fenêtre
						 JScrollPane scrollPane = new JScrollPane(table);
						 PanelM.setBackground(MyColor.WHITE);
						 scrollPane.setBackground(MyColor.SECONDARY);
						 FrameM.add(scrollPane);
						 PanelM.add(scrollPane);
						  FrameM.add(PanelM);

					        // Rendre le JFrame FrameT visible après avoir ajouté le panneau
					        FrameM.setVisible(true);
							
			}
		});
        
        helpMenu.add(aboutItemm);
        JMenu depMenu = new JMenu("Gestion departements");
        depMenu.setOpaque(true);
        depMenu.setBackground(MyColor.SECONDARY);
        depMenu.setForeground(MyColor.WHITE);
        
        JMenuItem aboutItem3 = new JMenuItem("ajouter un departement");
        aboutItem3.setOpaque(true);
        aboutItem3.setBackground(MyColor.SECONDARY);
        aboutItem3.setForeground(MyColor.WHITE);
        aboutItem3.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				//INTERFACE D'AJOUT DE NOUVEAUX UTILISATEURS:
				JFrame newFrame = new JFrame();
		        newFrame.setTitle("Ajout des utilisateurs");
		        newFrame.setSize(500, 500);
		        newFrame.setLocationRelativeTo(null);
		        newFrame.setVisible(true);
		        newFrame.setLayout(new BoxLayout(newFrame.getContentPane(), BoxLayout.Y_AXIS));

		        JPanel PANEL = new JPanel();
		        PANEL.setLayout(new GridBagLayout());
		        PANEL.setBackground(MyColor.SECONDARY);
		        GridBagConstraints constraints = new GridBagConstraints();
		        constraints.fill = GridBagConstraints.HORIZONTAL;
		        constraints.insets = new Insets(5, 10, 0, 10);

		        JLabel labell = new JLabel("Ajouter de nouveaux départements:");
		        constraints.gridx = 0;
		        constraints.gridy = 0;
		        constraints.anchor = GridBagConstraints.WEST;
		        labell.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 30));
		        labell.setForeground(Color.WHITE);
		        PANEL.add(labell, constraints);

		        JTextField textfield11 = createPlaceholderTextField("Nom de département");
		        constraints.gridy = 1;
		        PANEL.add(textfield11, constraints);
		        textfield11.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

		        JTextField textfield12 = createPlaceholderTextField("ID de département");
		        constraints.gridy = 2;
		        PANEL.add(textfield12, constraints);
		        textfield12.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

		        

		        newFrame.add(PANEL);
		        newFrame.pack();
		 
				
		           //boutton pour ajouter de nouveaux utilisateurs a la base de données
		           JButton button12 = new JButton("AJOUTER");
		           //button12.setBounds(305, 291, 281, 38);
		           button12.setBackground(MyColor.SECONDARY2);
		           button12.setForeground(MyColor.WHITE);
		           constraints.gridy = 3;
		           PANEL.add(button12,constraints);
		           button12.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						try {
					        Connection conn= DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
					    
					        String query2 = "INSERT INTO departement (id_departement,nom_departement) VALUES (?,?)";
					        PreparedStatement Statement2 = conn.prepareStatement(query2);
					        String nom_departement = textfield11.getText();
					        String id_departement = textfield12.getText();
					        

				            Statement2.setString(2,nom_departement);
				            Statement2.setString(1, id_departement);
				            

				            int rowsAffected = Statement2.executeUpdate();

				            // Vérifier si l'insertion a réussi
				            if (rowsAffected > 0) {
				                JOptionPane.showMessageDialog(null, "Machine insérée avec succès !");
				            } else {
				                JOptionPane.showMessageDialog(null, "Erreur d'insertion");
				            }

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
        
        depMenu.add(aboutItem3);
         
        JMenuItem aboutItem4 = new JMenuItem("supprimer un departement");
        aboutItem4.setOpaque(true);
        aboutItem4.setBackground(MyColor.SECONDARY);
        aboutItem4.setForeground(MyColor.WHITE);
        aboutItem4.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JFrame newFrame = new JFrame();
		        newFrame.setTitle("suppression des departement");
		        newFrame.setSize(500, 500);
		        newFrame.setLocationRelativeTo(null);
		        newFrame.setVisible(true);
		        newFrame.setLayout(new BoxLayout(newFrame.getContentPane(), BoxLayout.Y_AXIS));

		        JPanel PANEL = new JPanel();
		        PANEL.setLayout(new GridBagLayout());
		        PANEL.setBackground(MyColor.SECONDARY);
		        GridBagConstraints constraints = new GridBagConstraints();
		        constraints.fill = GridBagConstraints.HORIZONTAL;
		        constraints.insets = new Insets(5, 10, 0, 10);

		        JLabel labell = new JLabel("supprimer un departement:");
		        constraints.gridx = 0;
		        constraints.gridy = 0;
		        constraints.anchor = GridBagConstraints.WEST;
		        labell.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 30));
		        labell.setForeground(Color.WHITE);
		        PANEL.add(labell, constraints);

		        JTextField textfield11 = createPlaceholderTextField("ID de departement à supprimer");
		        constraints.gridy = 1;
		        PANEL.add(textfield11, constraints);
		        textfield11.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

		        // Ajouter d'autres JTextField si nécessaire en utilisant le même modèle ci-dessus

		        newFrame.add(PANEL);
		        newFrame.pack();
				
		           //boutton pour supprimer des utilisateurs a la base de données
		           JButton button12 = new JButton("SUPPRIMER");
		           //button12.setBounds(305, 291, 281, 38);
		           button12.setBackground(MyColor.SECONDARY2);
		           button12.setForeground(MyColor.WHITE);
		           constraints.gridy = 2;
		           PANEL.add(button12);
		           button12.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						String id_departement = textfield11.getText();
						try {
				            Connection conn = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
				            

				            // Construire la requête paramétrée pour supprimer les enregistrements
				            String deleteQuery = "DELETE FROM departement WHERE id_departement='" + id_departement + "'";				            PreparedStatement pstmt = conn.prepareStatement(deleteQuery);

				            // Exécuter la requête pour supprimer les enregistrements correspondants
				            int rowsAffected = pstmt.executeUpdate();

				            if (rowsAffected > 0) {
				            	JOptionPane.showMessageDialog(null,"Les enregistrements ont été supprimés avec succès.");
				            } else {
				                JOptionPane.showMessageDialog(null,"Aucun enregistrement n'a été supprimé. Veuillez vérifier votre condition.");
				            }

				            pstmt.close();
				            conn.close();
				        } catch (SQLException E) {
				            E.printStackTrace();
				        }
					}
					});
				
			}
		});
        
        depMenu.add(aboutItem4);
        
        JMenu serMenu = new JMenu("Gestion services");
        serMenu.setOpaque(true);
        serMenu.setBackground(MyColor.SECONDARY);
        serMenu.setForeground(MyColor.WHITE);
        
        JMenuItem aboutItem5 = new JMenuItem("ajouter un service");
        aboutItem5.setOpaque(true);
        aboutItem5.setBackground(MyColor.SECONDARY);
        aboutItem5.setForeground(MyColor.WHITE);
        aboutItem5.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				//INTERFACE D'AJOUT DE NOUVEAUX services:
				JFrame newFrame = new JFrame();
		        newFrame.setTitle("Ajout des services");
		        newFrame.setSize(500, 500);
		        newFrame.setLocationRelativeTo(null);
		        newFrame.setVisible(true);
		        newFrame.setLayout(new BoxLayout(newFrame.getContentPane(), BoxLayout.Y_AXIS));

		        JPanel PANEL = new JPanel();
		        PANEL.setLayout(new GridBagLayout());
		        PANEL.setBackground(MyColor.SECONDARY);
		        GridBagConstraints constraints = new GridBagConstraints();
		        constraints.fill = GridBagConstraints.HORIZONTAL;
		        constraints.insets = new Insets(5, 10, 0, 10);

		        JLabel labell = new JLabel("Ajouter de nouveaux service:");
		        constraints.gridx = 0;
		        constraints.gridy = 0;
		        constraints.anchor = GridBagConstraints.WEST;
		        labell.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 30));
		        labell.setForeground(Color.WHITE);
		        PANEL.add(labell, constraints);

		        JTextField textfield11 = createPlaceholderTextField("Nom de service");
		        constraints.gridy = 1;
		        PANEL.add(textfield11, constraints);
		        textfield11.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

		        JTextField textfield12 = createPlaceholderTextField("ID de service");
		        constraints.gridy = 2;
		        PANEL.add(textfield12, constraints);
		        textfield12.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

		        JTextField textfield13 = createPlaceholderTextField("ID de division parent");
		        constraints.gridy = 2;
		        PANEL.add(textfield13, constraints);
		        textfield13.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

		        

		        newFrame.add(PANEL);
		        newFrame.pack();
		 
				
		           //boutton pour ajouter de nouveaux utilisateurs a la base de données
		           JButton button12 = new JButton("AJOUTER");
		           //button12.setBounds(305, 291, 281, 38);
		           button12.setBackground(MyColor.SECONDARY2);
		           button12.setForeground(MyColor.WHITE);
		           constraints.gridy = 4;
		           PANEL.add(button12,constraints);
		           button12.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						try {
					        Connection conn= DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
					    
					        String query4 = "INSERT INTO service (nom_service,id_service,id_division) VALUES (?,?,?)";
					        PreparedStatement Statement4 = conn.prepareStatement(query4);
					        String nom_service = textfield11.getText();
					        String id_service = textfield12.getText();
					        String id_division = textfield13.getText();
					        

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
        
        serMenu.add(aboutItem5);
        
        JMenuItem aboutItem6 = new JMenuItem("supprimer un service");
        aboutItem6.setOpaque(true);
        aboutItem6.setBackground(MyColor.SECONDARY);
        aboutItem6.setForeground(MyColor.WHITE);
        aboutItem6.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JFrame newFrame = new JFrame();
		        newFrame.setTitle("suppression des services");
		        newFrame.setSize(500, 500);
		        newFrame.setLocationRelativeTo(null);
		        newFrame.setVisible(true);
		        newFrame.setLayout(new BoxLayout(newFrame.getContentPane(), BoxLayout.Y_AXIS));

		        JPanel PANEL = new JPanel();
		        PANEL.setLayout(new GridBagLayout());
		        PANEL.setBackground(MyColor.SECONDARY);
		        GridBagConstraints constraints = new GridBagConstraints();
		        constraints.fill = GridBagConstraints.HORIZONTAL;
		        constraints.insets = new Insets(5, 10, 0, 10);

		        JLabel labell = new JLabel("supprimer un service:");
		        constraints.gridx = 0;
		        constraints.gridy = 0;
		        constraints.anchor = GridBagConstraints.WEST;
		        labell.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 30));
		        labell.setForeground(Color.WHITE);
		        PANEL.add(labell, constraints);

		        JTextField textfield11 = createPlaceholderTextField("ID de service à supprimer");
		        constraints.gridy = 1;
		        PANEL.add(textfield11, constraints);
		        textfield11.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

		        // Ajouter d'autres JTextField si nécessaire en utilisant le même modèle ci-dessus

		        newFrame.add(PANEL);
		        newFrame.pack();
				
		           //boutton pour supprimer des utilisateurs a la base de données
		           JButton button12 = new JButton("SUPPRIMER");
		           //button12.setBounds(305, 291, 281, 38);
		           button12.setBackground(MyColor.SECONDARY2);
		           button12.setForeground(MyColor.WHITE);
		           constraints.gridy = 2;
		           PANEL.add(button12);
		           button12.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						String id_service = textfield11.getText();
						try {
				            Connection conn = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
				            

				            // Construire la requête paramétrée pour supprimer les enregistrements
				            String deleteQuery = "DELETE FROM service WHERE id_departement='" + id_service + "'";				            PreparedStatement pstmt = conn.prepareStatement(deleteQuery);

				            // Exécuter la requête pour supprimer les enregistrements correspondants
				            int rowsAffected = pstmt.executeUpdate();

				            if (rowsAffected > 0) {
				            	JOptionPane.showMessageDialog(null,"Les enregistrements ont été supprimés avec succès.");
				            } else {
				                JOptionPane.showMessageDialog(null,"Aucun enregistrement n'a été supprimé. Veuillez vérifier votre condition.");
				            }

				            pstmt.close();
				            conn.close();
				        } catch (SQLException E) {
				            E.printStackTrace();
				        }
					}
					});
				
				
			}
		});
        
        serMenu.add(aboutItem6);
        
        JMenu divMenu = new JMenu("Gestion divisions");
        divMenu.setOpaque(true);
        divMenu.setBackground(MyColor.SECONDARY);
        divMenu.setForeground(MyColor.WHITE);
        JMenuItem aboutItem7 = new JMenuItem("ajouter une division");
        aboutItem7.setOpaque(true);
        aboutItem7.setBackground(MyColor.SECONDARY);
        aboutItem7.setForeground(MyColor.WHITE);
        aboutItem7.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				//INTERFACE D'AJOUT DE NOUVEAUX divisions:
				JFrame newFrame = new JFrame();
		        newFrame.setTitle("Ajout des divisions");
		        newFrame.setSize(500, 500);
		        newFrame.setLocationRelativeTo(null);
		        newFrame.setVisible(true);
		        newFrame.setLayout(new BoxLayout(newFrame.getContentPane(), BoxLayout.Y_AXIS));

		        JPanel PANEL = new JPanel();
		        PANEL.setLayout(new GridBagLayout());
		        PANEL.setBackground(MyColor.SECONDARY);
		        GridBagConstraints constraints = new GridBagConstraints();
		        constraints.fill = GridBagConstraints.HORIZONTAL;
		        constraints.insets = new Insets(5, 10, 0, 10);

		        JLabel labell = new JLabel("Ajouter de nouveaux divisions:");
		        constraints.gridx = 0;
		        constraints.gridy = 0;
		        constraints.anchor = GridBagConstraints.WEST;
		        labell.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 30));
		        labell.setForeground(Color.WHITE);
		        PANEL.add(labell, constraints);

		        JTextField textfield11 = createPlaceholderTextField("Nom de division");
		        constraints.gridy = 1;
		        PANEL.add(textfield11, constraints);
		        textfield11.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

		        JTextField textfield12 = createPlaceholderTextField("ID de division");
		        constraints.gridy = 2;
		        PANEL.add(textfield12, constraints);
		        textfield12.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

		        JTextField textfield13 = createPlaceholderTextField("ID de département parent");
		        constraints.gridy = 2;
		        PANEL.add(textfield13, constraints);
		        textfield13.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

		        

		        newFrame.add(PANEL);
		        newFrame.pack();
		 
				
		           //boutton pour ajouter de nouveaux division a la base de données
		           JButton button12 = new JButton("AJOUTER");
		           //button12.setBounds(305, 291, 281, 38);
		           button12.setBackground(MyColor.SECONDARY2);
		           button12.setForeground(MyColor.WHITE);
		           constraints.gridy = 4;
		           PANEL.add(button12,constraints);
		           button12.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						try {
						Connection conn= DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
					    
				        String query3 = "INSERT INTO division (nom_division,id_division,id_departement) VALUES (?,?,?)";
				        PreparedStatement Statement3 = conn.prepareStatement(query3);
				        String nom_division = textfield11.getText();
				        String id_division = textfield12.getText();
				        String id_departement = textfield13.getText();
				        

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
        divMenu.add(aboutItem7);

        JMenuItem aboutItem8 = new JMenuItem("supprimer une division");
        aboutItem8.setOpaque(true);
        aboutItem8.setBackground(MyColor.SECONDARY);
        aboutItem8.setForeground(MyColor.WHITE);
        aboutItem8.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JFrame newFrame = new JFrame();
		        newFrame.setTitle("suppression des divisions");
		        newFrame.setSize(500, 500);
		        newFrame.setLocationRelativeTo(null);
		        newFrame.setVisible(true);
		        newFrame.setLayout(new BoxLayout(newFrame.getContentPane(), BoxLayout.Y_AXIS));

		        JPanel PANEL = new JPanel();
		        PANEL.setLayout(new GridBagLayout());
		        PANEL.setBackground(MyColor.SECONDARY);
		        GridBagConstraints constraints = new GridBagConstraints();
		        constraints.fill = GridBagConstraints.HORIZONTAL;
		        constraints.insets = new Insets(5, 10, 0, 10);

		        JLabel labell = new JLabel("supprimer une division:");
		        constraints.gridx = 0;
		        constraints.gridy = 0;
		        constraints.anchor = GridBagConstraints.WEST;
		        labell.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 30));
		        labell.setForeground(Color.WHITE);
		        PANEL.add(labell, constraints);

		        JTextField textfield11 = createPlaceholderTextField("ID de division à supprimer");
		        constraints.gridy = 1;
		        PANEL.add(textfield11, constraints);
		        textfield11.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

		        // Ajouter d'autres JTextField si nécessaire en utilisant le même modèle ci-dessus

		        newFrame.add(PANEL);
		        newFrame.pack();
				
		           //boutton pour supprimer des utilisateurs a la base de données
		           JButton button12 = new JButton("SUPPRIMER");
		           //button12.setBounds(305, 291, 281, 38);
		           button12.setBackground(MyColor.SECONDARY2);
		           button12.setForeground(MyColor.WHITE);
		           constraints.gridy = 2;
		           PANEL.add(button12);
		           button12.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						String id_division = textfield11.getText();
						try {
				            Connection conn = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
				            

				            // Construire la requête paramétrée pour supprimer les enregistrements
				            String deleteQuery = "DELETE FROM division WHERE id_division='" + id_division + "'";				            PreparedStatement pstmt = conn.prepareStatement(deleteQuery);

				            // Exécuter la requête pour supprimer les enregistrements correspondants
				            int rowsAffected = pstmt.executeUpdate();

				            if (rowsAffected > 0) {
				            	JOptionPane.showMessageDialog(null,"Les enregistrements ont été supprimés avec succès.");
				            } else {
				                JOptionPane.showMessageDialog(null,"Aucun enregistrement n'a été supprimé. Veuillez vérifier votre condition.");
				            }

				            pstmt.close();
				            conn.close();
				        } catch (SQLException E) {
				            E.printStackTrace();
				        }
					}
					});
             				
			}
		});
        
        divMenu.add(aboutItem8);
        // Ajoutez les sous-menus au menu principal
        mainMenu.add(fileMenu);
        mainMenu.add(helpMenu);
        mainMenu.add(depMenu);
        mainMenu.add(serMenu);
        mainMenu.add(divMenu);
        mainMenu.add(fileMenu1);

        // Ajoutez le menu principal à la barre de menus
        menuBar.add(mainMenu,lblMenuIcon);

        return menuBar;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            InterfaceAdministrateur example = new InterfaceAdministrateur();
            example.setVisible(true);
        });
    }
    private static void onFocusGained(JTextField field, String placeholder) {
        if (field.getText().equals(placeholder)) {
            field.setText("");
            field.setForeground(MyColor.BLACK);
        }
    }

    private static void onFocusLost(JTextField field, String placeholder) {
        if (field.getText().isEmpty()) {
            field.setText(placeholder);
            field.setForeground(MyColor.PLACEHOLDER);
        }
    }
    private static JTextField createPlaceholderTextField(String placeholder) {
        JTextField textField = new JTextField(15);
        textField.setText(placeholder);
        textField.setForeground(Color.GRAY);
        textField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                onFocusGained(textField, placeholder);
            }

            @Override
            public void focusLost(FocusEvent e) {
                onFocusLost(textField, placeholder);
            }
        });
        textField.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.BLACK),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
        return textField;
    }
	}


