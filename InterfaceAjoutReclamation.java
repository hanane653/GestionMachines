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
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


@SuppressWarnings("serial")
public class InterfaceAjoutReclamation extends JFrame {
	private static final String DB_URL = "jdbc:mysql://localhost:3306/gestion_machines";
    private static final String DB_USERNAME = "root";
    private static final String DB_PASSWORD = "";
    public InterfaceAjoutReclamation() {
        setTitle("Ajout des réclamations");
        setBackground(Color.WHITE);
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Créez un JPanel pour le contenu de la fenêtre
        JPanel contentPanel = new JPanel(new BorderLayout());
        contentPanel.setBackground(MyColor.WHITE);
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
        JPanel imagePanel = new JPanel();
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
        JMenu fileMenu = new JMenu("Réclamation");
        fileMenu.setOpaque(true);
        fileMenu.setBackground(MyColor.SECONDARY);
        fileMenu.setForeground(MyColor.WHITE);
        
        JMenuItem newItem = new JMenuItem("Nouvelle réclamation");
        newItem.setOpaque(true);
        newItem.setBackground(MyColor.SECONDARY);
        newItem.setForeground(MyColor.WHITE);
        newItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Reclamation window = new Reclamation();
				window.frame.setVisible(true);
			
			}
		});
        JMenuItem exitItem = new JMenuItem("suivre ma réclamation");
        
        exitItem.setOpaque(true);
        exitItem.setBackground(MyColor.SECONDARY);
        exitItem.setForeground(MyColor.WHITE);
        exitItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JFrame newFrame = new JFrame();
		        newFrame.setTitle("Suivre ma réclamation");
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

		        JLabel labell = new JLabel("Entrer votre matricule afin de suivre votre réclamation:");
		        constraints.gridx = 0;
		        constraints.gridy = 0;
		        constraints.anchor = GridBagConstraints.WEST;
		        labell.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 30));
		        labell.setForeground(Color.WHITE);
		        PANEL.add(labell, constraints);

		        

		        JTextField textfield12 = createPlaceholderTextField("Matricule");
		        constraints.gridy = 2;
		        PANEL.add(textfield12, constraints);
		        textfield12.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

		        
		        // Ajouter d'autres JTextField si nécessaire en utilisant le même modèle ci-dessus

		        newFrame.add(PANEL);
		        newFrame.pack();
				
		           //boutton pour ajouter de nouveaux utilisateurs a la base de données
		           JButton button12 = new JButton("SUIVRE MA RECLAMATION");
		           //button12.setBounds(305, 291, 281, 38);
		           button12.setBackground(MyColor.SECONDARY2);
		           button12.setForeground(MyColor.WHITE);
		           constraints.gridy = 5;
		           PANEL.add(button12,constraints);
		           button12.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						DefaultTableModel tableModel;
						 List<String> reclamations = new ArrayList<>();
						 try {
							 Connection conn= DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
					            // Créer la requête SQL
							 String sql = "SELECT id_reclamation, `date d'ajout`, etat_reclamation, id_utilisateur, id_technicien, description FROM reclamation WHERE id_utilisateur = '" + textfield12.getText() + "'";
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
						 add(scrollPane);
						 
						
						
						 setVisible(true);
						
					}
				});
				
			}
		});
        fileMenu.add(newItem);
        fileMenu.add(exitItem);

        JMenu helpMenu = new JMenu("Aide");
        helpMenu.setOpaque(true);
        helpMenu.setBackground(MyColor.SECONDARY);
        helpMenu.setForeground(MyColor.WHITE);
        JMenuItem aboutItem = new JMenuItem("À propos");
        aboutItem.setOpaque(true);
        aboutItem.setBackground(MyColor.SECONDARY);
        aboutItem.setForeground(MyColor.WHITE);
        helpMenu.add(aboutItem);

        // Ajoutez les sous-menus au menu principal
        mainMenu.add(fileMenu);
        mainMenu.add(helpMenu);

        // Ajoutez le menu principal à la barre de menus
        menuBar.add(mainMenu,lblMenuIcon);

        return menuBar;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            InterfaceAjoutReclamation example = new InterfaceAjoutReclamation();
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
