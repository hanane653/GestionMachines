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
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import gestionMachines.ScaledImage;


@SuppressWarnings({ "serial", "unused" })
public class ReceptionReclamation extends JFrame{

    private static final String DB_URL = "jdbc:mysql://localhost:3306/gestion_machines";
    private static final String DB_USERNAME = "root";
    private static final String DB_PASSWORD = "";
    JPanel imagePanel = new JPanel(new BorderLayout());
    @SuppressWarnings("unused")
	private DefaultTableModel tableModel;
	public ReceptionReclamation() {
		 ScaledImage imageClose = new ScaledImage(ReceptionReclamation.class.getResource("close.png"), 16, 16);

		setTitle("Menu Technicien");
		
        


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
        //JPanel labelPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 10));
        //labelPanel.setOpaque(false);
        
        //labelPanel.setBackground(MyColor.SECONDARY);
        JLabel label = new JLabel("<html><div style='text-align: center;'>Bienvenue dans l'interface TECHNICIEN : vous pouvez Recevoir des réclamations at envoyer des observations à l'administrateur</div></html>");
        label.setForeground(MyColor.WHITE);
        label.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 30));
        //imagePanel.add(label);
        
        imagePanel.add(label, BorderLayout.CENTER);
         JPanel picPanel = new JPanel(new BorderLayout());
         picPanel.setBackground(MyColor.SECONDARY);
         
         picPanel.add(backgroundImag,BorderLayout.SOUTH);
         imagePanel	.add(picPanel,BorderLayout.SOUTH);
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
        JMenu fileMenu = new JMenu("Reclamations");
        fileMenu.setOpaque(true);
        fileMenu.setBackground(MyColor.SECONDARY);
        fileMenu.setForeground(MyColor.WHITE);
        mainMenu.add(fileMenu);
        JMenu fileMenu2 = new JMenu("Observations");
        fileMenu2.setOpaque(true);
        fileMenu2.setBackground(MyColor.SECONDARY);
        fileMenu2.setForeground(MyColor.WHITE);
        mainMenu.add(fileMenu2);
        JMenuItem component2 = new JMenuItem("envoyer une observation à l'administrateur");
        fileMenu2.add(component2);
        component2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JFrame newFrame = new JFrame();
		        newFrame.setTitle("Observations");
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

		        JLabel labell = new JLabel("envoyer une observation à l'administrateur:");
		        constraints.gridx = 0;
		        constraints.gridy = 0;
		        constraints.anchor = GridBagConstraints.WEST;
		        labell.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 30));
		        labell.setForeground(Color.WHITE);
		        PANEL.add(labell, constraints);

		        

		        JTextField textfield12 = createPlaceholderTextField("description");
		        constraints.gridy = 2;
		        PANEL.add(textfield12, constraints);
		        textfield12.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

		        
		        // Ajouter d'autres JTextField si nécessaire en utilisant le même modèle ci-dessus

		        newFrame.add(PANEL);
		        newFrame.pack();
				
		           //boutton pour ajouter de nouveaux utilisateurs a la base de données
		           JButton button12 = new JButton("envoyer l'observation");
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
					    
					        String query10 = "INSERT INTO observation (id_observation,`date d'ajout`,id_technicien,id_administrateur,description) VALUES (?,?,?,?,?)";
					        PreparedStatement Statement10 = conn.prepareStatement(query10);
					        String description = textfield12.getText();
					       
					        
							String id_administrateur ="A346451";
					        
					        String id_technicien = gestionMachines.InterfaceTechnicien.txtUsername.getText();
					        
					        Random random = new Random();

					        // Définir la valeur maximale (par exemple 100)
					        int maxValue = 100;

					        // Générer un nombre aléatoire compris entre 1 et maxValue (inclus)
					        int id_observation = random.nextInt(maxValue) + 1;
				            Statement10.setLong(1, id_observation);
				            Statement10.setString(4, id_administrateur);
				            
				            Statement10.setString(3, id_technicien);
				            Statement10.setString(5, description);
				           
				           
				            java.util.Date javaDate = new java.util.Date(); // Date actuelle
				            java.sql.Date sqlDate = new java.sql.Date(javaDate.getTime());
				            Statement10.setDate(2, sqlDate);


				            int rowsAffected = Statement10.executeUpdate();

				            // Vérifier si l'insertion a réussi
				            if (rowsAffected > 0) {
				                JOptionPane.showMessageDialog(null, "Observation envoyée avec succès !");
				            } else {
				                JOptionPane.showMessageDialog(null, "Erreur d'envoi");
				            }

				            Statement10.close();
				            conn.close();
					    }
					    catch(Exception E){
					    	 E.printStackTrace();				    	
					    }
					    

					
						
						
					}
						
					
				});
				
			}
		});
        JMenuItem component = new JMenuItem("listes des réclamations existantes");
        fileMenu.add(component);
        component.addActionListener(new ActionListener() {
	
			@Override
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel tableModel;
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
						 imagePanel.add(scrollPane);
						 fileMenu.add(component);
						
						 add(imagePanel);
						 setVisible(true);
						 
						  
				
			}
		});
        
        

        // Ajoutez le menu principal à la barre de menus
        menuBar.add(mainMenu,lblMenuIcon);

        return menuBar;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ReceptionReclamation example = new ReceptionReclamation();
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


